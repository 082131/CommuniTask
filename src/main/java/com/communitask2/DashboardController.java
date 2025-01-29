/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.communitask2;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class DashboardController implements Initializable {

    @FXML
    private VBox graphContainer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	BarChart<String, Number> barChart = createGraph();
        graphContainer.getChildren().add(barChart);
    }    
    
    private BarChart<String, Number> createGraph() {
        // Setup for the bar chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Complaint Types");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Complaints");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Complaints Summary");

        // Fetch data from the database
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT type, COUNT(*) AS count FROM complaints GROUP BY type")) {

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Complaints");

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int count = resultSet.getInt("count");
                series.getData().add(new XYChart.Data<>(type, count));
            }

            barChart.getData().add(series);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return barChart;
    }
}
