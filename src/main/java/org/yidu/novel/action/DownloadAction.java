package org.yidu.novel.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

import com.opensymphony.xwork2.Action;

/**
 * 
 * <p>
 * 区块编辑Action
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

    /**
     * シリアライズ時のID。
     */
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
    public static final String DOWNLOAD = "download";

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
        try {
            loadData();
        } catch (Exception e) {
            logger.error(getText("YIDU99999"), e);
            logger.debug("execute abnormally end.");
            return Action.ERROR;
        }
        logger.debug("execute normally end.");
        return DOWNLOAD;
    }

    @Override
    protected void loadData() {
        logger.debug("loadData download file name was set,going to download.");
        setDownloadFileName(articleno + ".txt");
        long size = 0;
        try {
            Vector<InputStream> vector = new Vector<InputStream>();
            File dir = new File(YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH) + "/" + (articleno / 1000) + "/"
                    + articleno);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                Arrays.sort(files, new FileSort());
                for (File file : files) {
                    size = size + file.length();
                    vector.addElement(new FileInputStream(file));
                }
            }
            Enumeration<InputStream> enumeration = vector.elements();
            is = new SequenceInputStream(enumeration);
            length = size;
        } catch (Exception e) {
            logger.error(getText("YIDU99999"), e);
            addActionError(getText("YIDU99999"));
        }
    }

    static class FileSort implements Comparator<File> {
        public int compare(File src, File target) {
            int diff = src.getName().compareTo(target.getName());
            return diff;
        }
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

}
