package com.cs407.hippie_christmas;

public class Items {

    // every item should have a category, title/name, content(where its located, any more info)

    private String category;
    private String title;
    private String content;
    private String location;


    public Items(String category, String title, String content, String location) {

        this.category = category;
        this.title = title;
        this.content = content;
        this.location = location;
    }

    public String getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getLocation() { return location; }
}
