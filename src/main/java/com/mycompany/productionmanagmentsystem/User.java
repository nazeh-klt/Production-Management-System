/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    public static void save_to_file() {
        try (BufferedWriter UserWriter = new BufferedWriter(new FileWriter("users.csv"));) {
            UserWriter.write("role, name, password");
            for (User user : users) {
                UserWriter.write("\n" + user.role + "," + user.name + "," + user.password);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
        public static void load_from_file() {
        try (BufferedReader UserReader = new BufferedReader(new FileReader("users.csv"));) {
            String line;
            UserReader.readLine();
            while ((line = UserReader.readLine()) != null) {
                String[] attributes = line.split(",");
                add(Integer.parseInt(attributes[0]), attributes[1], attributes[2]);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}


//0 = Manager
//1 = Supervisor
