package com.mycompany.productionmanagmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ManageTasks extends JFrame {

    ManageTasks() {
        ManageTasks frame = this;
        setTitle("Manage Tasks");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Action Buttons
        JButton add_task = new JButton("Add Task");
        add_task.setBounds(10, 10, 120, 30);
        this.add(add_task);

        JButton edit_task = new JButton("Edit Selected Task");
        edit_task.setBounds(10, 50, 150, 30);
        this.add(edit_task);

        JButton delete_task = new JButton("Delete Selected Task");
        delete_task.setBounds(10, 90, 150, 30);
        this.add(delete_task);

        JButton start_task = new JButton("Start Task");
        start_task.setBounds(10, 130, 120, 30);
        this.add(start_task);

        // Filter by Production Line
        JLabel filter_line_label = new JLabel("Filter by Line:");
        filter_line_label.setBounds(10, 180, 150, 25);
        this.add(filter_line_label);

        Vector<String> lineOptions = new Vector<>();
        lineOptions.add("All Lines");
        for (ProductionLine pl : ProductionLineController.lines) {
            lineOptions.add(pl.lineName);
        }
        JComboBox<String> filter_line_combo = new JComboBox<>(lineOptions);
        filter_line_combo.setBounds(10, 210, 150, 30);
        this.add(filter_line_combo);

        JButton filter_line_btn = new JButton("Filter");
        filter_line_btn.setBounds(10, 245, 150, 30);
        this.add(filter_line_btn);

        // Filter by Product
        JLabel filter_product_label = new JLabel("Filter by Product:");
        filter_product_label.setBounds(10, 290, 150, 25);
        this.add(filter_product_label);

        Vector<String> productOptions = new Vector<>();
        productOptions.add("All Products");
        for (Product p : ProductController.products.keySet()) {
            productOptions.add(p.name);
        }
        JComboBox<String> filter_product_combo = new JComboBox<>(productOptions);
        filter_product_combo.setBounds(10, 320, 150, 30);
        this.add(filter_product_combo);

        JButton filter_product_btn = new JButton("Filter");
        filter_product_btn.setBounds(10, 355, 150, 30);
        this.add(filter_product_btn);

        // Filter by Status
        JLabel filter_status_label = new JLabel("Filter by Status:");
        filter_status_label.setBounds(10, 400, 150, 25);
        this.add(filter_status_label);

        String[] statusOptions = {"All", "RUNNING", "COMPLETED", "ABORTED", "PENDING"};
        JComboBox<String> filter_status_combo = new JComboBox<>(statusOptions);
        filter_status_combo.setBounds(10, 430, 150, 30);
        this.add(filter_status_combo);

        JButton filter_status_btn = new JButton("Filter");
        filter_status_btn.setBounds(10, 465, 150, 30);
        this.add(filter_status_btn);

        JButton back_btn = new JButton("Back to Menu");
        back_btn.setBounds(10, 520, 150, 30);
        this.add(back_btn);

        // Create table with all tasks from all production lines
        ArrayList<String[]> data = getAllTasksData();
        String[][] data2 = data.toArray(new String[data.size()][10]);

        String[] columnNames = {"Task ID", "Line", "Product", "Required Qty", "Achieved Qty", "Progress %", 
                                "Client", "Start Date", "Deadline", "Status"};
        JTable table = new JTable(data2, columnNames);
        table.setDefaultEditor(Object.class, null);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(170, 10, 910, 540);
        table.setFillsViewportHeight(true);
        this.add(sp);

        this.setVisible(true);

        // Add Task Button Action
        add_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    JFrame addFrame = new JFrame("Add New Task");
                    addFrame.setSize(500, 550);
                    addFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    addFrame.setLayout(new GridLayout(10, 2, 10, 10));

                    // Production Line Selection
                    addFrame.add(new JLabel("Production Line:"));
                    Vector<String> lines = new Vector<>();
                    for (ProductionLine pl : ProductionLineController.lines) {
                        lines.add(pl.ID + " - " + pl.lineName);
                    }
                    JComboBox<String> lineCombo = new JComboBox<>(lines);
                    addFrame.add(lineCombo);

                    // Product Selection
                    addFrame.add(new JLabel("Product:"));
                    Vector<String> products = new Vector<>();
                    for (Product p : ProductController.products.keySet()) {
                        products.add(p.id + " - " + p.name);
                    }
                    JComboBox<String> productCombo = new JComboBox<>(products);
                    addFrame.add(productCombo);

                    // Required Quantity
                    addFrame.add(new JLabel("Required Quantity:"));
                    JTextField qtyField = new JTextField();
                    addFrame.add(qtyField);

                    // Client Name
                    addFrame.add(new JLabel("Client Name:"));
                    JTextField clientField = new JTextField();
                    addFrame.add(clientField);

                    // Start Date
                    addFrame.add(new JLabel("Start Date (DD):"));
                    JTextField startDayField = new JTextField();
                    addFrame.add(startDayField);

                    addFrame.add(new JLabel("Start Month (MM):"));
                    JTextField startMonthField = new JTextField();
                    addFrame.add(startMonthField);

                    addFrame.add(new JLabel("Start Year (YYYY):"));
                    JTextField startYearField = new JTextField();
                    addFrame.add(startYearField);

                    // Deadline Date
                    addFrame.add(new JLabel("Deadline (DD):"));
                    JTextField deadlineDayField = new JTextField();
                    addFrame.add(deadlineDayField);

                    addFrame.add(new JLabel("Deadline Month (MM):"));
                    JTextField deadlineMonthField = new JTextField();
                    addFrame.add(deadlineMonthField);

                    addFrame.add(new JLabel("Deadline Year (YYYY):"));
                    JTextField deadlineYearField = new JTextField();
                    addFrame.add(deadlineYearField);

                    JButton saveBtn = new JButton("Save");
                    addFrame.add(saveBtn);

                    addFrame.setVisible(true);

                    addFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            new ManageTasks();
                            addFrame.dispose();
                        }
                    });

                    saveBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String lineStr = (String) lineCombo.getSelectedItem();
                                int lineID = Integer.parseInt(lineStr.split(" - ")[0]);

                                String productStr = (String) productCombo.getSelectedItem();
                                int productID = Integer.parseInt(productStr.split(" - ")[0]);
                                Product product = ProductController.find_by_id(productID);

                                int quantity = Integer.parseInt(qtyField.getText().trim());
                                String client = clientField.getText().trim();

                                int startDay = Integer.parseInt(startDayField.getText().trim());
                                int startMonth = Integer.parseInt(startMonthField.getText().trim());
                                int startYear = Integer.parseInt(startYearField.getText().trim());

                                int deadlineDay = Integer.parseInt(deadlineDayField.getText().trim());
                                int deadlineMonth = Integer.parseInt(deadlineMonthField.getText().trim());
                                int deadlineYear = Integer.parseInt(deadlineYearField.getText().trim());

                                if (quantity <= 0) {
                                    JOptionPane.showMessageDialog(addFrame, 
                                        "Quantity must be positive!", 
                                        "Validation Error", 
                                        JOptionPane.WARNING_MESSAGE);
                                    return;
                                }

                                if (client.isEmpty()) {
                                    JOptionPane.showMessageDialog(addFrame, 
                                        "Client name cannot be empty!", 
                                        "Validation Error", 
                                        JOptionPane.WARNING_MESSAGE);
                                    return;
                                }

                                Date startDate = new Date(startDay, startMonth, startYear);
                                Date deadlineDate = new Date(deadlineDay, deadlineMonth, deadlineYear);

                                // Get the next task ID based on all tasks in all production lines
                                int nextTaskID = 1;
                                for (ProductionLine pl : ProductionLineController.lines) {
                                    for (Tasks t : pl.lineTasks) {
                                        if (t.ID >= nextTaskID) {
                                            nextTaskID = t.ID + 1;
                                        }
                                    }
                                }

                                ProductionLineController.add_tasks(lineID, nextTaskID, 
                                    product, quantity, 0, startDate, deadlineDate, client, "PENDING");

                                JOptionPane.showMessageDialog(addFrame, 
                                    "Task added successfully!", 
                                    "Success", 
                                    JOptionPane.INFORMATION_MESSAGE);

                                new ManageTasks();
                                addFrame.dispose();

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(addFrame, 
                                    "Invalid input! Please enter valid numbers.", 
                                    "Input Error", 
                                    JOptionPane.ERROR_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(addFrame, 
                                    "Error adding task: " + ex.getMessage(), 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error opening add task dialog: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete Task
        delete_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a task to delete!");
                    return;
                }

                int taskID = Integer.parseInt(data2[selectedRow][0]);
                String lineName = data2[selectedRow][1];
                
                int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to delete this task?",
                    "Confirm Delete", 
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Find the production line and remove the task
                    for (ProductionLine pl : ProductionLineController.lines) {
                        if (pl.lineName.equals(lineName)) {
                            pl.lineTasks.removeIf(t -> t.ID == taskID);
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Task deleted successfully!");
                    frame.dispose();
                    new ManageTasks();
                }
            }
        });

        // Start Task
        start_task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a task to start!");
                    return;
                }

                try {
                    int taskID = Integer.parseInt(data2[selectedRow][0]);
                    String lineName = data2[selectedRow][1];
                    
                    // Find the task in the production line
                    Tasks task = null;
                    ProductionLine line = null;
                    for (ProductionLine pl : ProductionLineController.lines) {
                        if (pl.lineName.equals(lineName)) {
                            line = pl;
                            for (Tasks t : pl.lineTasks) {
                                if (t.ID == taskID) {
                                    task = t;
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    if (task != null && task.status.equals("PENDING")) {
                        Thread thread = new Thread(task);
                        thread.start();
                        JOptionPane.showMessageDialog(frame, 
                            "Task started successfully!\nRunning in background...");
                    } else if (task != null) {
                        JOptionPane.showMessageDialog(frame, 
                            "Task is already running or completed!\nCurrent status: " + task.status);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Task not found!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error starting task: " + ex.getMessage());
                }
            }
        });

        // Filter by Line
        filter_line_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) filter_line_combo.getSelectedItem();
                if (selected.equals("All Lines")) {
                    frame.dispose();
                    new ManageTasks();
                } else {
                    Set<Tasks> filtered = new HashSet<>();
                    for (ProductionLine pl : ProductionLineController.lines) {
                        if (pl.lineName.equals(selected)) {
                            filtered.addAll(pl.lineTasks);
                        }
                    }
                    new TaskFilterResultsFrame("Production Line: " + selected, filtered);
                }
            }
        });

        // Filter by Product
        filter_product_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) filter_product_combo.getSelectedItem();
                if (selected.equals("All Products")) {
                    frame.dispose();
                    new ManageTasks();
                } else {
                    String productName = selected.split(" - ")[1];
                    Set<Tasks> filtered = new HashSet<>();
                    for (ProductionLine pl : ProductionLineController.lines) {
                        for (Tasks t : pl.lineTasks) {
                            if (t.product.name.equals(productName)) {
                                filtered.add(t);
                            }
                        }
                    }
                    new TaskFilterResultsFrame("Product: " + productName, filtered);
                }
            }
        });

        // Filter by Status
        filter_status_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) filter_status_combo.getSelectedItem();
                if (selected.equals("All")) {
                    frame.dispose();
                    new ManageTasks();
                } else {
                    Set<Tasks> filtered = new HashSet<>();
                    for (ProductionLine pl : ProductionLineController.lines) {
                        for (Tasks t : pl.lineTasks) {
                            if (t.status.equals(selected)) {
                                filtered.add(t);
                            }
                        }
                    }
                    new TaskFilterResultsFrame("Status: " + selected, filtered);
                }
            }
        });

        // Back Button
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SupervisorMainMenu();
            }
        });
    }

    private ArrayList<String[]> getAllTasksData() {
        ArrayList<String[]> data = new ArrayList<>();
        // Iterate through all production lines and get their tasks
        for (ProductionLine pl : ProductionLineController.lines) {
            for (Tasks t : pl.lineTasks) {
                String[] row = {
                    String.valueOf(t.ID),
                    pl.lineName,
                    t.product.name,
                    String.valueOf(t.requiredQuantity),
                    String.valueOf(t.achievedQuantity),
                    String.format("%.1f%%", t.get_achieved_percentage()),
                    t.clientName,
                    t.startDate.day + "/" + t.startDate.month + "/" + t.startDate.year,
                    t.deadlineDate.day + "/" + t.deadlineDate.month + "/" + t.deadlineDate.year,
                    t.status
                };
                data.add(row);
            }
        }
        return data;
    }
}

