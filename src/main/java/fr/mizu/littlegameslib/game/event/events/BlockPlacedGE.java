package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlacedGE extends GameEvent {
    private BlockPlaceEvent event;
    public BlockPlacedGE(GamePlayer who, GameState state, BlockPlaceEvent event) {
        super(who, state);
        this.event = event;
    }
    public BlockPlaceEvent getEvent() {
        return event;
    }
}
