package com.github.lablabteam.mysterium.logic;

import java.io.File;

public class Medium {
    private File image;
    private String name;
    private String description;
    
    public Medium(File image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }
    
    public File getImage() {
        return this.image;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
}
