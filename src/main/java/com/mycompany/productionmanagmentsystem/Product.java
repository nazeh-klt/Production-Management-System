/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Product {

    static int last_id = 0;
    int id;
    String name;
    HashMap<Item, Integer> required_items = new HashMap<>();

    Product(int id, String name) {
        this.id = id;
        last_id = Math.max(id, last_id);
        this.name = name;
    }

    Product(String name) {
        this.id = last_id;
        last_id++;
        this.name = name;

    }

    public void add(int id, int min_req) {
        required_items.put(ItemController.find_by_id(id), min_req);
    }

    public void add_full_hash(HashMap<Item, Integer> m) {
        required_items = m;
    }
    
    public HashMap<Item, Integer> getRequiredItems(){
        return required_items;
    }
}
