package org.yidu.novel.action;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.bean.MessageSearchBean;
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
    protected void loadData() {
        messageList = this.messageService.find(new MessageSearchBean());
    }

    @Transactional
    public String add() {
        initCollections(new String[] { "collectionProperties.article.category" });
        return MESSAGE;
    }

    @Transactional
    public String delete() {
        if (messageno != 0) {
            TMessage message = this.messageService.getByNo(messageno);
            if (message.getUserno() == LoginManager.getLoginUser().getUserno()) {
                this.messageService.delteByNo(messageno);
            } else {
                addActionError(getText("errors.unauthority.message"));
                return ERROR;
            }
        }
        this.loadData();
        return INPUT;
    }

}
