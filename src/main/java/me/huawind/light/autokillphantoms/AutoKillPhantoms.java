package me.huawind.light.autokillphantoms;

import me.huawind.light.autokillphantoms.commands.pkill;
import me.huawind.light.autokillphantoms.listeners.PhantomGUIHandler;
import me.huawind.light.autokillphantoms.listeners.Phantoms;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static me.huawind.light.autokillphantoms.listeners.Phantoms.*;

public final class AutoKillPhantoms extends JavaPlugin {
	private static Plugin plugin;

	public static Plugin getPlugin() {
		return plugin;
	}



	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[" +
				ChatColor.AQUA + "AutoKillPhantoms" + ChatColor.AQUA + "]" + ChatColor.DARK_GREEN + " Successfully loaded! v1.0");

		this.getCommand("pkill").setExecutor(new pkill());

		CustomConfig.setup();
		CustomConfig.get().addDefault("pkill.", null);

		getConfig().options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new Phantoms(), this);
		getServer().getPluginManager().registerEvents(new PhantomGUIHandler(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		saveHashMap();
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "[" +
				ChatColor.RED + "AutoKillPhantoms" + ChatColor.RED+ "]" + ChatColor.RED + " Disabled");
	}
}
