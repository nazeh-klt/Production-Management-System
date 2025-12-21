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

    static Set<Item> Items;

    public void add(Item item) {
        Items.add(item);
    }

    public void remove(Item item) {
        Items.remove(item);
    }

    public void load_to_file(Set l_items) {
        try (BufferedReader ItemReader = new BufferedReader(new FileReader("items.csv"));) {
            String line;
            while ((line = ItemReader.readLine())!= null) {
                String[] attributes = line.split(",");
                Item i = new Item(Integer.parseInt(attributes[0]),attributes[1],Integer.parseInt(attributes[2]),Integer.parseInt(attributes[3]),Integer.parseInt(attributes[4]));
            }


        } catch (Exception e) {

        }

    }

    public void save_to_file(Set s_items) {
        try (BufferedWriter ItemWriter = new BufferedWriter(new FileWriter("items.csv"));) {
            ItemWriter.write("id, category, price, available_amount, least_allowed_amount");
            for (Item item : Items) {
                ItemWriter.write('\n' + item.id + "" + item.category + "" + item.price + "" + "" + item.available_amount + "" + item.least_allowed_amount);
            }
        } catch (Exception e) {
            System.out.println("1");
        }
    }

    public void find_by_id(int id) {
    }

}
