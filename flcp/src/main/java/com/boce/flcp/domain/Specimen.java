package com.boce.flcp.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

import java.util.List;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain
 * @Description: TODO（打板）
 * @date 2017/12/13 11:20
 */
public class Specimen {
    @ApiModelProperty(value = "Id",position = 1)
    private String id;

    @ApiModelProperty(value = "需求Id",position = 2)
    private Long demand_id;

    @ApiModelProperty(value = "产品设计id",position = 3)
    private String design_id;

    @ApiModelProperty(value = "打板师id",position = 4)
    private Long specimen_user_id;

    @ApiModelProperty(value = "打板备注",position = 5)
    private String specimen_describe;

    @ApiModelProperty(value = "打板图片",position = 6)
    private String specimen_imgs;

    @ApiModelProperty(value = "打板时间",position = 7)
    private String specimen_time;

    @ApiModelProperty(value = "打板确认",position = 8)
    private boolean specimen_is_affirm;

    @ApiModelProperty(value = "设计图列表File对象(file_name,file_url)",position = 9)
    private List<File> imgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(Long demand_id) {
        this.demand_id = demand_id;
    }

    public String getDesign_id() {
        return design_id;
    }

    public void setDesign_id(String design_id) {
        this.design_id = design_id;
    }

    public Long getSpecimen_user_id() {
        return specimen_user_id;
    }

    public void setSpecimen_user_id(Long specimen_user_id) {
        this.specimen_user_id = specimen_user_id;
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

    public boolean isSpecimen_is_affirm() {
        return specimen_is_affirm;
    }

    public void setSpecimen_is_affirm(boolean specimen_is_affirm) {
        this.specimen_is_affirm = specimen_is_affirm;
    }

    public List<File> getImgs() {
        return imgs;
    }

    public void setImgs(List<File> imgs) {
        this.imgs = imgs;
    }
}
