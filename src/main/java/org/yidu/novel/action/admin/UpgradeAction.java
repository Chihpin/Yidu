package org.yidu.novel.action.admin;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.action.base.AbstractAdminBaseAction;
import org.yidu.novel.bean.UpgradeBean;
import org.yidu.novel.bean.UpgradeBean.FileType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * <p>
 * 升级Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * version 1.1.9
 * 
 * @author shinpa.you
 */
public class UpgradeAction extends AbstractAdminBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 4056013655209592754L;

    /**
     * 功能名称。
     */
    public static final String NAME = "upgrade";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    public static final String SITE_URL = "http://www.51yd.org/yiduupgrade/";

    private String uv;

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    private String ZIP_FILE_URL;
    private String INPUT_ZIP_FILE;
    private String OUTPUT_FOLDER;
    private String BASE_FOLDER;

    @Override
    protected void loadData() {

        if (StringUtils.isEmpty(uv)) {
            addActionError(getText("errors.not.exsits.upgradeVersion"));
            return;
        }
        // step1下载安装包并解压缩
        logger.info("going to download upgrade file ： " + uv + ".zip");
        ZIP_FILE_URL = SITE_URL + uv + ".zip";

        String currentPath = UpgradeAction.class.getClassLoader().getResource("").getPath();
        File f = new File(currentPath).getParentFile().getParentFile();
        INPUT_ZIP_FILE = f.getAbsolutePath() + File.separator + "tmp" + File.separator + uv + ".zip";
        OUTPUT_FOLDER = f.getAbsolutePath() + File.separator + "tmp" + File.separator + uv + File.separator;

        BASE_FOLDER = f.getAbsolutePath() + File.separator;

        downloadFile();
        if (this.hasErrors()) {
            return;
        }
        logger.info("going to unzip upgrade file ： " + uv + ".zip");
        unZipFile();
        if (this.hasErrors()) {
            return;
        }

        // 读取rules文件
        String content = readFileByLines(OUTPUT_FOLDER + "rule");

        Gson g = new Gson();
        List<UpgradeBean> upgradeList = g.fromJson(content, new TypeToken<List<UpgradeBean>>() {
        }.getType());

        for (UpgradeBean rb : upgradeList) {
            switch (rb.getType()) {
            case FileType.STATIC:
            case FileType.CLASS:
            case FileType.FTL:
            case FileType.JSP:
            case FileType.XML:
                // 替换或添加静态文件,class文件,ftl,jsp
                // 直接覆盖
                File srcFile = new File(OUTPUT_FOLDER + rb.getPath());
                File destFile = new File(BASE_FOLDER + rb.getPath());
                try {
                    FileUtils.copyFile(srcFile, destFile);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    logger.error(e1.getMessage(), e1);
                    addActionError(e1.getMessage());
                    return;
                }

                break;
            case FileType.PROPERTIES:
                // 修改properties文件
                try {
                    PropertiesConfiguration propertiesFile = new PropertiesConfiguration(Thread.currentThread().getContextClassLoader()
                            .getResource(rb.getFileName()));
                    propertiesFile.setProperty(rb.getKey(), rb.getContent());
                    File jdbcFile = new File(propertiesFile.getPath());
                    OutputStream out = new FileOutputStream(jdbcFile);
                    propertiesFile.save(out);
                } catch (Exception e) {
                    addActionError(e.getMessage());
                    logger.error(e);
                }
                break;
            default:
                break;
            }
        }
    }

    private void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    private void downloadFile() {
        try {
            URL url = new URL(ZIP_FILE_URL);
            url.openConnection();
            InputStream reader = url.openStream();

            FileOutputStream writer = new FileOutputStream(INPUT_ZIP_FILE);
            byte[] buffer = new byte[102400];
            int bytesRead = 0;

            logger.debug("Reading ZIP file 20KB blocks at a time.\n");

            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
                buffer = new byte[102400];
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addActionError(e.getMessage());
        }
    }

    private void unZipFile() {
        try {
            ZipFile zipFile = new ZipFile(INPUT_ZIP_FILE);
            Enumeration<?> zipEntries = zipFile.entries();
            File OUTFILEFOLD = new File(OUTPUT_FOLDER);
            if (!OUTFILEFOLD.exists()) {
                OUTFILEFOLD.mkdir();
            }
            String OUTDIR = OUTPUT_FOLDER;
            while (zipEntries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) zipEntries.nextElement();

                if (zipEntry.isDirectory()) {
                    logger.info("Extracting directory: " + OUTDIR + zipEntry.getName());
                    new File(OUTDIR + zipEntry.getName()).mkdir();
                    continue;
                }
                logger.info("Extracting file: " + OUTDIR + zipEntry.getName());
                copyInputStream(zipFile.getInputStream(zipEntry), new BufferedOutputStream(new FileOutputStream(OUTDIR + zipEntry.getName())));
            }
            zipFile.close();
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
            addActionError(ioe.getMessage());
        }
    }

    /**
     * 以行为单位读取文件
     */
    private String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            reader = new BufferedReader(isr);
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                sb.append(tempString + "\n");
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            addActionError(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

}
