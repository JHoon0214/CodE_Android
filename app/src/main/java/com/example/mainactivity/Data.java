package com.example.mainactivity;

public class Data {
    public String title;
    public String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    Data(String title, String category) {
        this.title = title;
        this.category = category;
    }
}
