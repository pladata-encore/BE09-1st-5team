package com.pizzamukza.pizzahut.domain.admin.sidemenu.controller;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.service.SideMenuService;

import java.util.List;

public class SideMenuController {

    private static final SideMenuService sms = new SideMenuService();

    public List<SideMenuDTO> getAllSides() {
        return sms.sideAllList();
    }

    public void addNewSideMenu(SideMenuDTO side) {
        boolean result = sms.registerSideMenu(side);

        if (result) {
            System.out.println("✅ 사이드 메뉴가 성공적으로 등록되었습니다.");
        } else {
            System.out.println("❌ 사이드 메뉴 등록에 실패했습니다.");
        }
    }

    public void increaseQuantity(String sideName, int amount) {
        sms.increaseQuantity(sideName, amount);
    }

    public static void decreaseQuantity(String sideName, int amount) {
        sms.decreaseQuantity(sideName, amount);
    }

    public void deleteSideMenu(String sideName) {
        sms.deleteSideMenu(sideName);
    }

    public void updateSideMenu(String sideName, int modify) {
        sms.updateSideMenu(sideName, modify);
    }

    public SideMenuDTO getSideById(int sideId) {
        return sms.findSideMenuById(sideId);
    }
}
