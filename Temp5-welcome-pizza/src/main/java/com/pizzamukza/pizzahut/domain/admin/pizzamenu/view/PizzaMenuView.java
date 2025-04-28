package com.pizzamukza.pizzahut.domain.admin.pizzamenu.view;

import com.pizzamukza.common.CommonMenuView;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.controller.PizzaMenuController;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.SizeDTO;
import com.pizzamukza.pizzahut.domain.main.AdminMainView;

import java.util.List;
import java.util.Scanner;

public class PizzaMenuView {
    static Scanner sc = new Scanner(System.in);
    static PizzaMenuController pmc = new PizzaMenuController();
    static AdminMainView adminMainView = new AdminMainView();

    public static void pizzaMenuMainView() {
        String adminPizzaMenu = """
                --- 피자 관리 ---
                1. 피자 목록 조회
                2. 신메뉴 등록
                3. 피자 수량 증가
                4. 피자 수량 감소
                5. 피자 품절
                0. 이전 메뉴로 돌아가기
                ==================
                번호를 입력하세요: """;
        int choice = 0;

        do {
            System.out.print(adminPizzaMenu);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showPizzaMenuList();
                    break;
                case 2:
                    addNewPizzaView();
                    break;
                case 3:
                    increaseQuantityView();
                    break;
                case 4:
                    decreaseQuantityView();
                    break;
                case 5:
                    deletePizzaView();
                    break;
                case 0:
                    adminMainView.main(new String[0]);
                default:
                    System.out.println("❌ 올바른 번호를 입력해주세요.");
            }

        } while (choice != 0);
    }

    private static void showPizzaMenuList() {
        List<PizzaDTO> pizzaList = pmc.getAllPizzas();  // 모든 피자 정보를 가져온다.

        System.out.println("\n🍕 현재 판매중인 피자 목록 🍕");
        System.out.println("=".repeat(50));

        for (PizzaDTO pizza : pizzaList) {
            SizeDTO size = pizza.getSizeDTO();  // PizzaDTO에서 SizeDTO를 가져온다.
            System.out.printf("🍕 %-20s | 가격: %,6d원 | 사이즈: %-7s | 수량: %2d\n",
                    pizza.getPizzaName(), size.getPrice(), size.getSizeName(), size.getQuantity());
        }

        System.out.println("=".repeat(60));
    }

    private static void addNewPizzaView() {
        System.out.print("🍕 피자 이름: ");
        String name = sc.nextLine();

        System.out.print("📦 수량: ");
        int quantity = sc.nextInt();

        sc.nextLine();

        System.out.print("📏 사이즈 (미디움 / 라지): ");
        String sizeName = sc.nextLine();

        System.out.print("💰 가격: ");
        int price = sc.nextInt();
        sc.nextLine();

        PizzaMenuDTO pizza = new PizzaMenuDTO(name);
        SizeDTO size = new SizeDTO(sizeName, price, quantity);

        PizzaMenuController.addNewPizza(pizza, size);

    }

    private static void increaseQuantityView() {
        System.out.print("📦 수량을 증가시킬 피자 이름: ");
        String pizzaName = sc.nextLine().trim();

        System.out.print("➕ 추가할 수량: ");
        int amount = sc.nextInt();

        pmc.increaseQuantity(pizzaName, amount);
    }

    private static void decreaseQuantityView() {
        System.out.print("📦 수량을 감소시킬 피자 이름: ");
        String pizzaName = sc.nextLine().trim();

        System.out.print("➖ 감소할 수량: ");
        int amount = Integer.parseInt(sc.nextLine());

        pmc.decreaseQuantity(pizzaName, amount);
    }

    private static void deletePizzaView() {
        System.out.print("❗ 품절 처리할 피자 이름: ");
        String pizzaName = sc.nextLine().trim();

        // 피자 품절 처리
        pmc.deletePizza(pizzaName);
    }

}

