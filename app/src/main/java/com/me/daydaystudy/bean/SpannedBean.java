package com.me.daydaystudy.bean;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/16.
 */

public class SpannedBean {

    /**
     * tid : 77
     * tname : 星座
     */

    private String tid;
    private String tname;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpannedBean)) return false;

        SpannedBean that = (SpannedBean) o;

        return getTname().equals(that.getTname());

    }

    @Override
    public int hashCode() {
        return getTname().hashCode();
    }
}
