/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.util.*;
import java.io.*;

/**
 *
 * @author Admin
 */

public class ItemController {

    static Set<Item> items = new HashSet<>();

    public static void add(Item item) {
        items.add(item);
    }

    public static void remove(Item item) {
        items.remove(item);
    }

    public static void load_to_file() {
        try (BufferedReader ItemReader = new BufferedReader(new FileReader("items.csv"));) {
            String line;
            ItemReader.readLine();
            while ((line = ItemReader.readLine())!= null) {
                String[] attributes = line.split(",");
                ItemController.add(new Item(Integer.parseInt(attributes[0]),attributes[1],Integer.parseInt(attributes[2]),Integer.parseInt(attributes[3]),Integer.parseInt(attributes[4])));
                
            }


        } catch (Exception e) {

        }

    }

    public static void save_to_file() {
        try (BufferedWriter ItemWriter = new BufferedWriter(new FileWriter("items.csv"));) {
            ItemWriter.write("id, category, price, available_amount, least_allowed_amount");
            for (Item item : items) {
                ItemWriter.write("\n" + item.id + "," + item.category + "," + item.price + "," + item.available_amount + "," + item.least_allowed_amount);
            }
        } catch (Exception e) {

        }
    }

    public static void find_by_id(int id) {
    }

}
