package com.example.app;

public class Reward {
    private String name;
    private String description;

    private int requiredPoints;
    public Reward(String name, String description, int requiredPoints, int rewardPoint) {
        this.name = name;
        this.description = description;
        this.requiredPoints = requiredPoints;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredPoints(){
        return requiredPoints;
    }
}

