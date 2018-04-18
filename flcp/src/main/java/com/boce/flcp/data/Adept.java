package com.boce.flcp.data;

/**
 * @author xutang
 * @version V1.0
 * @Package com.boce.flcp.data
 * @Description: TODO(技能)
 * @date 2017/12/7 16:18
 */
public enum Adept {
    SCULPT("工业打板",1),STRUCTURE("版型设计",2),PROCESS("KT板",3);

    private String name;

    private int index;

    Adept(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (Adept c : Adept.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
