package org.yidu.novel.extTools;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.yidu.novel.bean.UpgradeBean;

import com.google.gson.Gson;

/**
 * <p>
 * 易读系统额外工具---升级Version文件制作
 * </p>
 * Copyright(c) 2016 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public class UpgradeTools {

    public static void main(String[] args) {
        List<UpgradeBean> upList = new ArrayList<UpgradeBean>();
        addRules(upList);
        Gson gs = new Gson();
        String versionStr = gs.toJson(upList);
        write2File(versionStr);
    }

    private static void addRules(List<UpgradeBean> upList) {
        // UpgradeBean file1 = new UpgradeBean();
        // file1.setFileName("index.ftl");
        // file1.setPath("/themes/default/pc/index.ftl");
        // file1.setType(UpgradeBean.FileType.FTL);
        // upList.add(file1);
        //
        // UpgradeBean file2 = new UpgradeBean();
        // file2.setFileName("yidu.properties");
        // file2.setPath("/WEB-INF/classes/yidu.properties");
        // file2.setType(UpgradeBean.FileType.PROPERTIES);
        // file2.setKey("testKey");
        // file2.setContent("testContent");
        // upList.add(file2);
        //
        // UpgradeBean file3 = new UpgradeBean();
        // file3.setFileName("language/package.properties");
        // file3.setPath("/WEB-INF/classes/language/package.properties");
        // file3.setType(UpgradeBean.FileType.PROPERTIES);
        // file3.setKey("label.system.support");
        // file3.setContent("Powered by <a href=\"http://www.51yd.org\">易读小说系统  V1.2.0 Beta</a>");
        // upList.add(file3);

        UpgradeBean file4 = new UpgradeBean();

        file4.setType(UpgradeBean.FileType.SQL);
        file4.setSql("CREATE INDEX t_article_chapters_index ON t_article USING btree  (chapters)");
        upList.add(file4);
    }

    private static void write2File(String versionStr) {
        String currentPath = UpgradeTools.class.getClassLoader().getResource("").getPath();
        File f = new File(currentPath).getParentFile().getParentFile();
        try {
            FileOutputStream outSTr = new FileOutputStream(new File(f.getAbsolutePath() + File.separator + "rule"));
            BufferedOutputStream Buff = new BufferedOutputStream(outSTr);
            Buff.write(versionStr.getBytes());
            Buff.flush();
            Buff.close();
        } catch (Exception e) {

        }
    }
}
