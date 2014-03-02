package org.yidu.novel.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.yidu.novel.action.base.AbstractAdminListBaseAction;
import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;

/**
 * <p>
 * 消息管理
 * </p>
 * Copyright(c) 2013 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
@Action(value = "messageList")
public class MessageListAction extends AbstractAdminListBaseAction {

    private static final long serialVersionUID = -5969611068273330970L;
    /**
     * 功能名称。
     */
    public static final String NAME = "messageList";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    private int messageno;

    private List<TMessage> messageList = new ArrayList<TMessage>();

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
        initCollections(new String[] { "collectionProperties.message.isread" });

        if (StringUtils.isEmpty(pagination.getSortColumn())) {
            pagination.setSortColumn("postdate");
            pagination.setSortOrder("DESC");
        }
        // 总件数设置
        MessageSearchBean searchBean = new MessageSearchBean();
        pagination.setPreperties(messageService.getCount(searchBean));
        searchBean.setPagination(pagination);
        messageList = messageService.find(searchBean);
        // Setting number of records in the particular page
        pagination.setPageRecords(messageList.size());

    }

    public String delete() throws Exception {
        messageService.delteByNo(messageno);
        loadData();
        return INPUT;
    }

}
