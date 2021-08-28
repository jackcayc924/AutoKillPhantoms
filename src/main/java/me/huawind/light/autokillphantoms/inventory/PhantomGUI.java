package me.huawind.light.autokillphantoms.inventory;

import me.huawind.light.autokillphantoms.AutoKillPhantoms;
import me.huawind.light.autokillphantoms.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.huawind.light.autokillphantoms.listeners.Phantoms.AutoKillPhantomsMap;

public class PhantomGUI  {
	public void openPhantomInven(Player p)
	{
		Inventory gui = Bukkit.createInventory(p, 27, "Phantom Killer");
		ItemStack phantomsEnabled = new ItemStack(Material.GREEN_TERRACOTTA);
		ItemStack phantomsDisabled = new ItemStack(Material.RED_TERRACOTTA);
		ItemMeta phantomsEnabledMeta = phantomsEnabled.getItemMeta();
		ItemMeta phantomsDisabledMeta = phantomsDisabled.getItemMeta();

		if(CustomConfig.get().getInt("pkill."+ p.getUniqueId()) == 1) {
			phantomsEnabledMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
			phantomsEnabled.setItemMeta(phantomsEnabledMeta);
			gui.setItem(13, phantomsEnabled);

			p.openInventory(gui);
		}

		if(CustomConfig.get().getInt("pkill."+ p.getUniqueId()) == 0)
		{
			phantomsDisabledMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Disabled");
			phantomsDisabled.setItemMeta(phantomsDisabledMeta);
			gui.setItem(13, phantomsDisabled);

			p.openInventory(gui);
		}
	}
}
