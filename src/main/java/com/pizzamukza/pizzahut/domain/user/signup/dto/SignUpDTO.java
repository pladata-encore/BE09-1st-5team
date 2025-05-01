package com.pizzamukza.pizzahut.domain.user.signup.dto;
public class SignUpDTO {
    private String memberId;
    private String username;
    private String password;
    private String name;

    public SignUpDTO() {
    }

    public SignUpDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public SignUpDTO(String memberId, String name, String password, String username) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "memberId='" + memberId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}