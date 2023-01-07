package fr.mizu.littlegameslib;

import fr.mizu.littlegameslib.game.GameItem;
import fr.mizu.littlegameslib.listeners.ItemListener;
import fr.mizu.littlegameslib.listeners.PlayerMoveListener;
import fr.mizu.littlegameslib.listeners.PlayerPVPListener;
import fr.mizu.littlegameslib.listeners.SettingsListener;
import fr.mizu.littlegameslib.misc.bossbar.BossBar;
import fr.mizu.littlegameslib.misc.teamItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LittleGamesLib extends JavaPlugin {

    public static LittleGamesLib Instance;
    public static GameItem defaultTeamGUIItem;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        PluginManager pm = Bukkit.getPluginManager();
        BossBar.initBars();
        pm.registerEvents(new PlayerPVPListener(), this);
        pm.registerEvents(new SettingsListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new ItemListener(), this);

        saveDefaultConfig();
        makeDefaultTeamItemGUI();
    }

    private void makeDefaultTeamItemGUI(){
        defaultTeamGUIItem = new teamItem();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GameItem getDefaultTeamGUIItem() {
        return defaultTeamGUIItem;
    }
}
