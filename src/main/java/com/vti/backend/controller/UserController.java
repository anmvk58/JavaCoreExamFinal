package com.vti.backend.controller;

import com.vti.backend.service.UserService;
import com.vti.utils.CheckUtils;

import java.sql.SQLException;

public class UserController {
    private UserService service;

    public UserController() {
        service = new UserService();
    }

    public void printListUser() throws SQLException {
        service.printListUser();
    }

    public void printUserById(int id) throws SQLException {
        service.printUserById(id);
    }

    public void deleteUserById(int id) throws SQLException {
        service.deleteUserById(id);
    }

    public void login(String email, String password) throws SQLException {
        //validate xem email và password đã hợp lệ chưa
        // check email
        if(CheckUtils.checkEmailValidate(email) && CheckUtils.checkPasswordValidate(password)){
            service.login(email, password);
        } else {
            System.out.println("Email hoặc passWord không hợp lệ !");
        }
        // Nếu hợp lệ rồi
        // gọi hàm login của Service
    }
}
