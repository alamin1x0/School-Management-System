package com.example.shc_learing_app.contact;

public class ContactData {
    private String name,  contact, key; //email,

    public ContactData() {
    }

    public ContactData(String name,  String contact, String key) {
        this.name = name;
        //this.email = email;
        this.contact = contact;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
