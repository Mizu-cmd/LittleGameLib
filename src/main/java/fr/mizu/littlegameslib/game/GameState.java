package fr.mizu.littlegameslib.game;

import fr.mizu.littlegameslib.LittleGamesLib;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public abstract class GameState {

    public abstract String name();
    private Game game;
    public abstract boolean useStateDuration();
    public abstract int stateTime();
    private int stateTime;
    private BukkitTask loop;

    protected void startState(){
        onBegin();

        if (!useStateDuration()) return;
        this.stateTime = stateTime();

        this.loop = new BukkitRunnable() {
            @Override
            public void run() {
                if (stateTime <= 0){
                    stopState();
                }
                onUpdate(stateTime);
                stateTime--;
            }
        }.runTaskTimerAsynchronously(LittleGamesLib.Instance, 20L, 20L);
    }
    protected void stopState(){
        //send event
        loop.cancel();
        onEnd();
    }

    protected void cancelState(){
        if (loop != null)  loop.cancel();
        setGame(null);
        stateTime = 0;
    }

    /**
     * called on the beginning of the state
     */
    public abstract void onBegin();
    /**
     * called on the end of the state
     */
    public abstract void onEnd();

    /**
     * onUpdate() is called every seconds
     * @param stateTime the current state time
     */
    public abstract void onUpdate(int stateTime);

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<GamePlayer> getPlayers(){
        return game.getPlayers();
    }

    public void sendMessageToAllPlayers(String message){
        getPlayers().forEach(gamePlayer -> gamePlayer.getOnlinePlayer().sendMessage(message));
    }
}
