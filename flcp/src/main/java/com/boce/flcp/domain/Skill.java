package com.boce.flcp.domain;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.domain
 * @Description: TODO[技能]
 * @date 2017/12/11 13:49
 */
public class Skill {
    private String id;
    private boolean checked;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
