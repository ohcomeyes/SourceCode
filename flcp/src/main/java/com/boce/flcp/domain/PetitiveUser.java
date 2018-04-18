package com.boce.flcp.domain;

public class PetitiveUser implements Cloneable{
//    private static PetitiveUser petitiveUser = new PetitiveUser();
    private Long demand_id;
    private Long user_id;
    private String user_phone;
    private String user_name;
    private int user_photo_size;

    public PetitiveUser() {
    }

    //对象简单new快 对象复杂clone快
//    public static PetitiveUser getPetitiveUser(){
//        try {
//            return (PetitiveUser) petitiveUser.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public Long getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(Long demand_id) {
        this.demand_id = demand_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_photo_size() {
        return user_photo_size;
    }

    public void setUser_photo_size(int user_photo_size) {
        this.user_photo_size = user_photo_size;
    }
}
