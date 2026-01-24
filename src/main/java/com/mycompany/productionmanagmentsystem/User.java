/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class User {

    static Set<User> users = new HashSet<>();

    public static void add(int role, String name, String password) {
        users.add(new User(role, name, password));
    }

    int role;
    String name;
    String password;

    User(int role, String name, String password) {
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public static User login(String name, String password) {
        for (User user : users) {
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }
}

//0 = Manager
//1 = Supervisor
