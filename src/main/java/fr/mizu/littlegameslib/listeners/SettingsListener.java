package fr.mizu.littlegameslib.listeners;

import fr.mizu.littlegameslib.game.GameListener;
import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.types.PlayerType;
import fr.mizu.littlegameslib.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SettingsListener implements Listener {
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        GamePlayer player = PlayerManager.getGamePlayer(e.getPlayer());

        if (!player.isInGame() || !player.isOnline()) return;
        for (GameListener listener : player.getGame().getListeners()){
           listener.onBlockPlace(player, e, player.getGame().getState());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        GamePlayer player = PlayerManager.getGamePlayer(e.getPlayer());
        if (!player.isInGame() || !player.isOnline()) return;
        player.getGame().leaveGame(player);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if (!(e.getEntity() instanceof Player)) return;
        if(e.isCancelled()) return;

        Player player = (Player) e.getEntity();

        GamePlayer gamePlayer = PlayerManager.getGamePlayer(player);
        if (!gamePlayer.isInGame() || !gamePlayer.isOnline()) return;

        gamePlayer.getGame().getListeners().forEach(listener -> listener.onFoodLevelChange(gamePlayer, e, gamePlayer.getGame().getState()));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        GamePlayer player = PlayerManager.getGamePlayer(e.getPlayer());

        if (!player.isInGame() || !player.isOnline()) return;

        for (GameListener listener : player.getGame().getListeners()){
            listener.onBlockBreak(player, e, player.getGame().getState());
        }
    }

}
