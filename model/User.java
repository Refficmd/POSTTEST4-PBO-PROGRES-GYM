package model;

public class User extends Orang implements Printable {
    private String password;
    private String role; // ADMIN atau USER

    public User(String username, String password, String role) {
        super(username);
        this.password = password;
        this.role = role.toUpperCase();
    }

    public String getUsername() { return name; } // pakai field dari Person
    public String getPassword() { return password; }
    public String getRole() { return role; }

    @Override
    public void introduce() {
        System.out.println("Halo, saya user dengan username: " + name + " (role: " + role + ")");
    }

    @Override
    public void printData() {
        System.out.println("User{username='" + name + "', role='" + role + "'}");
    }
}
