package org.yidu.novel.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.template.EncodeURLMethod;
import org.yidu.novel.template.GetTextMethod;
import org.yidu.novel.template.UpperNumMethod;
import org.yidu.novel.utils.Pagination;
import org.yidu.novel.utils.Utils;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * 公共页和用户页共通父类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class AbstractPublicAndUserBaseAction extends AbstractBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6698799932081679448L;

    /**
     * 获取数据
     */
    protected abstract void loadData();

    /**
     * 是否存在错误
     */
    private boolean hasError = false;

    /**
     * 区块信息
     */
    protected Map<String, Object> blocks = new HashMap<String, Object>();

    /**
     * 获取区块信息
     * 
     * @return 区块信息
     */
    public Map<String, Object> getBlocks() {
        return blocks;
    }

    /**
     * 设置区块信息
     * 
     * @param blocks
     *            区块信息
     */
    public void setBlocks(Map<String, Object> blocks) {
        this.blocks = blocks;
    }

    /**
     * 设置是否存在错误标识
     * 
     * @param hasError
     *            是否存在错误标识
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * 获得是否存在错误标识
     * 
     * @return 是否存在错误标识
     */
    public boolean getHasError() {
        return hasError;
    }

    /**
     * 公共页和用户页用类型JSON文字列
     * 
     * @return 类型JSON文字列
     */
    public String getCategoryData() {

        Gson gson = new Gson();
        LinkedMap pulldown = new LinkedMap();
        String value = getText("collectionProperties.article.category");
        String[] items = value.split(",");
        for (String item : items) {
            String[] property = item.split(":");
            if (property.length == 2) {
                pulldown.put(property[0], property[1]);
            }
        }
        return gson.toJson(pulldown);
    }

    /**
     * 
     * <p>
     * 获取启用广告标识
     * </p>
     * 
     * @return 启用广告标识
     */
    public boolean getAdEffective() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.AD_EFFECTIVE, true);
    }

    /**
     * 取得自定义的freemarker方法 <br>
     * 实现encodeURL
     * 
     * @return EncodeURL
     */
    public EncodeURLMethod getEncodeURL() {
        return new EncodeURLMethod(ServletActionContext.getResponse());
    }

    /**
     * 取得自定义的freemarker方法 <br>
     * 实现struts的gettxt
     * 
     * @return GetText
     */
    public GetTextMethod getGetText() {
        return new GetTextMethod(this);
    }

    /**
     * 取得自定义的freemarker方法 <br>
     * 实现struts的gettxt
     * 
     * @return GetText
     */
    public UpperNumMethod getUpperNum() {
        return new UpperNumMethod();
    }

    /**
     * 取得模版名
     * 
     * @return 模版名
     */
    public String getThemeName() {

        return YiDuConstants.yiduConf.getString("themeName", "default");
    }

    /**
     * <p>
     * 是否开启QQ登录
     * </p>
     * 
     * @return 是否开启QQ登录
     */
    public boolean getEnableQQLogin() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_QQLOGIN, false);
    }

    protected int getRecommondArticleno() {
        return 0;
    }

    protected int getRecommondCategory() {
        return 0;
    }

    protected String getBlockKey() {
        return CacheManager.CacheKeyPrefix.CACHE_KEY_GLOBAL_BLOCK;
    }

    protected Short getBlockTarget() {
        return YiDuConstants.BlockTarget.ALL_SITE;
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute start.");
        // 读取数据
        loadData();
        // 读取区块
        loadBlock();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            setHasError(true);
            return FREEMARKER_ERROR;
        }
        logger.debug("execute normally end.");
        return FREEMARKER;
    }

    /**
     * 初始化区块信息
     */
    protected void loadBlock() {
        logger.debug("loadBlock start.");
        // 从缓存中把首页用的区块信息取出
        blocks = CacheManager.getObject(getBlockKey(), null);
        if (!Utils.isDefined(blocks)) {
            blocks = new HashMap<String, Object>();
            // 没有取到的话从数据库里取出
            // block数据取得
            List<TSystemBlock> blockList = new ArrayList<TSystemBlock>();
            SystemBlockSearchBean searchBean = new SystemBlockSearchBean();
            searchBean.setTargets(YiDuConstants.BlockTarget.ALL_SITE, getBlockTarget());
            blockList = systemBlockService.find(searchBean);
            for (TSystemBlock tSystemBlock : blockList) {
                int limitnum = tSystemBlock.getLimitnum() == null ? 1 : tSystemBlock.getLimitnum();
                if (tSystemBlock.getType() == YiDuConstants.BlockType.ARTICLE_LIST) {
                    ArticleSearchBean articleSearchBean = new ArticleSearchBean();
                    if (Utils.isDefined(tSystemBlock.getCategory())) {
                        articleSearchBean.setCategory(tSystemBlock.getCategory());
                    }
                    Pagination pagination = new Pagination(limitnum, 1);
                    pagination.setSortColumn(tSystemBlock.getSortcol());
                    pagination.setSortOrder(tSystemBlock.getIsasc() ? "ASC" : "DESC");
                    articleSearchBean.setPagination(pagination);
                    List<TArticle> articleList = articleService.find(articleSearchBean);
                    blocks.put(tSystemBlock.getBlockid(), articleList);
                } else if (tSystemBlock.getType() == YiDuConstants.BlockType.RODMON_LIST) {
                    List<TArticle> articleList = articleService.findRandomRecommendArticleList(limitnum);
                    blocks.put(tSystemBlock.getBlockid(), articleList);

                } else if (tSystemBlock.getType() == YiDuConstants.BlockType.BACK_LIST) {
                    // TODO 暂未开放，貌似没什么用啊
                    List<TArticle> articleList = articleService.findRecommendArticleList(getRecommondCategory(),
                            getRecommondArticleno(), limitnum);
                    blocks.put(tSystemBlock.getBlockid(), articleList);

                } else if (tSystemBlock.getType() == YiDuConstants.BlockType.CUSTONIZE_ARTICLE_LIST) {
                    ArticleSearchBean articleSearchBean = new ArticleSearchBean();
                    articleSearchBean.setArticlenos(tSystemBlock.getContent());
                    List<TArticle> articleList = articleService.find(articleSearchBean);
                    blocks.put(tSystemBlock.getBlockid(), articleList);
                } else if (tSystemBlock.getType() == YiDuConstants.BlockType.HTML) {
                    blocks.put(tSystemBlock.getBlockid(), tSystemBlock.getContent());
                }
                CacheManager.putObject(getBlockKey(), null, blocks);
            }
        }
        logger.debug("loadBlock normally end.");
    }

    public String getSiteTitle() {
        return getText("label.system.title");
    }

    public String getSiteKeyword() {
        return getText("label.system.siteKeywords");
    }

    public String getSiteDescription() {
        return getText("label.system.siteDescription");
    }

    public String getSiteName() {
        return getText("label.system.name");
    }

    public String getSiteUrl() {
        return getText("label.system.url");
    }

    public String getSiteCopyright() {
        return getText("label.system.copyright");
    }

    public String getSiteSupport() {
        return getText("label.system.support");
    }

    public String getBeianNo() {
        return getText("label.system.beianNo");
    }

    public String getAnalyticscode() {
        return getText("label.system.analyticscode");
    }

    public String getDomain() {
        return getText("label.system.domain");
    }

}
