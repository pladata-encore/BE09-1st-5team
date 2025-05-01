package com.pizzamukza.pizzahut.domain.admin.adminlogin.service;

import com.pizzamukza.pizzahut.domain.admin.adminlogin.rapository.AdminLoginRepository;

import java.sql.Connection;

import static com.pizzamukza.common.JDBCTemplate.*;

public class AdminLoginService {

    private final AdminLoginRepository adminLoginRepository = new AdminLoginRepository();

    public boolean login(String username, String password) {
        Connection con = getConnection();

        boolean result = adminLoginRepository.checkAdminLogin(con, username, password);

        if (result == false) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
        return result;
    }
}
