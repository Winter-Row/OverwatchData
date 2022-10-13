package com.example.overwatchdata;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DataController implements Initializable {

    @FXML
    private TableColumn<Hero, String> HeroNameCol;

    @FXML
    private TableColumn<Hero, String> HeroRankCol;

    @FXML
    private TableColumn<Hero, String> RoleCol;

    @FXML
    private TableView<Hero> TableView;

    @FXML
    private TableColumn<Hero, Double> WinRateCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HeroNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        HeroRankCol.setCellValueFactory(new PropertyValueFactory<>("heroRank"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        WinRateCol.setCellValueFactory(new PropertyValueFactory<>("winRate"));
        TableView.getItems().addAll(DBUtility.getHerosFromDB());

    }
}
