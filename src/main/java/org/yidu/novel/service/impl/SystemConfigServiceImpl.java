package org.yidu.novel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.yidu.novel.bean.SystemConfigSearchBean;
import org.yidu.novel.entity.TSystemConfig;
import org.yidu.novel.service.SystemConfigService;

public class SystemConfigServiceImpl extends HibernateSupportServiceImpl implements SystemConfigService {

    @Override
    public List<TSystemConfig> find(SystemConfigSearchBean searchBean) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append("FROM TSystemBlock where 1=1");

        if (searchBean.getConfigno() != 0) {
            hql.append(" AND configno = ? ");
            params.add(searchBean.getConfigno());
        }

        // TODO

        if (StringUtils.isNotEmpty(searchBean.getType())) {
            hql.append(" AND type = ? ");
            params.add(searchBean.getType());
        }

        return this.find(hql.toString(), params);
    }

    @Override
    public TSystemConfig getByNo(int blockno) {
        TSystemConfig systemConfig = this.get(TSystemConfig.class, blockno);
        return systemConfig;
    }

    @Override
    public void deleteByNo(int blockno) {
        TSystemConfig systemConfig = getByNo(blockno);
        this.delete(systemConfig);
    }

    @Override
    public void save(TSystemConfig systemConfig) {
        this.save(systemConfig);
    }
}
