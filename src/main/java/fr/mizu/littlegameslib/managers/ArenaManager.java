package fr.mizu.littlegameslib.managers;

import fr.mizu.littlegameslib.MiniGamePlugin;
import fr.mizu.littlegameslib.arena.Arena;
import fr.mizu.littlegameslib.config.ConfigFile;

import java.io.File;
import java.util.ArrayList;

public class ArenaManager {

    public static ArrayList<Arena> loadArenas(MiniGamePlugin plugin){

        ArrayList<Arena> arenas = new ArrayList<>();
        File[] allArenas = new File(plugin.getDataFolder() + "/arenas").listFiles();

        if (allArenas == null) return arenas;

        for (File file : allArenas){
            if (file.isFile()){
                ConfigFile configFile = new ConfigFile(plugin.getDataFolder()+"/arenas/"+ file.getName());
                Arena arena = new Arena(configFile, plugin);
                arenas.add(arena);
                plugin.getArenas().add(arena);
            }
        }
        return arenas;
    }
}
