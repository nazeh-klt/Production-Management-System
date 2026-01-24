package com.mycompany.productionmanagmentsystem;

import javax.swing.*;

public class LogIn extends JFrame {

    public LogIn() {

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 25);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 180, 25);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 90, 100, 25);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 90, 180, 25);
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 150, 100, 30);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            dispose();

            // 🔹 بسيط للمشروع (بدون DB)
            if (user.equalsIgnoreCase("manager")) {
                new ManageProductionLines();   // مدير الإنتاج
            } else {
                new SupervisorMainMenu();      // مشرف الإنتاج
            }
        });

        setVisible(true);
    }
}
