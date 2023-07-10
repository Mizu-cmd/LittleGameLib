package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;

public class JoinGameGE extends GameEvent {
    public JoinGameGE(GamePlayer who, GameState state) {
        super(who, state);
    }
}
