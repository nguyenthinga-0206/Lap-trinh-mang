package model;

public class User {
    //    CSDL:
//    CREATE TABLE `data`.`test1` (
//            `id` int auto_increment,
//             `roles` int not null,
//            `username` nvarchar(255) not null,
//            `password` nvarchar(255) not null,
//            `firsname` nvarchar(255),
//            `lastname` nvarchar(255),
//             PRIMARY KEY (`id`)
//);
    protected int id;
    protected int roles;
    protected String username;
    protected String password;
    protected String firsname;
    protected String lastname;

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(int id, int roles, String username, String password, String firsname, String lastname) {
        super();
        this.id = id;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.firsname = firsname;
        this.lastname = lastname;
    }

    public User(int roles, String username, String password, String firsname, String lastname) {
        super();
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.firsname = firsname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
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

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
