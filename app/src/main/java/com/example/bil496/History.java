package com.example.bil496;

public class History {
    private String amount;
    private int like;
    private String name;

    public History() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public History(int like, String amount, String name) {
        this.amount = amount;
        this.like = like;
        this.name = name;
    }
    public History(String name, String amount, int like) {
        this.amount = amount;
        this.like = like;
        this.name = name;
    }
}