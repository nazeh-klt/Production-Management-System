/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.util.ArrayList;

/**
 *
 * @author samid
 */
public class ProductionLine {
    int ID;
    String lineName;
    String lineState;
    ArrayList<Tasks> lineTasks;

    public ProductionLine(int ID, String lineName, String lineState) {
        this.ID = ID;
        this.lineName = lineName;
        this.lineState = lineState;
    }
    
}
