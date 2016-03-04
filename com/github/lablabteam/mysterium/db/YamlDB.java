package com.github.lablabteam.mysterium.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.github.lablabteam.mysterium.db.yaml.YamlObject;
import com.github.lablabteam.mysterium.db.yaml.YamlObject_clue;
import com.github.lablabteam.mysterium.db.yaml.YamlObject_clues;
import com.github.lablabteam.mysterium.db.yaml.YamlObject_difficultyLevel;

public class YamlDB implements DB{
    
    private YamlObject yamlObject;
    
    public YamlDB() throws FileNotFoundException, YamlException {
        YamlReader reader = new YamlReader(new FileReader("res/tests/db.yml"));
        reader.getConfig().setPropertyElementType(YamlObject.class, "difficultyLevels", YamlObject_difficultyLevel.class);
        reader.getConfig().setPropertyElementType(YamlObject_clues.class, "tools", YamlObject_clue.class);
        reader.getConfig().setPropertyElementType(YamlObject_clues.class, "locations", YamlObject_clue.class);
        reader.getConfig().setPropertyElementType(YamlObject_clues.class, "suspects", YamlObject_clue.class);
        yamlObject = reader.read(YamlObject.class);
    }
    
    public void writeToFile() throws IOException {
        YamlWriter writer = new YamlWriter(new FileWriter("res/tests/db2.yml"));
        writer.getConfig().setPropertyElementType(YamlObject.class, "difficultyLevels", YamlObject_difficultyLevel.class);
        writer.getConfig().setPropertyElementType(YamlObject_clues.class, "tools", YamlObject_clue.class);
        writer.getConfig().setPropertyElementType(YamlObject_clues.class, "locations", YamlObject_clue.class);
        writer.getConfig().setPropertyElementType(YamlObject_clues.class, "suspects", YamlObject_clue.class);
        writer.write(yamlObject);
        writer.close();
    }
    
    public void p() {
        System.out.println("Difficulty: " + yamlObject.difficultyLevels.get(0).difficulty);
        System.out.println("t2: " + yamlObject.clues.tools.get(0).name);
        System.out.println("image: " + yamlObject.clues.locations.get(1).images.get(0));
    }
}
