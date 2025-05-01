package com.pizzamukza.pizzahut.domain.user.pizzamenu.repository;

import static com.pizzamukza.common.JDBCTemplate.getConnection;

import com.pizzamukza.pizzahut.domain.admin.sidemenu.dto.SideMenuDTO;
import com.pizzamukza.pizzahut.domain.user.pizzamenu.dto.SideOrder;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SideRepository {

 /* public static void main(String[] args) {
    SideRepository repository = new SideRepository();
    repository.decreaseSideQuantityBySideId(1, 3);
    List<SideMenuDTO> menu = repository.getSideMenuList();
    for (SideMenuDTO menuDTO : menu) {
      System.out.println(menuDTO);
    }
  }*/

  private final Properties prop;

  public SideRepository() {
    prop = new Properties();
    try {
      prop.loadFromXML(new FileInputStream(
          "src/main/java/com/pizzamukza/pizzahut/domain/user/pizzamenu/mapper/SideMapper.xml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 주문 시, 사이드 수량 감소 -> 성공 시 감소한 수량 반환, 실패 시 -1 반환
  public int decreaseSideQuantityBySideId(int sideId, int quantity) {
    String sql = prop.getProperty("decreaseSideById");
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = getConnection();
      conn.setAutoCommit(false); // 트랜잭션 시작

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, quantity);  // 첫 번째 ?에 감소할 수량
      pstmt.setInt(2, sideId);    // 두 번째 ?에 사이드 ID

      int result = pstmt.executeUpdate(); // 실행

      if (result > 0) {
        conn.commit();  // 성공하면 커밋
        return quantity;
      } else {
        conn.rollback();  // 실패하면 롤백
        return -1;
      }
    } catch (SQLException e) {
      if (conn != null) {
        try {
          conn.rollback();  // 예외 발생 시 롤백
        } catch (SQLException se) {
          se.printStackTrace();
        }
      }
      throw new RuntimeException(e);
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (Exception ignored) {
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (Exception ignored) {
      }
    }
  }


  public List<SideMenuDTO> getSideMenuList() {
    String sql = prop.getProperty("selectAllSideDetails");
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<SideMenuDTO> sideMenus = new ArrayList<>();

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        int sideId = rs.getInt("sideId");
        String sideName = rs.getString("sideName");
        int price = rs.getInt("price");
        int quantity = rs.getInt("quantity");

        SideMenuDTO sideMenu = new SideMenuDTO(sideId, sideName, price, quantity);
        sideMenus.add(sideMenu);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } catch (Exception ignored) {
      }
      try {
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (Exception ignored) {
      }
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (Exception ignored) {
      }
    }
    for (SideMenuDTO sideMenu : sideMenus) {
      System.out.println(sideMenu);
    }
    return sideMenus;
  }


  public SideMenuDTO getSideById(int id) {
    String sql = prop.getProperty("selectSideDetailsById");
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    SideMenuDTO sideMenu = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        int sideId = rs.getInt("sideId");
        String sideName = rs.getString("sideName");
        int price = rs.getInt("price");
        int quantity = rs.getInt("quantity");

        sideMenu = new SideMenuDTO(sideId, sideName, price, quantity);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) rs.close();
      } catch (Exception ignored) {}
      try {
        if (pstmt != null) pstmt.close();
      } catch (Exception ignored) {}
      try {
        if (conn != null) conn.close();
      } catch (Exception ignored) {}
    }

    return sideMenu;
  }

}
