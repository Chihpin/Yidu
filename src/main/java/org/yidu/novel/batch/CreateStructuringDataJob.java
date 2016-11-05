package org.yidu.novel.batch;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.utils.DateUtils;
import org.yidu.novel.utils.FileUtils;
import org.yidu.novel.utils.Pagination;

/**
 * 
 * <p>
 * 生成结构化数据
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class CreateStructuringDataJob extends QuartzJobBean {
    /**
     * 输出log
     */
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 每个文件默认的URL数量
     */
    private static final int COUNT_PER_FILE = 1000;

    /**
     * 网站地图路径
     */
    private static final String STRUCTURINGDATA_DIR = "structuringdata";
    /**
     * 小说关联操作服务
     */
    private ArticleService articleService;

    /**
     * 手机移动地图的文件前缀
     */
    private String mobileSitemapPrefix = "mobile_";

    /**
     * 
     * 设置articleService
     * 
     * 
     * @param articleService
     *            articleService
     */
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.debug("CreateStructuringDataJob start.");
        boolean createStructuringDataFlag = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_STRUCTURINGDATA, false);
        if (createStructuringDataFlag) {
            String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
            String sitemapUri = uri + (StringUtils.endsWith(uri, "/") ? "" : "/") + STRUCTURINGDATA_DIR + "/";
            try {
                String currentPath = CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
                File f = new File(currentPath).getParentFile().getParentFile();
                currentPath = f.getAbsolutePath();
                log.debug(currentPath);
                String sitemapDir = currentPath + "/" + STRUCTURINGDATA_DIR + "/";

                log.debug("sitemap dir: " + sitemapDir);
                if (!new File(sitemapDir).exists()) {
                    new File(sitemapDir).mkdirs();
                }

                createXmlSiteMap(sitemapDir, sitemapUri, false);
                uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
                sitemapUri = uri + (StringUtils.endsWith(uri, "/") ? "" : "/") + STRUCTURINGDATA_DIR + "/";
                createXmlSiteMap(sitemapDir, sitemapUri, true);

                log.debug("CreateStructuringDataJob normally end.");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void createXmlSiteMap(String sitemapDir, String sitemapUri, boolean isCreateMobileSiteMap) {

        List<String> infoUrlList = ceateInfoBaiduXmlSiteMap(sitemapDir, sitemapUri, isCreateMobileSiteMap);

        // 生成sitemap索引文件
        StringBuffer indexBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        indexBuffer.append("<sitemapindex>\n");

        // 添加info页的sitemap
        for (String url : infoUrlList) {
            appendIndex(indexBuffer, url);
        }
        indexBuffer.append("</sitemapindex>");

        String fileName = sitemapDir + "structuringdata.xml";
        if (isCreateMobileSiteMap) {
            fileName = sitemapDir + mobileSitemapPrefix + "structuringdata.xml";
        }
        FileUtils.writeFile(new File(fileName), indexBuffer.toString(), false);
    }

    /**
     * 制作小说介绍页的网站地图<br>
     * 
     * @param sitemapUri
     * 
     * @param 文件列表
     */
    private List<String> ceateInfoBaiduXmlSiteMap(String sitemapDir, String sitemapUri, boolean isCreateMobileSiteMap) {
        int count = articleService.getCount(new ArticleSearchBean());
        int files = count / COUNT_PER_FILE;
        if (count % COUNT_PER_FILE > 0) {
            files++;
        }

        List<String> urlList = new ArrayList<String>();
        String fileNameFormat = "structuringdata_info_{0}.xml";
        if (isCreateMobileSiteMap) {
            fileNameFormat = mobileSitemapPrefix + "structuringdata_info_{0}.xml";
        }
        String fileName = "";
        for (int i = 1; i <= files; i++) {
            StringBuffer sb = new StringBuffer();
            sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<urlset>\n");
            ArticleSearchBean searchBean = new ArticleSearchBean();
            Pagination pagination = new Pagination(COUNT_PER_FILE, i);
            pagination.setSortColumn("articleno");
            pagination.setSortOrder("ASC");
            searchBean.setPagination(pagination);
            List<TArticle> articleList = articleService.find(searchBean);
            for (TArticle article : articleList) {
                sb.append("\t<url>\n");
                sb.append(MessageFormat.format("\t\t<loc><![CDATA[{0}]]></loc>\n", constructInfoURL(article, isCreateMobileSiteMap)));
                sb.append("\t\t<lastmod>" + DateUtils.format(article.getLastupdate()) + "</lastmod>\n");
                sb.append("\t\t<data>\n");
                sb.append("\t\t\t<name><![CDATA[" + article.getArticlename() + "]]></name>\n");
                sb.append("\t\t\t<author>\n");
                sb.append("\t\t\t\t<name><![CDATA[" + article.getAuthor() + "]]></name>\n");
                sb.append(MessageFormat.format("\t\t\t\t<url><![CDATA[{0}]]></url>\n", constructAuthorURL(article.getAuthor(), isCreateMobileSiteMap)));

                sb.append("\t\t\t</author>\n");
                sb.append(MessageFormat.format("\t\t\t<image><![CDATA[{0}]]></image>\n",
                        constructImageURL(article.getImgUrl(), isCreateMobileSiteMap)));
                sb.append(MessageFormat.format("\t\t\t<description><![CDATA[{0}]]></description>\n", article.getIntro()));
                sb.append(MessageFormat.format("\t\t\t<genre>{0}</genre>\n", article.getCategoryStr()));
                sb.append(MessageFormat.format("\t\t\t<wordCount>{0}</wordCount>\n", String.valueOf(article.getSize())));
                sb.append(MessageFormat.format("\t\t\t<url><![CDATA[{0}]]></url>\n", constructInfoURL(article, isCreateMobileSiteMap)));
                sb.append(MessageFormat.format("\t\t\t<updateStatus>{0}</updateStatus>\n", article.getFullflag() ? "已完结" : "更新中"));
                sb.append("\t\t\t<trialStatus>免费</trialStatus>\n");
                sb.append(MessageFormat.format("\t\t\t<keywords><![CDATA[{0}]]></keywords>\n", article.getKeywords()));
                sb.append(MessageFormat.format("\t\t\t<totalClick>{0}</totalClick>\n", String.valueOf(article.getAllvisit())));
                sb.append("\t\t\t<newestChapter>\n");
                sb.append(MessageFormat.format("\t\t\t\t<headline><![CDATA[{0}]]></headline>\n", article.getLastchapter()));
                sb.append(MessageFormat.format("\t\t\t\t<url><![CDATA[{0}]]></url>\n",
                        constructReaderURL(article.getArticleno(), article.getLastchapterno(), isCreateMobileSiteMap)));
                sb.append(MessageFormat.format("\t\t\t\t<dateModified>{0}</dateModified>\n", DateUtils.format(article.getLastupdate())));
                sb.append("\t\t\t</newestChapter>\n");
                sb.append(MessageFormat.format("\t\t\t<dateModified>{0}</dateModified>\n", DateUtils.format(new Date())));
                sb.append("\t\t</data>\n");
                sb.append("\t</url>\n");
            }
            sb.append("</urlset>");
            fileName = MessageFormat.format(sitemapDir + fileNameFormat, i);
            FileUtils.writeFile(new File(fileName), sb.toString(), false);
            urlList.add(MessageFormat.format(sitemapUri + fileNameFormat, i));
        }
        return urlList;
    }

    /**
     * 向sitemap索引文件中添加sitemap文件索引
     * 
     * @param fileIndex
     *            文件索引
     * @param indexBuffer
     *            StringBuffer
     * @param uri
     *            路径
     */
    private void appendIndex(StringBuffer indexBuffer, String url) {
        indexBuffer.append("\t<sitemap>\n");
        indexBuffer.append("\t\t<loc>" + url + "</loc>\n");
        indexBuffer.append("\t\t<lastmod>" + DateUtils.format(new Date()) + "</lastmod>\n");
        indexBuffer.append("\t</sitemap>\n");
    }

    /**
     * 构造小说阅读页URL
     * 
     * @param chapter
     *            章节信息
     * @return 小说阅读页URL
     */
    private String constructReaderURL(int articleNo, int chapterNo, boolean isCreateMobileSiteMap) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_READER_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        int subDir = articleNo / YiDuConstants.SUB_DIR_ARTICLES;

        if (StringUtils.contains(loc, "{pinyin}")) {
            TArticle article = articleService.getByNo(articleNo);
            loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                    .replace("{chapter_no}", String.valueOf(chapterNo)).replace("{pinyin}", String.valueOf(article.getPinyin()));
        } else {
            loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                    .replace("{chapter_no}", String.valueOf(chapterNo));
        }
        if (!uri.endsWith("/") && !loc.startsWith("/")) {
            uri += "/";
        }
        return uri + loc;
    }

    /**
     * 构造小说信息页URL
     * 
     * @param article
     *            小说信息
     * @return 小说信息页URL
     */
    private String constructInfoURL(TArticle article, boolean isCreateMobileSiteMap) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_INFO_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        int subDir = article.getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
        int articleNo = article.getArticleno();
        loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                .replace("{pinyin}", String.valueOf(article.getPinyin()));
        if (!uri.endsWith("/") && !loc.startsWith("/")) {
            uri += "/";
        }
        return uri + loc;
    }

    /**
     * 构造小说作者URL
     * 
     * @param chapter
     *            章节信息
     * @return 小说阅读页URL
     */
    private String constructAuthorURL(String author, boolean isCreateMobileSiteMap) {
        String loc = "/author/{0}.html";
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }

        try {
            loc = MessageFormat.format(loc, URLEncoder.encode(author, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return uri + loc;
    }

    /**
     * 构造小说作者URL
     * 
     * @param chapter
     *            章节信息
     * @return 小说阅读页URL
     */
    private String constructImageURL(String url, boolean isCreateMobileSiteMap) {
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        if (isCreateMobileSiteMap) {
            uri = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILE_URI);
        }
        return uri + url;
    }

}
