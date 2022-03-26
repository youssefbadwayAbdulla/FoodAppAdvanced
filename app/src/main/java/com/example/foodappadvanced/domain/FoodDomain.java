package com.example.foodappadvanced.domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title,pic,description;

    public FoodDomain(String title, String pic, String description, int price, int numberInCard) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.numberInCard = numberInCard;
    }

    private int price;
    private int numberInCard;

    public FoodDomain(String title, String pic, String description, int price) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }
}
