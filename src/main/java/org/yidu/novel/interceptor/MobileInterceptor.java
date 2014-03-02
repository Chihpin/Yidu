package org.yidu.novel.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

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

                // check if a wireless version of the page exists
                // by looking for a wireless action mapping in the struts.xml
                // Map<String, ResultConfig> results =
                // invocation.getProxy().getConfig().getResults();
                // System.out.println("Results:" + results.toString());
                // if (!results.containsKey(resultCode +
                // RESULT_CODE_SUFFIX_MOBILE)) {
                // return;
                // }

                // send to mobile version if mobile browser is used
                // final String acceptHeader =
                // ServletActionContext.getRequest().getHeader(REQUEST_HEADER_ACCEPT);
                //
                // System.out.println("acceptHeader: " + acceptHeader);
                //
                // if (acceptHeader != null &&
                // acceptHeader.toLowerCase().contains(ACCEPT_HEADER_MOBILE)) {
                // invocation.setResultCode(resultCode +
                // RESULT_CODE_SUFFIX_MOBILE);
                // return;
                // }

                if (StringUtils.equalsIgnoreCase(resultCode, Action.INPUT)
                        || StringUtils.equals(resultCode, Action.SUCCESS)
                        || StringUtils.equals(resultCode, Action.ERROR)) {

                    // Get User Agent String
                    String userAgent = ServletActionContext.getRequest().getHeader("User-Agent");
                    System.out.println("UA: " + userAgent);

                    // Boolean to indicate whether to show mobile version
                    boolean showMobileVersion = false;

                    // Run through each entry in the list of browsers
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
        });

        return invocation.invoke();
    }
}
