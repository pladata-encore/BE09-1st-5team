package com.pizzamukza.pizzahut.domain.user.signup.view;

import com.pizzamukza.common.CommonMenuView;
import com.pizzamukza.common.UserInfo;
import com.pizzamukza.pizzahut.domain.main.AdminMainView;
import com.pizzamukza.pizzahut.domain.user.login.dto.Login;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.controller.OrderController;
import com.pizzamukza.pizzahut.domain.user.signup.controller.SignUpController;
import com.pizzamukza.pizzahut.domain.user.signup.dto.SignUpDTO;


import java.util.List;
import java.util.Scanner;

import static com.pizzamukza.common.CommonMenuView.username;

public class SignUpView {
    private static SignUpController controller = new SignUpController();
    static OrderController orderController = new OrderController();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MainMenu(); //메인으로 하면 이전화면으로 돌아감
        //    new SignUpView().MainMenu();
        //  new SignUpView().displayUserStartMenu();
        //new SignUpView().displayUserMenu();

    }

    public static void MainMenu() {
        while (true) {
            CommonMenuView.printMainMenu();
            String choice = sc.nextLine();

            switch (choice) {
                case "1":

                    break;
                case "2":
                    displayUserStartMenu();
                    break;
                case "0":
                    logout();//바꿔야함
                    return; // 종료
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    public static void displayUserStartMenu() {
        while (true) {
            CommonMenuView.printUserMenu();
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    logout();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void registerUser() {
        String username = "";
        String pw = "";
        String name = "";
        while (true) {
            System.out.print("===== 회원가입 하시겠습니까?(Y/N)===== : ");
            char who = sc.next().charAt(0);
            sc.nextLine();
            if (who == 'Y') {
                System.out.print("아이디를 입력하세요 : ");
                username = sc.nextLine();
                System.out.print("비밀번호를 입력하세요 : ");
                pw = sc.nextLine();
                System.out.print("이름를 입력하세요: ");
                name = sc.nextLine();
                controller.register(username, pw, name);
                System.out.println("✅회원가입이 완료 되었습니다.");
                System.out.println("로그인 화면으로 돌아갑니다...");
                loginUser();
                break;
            } else if (who == 'N') {
                System.out.println("아쉽네요");
                System.out.print("이전 화면으로 돌아갑니다...");
                break;
            } else {
                System.out.println("장난치지 마세요");
            }
        }
    }

    private static void loginUser() {
        System.out.println("=============== 사용자 로그인 ================");
        while (true) {
            System.out.print("아이디를 입력하세요 : ");
            String loginusername = sc.nextLine();
            System.out.print("비밀번호를 입력하세요 : ");
            String loginPw = sc.nextLine();

            if (controller.login(loginusername, loginPw)) {
                String name = UserInfo.info.getName();
                System.out.println("로그인 성공! 어서오세요~ " + name + "님! 사용자 메뉴로 이동합니다...");
                displayUserMenu();
                //    CommonMenuView.printUserMenu();// 이전상태로 이동
                break;
            } else {
                System.out.println("[!] 아이디 또는 비밀번호가 올바르지 않습니다.");
            }
        }
    }

    private static void logout() {
        if (UserInfo.info != null) {
            System.out.println(UserInfo.info.getName() + " 님 ✅ 로그아웃 되었습니다. 메인 메뉴로 돌아갑니다.");
            UserInfo.info = null;
            displayUserStartMenu();
        } else {
            AdminMainView.main(new String[0]);
        }
    }

    public static void displayUserMenu() {
        while (true) {
            System.out.print(CommonMenuView.USER_MENU);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    orderController.startOrderMenu();
                    // TODO : 주문하기 메서드 추가 예정
                    break;
                case "2":
                    edit();
                    break;
                case "3":
                    deleteUser();
                    return; // 탈퇴 후 로그인으로 돌아가거나 프로그램 종료
                case "0":
                    logout();
                    return; // 종료
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void edit() {
        while (true) {
            System.out.println("비밀번호를 잘못 입력하셨다면 수정하실 수 있습니다.");
            System.out.print("비밀번호 수정하시겠습니까? (Y/N) : ");
            char edit = sc.next().charAt(0);
            sc.nextLine();
            if (edit == 'Y') {
                String username = UserInfo.info.getUsername();
                System.out.print("새 비밀번호: ");
                String newPw = sc.nextLine();
                System.out.println(username + "님의 비밀번호가 새롭게 설정 되었습니다.");
                controller.changePassword(username, newPw);
                displayUserStartMenu();
                break;
            } else if (edit == 'N') {
                System.out.println("이전 화면으로 이동합니다.");
                break;
            } else {
                System.out.println("장난 치지마세요");

            }
        }
    }

    private static void deleteUser() {
        System.out.println("===== 회원 탈퇴 =====");
        System.out.println("현재 회원 목록:");
        List<SignUpDTO> users = SignUpView.controller.getAllUsers();
        for (SignUpDTO u : users) {
            System.out.println("번호: " + u.getMemberId() + " | 아이디: " + u.getUsername() + " | 이름: " + u.getName());
        }

        System.out.print("삭제할 회원 번호를 입력하세요: ");
        int memberIdToDelete = Integer.parseInt(sc.nextLine());
        SignUpView.controller.deleteUserById(memberIdToDelete);
        System.out.println("삭제가 완료되었습니다.");
    }
}
