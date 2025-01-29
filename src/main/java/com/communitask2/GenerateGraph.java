package com.communitask2;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenerateGraph {

    public Pane createGraphPane(Connection connection) {
        // Define the axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Complaint Type");
        yAxis.setLabel("Number of Complaints");

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Complaints Summary");

        // Fetch data from the database and populate the graph
        try {
            ArrayList<String> complaintTypes = new ArrayList<>();
            ArrayList<Integer> complaintCounts = new ArrayList<>();

            String query = "SELECT complaint_type, COUNT(*) AS count FROM complaints GROUP BY complaint_type;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("complaint_type");
                int count = resultSet.getInt("count");
                complaintTypes.add(type);
                complaintCounts.add(count);
            }

            // Populate the chart data
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Number of Complaints");

            for (int i = 0; i < complaintTypes.size(); i++) {
                series.getData().add(new XYChart.Data<>(complaintTypes.get(i), complaintCounts.get(i)));
            }

            barChart.getData().add(series);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error fetching data for the graph: " + e.getMessage());
        }

        // Wrap the chart in a pane to display it
        Pane graphPane = new Pane();
        graphPane.getChildren().add(barChart);
        return graphPane;
    }
}