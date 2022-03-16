package com.alessiodp.parties.api.events.velocity.player;

import com.alessiodp.parties.api.enums.JoinCause;
import com.alessiodp.parties.api.events.common.player.IPlayerPostJoinEvent;
import com.alessiodp.parties.api.events.velocity.VelocityPartiesEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VelocityPartiesPlayerPostJoinEvent extends VelocityPartiesEvent implements IPlayerPostJoinEvent {
	private final PartyPlayer player;
	private final Party party;
	private final JoinCause cause;
	private final PartyPlayer inviter;
	
	public VelocityPartiesPlayerPostJoinEvent(PartyPlayer player, Party party, JoinCause cause, PartyPlayer inviter) {
		this.player = player;
		this.party = party;
		this.cause = cause;
		this.inviter = inviter;
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
	public @NotNull JoinCause getCause() {
		return cause;
	}
	
	@Override
	public @Nullable PartyPlayer getInviter() {
		return inviter;
	}
}
