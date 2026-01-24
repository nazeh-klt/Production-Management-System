package com.mycompany.productionmanagmentsystem;

import java.util.ArrayList;

public class ProductionLine {

    static int last_id = 0;

    int ID;
    String lineName;
    String lineState;
    ArrayList<Tasks> lineTasks;
    String evaluationNote = "";

    public ProductionLine(String lineName, String lineState) {
        this.ID = ++last_id;
        this.lineName = lineName;
        this.lineState = lineState;
        this.lineTasks = new ArrayList<>();
    }

    public ProductionLine(int ID, String lineName, String lineState) {
        this.ID = ID;
        this.lineName = lineName;
        this.lineState = lineState;
        this.lineTasks = new ArrayList<>();
        last_id = Math.max(last_id, ID);
    }

    public double get_completion_percentage() {
        if (lineTasks.isEmpty()) return 0;

        double sum = 0;
        for (Tasks t : lineTasks) {
            sum += t.get_achieved_percentage();
        }
        return sum / lineTasks.size();
    }
}
