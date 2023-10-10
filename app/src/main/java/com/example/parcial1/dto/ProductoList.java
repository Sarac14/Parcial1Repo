package com.example.parcial1.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductoList implements Serializable {
    @SerializedName("products")
    public List<Producto> products = new ArrayList<>();

    public ProductoList(List<Producto> products) {
        this.products = products;
    }

    public List<Producto> getProducts() {
        return products;
    }
}
