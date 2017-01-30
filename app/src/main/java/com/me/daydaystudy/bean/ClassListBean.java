package com.me.daydaystudy.bean;

import java.util.List;

/**
 * @author :   郗琛
 * @date :   2017/1/24
 */

public class ClassListBean {

    /**
     * datalist : [{"cid":"5663","course_tname":"蔡岩","course_name":"蔡岩烘焙食品（一）","course_price":"5.00","course_pic":"http://img.dianfu.net/img/20161216/3d07f30fc00e2a2dd1309c5a97e2caba.jpg","course_paycount":"1","school_name":"优学教育"},{"cid":"5664","course_tname":"蔡岩","course_name":"蔡岩烘焙食品（二）","course_price":"5.00","course_pic":"http://img.dianfu.net/img/20161216/2baf9688a15aca9cccacfdc8c9043cce.jpg","course_paycount":"0","school_name":"优学教育"},{"cid":"5662","course_tname":"刘景超","course_name":"实用编发教程（28）","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161209/b773239fe5b727ed17607a7aa7e3573e.jpg","course_paycount":"84","school_name":"优学教育"},{"cid":"5655","course_tname":"徐磊","course_name":"我们爱素描-小萝莉","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161208/7576e3b6aba276cf8b52067629dd7b3a.jpg","course_paycount":"51","school_name":"优学教育"},{"cid":"5654","course_tname":"徐磊","course_name":"我们爱素描-女侧面","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161208/eeef3289b3d69d7352302e72ae0b08b2.jpg","course_paycount":"19","school_name":"优学教育"},{"cid":"5653","course_tname":"徐磊","course_name":"我们爱素描-女肖像","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161208/e340615f0636eaa51736eec71a1711f6.jpg","course_paycount":"17","school_name":"优学教育"},{"cid":"5652","course_tname":"徐磊","course_name":"我们爱素描-男肖像","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161208/dead230a3b58c1be97ab72ef94057e62.jpg","course_paycount":"10","school_name":"优学教育"},{"cid":"5651","course_tname":"徐磊","course_name":"我们爱素描-赫本","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161208/4b981f3131a0a35107629130caee56a6.jpg","course_paycount":"10","school_name":"优学教育"},{"cid":"5650","course_tname":"徐磊","course_name":"我们爱素描-光斑下的女孩","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161208/6633b6018cb778130c97ab46e08f2fc4.jpg","course_paycount":"8","school_name":"优学教育"},{"cid":"5629","course_tname":"迎刃","course_name":"自信恋爱学","course_price":"0.00","course_pic":"http://img.dianfu.net/img/20161202/1980ee0e048b1b5b9295139a381fe379.jpg","course_paycount":"39","school_name":"优学教育"}]
     * count : 487
     * limit : 10
     * curpage : 1
     */

    private int count;
    private int limit;
    private String curpage;
    private List<DatalistBean> datalist;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCurpage() {
        return curpage;
    }

    public void setCurpage(String curpage) {
        this.curpage = curpage;
    }

    public List<DatalistBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DatalistBean> datalist) {
        this.datalist = datalist;
    }

    public static class DatalistBean {
        /**
         * cid : 5663
         * course_tname : 蔡岩
         * course_name : 蔡岩烘焙食品（一）
         * course_price : 5.00
         * course_pic : http://img.dianfu.net/img/20161216/3d07f30fc00e2a2dd1309c5a97e2caba.jpg
         * course_paycount : 1
         * school_name : 优学教育
         */

        private String cid;
        private String course_tname;
        private String course_name;
        private String course_price;
        private String course_pic;
        private String course_paycount;
        private String school_name;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse_tname() {
            return course_tname;
        }

        public void setCourse_tname(String course_tname) {
            this.course_tname = course_tname;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_price() {
            return course_price;
        }

        public void setCourse_price(String course_price) {
            this.course_price = course_price;
        }

        public String getCourse_pic() {
            return course_pic;
        }

        public void setCourse_pic(String course_pic) {
            this.course_pic = course_pic;
        }

        public String getCourse_paycount() {
            return course_paycount;
        }

        public void setCourse_paycount(String course_paycount) {
            this.course_paycount = course_paycount;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }
    }
}
