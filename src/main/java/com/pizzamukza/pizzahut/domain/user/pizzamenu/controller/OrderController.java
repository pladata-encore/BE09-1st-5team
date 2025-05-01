package com.pizzamukza.pizzahut.domain.user.pizzamenu.controller;

import static com.pizzamukza.pizzahut.domain.user.signup.view.SignUpView.displayUserMenu;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.Pizza;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.SideOrder;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.service.OrderService;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.view.OrderView;




public class OrderController {

  /*
  아래 코드와 같이 Ordercontroller객체를 생성하고,
  startOrderMenu() 메서드를 호출하면 주문 메뉴가 실행됩니다. */

/*  public static void main(String[] args) {
    OrderController orderController = new OrderController();
    orderController.startOrderMenu();
  }*/



  public void startOrderMenu() {
    OrderView orderView = new OrderView();
    OrderService orderService = new OrderService();

    int[] pizza;
    int[] side;
    Pizza pizza1 = new Pizza();
    SideMenuDTO side1 = new SideMenuDTO();

    orderView.displayPizzaOrderMenu();
    pizza = orderView.insertPizza();
    // 피자 객체에 현재 입력정보 받기
    pizza1 = orderService.getPizzaById(pizza[0]);

    orderView.displaySideMenu();
    side = orderView.insertSide();
    // 사이드오더 객체에 현재 입력정보 받기
    side1 = orderService.getSideOrderById(side[0]);

    // 총 주문금액 출력
    orderView.displayOrder(pizza1, pizza[1], side1, side[1]);
    boolean pay = false;

    pay = orderView.pay();

    if (pay) {
      orderService.decreaseSide(side);
      orderService.decreasePizza(pizza);
      // 주문 성공 안내메시지 출력
      orderView.displayOrderSuccessMessage();
    }
    // 사용자 시작 메뉴로
    displayUserMenu();
  }

}
