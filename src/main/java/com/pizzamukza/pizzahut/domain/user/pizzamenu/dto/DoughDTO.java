package com.pizzamukza.pizzahut.domain.user.pizzamenu.dto;

public class DoughDTO {
  public int doughId;
  public String doughName;
  public int doughPrice;

  @Override
  public String toString() {
    return "DoughDTO{" +
        "doughId=" + doughId +
        ", doughName='" + doughName + '\'' +
        ", doughPrice=" + doughPrice +
        '}';
  }

  public int getDoughId() {
    return doughId;
  }

  public void setDoughId(int doughId) {
    this.doughId = doughId;
  }

  public String getDoughName() {
    return doughName;
  }

  public void setDoughName(String doughName) {
    this.doughName = doughName;
  }

  public int getDoughPrice() {
    return doughPrice;
  }

  public void setDoughPrice(int doughPrice) {
    this.doughPrice = doughPrice;
  }

  public DoughDTO(int doughId, String doughName, int doughPrice) {
    this.doughId = doughId;
    this.doughName = doughName;
    this.doughPrice = doughPrice;
  }


}
