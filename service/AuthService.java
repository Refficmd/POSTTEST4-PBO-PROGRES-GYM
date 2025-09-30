package service;

import model.User;
import java.util.ArrayList;
import java.util.Optional;

public class AuthService {
    private final ArrayList<User> users = new ArrayList<>();

    public AuthService() {
        users.add(new User("admin", "admin123", "ADMIN"));
        users.add(new User("james", "user123", "USER"));
        users.add(new User("Reffi", "reffi123", "USER")); 
    }

    public Optional<User> login(String username, String password, String expectedRole) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword().equals(password)
                    && u.getRole().equalsIgnoreCase(expectedRole)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public boolean register(String username, String password) {
        Validator.require(Validator.notBlank(username), "Username tidak boleh kosong.");
        Validator.require(Validator.notBlank(password), "Password tidak boleh kosong.");
        boolean exists = users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
        Validator.require(!exists, "Username sudah dipakai.");
        users.add(new User(username, password, "USER"));
        return true;
    }
}
