package org.yidu.novel.service;

import java.util.List;

import org.yidu.novel.bean.SystemConfigSearchBean;
import org.yidu.novel.entity.TSystemConfig;

public interface SystemConfigService {

    public List<TSystemConfig> find(final SystemConfigSearchBean searchBean);

    public TSystemConfig getByNo(final int configno);

    public void deleteByNo(final int configno);

    public void save(final TSystemConfig systemConfig);

}
