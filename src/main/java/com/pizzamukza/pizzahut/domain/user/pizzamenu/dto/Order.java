package com.pizzamukza.pizzahut.domain.user.pizzamenu.dto;



public class Order {
  int orderId;
  int totalPrice;
  PizzaOrder pizzaOrder;
  SideOrder sideOrder;

  public Order(int orderId, PizzaOrder pizzaOrder, SideOrder sideOrder) {
    this.orderId = orderId;
    this.pizzaOrder = pizzaOrder;
    this.sideOrder = sideOrder;
  }

}
