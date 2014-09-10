package org.yidu.novel.action.base;

import org.yidu.novel.bean.ResponseBean;

/**
 * <p>
 * JSON处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public abstract class JsonBaseAction extends AbstractBaseAction {

    private static final long serialVersionUID = 1L;

    protected ResponseBean<?> res;

    @Override
    public String execute() {
        res = loadJsonData();
        return JSON_RESULT;
    }

    protected abstract ResponseBean<?> loadJsonData();

    public ResponseBean<?> getData() {
        return res;
    }

}
