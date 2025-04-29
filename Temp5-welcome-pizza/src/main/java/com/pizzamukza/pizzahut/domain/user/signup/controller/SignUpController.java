package com.pizzamukza.pizzahut.domain.user.signup.controller;

import com.pizzamukza.pizzahut.domain.user.signup.dto.SignUpDTO;
import com.pizzamukza.pizzahut.domain.user.signup.service.SignUpService;

import java.util.List;

public class SignUpController {
    private SignUpService service = new SignUpService();

    public void register(String username, String pw, String name) {
        SignUpDTO user = new SignUpDTO(username, pw, name);
        service.registerUser(user);
    }

    public boolean login(String username, String pw) {
        return service.authenticate(username, pw);
    }

    public void changePassword(String username, String newPw) {
        service.updatePassword(username, newPw);
    }


    public void deleteUserById(int memberId) {
        service.deleteUserbyId(memberId);
    }
    public List<SignUpDTO> getAllUsers() {
        return service.getAllUsers();
    }

}
