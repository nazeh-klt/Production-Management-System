/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.productionmanagmentsystem;

import java.util.*;
import java.io.*;
/**
 *
 * @author USER
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductionManagmentSystem {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        User.load_from_file();
        ItemController.load_from_file();
        ProductController.load_from_file();
        ProductionLineController.load_from_file();
        
        DailySave daily_save = new DailySave();
        daily_save.start();
        
        LogIn l = new LogIn();
    }
}
