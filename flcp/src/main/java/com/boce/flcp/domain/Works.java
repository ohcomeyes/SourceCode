package com.boce.flcp.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @program: flcp
 * @description: 作品
 * @author: Mr.Tang
 * @create: 2018-05-08 10:20
 **/
@Entity
@Table(name="works")
public class Works {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String source;
    private String type;
    private String url;
    private String used;
    private String who;
    private String createdAt;

    public Works() {
    }

    public Works(Long id, String source, String type, String url, String used, String who, String createdAt) {
        this.id = id;
        this.source = source;
        this.type = type;
        this.url = url;
        this.used = used;
        this.who = who;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
