package com.github.lablabteam.mysterium.tests;

import java.io.IOException;
import com.github.lablabteam.mysterium.db.YamlDB;
import com.github.lablabteam.mysterium.logic.FSM;
import com.github.lablabteam.mysterium.view.GuiView;
import com.github.lablabteam.mysterium.view.TermView;


public class Main {

    public static void main(String[] args) throws IOException {
        FSM f = new FSM();
        f.runStateMachine();
    }

}
