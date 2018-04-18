package com.boce.flcp.domain.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain.list
 * @Description: TODO
 * @date 2017/12/6 15:27
 */
@ApiModel(value = "SpecimenList",description = "打板列表")
public class SpecimenList {
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
    @ApiModelProperty(value = "需求类型#vi:VI设计,logo:LOGO设计",position = 5,example = "vi:VI设计,logo:LOGO设计",allowableValues="vi,logo")
    private String demand_type;
    @ApiModelProperty(value = "需求金额",position = 6)
    private BigDecimal demand_money;
    @ApiModelProperty(value = "需求描述",position = 7)
    private String demand_describe = "";
    @ApiModelProperty(value = "任务类型#1:招标任务,2:悬赏任务",position = 8,example = "1:招标任务,2:悬赏任务",allowableValues="range[1,2]")
    private String classify_type;
    @ApiModelProperty(value = "中标人",position = 9)
    private Long demand_bid_winner;
    @ApiModelProperty(value = "中标人昵称",position = 10)
    private String demand_bid_winner_name;
    @ApiModelProperty(value = "需求状态#making:待打板,send:待送样,using:待启用",position = 11)
    private String demand_status;
    @ApiModelProperty(value = "需求创建时间",position = 12)
    private String demand_time = "";
    @ApiModelProperty(value = "设计完成时间",position = 13)
    private String design_time = "";
    @ApiModelProperty(value = "打板送样#true:是,false:否",position = 14)
    private boolean demand_making_send;

    public SpecimenList() {
        super();
    }

    public SpecimenList(Long id, String demand_id, String demand_name, String demand_employer,String phone, String demand_type, BigDecimal demand_money, String demand_describe, String classify_type, Long demand_bid_winner, String demand_bid_winner_name, String demand_status, String demand_time,String design_time, boolean demand_making_send) {
        super();
        this.id = id;
        this.demand_id = demand_id;
        this.demand_name = demand_name;
        this.demand_employer = demand_employer;
        this.phone = phone;
        this.demand_type = demand_type;
        this.demand_money = demand_money;
        this.demand_describe = demand_describe;
        this.classify_type = classify_type;
        this.demand_bid_winner = demand_bid_winner;
        this.demand_bid_winner_name = demand_bid_winner_name;
        this.demand_status = demand_status;
        this.demand_time = demand_time;
        this.design_time = design_time;
        this.demand_making_send = demand_making_send;
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

    public String getClassify_type() {
        return classify_type;
    }

    public void setClassify_type(String classify_type) {
        this.classify_type = classify_type;
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

    public String getDesign_time() {
        return design_time;
    }

    public void setDesign_time(String design_time) {
        this.design_time = design_time;
    }

    public boolean isDemand_making_send() {
        return demand_making_send;
    }

    public void setDemand_making_send(boolean demand_making_send) {
        this.demand_making_send = demand_making_send;
    }
}
