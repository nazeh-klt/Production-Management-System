/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author USER
 */
public class ManageItems extends JFrame {

    ManageItems() {
        ManageItems frame = this;
        setTitle("Manage Items");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        setLayout(null);

        JButton add_item = new JButton("Add Item");
        add_item.setBounds(10, 10, 100, 30);
        this.add(add_item);

        JButton edit_b = new JButton("Edit Selected Item");
        edit_b.setBounds(10, 50, 150, 30);
        this.add(edit_b);

        JButton delete_b = new JButton("Delete Selected Item");
        delete_b.setBounds(10, 90, 150, 30);
        this.add(delete_b);

        //filter by name
        JLabel filter_name = new JLabel("Filter By Name");
        JButton filter_name_button = new JButton("filter");
        JTextField filter_name_input = new JTextField("");
        filter_name.setBounds(10, 150, 150, 30);
        filter_name_button.setBounds(120, 150, 70, 30);
        filter_name_input.setBounds(10, 190, 180, 30);
        this.add(filter_name_input);
        this.add(filter_name);
        this.add(filter_name_button);

        //filter by category
        JLabel filter_c = new JLabel("Filter By Category");
        JButton filter_c_button = new JButton("filter");
        JTextField filter_c_input = new JTextField("");
        filter_c.setBounds(10, 250, 150, 30);
        filter_c_button.setBounds(120, 250, 70, 30);
        filter_c_input.setBounds(10, 290, 180, 30);
        this.add(filter_c);
        this.add(filter_c_button);
        this.add(filter_c_input);

        //filter by state
        JLabel filter_s = new JLabel("Filter By State");
        JButton filter_s_button = new JButton("filter");
        String[] options = {"available", "run out", "below the limit"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        filter_s.setBounds(10, 350, 150, 30);
        filter_s_button.setBounds(120, 350, 70, 30);
        comboBox.setBounds(10, 390, 180, 30);
        this.add(filter_s);
        this.add(filter_s_button);
        this.add(comboBox);

        JButton save_items = new JButton("Save Items to file");
        save_items.setBounds(10, 500, 150, 30);
        this.add(save_items);

        ArrayList<String[]> data = new ArrayList<String[]>();
        for (Item i : ItemController.items) {
            String[] s = {Integer.toString(i.id), i.name, i.category, Integer.toString(i.price), Integer.toString(i.available_amount), Integer.toString(i.least_allowed_amount)};
            data.add(s);
        }
        String[][] data2 = data.toArray(new String[data.size()][5]);

        String[] columnNames = {"id", "Name", "Category", "Price", "available", "minimum amount"};
        JTable table = new JTable(data2, columnNames);
        table.setDefaultEditor(Object.class, null);
        table.setDefaultEditor(Number.class, null);
        table.setDefaultEditor(Boolean.class, null);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(200, 10, 670, 530);
        table.setFillsViewportHeight(true);
        this.add(sp);

        this.setVisible(true);

        add_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame add = new JFrame();
                add.setTitle("Simple Form - FlowLayout");
                add.setSize(400, 400);
                add.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                add.setLayout(new GridLayout(6, 2, 10, 10));

                add.add(new JLabel("Name:"));
                JTextField data_name = new JTextField(20);
                add.add(data_name);

                add.add(new JLabel("Category:"));
                JTextField data_category = new JTextField(20);
                add.add(data_category);

                add.add(new JLabel("Price:"));
                JTextField data_price = new JTextField(20);
                add.add(data_price);

                add.add(new JLabel("available amount:"));
                JTextField data_available_amount = new JTextField(20);
                add.add(data_available_amount);

                add.add(new JLabel("least allowed amount:"));
                JTextField data_least_allowd = new JTextField(20);
                add.add(data_least_allowd);

                JButton save_bt = new JButton("save");
                add.add(save_bt);

                add.setVisible(true);

                add.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        new ManageItems();
                        add.dispose();
                    }
                });

                save_bt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            ItemController.add(data_name.getText(), data_category.getText(), Integer.parseInt(data_price.getText()), Integer.parseInt(data_available_amount.getText()), Integer.parseInt(data_least_allowd.getText()));
                            new ManageItems();
                            add.dispose();
                        } catch (NumberFormatException ex) {
                            System.out.println(ex);
                        }
                    }
                });
            }

        });
        delete_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Integer.parseInt(data2[table.getSelectedRow()][0]));
                ItemController.remove(Integer.parseInt(data2[table.getSelectedRow()][0]));
                frame.dispose();
                new ManageItems();
            }
        });
        edit_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Item item = ItemController.find_by_id(Integer.parseInt(data2[table.getSelectedRow()][0]));
                JFrame add = new JFrame();
                add.setTitle("Simple Form - FlowLayout");
                add.setSize(400, 400);
                add.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                add.setLayout(new GridLayout(6, 2, 10, 10));

                add.add(new JLabel("Name:"));
                JTextField data_name = new JTextField(20);
                data_name.setText(item.name);
                add.add(data_name);

                add.add(new JLabel("Category:"));
                JTextField data_category = new JTextField(20);
                data_category.setText(item.category);
                add.add(data_category);

                add.add(new JLabel("Price:"));
                JTextField data_price = new JTextField(20);
                data_price.setText(String.valueOf(item.price));
                add.add(data_price);

                add.add(new JLabel("available amount:"));
                JTextField data_available_amount = new JTextField(20);
                data_available_amount.setText(String.valueOf(item.available_amount));
                add.add(data_available_amount);

                add.add(new JLabel("least allowed amount:"));
                JTextField data_least_allowd = new JTextField(20);
                data_least_allowd.setText(String.valueOf(item.least_allowed_amount));
                add.add(data_least_allowd);

                JButton save_bt = new JButton("save");
                add.add(save_bt);

                add.setVisible(true);

                add.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        new ManageItems();
                        add.dispose();
                    }
                });

                save_bt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            item.name = data_name.getText();
                            item.category = data_category.getText();
                            item.price = Integer.parseInt(data_price.getText());
                            item.available_amount = Integer.parseInt(data_available_amount.getText());
                            item.least_allowed_amount = Integer.parseInt(data_least_allowd.getText());
                            new ManageItems();
                            add.dispose();
                        } catch (NumberFormatException ex) {
                            System.out.println(ex);
                        }
                    }
                });
            }
        });
        save_items.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemController.save_to_file();
            }
        });

        filter_name_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameFilter = filter_name_input.getText();
                if (nameFilter.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a name to filter!",
                            "Empty Filter", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Set<Item> filteredItems = ItemController.filter_by_name(nameFilter);
                if (filteredItems.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "No items found with name: " + nameFilter,
                            "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Open new frame with filtered results
                    new FilterResultsFrame("Filter by Name: " + nameFilter, filteredItems);
                }
            }
        });

        // FILTER BY CATEGORY BUTTON
        filter_c_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoryFilter = filter_c_input.getText().trim();
                if (categoryFilter.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a category to filter!",
                            "Empty Filter", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Set<Item> filteredItems = ItemController.filter_by_category(categoryFilter);
                if (filteredItems.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "No items found in category: " + categoryFilter,
                            "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Open new frame with filtered results
                    new FilterResultsFrame("Filter by Category: " + categoryFilter, filteredItems);
                }
            }
        });

        // FILTER BY STATE BUTTON
        filter_s_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                String stateName = (String) comboBox.getSelectedItem();

                Set<Item> filteredItems = ItemController.filter_by_state(selectedIndex);
                if (filteredItems.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "No items found with state: " + stateName,
                            "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Open new frame with filtered results
                    new FilterResultsFrame("Filter by State: " + stateName, filteredItems);
                }
            }
        });

    }
}

