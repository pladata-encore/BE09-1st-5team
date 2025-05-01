package com.pizzamukza.pizzahut.domain.user.pizzamenu.dto;

import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaDTO;

// 이름, 가격, 수량, 사이즈 - DTO
// 피자 주문 정보(도우 포함)
public class PizzaOrder {
  int pizzaOrderId;
  int orderId;
  PizzaDTO pizzaDTO; // 정호님(주문할 피자 정보-페퍼로니피자 라지-30000원)
  DoughDTO doughDTO; // 치즈크러스트 (5000원)
  int quantity; // 2개
  int totalPrice; // 35000*2 = 700000원

  @Override
  public String toString() {
    return "PizzaOrder{" +
        "pizzaOrderId=" + pizzaOrderId +
        ", pizzaDTO=" + pizzaDTO +
        ", doughDTO=" + doughDTO +
        ", quantity=" + quantity +
        ", totalPrice=" + totalPrice +
        '}';
  }

  public PizzaOrder(int pizzaOrderId, PizzaDTO pizzaDTO, DoughDTO doughDTO, int quantity,
      int totalPrice) {
    this.pizzaOrderId = pizzaOrderId;
    this.pizzaDTO = pizzaDTO;
    this.doughDTO = doughDTO;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }

  public int getPizzaOrderId() {
    return pizzaOrderId;
  }

  public void setPizzaOrderId(int pizzaOrderId) {
    this.pizzaOrderId = pizzaOrderId;
  }

  public PizzaDTO getPizzaDTO() {
    return pizzaDTO;
  }

  public void setPizzaDTO(PizzaDTO pizzaDTO) {
    this.pizzaDTO = pizzaDTO;
  }

  public DoughDTO getDoughDTO() {
    return doughDTO;
  }

  public void setDoughDTO(DoughDTO doughDTO) {
    this.doughDTO = doughDTO;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }
}
