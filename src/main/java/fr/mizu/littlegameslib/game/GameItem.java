package fr.mizu.littlegameslib.game;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class GameItem{

     public abstract ItemStack itemStack();
     public abstract boolean isUnique();
     public abstract boolean canDrop();
     public abstract boolean canPlace();
     public abstract boolean canMove();
     public abstract boolean canInteract();
     public void onClick(GamePlayer gamePlayer, InventoryClickEvent e) {}
     public void onDrop(GamePlayer gamePlayer, PlayerDropItemEvent e) {}
     public void onInteract(GamePlayer gamePlayer, PlayerInteractEvent e) {}
}
