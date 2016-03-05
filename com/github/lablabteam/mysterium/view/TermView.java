package com.github.lablabteam.mysterium.view;

import java.util.List;

import com.github.lablabteam.mysterium.logic.Controller;
import com.github.lablabteam.mysterium.utils.CallbackOnFinished;
import com.github.lablabteam.mysterium.utils.ProcessCallback;
import com.github.lablabteam.mysterium.utils.ResourceManager;
import com.github.lablabteam.mysterium.utils.locale.Locale;

public class TermView extends View {

    public TermView(Controller controller) {
        super(controller);
    }

    @Override
    public <T> void displayLoading(T obj, CallbackOnFinished<T> cb, List<String> paths) {
        ResourceManager rm = ResourceManager.getInstance();
        
        rm.loadListOfFilesByPath(paths, new ProcessCallback() {
            @Override
            public void callbackStatusPercentage(int percentage) {
                System.out.println(percentage + "%");
            }

            @Override
            public void callbackStatusAction(String action) {
                System.out.println(action);
            }
        });
        
        cb.callbackOnFinished(obj);
    }

    @Override
    public void setupGameView() {
        System.out.println(Locale.getInstance().getText("setup_game_view_message"));
    }

    @Override
    public void closeGameView() {
        System.out.println("Closing TermView");
    }

    @Override
    public void showMainMenu() {
        System.out.println("Show main menu");
    }

    @Override
    public void showHelp() {
        System.out.println("Help!");
    }

    @Override
    public void showMysteriumGame() {
        System.out.println("Mysterium game!");
    }

}
