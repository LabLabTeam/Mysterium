package com.github.lablabteam.mysterium.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.github.lablabteam.mysterium.logic.Controller;
import com.github.lablabteam.mysterium.utils.CallbackOnFinished;
import com.github.lablabteam.mysterium.utils.ResourceManager;
import com.github.lablabteam.mysterium.utils.locale.Locale;
import com.github.lablabteam.mysterium.utils.ProcessCallback;


public class GuiView extends View {
    
    JFrame frame;

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

    @Override
    public void setupGameView() {
        System.out.println(Locale.getInstance().getText("setup_game_view_message"));
        frame = new JFrame("Mysterium loading...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.pack();
        frame.setSize(1000, 750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    @Override
    public void showMainMenu() {
        JPanel mainPanel = new JPanel( new FlowLayout( FlowLayout.CENTER, 10, 20 ) );
        JButton playButton = new JButton("Play");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");
        
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller.showHelp();
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller.requestExit();
            }
        });
        
        mainPanel.add(playButton);
        mainPanel.add(helpButton);
        mainPanel.add(exitButton);
        frame.setContentPane(mainPanel);
    }

    @Override
    public void closeGameView() {
        frame.setVisible(false);
        frame.dispose();
    }

    @Override
    public void showHelp() {
        JOptionPane.showMessageDialog(frame, "MSG", "InfoBox: " + "Titulo!", JOptionPane.INFORMATION_MESSAGE);
    }
    

}
