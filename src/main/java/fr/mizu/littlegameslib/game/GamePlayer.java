package fr.mizu.littlegameslib.game;

import fr.mizu.littlegameslib.Internationalization.I18N;
import fr.mizu.littlegameslib.game.types.PlayerType;
import fr.mizu.littlegameslib.misc.bossbar.BossBar;
import fr.mizu.littlegameslib.misc.scoreboard.GameBoard;
import fr.mizu.littlegameslib.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GamePlayer {
    private OfflinePlayer player;
    private GameTeam team;
    private Game game;
    private PlayerType type;
    private I18N language;
    private GameBoard board;
    private BossBar bossBar;
    private HashMap<ItemStack, GameItem> playerItems = new HashMap<>();
    public GamePlayer(OfflinePlayer player)
    {
        this.player = player;
        this.type = PlayerType.PLAYER;
    }

    public void resetPlayer(){
        Player p = getOnlinePlayer();
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setLevel(0);
        p.setExp(0);
        p.getInventory().clear();
        p.getActivePotionEffects().clear();
        this.destroyBoard();
        this.destroyBossBar();
    }

    public void setPlayer(OfflinePlayer player) {
        this.player = player;
    }

    protected void setGame(Game game) {
        this.game = game;
    }

    public I18N getLanguage() {
        return language;
    }

    public void setLanguage(I18N language) {
        this.language = language;
    }
    public Player getOnlinePlayer(){
        return player.getPlayer();
    }
    public boolean isOnline(){
        return getOnlinePlayer() != null;
    }
    public void setTeam(GameTeam team){
        GameTeam oldTeam = this.team;
        this.team = team;
        if(oldTeam != null) oldTeam.removePlayer(this);
    }

    public GameTeam getTeam() {
        return team;
    }

    public Game getGame() {
        return game;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public boolean isInGame(){
        return this.game != null;
    }

    public List<GameArea> getAreas(){
        if(!this.isInGame()) return null;
        if(!this.isOnline()) return null;
        List<GameArea> areas = new ArrayList<GameArea>();
        for(GameArea area : this.getGame().getAreas()){
            if(LocationUtil.isInArea(this.getOnlinePlayer().getLocation(), area.getLoc1(), area.getLoc2()))
                areas.add(area);
        }
        return areas;
    }

    /**
     *
     * @return the player's scoreboard or a new empty one if there is none
     */
    public GameBoard getBoard(){
        if (board != null) return board;
        else {
            this.board = new GameBoard(this, "empty");
            return board;
        }
    }

    /**
     * use this method to hide scoreboard from player
     */
    public void destroyBoard(){
        if (!hasBoard()) return;

        player.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        this.board = null;
    }

    public boolean hasBoard(){
        return board != null;
    }

    /**
     *
     * @return the player's boss bar or a new empty one if there is none
     */
    public BossBar getBossBar(){
        if (bossBar != null) return bossBar;
        else {
            this.bossBar = new BossBar(player.getPlayer(), "empty");
            return bossBar;
        }
    }

    public boolean hasBossBar(){
        return bossBar != null;
    }

    public void destroyBossBar(){
        if (!hasBossBar()) return;

        bossBar.destroy();
    }

    public HashMap<ItemStack, GameItem> getPlayerItems() {
        return playerItems;
    }
    public void addItem(GameItem item){
        playerItems.put(item.itemStack(), item);
        getOnlinePlayer().getInventory().addItem(item.itemStack());
    }

    public void setItem(GameItem item, int slot){
        playerItems.put(item.itemStack(), item);
        getOnlinePlayer().getInventory().setItem(slot, item.itemStack());
    }

    public GameSettings getSettings(){
        return game.getSettings(type);
    }
}
