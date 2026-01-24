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

            String name = userField.getText();
            String password = new String(passField.getPassword());

            User user = User.login(name, password);

            if (user == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // login success
            dispose();

            // 0 = Manager | 1 = Supervisor
            if (user.role == 0) {
                new ManageProductionLines();   // Manager
            } else if (user.role == 1) {
                new SupervisorMainMenu();      // Supervisor
            }
        });

        setVisible(true);
    }
}
