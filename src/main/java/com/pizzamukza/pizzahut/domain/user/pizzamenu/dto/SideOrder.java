package com.pizzamukza.pizzahut.domain.user.pizzamenu.dto;

import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.SizeDTO;

public class SideOrder {
  int sizeOrderId;
  SizeDTO sizeDTO;
  int quantity;
  int totalPrice;

  public SideOrder() {}

  @Override
  public String toString() {
    return "SizeOrder{" +
        "sizeOrderId=" + sizeOrderId +
        ", sizeDTO=" + sizeDTO +
        ", quantity=" + quantity +
        ", totalPrice=" + totalPrice +
        '}';
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public SideOrder(int sizeOrderId, SizeDTO sizeDTO, int quantity, int totalPrice) {
    this.sizeOrderId = sizeOrderId;
    this.sizeDTO = sizeDTO;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }

  public int getSizeOrderId() {
    return sizeOrderId;
  }

  public SizeDTO getSizeDTO() {
    return sizeDTO;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getTotalPrice() {
    return totalPrice;
  }
}
