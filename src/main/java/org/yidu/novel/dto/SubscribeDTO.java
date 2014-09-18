package org.yidu.novel.dto;

import org.yidu.novel.entity.TArticle;

public class SubscribeDTO extends TArticle {

    private static final long serialVersionUID = -9120931969014388341L;

    private int subscribeno;

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

}
