package org.yidu.novel.result;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.action.ImageAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class CustomImageBytesResult implements Result {

    private static final long serialVersionUID = -4829552280917099091L;

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void execute(ActionInvocation invocation) throws Exception {
        logger.debug("execute(10) start.");
        ImageAction action = (ImageAction) invocation.getAction();
        byte[] binaryinfo = action.getGraph();
        try {
            if (binaryinfo != null) {
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType(action.getCustomContentType());
                response.getOutputStream().write(binaryinfo);
                response.getOutputStream().flush();
                logger.debug("execute(99) normally end.");
            } else {
                logger.debug("execute(80) binaryinfo is null.");
                logger.debug("execute(89) abnormally end.");
            }
        } catch (Exception e) {
            logger.debug("execute(79) abnormally end by ClientAbortException.");
        }
    }
}