class TaskFilterResultsFrame extends JFrame {
    TaskFilterResultsFrame(String filterTitle, Set<Tasks> filteredTasks) {
        setTitle("Filter Results - " + filterTitle);
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel filterLabel = new JLabel(filterTitle);
        filterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(filterLabel);

        JLabel countLabel = new JLabel("  |  Results: " + filteredTasks.size());
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(countLabel);

        ArrayList<String[]> data = new ArrayList<>();
        for (Tasks t : filteredTasks) {
            // Find which production line this task belongs to
            String lineName = "";
            for (ProductionLine pl : ProductionLineController.lines) {
                if (pl.lineTasks.contains(t)) {
                    lineName = pl.lineName;
                    break;
                }
            }
            
            String[] row = {
                String.valueOf(t.ID),
                lineName,
                t.product.name,
                String.valueOf(t.requiredQuantity),
                String.valueOf(t.achievedQuantity),
                String.format("%.1f%%", t.get_achieved_percentage()),
                t.clientName,
                t.startDate.day + "/" + t.startDate.month + "/" + t.startDate.year,
                t.deadlineDate.day + "/" + t.deadlineDate.month + "/" + t.deadlineDate.year,
                t.status
            };
            data.add(row);
        }
        String[][] data2 = data.toArray(new String[data.size()][10]);

        String[] columnNames = {"Task ID", "Line", "Product", "Required Qty", "Achieved Qty", 
                                "Progress %", "Client", "Start Date", "Deadline", "Status"};
        JTable table = new JTable(data2, columnNames);
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);

        JScrollPane sp = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(150, 35));
        closeButton.addActionListener(e -> dispose());
        bottomPanel.add(closeButton);

        add(topPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}