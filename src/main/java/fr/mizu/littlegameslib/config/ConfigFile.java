package fr.mizu.littlegameslib.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile extends YamlConfiguration {
    private File YmlFile;

    public ConfigFile(String name){
        this.YmlFile = new File(name);
        try {
            if (YmlFile.getParentFile() != null && !YmlFile.getParentFile().exists()){
                YmlFile.getParentFile().mkdir();
            }
            if (!YmlFile.exists()){
                YmlFile.createNewFile();
            }
            this.load(YmlFile);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(){
        try {
            this.save(YmlFile);
            this.load(YmlFile);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload(){
        try {
            this.load(YmlFile);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
