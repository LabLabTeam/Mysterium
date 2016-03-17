package com.github.lablabteam.mysterium.logic;

import java.util.Arrays;
import java.util.List;

import com.github.lablabteam.mysterium.utils.CallbackOnFinished;
import com.github.lablabteam.mysterium.utils.ProcessCallback;
import com.github.lablabteam.mysterium.utils.Sound;
import com.github.lablabteam.mysterium.utils.Utils;
import com.github.lablabteam.mysterium.view.GuiView;
import com.github.lablabteam.mysterium.view.TermView;
import com.github.lablabteam.mysterium.view.View;


public class FSM implements Controller{
    private enum States {
        INIT, // Init, just go to next state
        LOADING_SCREEN, // Display a loading screen while loading resources
        POST_LOADING_SCREEN, // Dummy state. Should not be called... except if the loading screen works in a separate thread asyncronously
        SETUP_GAME_VIEW,
        S9,
        EXIT
    }
    
    private Sound s;
    private States state;
    private View view;
    private Game game;
    private boolean exit;
    
    private boolean SIGNAL_requestExit = false;
    
    public FSM() {
        this.state = States.INIT;
        this.view = new GuiView(this);
        this.exit = false;
    }
    
    public void runStateMachine() {
        while (!shouldExit()) {
            execState();
        }
    }
    
    private void setNextState(States state) {
        this.state = state;
        System.out.println("   New state " + this.state);
    }
    
    public boolean shouldExit() {
        return this.exit;
    }

    private void execState() {
        switch (this.state) {
        case INIT:
            setNextState(States.LOADING_SCREEN);
        case LOADING_SCREEN:
            setNextState(States.POST_LOADING_SCREEN);
            
            List<String> paths = Arrays.asList(
                    "res/audio/A_Long_Cold_Sting.wav",
                    "res/audio/African_Drums_Sting.wav",
                    "res/audio/All_This_Down_Time_Sting.wav",
                    "res/audio/Animal_Sting.wav",
                    "res/audio/Ask_Rufus.wav",
                    "res/audio/Bomber_Sting.wav",
                    "res/audio/Cataclysmic_Molten_Core_Sting.wav"
                );
            
            
            view.displayLoading(this, new CallbackOnFinished<FSM>() {
                @Override
                public void callbackOnFinished(FSM obj) {
                    Utils.sleep(1000);
                    obj.setNextState(States.SETUP_GAME_VIEW);
                }

                @Override
                public void callbackOnError(FSM obj, Exception ex) {
                    //view.displayError();
                    obj.setNextState(States.EXIT);
                }}, paths);
            break;
        case POST_LOADING_SCREEN:
            Utils.sleep(500);
            break;
        case SETUP_GAME_VIEW:
            letsRockAndRoll();
            setNextState(States.S9);
            view.setupGameView();
            view.showMainMenu();
            break;
        case S9:
            if (this.SIGNAL_requestExit) setNextState(States.EXIT);
            Utils.sleep(100);
            break;
        case EXIT:
            this.exit = true;
            view.closeGameView();
            System.exit(0);
            break;
        default:
            break;
        }
    }

    @Override
    public void showHelp() {
        view.showHelp();
    }

    @Override
    public void requestExit() {
        SIGNAL_requestExit = true;
    }
    
    @Override
    public void letsRockAndRoll() {
        if (s == null) {
            s = new Sound("res/audio/Latin_Industries.wav", new ProcessCallback() {
                @Override
                public void callbackStatusPercentage(int percentage) {
                    System.out.println(percentage + "%");
                }
    
                @Override
                public void callbackStatusAction(String action) {
                    System.out.println(action);
                    s.play();
                }
            });
        }
        s.play();
    }
    
    @Override
    public void partyNoMore() {
        s.stop();
    }
}
