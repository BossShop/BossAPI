package org.black_ixx.bossapi.pointplugins;

import org.bukkit.plugin.Plugin;

public interface BAPointsPluginInterface {
	
	public boolean setPoints(String name, int points);
	
	public int getPoints(String name);
	
	public boolean givePoints(String name, int points);
	
	public boolean takePoints(String name, int points);
	
	public Plugin getPointPlugin();
	
	

}
