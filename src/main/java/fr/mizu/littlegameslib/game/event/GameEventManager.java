package fr.mizu.littlegameslib.game.event;

import fr.mizu.littlegameslib.annotation.GameEventHandler;
import fr.mizu.littlegameslib.game.GameListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GameEventManager {

    private List<GameListener> registered = new ArrayList<>();

    public void register(GameListener listener){
        if (!registered.contains(listener))
            registered.add(listener);
    }

    public void unregister(GameListener listener){
        if(registered.contains(listener))
            registered.remove(listener);
    }

    public void call(GameEvent event){
        for (GameListener listener : registered){
            Method[] methods = listener.getClass().getMethods();
            for(Method method : methods){
                if (method.isAnnotationPresent(GameEventHandler.class)){
                    try {
                        Class<?>[] parameters = method.getParameterTypes();
                        if (parameters.length == 1 && event.getClass() == parameters[0]){
                            method.invoke(listener.getClass().newInstance(), event);
                        }
                    } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
