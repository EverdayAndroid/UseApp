package com.everday.useapp.entity;

import java.util.List;

public class TaskInfoBean {

    /**
     * result : 0
     * detail :
     * data : {"records":[{"taskID":337884,"taskName":"电子产品维修员","startTime":"2019-08-29 16:30","endTime":"2019-08-29 18:00","detailAddress":"上海市浦东新区上海野生动物园","longitude":"121.699255000000","latitude":"31.050177000000","cityID":310100,"cityName":"上海市","salary":"180.00","laborID":null,"projectID":178624,"positionID":53,"positionName":"电子产品维护","distance":"1124.2公里","companyID":13,"companyName":"上海顶****有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337919,"taskName":"二手车市场推广","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"上海市浦东新区港城路","longitude":"121.574851000000","latitude":"31.353017000000","cityID":310100,"cityName":"上海市","salary":"149.00","laborID":null,"projectID":178659,"positionID":22,"positionName":"市场推广","distance":"1092.4公里","companyID":10,"companyName":"捷****信息咨询有限公司","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337937,"taskName":"电子产品维修员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"广州市白云区钟落潭镇障岗村广东青年职业学院(白云校区)","longitude":"113.434613000000","latitude":"23.374382000000","cityID":440100,"cityName":"广州市","salary":"121.00","laborID":null,"projectID":178677,"positionID":53,"positionName":"电子产品维护","distance":"1601.5公里","companyID":13,"companyName":"上海顶****有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337942,"taskName":"家庭保洁","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"北京市通州区马驹桥镇环科中路17号联东U谷西区","longitude":"116.428307000000","latitude":"39.955036000000","cityID":110100,"cityName":"北京市","salary":"83.00","laborID":null,"projectID":178682,"positionID":47,"positionName":"保洁","distance":"412.1公里","companyID":3,"companyName":"培刀科技","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337819,"taskName":"家庭保洁","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"上海市杨浦区翔殷路","longitude":"121.531951204338","latitude":"31.305046990832","cityID":310100,"cityName":"上海市","salary":"134.00","laborID":null,"projectID":178559,"positionID":47,"positionName":"保洁","distance":"1093.1公里","companyID":4,"companyName":"无界网络","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337831,"taskName":"企业市场推广","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"上海市浦东新区蓝村路","longitude":"121.528201184855","latitude":"31.211883115484","cityID":310100,"cityName":"上海市","salary":"162.00","laborID":null,"projectID":178571,"positionID":60,"positionName":"市场调研","distance":"1100.0公里","companyID":5,"companyName":"安徽****信息科技有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337963,"taskName":"通信技术咨询顾问","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"北京市朝阳区金隅国际A座2801","longitude":"116.487438000000","latitude":"39.911018000000","cityID":110100,"cityName":"北京市","salary":"124.00","laborID":null,"projectID":178703,"positionID":42,"positionName":"咨询顾问","distance":"413.6公里","companyID":9,"companyName":"广州******科技有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337448,"taskName":"蓝妹啤酒促销","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"上海市徐汇区龙华中路","longitude":"121.457442640448","latitude":"31.184781795120","cityID":310100,"cityName":"上海市","salary":"123.00","laborID":null,"projectID":178203,"positionID":22,"positionName":"市场推广","distance":"1097.3公里","companyID":12,"companyName":"上海****营销策划有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337479,"taskName":"奥特莱斯卖场促销员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"上海市宝山区江杨北路","longitude":"121.439814000000","latitude":"31.407859000000","cityID":310100,"cityName":"上海市","salary":"138.00","laborID":null,"projectID":178234,"positionID":56,"positionName":"市场推广","distance":"1078.9公里","companyID":3,"companyName":"培刀科技","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":"0 12ea","extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337481,"taskName":"话剧执舞台监督员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"上海市虹口区江湾镇","longitude":"121.485079000000","latitude":"31.305508000000","cityID":310100,"cityName":"上海市","salary":"97.00","laborID":null,"projectID":178236,"positionID":87,"positionName":"会展执行","distance":"1089.9公里","companyID":14,"companyName":"一台****产业有限公司","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337482,"taskName":"CZ在线客服","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"上海市虹口区江湾镇","longitude":"121.485079000000","latitude":"31.305508000000","cityID":310100,"cityName":"上海市","salary":"368.00","laborID":null,"projectID":178237,"positionID":69,"positionName":"在线客服","distance":"1089.9公里","companyID":11,"companyName":"上海*****快递有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337531,"taskName":"CZ在线客服","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"北京市东城区朝阳门北大街5号五矿广场","longitude":"116.344156000000","latitude":"40.047528000000","cityID":110100,"cityName":"北京市","salary":"113.00","laborID":null,"projectID":178286,"positionID":69,"positionName":"在线客服","distance":"412.2公里","companyID":11,"companyName":"上海*****快递有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337574,"taskName":"物流搬运员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"北京市玄武区中山东路235号","longitude":"116.471717000000","latitude":"39.967583000000","cityID":110100,"cityName":"北京市","salary":"186.00","laborID":null,"projectID":178329,"positionID":64,"positionName":"搬运工","distance":"416.0公里","companyID":8,"companyName":"北京***物流有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337635,"taskName":"物流搬运员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"上海市浦东新区上海儿童医学中心","longitude":"121.523926383033","latitude":"31.204050478629","cityID":310100,"cityName":"上海市","salary":"142.00","laborID":null,"projectID":178375,"positionID":64,"positionName":"搬运工","distance":"1100.3公里","companyID":8,"companyName":"北京***物流有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337688,"taskName":"酒店客房保洁员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 16:30","detailAddress":"上海市闵行区合川路","longitude":"121.384995767326","latitude":"31.167103637856","cityID":310100,"cityName":"上海市","salary":"144.00","laborID":null,"projectID":178428,"positionID":4,"positionName":"保洁","distance":"1093.7公里","companyID":6,"companyName":"北**管理有限公司","isAssignLocation":1,"duration":"0.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null}],"totalCount":1281,"totalPages":86,"pageIndex":1,"pageSize":15}
     */

