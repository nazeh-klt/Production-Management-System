package com.mycompany.productionmanagmentsystem;

import javax.swing.*;
import java.util.ArrayList;

public class ManageProductionLines extends JFrame {

    public ManageProductionLines() {

        ManageProductionLines frame = this;

        setTitle("Manage Production Lines");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JButton add_line = new JButton("Add Line");
        add_line.setBounds(10, 10, 120, 30);
        add(add_line);

        JButton delete_line = new JButton("Delete Selected Line");
        delete_line.setBounds(10, 50, 180, 30);
        add(delete_line);

        JButton edit_state = new JButton("Edit State");
        edit_state.setBounds(10, 90, 180, 30);
        add(edit_state);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(10, 130, 120, 30);
        add(refresh);

        JButton evaluation = new JButton("Evaluation Notes");
        evaluation.setBounds(10, 170, 180, 30);
        add(evaluation);

        // ===== Table =====
        ArrayList<String[]> data = new ArrayList<>();

        for (ProductionLine p : ProductionLineController.lines) {
            String[] row = {
                String.valueOf(p.ID),
                p.lineName,
                p.lineState,
                String.format("%.2f", p.get_completion_percentage()) + "%",
                p.evaluationNote
            };
            data.add(row);
        }

        String[][] data2 = data.toArray(new String[data.size()][5]);
        String[] columnNames = {
            "ID",
            "Line Name",
            "State",
            "Completion %",
            "Evaluation Notes"
        };

        JTable table = new JTable(data2, columnNames);
        table.setDefaultEditor(Object.class, null);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(200, 10, 670, 530);
        add(sp);

        setVisible(true);

        // ===== Add Line =====
        add_line.addActionListener(e -> {
            JFrame add = new JFrame("Add Production Line");
            add.setSize(350, 230);
            add.setLayout(null);
            add.setLocationRelativeTo(null);

            JLabel name_l = new JLabel("Line Name:");
            name_l.setBounds(20, 30, 100, 25);
            add.add(name_l);

            JTextField name_f = new JTextField();
            name_f.setBounds(130, 30, 150, 25);
            add.add(name_f);

            JLabel state_l = new JLabel("State:");
            state_l.setBounds(20, 70, 100, 25);
            add.add(state_l);

            String[] states = {"ACTIVE", "STOPPED", "MAINTENANCE"};
            JComboBox<String> state_cb = new JComboBox<>(states);
            state_cb.setBounds(130, 70, 150, 25);
            add.add(state_cb);

            JButton save = new JButton("Save");
            save.setBounds(110, 130, 100, 30);
            add.add(save);

            save.addActionListener(ev -> {
                ProductionLineController.addProductionLine(
                    ProductionLine.last_id + 1,
                    name_f.getText(),
                    state_cb.getSelectedItem().toString()
                );
                add.dispose();
                frame.dispose();
                new ManageProductionLines();
            });

            add.setVisible(true);
        });

        // ===== Delete Line (WITH CONFIRMATION) =====
        delete_line.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a line first");
                return;
            }

            String lineName = table.getValueAt(row, 1).toString();
            int id = Integer.parseInt(table.getValueAt(row, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to delete \"" + lineName + "\" production line?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                ProductionLineController.removeProductionLine(id);
                frame.dispose();
                new ManageProductionLines();
            }
        });

        // ===== Edit State =====
        edit_state.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Select a line first");
                return;
            }

            int id = Integer.parseInt(table.getValueAt(row, 0).toString());
            ProductionLine line = ProductionLineController.find_by_ID(id);

            String[] states = {"ACTIVE", "STOPPED", "MAINTENANCE"};
            JComboBox<String> cb = new JComboBox<>(states);
            cb.setSelectedItem(line.lineState);

            int result = JOptionPane.showConfirmDialog(
                frame,
                cb,
                "Edit Line State",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                line.lineState = cb.getSelectedItem().toString();
                frame.dispose();
                new ManageProductionLines();
            }
        });

        refresh.addActionListener(e -> {
            frame.dispose();
            new ManageProductionLines();
        });

        // ===== Evaluation Notes =====
        evaluation.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            int id = Integer.parseInt(table.getValueAt(row, 0).toString());
            ProductionLine line = ProductionLineController.find_by_ID(id);
            new ManageProductionLineNotes(line, frame);
        });
    }
}


class ManageProductionLineNotes extends JFrame {

    public ManageProductionLineNotes(ProductionLine line, JFrame parent) {

        setTitle("Evaluation Notes - " + line.lineName);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel label = new JLabel("Evaluation Notes:");
        label.setBounds(20, 20, 200, 25);
        add(label);

        JTextArea notesArea = new JTextArea();
        notesArea.setText(line.evaluationNote);

        JScrollPane sp = new JScrollPane(notesArea);
        sp.setBounds(20, 60, 440, 200);
        add(sp);

        JButton save = new JButton("Save");
        save.setBounds(180, 280, 120, 30);
        add(save);

        save.addActionListener(e -> {
            line.evaluationNote = notesArea.getText();
            dispose();
            parent.dispose();
            new ManageProductionLines();
        });

        setVisible(true);
    }
}
