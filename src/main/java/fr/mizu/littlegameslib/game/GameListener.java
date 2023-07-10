package fr.mizu.littlegameslib.game;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public abstract class GameListener {
    public void onPlayerJoin(GamePlayer player, GameState state) { }
    public  void onPlayerLeave(GamePlayer player, GameState state) { }
    public void onPlayerGetKilledByAnother(GamePlayer victim, GamePlayer killer, GameState state) { }
    public void onPlayerDamageAnother(GamePlayer victim, GamePlayer killer, EntityDamageByEntityEvent e, GameState state) { }
    public void onPlayerTakesDamage(GamePlayer player, EntityDamageEvent e, GameState state) { }
    public void onFoodLevelChange(GamePlayer player, FoodLevelChangeEvent e, GameState state) { }
    public void onBlockPlace(GamePlayer player, BlockPlaceEvent e, GameState state) { }
    public void onBlockBreak(GamePlayer player, BlockBreakEvent e, GameState state) { }
    public void onPlayerEnterArea(GamePlayer player, GameArea area, GameState state) { }
    public void onPlayerLeaveArea(GamePlayer player, GameArea area, GameState state) { }

}