    private int result;
    private String detail;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * records : [{"taskID":337884,"taskName":"电子产品维修员","startTime":"2019-08-29 16:30","endTime":"2019-08-29 18:00","detailAddress":"上海市浦东新区上海野生动物园","longitude":"121.699255000000","latitude":"31.050177000000","cityID":310100,"cityName":"上海市","salary":"180.00","laborID":null,"projectID":178624,"positionID":53,"positionName":"电子产品维护","distance":"1124.2公里","companyID":13,"companyName":"上海顶****有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337919,"taskName":"二手车市场推广","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"上海市浦东新区港城路","longitude":"121.574851000000","latitude":"31.353017000000","cityID":310100,"cityName":"上海市","salary":"149.00","laborID":null,"projectID":178659,"positionID":22,"positionName":"市场推广","distance":"1092.4公里","companyID":10,"companyName":"捷****信息咨询有限公司","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337937,"taskName":"电子产品维修员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"广州市白云区钟落潭镇障岗村广东青年职业学院(白云校区)","longitude":"113.434613000000","latitude":"23.374382000000","cityID":440100,"cityName":"广州市","salary":"121.00","laborID":null,"projectID":178677,"positionID":53,"positionName":"电子产品维护","distance":"1601.5公里","companyID":13,"companyName":"上海顶****有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337942,"taskName":"家庭保洁","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"北京市通州区马驹桥镇环科中路17号联东U谷西区","longitude":"116.428307000000","latitude":"39.955036000000","cityID":110100,"cityName":"北京市","salary":"83.00","laborID":null,"projectID":178682,"positionID":47,"positionName":"保洁","distance":"412.1公里","companyID":3,"companyName":"培刀科技","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337819,"taskName":"家庭保洁","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"上海市杨浦区翔殷路","longitude":"121.531951204338","latitude":"31.305046990832","cityID":310100,"cityName":"上海市","salary":"134.00","laborID":null,"projectID":178559,"positionID":47,"positionName":"保洁","distance":"1093.1公里","companyID":4,"companyName":"无界网络","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337831,"taskName":"企业市场推广","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"上海市浦东新区蓝村路","longitude":"121.528201184855","latitude":"31.211883115484","cityID":310100,"cityName":"上海市","salary":"162.00","laborID":null,"projectID":178571,"positionID":60,"positionName":"市场调研","distance":"1100.0公里","companyID":5,"companyName":"安徽****信息科技有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337963,"taskName":"通信技术咨询顾问","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"北京市朝阳区金隅国际A座2801","longitude":"116.487438000000","latitude":"39.911018000000","cityID":110100,"cityName":"北京市","salary":"124.00","laborID":null,"projectID":178703,"positionID":42,"positionName":"咨询顾问","distance":"413.6公里","companyID":9,"companyName":"广州******科技有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337448,"taskName":"蓝妹啤酒促销","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"上海市徐汇区龙华中路","longitude":"121.457442640448","latitude":"31.184781795120","cityID":310100,"cityName":"上海市","salary":"123.00","laborID":null,"projectID":178203,"positionID":22,"positionName":"市场推广","distance":"1097.3公里","companyID":12,"companyName":"上海****营销策划有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337479,"taskName":"奥特莱斯卖场促销员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"上海市宝山区江杨北路","longitude":"121.439814000000","latitude":"31.407859000000","cityID":310100,"cityName":"上海市","salary":"138.00","laborID":null,"projectID":178234,"positionID":56,"positionName":"市场推广","distance":"1078.9公里","companyID":3,"companyName":"培刀科技","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":"0 12ea","extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337481,"taskName":"话剧执舞台监督员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 18:00","detailAddress":"上海市虹口区江湾镇","longitude":"121.485079000000","latitude":"31.305508000000","cityID":310100,"cityName":"上海市","salary":"97.00","laborID":null,"projectID":178236,"positionID":87,"positionName":"会展执行","distance":"1089.9公里","companyID":14,"companyName":"一台****产业有限公司","isAssignLocation":1,"duration":"2小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337482,"taskName":"CZ在线客服","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"上海市虹口区江湾镇","longitude":"121.485079000000","latitude":"31.305508000000","cityID":310100,"cityName":"上海市","salary":"368.00","laborID":null,"projectID":178237,"positionID":69,"positionName":"在线客服","distance":"1089.9公里","companyID":11,"companyName":"上海*****快递有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337531,"taskName":"CZ在线客服","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"北京市东城区朝阳门北大街5号五矿广场","longitude":"116.344156000000","latitude":"40.047528000000","cityID":110100,"cityName":"北京市","salary":"113.00","laborID":null,"projectID":178286,"positionID":69,"positionName":"在线客服","distance":"412.2公里","companyID":11,"companyName":"上海*****快递有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337574,"taskName":"物流搬运员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:00","detailAddress":"北京市玄武区中山东路235号","longitude":"116.471717000000","latitude":"39.967583000000","cityID":110100,"cityName":"北京市","salary":"186.00","laborID":null,"projectID":178329,"positionID":64,"positionName":"搬运工","distance":"416.0公里","companyID":8,"companyName":"北京***物流有限公司","isAssignLocation":1,"duration":"1小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337635,"taskName":"物流搬运员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 17:30","detailAddress":"上海市浦东新区上海儿童医学中心","longitude":"121.523926383033","latitude":"31.204050478629","cityID":310100,"cityName":"上海市","salary":"142.00","laborID":null,"projectID":178375,"positionID":64,"positionName":"搬运工","distance":"1100.3公里","companyID":8,"companyName":"北京***物流有限公司","isAssignLocation":1,"duration":"1.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null},{"taskID":337688,"taskName":"酒店客房保洁员","startTime":"2019-08-29 16:00","endTime":"2019-08-29 16:30","detailAddress":"上海市闵行区合川路","longitude":"121.384995767326","latitude":"31.167103637856","cityID":310100,"cityName":"上海市","salary":"144.00","laborID":null,"projectID":178428,"positionID":4,"positionName":"保洁","distance":"1093.7公里","companyID":6,"companyName":"北**管理有限公司","isAssignLocation":1,"duration":"0.5小时","taskStatus":150,"taskStatusDescription":"","projectFiles":"","taskLogId":0,"cancelReason":null,"cancelTime":null,"isFaker":1,"salaryPayTime":null,"extendType":0,"extendProperty1":"","leaveDetailAddress":"","arriveDetailAddress":"","leaveAddressDistance":null,"arriveAddressDistance":null}]
         * totalCount : 1281
         * totalPages : 86
         * pageIndex : 1
         * pageSize : 15
         */

