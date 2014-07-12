package org.black_ixx.bossapi.events;

import org.black_ixx.bossapi.playerstorage.BAUser;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BAUnloadUserEvent extends Event {

	private static final HandlerList handlers = new HandlerList();


	private BAUser user;



	public BAUnloadUserEvent(BAUser user) {
		this.user=user;
	}


	public BAUser getUser(){
		return user;
	}
	



	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}