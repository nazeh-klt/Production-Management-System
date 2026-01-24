/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import static com.mycompany.productionmanagmentsystem.User.users;
import java.io.BufferedWriter;
import java.io.FileWriter;


/**
 *
 * @author Admin
 */
public class Logger {
    
        public static void save_to_file(String Error) {
        try (BufferedWriter UserWriter = new BufferedWriter(new FileWriter("users.csv"));) {
                UserWriter.write(Error);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

