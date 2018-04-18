package com.boce.flcp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "unify",description = "返回")
public class Unify {
    @ApiModelProperty(value = "返回码",example="200")
    private String code;
    @ApiModelProperty(value = "返回信息",example = "成功")
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
