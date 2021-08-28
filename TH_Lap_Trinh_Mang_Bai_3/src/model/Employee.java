package model;

import java.time.LocalDate;

public class Employee {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String position;
    private LocalDate birthday;
    private Department department;

    public Employee() {
    }

    public Employee(int id, String name, String address, String email, String phone, String position, LocalDate birthday, Department department) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.birthday = birthday;
        this.department = department;
    }

    public Employee(String name, String address, String email, String phone, String position, LocalDate birthday, Department department) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.birthday = birthday;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
