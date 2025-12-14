/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;
import java.util.*;
/**
 *
 * @author USER
 */
public interface controller {
    public void add(Object object);
    public void remove(Object object);
    public void load_to_file(Set set);
    public void save_to_file(Set set);
    public void find_by_id(int id);
}
