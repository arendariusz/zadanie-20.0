package com.example.zadanie20;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public UserRepository() {
        userList.add(new User("Andrzej", "Małolepszy", 41));
        userList.add(new User("Lucyna", "Szczupła", 21));
        userList.add(new User("Darek", "Zegarek", 30));
    }

    public List<User> getAll() {
        return userList;
    }

    public void add(User user) {
        userList.add(user);
    }
}
