/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import javax.swing.*;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samid
 */
public class Tasks implements Runnable {

    int ID;
    Product product;
    int requiredQuantity, achievedQuantity;
    String clientName;
    Date startDate;
    Date deadlineDate;
    String status;

    public Tasks(int ID, Product product, int requiredQuantity, int achievedQuantity, Date startDate, Date deadlineDate, String clientName, String status) {
        this.ID = ID;
        this.product = product;
        this.requiredQuantity = requiredQuantity;
        this.achievedQuantity = achievedQuantity;
        this.clientName = clientName;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.status = status;
    }

    public double get_achieved_percentage() {
        return (double) achievedQuantity / requiredQuantity * 100;
    }

    @Override
    public void run() {
        int currentItemAmount;
        Scanner scan = new Scanner(System.in);
        while (achievedQuantity < requiredQuantity) {
            for (Map.Entry<Item, Integer> i : product.required_items.entrySet()) {
                currentItemAmount = 0;
                while (currentItemAmount < i.getValue()) {
                    i.getKey().available_amount--;
                    currentItemAmount++;
                    if (i.getKey().available_amount == i.getKey().least_allowed_amount) {
                        System.out.println("WARNING!! The least threshold is being crossed");
                    }
                    if (i.getKey().available_amount == 0) {
                        System.out.println(i.getKey().name + " current amount is " + i.getKey().available_amount);
                        break;
                    }
                }
                if (i.getKey().available_amount == 0) {
                        Item item = i.getKey();
                        String[] options = {"Abort Task", "Wait"};
                        int choice = JOptionPane.showOptionDialog(
                                null,
                                "Item \"" + item.name + "\" is out of stock.\nWhat do you want to do?",
                                "Out of Stock",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null,
                                options,
                                options[0]
                        );

                        if (choice == 0) {
                            return;
                        } else if (choice == 1) {
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                }
            achievedQuantity++;
        }
        scan.close();
    }
}
