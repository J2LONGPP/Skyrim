package com.coder.mybatis.entity;

public class Sale {

    private Integer year_id;
    private Integer month_id;
    private Integer day_id;
    private Double sales_value;

    public Integer getYear_id() {
        return year_id;
    }

    public void setYear_id(Integer year_id) {
        this.year_id = year_id;
    }

    public Integer getMonth_id() {
        return month_id;
    }

    public void setMonth_id(Integer month_id) {
        this.month_id = month_id;
    }

    public Integer getDay_id() {
        return day_id;
    }

    public void setDay_id(Integer day_id) {
        this.day_id = day_id;
    }

    public Double getSales_value() {
        return sales_value;
    }

    public void setSales_value(Double sales_value) {
        this.sales_value = sales_value;
    }
}
