package com.vti.backend.service;

import com.vti.backend.repository.UserRepository;
import com.vti.database.Admin;
import com.vti.database.Employee;
import com.vti.database.User;
import com.vti.utils.CheckUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private UserRepository repository;
    private Scanner scanner;

    public UserService() {
        repository = new UserRepository();
        scanner = new Scanner(System.in);
    }

    public void printListUser() throws SQLException {
        List<User> list = repository.getListUser();

        System.out.println("Danh Sách User: ");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%s\t | %25s | %25s\n", "id", " full_name", "email");
        for (User u : list){
            System.out.printf("%d\t | %25s | %25s", u.getId(), u.getFullName(), u.getEmail());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------\n");
    }

    public void printUserById(int id) throws SQLException {
        User user = repository.getUserById(id);
        if(user instanceof Admin){
            System.out.println("User này là Admin: ");
            Admin admin = (Admin) user;
            System.out.println(admin);
        } else {
            System.out.println("User này là Employee: ");
            Employee employee = (Employee) user;
            System.out.println(employee);
         }
    }

    public void deleteUserById(int id) throws SQLException {
        repository.deleteUserById(id);
    }

    public void login(String email, String password) throws SQLException {
        if(repository.login(email, password)){
            userAction(repository.getUserTypeByEmail(email));
        }
    }

    public void createEmployee() throws SQLException {
        System.out.println("Mời nhập fullname: ");
        scanner.nextLine();
        String fullName = scanner.nextLine();
        System.out.println("Mời nhập email: ");
        String email = scanner.nextLine();

        if(repository.createEmployee(fullName, email)){
            System.out.println("Thêm mới thành công !");
        } else {
            System.out.println("Thêm mới không thành công");
        }
    }

    public void userAction(String userType) throws SQLException {
        if(userType.equals("Admin")){
            while (true){
                CheckUtils.viewAdminMenu();
                System.out.println("Mời nhập lựa chọn: ");
                int choose = scanner.nextInt();
                switch (choose){
                    case 1:
                        //gọi hàm thêm nhân viên:
                        createEmployee();
                        break;
                    case 2:
                        printListUser();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Lựa chọn từ 1 - 3 only");
                }
            }

        } else {
            while (true) {
                CheckUtils.viewEmployeeMenu();
                System.out.println("Mời nhập lựa chọn: ");
                int choose = scanner.nextInt();
                switch (choose){
                    case 2:
                        printListUser();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Lựa chọn từ 2 - 3 only");
                }
            }

        }
    }
}

