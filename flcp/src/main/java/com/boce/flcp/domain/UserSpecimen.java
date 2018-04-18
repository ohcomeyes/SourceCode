package com.boce.flcp.domain;

import com.boce.flcp.data.Adept;
import com.boce.flcp.util.CommonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain
 * @Description: TODO(打板师)
 * @date 2017/12/7 16:09
 */
@Entity
@Table(name="user_specimen")
@ApiModel(value = "UserSpecimen",description = "打板师对象")
public class UserSpecimen {
    @ApiModelProperty(value = "id",position = 1)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ApiModelProperty(value = "用户",position=2)
    private String account;
    @ApiModelProperty(value = "密码",position=3)
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "昵称",position = 4)
    private String name;
    @ApiModelProperty(value = "地址",position = 5)
    private String address;
    @ApiModelProperty(value = "联系电话",position = 6)
    private String phone;
    @ApiModelProperty(value = "技能",position = 7)
    private String adept;
    @ApiModelProperty(value = "组织",position = 8)
    private Boolean organization;
    @ApiModelProperty(value = "创建时间",position = 9)
    private String time = CommonUtils.getDate("yyyy-MM-dd HH:mm:ss");

    @Transient
    @ApiModelProperty(value = "技能列表",position = 10)
    private List<Skill> skillList = new ArrayList<>();


    public UserSpecimen() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdept() {
        return adept;
    }

    public void setAdept(String adept) {

        this.adept = adept;
    }

    public Boolean getOrganization() {
        return organization;
    }

    public void setOrganization(Boolean organization) {
        this.organization = organization;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if(CommonUtils.isNotBlank(time)){
            this.time = time;
        }else{
            this.time = CommonUtils.getDate("yyyy-MM-dd HH:mm:ss");
        }
    }
    public List<Skill> getSkillList() {
        return skillList;
    }
    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
