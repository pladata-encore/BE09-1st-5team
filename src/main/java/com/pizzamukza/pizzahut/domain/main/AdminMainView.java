package com.pizzamukza.pizzahut.domain.main;

import com.pizzamukza.common.CommonMenuView;
import com.pizzamukza.pizzahut.domain.admin.adminlogin.view.AdminLoginView;
import com.pizzamukza.pizzahut.domain.user.signup.view.SignUpView;

import java.util.Scanner;

public class AdminMainView {
    static Scanner sc = new Scanner(System.in);
    static AdminLoginView alv = new AdminLoginView();
    static SignUpView sv = new SignUpView();

    public static void main(String[] args) {
        CommonMenuView.printMainMenu();
        int choice = sc.nextInt();

        switch (choice) {
            // 1번 클릭 시 관리자 초기 메인화면 이동 (로그인 화면)
            case 1:
                alv.adminLoginView();
            // 2번 클릭 시 사용자 초기 메인화면 이동 (printUserMenu)
            case 2:
                sv.displayUserStartMenu();
            case 0:
                System.out.println("프로그램이 종료됩니다.");
                break;
            default:
                System.out.println("번호를 다시 입력해주세요");
        }
    }
}
