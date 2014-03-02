package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.SystemBlockSearchBean;
import org.yidu.novel.entity.TSystemBlock;
import org.yidu.novel.service.SystemBlockService;
import org.yidu.novel.utils.Pagination;

public class SystemBlockServiceImpl extends HibernateSupportServiceImpl implements SystemBlockService {

    @Override
    public List<TSystemBlock> find(SystemBlockSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TSystemBlock where 1=1");

        if (searchBean.getBlockno() != 0) {
            hql.append(" AND blockno = ? ");
            params.add(searchBean.getBlockno());
        }

        if (StringUtils.isNotEmpty(searchBean.getBlockname())) {
            hql.append(" AND blockname = ? ");
            params.add(searchBean.getBlockname());
        }

        if (searchBean.getType() != null && searchBean.getType() != 0) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }

        Pagination pagination = searchBean.getPagination();
        // 添加排序信息
        if (pagination != null) {
            hql.append(pagination.getSortInfo());
            return this.findByRange(hql.toString(), pagination.getStart(), pagination.getEnd(), params);
        } else {
            return this.find(hql.toString(), params);
        }
    }

    @Override
    public TSystemBlock getByNo(int blockno) {
        TSystemBlock systemBlock = this.get(TSystemBlock.class, blockno);
        return systemBlock;
    }

    @Override
    public void delteByNo(int blockno) {
        TSystemBlock systemBlock = getByNo(blockno);
        this.delete(systemBlock);
    }

    @Override
    public void save(TSystemBlock systemBlock) {
        this.saveOrUpdate(systemBlock);
    }

    @Override
    public Integer getCount(SystemBlockSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("Select count(*) FROM TSystemBlock where 1=1");

        if (searchBean.getBlockno() != 0) {
            hql.append(" AND blockno = ? ");
            params.add(String.valueOf(searchBean.getBlockno()));
        }

        if (StringUtils.isNotEmpty(searchBean.getBlockname())) {
            hql.append(" AND blockname = ? ");
            params.add(searchBean.getBlockname());
        }

        if (searchBean.getType() != null && searchBean.getType() != 0) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }
        return this.getIntResult(hql.toString(), params);

    }

}
