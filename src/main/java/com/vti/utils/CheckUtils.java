package com.vti.utils;

public class CheckUtils {
    public static boolean checkEmailValidate(String email){
        if(email.contains("@")){
            return true;
        }

        return false;
    }

    public static boolean checkPasswordValidate(String password){
        if(password.length() < 6 || password.length() > 12){
            return false;
        }

        for(int i = 0; i < password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public static void viewAdminMenu(){
        System.out.println("\n---- Xin Chào Admin ----");
        System.out.println("1 - Thêm mới nhân viên");
        System.out.println("2 - Xem danh sách nhân viên");
        System.out.println("3 - Thoát khỏi chương trình");
    }

    public static void viewEmployeeMenu(){
        System.out.println("\n---- Xin Chào Employee ----");
        System.out.println("2 - Xem danh sách nhân viên");
        System.out.println("3 - Thoát khỏi chương trình");
    }

}
