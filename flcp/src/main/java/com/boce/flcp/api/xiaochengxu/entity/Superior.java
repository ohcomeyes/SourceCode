package com.boce.flcp.api.xiaochengxu.entity;

/**
 * @program: flcp
 * @description: 设计大牛
 * @author: Mr.Tang
 * @create: 2018-05-07 16:02
 **/
public class Superior {
    private Long id;
    private String name;
    private String describe;
    private String portrait;

    public Superior(Long id, String name, String describe, String portrait) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.portrait = portrait;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
