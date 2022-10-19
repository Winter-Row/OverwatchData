package com.example.overwatchdata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HeroNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        HeroRankCol.setCellValueFactory(new PropertyValueFactory<>("heroRank"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        WinRateCol.setCellValueFactory(new PropertyValueFactory<>("winRate"));
        TableView.getItems().addAll(DBUtility.getHerosFromDB());

    }

    @FXML
    void changeToDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Dashboard-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
