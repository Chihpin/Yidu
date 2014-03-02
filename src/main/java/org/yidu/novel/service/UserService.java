package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.UserSearchBean;
import org.yidu.novel.entity.TUser;

public interface UserService {

    /**
     * 根据帐号密码取得用户信息，如果取不到就返回NULL
     * 
     * @param loginid
     * @param password
     * @return 用户信息
     */
    public TUser findByLoginInfo(final String loginid, final String password);

    /**
     * 根据帐号密码取得用户信息，如果取不到就返回NULL<br>
     * 为了在拦截器里使用，临时添加，将来应该和上面合并
     * 
     * @param loginid
     * @param password
     * @return 用户信息
     */
    public TUser findByLoginInfoByJDBC(String loginid, String password);

    /**
     * 取得条件用户列表
     * 
     * @param searchBean
     *            检索条件
     * @return 用户信息列表
     */
    public List<TUser> find(final UserSearchBean searchBean);

    /**
     * 根据用户号取得用户信息，如果取不到就返回NULL<br>
     * 
     * @param userno
     * @return 用户信息
     */
    public TUser getByNo(final int userno);

    /**
     * 根据用户号删除用户信息
     * 
     * @param userno
     */
    public void delteByNo(final int userno);

    /**
     * 保存用户信息
     * 
     * @param user
     *            用户信息
     */
    public void save(final TUser user);

    /**
     * 取得条件用户件数
     * 
     * @param searchBean
     *            检索条件
     * @return 件数
     */
    public int getCount(final UserSearchBean searchBean);

}
