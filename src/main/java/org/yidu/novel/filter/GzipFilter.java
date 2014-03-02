package org.yidu.novel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

public class GzipFilter implements Filter {
    private FilterConfig filterConfig = null;

    protected final FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        if (!YiDuConstants.yiduConf.getBoolean(YiDuConfig.GZIP_EFFECTIVE, false)) {
            chain.doFilter(req, res);
        } else {
            if (req instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String ae = request.getHeader("accept-encoding");
                if (ae != null && ae.indexOf("gzip") != -1) {
                    GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
                    chain.doFilter(req, wrappedResponse);
                    wrappedResponse.finishResponse();
                    return;
                }
            }
            chain.doFilter(req, res);
        }
    }
}
