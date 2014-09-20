package org.yidu.novel.batch;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.action.SiteMapAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.constant.YiDuConstants.SiteMapType;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TChapter;
import org.yidu.novel.service.ArticleService;
import org.yidu.novel.service.ChapterService;
import org.yidu.novel.utils.DateUtils;
import org.yidu.novel.utils.FileUtils;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 生成静态网站地图
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class CreateSiteMapJob extends QuartzJobBean {
    /**
     * 输出log
     */
    private Log log = LogFactory.getLog(this.getClass());
    /**
     * 网站地图路径
     */
    private static final String SITEMAP_DIR = "sitemap";
    /**
     * 小说关联操作服务
     */
    private ArticleService articleService;
    /**
     * 章节关联操作服务
     */
    private ChapterService chapterService;

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

    /**
     * 
     * 设置chapterService
     * 
     * 
     * @param chapterService
     *            chapterService
     */
    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.debug("CreateSiteMapJob start.");
        boolean createSiteMapFlag = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_SITEMAP, false);
        if (createSiteMapFlag) {
            String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
            try {
                String currentPath = CreateSiteMapJob.class.getClassLoader().getResource("").getPath();
                String dir = currentPath + "/" + SITEMAP_DIR + "/";
                if (!new File(dir).exists()) {
                    new File(dir).mkdirs();
                }
                // 减16的字符是WEB-INF/classes/
                // String webRootPath = currentPath.substring(0,
                // currentPath.length() - 16);
                if (SiteMapType.XML.getName().equalsIgnoreCase(
                        YiDuConstants.yiduConf.getString(YiDuConfig.SITEMAP_TYPE))) {
                    List<TArticle> articleList = articleService.find(new ArticleSearchBean());
                    List<TChapter> chapterList = chapterService.find(new ChapterSearchBean());
                    int fileIndex = 1;
                    createBaiduXmlSiteMap(articleList, chapterList, dir, fileIndex);
                } else {
                    String responseBody = Utils.getContentFromUri(uri + SiteMapAction.URL);
                    if (StringUtils.isNotBlank(responseBody)) {
                        File destFile = new File(dir + "/index.html");
                        FileUtils.writeFile(destFile, responseBody, false);
                    }
                }
                log.debug("CreateSiteMapJob normally end.");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 创建符合百度规范的xml格式的sitemap
     * 
     * @param articleList
     *            小说列表
     * @param chapterList
     *            章节列表
     * @param siteMapDir
     *            网站地图路径
     * @param fileIndex
     *            文件索引
     * @throws Exception
     *             处理异常
     */
    public void createBaiduXmlSiteMap(List<TArticle> articleList, List<TChapter> chapterList, String siteMapDir,
            int fileIndex) throws Exception {
        StringBuffer indexBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        indexBuffer.append("<sitemapindex>\n");

        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        uri = uri.endsWith("/") ? uri : uri + "/";

        StringBuffer sb = new StringBuffer();
        String fileName = "";
        // xml格式的sitemap每个文件最大支持10M，或5万条url， 这里设置每10000条URL写一次文件

        // 写章节阅读页
        for (int i = 0; i < chapterList.size(); i++) {
            TChapter chapter = chapterList.get(i);
            // 首次
            if (i % 10000 == 0) {
                sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                sb.append("<urlsest>\n");
            }
            sb.append(createURL(chapter));

            // 写文件
            if ((i + 1) % 10000 == 0) {
                sb.append("</urlsest>");
                fileName = siteMapDir + "sitemap_" + fileIndex + ".xml";
                FileUtils.writeFile(new File(fileName), sb.toString(), false);
                appendIndex(fileIndex, indexBuffer, uri);
                fileIndex++;
            }
        }
        sb.append("</urlsest>");
        fileName = siteMapDir + "sitemap_" + fileIndex + ".xml";
        FileUtils.writeFile(new File(fileName), sb.toString(), false);
        appendIndex(fileIndex, indexBuffer, uri);
        fileIndex++;

        // 写小说信息页
        for (int i = 0; i < articleList.size(); i++) {
            TArticle article = articleList.get(i);
            // 首次
            if (i % 10000 == 0) {
                sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                sb.append("<urlsest>\n");
            }
            sb.append(createURL(article));

            // 写文件
            if ((i + 1) % 10000 == 0) {
                sb.append("</urlsest>");
                fileName = siteMapDir + "sitemap_" + fileIndex + ".xml";
                FileUtils.writeFile(new File(fileName), sb.toString(), false);
                appendIndex(fileIndex, indexBuffer, uri);
                fileIndex++;
            }
        }
        sb.append("</urlsest>");
        fileName = siteMapDir + "sitemap_" + fileIndex + ".xml";
        FileUtils.writeFile(new File(fileName), sb.toString(), false);

        appendIndex(fileIndex, indexBuffer, uri);
        indexBuffer.append("</sitemapindex>");

        fileName = siteMapDir + "sitemap.xml";
        FileUtils.writeFile(new File(fileName), indexBuffer.toString(), false);

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
    private void appendIndex(int fileIndex, StringBuffer indexBuffer, String uri) {
        String indexLoc = uri + SITEMAP_DIR + "/sitemap_" + fileIndex + ".xml";
        indexBuffer.append("\t<sitemap>\n");
        indexBuffer.append("\t\t<loc>" + indexLoc + "</loc>\n");
        indexBuffer.append("\t\t<lastmod>" + DateUtils.format(new Date()) + "</lastmod>\n");
        indexBuffer.append("\t</sitemap>\n");
    }

    /**
     * 向sitemap文件中添加章节阅读页&lt;url&gt;xxx&lt;/url&gt;
     * 
     * @param chapter
     *            章节信息
     * @return 创建URL
     */
    private String createURL(TChapter chapter) {
        StringBuffer sb = new StringBuffer("\t<url>\n");
        String loc = constructURL(chapter);
        sb.append("\t\t<loc>" + loc + "</loc>\n");
        sb.append("\t\t<lastmod>" + DateUtils.format(chapter.getPostdate()) + "</lastmod>\n");
        sb.append("\t\t<changefreq>always</changefreq>\n");
        sb.append("\t\t<priority>1.0</priority>\n");
        sb.append("\t</url>\n");
        return sb.toString();
    }

    /**
     * 向sitemap文件中添加小说信息页&lt;url&gt;xxx&lt;/url&gt;
     * 
     * @param article
     *            小说信息
     * @return 小说URL
     */
    private String createURL(TArticle article) {
        StringBuffer sb = new StringBuffer("\t<url>\n");
        String loc = constructURL(article);
        sb.append("\t\t<loc>" + loc + "</loc>\n");
        sb.append("\t\t<lastmod>" + DateUtils.format(article.getLastupdate()) + "</lastmod>\n");
        sb.append("\t\t<changefreq>always</changefreq>\n");
        sb.append("\t\t<priority>1.0</priority>\n");
        sb.append("\t</url>\n");
        return sb.toString();
    }

    /**
     * 构造小说阅读页URL
     * 
     * @param chapter
     *            章节信息
     * @return 小说阅读页URL
     */
    private String constructURL(TChapter chapter) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_CHAPTER_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        int subDir = chapter.getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
        int articleNo = chapter.getArticleno();
        int chapterNo = chapter.getChapterno();
        // /reader/{sub_dir}/{article_no}/{chapter_no}.html
        loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo))
                .replace("{chapter_no}", String.valueOf(chapterNo));
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
    private String constructURL(TArticle article) {
        String loc = YiDuConstants.yiduConf.getString(YiDuConfig.XML_SITEMAP_ARTICLE_URL);
        String uri = YiDuConstants.yiduConf.getString(YiDuConfig.URI);
        int subDir = article.getArticleno() / YiDuConstants.SUB_DIR_ARTICLES;
        int articleNo = article.getArticleno();
        // /info/{sub_dir}/{article_no}.html
        loc = loc.replace("{sub_dir}", String.valueOf(subDir)).replace("{article_no}", String.valueOf(articleNo));
        if (!uri.endsWith("/") && !loc.startsWith("/")) {
            uri += "/";
        }
        return uri + loc;
    }

}