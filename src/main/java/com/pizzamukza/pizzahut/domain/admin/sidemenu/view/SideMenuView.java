package com.pizzamukza.pizzahut.domain.admin.sidemenu.view;

import com.pizzamukza.common.CommonMenuView;
import com.pizzamukza.pizzahut.domain.admin.adminlogin.view.AdminLoginView;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.controller.SideMenuController;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.repository.SideMenuRepository;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.service.SideMenuService;
import com.pizzamukza.pizzahut.domain.main.AdminMainView;

import java.util.List;
import java.util.Scanner;

public class SideMenuView {
    static SideMenuService sms = new SideMenuService();
    static SideMenuController smc = new SideMenuController();
    static Scanner sc = new Scanner(System.in);
    static AdminMainView adminMainView = new AdminMainView();

    public static void main(String[] args) {
        SideMenuService sm = new SideMenuService();

//        sm.selectAllSideMenus();

        /* 관리자 메뉴 출력 (관리자 로그인시 공동) */
        CommonMenuView.printAdminServiceMenu();
        int input = sc.nextInt();
        switch (input) {
            case 1 : break;
            case 2 : printSideMenuStart();
                break;
            case 0 : CommonMenuView.printAdminMenu();
                break;
            default :
                System.out.println("번호를 잘못 입력하셨습니다.");
        }

    }

    /* 초기 메인화면으로 돌아가기 */
    public static void printSideMenuBack() {
        CommonMenuView.printMainMenu();
    }

     /* 사이드 관리 화면 출력 */
    public static void printSideMenuStart() {
        Scanner sc = new Scanner(System.in);

        String start = """
                ====== 사이드 관리 ======
                1. 사이드 메뉴 목록 조회
                2. 신메뉴 등록
                3. 사이드 메뉴 수량 증가
                4. 사이드 메뉴 수량 감소
                5. 사이드 메뉴 품절
                6. 사이드 가격 수정
                0. 이전 메뉴로 돌아가기
                =======================
                번호를 입력하세요:  """;

        int input = 0;

        do {
            System.out.print(start);
            input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1 : showSideMenuList();
                    break;
                case 2 : printSideMenuInsert();
                    break;
                case 3 : increaseQuantityView();
                    break;
                case 4 : decreaseQuantityView();
                    break;
                case 5 : deleteSideMenuView();
                    break;
                case 6 : printSideMenuUpdate();
                    break;
                case 0 :
                    AdminLoginView.adminMenu();
                    break;
                default :
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }

        } while (input != 0);

    }

    /* 사이드 목록조회 */
    public static void showSideMenuList() {
        List<SideMenuDTO> sideList = smc.getAllSides();

        System.out.println("\n🍟 현재 판매중인 사이드 목록 🍟");
        System.out.println("=".repeat(30));
        for (SideMenuDTO side : sideList) {
            System.out.printf("🍟 %-15s | 수량: %2d | 가격: %3d원\n", side.getSideName(), side.getQuantity(), side.getPrice());
        }
        System.out.println("=".repeat(30));
    }

    /* 사이드 등록 및 재고(0) */
    public static void printSideMenuInsert() {
        sc.nextLine();  // 사이드 메뉴 등록 전에 남은 개행 제거 (1회만)

        System.out.print("🍟 사이드 메뉴 이름: ");
        String sideName = sc.nextLine();  // 여기서는 개행 제거 없이 입력 받음

        System.out.print("📦 수량: ");
        int quantity = sc.nextInt();
        sc.nextLine();  // 개행 제거

        System.out.print("💰 가격: ");
        int price = sc.nextInt();
        sc.nextLine();  // 개행 제거

        SideMenuDTO newSideMenuDTO = new SideMenuDTO(sideName, quantity, price);
        smc.addNewSideMenu(newSideMenuDTO);
    }


    /* 사이드 수량 증가 */
    private static void increaseQuantityView() {
        sc.nextLine();

        System.out.print("📦 수량을 증가시킬 사이드 메뉴 이름: ");
        String sideName = sc.nextLine().trim();

        System.out.print("➕ 추가할 수량: ");
        int amount = sc.nextInt();

        smc.increaseQuantity(sideName, amount);
    }

    /* 사이드 수량 감소 */
    public static void decreaseQuantityView() {
        sc.nextLine();

        System.out.print("📦 수량을 감소시킬 사이드 메뉴 이름: ");
        String sideName = sc.nextLine().trim();

        System.out.print("➖ 감소할 수량: ");
        int amount = Integer.parseInt(sc.nextLine());

        smc.decreaseQuantity(sideName, amount);
    }

    /* 사이드 삭제 */
    private static void deleteSideMenuView() {
        sc.nextLine();

        System.out.print("❗ 품절 처리할 사이드 메뉴 이름: ");
        String sideName = sc.nextLine().trim();

        // 피자 품절 처리
        smc.deleteSideMenu(sideName);

    }

    /* 사이드 가격 수정 */
    public static void printSideMenuUpdate() {

        sc.nextLine();

        System.out.print("📦 가격을 수정할 사이드 메뉴 이름: ");
        String sideName = sc.nextLine().trim();

        System.out.print("💲 메뉴의 수정 가격 : ");
        int modify = sc.nextInt();

        smc.updateSideMenu(sideName, modify);
    }

    /* 입력 : sideID, 출력 : sideName, quantity, price */
    public static void showSideMenuById() {
        System.out.print("🔍 조회할 사이드 ID를 입력하세요: ");
        int sideId = sc.nextInt();
        sc.nextLine(); // 개행 제거

        SideMenuDTO sideDTO = smc.getSideById(sideId);

        smc.getSideById(sideDTO.getSideId());
        if (sideDTO != null) {
            System.out.println("🍟 조회 결과");
            System.out.println("=".repeat(30));
            System.out.printf("🍟 %-15s | 수량: %2d | 가격: %3d원\n",
                    sideDTO.getSideName(), sideDTO.getQuantity(), sideDTO.getPrice());
            System.out.println("=".repeat(30));
        } else {
            System.out.println("⚠️ 해당 ID의 사이드 메뉴가 없습니다.");
        }
    }

}
