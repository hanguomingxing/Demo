package com.me.daydaystudy.bean;

import java.util.List;

/**
 * autour: 孙运泰
 * date: 2017/2/6 15:35
 * update: 2017/2/6
 */

public class CatalogBean {

    /**
     * code : 200
     * course_name : 思维导图实际运用
     * data : [{"id":"5003","nodes":[{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"什么是思维导图","sections_sort":"1","seid":"25513","vtime":892457},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"思维导图制作","sections_sort":"2","seid":"25515","vtime":1012829},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"提取关键词","sections_sort":"3","seid":"25516","vtime":329165},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"制作思维导图的步骤","sections_sort":"4","seid":"25518","vtime":797162},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"思维导图实战运用（作业篇）","sections_sort":"5","seid":"25520","vtime":207005},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"思维导图的用处","sections_sort":"6","seid":"25521","vtime":377532}],"step_course_id":"5221","step_name":"思维导图实际运用","step_order":"1"}]
     * msg :
     */

    private int code;
    private String course_name;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5003
         * nodes : [{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"什么是思维导图","sections_sort":"1","seid":"25513","vtime":892457},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"思维导图制作","sections_sort":"2","seid":"25515","vtime":1012829},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"提取关键词","sections_sort":"3","seid":"25516","vtime":329165},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"制作思维导图的步骤","sections_sort":"4","seid":"25518","vtime":797162},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"思维导图实战运用（作业篇）","sections_sort":"5","seid":"25520","vtime":207005},{"sections_chid":"5003","sections_des":"","sections_isfree":"1","sections_name":"思维导图的用处","sections_sort":"6","seid":"25521","vtime":377532}]
         * step_course_id : 5221
         * step_name : 思维导图实际运用
         * step_order : 1
         */

        private String id;
        private String step_course_id;
        private String step_name;
        private String step_order;
        private List<NodesBean> nodes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStep_course_id() {
            return step_course_id;
        }

        public void setStep_course_id(String step_course_id) {
            this.step_course_id = step_course_id;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        public String getStep_order() {
            return step_order;
        }

        public void setStep_order(String step_order) {
            this.step_order = step_order;
        }

        public List<NodesBean> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBean> nodes) {
            this.nodes = nodes;
        }

        public static class NodesBean {
            /**
             * sections_chid : 5003
             * sections_des :
             * sections_isfree : 1
             * sections_name : 什么是思维导图
             * sections_sort : 1
             * seid : 25513
             * vtime : 892457
             */

            private String sections_chid;
            private String sections_des;
            private String sections_isfree;
            private String sections_name;
            private String sections_sort;
            private String seid;
            private int vtime;

            public String getSections_chid() {
                return sections_chid;
            }

            public void setSections_chid(String sections_chid) {
                this.sections_chid = sections_chid;
            }

            public String getSections_des() {
                return sections_des;
            }

            public void setSections_des(String sections_des) {
                this.sections_des = sections_des;
            }

            public String getSections_isfree() {
                return sections_isfree;
            }

            public void setSections_isfree(String sections_isfree) {
                this.sections_isfree = sections_isfree;
            }

            public String getSections_name() {
                return sections_name;
            }

            public void setSections_name(String sections_name) {
                this.sections_name = sections_name;
            }

            public String getSections_sort() {
                return sections_sort;
            }

            public void setSections_sort(String sections_sort) {
                this.sections_sort = sections_sort;
            }

            public String getSeid() {
                return seid;
            }

            public void setSeid(String seid) {
                this.seid = seid;
            }

            public int getVtime() {
                return vtime;
            }

            public void setVtime(int vtime) {
                this.vtime = vtime;
            }
        }
    }
}
