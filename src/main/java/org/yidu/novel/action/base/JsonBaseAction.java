package org.yidu.novel.action.base;

import org.yidu.novel.utils.Pagination;
import org.yidu.novel.bean.ResponseBean;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

/**
 * <p>
 * JSON处理的基类
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.1.9
 * @author shinpa.you
 */
public abstract class JsonBaseAction extends AbstractBaseAction {


    public enum RETCODE {
        SUCCESS(0),     // 请求成功

        // User:
        UNLOGIN(1001),   // 请登录
        LOGIN_PERR(1002), // 用户名或密码错误

        // Subscribe
        SUB_MAX(2001),

        // Bookcase
        BOOKCASE_MAX(3001),
        BOOKCASE_NOTFOUND(3002),

        // Book
        BOOK_NOTFOUND(4001),

        // 通用
        PARAM_ERR(-1),   // 参数错误
        LOGIC_ERR(-2),   // 逻辑错误
        SERVER_ERR(-3);  // 服务器错误


        public final int intValue;

        private RETCODE(int number) {
            this.intValue = number;
        }

        public int getIntValue() {
            return intValue;
        }
    }



    /**
     * 串行化版本统一标识符
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回数据
     */
    protected ResponseBean<?> res;


    /**
     * 初始化Pagination，默认每页显示1件
     */
    protected Pagination pagination = new Pagination(YiDuConstants.yiduConf.getInt(YiDuConfig.COUNT_PER_PAGE, 20), 1);

    /**
     * 获取 pagination
     * 
     * @return pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 
     * 设置pagination
     * 
     * 
     * @param pagination
     *            pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    
    @Override
    public String execute() {
        res = loadJsonData();

        return JSON_RESULT;
    }

    /**
     * 加载Json数据
     * 
     * @return Json数据
     */
    protected abstract ResponseBean<?> loadJsonData();

    /**
     * 取得返回数据
     * 
     * @return 返回数据
     */
    public ResponseBean<?> getData() {
        return res;
    }

}
