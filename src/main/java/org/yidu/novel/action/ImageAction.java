package org.yidu.novel.action;

import java.io.File;
import java.io.FileInputStream;

import org.apache.struts2.convention.annotation.Result;
import org.yidu.novel.action.base.AbstractPublicListBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * 
 * <p>
 * 图片表示Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Result(name = "input", type = "imageResult")
public class ImageAction extends AbstractPublicListBaseAction {

    private static final long serialVersionUID = -4215796997609788238L;

    /**
     * 小说编号
     */
    private int articleno;

    public int getArticleno() {
        return articleno;
    }

    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_OTHERS;
    }

    public String getCustomContentType() {
        return "image/jpeg";
    }

    public String getCustomContentDisposition() {
        return articleno + ".jpg";
    }

    public byte[] getGraph() {
        logger.debug("getGraph(10) start.");
        byte[] binaryinfo = null;
        if (articleno != 0) {
            String picPath = YiDuConstants.yiduConf.getString(YiDuConfig.IAMGE_PATH);
            String jpgPath = picPath + "/" + articleno / 1000 + "/" + articleno + "/" + articleno + "s.jpg";
            String gifPath = picPath + "/" + articleno / 1000 + "/" + articleno + "/" + articleno + "s.gif";
            try {
                File file = new File(jpgPath);
                if (file.exists()) {
                    binaryinfo = new byte[(int) file.length()];
                    FileInputStream inputStream = new FileInputStream(file);
                    inputStream.read(binaryinfo);
                    inputStream.close();
                } else {
                    file = new File(gifPath);
                    if (file.exists()) {
                        binaryinfo = new byte[(int) file.length()];
                        FileInputStream inputStream = new FileInputStream(file);
                        inputStream.read(binaryinfo);
                        inputStream.close();
                    }
                }
            } catch (Exception e) {
                logger.error(e);
            }
            // Path path = Paths.get(jpgPath);
            // try {
            // binaryinfo = Files.readAllBytes(path);
            // } catch (IOException e) {
            // logger.error(e);
            // }
            // if (binaryinfo == null) {
            // String gifPath = picPath + "/" + articleno / 1000 + "/" +
            // articleno + "/" + articleno + "s.gif";
            // path = Paths.get(gifPath);
            // try {
            // binaryinfo = Files.readAllBytes(path);
            // } catch (IOException e) {
            // logger.error(e);
            // }
            // }
            logger.debug("getGraph(99) normally end.");
        } else {
            logger.debug("getGraph(30) measurePointId is null.");
        }
        return binaryinfo;
    }
}
