package com.boce.flcp.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain.model
 * @Description: TODO(需求分析模型)
 * @date 2017/11/28 16:20
 */
public class DemandAnalyze {
    private int demand_count;//需求量
    private int demand_pv; //需求访问量
    private int demand_petitive_count;//需求竞标量
    private Date date;//时间
    private BigDecimal demand_money;//需求总额



    public DemandAnalyze(int demand_count,BigDecimal demand_money){
        super();
        this.demand_count = demand_count;
        this.demand_money = demand_money;
    }

    public int getDemand_count() {
        return demand_count;
    }

    public void setDemand_count(int demand_count) {
        this.demand_count = demand_count;
    }

    public int getDemand_pv() {
        return demand_pv;
    }

    public void setDemand_pv(int demand_pv) {
        this.demand_pv = demand_pv;
    }

    public int getDemand_petitive_count() {
        return demand_petitive_count;
    }

    public void setDemand_petitive_count(int demand_petitive_count) {
        this.demand_petitive_count = demand_petitive_count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getDemand_money() {
        return demand_money;
    }

    public void setDemand_money(BigDecimal demand_money) {
        this.demand_money = demand_money;
    }
}
