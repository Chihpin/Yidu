package org.yidu.novel.action.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TMessage;
import org.yidu.novel.utils.LoginManager;

/**
 * 
 * <p>
 * 消息管理Action
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class MessageAction extends AbstractUserBaseAction {
    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 6707140588808286899L;

    private int messageno;

    private List<TMessage> messageList;

    public int getMessageno() {
        return messageno;
    }

    public void setMessageno(int messageno) {
        this.messageno = messageno;
    }

    public List<TMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<TMessage> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String getTempName() {
        return "user/message";
    }

    @Override
    public int getPageType() {
        return YiDuConstants.Pagetype.PAGE_USER_MESSAGE;
    }

    @Override
    protected void loadData() {
        MessageSearchBean searchBean = new MessageSearchBean();
        int userno = LoginManager.getLoginUser().getUserno();
        searchBean.setUserno(userno);
        searchBean.setTouserno(userno);
        messageList = this.messageService.find(searchBean);
    }

    @Transactional
    public String add() {
        return FREEMARKER_MESSAGE;
    }

    @Transactional
    public String delete() {
        if (messageno != 0) {
            TMessage message = this.messageService.getByNo(messageno);
            if (message.getUserno() == LoginManager.getLoginUser().getUserno()) {
                this.messageService.deleteByNo(messageno);
            } else {
                addActionError(getText("errors.unauthority.message"));
                return FREEMARKER_ERROR;
            }
        }
        this.loadData();
        return INPUT;
    }

}
