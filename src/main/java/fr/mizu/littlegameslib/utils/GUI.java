package fr.mizu.littlegameslib.utils;

import fr.mizu.littlegameslib.LittleGamesLib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class GUI implements Listener {
    private Inventory inventory;
    public GUI(){
        this.inventory = Bukkit.createInventory(null, size(), name());
        Bukkit.getPluginManager().registerEvents(this, LittleGamesLib.Instance);

        for (int x = 0; x < items().length; x ++){
            for (int y = 0; y < items()[x].length; y++){
                inventory.setItem(y+(9*x), items()[x][y]);
            }
        }
    }

    public abstract String name();
    public abstract int size();
    public abstract boolean canMoveObject();
    protected abstract ItemStack[][] items();

    public void setItems(ItemStack[][] items){
        for (int x = 0; x < items.length; x ++){
            for (int y = 0; y < items[x].length; y++){
                inventory.setItem(y+(9*x), items[x][y]);
            }
        }
    }
    public void setItem(ItemStack item, int slot){
        inventory.setItem(slot, item);
    }
    public void clear(){
        inventory.clear();
    }
    public void open(Player player){
        player.openInventory(inventory);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (!inventory.contains(e.getCurrentItem())) return;
        if (!canMoveObject()) e.setCancelled(true);
        onGUIClick(e);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if (e.getInventory().getName().equals(inventory.getName())){
            HandlerList.unregisterAll(this);
        }
    }

    public abstract void onGUIClick(InventoryClickEvent e);

}
