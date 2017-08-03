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
@Action(value = "api/sub/list")
public class ListAction extends JsonBaseUserAction {
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

        SubscribeSearchBean searchBean = new SubscribeSearchBean();
        searchBean.setUserno(LoginManager.getLoginUser().getUserno());
        subscribeList = this.subscribeService.findAllData(searchBean);


        ResponseBean<String> responseBean = new ResponseBean<String>();
        Gson gson = new Gson();
        String obj = gson.toJson(subscribeList);
        responseBean.setDataObj(obj);
        return responseBean;
    }

}
