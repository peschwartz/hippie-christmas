package com.cs407.hippie_christmas;

public class Items {

    // every item should have a category, title/name, content(where its located, any more info)

    private String category;
    private String title;
    private String content;

    public Items(String category, String title, String content) {

        this.category = category;
        this.title = title;
        this.content = content;
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
}
