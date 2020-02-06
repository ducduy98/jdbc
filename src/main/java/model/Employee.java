package model;

import annotation.Entity;

@Entity(name = "NhanVien")
public class Employee {
    private String id;
    private String name;
    private long Salary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return Salary;
    }

    public void setSalary(long salary) {
        Salary = salary;
    }
}
