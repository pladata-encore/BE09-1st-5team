package com.pizzamukza.pizzahut.domain.admin.sidemenu.repository;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.pizzamukza.common.JDBCTemplate.close;

public class SideMenuRepository {

    private static Properties props = new Properties();
    private static SideMenuDTO sm = new SideMenuDTO();


    public SideMenuRepository() {
        props = new Properties();
        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/pizzamukza/pizzahut/domain/admin/sidemenu/mapper/TestMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* 목록 조회 */
    public static List<SideMenuDTO> selectAllSideMenu(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        List<SideMenuDTO> sideMenuDTOS = new ArrayList<>();

        String query = "SELECT * FROM tbl_side_menu";
//        System.out.println("query = " + query);

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            /* sm은 static으로 1개입니다. sideMenuDTOS.add(sm)은 같은 객체를 계속 추가하는 것과 같습니다.
            따라서 같은 객체를 여러번 넣는 것이 됩니다.
            setSideID, setSideName을 한다고 해도 객체가 달라지는 것이 아닌 객체 값이 달라지기 떄문에
            여전히 오류가 있습니다. */

            while (rset.next()) {
                /*sm.setSideId(rset.getInt("sideId"));
                sm.setSideName(rset.getString("sideName"));
                sm.setPrice(rset.getInt("price"));
                sm.setQuantity(rset.getInt("quantity"));
                sideMenuDTOS.add(sm);*/
                SideMenuDTO sideMenuDTO = new SideMenuDTO();
                sideMenuDTO.setSideId(rset.getInt("sideId"));
                sideMenuDTO.setSideName(rset.getString("sideName"));
                sideMenuDTO.setPrice(rset.getInt("price"));
                sideMenuDTO.setQuantity(rset.getInt("quantity"));
                sideMenuDTOS.add(sideMenuDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }

        return sideMenuDTOS;

    }

    public int insertSideMenu(Connection con, SideMenuDTO side) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = props.getProperty("insertSideMenu");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, side.getSideName());
            pstmt.setInt(2, side.getQuantity());
            pstmt.setInt(3, side.getPrice());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int increaseQuantity(Connection con, String sideName, int amount) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = props.getProperty("increaseQuantity");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setString(2, sideName);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int decreaseQuantity(Connection con, String sideName, int amount) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = props.getProperty("decreaseQuantity");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setString(2, sideName);

            result = pstmt.executeUpdate(); // 영향을 받은 행 수 반환
        } catch (SQLException e) {
            System.out.println("⚠️ 사이드 메뉴 수량 감소 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteSideMenu(Connection con, String sideName) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = props.getProperty("deleteSideMenu");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, sideName);

            result = pstmt.executeUpdate(); // 영향을 받은 행 수 반환
        } catch (SQLException e) {
            System.out.println("⚠️ 사이드 메뉴 품절 처리 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updateSideMenu(Connection con, String sideName, int modify) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = props.getProperty("updateSideMenu");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, modify);
            pstmt.setString(2, sideName);

            result = pstmt.executeUpdate(); // 영향을 받은 행 수 반환
        } catch (SQLException e) {
            System.out.println("⚠️ 사이드 메뉴 품절 처리 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return result;
    }

    public SideMenuDTO selectSideById(Connection con, int sideId) {
        SideMenuDTO side = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = props.getProperty("selectSideById");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, sideId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                side = new SideMenuDTO();
                side.setSideName(rset.getString("sideName"));
                side.setQuantity(rset.getInt("quantity"));
                side.setPrice(rset.getInt("price"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }

        return side;
    }


//    /* 목록 조회 */
//    public static List<SideMenuDTO> selectAllSideMenu(Connection con) {
//        Statement stmt = null;
//        ResultSet rset = null;
//        List<SideMenuDTO> sideMenuDTOS = new ArrayList<>();
//
//        String query = "SELECT * FROM tbl_side_menu";
//        System.out.println("query = " + query);
//
//        try {
//            stmt = con.createStatement();
//            rset = stmt.executeQuery(query);
//
//            while (rset.next()) {
//                SideMenuDTO sm = new SideMenuDTO();
//                sm.setSideId(rset.getInt("sideId"));
//                sm.setSideName(rset.getString("sideName"));
//                sm.setPrice(rset.getInt("price"));
//                sm.setQuantity(rset.getInt("quantity"));
//                sideMenuDTOS.add(sm);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(rset);
//            close(stmt);
//            close(con);
//        }
//
//        return sideMenuDTOS;
//
//    }
}