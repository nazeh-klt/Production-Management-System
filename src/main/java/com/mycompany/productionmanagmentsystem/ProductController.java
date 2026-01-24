/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import java.util.*;
import java.io.*;

/**
 *
 * @author Admin
 */
public class ProductController {

    static HashMap<Product, Integer> products = new HashMap<>();

    public static void add(String name) {
        Boolean is_inside = false;
        Product current_product = null;
        for (Product product : products.keySet()) {
            if (product.name == name) {
                is_inside = true;
                current_product = product;
            }
        }

        int current_value;
        if (is_inside) {
            current_value = products.get(current_product);
            products.put(current_product, current_value + 1);
        } else {
            products.put(new Product(name), 0);
        }
    }

    public static void add(int id, String name) {
        Boolean is_inside = false;
        Product current_product = null;
        for (Product product : products.keySet()) {
            if (product.name == name) {
                is_inside = true;
                current_product = product;
            }
        }
        int current_value;
        if (is_inside) {
            current_value = products.get(current_product);
            products.put(current_product, current_value + 1);
        } else {
            products.put(new Product(id, name), 0);
        }
    }

    public static void remove(int id) {
        products.remove(find_by_id(id));
    }

public static void load_from_file() {
    try (BufferedReader ProductReader = new BufferedReader(new FileReader("products.csv"))) {
        String line;
        ProductReader.readLine();
        while ((line = ProductReader.readLine()) != null) {
            String[] attributes = line.split(",");
            add(Integer.parseInt(attributes[0]), attributes[1]);
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    try (BufferedReader ProductItemsReader = new BufferedReader(new FileReader("productitems.txt"))) {
        String line;
        ProductItemsReader.readLine();
        while ((line = ProductItemsReader.readLine()) != null) {
            String[] attributes = line.split(",");
            
            if (attributes.length < 3) {
                System.out.println("Warning: Skipping invalid line: " + line);
                continue;
            }
            
            int productId = Integer.parseInt(attributes[0].trim());
            int itemId = Integer.parseInt(attributes[1].trim());
            int itemAmount = Integer.parseInt(attributes[2].trim());
            
            Product targetProduct = null;
            for (Product product : products.keySet()) {
                if (product.id == productId) {
                    targetProduct = product;
                    break;
                }
            }
            
            if (targetProduct == null) {
                System.out.println("Warning: Product ID " + productId + " not found");
                continue;
            }
            
            Item targetItem = ItemController.find_by_id(itemId);
            
            if (targetItem == null) {
                System.out.println("Warning: Item ID " + itemId + " not found");
                continue;
            }
            
            targetProduct.required_items.put(targetItem, itemAmount);
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    public static void save_to_file() {
        try (BufferedWriter ProductWriter = new BufferedWriter(new FileWriter("products.csv"));) {
            ProductWriter.write("id, name");
            for (Product product : products.keySet()) {
                ProductWriter.write("\n" + product.id + "," + product.name);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try(BufferedWriter ProductItemsWriter = new BufferedWriter(new FileWriter("productitems.txt"))){
            ProductItemsWriter.write("product id, item id, item amount");
            for (Product product : products.keySet()) {
                for(Item item : product.required_items.keySet())
                ProductItemsWriter.write("\n" + product.id + "," + item.id + "," + product.required_items.get(item));
                
            }
   
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Product find_by_id(int id) {
        for (Product product : products.keySet()) {
            if (id == product.id) {
                return product;
            }
        }
        return null;

    }

    public static HashMap<Product, Integer> filter_by_name(String name) {
        HashMap<Product, Integer> found_products = new HashMap<>();
        for (Product product : products.keySet()) {
            if (product.name == name) {
                found_products.put(product, products.get(product));
            }
        }
        return found_products;
    }
}
