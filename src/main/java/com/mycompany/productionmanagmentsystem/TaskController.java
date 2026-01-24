/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author samid
 */
public class TaskController extends Thread{
   static Set<Tasks> tasks = new HashSet<>();
   public static void addTask(int ID, Product product, int quantity, int achievedQuantity, Date startDate, Date deadlineDate, String clientName, String status){
       tasks.add(new Tasks(ID, product, quantity, achievedQuantity, startDate, deadlineDate, clientName, status));
    }
    
   public static void runTask(Tasks t){
       
   }
   @Override
   public void run(){
       
   }
   
}
