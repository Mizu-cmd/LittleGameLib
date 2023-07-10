package fr.mizu.littlegameslib.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ComplexCommand implements CommandExecutor, TabCompleter {

    private final HashMap<String, ISubCommand> subCommands = new HashMap<>();
    private String name;
    private String description;
    private String syntaxError;

    private String permission;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if(subCommands.isEmpty()) return false;
        if (permission != null && !player.hasPermission(this.permission)) return false;

        ISubCommand subCommand = subCommands.getOrDefault(args[0].toLowerCase(), null);

        if (subCommand == null) return false;

        String[] subCommandArgs = Arrays.copyOfRange(args, 1, args.length);
        subCommand.perform(player, subCommandArgs);

        return false;
    }

    //set the name of the command
    public void setName(String name) {
        this.name = name;
    }

    //set the description of the command
    public void setDescription(String description) {
        this.description = description;
    }

    //set the string that is displayed when the player makes a mistake while writing the command
    public void setSyntaxError(String syntaxError) {
        this.syntaxError = syntaxError;
    }

    //set the permission the player needs to have in order to execute any of the subcommands
    public void setPermission(String permission){
        this.permission = permission;
    }

    //this method adds new subcommands that are called when the player write "/skywars join" for example
    public void addSubCommand(String name, ISubCommand subCommand){
        subCommands.putIfAbsent(name, subCommand);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> t = new ArrayList<>();
        for(String sub : subCommands.keySet()){
            t.add(sub);
        }
        return t;
    }
}
