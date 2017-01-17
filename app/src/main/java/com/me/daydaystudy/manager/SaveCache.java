package com.me.daydaystudy.manager;

import com.me.daydaystudy.bean.DbUtilsBean;
import com.me.daydaystudy.utils.DBUtils;

import org.xutils.ex.DbException;

/**
 * @author :   郗琛
 * @date :   2017/1/11
 */

public class SaveCache {

    /**
     * 获取保存的数据
     *
     * @return
     */
    public static String getCache(String url) {
        try {
            DbUtilsBean first = DBUtils.getDb().selector(DbUtilsBean.class).where("URL", "=", url).and("EXPIRATION_TIME", "<", System.currentTimeMillis() + "").findFirst();
            return first.getContent();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 保存数据
     *
     * @return
     */
    public static void saveCache(String url, String content, long time) {
        try {
            DBUtils.getDb().saveOrUpdate(new DbUtilsBean(url, content, System.currentTimeMillis() + time));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
