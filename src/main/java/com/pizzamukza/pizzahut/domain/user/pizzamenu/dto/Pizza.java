package com.pizzamukza.pizzahut.domain.user.pizzamenu.dto;

public class Pizza {
  int id;
  String pizzaName; // 페퍼로니피자
  String size; // 미디움, 라지
  public int price;

  public Pizza(){}
  @Override
  public String toString() {
    return id + ". " + pizzaName + " " + size + " " + price + "원 " + quantity + "개";
  }


  int quantity; // 가격

  public Pizza(int sizeId, String pizzaName, String sizeName, int price, int quantity) {
    this.id = sizeId;
    this.pizzaName = pizzaName;
    this.size = sizeName;
    this.price = price;
    this.quantity = quantity;
  }

}
