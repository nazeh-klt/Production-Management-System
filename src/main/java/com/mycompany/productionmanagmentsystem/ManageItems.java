/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productionmanagmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ManageItems extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField filterNameInput;
    private JTextField filterCategoryInput;
    private JComboBox<String> stateCombo;

    public ManageItems() {
        setTitle("Manage Items");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left panel with filters and buttons
        JPanel leftPanel = createLeftPanel();

        // Center panel with table
        JPanel centerPanel = createCenterPanel();

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel, BorderLayout.CENTER);
        
        refreshTable(ItemController.items);
        setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(200, 0));

        // Buttons
        JButton addBtn = new JButton("Add Item");
        JButton editBtn = new JButton("Edit Selected");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton saveBtn = new JButton("Save to File");
        JButton refreshBtn = new JButton("Refresh All");

        addBtn.setMaximumSize(new Dimension(180, 30));
        editBtn.setMaximumSize(new Dimension(180, 30));
        deleteBtn.setMaximumSize(new Dimension(180, 30));
        saveBtn.setMaximumSize(new Dimension(180, 30));
        refreshBtn.setMaximumSize(new Dimension(180, 30));

        panel.add(addBtn);
        panel.add(Box.createVerticalStrut(5));
        panel.add(editBtn);
        panel.add(Box.createVerticalStrut(5));
        panel.add(deleteBtn);
        panel.add(Box.createVerticalStrut(15));

        // Filter by name
        panel.add(new JLabel("Filter by Name:"));
        filterNameInput = new JTextField();
        filterNameInput.setMaximumSize(new Dimension(180, 25));
        JButton filterNameBtn = new JButton("Filter");
        filterNameBtn.setMaximumSize(new Dimension(180, 25));
        panel.add(filterNameInput);
        panel.add(filterNameBtn);
        panel.add(Box.createVerticalStrut(10));

        // Filter by category
        panel.add(new JLabel("Filter by Category:"));
        filterCategoryInput = new JTextField();
        filterCategoryInput.setMaximumSize(new Dimension(180, 25));
        JButton filterCategoryBtn = new JButton("Filter");
        filterCategoryBtn.setMaximumSize(new Dimension(180, 25));
        panel.add(filterCategoryInput);
        panel.add(filterCategoryBtn);
        panel.add(Box.createVerticalStrut(10));

        // Filter by state
        panel.add(new JLabel("Filter by State:"));
        String[] states = {"Available", "Run Out", "Below Limit"};
        stateCombo = new JComboBox<>(states);
        stateCombo.setMaximumSize(new Dimension(180, 25));
        JButton filterStateBtn = new JButton("Filter");
        filterStateBtn.setMaximumSize(new Dimension(180, 25));
        panel.add(stateCombo);
        panel.add(filterStateBtn);

        panel.add(Box.createVerticalGlue());
        panel.add(saveBtn);
        panel.add(Box.createVerticalStrut(5));
        panel.add(refreshBtn);

        // Action listeners
        addBtn.addActionListener(e -> addItem());
        editBtn.addActionListener(e -> editItem());
        deleteBtn.addActionListener(e -> deleteItem());
        saveBtn.addActionListener(e -> saveToFile());
        refreshBtn.addActionListener(e -> refreshTable(ItemController.items));

        filterNameBtn.addActionListener(e -> {
            String name = filterNameInput.getText().trim();
            if (!name.isEmpty()) {
                refreshTable(ItemController.filter_by_name(name));
            } else {
                refreshTable(ItemController.items);
            }
        });

        filterCategoryBtn.addActionListener(e -> {
            String category = filterCategoryInput.getText().trim();
            if (!category.isEmpty()) {
                refreshTable(ItemController.filter_by_category(category));
            } else {
                refreshTable(ItemController.items);
            }
        });

        filterStateBtn.addActionListener(e -> {
            int state = stateCombo.getSelectedIndex();
            refreshTable(ItemController.filter_by_state(state));
        });

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"ID", "Name", "Category", "Price", "Available", "Min Amount"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void refreshTable(Set<Item> items) {
        tableModel.setRowCount(0);
        for (Item item : items) {
            tableModel.addRow(new Object[]{
                item.id,
                item.name,
                item.category,
                item.price,
                item.available_amount,
                item.least_allowed_amount
            });
        }
    }

    private void addItem() {
        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField availableField = new JTextField();
        JTextField minField = new JTextField();

        Object[] message = {
            "Name:", nameField,
            "Category:", categoryField,
            "Price:", priceField,
            "Available Amount:", availableField,
            "Minimum Amount:", minField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Item",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                int price = Integer.parseInt(priceField.getText().trim());
                int available = Integer.parseInt(availableField.getText().trim());
                int min = Integer.parseInt(minField.getText().trim());

                if (name.isEmpty() || category.isEmpty()) {
                    throw new IllegalArgumentException("Name and category cannot be empty");
                }

                ItemController.add(name, category, price, available, min);
                refreshTable(ItemController.items);
                JOptionPane.showMessageDialog(this, "Item added successfully!");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to edit",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Item item = ItemController.find_by_id(id);

        if (item == null) {
            JOptionPane.showMessageDialog(this, "Item not found",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField nameField = new JTextField(item.name);
        JTextField categoryField = new JTextField(item.category);
        JTextField priceField = new JTextField(String.valueOf(item.price));
        JTextField availableField = new JTextField(String.valueOf(item.available_amount));
        JTextField minField = new JTextField(String.valueOf(item.least_allowed_amount));

        Object[] message = {
            "Name:", nameField,
            "Category:", categoryField,
            "Price:", priceField,
            "Available Amount:", availableField,
            "Minimum Amount:", minField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Edit Item",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();
                int price = Integer.parseInt(priceField.getText().trim());
                int available = Integer.parseInt(availableField.getText().trim());
                int min = Integer.parseInt(minField.getText().trim());

                if (name.isEmpty() || category.isEmpty()) {
                    throw new IllegalArgumentException("Name and category cannot be empty");
                }

                // Update the item
                item.name = name;
                item.category = category;
                item.price = price;
                item.available_amount = available;
                item.least_allowed_amount = min;

                refreshTable(ItemController.items);
                JOptionPane.showMessageDialog(this, "Item updated successfully!");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this item?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            ItemController.remove(id);
            refreshTable(ItemController.items);
            JOptionPane.showMessageDialog(this, "Item deleted successfully!");
        }
    }

    private void saveToFile() {
        ItemController.save_to_file();
        JOptionPane.showMessageDialog(this, "Items saved to file successfully!");
    }
}