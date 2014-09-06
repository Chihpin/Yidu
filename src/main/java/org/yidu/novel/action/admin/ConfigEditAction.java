package org.yidu.novel.action.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminEditBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * 系统相关配置Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "configEdit")
public class ConfigEditAction extends AbstractAdminEditBaseAction {

    private static final long serialVersionUID = -6768164951656460867L;
    /**
     * TXT文件路径
     */
    private String filePath;
    /**
     * 图片文件路径
     */
    private String relativeIamgePath;
    /**
     * 模版名
     */
    private String themeName;
    /**
     * 是否跳过登录校验
     */
    private boolean skipAuthCheck;
    /**
     * 是否开启缓存
     */
    private boolean cacheEffective;
    /**
     * 是否开启伪静态
     */
    private boolean cleanUrl;
    /**
     * 是否开启Gzip压缩
     */
    private boolean gzipEffective;
    /**
     * 是否开启广告
     */
    private boolean adEffective;
    /**
     * 每页表示件数
     */
    private int countPerPage;

    /**
     * 可以添加最大书签数
     */

    private int maxBookcase;

    /**
     * 是否生成静态首页
     */
    private boolean createIndexPage;
    /**
     * 是否生成站点地图
     */
    private boolean createSiteMap;
    /**
     * 存放TXT的编码
     */
    private String txtEncoding;
    /**
     * 数据库URL
     */
    private String dburl;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 是否支持未登录发表评论
     */
    private boolean addReviewWithoutLogin;
    /**
     * 是否开启手机页面
     */
    private boolean enableMobileSite;
    /**
     * 是否使用域名作为手机页面的判断标准
     */
    private boolean judgmobilesitebydomian;
    /**
     * 手机站点域名
     */
    private String mobilesitedomian;

    /**
     * 是否启用伪原创
     */
    private boolean enablePseudo;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRelativeIamgePath() {
        return relativeIamgePath;
    }

