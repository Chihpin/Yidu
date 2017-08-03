package org.yidu.novel.action.api.sub;

import com.google.gson.Gson;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.JsonBaseUserAction;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.bean.SubscribeSearchBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.dto.SubscribeDTO;
import org.yidu.novel.entity.TSubscribe;
import org.yidu.novel.utils.LoginManager;
import org.yidu.novel.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 *          我的小说订阅列表
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
@Action(value = "api/sub/add")
public class AddAction extends JsonBaseUserAction {

    /**
     * 订阅编号
     */
    private int subscribeno;

    /**
     * 小说编号
     */
    private int articleno;
    /**
     * 小说列表
     */
    private List<SubscribeDTO> subscribeList = new ArrayList<SubscribeDTO>();

    /**
     * 获取subscribeno
     * 
     * @return subscribeno
     */
    public int getSubscribeno() {
        return subscribeno;
    }

    /**
     * 
     * 设置subscribeno
     * 
     * 
     * @param subscribeno
     *            subscribeno
     */
    public void setSubscribeno(int subscribeno) {
        this.subscribeno = subscribeno;
    }

    /**
     * 获取articleno
     * 
     * @return articleno
     */
    public int getArticleno() {
        return articleno;
    }

    /**
     * 
     * 设置articleno
     * 
     * 
     * @param articleno
     *            articleno
     */
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }

    /**
     * 获取subscribeList
     * 
     * @return subscribeList
     */
    public List<SubscribeDTO> getSubscribeList() {
        return subscribeList;
    }

    /**
     * 
     * 设置subscribeList
     * 
     * 
     * @param subscribeList
     *            subscribeList
     */
    public void setSubscribeList(List<SubscribeDTO> subscribeList) {
        this.subscribeList = subscribeList;
    }

    public int getMaxSubscribeNum() {
        return YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_SUBSCRIBE);
    }

    public int getSubscribeNum() {
        return Utils.isDefined(subscribeList) ? subscribeList.size() : 0;
    }


    @Override
    protected ResponseBean<?> loadJsonData() {

        ResponseBean<String> responseBean = new ResponseBean<String>();

        // 小说号如果没有的话，终止登录，返回错误画面
        if (articleno == 0) {
            addActionError(getText("errors.not.exsits.article"));

            return new ResponseBean<Object>(RETCODE.PARAM_ERR.intValue, null);
        }

        // 检查当前登录的最大件数
        SubscribeSearchBean searchBean = new SubscribeSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        int subscribeCount = this.subscribeService.getCount(searchBean);
        if (subscribeCount > YiDuConstants.yiduConf.getInt(YiDuConfig.MAX_SUBSCRIBE, 30)) {
            addActionError(getText("errors.max.subscribe"));

            return new ResponseBean<Object>(RETCODE.SUB_MAX.intValue, null);
        }

        searchBean.setArticleno(articleno);
        subscribeCount = this.subscribeService.getCount(searchBean);
        if (subscribeCount == 0) {
            TSubscribe subscribe = new TSubscribe();
            subscribe.setArticleno(articleno);
            subscribe.setUserno(LoginManager.getLoginUser().getUserno());

            this.subscribeService.save(subscribe);

        }
        // 已经存在啦，算了，告诉他成功啦！哈哈
        addActionMessage(getText("messages.proccess.success"));

        return responseBean;
    }
}
