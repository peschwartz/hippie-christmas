package com.cs407.hippie_christmas;

public class Items {

    // every item should have a category, title/name, content(where its located, any more info)

    private String category;
    private String title;
    private String location;
    private String image_uri;

    public Items(String category, String title, String location, String image_uri) {

        this.category = category;
        this.title = title;
        this.location = location;
        this.image_uri = image_uri;
    }

    public String getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public String getLocation() {
        return location;
    }
    public String getImageUri() {
        return image_uri;
    }
}
