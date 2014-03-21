package org.yidu.novel.action.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicAndUserBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.utils.Utils;

/**
 * 
 * <p>
 * 小说安装画面Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class IndexAction extends AbstractPublicAndUserBaseAction {

    private static final long serialVersionUID = -5991997032217966675L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "install";
    /**
     * 功能名称。
     */
    public static final String NAME = "index";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private String prefixjdbc = "jdbc:postgresql://";
    private static String LOCK_FILE = "install.lock";

    private String title;
    private String siteKeywords;
    private String siteDescription;
    private String name;
    private String url;
    private String copyright;
    private String beianNo;
    private String analyticscode;

    private String dbhost;
    private String dbport;
    private String dbname;
    private String dbusername;
    private String dbpassword;

    private String username;
    private String password;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSiteKeywords() {
        return siteKeywords;
    }

    public void setSiteKeywords(String siteKeywords) {
        this.siteKeywords = siteKeywords;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getBeianNo() {
        return beianNo;
    }

    public void setBeianNo(String beianNo) {
        this.beianNo = beianNo;
    }

    public String getAnalyticscode() {
        return analyticscode;
    }

    public void setAnalyticscode(String analyticscode) {
        this.analyticscode = analyticscode;
    }

    public String getDbhost() {
        return dbhost;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    public String getDbport() {
        return dbport;
    }

    public void setDbport(String dbport) {
        this.dbport = dbport;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbusername() {
        return dbusername;
    }

    public void setDbusername(String dbusername) {
        this.dbusername = dbusername;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SkipValidation
    public String execute() {
        logger.debug("execute start.");

        File lockFile = new File(LOCK_FILE);
        if (lockFile.exists()) {
            addActionError(getText("errors.install.file.exist", new String[] { LOCK_FILE }));
            return ERROR;
        }

        // 设定文件初期读入
        try {
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration("jdbc.properties");
            String dburl = jdbcConf.getString(YiDuConfig.JDBC_URL).substring(prefixjdbc.length());
            String[] dbinfo = StringUtils.split(dburl, ":");
            dbhost = dbinfo[0];
            dbinfo = StringUtils.split(dbinfo[1], "/");
            dbport = dbinfo[0];
            dbname = dbinfo[1];
            dbusername = jdbcConf.getString(YiDuConfig.JDBC_USERNAME);
            dbpassword = jdbcConf.getString(YiDuConfig.JDBC_PASSWORD);
            PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("language/package.properties"));
            title = languageConf.getString(YiDuConfig.TITLE);
            siteKeywords = languageConf.getString(YiDuConfig.SITEKEYWORDS);
            siteDescription = languageConf.getString(YiDuConfig.SITEDESCRIPTION);
            name = languageConf.getString(YiDuConfig.NAME);
            url = languageConf.getString(YiDuConfig.URL);
            copyright = languageConf.getString(YiDuConfig.COPYRIGHT);
            beianNo = languageConf.getString(YiDuConfig.BEIANNO);
            analyticscode = languageConf.getString(YiDuConfig.ANALYTICSCODE);

        } catch (ConfigurationException e) {
            logger.error(e);
        }
        logger.debug("execute normally end.");
        return INPUT;
    }

    public String save() {

        File lockFile = new File(LOCK_FILE);
        if (lockFile.exists()) {
            addActionError(getText("errors.install.file.exist", new String[] { LOCK_FILE }));
            return ERROR;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 链接数据库
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection(prefixjdbc + dbhost + ":" + dbport + "/postgres", dbusername, dbpassword);
            stmt = conn.createStatement();
            rs = null;
            // 检查数据库是否存在
            String checkDBsql = "SELECT 1 FROM pg_database WHERE datname = '" + dbname + "'";
            rs = stmt.executeQuery(checkDBsql);
            boolean dbExist = false;
            while (rs.next()) {
                dbExist = true;
            }
            // 存在的话，先删除
            if (dbExist) {
                stmt.execute("drop database " + dbname);
            }
            excuteSqlFromFile(conn, "01_createdb.sql", new Object[] { dbname });
            // 切换数据库
            conn = DriverManager.getConnection(prefixjdbc + dbhost + ":" + dbport + "/" + dbname, dbusername,
                    dbpassword);
            // 建表
            excuteSqlFromFile(conn, "02_createtable.sql", new Object[] {});
            excuteSqlFromFile(conn, "03_createindex.sql", new Object[] {});
            excuteSqlFromFile(conn, "04_master_data.sql", new Object[] { username, Utils.convert2MD5(password) });

            // 更新jdbc文件
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("jdbc.properties"));

            jdbcConf.setProperty(YiDuConfig.JDBC_URL, prefixjdbc + dbhost + ":" + dbport + "/" + dbname);
            jdbcConf.setProperty(YiDuConfig.JDBC_USERNAME, dbusername);
            jdbcConf.setProperty(YiDuConfig.JDBC_PASSWORD, dbpassword);

            File jdbcFile = new File(jdbcConf.getPath());
            OutputStream out = new FileOutputStream(jdbcFile);
            jdbcConf.save(out);

            // 更新语言文件
            PropertiesConfiguration languageConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("language/package.properties"));

            languageConf.setProperty(YiDuConfig.TITLE, title);
            languageConf.setProperty(YiDuConfig.SITEKEYWORDS, siteKeywords);
            languageConf.setProperty(YiDuConfig.SITEDESCRIPTION, siteDescription);
            languageConf.setProperty(YiDuConfig.NAME, name);
            languageConf.setProperty(YiDuConfig.URL, url);
            languageConf.setProperty(YiDuConfig.COPYRIGHT, copyright);
            languageConf.setProperty(YiDuConfig.BEIANNO, beianNo);
            languageConf.setProperty(YiDuConfig.ANALYTICSCODE, analyticscode);

            File languageFile = new File(languageConf.getPath());
            out = new FileOutputStream(languageFile);
            languageConf.save(out);

            lockFile.createNewFile();

        } catch (Exception e) {
            addActionError(e.getMessage());
            logger.error(e);
            return ERROR;
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        addActionMessage(getText("messages.install.success"));
        return SUCCESS;
    }

    private void excuteSqlFromFile(Connection conn, String fileName, Object... params) throws FileNotFoundException,
            IOException, SQLException {

        // 新建数据库
        java.net.URL url = this.getClass().getResource(fileName);
        // 从URL对象中获取路径信息
        String realPath = url.getPath();

        File file = new File(realPath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sql = new String();
        String line = new String();
        while ((line = br.readLine()) != (null)) {
            sql += line + "\r\n";
        }
        br.close();
        Statement stmt = conn.createStatement();
        sql = MessageFormat.format(sql, params);
        stmt.execute(sql);
    }

    @Override
    protected void loadData() {
    }
}
