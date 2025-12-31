/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.io.*;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author samid
 */
public class ProductionLineController {
    static Set<ProductionLine> lines = new HashSet<>();
    public static void addProductionLine(int ID, String lineName, String lineState){
        lines.add(new ProductionLine(ID, lineName, lineState));
    }
    public static void removeProductionLine(int ID){
        //lines.removeIf(p -> p.ID == (ID) && p.lineName.equals(lineName) && p.lineState.equals(lineState));
        lines.remove(find_by_ID(ID));
    }
    public static void load_from_file(){
        try(BufferedReader br = new BufferedReader(new FileReader("productionlines.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] rows = line.split(",");
                lines.add(new ProductionLine(Integer.parseInt(rows[0]), rows[1], rows[2]));
            }
        } catch(FileNotFoundException | ArrayIndexOutOfBoundsException | NullPointerException | SecurityException e){
            e.getMessage();
        } catch(IOException e){
            e.getMessage();
        }
    }
    public static void save_file(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("production lines.csv"));){
            for(ProductionLine p: lines){
                bw.write("/n" + p.ID + "," + p.lineName + "," + p.lineState);
            }
        } catch(IOException e){
            e.getMessage();
        } 
    }
    public static ProductionLine find_by_ID(int ID){
        for(ProductionLine p: lines){
            if(p.ID == ID){
                return p;
            }
        }
        return null;
    }
    public static ProductionLine find_by_lineName(int lineName){
       for(ProductionLine p: lines){
           if(p.lineName.equals(lineName)){
               return p;
            }
        }
       return null;
    }
    public static ProductionLine find_by_lineState(int lineState){
       for(ProductionLine p: lines){
           if(p.lineState.equals(lineState)){
               return p;
            }
        }
        return null;
    } 
    public static void add_tasks(int lineID, int ID, Product product, int quantity, int achievedQuantity, Date startDate, Date deadlineDate, String clientName, String status){      
      find_by_ID(ID).lineTasks.add(new Tasks(ID, product, quantity, achievedQuantity, startDate, deadlineDate, clientName,status));
    }
       
}
