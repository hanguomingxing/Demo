package com.me.daydaystudy.bean;

/**
 * @author :   郗琛
 * @date :   2017/1/11
 */

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;


/**
 * 从网络请求下来的数据保存
 *
 * @author :   郗琛
 * @date :   2016/12/29
 */

@Table(name = "netData")      //为表创建NAME
public class DbUtilsBean {
    /**
     * 主键
     * 请求路径
     */
    @Column(name = "URL", isId = true, property = "NOT NULL")
    private String url;
    /**
     * 请求的数据
     */
    @Column(name = "CONTENT", property = "NOT NULL")
    private String content;
    /**
     * 过期时间
     */
    @Column(name = "EXPIRATION_TIME", property = "NOT NULL")
    private long expirationTime;
    /**
     * 请求时间
     */
    @Column(name = "REQUEST_TIME", property = "NOT NULL")
    private long requestTime;


    public DbUtilsBean() {
    }

    public DbUtilsBean(String url, String content, long requestTime) {
        this.url = url;
        this.content = content;
        this.requestTime = requestTime;
        this.expirationTime = System.currentTimeMillis() + requestTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }
}

