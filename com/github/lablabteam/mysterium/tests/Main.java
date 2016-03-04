package com.github.lablabteam.mysterium.tests;

import java.io.IOException;

import com.github.lablabteam.mysterium.db.YamlDB;


public class Main {

    public static void main(String[] args) throws IOException {
        
        YamlDB db = new YamlDB();
        db.p();
        db.writeToFile();
    }

}
