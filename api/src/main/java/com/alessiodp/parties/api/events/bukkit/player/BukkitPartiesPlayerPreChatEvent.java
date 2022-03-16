package com.alessiodp.parties.api.events.bukkit.player;

import com.alessiodp.parties.api.events.bukkit.BukkitPartiesEvent;
import com.alessiodp.parties.api.events.common.player.IPlayerPreChatEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class BukkitPartiesPlayerPreChatEvent extends BukkitPartiesEvent implements IPlayerPreChatEvent, Cancellable {
	private boolean cancelled;
	private final PartyPlayer player;
	private final Party party;
	private String formattedMessage;
	private String message;
	
	public BukkitPartiesPlayerPreChatEvent(PartyPlayer player, Party party, String formattedMessage, String message) {
		super(false);
		this.player = player;
		this.party = party;
		this.formattedMessage = formattedMessage;
		this.message = message;
	}
	
	@Override
	public @NotNull PartyPlayer getPartyPlayer() {
		return player;
	}
	
	@Override
	public @NotNull Party getParty() {
		return party;
	}
	
	@Override
	public @NotNull String getFormattedMessage() {
		return formattedMessage;
	}
	
	@Override
	public void setFormattedMessage(String formattedMessage) {
		this.formattedMessage = formattedMessage;
	}
	
	@Override
	public @NotNull String getMessage() {
		return message;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
