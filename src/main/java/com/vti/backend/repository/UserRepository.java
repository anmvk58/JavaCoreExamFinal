package com.vti.backend.repository;

import com.vti.database.Admin;
import com.vti.database.Employee;
import com.vti.database.User;
import com.vti.utils.CheckUtils;
import com.vti.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {
    private ConnectionUtils connectionUtils;

    public UserRepository() {
        connectionUtils = new ConnectionUtils();
    }

    public List<User> getListUser() throws SQLException {
        Connection connection = connectionUtils.getConnection();

        Statement statement = connection.createStatement();

        // Querry và hứng kết quả vào ResultSet
        ResultSet resultSet = statement.executeQuery("select id, full_name, email from users");

        List<User> list = new ArrayList<>();

        while (resultSet.next()){
            User userTemp = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );

            list.add(userTemp);
        }

        return list;
    }

    public User getUserById(int id) throws SQLException {
        Connection connection = connectionUtils.getConnection();

        PreparedStatement statement = connection.prepareStatement("select * from users where id = ?");

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            if(resultSet.getString(7).equals("Admin")){
                //khoi tao Admin
                Admin admin = new Admin(resultSet.getInt("id"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getInt("exp_in_year")
                        );
                return admin;
            } else {
                // khoi tao Employee
                Employee employee = new Employee(resultSet.getInt("id"),
                                        resultSet.getString("full_name"),
                                        resultSet.getString("email"),
                                        resultSet.getString("password"),
                                        resultSet.getString("pro_skill")
                );
                return employee;
            }
        } else {
            return null;
        }
    }

    public void deleteUserById(int id) throws SQLException {
        //Check xem user có tồn tại không ?
        if(getUserById(id) != null){
            // có tồn tại
            Connection connection = connectionUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");

            statement.setInt(1, id);

            int effRows = statement.executeUpdate();
            System.out.println("Đã xóa " + effRows + " bản ghi !");
        } else {
            System.out.println("Không tồn tại User tương ứng với ID");
        }
    }

    public boolean login(String email, String password) throws SQLException {
        Connection connection = connectionUtils.getConnection();

        PreparedStatement statement = connection.prepareStatement("select password from users where email = ?");

        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            if(resultSet.getString(1).equals(password)){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String getUserTypeByEmail(String email) throws SQLException {
        Connection connection = connectionUtils.getConnection();

        PreparedStatement statement = connection.prepareStatement("select user_type from users where email = ?");

        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        String result = null;
        if(resultSet.next()){
             result = resultSet.getString(1);
        }
        return result;
    }

    public boolean createEmployee(String fullName, String email) throws SQLException {
        Connection connection = connectionUtils.getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into users (full_name, email, password, user_type) values (? , ?, '123456', 'Employee')");

        statement.setString(1, fullName);
        statement.setString(2, email);

        int effectedRow = statement.executeUpdate();
        if(effectedRow == 1){
            return true;
        }
        return false;
    }

}
