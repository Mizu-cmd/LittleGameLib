package fr.mizu.littlegameslib.listeners;

import fr.mizu.littlegameslib.game.Game;
import fr.mizu.littlegameslib.game.GameArea;
import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.event.events.PlayerEnterAreaGE;
import fr.mizu.littlegameslib.game.event.events.PlayerLeaveAreaGE;
import fr.mizu.littlegameslib.managers.PlayerManager;
import fr.mizu.littlegameslib.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        GamePlayer player = PlayerManager.getGamePlayer(e.getPlayer());
        if(player.isInGame()){
            for(GameArea area : player.getGame().getAreas()){
                Location f = e.getFrom();
                Location t = e.getTo();
                Location l1 = area.getLoc1();
                Location l2 = area.getLoc2();
                boolean fromIsIn = false;
                boolean toIsIn = false;
                if(LocationUtil.isInArea(f, l1, l2)){
                    fromIsIn = true;
                }
                if(LocationUtil.isInArea(t, l1, l2)){
                    toIsIn = true;
                }
                Game game = player.getGame();
                if(fromIsIn && !toIsIn){
                    game.getEventManager().call(new PlayerLeaveAreaGE(player, game.getState(), area));

                }else if(!fromIsIn && toIsIn){
                    game.getEventManager().call(new PlayerEnterAreaGE(player, game.getState(), area));
                }
            }
        }
    }
}
