package com.boce.flcp.api.xiaochengxu.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @program: flcp
 * @description: 小程序查询数据实体
 * @author: Mr.Tang
 * @create: 2018-05-07 11:39
 **/
public class WebDemand {
    private Long id;
    private String demand_employer;
    private String demand_name;
    private BigDecimal demand_money;
    private String demand_describe;
    private String end_date;

    public WebDemand(Long id, String demand_employer, String demand_name, BigDecimal demand_money,String demand_describe, String end_date) {
        this.id = id;
        this.demand_employer = demand_employer;
        this.demand_name = demand_name;
        this.demand_money = demand_money;
        this.demand_describe = demand_describe;
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemand_name() {
        return demand_name;
    }

    public void setDemand_name(String demand_name) {
        this.demand_name = demand_name;
    }

    public BigDecimal getDemand_money() {
        return demand_money;
    }

    public void setDemand_money(BigDecimal demand_money) {
        this.demand_money = demand_money;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDemand_employer() {
        return demand_employer;
    }

    public void setDemand_employer(String demand_employer) {
        this.demand_employer = demand_employer;
    }

    public String getDemand_describe() {
        return demand_describe;
    }

    public void setDemand_describe(String demand_describe) {
        this.demand_describe = demand_describe;
    }
}
