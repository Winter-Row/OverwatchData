package com.example.overwatchdata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private ComboBox<String> rankComboBox;

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barChart.setTitle("Hero Win Rates");
        barChart.legendVisibleProperty().setValue(false);
        pieChart.setTitle("Role Win Rates");

        rankComboBox.getItems().addAll(DBUtility.getRanks());
        rankComboBox.valueProperty().addListener((obs, oldValue, newValue) ->{
            pieChart.getData().clear();
            barChart.getData().clear();
            pieChart.getData().addAll(DBUtility.getWinRateForRolesByRank(newValue));
            barChart.getData().addAll(DBUtility.getWinRateForHerosByRank(newValue));
        });
        rankComboBox.setValue("All");
    }

    @FXML
    void changeToTableView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("data-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


}

