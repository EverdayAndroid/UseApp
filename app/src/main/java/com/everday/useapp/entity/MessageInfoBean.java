package com.everday.useapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/9/8
 * description: 消息
 */
public class MessageInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : {"state":"ok","page":{"totalRow":1,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"id":1,"title":"通知公告","typeName":"发布测试","pubDate":"2019-08-26 00:00:00","content":"12321"}]}}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * state : ok
         * page : {"totalRow":1,"pageNumber":1,"lastPage":true,"firstPage":true,"totalPage":1,"pageSize":10,"list":[{"id":1,"title":"通知公告","typeName":"发布测试","pubDate":"2019-08-26 00:00:00","content":"12321"}]}
         */

        private String state;
        private PageBean page;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public static class PageBean {
            /**
             * totalRow : 1
             * pageNumber : 1
             * lastPage : true
             * firstPage : true
             * totalPage : 1
             * pageSize : 10
             * list : [{"id":1,"title":"通知公告","typeName":"发布测试","pubDate":"2019-08-26 00:00:00","content":"12321"}]
             */

            private int totalRow;
            private int pageNumber;
            private boolean lastPage;
            private boolean firstPage;
            private int totalPage;
            private int pageSize;
            private List<MessageBean> list;

            public int getTotalRow() {
                return totalRow;
            }

            public void setTotalRow(int totalRow) {
                this.totalRow = totalRow;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public boolean isLastPage() {
                return lastPage;
            }

            public void setLastPage(boolean lastPage) {
                this.lastPage = lastPage;
            }

            public boolean isFirstPage() {
                return firstPage;
            }

            public void setFirstPage(boolean firstPage) {
                this.firstPage = firstPage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public List<MessageBean> getList() {
                if (list == null) {
                    return new ArrayList<>();
                }
                return list;
            }
        }
    }
}
