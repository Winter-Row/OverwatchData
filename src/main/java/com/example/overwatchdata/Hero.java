package com.example.overwatchdata;

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
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHeroRank() {
        return heroRank;
    }

    public void setHeroRank(String heroRank) {
        this.heroRank = heroRank;
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
