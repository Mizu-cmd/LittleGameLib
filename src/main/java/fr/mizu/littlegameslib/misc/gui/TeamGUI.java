package fr.mizu.littlegameslib.misc.gui;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameTeam;
import fr.mizu.littlegameslib.managers.PlayerManager;
import fr.mizu.littlegameslib.utils.GUI;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TeamGUI extends GUI {

    @Override
    public String name() {
        return "§6Select a Team";
    }

    @Override
    public int size() {
        return 9*3;
    }

    @Override
    public boolean canMoveObject() {
        return false;
    }

    @Override
    public ItemStack[][] items() {
        ItemStack[][] items = {};
        return items;
    }

    @Override
    public void onGUIClick(InventoryClickEvent e) {
        e.setCancelled(true);
        GamePlayer gamePlayer = PlayerManager.getGamePlayer((OfflinePlayer) e.getWhoClicked());
        if (!gamePlayer.isInGame() || !gamePlayer.isOnline()) return;
        GameTeam team = gamePlayer.getGame().getTeamByName(e.getCurrentItem().getItemMeta().getDisplayName());
        if (team.getPlayers().contains(gamePlayer)){
            gamePlayer.getOnlinePlayer().sendMessage("§cYou allready are inside the team");
            return;
        }
        if (!team.addPlayer(gamePlayer)){
            gamePlayer.getOnlinePlayer().sendMessage("§cThe Team is full");
            return;
        }
        team.addPlayer(gamePlayer);
        gamePlayer.getOnlinePlayer().sendMessage("§aYou are now in the team : "+team.getName());
        gamePlayer.getOnlinePlayer().closeInventory();
    }
}
