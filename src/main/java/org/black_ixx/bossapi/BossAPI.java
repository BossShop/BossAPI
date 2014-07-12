package org.black_ixx.bossapi;

import org.black_ixx.bossapi.configuration.BAConfig;
import org.black_ixx.bossapi.managers.StringManager;
import org.black_ixx.bossapi.playerstorage.BAUserManager;
import org.black_ixx.bossapi.playerstorage.PlayerListener;
import org.black_ixx.bossapi.pointplugins.BAPointsPluginManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BossAPI extends JavaPlugin{
	
	///////////////////////////////////////// Variables
	
	private static BossAPI plugin;
	
	private BAPointsPluginManager pointsplugin;
	private StringManager string;
	private BAUserManager user;

	///////////////////////////////////////// Enable/Disable/Reload
	
	@Override
	public void onEnable(){
		plugin=this;
		
		pointsplugin = new BAPointsPluginManager(this);
		string = new StringManager();
		
		if(user==null){
		user = new BAUserManager(this);
		}
		
		new BukkitRunnable() {			
			@Override
			public void run() {
				user.load();				
			}
		}.runTaskLater(plugin, 5);
		
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

		if(getConfig().getString("CreationVersion")==null){
			FileConfiguration c = getConfig();
			c.set("CreationVersion", getDescription().getVersion());
			c.set("PointsPlugin", "auto-detect");
			c.set("SaveDelay", 60*20);
			saveConfig();
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDisable(){
		user.unload();
	}

	///////////////////////////////////////// Main Methods
	
	public static BossAPI getAPI(){
		return plugin;
	}
	
	public BAPointsPluginManager getPointsPluginManager(){
		return pointsplugin;
	}
	
	public BAConfig createConfig(Plugin plugin, String file_name){
		return new BAConfig(plugin, file_name);
	}
	
	public StringManager getStringManager(){
		return string;
	}
	
	public BAUserManager getUserManager(){
		return user;
	}

}
