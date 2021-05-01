package ceng.estu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reuzun
 */
public class User {

    public static User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Shoe> getBasket() {
        return basket;
    }

    public void setBasket(List<Shoe> basket) {
        this.basket = basket;
    }

    private String username;
    private UserType type;
    private String name;
    private String surname;
    private String email;
    private List<Shoe> basket;

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getPhoneNos() {
        return phoneNos;
    }

    public void setPhoneNos(List<String> phoneNos) {
        this.phoneNos = phoneNos;
    }

    private List<String> addresses;
    private List<String> phoneNos;

    private User(String username, UserType type, String name, String surname, String email, List<String> addresses, List<String> phoneNos) {
        this.username = username;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.basket = new ArrayList<>();
        this.addresses = addresses;
        this.phoneNos = phoneNos;

        user = this;
    }

    public static void setUser(String username, UserType type, String name, String surname, String email, List<String> addresses, List<String> phoneNos) {
        user = new User(username, type, name, surname, email, addresses, phoneNos);
        //return user;
    }

}
