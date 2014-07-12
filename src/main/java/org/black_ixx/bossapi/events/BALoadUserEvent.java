package org.black_ixx.bossapi.events;

import org.black_ixx.bossapi.playerstorage.BAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BALoadUserEvent extends Event {

	private static final HandlerList handlers = new HandlerList();


	private BAUser user;
	private Player player;



	public BALoadUserEvent(BAUser user, Player player) {
		this.user=user;
		this.player=player;
	}

	public Player getPlayer(){
		return player;
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