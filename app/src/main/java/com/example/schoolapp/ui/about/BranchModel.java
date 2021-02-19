package com.example.schoolapp.ui.about;

public class BranchModel extends BanchAdapter {

    private int img;
    private String title, description;

    public BranchModel(int img, String title, String description) {
        this.img = img;
        this.title = title;
        this.description = description;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
