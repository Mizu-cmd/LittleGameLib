package fr.mizu.littlegameslib.game.event.events;

import fr.mizu.littlegameslib.game.GameArea;
import fr.mizu.littlegameslib.game.GamePlayer;
import fr.mizu.littlegameslib.game.GameState;
import fr.mizu.littlegameslib.game.event.GameEvent;

public class PlayerEnterAreaGE extends GameEvent {
    private GameArea area;
    public PlayerEnterAreaGE(GamePlayer who, GameState state, GameArea area) {
        super(who, state);
        this.area = area;
    }
    public GameArea getArea() {
        return area;
    }
}
