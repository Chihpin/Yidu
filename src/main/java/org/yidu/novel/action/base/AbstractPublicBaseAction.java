package org.yidu.novel.action.base;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.LoginManager;

/**
 * <p>
 * 公共画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class AbstractPublicBaseAction extends AbstractPublicAndUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 4900892616460135567L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "";

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
     * 获取回退URL
     * 
     * @return 回退URL
     */
    public String getBackUrl() {
        return LoginManager.getAndCleanReferer();
    }

    @SkipValidation
    @Override
    public String execute() {
        logger.debug("execute start.");
        // 读取区块
        loadBlock();
        // 读取数据
        loadData();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            setHasError(true);
            return FREEMARKER_ERROR;
        }
        logger.debug("execute normally end.");
        return FREEMARKER;
    }

    protected int getArticleno() {
        return 0;
    }

    protected int getCategory() {
        return 0;
    }

    protected String getBlockKey() {
        return CacheManager.CacheKeyPrefix.CACHE_KEY_GLOBAL_BLOCK;
    }

    protected Short getBlockTarget() {
        return YiDuConstants.BlockTarget.ALL_SITE;
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
                    List<TArticle> articleList = articleService.findRecommendArticleList(getCategory(), getArticleno(),
                            limitnum);
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

    /**
     * 取得当前页的类型
     * 
     * <pre>
     * 1:主页
     * 2：小说列表
     * 3：小说介绍页
     * 4：小说阅读页
     * 11：登录页
     * 99：其他页
     * </pre>
     * 
     * @return 当前页的类型
     */
    public abstract int getPageType();

    /**
     * 取得表示用的模版名称
     * 
     * @return 模版名称
     */
    public abstract String getTempName();

    /**
     * 
     * <p>
     * 获取当前登录状态
     * </p>
     * 
     * @return 当前登录状态
     */
    public boolean getLoginFlag() {
        return LoginManager.isLoginFlag();
    }

    /**
     * 
     * <p>
     * 获取当前登录用户
     * </p>
     * 
     * @return 当前登录用户
     */
    public TUser getLoginUser() {
        return LoginManager.getLoginUser();
    }

    /**
     * 获取是否开启了章节列表页标识
     * 
     * @return 是否开启了章节列表页标识
     */
    public boolean getEnableChapterIndexPage() {
        return YiDuConstants.yiduConf.getBoolean(YiDuConfig.ENABLE_CHAPTER_INDEX_PAGE, false);
    }

}
