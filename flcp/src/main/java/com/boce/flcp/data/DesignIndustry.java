package com.boce.flcp.data;

public enum DesignIndustry {
    SCULPT("造型设计",1),STRUCTURE("结构设计",2),PROCESS("工艺设计",3);

    private String name;

    private int index;

    DesignIndustry(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (DesignIndustry c : DesignIndustry.values()) {
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
