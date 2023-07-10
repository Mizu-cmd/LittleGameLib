package fr.mizu.littlegameslib.commands;

import org.bukkit.entity.Player;

public interface ISubCommand {
    String description();
    String name();
    String syntaxError();
    void perform(Player player, String... args);
}
