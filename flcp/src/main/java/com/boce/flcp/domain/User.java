package com.boce.flcp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "user",description = "用户对象")
@Entity
@Table(name="user_design")
@NamedQuery(name = "User.withAccountQuery",
        query = "select u from User u where u.account=?1")
public class User {
    @ApiModelProperty(value = "id",position=1)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Transient
    private Long rest_id;//资源Id

    @ApiModelProperty(value = "用户",position=2)
    private String account;
    @ApiModelProperty(value = "密码",position=3)
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "昵称",position=4)
    private String name;
    @ApiModelProperty(value = "性别#true:男,false:女",position=5)
    private boolean sex;
    @JsonIgnore
    @ApiModelProperty(value = "设计行业#1:造型设计,2:结构设计,3：工艺设计",position=6,allowableValues="range[1,3]")
    private String design_industry;
    @ApiModelProperty(value = "设计年限",position=7,allowableValues="range[1,100]")
    private int design_age;
    @ApiModelProperty(value = "用户注册时间",position=8)
    private String time;
    @ApiModelProperty(value = "用户头像",position=9)
    private String portrait;
    @ApiModelProperty(value = "用户描述",position=10)
    private String describe;

    @Transient
    @ApiModelProperty(value = "设计行业#1:造型设计,2:结构设计,3：工艺设计",position=6,allowableValues="range[1,3]")
    private List<String> industrys = new ArrayList<>();

    public User() {
        super();
    }

    public User(String account, String password, String name, boolean sex, String design_industry, int design_age) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.design_industry = design_industry;
        this.design_age = design_age;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesign_industry() {
        return design_industry;
    }

    public void setDesign_industry(String design_industry) {
        this.design_industry = design_industry;
    }

    public int getDesign_age() {
        return design_age;
    }

    public void setDesign_age(int design_age) {
        this.design_age = design_age;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getIndustrys() {
        return industrys;
    }

    public void setIndustrys(List<String> industrys) {
        this.industrys = industrys;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
