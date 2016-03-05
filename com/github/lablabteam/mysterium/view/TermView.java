package com.github.lablabteam.mysterium.view;

import java.util.List;

import com.github.lablabteam.mysterium.logic.Controller;
import com.github.lablabteam.mysterium.utils.CallbackOnFinished;
import com.github.lablabteam.mysterium.utils.ProcessCallback;
import com.github.lablabteam.mysterium.utils.ResourceManager;

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

}
