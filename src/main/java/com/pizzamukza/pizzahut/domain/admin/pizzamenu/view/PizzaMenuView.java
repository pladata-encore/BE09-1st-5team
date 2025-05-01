package com.pizzamukza.pizzahut.domain.admin.pizzamenu.view;

import com.pizzamukza.common.CommonMenuView;
import com.pizzamukza.pizzahut.domain.admin.adminlogin.view.AdminLoginView;
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
                    AdminLoginView.adminMenu();
                default:
                    System.out.println("❌ 올바른 번호를 입력해주세요.");
            }

        } while (choice != 0);
    }

    private static void showPizzaMenuList() {
        List<PizzaDTO> pizzaList = pmc.getAllPizzas();  // 모든 피자 정보를 가져온다.

        System.out.println("\n🍕 현재 판매중인 피자 목록 🍕");
        System.out.println("=".repeat(72));
        System.out.printf("%-8s %-20s %-10s %12s %8s\n", "피자번호", "피자 이름", "사이즈", "가격", "수량");
        System.out.println("-".repeat(72));

        for (PizzaDTO pizza : pizzaList) {
            SizeDTO size = pizza.getSizeDTO();  // SizeDTO 가져오기
            System.out.printf("%-8d %-20s %-10s %,12d원 %6d개\n",
                    size.getPizzaId(),               // 피자 ID
                    "🍕 " + pizza.getPizzaName(),    // 피자 이름
                    size.getSizeName(),              // 사이즈
                    size.getPrice(),                 // 가격
                    size.getQuantity());             // 수량
        }

        System.out.println("=".repeat(72));
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
        showPizzaMenuList();

        System.out.print("📦 수량을 증가시킬 피자 번호: ");
        int pizzaId = sc.nextInt();
        sc.nextLine();

        System.out.print("➕ 추가할 수량: ");
        int amount = sc.nextInt();
        sc.nextLine();

        pmc.increaseQuantity(pizzaId, amount);
    }

    private static void decreaseQuantityView() {
        showPizzaMenuList();

        System.out.print("📦 수량을 감소시킬 피자 번호: ");
        int pizzaId = sc.nextInt();
        sc.nextLine();

        System.out.print("➖ 감소할 수량: ");
        int amount = Integer.parseInt(sc.nextLine());

        pmc.decreaseQuantity(pizzaId, amount);
    }

    private static void deletePizzaView() {
        System.out.print("❗ 품절 처리할 피자 이름: ");
        String pizzaName = sc.nextLine().trim();

        // 피자 품절 처리
        pmc.deletePizza(pizzaName);
    }

}

