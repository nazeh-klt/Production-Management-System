/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

/**
 *
 * @author Admin
 */
public class Item {

    static int last_id = 0;
    int id;
    String name;
    String category;
    int price;
    int available_amount;
    int least_allowed_amount;

    Item(int id, String name, String category, int price, int available_amount, int least_allowed_amount) {
        this.id = id;
        this.name = name;
        last_id = Math.max(id, last_id);
        this.category = category;
        this.price = price;
        this.available_amount = available_amount;
        this.least_allowed_amount = least_allowed_amount;
    }

    Item(String name, String category, int price, int available_amount, int least_allowed_amount) {
        this.id = last_id;
        last_id++;
        this.name = name;
        this.category = category;
        this.price = price;
        this.available_amount = available_amount;
        this.least_allowed_amount = least_allowed_amount;
    }

}
