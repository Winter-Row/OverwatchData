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
    private static String user = "Hunter1169835";
    //password for sql server
    private static String pw = "6MgnNaNjEt";
    //ip port and db name used
    private static String connURL = "jdbc:mysql://172.31.22.43 /Hunter1169835";

    /**
     * This method returns the heros from the db to be displayed in a table
     * @return
     */
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
            //looping over results from query
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

    /**
     * This method gets the win rate and hero's by there rank from the DB to be display in a bar chart
     * @param rank
     * @return
     */
    public static XYChart.Series<String, Double> getWinRateForHerosByRank(String rank){

        XYChart.Series<String, Double> winRates = new XYChart.Series<>();
        String sql = "SELECT Name, WinRate " +
                "FROM Heros " +
                "WHERE HeroRank =" + "'" + rank + "' " +
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

    /**
     * This method gets the win rate for roles by rank from the DB to be displayed in a pie chart
     * @param rank
     * @return
     */
    public static ObservableList<PieChart.Data> getWinRateForRolesByRank(String rank){

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        String sql = "SELECT Role, SUM(WinRate) AS WinRate " +
                "FROM Heros " +
                "WHERE HeroRank =" + "'" + rank + "' " +
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

    /**
     * getting the ranks from the DB to be display in a combo box
     * @return
     */
    public static ArrayList<String> getRanks(){
        ArrayList<String> ranks = new ArrayList<>();
        String sql = "SELECT HeroRank " +
                "FROM Heros " +
                "GROUP BY HeroRank;";
        try(
                Connection conn = DriverManager.getConnection(connURL, user, pw);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ){
            while(resultSet.next()){
                ranks.add(resultSet.getString("HeroRank"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ranks;
    }
}
