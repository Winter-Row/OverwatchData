package com.example.overwatchdata;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtility {
    //username for sql server
    private static String user = "root";
    //password for sql server
    private static String pw = "Kubotadog#19";
    //ip port and db name used
    private static String connURL = "jdbc:mysql://127.0.0.1:3306/overwatch";

    public static ArrayList<Hero> getHerosFromDB(){

        ArrayList<Hero> hero = new ArrayList<>();
        String sql = "SELECT * FROM Heros";

        try(
            //connecting to DB
            Connection conn = DriverManager.getConnection(connURL, user, pw);
            Statement statement = conn.createStatement();
            //executing sql statement
            ResultSet resultSet = statement.executeQuery(sql)
        ){
            while(resultSet.next()){
                int heroID = resultSet.getInt("HeroID");
                String name = resultSet.getString("Name");
                String role = resultSet.getString("Role");
                double winRate = resultSet.getDouble("WinRate");
                String heroRank = resultSet.getString("HeroRank");

                Hero newHero = new Hero(heroID, name, role,heroRank, winRate);
                hero.add(newHero);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return hero;
    }

    public static XYChart.Series<String, Double> getWinRateByRank(){

        XYChart.Series<String, Double> winRates = new XYChart.Series<>();
        String sql = "SELECT Name, WinRate " +
                "FROM Heros " +
                "WHERE HeroRank = 'All' " +
                "ORDER BY HeroID;";

        try(
                //connecting to DB
                Connection conn = DriverManager.getConnection(connURL, user, pw);
                Statement statement = conn.createStatement();
                //executing sql statement
                ResultSet resultSet = statement.executeQuery(sql)
        ){
            while(resultSet.next()){
                String name = resultSet.getString("Name");
                double winRate = resultSet.getDouble("WinRate");

                winRates.getData().add(new XYChart.Data<>(name,winRate));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return winRates;
    }

    public static ObservableList<PieChart.Data> getWinRateForRolesByRank(){

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String sql = "SELECT Role, SUM(WinRate) AS WinRate\n" +
                "FROM Heros\n" +
                "WHERE HeroRank = 'All'\n" +
                "GROUP BY Role;";

        try(
                //connecting to DB
                Connection conn = DriverManager.getConnection(connURL, user, pw);
                Statement statement = conn.createStatement();
                //executing sql statement
                ResultSet resultSet = statement.executeQuery(sql)
        ){
            while(resultSet.next()){
                String role = resultSet.getString("Role");
                double winRate = resultSet.getDouble("WinRate");

                pieChartData.add(new PieChart.Data(role,winRate));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return pieChartData;
    }
}
