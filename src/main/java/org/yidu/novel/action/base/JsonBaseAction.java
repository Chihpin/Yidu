package org.yidu.novel.action.base;

import org.yidu.novel.bean.ResponseBean;

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
