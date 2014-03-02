package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;

public interface SystemBlockService {

    public List<TSystemBlock> find(final SystemBlockSearchBean searchBean);

    public Integer getCount(final SystemBlockSearchBean searchBean);

    public TSystemBlock getByNo(final int blockno);

    public void delteByNo(final int blockno);

    public void save(final TSystemBlock systemBlock);

}
