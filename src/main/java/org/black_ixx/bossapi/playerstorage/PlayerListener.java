package org.black_ixx.bossapi.playerstorage;

import org.black_ixx.bossapi.BossAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	
	private BossAPI plugin;
	
	public PlayerListener(BossAPI plugin){
		this.plugin=plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		plugin.getUserManager().playerJoin(e.getPlayer());
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		plugin.getUserManager().playerLeave(e.getPlayer());
	}
	
	@EventHandler
	public void onLeave(PlayerKickEvent e){
		plugin.getUserManager().playerLeave(e.getPlayer());
	}
	

}
