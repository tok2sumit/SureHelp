package com.tok2sumit.surehelp.HelperClasses.HomeAdapter;

public class FeaturedHelperClass {
    static int image;
    static String title,description;

    public FeaturedHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public static int getImage() {
        return image;
    }

//    public void setImage(int image) {
//        this.image = image;
//    }

    public static String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public static String getDescription() {
        return description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }
}
