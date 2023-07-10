package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;

public class LeaveGameGE extends GameEvent {
    public LeaveGameGE(GamePlayer who, GameState state) {
        super(who, state);
    }
}
