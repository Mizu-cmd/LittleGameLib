package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerTakesDamageGE extends GameEvent {

    private EntityDamageEvent event;
    public PlayerTakesDamageGE(GamePlayer who, GameState state, EntityDamageEvent event) {
        super(who, state);
        this.event = event;
    }
    public EntityDamageEvent getEvent() {
        return event;
    }
}
