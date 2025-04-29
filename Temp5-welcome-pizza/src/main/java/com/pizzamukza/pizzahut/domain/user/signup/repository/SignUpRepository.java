package com.pizzamukza.pizzahut.domain.user.signup.repository;

import com.pizzamukza.pizzahut.domain.user.signup.dto.SignUpDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pizzamukza.common.JDBCTemplate.close;

public class SignUpRepository {

    public int insertMember(Connection con, SignUpDTO user) {

        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "INSERT INTO tbl_member (username, password, name) VALUES (?, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }


        return result;
    }
    public SignUpDTO findByUser(Connection con, String username, String password) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SignUpDTO user = null;
        String sql = "SELECT * FROM tbl_member WHERE username = ? AND password = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new SignUpDTO();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
        }

        return user;
    }
    public void updatePassword(Connection con, String username, String newPassword) {
        PreparedStatement pstmt = null;
        String sql = "UPDATE tbl_member SET password = ? WHERE username = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
    }


    public SignUpDTO findByUsername(String username) {
        return null;
    }

    public boolean existsByUsername(String username) {
        return false;
    }

    public void deleteMemberById(Connection con, int memberId) {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM tbl_member WHERE memberId = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            int result = pstmt.executeUpdate();
            System.out.println(" 회원 번호 :" + memberId + " 삭제 " + result + "님 아쉬워요!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
    }
    public List<SignUpDTO> findAll(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<SignUpDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM tbl_member";

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SignUpDTO user = new SignUpDTO();
                user.setMemberId(rs.getString("memberId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(pstmt);
        }

        return users;
    }

}
