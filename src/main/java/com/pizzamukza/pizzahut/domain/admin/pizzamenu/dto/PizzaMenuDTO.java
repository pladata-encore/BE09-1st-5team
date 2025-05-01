package com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto;

public class PizzaMenuDTO {

    private int pizzaId;
    private String pizzaName;

    public PizzaMenuDTO() {
    }

    public PizzaMenuDTO(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }
}
