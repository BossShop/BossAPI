package org.black_ixx.bossapi.pointplugins.supported;

import org.black_ixx.bossapi.BossAPI;
import org.bukkit.plugin.Plugin;

import pgDev.bukkit.CommandPoints.CommandPoints;
import pgDev.bukkit.CommandPoints.CommandPointsAPI;

public class SBAPointsPluginCommandPoints extends SBAPointsPluginSupported{

	private CommandPointsAPI p;
	private CommandPoints cp;

	private BossAPI plugin;

	@Override
	public boolean setPoints(String name, int points) {
		p.setPoints(name, points, plugin);
		return true;
	}

	@Override
	public int getPoints(String name) {
		return p.getPoints(name, plugin);
	}

	@Override
	public boolean givePoints(String name, int points) {
		p.addPoints(name, points, null, plugin);
		return true;
	}

	@Override
	public boolean takePoints(String name, int points) {
		if(getPoints(name)<points){
			return false;
		}
		p.removePoints(name, points, null, plugin);
		return true;
	}

	@Override
	public Plugin getPointPlugin() {
		return cp;
	}

	@Override
	public boolean load() {
		plugin = BossAPI.getAPI();
		Plugin commandPoints = plugin.getServer().getPluginManager().getPlugin("CommandPoints");
		if (commandPoints != null) {
			cp = ((CommandPoints)commandPoints);
			p = cp.getAPI();
			return true;
		}else{
			return false;
		}
	}

}
