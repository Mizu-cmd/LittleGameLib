package fr.mizu.littlegameslib.commands;

import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String name();

    public abstract String syntaxError();
    public abstract void perform(Player player, String[] subcommandArgs);
}
