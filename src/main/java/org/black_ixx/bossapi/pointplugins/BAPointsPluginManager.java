package org.black_ixx.bossapi.pointplugins;

import java.util.ArrayList;
import java.util.List;

import org.black_ixx.bossapi.BossAPI;
import org.black_ixx.bossapi.pointplugins.supported.SBAPointsPluginCommandPoints;
import org.black_ixx.bossapi.pointplugins.supported.SBAPointsPluginEnjinMinecraftPlugin;
import org.black_ixx.bossapi.pointplugins.supported.SBAPointsPluginPlayerPoints;
import org.black_ixx.bossapi.pointplugins.supported.SBAPointsPluginSupported;

public class BAPointsPluginManager {

	private BossAPI plugin;

	private List<BAPointsPluginInterface> pis;

	private BAPointsPluginInterface pi;
	private String pp;

	public BAPointsPluginManager(BossAPI plugin){
		this.plugin=plugin;
		
		pis = new ArrayList<BAPointsPluginInterface>();

		pp = plugin.getConfig().getString("PointsPlugin");
		
		if(pp==null){
			pp="PlayerPoints";
		}
		
		loadInterface(new SBAPointsPluginPlayerPoints());
		loadInterface(new SBAPointsPluginCommandPoints());
		loadInterface(new SBAPointsPluginEnjinMinecraftPlugin());

	}
	
	
	private void loadInterface(SBAPointsPluginSupported ps){
		if(ps.load()){
			addInterface(ps);
		}
	}


	public void addInterface(BAPointsPluginInterface pi){
		pis.add(pi);
		plugin.getLogger().info("Hooked into "+pi.getPointPlugin().getName()+".");	

		if(this.pi==null |! selectInterface()){
			this.pi=pi;
		}
	}

	private boolean selectInterface(){
		for (BAPointsPluginInterface pi : pis){
			if(pi!=null){
				if(pi.getPointPlugin()!=null){
					if(pi.getPointPlugin().getName().equalsIgnoreCase(pp)){
						this.pi=pi;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	public BAPointsPluginInterface getInterface(){
		return pi;
	}


}
