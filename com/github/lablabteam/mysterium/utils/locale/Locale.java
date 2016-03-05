package com.github.lablabteam.mysterium.utils.locale;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

@SuppressWarnings("rawtypes")
public class Locale {
    private static Map currentLocale;
    private static Map allLocale;
    private static Locale Instance;
    
    private Locale() {
        YamlReader yamlReader;
        try {
            yamlReader = new YamlReader(new FileReader("res/locale/default.yml"));
            allLocale = (Map) yamlReader.read();
        } catch (Exception e) {
            Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("./default.yml"));
            yamlReader = new YamlReader(reader);
            try {
                allLocale = (Map) yamlReader.read();
            } catch (YamlException e1) {
                e1.printStackTrace();
                throw new RuntimeException("Error while creating a locale!", e);
            }
        }
        
        
        setLocale("default");
    }
    
    public static Locale getInstance() {
        if (Locale.Instance == null) {
            Locale.Instance = new Locale();
        }
        return Locale.Instance;
    }
    
    public void setLocale(String language) {
        currentLocale = (Map) allLocale.get(language);
    }
    
    public String getText(String msgKey) {
        return currentLocale.get(msgKey).toString();
    }
}
