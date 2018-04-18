package com.boce.flcp.domain;

import com.boce.flcp.web.DataController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Entity
@Table(name="demand")
@ApiModel(value = "demand",description = "需求对象")
public class Demand{
    @ApiModelProperty(value = "id",position = 1)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ApiModelProperty(value = "资源id",position = 21)
    @Transient
    private Long rest_id;

    @ApiModelProperty(value = "是否已竞标",position = 20)
    @Transient
    private boolean is_petitive = false;

    @JsonIgnore
    @ApiModelProperty(value = "需求id",position = 2)
    private String demand_id;

    @ApiModelProperty(value = "需求内容",position = 3)
    private String demand_name;

    @ApiModelProperty(value = "雇主",position = 4)
    private String demand_employer;

    @ApiModelProperty(value = "行业")
    private String demand_trade ;

    @ApiModelProperty(value = "需求类型",position = 5)
    private String demand_type;

    @ApiModelProperty(value = "任务类型#1:招标任务,2:悬赏任务",position = 12,example = "1:招标任务,2:悬赏任务",allowableValues="range[1,2]")
    private String classify_type;

    @ApiModelProperty(value = "设计金额",position = 6)
    private BigDecimal demand_money;

    @ApiModelProperty(value = "参考图",position = 7)
    private String demand_imgs = "";

    @ApiModelProperty(value = "需求描述",position = 8)
    private String demand_describe = "";

    @ApiModelProperty(value = "需求附件",position = 9)
    private String demand_accessory = "";

    @ApiModelProperty(value = "开始时间",position = 10)
    private String start_date;

    @ApiModelProperty(value = "截至时间",position = 11)
    private String end_date;

    @ApiModelProperty(value = "联系电话",position = 13)
    private String phone;

    @ApiModelProperty(value = "竞标人数",position = 14)
    private int demand_petitive_user;

    @ApiModelProperty(value = "中标人Id",position = 14)
    private Long demand_bid_winner;

    @ApiModelProperty(value = "中标人",position = 24)
    private String demand_bid_winner_name;

    @ApiModelProperty(value = "中标时间",position = 25)
    private String demand_bid_winner_time;

    @ApiModelProperty(value = "需求状态#issue:待招标,bid:待设计,audit:待审核,approve:已通过,reject:已驳回",position = 15)
    private String demand_status;

    @ApiModelProperty(value = "打板送样#true:是,false:否",position = 16)
    private boolean demand_making_send;

    @ApiModelProperty(value = "打板送样金额")
    private BigDecimal demand_specimen_money;

    @ApiModelProperty(value = "是否提成")
    private boolean demand_cost;

    @ApiModelProperty(value = "提成规则")
    private Long demand_cost_id;

    @ApiModelProperty(value = "需求创建时间",position = 21)
    private String demand_time = "";

    @ApiModelProperty(value = "设计id",position = 23)
    private String design_id ="";

    @ApiModelProperty(value = "设计缩略图",position = 17)
    private String design_imgs ="";

    @ApiModelProperty(value = "设计描述",position = 18)
    private String design_describe ="";

    @ApiModelProperty(value = "设计附件",position = 19)
    private String design_accessorys ="";

    @ApiModelProperty(value = "设计时间",position = 20)
    private String design_time = "";

    @ApiModelProperty(value = "审核设计通过时间",position = 28)
    private String design_audit_time = "";

    @ApiModelProperty(value = "审核意见",position = 29)
    private String design_audit_msg = "";

    @ApiModelProperty(value = "打板id",position = 23)
    private String specimen_id;

    @ApiModelProperty(value = "打板师id",position = 24)
    private Long specimen_user_id;

    @ApiModelProperty(value = "打板方",position = 25)
    private String specimen_user_name;

    @ApiModelProperty(value = "打板备注",position = 31)
    private String specimen_describe;

    @ApiModelProperty(value = "打板图片",position = 32)
    private String specimen_imgs;

    @ApiModelProperty(value = "打板时间",position = 30)
    private String specimen_time;

    @ApiModelProperty(value = "审核打板通过时间",position = 33)
    private String specimen_audit_time = "";

    @ApiModelProperty(value = "审核意见",position = 34)
    private String specimen_audit_msg = "";

    @ApiModelProperty(value = "设计明细",position = 22)
    @Transient
    private List<Design> designs = new ArrayList<>();

    @ApiModelProperty(value = "打板明细",position = 28)
    @Transient
    private List<Specimen> specimens = new ArrayList<>();

    public Demand() {
        super();
    }

