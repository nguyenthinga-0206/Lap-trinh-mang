package model;

public class Department {
    private int id;
    private String name;



    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(int id, String nameDepartment) {
        this.id = id;
        this.name = nameDepartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
