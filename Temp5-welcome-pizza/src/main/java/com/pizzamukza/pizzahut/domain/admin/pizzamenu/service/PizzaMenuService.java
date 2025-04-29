package com.pizzamukza.pizzahut.domain.admin.pizzamenu.service;

import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.PizzaMenuDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.dto.SizeDTO;
import com.pizzamukza.pizzahut.domain.admin.pizzamenu.repository.PizzaMenuRepository;

import java.sql.Connection;
import java.util.List;

import static com.pizzamukza.common.JDBCTemplate.*;

public class PizzaMenuService {

    private final PizzaMenuRepository repository = new PizzaMenuRepository();

    public List<PizzaDTO> pizzaAllList() {
        Connection con = getConnection();
        List<PizzaDTO> pizzaList = repository.pizzaAllList(con);
        close(con);
        return pizzaList;
    }

    public boolean registerNewPizza(PizzaMenuDTO pizza, SizeDTO size) {
        Connection con = getConnection();

        int result = repository.insertPizza(con, pizza, size);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);

        return result > 0;
    }

    public void increaseQuantity(String pizzaName, int amount) {
        Connection con = getConnection();

        int result = repository.increaseQuantity(con, pizzaName, amount);

        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
    }

    public void decreaseQuantity(String pizzaName, int amount) {
        Connection con = getConnection();
        try {
            int result = repository.decreaseQuantity(con, pizzaName, amount);
            if (result > 0) {
                commit(con);
            } else {
                rollback(con);
            }
        } finally {
            close(con);
        }
    }

    public void deletePizza(String pizzaName) {

        Connection con = getConnection();
        try {
            // 번호 조회용
            int pizzaId = repository.selectDeletePizzaId(con, pizzaName);

            // 조회된번호로 다시 삭제 요청
            int result = 0;
            if(pizzaId > 0)
                result = repository.deletePizza(con, pizzaId, pizzaName);

            if (result > 0) {
                commit(con);
                System.out.println("✅ 피자가 품절 처리되었습니다.");
            } else {
                rollback(con);
                System.out.println("❌ 품절 처리에 실패했습니다.");
            }
        } finally {
            close(con);
        }

    }
}
