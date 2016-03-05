package com.github.lablabteam.mysterium.view;

import java.util.List;

import com.github.lablabteam.mysterium.logic.Controller;
import com.github.lablabteam.mysterium.utils.CallbackOnFinished;

public abstract class View {
    protected Controller controller;
    
    public View(Controller controller) {
        this.controller = controller;
    }
    
    public abstract <T> void displayLoading(T obj, CallbackOnFinished<T> cb, List<String> paths);
}
