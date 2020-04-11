package com.example.bil496;

public class ProductActivity {
    private int imageId;
    private String name;
    private double price;


    public ProductActivity(){}

    public ProductActivity(int imageId, String name, double price) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
