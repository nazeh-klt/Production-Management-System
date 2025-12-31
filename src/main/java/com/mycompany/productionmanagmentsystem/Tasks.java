/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

/**
 *
 * @author samid
 */
public class Tasks {
    int ID;
    Product product;
    int quantity, achievedQuantity;
    String clientName;
    Date startDate;
    Date deadlineDate;
    String status;

    public Tasks(int ID, Product product, int quantity, int achievedQuantity, Date startDate, Date deadlineDate, String clientName, String status) {
        this.ID = ID;
        this.product = product;
        this.quantity = quantity;
        this.achievedQuantity = achievedQuantity;
        this.clientName = clientName;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.status = status;
    }
    
    public double get_achieved_percentage(){
        return (double)achievedQuantity/quantity*100;
    }
    
    
}
