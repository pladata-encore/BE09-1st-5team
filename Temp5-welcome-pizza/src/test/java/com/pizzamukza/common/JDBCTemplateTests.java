package com.pizzamukza.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static com.pizzamukza.common.JDBCTemplate.getConnection;
import static org.junit.jupiter.api.Assertions.*;

class JDBCTemplateTests {

    @DisplayName("Connection 연결 확인")
    @Test
    void Connection() {
        Connection conn = getConnection();
        Assertions.assertNotNull(conn);
    }

}