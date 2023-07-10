package fr.mizu.littlegameslib.game;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class GameTeam {
    private List<GamePlayer> players = new ArrayList<>();
    private int size;
    private boolean isAlive;
    private String name;
    private Color color;
    private ChatColor chatColor;
    private List<Location> teamSpawns;
    private boolean allowTeamDamage = false;

    public GameTeam(Color dyeColor, ChatColor chatColor, String name, int size)
    {
        this.name = name;
        this.color = dyeColor;
        this.chatColor = chatColor;
        this.teamSpawns = new ArrayList<>();
        this.size = size;
        this.isAlive = true;
    }

    public void sendMessage(String msg)
    {
        for (GamePlayer player : this.getPlayers()){
            if (player.isOnline())
                player.getOnlinePlayer().sendMessage(msg);
        }
    }

    public String getName() {
        return name;
    }

    public List<Location> getTeamSpawns() {
        return teamSpawns;
    }

    public void addTeamSpawn(Location location){
        this.teamSpawns.add(location);
    }

    public Color getColor() {
        return color;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public boolean allowTeamDamage() {
        return allowTeamDamage;
    }

    public void setAllowTeamDamage(boolean allowTeamDamage) {
        this.allowTeamDamage = allowTeamDamage;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public boolean addPlayer(GamePlayer player){
        if (getPlayers().size() >= size){
            return false;
        }
        if (player.getTeam() != null){
            player.getTeam().removePlayer(player);
        }
        this.players.add(player);
        player.setTeam(this);
        return true;
    }

    public void removePlayer(GamePlayer player){
        if (!this.players.contains(player)) return;
        this.players.remove(player);
        player.setTeam(null);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public boolean isFull(){
        return players.size() >= size;
    }
    public int getSize() {
        return size;
    }
}
