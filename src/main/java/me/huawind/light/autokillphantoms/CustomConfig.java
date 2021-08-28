package me.huawind.light.autokillphantoms;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

	private static File file;
	private static FileConfiguration customFile;

	//find or generates custom config
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("AutoKillPhantoms").getDataFolder(), "data.yml");

		if (!file.exists()){
			try{
				file.createNewFile();
			}catch (IOException e){
				//o
			}

		}
		customFile = YamlConfiguration.loadConfiguration(file);

	}

	public static FileConfiguration get(){
		return customFile;
	}

	public  static void save(){
		try{
			customFile.save(file);
		}catch (IOException e){
			System.out.println("Couldnt save file");
		}
	}

	public static void reload(){
		customFile = YamlConfiguration.loadConfiguration(file);
	}

}