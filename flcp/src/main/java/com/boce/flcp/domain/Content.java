package com.boce.flcp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "content",description = "带内容的返回")
public class Content extends Unify{
    @ApiModelProperty(value = "返回内容(注:如需要)")
    private Object object;

    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
}
