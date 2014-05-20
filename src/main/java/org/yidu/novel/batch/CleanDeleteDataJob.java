package org.yidu.novel.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.yidu.novel.service.CleanDeleteDataService;

/**
 * 
 * <p>
 * 清理已删除数据
 * </p>
 * Copyright(c) 2014 YiDu-Novel. All rights reserved.
 * 
 * @version 1.0.0
 * @author shinpa.you
 */
public class CleanDeleteDataJob extends QuartzJobBean {
    /**
     * 输出log
     */
    private Log logger = LogFactory.getLog(this.getClass());

    private CleanDeleteDataService cleanDeleteDataService;

    public void setCleanDeleteDataService(CleanDeleteDataService cleanDeleteDataService) {
        this.cleanDeleteDataService = cleanDeleteDataService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.debug("CleanDeleteDataJob start.");
        try {
            cleanDeleteDataService.cleanDeleteData();
            logger.debug("CleanDeleteDataJob normally end.");
        } catch (Exception e) {
            logger.error(e);
            logger.debug("CleanDeleteDataJob abnormally end.");
        }
    }
}