package org.yidu.novel.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.base.AbstractBaseAction;
import org.yidu.novel.action.base.AbstractPublicBaseAction;
import org.yidu.novel.action.base.AbstractUserBaseAction;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class MobileInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -8192961773909614182L;
    private static final String RESULT_CODE_SUFFIX_MOBILE = "mobile_";
    private final Log logger = LogFactory.getLog(this.getClass());

    private static final String[] MOBILE_BROWSER_UAS = { "iPhone OS", "Android", "BlackBerry", "Windows Phone" };

    public String intercept(ActionInvocation invocation) throws Exception {
        logger.debug("AuthCheckInterceptor start.");

        invocation.addPreResultListener(new PreResultListener() {
            public void beforeResult(ActionInvocation invocation, String resultCode) {

                if (invocation.getAction() instanceof AbstractPublicBaseAction
                        || invocation.getAction() instanceof AbstractUserBaseAction) {
                    // 公众页和用户页显示手机版网页
                    // 只过滤正常结果
                    if (StringUtils.equalsIgnoreCase(resultCode, AbstractBaseAction.FREEMARKER)
                            || StringUtils.equals(resultCode, AbstractBaseAction.FREEMARKER_ERROR)) {

                        // 手机版本标识
                        boolean showMobileVersion = false;
                        if (YiDuConstants.yiduConf.getBoolean(YiDuConfig.JUDG_MOBILESITE_BY_DOMIAN, false)) {
                            if (StringUtils.equals(ServletActionContext.getRequest().getServerName(),
                                    YiDuConstants.yiduConf.getString(YiDuConfig.MOBILESITE_DOMIAN))) {
                                showMobileVersion = true;
                            }
                        } else {
                            // 获取User Agent
                            String userAgent = ServletActionContext.getRequest().getHeader("User-Agent");

                            // 当前userAgent是不是手机
                            for (String ua : MOBILE_BROWSER_UAS) {
                                if (userAgent.toLowerCase().matches(".*" + ua.toLowerCase() + ".*")) {
                                    showMobileVersion = true;
                                    break;
                                }
                            }
                        }
                        if (showMobileVersion) {
                            invocation.setResultCode(RESULT_CODE_SUFFIX_MOBILE + resultCode);
                        }
                    }
                }
            }
        });
        logger.debug("AuthCheckInterceptor normally end.");
        return invocation.invoke();
    }
}
