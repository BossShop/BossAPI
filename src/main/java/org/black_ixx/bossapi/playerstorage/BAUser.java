package org.black_ixx.bossapi.playerstorage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.black_ixx.bossapi.BossAPI;
import org.black_ixx.bossapi.events.BALoadUserEvent;
import org.black_ixx.bossapi.events.BASaveUserEvent;
import org.black_ixx.bossapi.events.BAUnloadUserEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BAUser {

	private BossAPI plugin;
	private String name;
	private File file;
	private FileConfiguration config;

	private HashMap<Plugin, Object> plugin_objects = new HashMap<Plugin, Object>();

	/////////////////////////////////////////	  

	public BAUser(BossAPI plugin, Player player){
		this.plugin = plugin;
		this.name = player.getName();
		this.file = new File(plugin.getDataFolder() + File.separator + "players/"+name.charAt(0), name + ".yml");
		this.config = YamlConfiguration.loadConfiguration(file);

		plugin.getServer().getPluginManager().callEvent(new BALoadUserEvent(this, player));
	}

	protected BAUser(BossAPI plugin, String name){
		this.plugin = plugin;
		this.name = name;
		this.file = new File(plugin.getDataFolder() + File.separator + "players/"+name.charAt(0), name + ".yml");
		this.config = YamlConfiguration.loadConfiguration(file);

	}

	/////////////////////////////////////////

	public String getName(){
		return name;
	}

	public FileConfiguration getStorage(){
		return config;
	}

	public BossAPI getAPI(){
		return plugin;
	}

	public void setPluginObject(Plugin plugin, Object o){
		plugin_objects.put(plugin, o);
	}

	public Object getPluginObject(Plugin plugin){
		return plugin_objects.get(plugin);
	}

	public void removePluginObject(Plugin plugin){
		plugin_objects.remove(plugin);
	}
	
	public void configSet(Plugin plugin, String path, Object o){
		config.set(plugin.getName()+"."+path, o);
	}
	
	public Object configGet(Plugin plugin, String path){
		return config.get(plugin.getName()+"."+path);
	}

	/////////////////////////////////////////

	protected void save(boolean b){

		if(b){
			plugin.getServer().getPluginManager().callEvent(new BASaveUserEvent(this));
		}	

		try {
			config.save(file);
		} catch (IOException e) {
			plugin.getLogger().severe("Unable to save Player File of Player "+name+"!");
		}
	}

	protected void unload(boolean b){
		save(b);
		plugin.getServer().getPluginManager().callEvent(new BAUnloadUserEvent(this));
		plugin_objects=null;
	}

	/////////////////////////////////////////


}
