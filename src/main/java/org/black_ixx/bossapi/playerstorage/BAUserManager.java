package org.black_ixx.bossapi.playerstorage;

import org.black_ixx.bossapi.BossAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class BAUserManager {

	private BossAPI plugin;
	private int id;

	public BAUserManager(BossAPI plugin){
		this.plugin=plugin;
	}

	public void load(){
		int delay = plugin.getConfig().getInt("SaveDelay")*20;
		if(delay!=0){
			id = new SBASaveSchedule(plugin).runTaskTimer(plugin, delay, delay).getTaskId();
		}

		for (Player p : Bukkit.getOnlinePlayers()){
			playerJoin(p);			
		}				
	}

	@Deprecated
	public void unload(){
		if(id!=0){
			Bukkit.getScheduler().cancelTask(id);
		}

		for (Player p : Bukkit.getOnlinePlayers()){
			playerLeave(p);			
		}				
	}

	protected void playerJoin(Player p){
		BAUser user = new BAUser(plugin, p);
		p.setMetadata("bauser", new FixedMetadataValue(plugin, user));
	}

	protected void playerLeave(Player p){
		BAUser user = getUser(p);
		if(user!=null){
			user.unload(true);
			p.removeMetadata("bauser", plugin);
		}
	}

	public BAUser getUser(Player p){
		if(p.hasMetadata("bauser")){
			return (BAUser) (p.getMetadata("bauser").get(0).value());
		}
		return null;
	}

	public BAOfflineUser getOfflineUser(String name){
		return new BAOfflineUser(plugin, name);
	}


}
