package fr.mizu.littlegameslib.misc.scoreboard;

import fr.mizu.littlegameslib.game.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class GameBoard {
    private GamePlayer player;
    private Objective objective;
    private Scoreboard scoreboard;

    public GameBoard(GamePlayer player, String title){
        this.player = player;

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        this.scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("title", "dummy");
        objective.setDisplayName(title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        this.objective = objective;
        player.getOnlinePlayer().setScoreboard(scoreboard);
    }

    public void setLine(int line, String text){
        Score score = this.objective.getScore(text);
        score.setScore(line);
    }

    public void setTitle(String title){
        this.scoreboard.getObjective("title").setDisplayName(title);
    }

}
