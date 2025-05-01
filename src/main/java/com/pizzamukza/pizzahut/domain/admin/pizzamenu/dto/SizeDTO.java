package com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto;

public class SizeDTO {

    private int sizeId;
    private String sizeName;
    private int price;
    private int pizzaId;
    private int quantity;

    public SizeDTO() {
    }

    public SizeDTO(String sizeName, int price) {
        this.sizeName = sizeName;
        this.price = price;
    }

    public SizeDTO(int sizeId, String sizeName, int price, int pizzaId, int quantity) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.price = price;
        this.pizzaId = pizzaId;
        this.quantity = quantity;
    }

    public SizeDTO(String sizeName, int price, int quantity) {
        this.sizeName = sizeName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }
}
