package fr.mizu.littlegameslib.arena;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import fr.mizu.littlegameslib.MiniGamePlugin;
import fr.mizu.littlegameslib.config.ConfigFile;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private String id;
    private ArenaSettings currentSettings;
    private ConfigFile arenaConfig;
    private MiniGamePlugin plugin;
    private Location loc1;
    private Location loc2;
    private Location lobby;
    private Location mainLobby;
    private List<Location> spawns;
    private World world;

    public Arena(String id, World world, MiniGamePlugin plugin){
        this.id = id;
        this.world = world;
        this.plugin = plugin;
        this.spawns = new ArrayList<>();
        this.arenaConfig = new ConfigFile(plugin.getDataFolder()+"/arenas/"+id+".yml");
    }

    public Arena(ConfigFile file, MiniGamePlugin plugin){
        this.plugin = plugin;
        this.arenaConfig = file;
        this.spawns = new ArrayList<>();
        loadArena(file);
    }

    public void saveArena(){
        arenaConfig.set("id", id);
        arenaConfig.set("world", world.getUID().toString());
        arenaConfig.set("world-name", world.getName());
        arenaConfig.set("loc1", loc1);
        arenaConfig.set("loc2", loc2);
        arenaConfig.set("lobby", lobby);
        arenaConfig.set("mainLobby", mainLobby);
        arenaConfig.set("spawns", spawns);
        arenaConfig.save();
    }

    private void loadArena(ConfigFile arenaFile){
        this.id = arenaFile.getString("id");
        this.world = Bukkit.getWorld(UUID.fromString(arenaFile.getString("world")));
        this.loc1 = (Location) arenaFile.get("loc1");
        this.loc2 = (Location) arenaFile.get("loc2");
        this.lobby = (Location) arenaFile.get("lobby");
        this.mainLobby = (Location) arenaFile.get("mainLobby");

        List<Location> spawns = (List<Location>) arenaFile.getList("spawns");
        if (spawns != null){
            for (Location spawn : spawns){
                addSpawn(spawn);
            }
        }

        System.out.println(ChatColor.GREEN+"Arena ID: "+id+" successfully loaded !");
    }

    public World cloneArena(){
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();

        String name = "/Games/"+UUID.randomUUID();

        worldManager.cloneWorld(world.getName(), name);

        System.out.println("Arena cloned !");

        return Bukkit.getWorld(name);

    }

    public void setLoc1(Location loc1) {
        this.loc1 = loc1;
    }

    public void setLoc2(Location loc2) {
        this.loc2 = loc2;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public void setMainLobby(Location mainLobby) {
        this.mainLobby = mainLobby;
    }

    public List<Location> getSpawns() {
        return spawns;
    }

    public void addSpawn(Location location){
        this.spawns.add(location);
    }

    public String getId() {
        return id;
    }

    public World getWorld() {
        return world;
    }

    public Location getLobby() {
        return lobby;
    }

    public Location getLoc1() {
        return loc1;
    }

    public Location getLoc2() {
        return loc2;
    }

    public Location getMainLobby() {
        return mainLobby;
    }

    public MiniGamePlugin getPlugin() {
        return plugin;
    }

    public ArenaSettings getCurrentSettings() {
        return currentSettings;
    }

    public void setCurrentSettings(ArenaSettings currentSettings) {
        this.currentSettings = currentSettings;
    }
}
