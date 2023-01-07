package fr.mizu.littlegameslib.misc;

import fr.mizu.littlegameslib.game.GameItem;
import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameTeam;
import fr.mizu.littlegameslib.misc.gui.TeamGUI;
import fr.mizu.littlegameslib.utils.GUI;
import fr.mizu.littlegameslib.utils.ItemUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class teamItem extends GameItem {
    @Override
    public ItemStack itemStack() {
        return ItemUtil.makeItem(Material.WOOL, "§6Change team", false);
    }

    @Override
    public boolean isUnique() {
        return false;
    }

    @Override
    public boolean canDrop() {
        return false;
    }

    @Override
    public boolean canPlace() {
        return false;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canInteract() {
        return false;
    }

    @Override
    public void onInteract(GamePlayer gamePlayer, PlayerInteractEvent e) {
        GUI gui = new TeamGUI();
        for (int i = 0; i < gamePlayer.getGame().getTeams().size(); i++){
            GameTeam team = gamePlayer.getGame().getTeams().get(i);

            byte color = 0;
            if (team.getColor() == Color.RED){
                color = 14;
            }
            String[] lore = new String[team.getPlayers().size()];
            for (int j  = 0; j < lore.length; j++){
                lore[j] = "- §6§b"+ team.getPlayers().get(j).getOnlinePlayer().getName();
            }

            ItemStack item = ItemUtil.makeItem(Material.WOOL, color, team.getName(), false, lore);
            gui.setItem(item, i);
        }
        gui.open(gamePlayer.getOnlinePlayer());
    }
}