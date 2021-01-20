package com.ndesigne.artifex2.model.entities;

public class Image extends BasePayload {
    String owner, image;

    public Image(String owner, String image, String key) {
        super(key);
        this.owner = owner;
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
