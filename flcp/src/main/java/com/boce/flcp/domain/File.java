package com.boce.flcp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain
 * @Description: TODO(文件)
 * @date 2017/11/24 11:10
 */
@ApiModel(value = "file",description = "文件")
public class File {
    @ApiModelProperty(value = "文件名称",position = 0)
    private String file_name;
    @ApiModelProperty(value = "文件地址",position = 1)
    private String file_url;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
