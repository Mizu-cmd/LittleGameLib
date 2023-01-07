package fr.mizu.littlegameslib.game;

import fr.mizu.littlegameslib.LittleGamesLib;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

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
        loop.cancel();
        setGame(null);
        stateTime = 0;
    }

    public abstract void onBegin();
    public abstract void onEnd();
    public abstract void onUpdate(int stateTime);

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
