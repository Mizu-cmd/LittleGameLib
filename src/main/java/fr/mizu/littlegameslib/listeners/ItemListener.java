package fr.mizu.littlegameslib.listeners;

import fr.mizu.littlegameslib.game.GameItem;
import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.managers.PlayerManager;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {
    @EventHandler
    public void onItemInteract(PlayerInteractEvent e){
        ItemStack item = e.getItem();
        if (item == null || item.getType() == Material.AIR) return;

        GamePlayer player = PlayerManager.getGamePlayer(e.getPlayer());
        if (!player.isInGame() || !player.isOnline()) return;

        GameItem gameItem = player.getPlayerItems().get(item);
        if (gameItem == null) return;

        //if (!gameItem.canInteract()) e.setCancelled(true);
        gameItem.onInteract(player, e);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        ItemStack item = e.getItemDrop().getItemStack();
        if (item == null || item.getType() == Material.AIR) return;

        GamePlayer player = PlayerManager.getGamePlayer(e.getPlayer());
        if (!player.isInGame() || !player.isOnline()) return;

        GameItem gameItem = player.getPlayerItems().get(item);
        if (gameItem == null) return;

        if (!gameItem.canDrop()) e.setCancelled(true);
        gameItem.onDrop(player, e);
    }

    @EventHandler
    public void onItemMove(InventoryClickEvent e){
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        GamePlayer player = PlayerManager.getGamePlayer((OfflinePlayer) e.getWhoClicked());
        if (!player.isInGame() || !player.isOnline()) return;

        GameItem gameItem = player.getPlayerItems().get(item);
        if (gameItem == null) return;

        if (!gameItem.canMove()) e.setCancelled(true);
        gameItem.onClick(player, e);
    }


}
