package org.yidu.novel.service.impl;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.service.CleanDeleteDataService;

public class CleanDeleteDataServiceImpl extends HibernateSupportServiceImpl implements CleanDeleteDataService {

    @Override
    public void cleanDeleteData() {
        int keepdays = YiDuConstants.yiduConf.getInt(YiDuConfig.KEEP_DELETE_DATA_DAYS, 5);
        Set<String> tableSet = new HashSet<String>();
        tableSet.add("t_user");
        tableSet.add("t_review");
        tableSet.add("t_chapter");
        tableSet.add("t_system_block");
        tableSet.add("t_bookcase");
        tableSet.add("t_article");
        tableSet.add("t_message");
        tableSet.add("t_credit_history");
        String deleteSql = "delete from {0} where deleteflag and modifytime < (now() -  INTERVAL '{1} days')";
        for (String table : tableSet) {
            yiduJdbcTemplate.execute(MessageFormat.format(deleteSql, new Object[] { table, keepdays }));
        }
    }

}
