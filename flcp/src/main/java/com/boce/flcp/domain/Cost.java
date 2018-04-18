package com.boce.flcp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @program: flcp
 * @description: 规则
 * @author: Mr.Tang
 * @create: 2018-03-28 14:36
 **/
@ApiModel(value = "cost",description = "规则")
@Entity
@Table(name="cost")
public class Cost {
    @ApiModelProperty(value = "Id",position = 0)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(value = "规则名",position = 1)
    private String cost_name;
    @ApiModelProperty(value = "规则类型",position = 2)
    private String cost_type;
    @ApiModelProperty(value = "规则方式",position = 3)
    private String cost_method;
    @ApiModelProperty(value = "设定值",position = 4)
    private String cost_value;
    @ApiModelProperty(value = "规则描述",position = 5)
    private String cost_describe;
    @ApiModelProperty(value = "规则状态",position = 6)
    private boolean cost_status;

    public Cost() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCost_name() {
        return cost_name;
    }

    public void setCost_name(String cost_name) {
        this.cost_name = cost_name;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }

    public String getCost_method() {
        return cost_method;
    }

    public void setCost_method(String cost_method) {
        this.cost_method = cost_method;
    }

    public String getCost_value() {
        return cost_value;
    }

    public void setCost_value(String cost_value) {
        this.cost_value = cost_value;
    }

    public String getCost_describe() {
        return cost_describe;
    }

    public void setCost_describe(String cost_describe) {
        this.cost_describe = cost_describe;
    }

    public boolean isCost_status() {
        return cost_status;
    }

    public void setCost_status(boolean cost_status) {
        this.cost_status = cost_status;
    }
}
