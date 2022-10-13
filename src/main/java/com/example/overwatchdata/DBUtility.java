package com.example.overwatchdata;

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
}
