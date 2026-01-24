package com.mycompany.productionmanagmentsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ManageProducts extends JFrame {

    ManageProducts() {
        ManageProducts frame = this;
        setTitle("Manage Products");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JButton add_product = new JButton("Add Product");
        add_product.setBounds(10, 10, 140, 30);
        this.add(add_product);

        JButton edit_product = new JButton("Edit Selected");
        edit_product.setBounds(10, 50, 140, 30);
        this.add(edit_product);

        JButton delete_product = new JButton("Delete Selected");
        delete_product.setBounds(10, 90, 140, 30);
        this.add(delete_product);

        JButton view_recipe = new JButton("View Recipe");
        view_recipe.setBounds(10, 130, 140, 30);
        this.add(view_recipe);

        JButton edit_recipe = new JButton("Edit Recipe");
        edit_recipe.setBounds(10, 170, 140, 30);
        this.add(edit_recipe);

        // Filter by Name
        JLabel filter_name = new JLabel("Filter by Name:");
        filter_name.setBounds(10, 220, 140, 25);
        this.add(filter_name);

        JTextField filter_name_input = new JTextField();
        filter_name_input.setBounds(10, 250, 140, 30);
        this.add(filter_name_input);

        JButton filter_name_btn = new JButton("Filter");
        filter_name_btn.setBounds(10, 285, 140, 30);
        this.add(filter_name_btn);

        // View by Production Line
        JLabel view_by_line = new JLabel("View by Line:");
        view_by_line.setBounds(10, 340, 140, 25);
        this.add(view_by_line);

        Vector<String> lineOptions = new Vector<>();
        lineOptions.add("All Lines");
        for (ProductionLine pl : ProductionLineController.lines) {
            lineOptions.add(pl.lineName);
        }
        JComboBox<String> line_combo = new JComboBox<>(lineOptions);
        line_combo.setBounds(10, 370, 140, 30);
        this.add(line_combo);

        JButton view_line_btn = new JButton("View");
        view_line_btn.setBounds(10, 405, 140, 30);
        this.add(view_line_btn);

        JButton save_products = new JButton("Save to File");
        save_products.setBounds(10, 460, 140, 30);
        this.add(save_products);

        JButton back_btn = new JButton("Back to Menu");
        back_btn.setBounds(10, 520, 140, 30);
        this.add(back_btn);

        // Create table
        ArrayList<String[]> data = getAllProductsData();
        String[][] data2 = data.toArray(new String[data.size()][3]);

        String[] columnNames = {"Product ID", "Product Name", "Stock Quantity"};
        JTable table = new JTable(data2, columnNames);
        table.setDefaultEditor(Object.class, null);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(160, 10, 820, 540);
        table.setFillsViewportHeight(true);
        this.add(sp);

        this.setVisible(true);

        // Add Product
        add_product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    JFrame addFrame = new JFrame("Add New Product");
                    addFrame.setSize(600, 500);
                    addFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    addFrame.setLayout(null);

                    JLabel nameLabel = new JLabel("Product Name:");
                    nameLabel.setBounds(20, 20, 150, 25);
                    addFrame.add(nameLabel);

                    JTextField nameField = new JTextField();
                    nameField.setBounds(180, 20, 380, 25);
                    addFrame.add(nameField);

                    JLabel recipeLabel = new JLabel("Recipe (Required Items):");
                    recipeLabel.setBounds(20, 60, 200, 25);
                    addFrame.add(recipeLabel);

                    JLabel itemLabel = new JLabel("Item:");
                    itemLabel.setBounds(20, 100, 100, 25);
                    addFrame.add(itemLabel);

                    Vector<String> itemOptions = new Vector<>();
                    for (Item item : ItemController.items) {
                        itemOptions.add(item.id + " - " + item.name);
                    }
                    JComboBox<String> itemCombo = new JComboBox<>(itemOptions);
                    itemCombo.setBounds(130, 100, 250, 25);
                    addFrame.add(itemCombo);

                    JLabel qtyLabel = new JLabel("Quantity:");
                    qtyLabel.setBounds(390, 100, 80, 25);
                    addFrame.add(qtyLabel);

                    JTextField qtyField = new JTextField();
                    qtyField.setBounds(470, 100, 90, 25);
                    addFrame.add(qtyField);

                    JButton addItemBtn = new JButton("Add to Recipe");
                    addItemBtn.setBounds(200, 140, 150, 30);
                    addFrame.add(addItemBtn);

                    JTextArea recipeArea = new JTextArea();
                    recipeArea.setEditable(false);
                    JScrollPane recipeSp = new JScrollPane(recipeArea);
                    recipeSp.setBounds(20, 180, 540, 200);
                    addFrame.add(recipeSp);

                    HashMap<Item, Integer> recipeMap = new HashMap<>();

                    addItemBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String itemStr = (String) itemCombo.getSelectedItem();
                                int itemId = Integer.parseInt(itemStr.split(" - ")[0]);
                                Item item = ItemController.find_by_id(itemId);
                                
                                int qty = Integer.parseInt(qtyField.getText().trim());
                                
                                if (qty <= 0) {
                                    JOptionPane.showMessageDialog(addFrame, 
                                        "Quantity must be positive!");
                                    return;
                                }

                                recipeMap.put(item, qty);
                                
                                StringBuilder sb = new StringBuilder();
                                for (Map.Entry<Item, Integer> entry : recipeMap.entrySet()) {
                                    sb.append(entry.getKey().name)
                                      .append(" x ")
                                      .append(entry.getValue())
                                      .append("\n");
                                }
                                recipeArea.setText(sb.toString());
                                qtyField.setText("");
                                
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(addFrame, 
                                    "Invalid quantity! Please enter a number.");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(addFrame, 
                                    "Error: " + ex.getMessage());
                            }
                        }
                    });

                    JButton saveBtn = new JButton("Save Product");
                    saveBtn.setBounds(200, 400, 150, 35);
                    addFrame.add(saveBtn);

                    JButton cancelBtn = new JButton("Cancel");
                    cancelBtn.setBounds(360, 400, 100, 35);
                    addFrame.add(cancelBtn);

                    saveBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String name = nameField.getText().trim();
                                
                                if (name.isEmpty()) {
                                    JOptionPane.showMessageDialog(addFrame, 
                                        "Product name cannot be empty!");
                                    return;
                                }

                                if (recipeMap.isEmpty()) {
                                    JOptionPane.showMessageDialog(addFrame, 
                                        "Recipe cannot be empty! Add at least one item.");
                                    return;
                                }

                                Product newProduct = new Product(name);
                                newProduct.add_full_hash(recipeMap);
                                ProductController.products.put(newProduct, 0);

                                JOptionPane.showMessageDialog(addFrame, 
                                    "Product added successfully!");

                                new ManageProducts();
                                addFrame.dispose();

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(addFrame, 
                                    "Error saving product: " + ex.getMessage());
                            }
                        }
                    });

                    cancelBtn.addActionListener(e1 -> {
                        new ManageProducts();
                        addFrame.dispose();
                    });

                    addFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            new ManageProducts();
                            addFrame.dispose();
                        }
                    });

                    addFrame.setVisible(true);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error opening add product dialog: " + ex.getMessage());
                }
            }
        });

        // Delete Product
        delete_product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, 
                        "Please select a product to delete!");
                    return;
                }

                int productId = Integer.parseInt(data2[selectedRow][0]);
                String productName = data2[selectedRow][1];

                int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to delete \"" + productName + "\"?",
                    "Confirm Delete", 
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    ProductController.remove(productId);
                    JOptionPane.showMessageDialog(frame, "Product deleted!");
                    frame.dispose();
                    new ManageProducts();
                }
            }
        });

        // View Recipe
        view_recipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, 
                        "Please select a product to view recipe!");
                    return;
                }

                int productId = Integer.parseInt(data2[selectedRow][0]);
                Product product = ProductController.find_by_id(productId);

                if (product == null) {
                    JOptionPane.showMessageDialog(frame, "Product not found!");
                    return;
                }

                StringBuilder recipe = new StringBuilder();
                recipe.append("Recipe for: ").append(product.name).append("\n\n");
                
                if (product.required_items.isEmpty()) {
                    recipe.append("No recipe defined.");
                } else {
                    for (Map.Entry<Item, Integer> entry : product.required_items.entrySet()) {
                        recipe.append(entry.getKey().name)
                              .append(" - Quantity: ")
                              .append(entry.getValue())
                              .append("\n");
                    }
                }

                JOptionPane.showMessageDialog(frame, recipe.toString(), 
                    "Product Recipe", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Filter by Name
        filter_name_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameFilter = filter_name_input.getText().trim();
                if (nameFilter.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "Please enter a product name!");
                    return;
                }

                HashMap<Product, Integer> filtered = ProductController.filter_by_name(nameFilter);
                if (filtered.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "No products found with name: " + nameFilter);
                } else {
                    new ProductFilterResultsFrame("Product Name: " + nameFilter, filtered);
                }
            }
        });

        // View by Production Line
        view_line_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) line_combo.getSelectedItem();
                if (selected.equals("All Lines")) {
                    frame.dispose();
                    new ManageProducts();
                } else {
                    ProductionLine line = null;
                    for (ProductionLine pl : ProductionLineController.lines) {
                        if (pl.lineName.equals(selected)) {
                            line = pl;
                            break;
                        }
                    }
                    
                    if (line != null) {
                        HashSet<Product> products = ProductionLineController.get_products_by_line(line.ID);
                        HashMap<Product, Integer> productsMap = new HashMap<>();
                        for (Product p : products) {
                            productsMap.put(p, ProductController.products.get(p));
                        }
                        new ProductFilterResultsFrame("Production Line: " + selected, productsMap);
                    }
                }
            }
        });

        // Save to File
        save_products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                        "Save all products to file?",
                        "Confirm Save", 
                        JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        ProductController.save_to_file();
                        JOptionPane.showMessageDialog(frame, 
                            "Products saved successfully!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error saving: " + ex.getMessage());
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

    private ArrayList<String[]> getAllProductsData() {
        ArrayList<String[]> data = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : ProductController.products.entrySet()) {
            Product p = entry.getKey();
            String[] row = {
                String.valueOf(p.id),
                p.name,
                String.valueOf(entry.getValue())
            };
            data.add(row);
        }
        return data;
    }
}

class ProductFilterResultsFrame extends JFrame {
    ProductFilterResultsFrame(String filterTitle, HashMap<Product, Integer> filteredProducts) {
        setTitle("Filter Results - " + filterTitle);
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel filterLabel = new JLabel(filterTitle);
        filterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(filterLabel);

        JLabel countLabel = new JLabel("  |  Results: " + filteredProducts.size());
        countLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(countLabel);

        ArrayList<String[]> data = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : filteredProducts.entrySet()) {
            Product p = entry.getKey();
            String[] row = {
                String.valueOf(p.id),
                p.name,
                String.valueOf(entry.getValue())
            };
            data.add(row);
        }
        String[][] data2 = data.toArray(new String[data.size()][3]);

        String[] columnNames = {"Product ID", "Product Name", "Stock Quantity"};
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