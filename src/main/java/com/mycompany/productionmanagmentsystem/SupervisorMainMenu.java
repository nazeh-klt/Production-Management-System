package com.mycompany.productionmanagmentsystem;

import javax.swing.*;

public class SupervisorMainMenu extends JFrame {

    public SupervisorMainMenu() {

        setTitle("Supervisor Panel");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel title = new JLabel("Select Management Section");
        title.setBounds(150, 30, 250, 30);
        add(title);

        JButton inventoryBtn = new JButton("Inventory Management");
        inventoryBtn.setBounds(150, 90, 200, 40);
        add(inventoryBtn);

        JButton tasksBtn = new JButton("Tasks Management");
        tasksBtn.setBounds(150, 150, 200, 40);
        add(tasksBtn);

        JButton productsBtn = new JButton("Products Management");
        productsBtn.setBounds(150, 210, 200, 40);
        add(productsBtn);

        inventoryBtn.addActionListener(e -> {
            dispose();
            new ManageItems();
        });

        tasksBtn.addActionListener(e -> {
            dispose();
            new ManageTasks();
        });

        productsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Products Management not implemented yet",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        setVisible(true);
    }
}
