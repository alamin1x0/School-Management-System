package com.example.shc_learing_app.studentlist;

public class StudentListData {
    private String name, phone, address,  image, key;

    public StudentListData() {
    }

    public StudentListData(String name, String phone, String address, String image, String key) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.image = image;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