    //top
    public Demand(String demand_name, String demand_describe) {
        this.demand_name = demand_name;
        this.demand_describe = demand_describe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRest_id() {
        return rest_id;
    }

    public void setRest_id(Long rest_id) {
        this.rest_id = rest_id;
    }

    public boolean isIs_petitive() {
        return is_petitive;
    }

    public void setIs_petitive(boolean is_petitive) {
        this.is_petitive = is_petitive;
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

    public String getDemand_imgs() {
        return demand_imgs;
    }

    public void setDemand_imgs(String demand_imgs) {
        this.demand_imgs = demand_imgs;
    }

    public String getDemand_describe() {
        return demand_describe;
    }

    public void setDemand_describe(String demand_describe) {
        this.demand_describe = demand_describe;
    }

    public String getDemand_accessory() {
        return demand_accessory;
    }

    public void setDemand_accessory(String demand_accessory) {
        this.demand_accessory = demand_accessory;
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

    public String getClassify_type() {
        return classify_type;
    }

    public void setClassify_type(String classify_type) {
        this.classify_type = classify_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDemand_petitive_user() {
        return demand_petitive_user;
    }

    public void setDemand_petitive_user(int demand_petitive_user) {
        this.demand_petitive_user = demand_petitive_user;
    }

    public Long getDemand_bid_winner() {
        return demand_bid_winner;
    }

    public void setDemand_bid_winner(Long demand_bid_winner) {
        this.demand_bid_winner = demand_bid_winner;
    }

    public String getDemand_bid_winner_name() {
        return demand_bid_winner_name;
    }

    public void setDemand_bid_winner_name(String demand_bid_winner_name) {
        this.demand_bid_winner_name = demand_bid_winner_name;
    }

    public String getDemand_bid_winner_time() {
        return demand_bid_winner_time;
    }

    public void setDemand_bid_winner_time(String demand_bid_winner_time) {
        this.demand_bid_winner_time = demand_bid_winner_time;
    }

    public String getDemand_status() {
        return demand_status;
    }

    public void setDemand_status(String demand_status) {
        this.demand_status = demand_status;
    }

    public boolean isDemand_making_send() {
        return demand_making_send;
    }

    public void setDemand_making_send(boolean demand_making_send) {
        this.demand_making_send = demand_making_send;
    }

    public String getDesign_imgs() {
        return design_imgs;
    }

    public void setDesign_imgs(String design_imgs) {
        this.design_imgs = design_imgs;
    }

    public String getDesign_describe() {
        return design_describe;
    }

    public void setDesign_describe(String design_describe) {
        this.design_describe = design_describe;
    }

    public String getDesign_accessorys() {
        return design_accessorys;
    }

    public void setDesign_accessorys(String design_accessorys) {
        this.design_accessorys = design_accessorys;
    }

    public String getDesign_time() {
        return design_time;
    }

    public void setDesign_time(String design_time) {
        this.design_time = design_time;
    }

    public String getDemand_time() {
        return demand_time;
    }

    public void setDemand_time(String demand_time) {
        this.demand_time = demand_time;
    }

    public List<Design> getDesigns() {
        return designs;
    }

    public void setDesigns(List<Design> designs) {
        this.designs = designs;
    }

    public List<Specimen> getSpecimens() {
        return specimens;
    }

    public void setSpecimens(List<Specimen> specimens) {
        this.specimens = specimens;
    }

    public String getDesign_id() {
        return design_id;
    }

    public void setDesign_id(String design_id) {
        this.design_id = design_id;
    }

    public String getSpecimen_id() {
        return specimen_id;
    }

    public void setSpecimen_id(String specimen_id) {
        this.specimen_id = specimen_id;
    }

    public Long getSpecimen_user_id() {
        return specimen_user_id;
    }

    public void setSpecimen_user_id(Long specimen_user_id) {
        this.specimen_user_id = specimen_user_id;
    }

    public String getSpecimen_user_name() {
        return specimen_user_name;
    }

    public void setSpecimen_user_name(String specimen_user_name) {
        this.specimen_user_name = specimen_user_name;
    }

    public String getSpecimen_describe() {
        return specimen_describe;
    }

    public void setSpecimen_describe(String specimen_describe) {
        this.specimen_describe = specimen_describe;
    }

    public String getSpecimen_imgs() {
        return specimen_imgs;
    }

    public void setSpecimen_imgs(String specimen_imgs) {
        this.specimen_imgs = specimen_imgs;
    }

    public String getSpecimen_time() {
        return specimen_time;
    }

    public void setSpecimen_time(String specimen_time) {
        this.specimen_time = specimen_time;
    }

    public String getDesign_audit_time() {
        return design_audit_time;
    }

    public void setDesign_audit_time(String design_audit_time) {
        this.design_audit_time = design_audit_time;
    }

    public String getDesign_audit_msg() {
        return design_audit_msg;
    }

    public void setDesign_audit_msg(String design_audit_msg) {
        this.design_audit_msg = design_audit_msg;
    }

    public String getSpecimen_audit_time() {
        return specimen_audit_time;
    }

    public void setSpecimen_audit_time(String specimen_audit_time) {
        this.specimen_audit_time = specimen_audit_time;
    }

    public String getSpecimen_audit_msg() {
        return specimen_audit_msg;
    }

    public void setSpecimen_audit_msg(String specimen_audit_msg) {
        this.specimen_audit_msg = specimen_audit_msg;
    }

    public String getDemand_trade() {
        return demand_trade;
    }

    public void setDemand_trade(String demand_trade) {
        this.demand_trade = demand_trade;
    }

    public BigDecimal getDemand_specimen_money() {
        return demand_specimen_money;
    }

    public void setDemand_specimen_money(BigDecimal demand_specimen_money) {
        this.demand_specimen_money = demand_specimen_money;
    }

    public boolean isDemand_cost() {
        return demand_cost;
    }

    public void setDemand_cost(boolean demand_cost) {
        this.demand_cost = demand_cost;
    }

    public Long getDemand_cost_id() {
        return demand_cost_id;
    }

    public void setDemand_cost_id(Long demand_cost_id) {
        this.demand_cost_id = demand_cost_id;
    }
}
