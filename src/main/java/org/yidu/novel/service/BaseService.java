package org.yidu.novel.service;

import java.util.List;

public interface BaseService {

    public <T> List<T> find(final T searchBean);

    public <T> Integer getCount(final T searchBean);

    public <T> T getByNo(final int no);

    public void delteByNo(final int no);

    public <T> void saveEntity(final T entity);
}
