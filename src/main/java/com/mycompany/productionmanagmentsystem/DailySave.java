/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

/**
 *
 * @author USER
 */
public class DailySave extends Thread {
    @Override
    public void run(){
        User.save_to_file();
        ItemController.save_to_file();
        ProductController.save_to_file();
        ProductionLineController.save_file();
        try {
            Thread.sleep(24 * 60 * 60 * 1000L);
        } catch (InterruptedException ex) {
            Logger.save_to_file(ex.getMessage());
        }
    }
}
