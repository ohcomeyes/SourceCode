package com.boce.flcp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "validate",description = "验证")
public class Validate extends Unify{
    @ApiModelProperty(value = "验证码")
    private String validate_id = "";

    public String getValidate_id() {
        return validate_id;
    }

    public void setValidate_id(String validate_id) {
        this.validate_id = validate_id;
    }
}