        private int totalCount;
        private int totalPages;
        private int pageIndex;
        private int pageSize;
        private List<RecordsBean> records;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * taskID : 337884
             * taskName : 电子产品维修员
             * startTime : 2019-08-29 16:30
             * endTime : 2019-08-29 18:00
             * detailAddress : 上海市浦东新区上海野生动物园
             * longitude : 121.699255000000
             * latitude : 31.050177000000
             * cityID : 310100
             * cityName : 上海市
             * salary : 180.00
             * laborID : null
             * projectID : 178624
             * positionID : 53
             * positionName : 电子产品维护
             * distance : 1124.2公里
             * companyID : 13
             * companyName : 上海顶****有限公司
             * isAssignLocation : 1
             * duration : 1.5小时
             * taskStatus : 150
             * taskStatusDescription :
             * projectFiles :
             * taskLogId : 0
             * cancelReason : null
             * cancelTime : null
             * isFaker : 1
             * salaryPayTime : null
             * extendType : 0
             * extendProperty1 :
             * leaveDetailAddress :
             * arriveDetailAddress :
             * leaveAddressDistance : null
             * arriveAddressDistance : null
             */

            private int taskID;
            private String taskName;
            private String startTime;
            private String endTime;
            private String detailAddress;
            private String longitude;
            private String latitude;
            private int cityID;
            private String cityName;
            private String salary;
            private Object laborID;
            private int projectID;
            private int positionID;
            private String positionName;
            private String distance;
            private int companyID;
            private String companyName;
            private int isAssignLocation;
            private String duration;
            private int taskStatus;
            private String taskStatusDescription;
            private String projectFiles;
            private int taskLogId;
            private Object cancelReason;
            private Object cancelTime;
            private int isFaker;
            private Object salaryPayTime;
            private int extendType;
            private String extendProperty1;
            private String leaveDetailAddress;
            private String arriveDetailAddress;
            private Object leaveAddressDistance;
            private Object arriveAddressDistance;

