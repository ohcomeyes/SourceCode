package com.boce.flcp.domain.model;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain.model
 * @Description: TODO(需求报表)
 * @date 2017/12/20 17:05
 */
public class DemandStatement {
    private String id;
    private String name;

    public DemandStatement() {
    }

    public DemandStatement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
