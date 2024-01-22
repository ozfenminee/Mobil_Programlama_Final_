package com.example.mobil_programlama_final.Model;

public class GalleryCardModel {
    String label;
    String description;
    String img;
    String username;

    public GalleryCardModel(String label, String description, String img, String username) {
        this.label = label;
        this.description = description;
        this.img = img;
        this.username = username;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
