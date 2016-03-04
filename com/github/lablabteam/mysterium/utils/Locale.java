package com.github.lablabteam.mysterium.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

@SuppressWarnings("rawtypes")
public class Locale {
    private static Map currentLocale;
    private static Map allLocale;
    private static Locale Instance;
    
    private Locale() throws FileNotFoundException, YamlException {
        YamlReader reader = new YamlReader(new FileReader("res/locale/default.yml"));
        allLocale = (Map) reader.read();
        setLocale("default");
    }
    
    public static Locale getInstance() throws FileNotFoundException, YamlException {
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
