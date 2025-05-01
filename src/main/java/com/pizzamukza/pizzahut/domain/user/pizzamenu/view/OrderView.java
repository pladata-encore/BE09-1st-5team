package com.pizzamukza.pizzahut.domain.user.pizzamenu.view;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.Pizza;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.SideOrder;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.repository.PizzaRepository;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.repository.SideRepository;
import java.util.List;
import java.util.Scanner;

public class OrderView {

  public static Scanner sc = new Scanner(System.in);
  private SideRepository sr = new SideRepository();
  private PizzaRepository pr = new PizzaRepository();

  public OrderView() {

  }

  public void displayPizzaOrderMenu() {
    System.out.println("\uD83C\uDF55 현재 판매중인 피자 목록 \uD83C\uDF55 ");
    List<Pizza> pizzaMenu = pr.getPizzaList();
    for (Pizza pizzaMenu1 : pizzaMenu) {
      System.out.println(pizzaMenu1);
    }
  }

  public void displaySideMenu() {
    System.out.println("\uD83C\uDF5F 현재 판매중인 사이드 목록 \uD83C\uDF5F ");
    sr.getSideMenuList();
  }

  public int[] insertPizza() {
    System.out.print("주문할 피자 번호를 입력하세요: ");
    int sizeId = sc.nextInt();
    System.out.print("수량을 입력하세요: ");
    int quantity = sc.nextInt();
    return new int[]{sizeId, quantity};
  }


  public int[] insertSide() {
    System.out.print("주문할 사이드 번호를 입력하세요: ");
    int sideId = sc.nextInt();
    System.out.print("수량을 입력하세요: ");
    int quantity = sc.nextInt();

    return new int[]{sideId, quantity};
  }

  public static void displayOrder(Pizza pizza, int pizzaQuantity, SideOrder sideOrder) {
    System.out.println("피자 주문 내역 입니다.");
    System.out.println(pizza);
    System.out.println(pizzaQuantity);
    System.out.println(sideOrder);
  }

  public static boolean pay() {
    System.out.println("\uD83D\uDCB3 결제 하시겠습니까? (Y/N)");
    String input = sc.next().toLowerCase();
    if (input.equals("y")) {
      return true;
    } else {
      return false;
    }
  }

  public static void displayOrderSuccessMessage() {
    System.out.println("✅ 주문이 성공적으로 완료되었습니다.");
  }


  public void displayOrder(Pizza pizza, int pizzaQuantity, SideMenuDTO side, int sideQuantity) {
    int pizzaPrice = pizza.price * pizzaQuantity;
    int sidePrice = side.getPrice() * sideQuantity;

    System.out.println("=".repeat(30));
    System.out.println("\uD83D\uDCE6 주문 요약서");
    System.out.println("=".repeat(30));
    System.out.println("\n \uD83C\uDF55 피자");
    System.out.println(pizza.toString() + " " + pizzaPrice + "원");
    System.out.println("\uD83C\uDF5F 사이드");
    System.out.println(side.toString() + " " + sidePrice + "원");
    System.out.println();
    System.out.println("=".repeat(30));

    System.out.println("총 주문 금액: " + (pizzaPrice + sidePrice) + "원");
    System.out.println("=".repeat(30));
  }
}
