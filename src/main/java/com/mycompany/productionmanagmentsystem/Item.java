/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

/**
 *
 * @author Admin
 */

public class Item{
    int id;
    String category;
    int price;
    int available_amount;
    int least_allowed_amount;
    Item(int id, String category, int price, int available_amount, int least_allowed_amount){
        this.id = id;
        this.category = category;
        this.price = price;
        this.available_amount = available_amount;
        this.least_allowed_amount = least_allowed_amount;
}

    
}
