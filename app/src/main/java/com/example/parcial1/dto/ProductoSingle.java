package com.example.parcial1.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductoSingle implements Serializable {

    @SerializedName("id")
    public Integer id;

    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("price")
    public double price;
    @SerializedName("discountPercentage")
    public double discountPercentage;
    @SerializedName("rating")
    public double rating;
    @SerializedName("stock")
    public Integer stock;
    @SerializedName("brand")
    public String brand;
    @SerializedName("category")
    public String category;
    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("images")
    public ArrayList<String> images = new ArrayList<>();;

    public ProductoSingle (Integer id, String title, String description, double price, double discountPercentage, double rating,
                    Integer stock, String brand, String category, String thumbnail, ArrayList<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @SerializedName("data")
    public Producto data;

    public ProductoSingle(Producto data) {
        this.data = data;
    }

    public Producto getData() {
        return data;
    }


    @Override
    public String toString() {
        return "ProductoSingle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
