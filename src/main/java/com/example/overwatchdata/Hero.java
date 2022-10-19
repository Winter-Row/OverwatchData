package com.example.overwatchdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hero {
    private int heroID;
    private String name, role, heroRank;
    private double winRate;

    public Hero(int heroID, String name, String role, String heroRank, double winRate) {
        setHeroID(heroID);
        setName(name);
        setRole(role);
        setHeroRank(heroRank);
        setWinRate(winRate);
    }

    public int getHeroID() {
        return heroID;
    }

    public void setHeroID(int heroID) {
        if(heroID > 0)
            this.heroID = heroID;
        else
            throw new IllegalArgumentException("Hero id must be greater than 1");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        List<String> names = Arrays.asList("Ana", "Ashe", "Baptiste" , "Bastion" , "Brigitte" , "D.Va" , "Doomfist" ,
                "Genji" , "Hanzo" , "Junkrat" , "Lúcio" , "McCree" , "Mei" , "Mercy" , "Moira" , "Orisa" , "Pharah" ,
                "Reaper" , "Reinhardt" , "Roadhog" , "Soldier: 76" , "Sombra" , "Symmetra" , "Torbjörn" , "Tracer" , "Widowmaker" ,
                "Winston" , "Wrecking Ball" , "Zarya" , "Zenyatta");
        if(names.contains(name))
            this.name = name;
        else
            throw new IllegalArgumentException("Name must be one of" + names);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("TANK");
        roles.add("SUPPORT");
        roles.add("DEFENSE");
        roles.add("OFFENSE");
        if(roles.contains(role))
            this.role = role;
        else
            throw new IllegalArgumentException("Role must be one of " + roles);
    }

    public String getHeroRank() {
        return heroRank;
    }

    public void setHeroRank(String heroRank) {
        ArrayList<String> ranks = new ArrayList<>();
        ranks.add("All");
        ranks.add("Bronze");
        ranks.add("Silver");
        ranks.add("Gold");
        ranks.add("Platinum");
        ranks.add("Diamond");
        ranks.add("Master");
        ranks.add("Grandmaster");
        if(ranks.contains(heroRank))
            this.heroRank = heroRank;
        else
            throw new IllegalArgumentException("Hero rank must be one of " + ranks);
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        if(winRate > 0 && winRate < 101)
            this.winRate = winRate;
        else
            throw new IllegalArgumentException("Win rate must be between 0 and 100");
    }
}
