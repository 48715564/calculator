package com.cn.domain.entity;

import com.cn.common.persistence.BaseEntity;

public class SysWorkDay extends BaseEntity {
    private String date;

    private Boolean isWorkDay;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Boolean getIsWorkDay() {
        return isWorkDay;
    }

    public void setIsWorkDay(Boolean isWorkDay) {
        this.isWorkDay = isWorkDay;
    }
}