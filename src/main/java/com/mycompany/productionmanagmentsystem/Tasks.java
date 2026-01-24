package com.mycompany.productionmanagmentsystem;

import java.util.Map;
import javax.swing.JOptionPane;

public class Tasks implements Runnable {

    int ID;
    static int last_id = -1;
    Product product;
    int requiredQuantity, achievedQuantity;
    String clientName;
    Date startDate;
    Date deadlineDate;
    String status;

    public Tasks(int ID,
                 Product product,
                 int requiredQuantity,
                 int achievedQuantity,
                 Date startDate,
                 Date deadlineDate,
                 String clientName,
                 String status) {

        this.ID = ID;
        this.product = product;
        this.requiredQuantity = requiredQuantity;
        this.achievedQuantity = achievedQuantity;
        this.clientName = clientName;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.status = status;
    }
    
    public Tasks(Product product,
                 int requiredQuantity,
                 int achievedQuantity,
                 Date startDate,
                 Date deadlineDate,
                 String clientName,
                 String status) {

        this.ID = ++last_id;
        this.product = product;
        this.requiredQuantity = requiredQuantity;
        this.achievedQuantity = achievedQuantity;
        this.clientName = clientName;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.status = status;
    }

    public double get_achieved_percentage() {
        if (requiredQuantity == 0) return 0;
        return (double) achievedQuantity / requiredQuantity * 100;
    }

    @Override
    public void run() {

        status = "RUNNING";

        while (achievedQuantity < requiredQuantity) {

            for (Map.Entry<Item, Integer> entry : product.required_items.entrySet()) {

                Item item = entry.getKey();
                int needed = entry.getValue();
                int used = 0;

                synchronized (item) {

                    while (used < needed) {

                        if (item.available_amount == 0) {
                            break;
                        }

                        item.available_amount--;
                        used++;

                        if (item.available_amount == item.least_allowed_amount) {
                            System.out.println(
                                "WARNING: " + item.name + " reached minimum threshold"
                            );
                        }
                    }
                }

                if (item.available_amount == 0) {

                    String[] options = {"Abort Task", "Wait"};
                    int choice = JOptionPane.showOptionDialog(
                            null,
                            "Item \"" + item.name + "\" is out of stock.\nWhat do you want to do?",
                            "Out of Stock",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    if (choice == 0) {
                        status = "ABORTED";
                        return;
                    } else if (choice == 1) {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }

            achievedQuantity++;
            
        }

        status = "COMPLETED";
    }
}