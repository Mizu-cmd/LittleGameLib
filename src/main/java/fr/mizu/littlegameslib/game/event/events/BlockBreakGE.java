package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;

public class BlockBreakGE extends GameEvent {
    public BlockBreakGE(GamePlayer who, GameState state) {
        super(who, state);
    }
}
