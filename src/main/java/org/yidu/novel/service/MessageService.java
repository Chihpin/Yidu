package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.MessageSearchBean;
import org.yidu.novel.entity.TMessage;

public interface MessageService {

    public TMessage getByNo(final int messageno);

    public void delteByNo(final int messageno);

    public void save(final TMessage message);

    public List<TMessage> find(final MessageSearchBean searchBean);

    public int getCount(final MessageSearchBean searchBean);

}
