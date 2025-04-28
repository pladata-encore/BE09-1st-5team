package com.pizzamukza.pizzahut.domain.admin.pizzamenu.repository;

import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.SizeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.pizzamukza.common.JDBCTemplate.close;

public class PizzaMenuRepository {

    private static Properties prop = new Properties();

    public PizzaMenuRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/pizzamukza/pizzahut/domain/admin/pizzamenu/mapper/AdminPizzaMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<PizzaDTO> pizzaAllList(Connection con) {
        List<PizzaDTO> pizzaList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("pizzaList");

        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                PizzaDTO pizzaDTO = new PizzaDTO();

                // PizzaMenuDTO 설정
                PizzaMenuDTO pizzaMenuDTO = new PizzaMenuDTO();
                pizzaMenuDTO.setPizzaId(rset.getInt("pizzaId"));
                pizzaMenuDTO.setPizzaName(rset.getString("pizzaName"));

                // SizeDTO 설정
                SizeDTO sizeDTO = new SizeDTO();
                sizeDTO.setSizeName(rset.getString("sizeName"));
                sizeDTO.setPrice(rset.getInt("price"));
                sizeDTO.setQuantity(rset.getInt("quantity"));

                // PizzaDTO에 PizzaMenuDTO와 SizeDTO 세팅
                pizzaDTO.setPizzaMenuDTO(pizzaMenuDTO);
                pizzaDTO.setSizeDTO(sizeDTO);

                pizzaList.add(pizzaDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException("🍕 피자 목록 조회 실패", e);
        } finally {
            close(rset);
            close(pstmt);
        }

        return pizzaList;
    }


    public int insertPizza(Connection con, PizzaMenuDTO pizza, SizeDTO size) {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rset = null;
        int result = 0;

        String sql1 = "INSERT INTO tbl_pizza (pizzaName) VALUES (?)";
        String sql2 = "INSERT INTO tbl_size (sizeName, price, quantity, pizzaId) VALUES (?, ?, ?, LAST_INSERT_ID())";  // size 테이블에 사이즈, 가격, 수량, pizzaId 추가

        try {
            // 트랜잭션 시작
            con.setAutoCommit(false);

            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setString(1, pizza.getPizzaName());
            pstmt1.executeUpdate();

            // 2단계: 사이즈 삽입하면서 방금 삽입한 피자 ID 사용
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setString(1, size.getSizeName());
            pstmt2.setInt(2, size.getPrice());
            pstmt2.setInt(3, size.getQuantity());
            result = pstmt2.executeUpdate();

            con.commit(); // 커밋

        } catch (SQLException e) {
            try {
                con.rollback(); // 롤백
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt2);
            close(pstmt1);
        }

        return result;
    }

    public int increaseQuantity(Connection con, String pizzaName, int amount) {
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = prop.getProperty("increaseQuantity");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setString(2, pizzaName);

            result = pstmt.executeUpdate();

            if (result == 0) {
                System.out.println("⚠️ 수량을 증가시킬 피자를 찾을 수 없습니다: " + pizzaName);
            } else {
                System.out.println("✅ 수량이 성공적으로 증가되었습니다: " + pizzaName + " (+ " + amount + ")");
            }

        } catch (SQLException e) {
            System.out.println("⚠️ 피자 수량 증가 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int decreaseQuantity(Connection con, String pizzaName, int amount) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = prop.getProperty("decreaseQuantity");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setString(2, pizzaName);

            result = pstmt.executeUpdate();

            if (result == 0) {
                System.out.println("⚠️ 수량을 감소시킬 피자를 찾을 수 없습니다: " + pizzaName);
            } else {
                System.out.println("✅ 수량이 성공적으로 감소되었습니다: " + pizzaName + " (- " + amount + ")");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ 피자 수량 감소 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deletePizza(Connection con, int pizzaId, String pizzaName) {

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        ResultSet rset = null;
        int result1 = 0;
        int result2 = 0;

        String sql1 = "DELETE FROM tbl_size WHERE pizzaId = ?";
        String sql2 = "DELETE FROM tbl_pizza WHERE pizzaName = ?";

        try {

            pstmt2 = con.prepareStatement(sql2);
            pstmt2.setString(1, pizzaName);
            result2 = pstmt2.executeUpdate();

            if (result2 > 0) {
                pstmt1 = con.prepareStatement(sql1);
                pstmt1.setInt(1, pizzaId);
                result1 = pstmt1.executeUpdate();

            }




        } catch (SQLException e) {
            System.out.println("⚠️ 피자 품절 처리 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(rset);
            close(pstmt1);
            close(pstmt2);
        }

        return result1 > 0 && result2 > 0 ? 1 : 0;

    }

    public int selectDeletePizzaId(Connection con, String pizzaName) {

        PreparedStatement pstmt1 = null;
        ResultSet rset = null;
        int pizzaId = -1;

        String sql1 = "SELECT pizzaId FROM tbl_pizza WHERE pizzaName = ?";

        try {
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setString(1, pizzaName);
            rset = pstmt1.executeQuery();


            if (rset.next()) {
                pizzaId = rset.getInt("pizzaId");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ 피자 품절 처리 쿼리 실행 실패: " + e.getMessage());
        } finally {
            close(rset);
            close(pstmt1);
        }

        return pizzaId;
    }
}
