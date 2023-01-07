package fr.mizu.littlegameslib.commands;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;

public abstract class RootCommand {

    public HashMap<String, SubCommand> subCommands = new HashMap<>();
    public abstract String name();
    public abstract String description();

    public abstract String syntaxError();

    public void addSubCommand(SubCommand subCommand)
    {
        subCommands.put(subCommand.name(), subCommand);
    }
    public void perform(Player player, String[] args)
    {
        if (args.length < 1  || !subCommands.containsKey(args[0])) return;

        String subCommand = args[0];
        subCommands.get(subCommand).perform(player, Arrays.copyOfRange(args, 1, args.length));
    }

}
