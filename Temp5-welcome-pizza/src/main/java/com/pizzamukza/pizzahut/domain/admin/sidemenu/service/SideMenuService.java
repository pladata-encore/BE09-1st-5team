package com.pizzamukza.pizzahut.domain.admin.sidemenu.service;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.sidemenu.repository.SideMenuRepository;

import java.sql.Connection;
import java.util.List;

import static com.pizzamukza.common.JDBCTemplate.*;

public class SideMenuService {

    private final SideMenuRepository smr;

    public SideMenuService() {
        smr = new SideMenuRepository();
    }

    public List<SideMenuDTO> sideAllList() {
        Connection con = getConnection();
        List<SideMenuDTO> sideList = smr.selectAllSideMenu(con);
        close(con);
        return sideList;
    }

    public void selectAllSideMenus() {

        Connection con = getConnection();

        List<SideMenuDTO> sideMenuDTOList = SideMenuRepository.selectAllSideMenu(con);

        for(SideMenuDTO sm : sideMenuDTOList) {
            System.out.println(sm);
        }

        close(con);

    }

    public boolean registerSideMenu(SideMenuDTO side) {
        Connection con = getConnection();

        int result = smr.insertSideMenu(con, side);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);

        return result > 0;
    }

    public void increaseQuantity(String sideName, int amount) {
        Connection con = getConnection();

        int result = smr.increaseQuantity(con, sideName, amount);

        if (result > 0) {
            commit(con);
            System.out.println("✅ 사이드 수량이 성공적으로 증가했습니다.");
        } else {
            rollback(con);
            System.out.println("❌ 사이드 수량 증가에 실패했습니다.");
        }

        close(con);
    }

    public void decreaseQuantity(String sideName, int amount) {
        Connection con = getConnection();
        try {
            int result = smr.decreaseQuantity(con, sideName, amount);
            if (result > 0) {
                commit(con);
                System.out.println("✅ 사이드 수량이 성공적으로 감소했습니다.");
            } else {
                rollback(con);
                System.out.println("❌ 사이드 수량 감소에 실패했습니다.");
            }
        } finally {
            close(con);
        }
    }

    public void deleteSideMenu(String sideName) {

        Connection con = getConnection();
        try {
            int result = smr.deleteSideMenu(con, sideName);
            if (result > 0) {
                commit(con);
                System.out.println("✅ 선택한 사이드 메뉴가 품절 처리되었습니다.");
            } else {
                rollback(con);
                System.out.println("❌ 선택한 사이드 메뉴 품절 처리에 실패했습니다.");
            }
        } finally {
            close(con);
        }

    }

    public void updateSideMenu(String sideName, int modify) {
        Connection con = getConnection();
        try {
            int result = smr.updateSideMenu(con, sideName, modify);
            if (result > 0) {
                commit(con);
                System.out.println("✅ 선택한 사이드 메뉴의 가격이 수정되었습니다.");
            } else {
                rollback(con);
                System.out.println("❌ 선택한 사이드 메뉴의 가격이 수정에 실패했습니다.");
            }
        } finally {
            close(con);
        }
    }

    public SideMenuDTO findSideMenuById(int sideId) {
        Connection con = getConnection();
        SideMenuDTO result = smr.selectSideById(con, sideId);
        close(con);
        return result;
    }
}


