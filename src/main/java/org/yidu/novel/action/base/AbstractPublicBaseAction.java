package org.yidu.novel.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.BeanUtils;
import org.yidu.novel.action.IndexAction;
import org.yidu.novel.bean.ArticleSearchBean;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.cache.CacheManager;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.ArticleDTO;
import org.yidu.novel.entity.TArticle;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.entity.TUser;
import org.yidu.novel.utils.CookieUtils;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Pagination;

/**
 * <p>
 * 公共画面的基类
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class AbstractPublicBaseAction extends AbstractBaseAction {

    private static final long serialVersionUID = 4900892616460135567L;
    /**
     * 命名空间。
     */
    public static final String NAMESPACE = "";

    protected static final String CACHE_KEY_INDEX_BLOCK = "CacheKey_indexBlock";
    protected static final String CACHE_KEY_ARTICEL_LIST_PREFIX = "CacheKey_ARTICLE_LIST";
    protected static final String CACHE_KEY_ARTICEL_PREFIX = "CacheKey_ARTICLE";
    protected static final String CACHE_KEY_CHAPTER_LIST_PREFIX = "CacheKey_CHAPTER_LIST";
    protected static final String CACHE_KEY_CHAPTER_PREFIX = "CacheKey_CHAPTER";
    protected static final String CACHE_KEY_HISTORY_PREFIX = "CacheKey_HISTORY";

    /**
     * 区块信息
     */
    private Map<String, Object> blocks = new HashMap<String, Object>();

    public Map<String, Object> getBlocks() {
        return blocks;
    }

    public void setBlocks(Map<String, Object> blocks) {
        this.blocks = blocks;
    }

    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        loadBlock();
        // Cookie
        if (!LoginManager.isLoginFlag()) {
            CookieUtils.getUserCookieAndLogoin(ServletActionContext.getRequest(), userService);
        }
        loadData();
        loadReadHistory();
        if (this.hasErrors()) {
            logger.debug("execute abnormally end.");
            setHasError(true);
            return ERROR;
        }
        logger.debug("execute normally end.");
        return FREEMARKER;
    }

    // 初始化区块信息
    protected void loadBlock() {
        logger.debug("loadBlock start.");
        if (this instanceof IndexAction) {
            logger.debug("this instanceof IndexAction.");
            // 从缓存中把首页用的区块信息取出
            blocks = CacheManager.getObject(CACHE_KEY_INDEX_BLOCK);
            if (blocks == null || blocks.size() == 0) {
                blocks = new HashMap<String, Object>();
                // 没有取到的话从数据库里取出
                // block数据取得
                List<TSystemBlock> blockList = new ArrayList<TSystemBlock>();
                SystemBlockSearchBean searchBean = new SystemBlockSearchBean();
                blockList = systemBlockService.find(searchBean);
                for (TSystemBlock tSystemBlock : blockList) {
                    if (tSystemBlock.getTarget() == YiDuConstants.BlockTarget.INDEX) {
                        if (tSystemBlock.getType() == YiDuConstants.BlockType.ARTICLE_LIST) {
                            ArticleSearchBean articleSearchBean = new ArticleSearchBean();
                            Pagination pagination = new Pagination(tSystemBlock.getLimitnum(), 1);
                            pagination.setSortColumn(tSystemBlock.getSortcol());
                            pagination.setSortOrder(tSystemBlock.getIsasc() ? "ASC" : "DESC");
                            articleSearchBean.setPagination(pagination);
                            List<TArticle> articleList = articleService.find(articleSearchBean);
                            blocks.put(tSystemBlock.getBlockid(), articleList);
                        } else if (tSystemBlock.getType() == YiDuConstants.BlockType.CUSTONIZE_ARTICLE_LIST) {
                            ArticleSearchBean articleSearchBean = new ArticleSearchBean();
                            articleSearchBean.setArticlenos(tSystemBlock.getContent());
                            List<TArticle> articleList = articleService.find(articleSearchBean);
                            blocks.put(tSystemBlock.getBlockid(), articleList);
                        } else if (tSystemBlock.getType() == YiDuConstants.BlockType.HTML) {
                            blocks.put(tSystemBlock.getBlockid(), tSystemBlock.getContent());
                        }
                        CacheManager.putObject(CACHE_KEY_INDEX_BLOCK, blocks);
                    }
                }
            }
        }
        logger.debug("loadBlock normally end.");
    }

    protected ArticleDTO getArticleInfoByNo(int articleno) {
        ArticleDTO article = CacheManager.getObject(CACHE_KEY_ARTICEL_PREFIX + articleno);
        if (article == null || article.getArticleno() == 0) {
            article = new ArticleDTO();
            TArticle tarticle = articleService.getByNo(articleno);
            if (tarticle == null || tarticle.getArticleno() == 0) {
                addActionError(getText("errors.not.exsits.article"));
                return null;
            }
            BeanUtils.copyProperties(tarticle, article);
            CacheManager.putObject(CACHE_KEY_ARTICEL_PREFIX + articleno, article);
        }
        return article;
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

}
