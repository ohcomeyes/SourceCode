package com.boce.flcp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain
 * @Description: 设计
 * @date 2017/11/21 17:16
 */
@ApiModel(value = "design",description = "需求设计")
public class Design {
    @ApiModelProperty(value = "Id",position = 0)
    private String id ;
    @ApiModelProperty(value = "需求Id",position = 1)
    private Long demand_id;
    @ApiModelProperty(value = "用户Id",position = 2)
    private Long user_id;
    @ApiModelProperty(value = "设计缩略图name%url#name%url",position = 3)
    private String design_imgs;
    @ApiModelProperty(value = "设计描述",position = 4)
    private String design_describe;
    @ApiModelProperty(value = "附件(格式同上)",position = 5)
    private String design_accessorys;
    @ApiModelProperty(value = "完成时间",position = 6)
    private String design_time;

    @ApiModelProperty(value = "设计图列表File对象(file_name,file_url)",position = 7)
    private List<File> imgs;
    @ApiModelProperty(value = "附件列表File对象(file_name,file_url)",position = 8)
    private List<File> accessorys;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public List<File> getImgs() {
        return imgs;
    }

    public void setImgs(List<File> imgs) {
        this.imgs = imgs;
    }

    public List<File> getAccessorys() {
        return accessorys;
    }

    public void setAccessorys(List<File> accessorys) {
        this.accessorys = accessorys;
    }
}
