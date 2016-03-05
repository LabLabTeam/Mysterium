package com.github.lablabteam.mysterium.view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import com.github.lablabteam.mysterium.logic.Controller;
import com.github.lablabteam.mysterium.utils.CallbackOnFinished;
import com.github.lablabteam.mysterium.utils.ResourceManager;
import com.github.lablabteam.mysterium.utils.ProcessCallback;


public class GuiView extends View {

    public GuiView(Controller controller) {
        super(controller);
    }

    @Override
    public <T> void displayLoading(T obj, CallbackOnFinished<T> cb, List<String> paths) {
        
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Mysterium loading...");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
        frame.setLayout(boxLayout);
        
        ImageIcon icon = new ImageIcon("res/img/logo.jpg");
        
        JLabel picture = new JLabel(icon);
        JProgressBar progressBar;
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        
        JLabel labelLoading = new JLabel("Loading...");
        
        frame.add(picture);
        frame.add(progressBar);
        frame.add(labelLoading);
        
        frame.pack();
        frame.setSize(picture.getWidth(), picture.getHeight() + labelLoading.getHeight() + progressBar.getHeight());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        ResourceManager rm = ResourceManager.getInstance();
        
        rm.loadListOfFilesByPath(paths, new ProcessCallback() {
            @Override
            public void callbackStatusPercentage(int percentage) {
                //System.out.println(percentage + "%");
                progressBar.setValue(percentage);
            }

            @Override
            public void callbackStatusAction(String action) {
                //System.out.println(action);
                //progressBar.setString(action);
                labelLoading.setText(action);
            }
        });
        progressBar.setValue(100);
        labelLoading.setText("Done!");
        
        cb.callbackOnFinished(obj);
        
        frame.setVisible(false);
        frame.dispose();
    }

}
