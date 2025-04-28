package com.pizzamukza.pizzahut.domain.admin.adminlogin.rapository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AdminLoginRepository {

    private final Properties prop = new Properties();

    public AdminLoginRepository() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/pizzamukza/pizzahut/domain/admin/adminlogin/mapper/admin-login-mapper.xml"));
        } catch (IOException e) {
            System.out.println("⚠️ admin-mapper.xml 로딩 실패: " + e.getMessage());
        }
    }

    public boolean checkAdminLogin(Connection conn, String username, String password) {
        boolean isAdmin = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("adminlogin");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                isAdmin = true;
            }

        } catch (SQLException e) {
            System.out.println("⚠️ 로그인 쿼리 실패: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (pstmt != null) pstmt.close(); } catch (SQLException ignored) {}
        }

        return isAdmin;
    }
}
