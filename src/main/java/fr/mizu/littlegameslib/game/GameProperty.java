package fr.mizu.littlegameslib.game;

public class GameProperty {
    private final int maxPlayers;
    private final int minPlayers;
    private final boolean useTeam;

    public GameProperty(int maxPlayers, int minPlayers, boolean useTeam){
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.useTeam = useTeam;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public boolean isUseTeam() {
        return useTeam;
    }
}
