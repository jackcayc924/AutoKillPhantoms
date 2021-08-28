package me.huawind.light.autokillphantoms.listeners;

import me.huawind.light.autokillphantoms.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

import static me.huawind.light.autokillphantoms.listeners.Phantoms.AutoKillPhantomsMap;
import static me.huawind.light.autokillphantoms.listeners.Phantoms.saveHashMap;

public class PhantomGUIHandler implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();

		if(e.getView().getTitle().equalsIgnoreCase("Phantom Killer"))
		{
			if(e.getRawSlot() == 13)
			{
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled"))
					{
						AutoKillPhantomsMap.put(e.getWhoClicked().getUniqueId().toString(), 0);
						saveHashMap();
						e.setCancelled(true);
						p.closeInventory();
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "Disabled"))
					{
						AutoKillPhantomsMap.put(e.getWhoClicked().getUniqueId().toString(), 1);
						saveHashMap();
						e.setCancelled(true);
						p.closeInventory();
					}
			}
			if(e.getRawSlot() != 13)
			{
				e.setCancelled(true);
			}
		}
	}
}
