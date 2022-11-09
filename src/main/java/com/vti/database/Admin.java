package com.vti.database;

public class Admin extends User{
    private int expInYear;

    public Admin(int id, String fullName, String email, String password, int expInYear ) {
        super(id, fullName, email, password);
        this.expInYear = expInYear;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + super.getId() +
                ", fullName='" + super.getFullName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", expInYear=" + expInYear +
                '}';
    }
}
