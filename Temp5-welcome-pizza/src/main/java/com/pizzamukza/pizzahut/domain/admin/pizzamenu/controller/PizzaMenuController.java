package com.pizzamukza.pizzahut.domain.admin.pizzamenu.controller;

import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.SizeDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.service.PizzaMenuService;

import java.util.List;

public class PizzaMenuController {

    private static final PizzaMenuService service = new PizzaMenuService();

    public List<PizzaDTO> getAllPizzas() {
        return service.pizzaAllList();
    }

    public static void addNewPizza(PizzaMenuDTO pizza, SizeDTO size) {
        boolean result = service.registerNewPizza(pizza, size);

        if (result) {
            System.out.println("✅ 피자 메뉴가 성공적으로 등록되었습니다.");
        } else {
            System.out.println("❌ 피자 메뉴 등록에 실패했습니다.");
        }
    }

    public void increaseQuantity(String pizzaName, int amount) {
        service.increaseQuantity(pizzaName, amount);
    }

    public static void decreaseQuantity(String pizzaName, int amount) {
        service.decreaseQuantity(pizzaName, amount);
    }

    public void deletePizza(String pizzaName) {
        service.deletePizza(pizzaName);
    }
}
