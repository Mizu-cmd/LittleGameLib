package fr.mizu.littlegameslib.managers;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import fr.mizu.littlegameslib.LittleGamesLib;
import fr.mizu.littlegameslib.MiniGamePlugin;
import fr.mizu.littlegameslib.arena.Arena;
import fr.mizu.littlegameslib.config.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.ArrayList;

public class ArenaManager {

    public static ArrayList<Arena> loadArenas(MiniGamePlugin plugin){

        ArrayList<Arena> arenas = new ArrayList<>();
        File[] allArenas = new File(plugin.getDataFolder() + "/arenas").listFiles();

        if (allArenas == null) return arenas;

        for (File file : allArenas){
            if (file.isFile()){
                ConfigFile configFile = new ConfigFile(plugin.getDataFolder()+"/arenas/"+file.getName());

                Arena arena = new Arena(configFile, plugin);
                plugin.getArenas().add(arena);

            }
        }
        return arenas;
    }
}
