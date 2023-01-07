package fr.mizu.littlegameslib;

import fr.mizu.littlegameslib.Internationalization.I18N;
import fr.mizu.littlegameslib.arena.Arena;
import fr.mizu.littlegameslib.game.Game;
import fr.mizu.littlegameslib.game.GameItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public abstract class MiniGamePlugin extends JavaPlugin {
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Arena> arenas = new ArrayList<>();

    public abstract I18N getDefaultLanguage();

    public GameItem getTeamItemStack(){
        return LittleGamesLib.getDefaultTeamGUIItem();
    }

    public Arena getArenaByID(String id){
        for(Arena arena : getArenas()){
            if (arena.getId().equalsIgnoreCase(id)){
                return arena;
            }
        }
        return null;
    }

    public ArrayList<Arena> getArenas() {
        return arenas;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}
