/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author samid
 */
public class ProductionLineController {
    static Set<ProductionLine> lines = new HashSet<>();
    public void addProductionLine(int ID, String lineName, String lineState){
        lines.add(new ProductionLine(ID, lineName, lineState));
    }
    public void removeProductionLine(int ID){
        //lines.removeIf(p -> p.ID == (ID) && p.lineName.equals(lineName) && p.lineState.equals(lineState));
        lines.remove(find_by_ID(ID));
    }
    public void load_from_file(){
        BufferedReader br = null;
        String line = "";
        try{
            br = new BufferedReader(new FileReader("production lines.csv"));
            while((line = br.readLine()) != null){
                String[] rows = line.split(",");
                lines.add(new ProductionLine(Integer.parseInt(rows[0]), rows[1], rows[2]));
            }
        } catch(FileNotFoundException | ArrayIndexOutOfBoundsException | NullPointerException | SecurityException e){
            e.getMessage();
        } catch(IOException e){
            e.getMessage();
        } finally{
            try{
                br.close();
            } catch(IOException e){
                e.getMessage();
            }
        }
    }
    public void save_file(){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter("production lines.csv"));
            for(ProductionLine p: lines){
                bw.write("/n" + p.ID + "," + p.lineName + "," + p.lineState);
            }
        } catch(IOException e){
            e.getMessage();
        } finally{
            try{
                bw.close();
            } catch(IOException e){
                e.getMessage();
            }
        }
    }
    public ProductionLine find_by_ID(int ID){
        for(ProductionLine p: lines){
            if(p.ID == ID){
                return p;
            }
        }
        return null;
    }
    public ProductionLine find_by_lineName(int lineName){
       for(ProductionLine p: lines){
           if(p.lineName.equals(lineName)){
               return p;
            }
        }
       return null;
    }
    public ProductionLine find_by_lineState(int lineState){
       for(ProductionLine p: lines){
           if(p.lineState.equals(lineState)){
               return p;
            }
        }
        return null;
    } 
    public void add_tasks(int ID, String lineName, String lineState){
        for(ProductionLine p: lines){
           if(p.ID == ID && p.lineName.equals(lineName) && p.lineState.equals(lineState)){
               p.lineTasks.add(new Tasks());
            }
        }
    }
       
}
