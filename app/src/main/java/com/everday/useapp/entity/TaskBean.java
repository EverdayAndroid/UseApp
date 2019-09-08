package com.everday.useapp.entity;

import java.io.Serializable;

/**
 * @author Everday
 * @emil wangtaohandsome@gmail.com
 * create at 2019/8/31
 * description: 待接单
 */
public class TaskBean implements Serializable {
    private Integer id;
    //派单商户标识
    private Integer shld;
    //派单商户名称
    private String shmc;
    //任务编号
    private String taskBh;
    //	任务名称
    private String taskName;
    //预计费用
    private Double yjfy;
    //任务开始时间
    private String startTime;
    //任务结束时间
    private String endTime;
    //任务时长
    private Integer duration;
    //任务地址
    private String address;
    //任务描述
    private String des;
    //创建时间
    private String createTime;
    //是否可用
    private Integer enable;
    //状态(1待接单2已接单)
    private Integer state;

    public Integer getId() {
        if(id == null){
            id = 0;
        }
        return id;
    }

    public Integer getShld() {
        if(shld == null){
            shld = 0;
        }
        return shld;
    }

    public String getShmc() {
        return shmc == null ? "":shmc;
    }

    public String getTaskBh() {
        return taskBh == null ? "" : taskBh;
    }

    public String getTaskName() {
        return taskName == null ? "" : taskName;
    }

    public Double getYjfy() {
        if(yjfy == null){
            yjfy = 0.0;
        }
        return yjfy;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public String getDes() {
        return des == null ? "" : des;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public Integer getEnable() {
        if(enable == null){
            enable = 0;
        }
        return enable;
    }

    public Integer getState() {
        if(state == null){
            state = 0;
        }
        return state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShld(Integer shld) {
        this.shld = shld;
    }

    public void setShmc(String shmc) {
        this.shmc = shmc;
    }

    public void setTaskBh(String taskBh) {
        this.taskBh = taskBh;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setYjfy(Double yjfy) {
        this.yjfy = yjfy;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
