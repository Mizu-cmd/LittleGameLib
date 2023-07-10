package fr.mizu.littlegameslib.game;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import fr.mizu.littlegameslib.LittleGamesLib;
import fr.mizu.littlegameslib.annotation.GameEventHandler;
import fr.mizu.littlegameslib.arena.Arena;
import fr.mizu.littlegameslib.game.event.GameEventManager;
import fr.mizu.littlegameslib.game.event.events.JoinGameGE;
import fr.mizu.littlegameslib.game.event.events.LeaveGameGE;
import fr.mizu.littlegameslib.game.types.IPlayerType;
import fr.mizu.littlegameslib.game.types.PlayerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {
    private String id;
    private GameState state;
    private Arena arena;
    private World world;
    private HashMap<IPlayerType, GameSettings> settings = new HashMap<>();
    private GameProperty properties;
    private List<GamePlayer> players;
    private List<GameArea> areas;
    private List<GameTeam> teams;
    private GameEventManager eventManager;
    private HashMap<GamePlayer, Integer> kills = new HashMap<>();

    public Game(String id, Arena arena, GameProperty properties, GameState state){
        this.world = arena.cloneArena();
        this.id = id;
        this.arena = arena;
        this.players = new ArrayList<>();
        this.settings.put(PlayerType.PLAYER, new GameSettings());
        this.areas = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.properties = properties;
        this.eventManager = new GameEventManager();
        this.setState(state);
        arena.getPlugin().getGames().add(this);
    }

    public void joinGame(GamePlayer player){
        if (player.isInGame()) return;
        if (!player.isOnline()) return;

        if (players.size() >= this.properties.getMaxPlayers()) return;

        player.getOnlinePlayer().teleport(world.getSpawnLocation());

        player.setLanguage(arena.getPlugin().getDefaultLanguage());
        player.setGame(this);

        this.players.add(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (properties.isUseTeam()){
                    for (GameTeam team : teams){
                        if (!team.isFull()){
                            team.addPlayer(player);
                            player.setItem(arena.getPlugin().getTeamItemStack(), 4);
                            break;
                        }
                    }
                }
            }
        }.runTaskLaterAsynchronously(LittleGamesLib.Instance, 5l);

        eventManager.call(new JoinGameGE(player, state));
    }
    public void leaveGame(GamePlayer player){
        if (!player.isInGame()) return;

        this.players.remove(player);


        if (player.isOnline()){
            player.getOnlinePlayer().teleport(getMainLobby());
        }

        if (player.getTeam() != null) player.getTeam().removePlayer(player);

        player.getPlayerItems().clear();
        player.setGame(null);
        player.resetPlayer();

        eventManager.call(new LeaveGameGE(player, state));

        if (players.size() == 0) stopGame();
    }
    public void stopGame(){
        for (GamePlayer gamePlayer : this.players){
            Player player = gamePlayer.getOnlinePlayer();
            player.teleport(arena.getMainLobby());
            gamePlayer.setGame(null);
            gamePlayer.setTeam(null);
            gamePlayer.setType(PlayerType.PLAYER);
            gamePlayer.resetPlayer();
        }
        this.resetWorld();
        players.clear();
        state.cancelState();
        teams.clear();
        kills.clear();
        arena.getPlugin().getGames().remove(this);
    }
    private void resetWorld(){
        new BukkitRunnable() {
            @Override
            public void run() {
                MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
                MVWorldManager worldManager = core.getMVWorldManager();

                worldManager.deleteWorld(world.getName(), true, true);
                worldManager.removeWorldFromConfig(world.getName());
            }
        }.runTaskLater(LittleGamesLib.Instance, 20*5);
    }

    public int getKills(GamePlayer player){
        return this.kills.get(player);
    }
    public void registerGameArena(GameArea gameArea){
        this.areas.add(gameArea);
    }
    public void registerAreas(GameArea...areas){
        for(GameArea area : areas){
            this.registerGameArena(area);
        }
    }
    public GameArea getGameArea(String name){
        for(GameArea area : areas){
            if (area.getName().equals(name)) return area;
        }
        return null;
    }

    public void setState(GameState state){
        this.state = state;
        state.setGame(this);
        state.startState();
    }

    public GameState getState() {
        return state;
    }

    public void increaseKill(GamePlayer player, int count){
        int playerKills = kills.getOrDefault(player, 0);
        kills.put(player, playerKills += count);
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public List<GameArea> getAreas() {
        return areas;
    }

    public void addArea(GameArea area){
        areas.add(area);
    }

    public void addEventListener(GameListener listener) {
        eventManager.register(listener);
    }

    public void removeEventListener(GameListener listener){
        eventManager.unregister(listener);
    }

    public Arena getArena() {
        return arena;
    }

    public List<GameTeam> getTeamsAlive(){
        List<GameTeam> teams = new ArrayList<>();
        for(GameTeam team : getTeams()){
            if(team.isAlive()) teams.add(team);
        }
        return teams;
    }

    public GameProperty getProperties() {
        return properties;
    }

    public List<GameTeam> getTeams() {
        return teams;
    }

    public GameTeam getTeamByName(String name){
        for (GameTeam team : this.getTeams()){
            if (team.getName().equals(name)) return team;
        }
        return null;
    }

    public void addTeam(GameTeam team){
        teams.add(team);
    }

    public String getId() {
        return id;
    }

    public World getWorld() {
        return world;
    }

    public Location getLoc1(){
        Location loc = arena.getLoc1();
        loc.setWorld(world);
        return loc;
    }

    public Location getLoc2(){
        Location loc = arena.getLoc2();
        loc.setWorld(world);
        return loc;
    }

    public Location getLobby(){
        Location loc = arena.getLobby();
        loc.setWorld(world);
        return loc;
    }

    public Location getMainLobby(){
        Location loc = arena.getMainLobby();
        return loc;
    }

    public Location getSpawn(int index){
        Location loc = arena.getSpawns().get(index);
        loc.setWorld(world);
        return loc;
    }

    public List<Location> getSpawns(){
        List<Location> locs = new ArrayList<>();
        for (Location loc : arena.getSpawns()){
            loc.setWorld(world);
            locs.add(loc);
        }
        return locs;
    }

    public GameEventManager getEventManager() {
        return eventManager;
    }

    public GameSettings getSettings(PlayerType type){
        return this.settings.get(type);
    }
}
