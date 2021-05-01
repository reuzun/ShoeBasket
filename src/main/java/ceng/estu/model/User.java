package ceng.estu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reuzun
 */
public class User {

    public static User user;

    String username;
    UserType type;
    String name;
    String surname;
    String email;
    List<Shoe> basket;

    public User(String username, UserType type, String name, String surname, String email) {
        this.username = username;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = new ArrayList<>();

        user = this;
    }
}
