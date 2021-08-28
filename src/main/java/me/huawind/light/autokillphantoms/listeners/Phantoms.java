package me.huawind.light.autokillphantoms.listeners;

import me.huawind.light.autokillphantoms.AutoKillPhantoms;
import me.huawind.light.autokillphantoms.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class Phantoms implements Listener {

	public static Map<String, Integer> AutoKillPhantomsMap = new HashMap<>();

	public static void saveHashMap()
	{
		for(Map.Entry<String, Integer> entry : AutoKillPhantomsMap.entrySet()) {
			CustomConfig.get().set("pkill."+entry.getKey(), entry.getValue());
		}
//important: save the config!
		CustomConfig.save();
	}

	@EventHandler
	public void phantomTarget(EntityTargetEvent e)
	{
		Bukkit.getScheduler().runTaskLater(AutoKillPhantoms.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if(e.getTarget() instanceof Player) {
					if (CustomConfig.get().getInt("pkill."+ e.getTarget().getUniqueId()) == 1) {
						if (e.getEntity() instanceof Phantom) {
							((Phantom) e.getEntity()).damage(100, e.getTarget());
						}
					}
				}
			}
		}, AutoKillPhantoms.getPlugin().getConfig().getLong("phantom-interval"));
	}

	@EventHandler
	public void onDeath(EntityDeathEvent e)
	{
		if(!AutoKillPhantoms.getPlugin().getConfig().getBoolean("phantom-drops"))
		{
			if(e.getEntity() instanceof Phantom) {
				for (ItemStack p : e.getDrops()) {
					p.setType(Material.AIR);
				}
			}
		}
		if(!AutoKillPhantoms.getPlugin().getConfig().getBoolean("phantom-exp"))
		{
			if(e.getEntity() instanceof Phantom)
			{
				e.setDroppedExp(0);
			}
		}
	}

}
