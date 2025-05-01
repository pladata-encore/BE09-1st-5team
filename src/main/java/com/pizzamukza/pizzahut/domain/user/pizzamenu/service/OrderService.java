package com.pizzamukza.pizzahut.domain.user.pizzamenu.service;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.controller.SideMenuController;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.Pizza;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.SideOrder;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.repository.PizzaRepository;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.repository.SideRepository;

public class OrderService {
  private static PizzaRepository pizzaRepository;
  private static SideRepository sideRepository;
  public OrderService() {
    pizzaRepository = new PizzaRepository();
    sideRepository = new SideRepository();
  }

  public void decreasePizza(int[] arr) {
    int sizeId = arr[0];
    int quantity = arr[1];
    pizzaRepository.decreasePizzaQuantityBySizeId(sizeId, quantity);
  }

  public void decreaseSide(int[] arr) {
    int sideId = arr[0];
    int quantity = arr[1];
    sideRepository.decreaseSideQuantityBySideId(sideId, quantity);
  }

  public int getPizzaPrice(int[] arr) {
    int sizeId = arr[0];
    int quantity = arr[1];
    int price = pizzaRepository.getPizzaPriceById(sizeId) * quantity;
    return price;
  }

  public Pizza getPizzaById(int id) {
    return pizzaRepository.getPizzaById(id);
  }



  public SideMenuDTO getSideOrderById(int id) {
    SideMenuDTO sideMenuDTO = new SideMenuDTO();
    sideMenuDTO = sideRepository.getSideById(id);
    return sideMenuDTO;
  }
}
