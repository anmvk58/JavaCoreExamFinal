package com.vti.frontend;

import com.vti.backend.controller.UserController;

import java.sql.SQLException;
import java.util.Scanner;

public class ExamProgram {
    public static void main(String[] args) throws SQLException {
        UserController controller = new UserController();
        controller.printListUser();
//        controller.printUserById(2);

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Mời nhập id muốn xóa !");
//        int input = scanner.nextInt();
//        controller.deleteUserById(input);
//
//        controller.printListUser();

        System.out.println("Mời nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Mời nhập mật khẩu: ");
        String password = scanner.nextLine();

        controller.login(email, password);


    }
}
