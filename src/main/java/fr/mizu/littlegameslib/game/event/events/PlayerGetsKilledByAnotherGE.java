package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerGetsKilledByAnotherGE extends GameEvent {

    private GamePlayer killer;
    private GamePlayer victim;
    private EntityDamageByEntityEvent event;
    public PlayerGetsKilledByAnotherGE(GameState state, GamePlayer killer, GamePlayer victim, EntityDamageByEntityEvent event) {
        super(victim, state);
        this.victim = victim;
        this.killer = killer;
        this.event = event;
    }

    public GamePlayer getKiller() {
        return killer;
    }

    /**
     *
     * @return the victim
     */
    @Override
    public GamePlayer getPlayer() {
        return victim;
    }

    public EntityDamageByEntityEvent getEvent() {
        return event;
    }
}
