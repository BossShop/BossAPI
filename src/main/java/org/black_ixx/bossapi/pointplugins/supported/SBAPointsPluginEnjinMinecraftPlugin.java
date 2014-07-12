package org.black_ixx.bossapi.pointplugins.supported;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.enjin.officialplugin.EnjinMinecraftPlugin;
import com.enjin.officialplugin.points.PointsAPI;
import com.enjin.officialplugin.points.PointsAPI.Type;

public class SBAPointsPluginEnjinMinecraftPlugin extends SBAPointsPluginSupported{

	private EnjinMinecraftPlugin emp;
	
	@Override
	public boolean setPoints(String name, int points) {
		try {
			PointsAPI.modifyPointsToPlayer(name, points, Type.SetPoints);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public int getPoints(String name) {
		try {
			return PointsAPI.getPointsForPlayer(name);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean givePoints(String name, int points) {
		try {
			PointsAPI.modifyPointsToPlayer(name, points, Type.AddPoints);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean takePoints(String name, int points) {
		if(getPoints(name)<points){
			return false;
		}
		try {
			PointsAPI.modifyPointsToPlayer(name, points, Type.RemovePoints);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Plugin getPointPlugin() {
		return emp;
	}

	@Override
	public boolean load() {
		Plugin plugin= Bukkit.getServer().getPluginManager().getPlugin("EnjinMinecraftPlugin");	
		if (plugin!=null) {
			emp = (EnjinMinecraftPlugin) plugin;
			return true;
		} else {
			return false;
		}
	}

}
