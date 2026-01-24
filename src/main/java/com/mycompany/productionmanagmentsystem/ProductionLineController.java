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
public class ProductionLineController{
    static Set<ProductionLine> lines = new HashSet<>();
    public static void addProductionLine(int ID, String lineName, String lineState){
        lines.add(new ProductionLine(ID, lineName, lineState));
    }
    
    public static void removeProductionLine(int ID){
        //lines.removeIf(p -> p.ID == (ID) && p.lineName.equals(lineName) && p.lineState.equals(lineState));
        lines.remove(find_by_ID(ID));
    }
    
    public static void load_from_file(){
        try(BufferedReader br = new BufferedReader(new FileReader("production_lines.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] rows = line.split(",");
                lines.add(new ProductionLine(Integer.parseInt(rows[0]), rows[1], rows[2]));
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        try(BufferedReader br = new BufferedReader(new FileReader("tasks.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] rows = line.split(",");
                add_tasks(
                        Integer.parseInt(rows[0]),
                        Integer.parseInt(rows[1]),
                        ProductController.find_by_id(Integer.parseInt(rows[2])),
                        Integer.parseInt(rows[3]),
                        Integer.parseInt(rows[4]),
                        new Date(Integer.parseInt(rows[7]), Integer.parseInt(rows[8]), Integer.parseInt(rows[9])),
                        new Date(Integer.parseInt(rows[10]), Integer.parseInt(rows[11]), Integer.parseInt(rows[12])),
                        rows[5],
                        rows[6]          
                );
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void save_file(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("production_lines.csv"));){
            for(ProductionLine p: lines){
                bw.write(p.ID + "," + p.lineName + "," + p.lineState);
                bw.write('\n');
            }
        } catch(IOException e){
            e.getMessage();
        } 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.csv"));){
            for(ProductionLine p: lines){
                for(var t:p.lineTasks){
                    bw.write(p.ID + "," + t.ID +","+ t.product.id +","+ t.requiredQuantity+","+ t.achievedQuantity+","+ t.clientName+","+ t.status+","+t.startDate.day+","+t.startDate.month+","+t.startDate.year+","+t.deadlineDate.day+","+t.deadlineDate.month+","+t.deadlineDate.year);
                    bw.write('\n');
                }
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
    
    public static HashSet<Tasks> tasks_by_line(int ID){
        HashSet<Tasks> lineTasks = new HashSet<>();
        for(Tasks t: find_by_ID(ID).lineTasks){
            lineTasks.add(t);
        }
        return lineTasks;
    }
    
    public static void add_tasks(int lineID, int ID, Product product, int quantity, int achievedQuantity, Date startDate, Date deadlineDate, String clientName, String status){      
      find_by_ID(lineID).lineTasks.add(new Tasks(ID, product, quantity, achievedQuantity, startDate, deadlineDate, clientName,status));
    }   
    
    public static void add_tasks(int lineID, Product product, int quantity, int achievedQuantity, Date startDate, Date deadlineDate, String clientName, String status){      
      find_by_ID(lineID).lineTasks.add(new Tasks(product, quantity, achievedQuantity, startDate, deadlineDate, clientName,status));
    }   
    
    public static HashSet<Product> get_products_by_line(int ID){
        HashSet products = new HashSet<>();
        for(Tasks t: find_by_ID(ID).lineTasks){
            products.add(t.product);
        }
        return products;
    }
    
    public static HashSet<Product> get_all_Products(){
        HashSet products = new HashSet<>();
        for(ProductionLine pr: lines){
            products.add(get_products_by_line(pr.ID));
        }
        return products;
    }
    
    public static Tasks get_task_by_id(int ID, int taskID){
        for(Tasks t: tasks_by_line(ID)){
            if(t.ID == taskID){
                return t;
            }
        }
        return null;
    }
    
    public static void startTask(int ID, int taskID){
        Tasks task = get_task_by_id(ID, taskID);
        Thread th = new Thread(task);
        th.start();
    }
}
