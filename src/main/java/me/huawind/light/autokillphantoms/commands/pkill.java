package me.huawind.light.autokillphantoms.commands;

import me.huawind.light.autokillphantoms.AutoKillPhantoms;
import me.huawind.light.autokillphantoms.inventory.PhantomGUI;
import me.huawind.light.autokillphantoms.listeners.PhantomGUIHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pkill implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		PhantomGUI inven = new PhantomGUI();
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("pkill")) {
			if (sender.hasPermission("light.pkill")) {
				if (args.length == 0) {
					inven.openPhantomInven(p);
				}
			}
		}
		if (sender.hasPermission("light.pkill")) {
			if (args.length == 1) {
				if (sender.hasPermission("light.pkillreload")) {
					if (args[0].equalsIgnoreCase("reload")) {
						p.sendMessage(ChatColor.GREEN + "PKILL: Successfully reloaded!");
						AutoKillPhantoms.getPlugin().reloadConfig();
					}
				}
			}
		}
		return false;
	}
}
