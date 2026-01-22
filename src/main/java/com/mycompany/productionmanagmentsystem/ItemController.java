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

    public static void add(String category, String name, int price, int available_amount, int least_allowed_amount) {
        items.add(new Item(category,name,price,available_amount,least_allowed_amount));
    }

    public static void remove(int id) {
        items.remove(find_by_id(id));
    }

    public static void load_from_file() {
        try (BufferedReader ItemReader = new BufferedReader(new FileReader("items.csv"));) {
            String line;
            ItemReader.readLine();
            while ((line = ItemReader.readLine())!= null) {
                String[] attributes = line.split(",");
                items.add(new Item(Integer.parseInt(attributes[0]),attributes[1],attributes[2],Integer.parseInt(attributes[3]),Integer.parseInt(attributes[4]),Integer.parseInt(attributes[5])));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void save_to_file() {
        try (BufferedWriter ItemWriter = new BufferedWriter(new FileWriter("items.csv"));) {
            ItemWriter.write("id, name, category, price, available_amount, least_allowed_amount");
            for (Item item : items) {
                ItemWriter.write("\n" + item.id + "," + item.name +","+ item.category + "," + item.price + "," + item.available_amount + "," + item.least_allowed_amount);
            }
        } catch (Exception e) {

        }
    }

    public static Item find_by_id(int id) {
        for(Item item: items){
            if (id==item.id){
                return item;
            }
        }
        return null;

    }
    public static Set<Item> filter_by_category(String category){
        Set<Item> found_items = new HashSet<>();
        for(Item item: items){
            if (item.category==category){
                found_items.add(item);
            }  
        }
        return found_items;
    }
}
