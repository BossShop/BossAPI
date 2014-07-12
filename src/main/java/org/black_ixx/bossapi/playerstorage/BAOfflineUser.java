package org.black_ixx.bossapi.playerstorage;

import org.black_ixx.bossapi.BossAPI;

public class BAOfflineUser extends BAUser{

	public BAOfflineUser(BossAPI plugin, String name) {
		super(plugin, name);
	}
	
	
	public void unload(){
		unload(false);
	}

}