    public void setRelativeIamgePath(String relativeIamgePath) {
        this.relativeIamgePath = relativeIamgePath;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public boolean isSkipAuthCheck() {
        return skipAuthCheck;
    }

    public void setSkipAuthCheck(boolean skipAuthCheck) {
        this.skipAuthCheck = skipAuthCheck;
    }

    public boolean isCacheEffective() {
        return cacheEffective;
    }

    public void setCacheEffective(boolean cacheEffective) {
        this.cacheEffective = cacheEffective;
    }

    public boolean isCleanUrl() {
        return cleanUrl;
    }

    public void setCleanUrl(boolean cleanUrl) {
        this.cleanUrl = cleanUrl;
    }

    public boolean isGzipEffective() {
        return gzipEffective;
    }

    public void setGzipEffective(boolean gzipEffective) {
        this.gzipEffective = gzipEffective;
    }

    public boolean isAdEffective() {
        return adEffective;
    }

    public void setAdEffective(boolean adEffective) {
        this.adEffective = adEffective;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getMaxBookcase() {
        return maxBookcase;
    }

    public void setMaxBookcase(int maxBookcase) {
        this.maxBookcase = maxBookcase;
    }

    public boolean isCreateIndexPage() {
        return createIndexPage;
    }

    public void setCreateIndexPage(boolean createIndexPage) {
        this.createIndexPage = createIndexPage;
    }

    public boolean isCreateSiteMap() {
        return createSiteMap;
    }

    public void setCreateSiteMap(boolean createSiteMap) {
        this.createSiteMap = createSiteMap;
    }

    public String getTxtEncoding() {
        return txtEncoding;
    }

    public void setTxtEncoding(String txtEncoding) {
        this.txtEncoding = txtEncoding;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
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

    public boolean isAddReviewWithoutLogin() {
        return addReviewWithoutLogin;
    }

    public void setAddReviewWithoutLogin(boolean addReviewWithoutLogin) {
        this.addReviewWithoutLogin = addReviewWithoutLogin;
    }

    public boolean isEnableMobileSite() {
        return enableMobileSite;
    }

    public void setEnableMobileSite(boolean enableMobileSite) {
        this.enableMobileSite = enableMobileSite;
    }

    public boolean isJudgmobilesitebydomian() {
        return judgmobilesitebydomian;
    }

    public void setJudgmobilesitebydomian(boolean judgmobilesitebydomian) {
        this.judgmobilesitebydomian = judgmobilesitebydomian;
    }

    public String getMobilesitedomian() {
        return mobilesitedomian;
    }

    public void setMobilesitedomian(String mobilesitedomian) {
        this.mobilesitedomian = mobilesitedomian;
    }

    public boolean isEnablePseudo() {
        return enablePseudo;
    }

    public void setEnablePseudo(boolean enablePseudo) {
        this.enablePseudo = enablePseudo;
    }

    @Override
    protected void loadData() {
        initCollections(new String[] { "collectionProperties.boolean" });
        filePath = YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH);
        relativeIamgePath = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH);
        skipAuthCheck = YiDuConstants.yiduConf.getBoolean(YiDuConfig.SKIP_AUTH_CHECK, false);
        cacheEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CACHE_EFFECTIVE, true);
        cleanUrl = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CLEAN_URL, true);
        gzipEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.GZIP_EFFECTIVE, true);
        adEffective = YiDuConstants.yiduConf.getBoolean(YiDuConfig.AD_EFFECTIVE, true);
        countPerPage = YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 15);
        maxBookcase = YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_BOOKCASE, 15);
        themeName = YiDuConstants.yiduConf.getString(YiDuConfig.THEME_NAME);
        createIndexPage = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_INDEXPAGE, true);
        createSiteMap = YiDuConstants.yiduConf.getBoolean(YiDuConfig.CREATE_SITEMAP, true);
        txtEncoding = YiDuConstants.yiduConf.getString(YiDuConfig.TXT_ENCODING);

        addReviewWithoutLogin = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ADD_REVIEW_WITHOUT_LOGIN, true);
        enableMobileSite = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_MOBILE_SITE, true);
        judgmobilesitebydomian = YiDuConstants.yiduConf.getBoolean(YiDuConfig.JUDG_MOBILESITE_BY_DOMIAN, true);
        mobilesitedomian = YiDuConstants.yiduConf.getString(YiDuConfig.MOBILESITE_DOMIAN);
        enablePseudo = YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_PSEUDO, false);

        // 设定文件初期读入
        try {
            PropertiesConfiguration jdbcConf = new PropertiesConfiguration("jdbc.properties");

            dburl = jdbcConf.getString(YiDuConfig.JDBC_URL);
            username = jdbcConf.getString(YiDuConfig.JDBC_USERNAME);
            password = jdbcConf.getString(YiDuConfig.JDBC_PASSWORD);

        } catch (ConfigurationException e) {
            logger.error(e);
        }

    }

    public String save() {

        YiDuConstants.yiduConf.setProperty(YiDuConfig.FILE_PATH, filePath);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.RELATIVE_IAMGE_PATH, relativeIamgePath);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.SKIP_AUTH_CHECK, skipAuthCheck);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CACHE_EFFECTIVE, cacheEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CLEAN_URL, cleanUrl);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.GZIP_EFFECTIVE, gzipEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.AD_EFFECTIVE, adEffective);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.COUNT_PER_PAGE, countPerPage);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MAX_BOOKCASE, maxBookcase);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.THEME_NAME, themeName);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CREATE_INDEXPAGE, createIndexPage);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.CREATE_SITEMAP, createSiteMap);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.TXT_ENCODING, txtEncoding);

        YiDuConstants.yiduConf.setProperty(YiDuConfig.ADD_REVIEW_WITHOUT_LOGIN, addReviewWithoutLogin);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_MOBILE_SITE, enableMobileSite);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.JUDG_MOBILESITE_BY_DOMIAN, judgmobilesitebydomian);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.MOBILESITE_DOMIAN, mobilesitedomian);
        YiDuConstants.yiduConf.setProperty(YiDuConfig.ENABLE_PSEUDO, enablePseudo);

        try {
            File yiduConfFile = new File(YiDuConstants.yiduConf.getPath());
            OutputStream out = new FileOutputStream(yiduConfFile);
            YiDuConstants.yiduConf.save(out);

            PropertiesConfiguration jdbcConf = new PropertiesConfiguration(Thread.currentThread()
                    .getContextClassLoader().getResource("jdbc.properties"));

            jdbcConf.getString(YiDuConfig.JDBC_URL, dburl);
            jdbcConf.getString(YiDuConfig.JDBC_USERNAME, username);
            jdbcConf.getString(YiDuConfig.JDBC_PASSWORD, password);

            File jdbcFile = new File(jdbcConf.getPath());
            out = new FileOutputStream(jdbcFile);
            jdbcConf.save(out);

        } catch (Exception e) {
            addActionError(e.getMessage());
            logger.error(e);
            return ADMIN_ERROR;
        }
        loadData();
        addActionMessage(getText("messages.save.success"));
        return INPUT;
    }
}
