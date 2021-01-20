package com.ndesigne.artifex2.model.entities;

public abstract class BasePayload {
    String key;

    public BasePayload(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
