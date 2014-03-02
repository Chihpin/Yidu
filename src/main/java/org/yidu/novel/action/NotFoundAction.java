package org.yidu.novel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.yidu.novel.action.base.AbstractPublicBaseAction;

@Action(value = "notFound")
public class NotFoundAction extends AbstractPublicBaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = -7303163605597104098L;

    /**
     * 功能名称。
     */
    public static final String NAME = "notFound";

    /**
     * URL。
     */
    public static final String URL = NAMESPACE + "/" + NAME;

    @SkipValidation
    public String execute() {
        logger.debug("execute start.");
        addActionError(getText("errors.not.exsits.page"));
        return ERROR;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public int getPageType() {
        // TODO Auto-generated method stub
        return 99;
    }
}
