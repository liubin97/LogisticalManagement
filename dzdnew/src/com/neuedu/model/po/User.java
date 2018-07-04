package com.neuedu.model.po;

public class User {

    private int user_id;
    private String name;
    private String id_card_num;//省份证号
    private String organization;//单位
    private String landline_tel; //座机号
    private String tel;
    private String address;
    private String zip_code;//邮编
    private String email;
    private int status;//客户状态：1-正常用户 2-已删除用户


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card_num() {
        return id_card_num;
    }

    public void setId_card_num(String id_card_num) {
        this.id_card_num = id_card_num;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLandline_tel() {
        return landline_tel;
    }

    public void setLandline_tel(String landline_tel) {
        this.landline_tel = landline_tel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", id_card_num='" + id_card_num + '\'' +
                ", organization='" + organization + '\'' +
                ", landline_tel='" + landline_tel + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