class FilterResultsFrame extends JFrame {

    FilterResultsFrame(String filterTitle, Set<Item> filteredItems) {
        setTitle("Filter Results - " + filterTitle);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        // Top panel with filter info
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel filterLabel = new JLabel(filterTitle);
        filterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(filterLabel);

        JLabel countLabel = new JLabel("  |  Results found: " + filteredItems.size());
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(countLabel);

        // Create table with filtered data
        ArrayList<String[]> data = new ArrayList<String[]>();
        for (Item i : filteredItems) {
            String[] s = {
                Integer.toString(i.id),
                i.name,
                i.category,
                Integer.toString(i.price),
                Integer.toString(i.available_amount),
                Integer.toString(i.least_allowed_amount)
            };
            data.add(s);
        }
        String[][] data2 = data.toArray(new String[data.size()][6]);

        String[] columnNames = {"id", "Name", "Category", "Price", "available", "minimum amount"};
        JTable table = new JTable(data2, columnNames);
        table.setDefaultEditor(Object.class, null);
        table.setDefaultEditor(Number.class, null);
        table.setDefaultEditor(Boolean.class, null);
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Bottom panel with close button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton closeButton = new JButton("Close and Return");
        closeButton.setPreferredSize(new Dimension(200, 40));
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bottomPanel.add(closeButton);

        // Add all panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        // When this frame closes, it automatically returns to ManageItems
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });
    }
}