/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

/**
 *
 * @author Admin
 */
public class Product {

    static int last_id = 0;
    int id;
    String name;

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

}
