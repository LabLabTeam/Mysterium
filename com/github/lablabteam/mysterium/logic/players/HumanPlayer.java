package com.github.lablabteam.mysterium.logic.players;

import com.github.lablabteam.mysterium.logic.Game;
import com.github.lablabteam.mysterium.view.View;

public class HumanPlayer extends Player {
    
    private View view;

    public HumanPlayer(Game game, String name, View view) {
        super(game, name);
        this.view = view;
    }

}
