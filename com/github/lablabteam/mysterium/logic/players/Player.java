package com.github.lablabteam.mysterium.logic.players;

import com.github.lablabteam.mysterium.logic.Game;
import com.github.lablabteam.mysterium.logic.Medium;

public abstract class Player {
    protected Game game;
    protected int points;
    protected Medium medium;
    protected String name;
    
    public Player(Game game, String name) {
        this.game = game;
        this.points = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public void incrasePointsBy(int amount) {
        this.points += amount;
    }
    
    public Medium getMedium() {
        return this.medium;
    }
    //public choose
}
