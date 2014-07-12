package org.black_ixx.bossapi.pointplugins.supported;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SBAPointsPluginPlayerPoints extends SBAPointsPluginSupported{

	private PlayerPoints p;


	@Override
	public boolean setPoints(String name, int points) {
		return p.getAPI().set(name, points);
	}

	@Override
	public int getPoints(String name) {
		return p.getAPI().look(name);
	}

	@Override
	public boolean givePoints(String name, int points) {
		return p.getAPI().give(name, points);
	}

	@Override
	public boolean takePoints(String name, int points) {
		return p.getAPI().take(name, points);
	}

	@Override
	public Plugin getPointPlugin() {
		return p;
	}

	@Override
	public boolean load() {
		final Plugin plugin= Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints");	     
		if (plugin!= null) {
			p = (PlayerPoints.class.cast(plugin));
			return true;
		} else {
			return false;
		}
	}

}
