package fr.mizu.littlegameslib.commands;

import fr.mizu.littlegameslib.LittleGamesLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<RootCommand> commands = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        for(int i = 0; i < commands.size(); i++){

            RootCommand rootCommand = commands.get(i);

            if (rootCommand.name().equalsIgnoreCase(command.getName())){
                rootCommand.perform(player, args);
            }
        }

        return true;
    }

    public void addCommand(RootCommand command)
    {
        commands.add(command);
        LittleGamesLib.Instance.getCommand(command.name()).setExecutor(this);
    }

}
