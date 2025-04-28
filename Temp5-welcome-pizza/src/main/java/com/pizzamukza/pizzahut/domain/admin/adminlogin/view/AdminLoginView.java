package com.pizzamukza.pizzahut.domain.admin.adminlogin.view;

import com.pizzamukza.common.CommonMenuView;
import com.pizzamukza.pizzahut.domain.admin.adminlogin.service.AdminLoginService;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.view.PizzaMenuView;
import com.pizzamukza.pizzahut.domain.main.AdminMainView;

import java.util.Scanner;

import static com.pizzamukza.pizzahut.domain.admin.pizzamenu.view.PizzaMenuView.pizzaMenuMainView;
import static com.pizzamukza.pizzahut.domain.admin.sidemenu.view.SideMenuView.printSideMenuStart;

public class AdminLoginView {

    static PizzaMenuView pizzaMenu = new PizzaMenuView();
    static AdminLoginView alv = new AdminLoginView();

    static Scanner sc = new Scanner(System.in);
    private static final AdminLoginService loginService = new AdminLoginService();
    static AdminMainView adminMainView = new AdminMainView();

    public static void adminLoginView() {
        CommonMenuView.printAdminMenu();
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                adminLogin();
                break;
            case 0:
                adminMainView.main(new String[0]);
            default:
                System.out.println("❌ 올바른 번호를 입력해주세요.");
        }
    }

    private static void adminLogin() {
        System.out.print("ID를 입력하세요(admin) : ");
        String adminId = sc.nextLine();

        System.out.print("비밀번호를 입력하세요(admin123) : ");
        String adminPwd = sc.nextLine();

        if (loginService.login(adminId, adminPwd)) {
            System.out.println("✅ 로그인 성공! 관리자 메뉴로 이동합니다.");
            adminMenu();
        } else {
            System.out.println("❌ 로그인 실패! 아이디 또는 비밀번호가 틀렸습니다.");
        }
    }

    public static void adminMenu() {
        CommonMenuView.printAdminServiceMenu();
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                pizzaMenuMainView();
            case 2:
                printSideMenuStart();
        }

    }

}
