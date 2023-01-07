package fr.mizu.littlegameslib.listeners;

import fr.mizu.littlegameslib.game.Game;
import fr.mizu.littlegameslib.game.GameListener;
import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.managers.PlayerManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerPVPListener implements Listener {

    @EventHandler
    public void playerDamageEvent(EntityDamageByEntityEvent e){
        GamePlayer player = null;
        GamePlayer damager = null;

        if(e.getEntity() instanceof Player && (e.getDamager() instanceof Arrow ||
                e.getDamager() instanceof Player)){

            player = PlayerManager.getGamePlayer((Player) e.getEntity());

            if(e.getDamager() instanceof Arrow){
                Arrow arrow = (Arrow) e.getDamager();
                if(arrow.getShooter() instanceof Player){
                    damager = PlayerManager.getGamePlayer((OfflinePlayer) arrow.getShooter());
                }
            }else{
                damager = PlayerManager.getGamePlayer((Player) e.getDamager());
            }
        }

        if (player == null || damager == null) return;
        if (!player.isInGame() || !damager.isInGame()) return;
        if (e.isCancelled()) return;
        if (!player.getGame().equals(damager.getGame())) return;

        if (player.getTeam().getPlayers().contains(damager)){
            e.setCancelled(true);
            return;
        }

        Game game = player.getGame();

        if((player.getOnlinePlayer().getHealth() - e.getFinalDamage() <= 0)){

            for (GameListener listener : game.getListeners()){
                listener.onPlayerGetKilledByAnother(player, damager, game.getState());
            }

            if (game.getSettings().countKills())
                game.increaseKill(damager, 1);
        } else {
            for (GameListener listener : game.getListeners()){
                listener.onPlayerDamageAnother(player, damager, e, game.getState());
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        GamePlayer player = PlayerManager.getGamePlayer((OfflinePlayer) e.getEntity());
        if (!player.isInGame() || !player.isOnline()) return;
        for (GameListener listener : player.getGame().getListeners()) {
            listener.onPlayerTakesDamage(player, e, player.getGame().getState());
        }
    }
}
