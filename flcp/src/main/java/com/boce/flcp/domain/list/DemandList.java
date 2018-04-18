package com.boce.flcp.domain.list;

import com.boce.flcp.domain.Demand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain.list
 * @Description: TODO(需求列表)
 * @date 2017/12/6 15:26
 */
@ApiModel(value = "DemandList",description = "需求列表")
public  class DemandList{
    @ApiModelProperty(value = "id",position = 1)
    private Long id;
    @ApiModelProperty(value = "需求id",position = 2)
    private String demand_id;
    @ApiModelProperty(value = "需求内容",position = 3)
    private String demand_name;
    @ApiModelProperty(value = "雇主",position = 4)
    private String demand_employer;
    @ApiModelProperty(value = "联系电话",position = 15)
    private String phone;
    @ApiModelProperty(value = "行业")
    private String demand_trade;
    @ApiModelProperty(value = "需求类型",position = 5)
    private String demand_type;
    @ApiModelProperty(value = "需求金额",position = 6)
    private BigDecimal demand_money;
    @ApiModelProperty(value = "需求描述",position = 7)
    private String demand_describe = "";
    @ApiModelProperty(value = "开始时间",position = 9)
    private String start_date;
    @ApiModelProperty(value = "截至时间",position = 10)
    private String end_date;
    @ApiModelProperty(value = "需求状态#issue:待招标",position = 8)
    private String demand_status;
    @ApiModelProperty(value = "需求创建时间",position = 12)
    private String demand_time = "";
    @ApiModelProperty(value = "打板送样#true:是,false:否",position = 13)
    private boolean demand_making_send;
    @ApiModelProperty(value = "竞标人数",position = 14)
    private int demand_petitive_user;

    public DemandList() {
        super();
    }

    public DemandList(Long id, String demand_id, String demand_name, String demand_employer,String phone, String demand_trade,String demand_type, BigDecimal demand_money, String demand_describe, String start_date, String end_date, String demand_status, boolean demand_making_send, String demand_time,int demand_petitive_user) {
        super();
        this.id = id;
        this.demand_id = demand_id;
        this.demand_name = demand_name;
        this.demand_employer = demand_employer;
        this.phone = phone;
        this.demand_trade = demand_trade;
        this.demand_type = demand_type;
        this.demand_money = demand_money;
        this.demand_describe = demand_describe;
        this.start_date = start_date;
        this.end_date = end_date;
        this.demand_status = demand_status;
        this.demand_making_send = demand_making_send;
        this.demand_time = demand_time;
        this.demand_petitive_user = demand_petitive_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(String demand_id) {
        this.demand_id = demand_id;
    }

    public String getDemand_name() {
        return demand_name;
    }

    public void setDemand_name(String demand_name) {
        this.demand_name = demand_name;
    }

    public String getDemand_employer() {
        return demand_employer;
    }

    public void setDemand_employer(String demand_employer) {
        this.demand_employer = demand_employer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDemand_type() {
        return demand_type;
    }

    public void setDemand_type(String demand_type) {
        this.demand_type = demand_type;
    }

    public BigDecimal getDemand_money() {
        return demand_money;
    }

    public void setDemand_money(BigDecimal demand_money) {
        this.demand_money = demand_money;
    }

    public String getDemand_describe() {
        return demand_describe;
    }

    public void setDemand_describe(String demand_describe) {
        this.demand_describe = demand_describe;
    }

    public String getDemand_trade() {
        return demand_trade;
    }

    public void setDemand_trade(String demand_trade) {
        this.demand_trade = demand_trade;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDemand_status() {
        return demand_status;
    }

    public void setDemand_status(String demand_status) {
        this.demand_status = demand_status;
    }

    public String getDemand_time() {
        return demand_time;
    }

    public void setDemand_time(String demand_time) {
        this.demand_time = demand_time;
    }

    public boolean isDemand_making_send() {
        return demand_making_send;
    }

    public void setDemand_making_send(boolean demand_making_send) {
        this.demand_making_send = demand_making_send;
    }

    public int getDemand_petitive_user() {
        return demand_petitive_user;
    }

    public void setDemand_petitive_user(int demand_petitive_user) {
        this.demand_petitive_user = demand_petitive_user;
    }
}
