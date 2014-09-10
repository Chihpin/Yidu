package org.yidu.novel.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.bean.ChapterSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

import com.opensymphony.xwork2.Action;

/**
 * 
 * <p>
 * 下载处理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Results(value = {
        @Result(name = "download", type = "stream", params = { "inputName", "is", "contentType",
                "application/octet-stream; charset=UTF-8", "contentLength", "${length}", "contentDisposition",
                "attachment; filename =${downloadFileName}" }),
        @Result(name = Action.ERROR, type = "httpheader", params = { "status", "404" }) })
public class DownloadAction extends AbstractPublicBaseAction {

    private static final long serialVersionUID = -6265156751627551469L;

    /**
     * 功能名称。
     */
    public static final String NAME = "download";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    /**
     * 返回结果值
     */
    public static final String RESULT_DOWNLOAD = "download";

    /**
     * 下载文件名
     * */
    private String downloadFileName;
    /**
     * 小说号
     */
    private int articleno;
    /**
     * 章节号
     */
    private int chapterno;

    /**
     * 文件长度
     * */
    private long length;
    /**
     * 输出流
     * */
    private InputStream is;

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public long getLength() {
        return length;
    }

    public InputStream getIs() {
        return is;
    }

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    public int getChapterno() {
        return chapterno;
    }

    public void setChapterno(int chapterno) {
        this.chapterno = chapterno;
    }

    @Override
    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        setDownloadFileName(articleno + ".txt");
        long size = 0;
        try {
            Vector<InputStream> vector = new Vector<InputStream>();
            ChapterSearchBean searchBean = new ChapterSearchBean();
            searchBean.setArticleno(articleno);
            List<TChapter> chapterList = this.chapterService.find(searchBean);
            File dir = new File(YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH) + "/"
                    + (articleno / YiDuConstants.SUB_DIR_ARTICLES) + "/" + articleno);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();

                // 做成文件Map
                Map<String, File> fileMap = new HashMap<String, File>();
                for (File file : files) {
                    fileMap.put(file.getName(), file);
                }

                for (TChapter chapter : chapterList) {
                    int chapterno = chapter.getChapterno();
                    File file = fileMap.get(chapterno + ".txt");
                    if (file != null) {
                        // 添加一个换行符
                        String chaptername = chapter.getChaptername() + "\n";
                        size += chaptername.length();
                        size += file.length();
                        // convert String into InputStream
                        InputStream chapternameis = new ByteArrayInputStream(
                                chaptername.getBytes(YiDuConstants.ENCODING_GBK));
                        vector.addElement(chapternameis);
                        vector.addElement(new FileInputStream(file));
                    }
                }
            }
            Enumeration<InputStream> enumeration = vector.elements();
            is = new SequenceInputStream(enumeration);
            length = size;
        } catch (Exception e) {
            logger.error(getText("YIDU99999"), e);
            addActionError(getText("YIDU99999"));
            return FREEMARKER_ERROR;
        }
        logger.debug("execute normally end.");
        return RESULT_DOWNLOAD;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

}
