package org.black_ixx.bossapi.playerstorage;

import org.black_ixx.bossapi.BossAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SBASaveSchedule extends BukkitRunnable{


	private BossAPI plugin;

	public SBASaveSchedule(BossAPI plugin){
		this.plugin=plugin;
	}


	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()){
			BAUser u = plugin.getUserManager().getUser(p);
			if(u!=null){
				u.save(true);
			}
		}
	}

}
