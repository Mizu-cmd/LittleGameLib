package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeGE extends GameEvent {
    private FoodLevelChangeEvent event;
    public FoodLevelChangeGE(GamePlayer who, GameState state, FoodLevelChangeEvent e) {
        super(who, state);
        this.event = e;
    }
    public FoodLevelChangeEvent getEvent() {
        return event;
    }
}
