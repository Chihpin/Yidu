package org.yidu.novel.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.action.base.AbstractUserBaseAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class MobileInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -8192961773909614182L;
    private static final String RESULT_CODE_SUFFIX_MOBILE = "mobile";

    private static final String[] MOBILE_BROWSER_UAS = { "iPhone OS", "Android", "BlackBerry", "Windows Phone" };

    public String intercept(ActionInvocation invocation) throws Exception {

        invocation.addPreResultListener(new PreResultListener() {
            public void beforeResult(ActionInvocation invocation, String resultCode) {

                if (invocation.getAction() instanceof AbstractPublicBaseAction
                        || invocation.getAction() instanceof AbstractUserBaseAction) {
                    // 公众页和用户页显示手机版网页
                    // 只过滤正常结果
                    if (StringUtils.equalsIgnoreCase(resultCode, Action.INPUT)
                            || StringUtils.equals(resultCode, Action.SUCCESS)
                            || StringUtils.equals(resultCode, Action.ERROR)) {

                        // 获取User Agent
                        String userAgent = ServletActionContext.getRequest().getHeader("User-Agent");
                        System.out.println("UA: " + userAgent);

                        // 手机版本标识
                        boolean showMobileVersion = false;

                        // 当前userAgent是不是手机
                        for (String ua : MOBILE_BROWSER_UAS) {
                            if (userAgent.toLowerCase().matches(".*" + ua.toLowerCase() + ".*")) {
                                showMobileVersion = true;
                                break;
                            }
                        }
                        if (showMobileVersion) {
                            invocation.setResultCode(resultCode + RESULT_CODE_SUFFIX_MOBILE);
                        }
                    }
                }
            }
        });
        return invocation.invoke();
    }
}
