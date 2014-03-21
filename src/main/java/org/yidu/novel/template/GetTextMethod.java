package org.yidu.novel.template;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>
 * Freemarker自定义方法 <br>
 * 实现getText(i18n)功能
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class GetTextMethod implements TemplateMethodModel {

    private ActionSupport action;

    /**
     * 带参数的构造函数
     * 
     * @param action
     *            ActionSupport对象
     */
    public GetTextMethod(ActionSupport action) {
        this.action = action;
    }

    /**
     * 执行方法
     * 
     * @param argList
     *            方法参数列表
     * @return Object 方法返回值
     * @throws TemplateModelException
     */
    @SuppressWarnings("rawtypes")
    public Object exec(List argList) throws TemplateModelException {
        // 限定方法中必须且只能传递一个参数
        if (argList.size() != 1) {
            throw new TemplateModelException("Wrong arguments!");
        }
        // 返回getText执行结果
        return action.getText((String) argList.get(0));
    }
}