            public int getTaskID() {
                return taskID;
            }

            public void setTaskID(int taskID) {
                this.taskID = taskID;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public Object getLaborID() {
                return laborID;
            }

            public void setLaborID(Object laborID) {
                this.laborID = laborID;
            }

            public int getProjectID() {
                return projectID;
            }

            public void setProjectID(int projectID) {
                this.projectID = projectID;
            }

            public int getPositionID() {
                return positionID;
            }

            public void setPositionID(int positionID) {
                this.positionID = positionID;
            }

            public String getPositionName() {
                return positionName;
            }

            public void setPositionName(String positionName) {
                this.positionName = positionName;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public int getCompanyID() {
                return companyID;
            }

            public void setCompanyID(int companyID) {
                this.companyID = companyID;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public int getIsAssignLocation() {
                return isAssignLocation;
            }

            public void setIsAssignLocation(int isAssignLocation) {
                this.isAssignLocation = isAssignLocation;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public int getTaskStatus() {
                return taskStatus;
            }

            public void setTaskStatus(int taskStatus) {
                this.taskStatus = taskStatus;
            }

            public String getTaskStatusDescription() {
                return taskStatusDescription;
            }

            public void setTaskStatusDescription(String taskStatusDescription) {
                this.taskStatusDescription = taskStatusDescription;
            }

            public String getProjectFiles() {
                return projectFiles;
            }

            public void setProjectFiles(String projectFiles) {
                this.projectFiles = projectFiles;
            }

            public int getTaskLogId() {
                return taskLogId;
            }

            public void setTaskLogId(int taskLogId) {
                this.taskLogId = taskLogId;
            }

            public Object getCancelReason() {
                return cancelReason;
            }

            public void setCancelReason(Object cancelReason) {
                this.cancelReason = cancelReason;
            }

            public Object getCancelTime() {
                return cancelTime;
            }

            public void setCancelTime(Object cancelTime) {
                this.cancelTime = cancelTime;
            }

            public int getIsFaker() {
                return isFaker;
            }

            public void setIsFaker(int isFaker) {
                this.isFaker = isFaker;
            }

            public Object getSalaryPayTime() {
                return salaryPayTime;
            }

            public void setSalaryPayTime(Object salaryPayTime) {
                this.salaryPayTime = salaryPayTime;
            }

            public int getExtendType() {
                return extendType;
            }

            public void setExtendType(int extendType) {
                this.extendType = extendType;
            }

            public String getExtendProperty1() {
                return extendProperty1;
            }

            public void setExtendProperty1(String extendProperty1) {
                this.extendProperty1 = extendProperty1;
            }

            public String getLeaveDetailAddress() {
                return leaveDetailAddress;
            }

            public void setLeaveDetailAddress(String leaveDetailAddress) {
                this.leaveDetailAddress = leaveDetailAddress;
            }

            public String getArriveDetailAddress() {
                return arriveDetailAddress;
            }

            public void setArriveDetailAddress(String arriveDetailAddress) {
                this.arriveDetailAddress = arriveDetailAddress;
            }

            public Object getLeaveAddressDistance() {
                return leaveAddressDistance;
            }

            public void setLeaveAddressDistance(Object leaveAddressDistance) {
                this.leaveAddressDistance = leaveAddressDistance;
            }

            public Object getArriveAddressDistance() {
                return arriveAddressDistance;
            }

            public void setArriveAddressDistance(Object arriveAddressDistance) {
                this.arriveAddressDistance = arriveAddressDistance;
            }
        }
    }
}
