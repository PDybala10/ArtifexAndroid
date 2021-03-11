package com.ndesigne.artifex2.model.entities;

public class Image extends BasePayload {
    String owner, image;
    int voitureId, photoId;

    public Image(int voitureId, int photoId, String owner, String image, String key) {
        super(key);
        this.owner = owner;
        this.image = image;
        this.voitureId = voitureId;
        this.photoId = photoId;
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

    public int getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(int voitureId) {
        this.voitureId = voitureId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
