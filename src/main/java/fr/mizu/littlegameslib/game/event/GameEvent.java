package fr.mizu.littlegameslib.game.event;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;

public abstract class GameEvent {
    private GamePlayer player;
    private GameState state;

    public GameEvent(GamePlayer who, GameState state){
        this.player = who;
        this.state = state;
    }

    public GamePlayer getPlayer() {
        return player;
    }

    public GameState getState() {
        return state;
    }
}
