package com.example.shc_learing_app.quizresult;

public class ScoreData {
    String name, image;
    long score;

    public ScoreData() {
    }

    public ScoreData(String name, String image, long score) {
        this.name = name;
        this.image = image;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
