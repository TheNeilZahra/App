package com.example.app;

public class Coupon {
    private String name;
    private String description;

    public Coupon(String name, String description, int resourceId) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

