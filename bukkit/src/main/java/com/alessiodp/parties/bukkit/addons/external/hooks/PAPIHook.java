package com.alessiodp.parties.bukkit.addons.external.hooks;

import com.alessiodp.parties.common.PartiesPlugin;
import com.alessiodp.parties.common.addons.internal.PartiesPlaceholder;
import com.alessiodp.parties.common.parties.objects.PartyImpl;
import com.alessiodp.parties.common.players.objects.PartyPlayerImpl;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PAPIHook extends PlaceholderExpansion implements Relational {
	@NotNull private final PartiesPlugin plugin;
	
	@Override
	public boolean canRegister() {
		return true;
	}
	
	@Override
	public @NotNull String getName() {
		return plugin.getPluginName();
	}
	
	@Override
	public @NotNull String getIdentifier() {
		return "parties";
	}
	
	@Override
	public @NotNull String getAuthor() {
		return "AlessioDP";
	}
	
	@Override
	public @NotNull String getVersion() {
		return plugin.getVersion();
	}
	
	@Override
	public boolean persist(){
		return true;
	}
	
	@Override
	public @NotNull List<String> getPlaceholders() {
		List<String> ret = new ArrayList<>();
		for (PartiesPlaceholder placeholder : PartiesPlaceholder.values()) {
			ret.add("%" + getIdentifier() + "_" + placeholder.getSyntax() + "%");
		}
		return ret;
	}
	
	public String parsePlaceholders(OfflinePlayer player, String msg) {
		return PlaceholderAPI.setPlaceholders(player, msg);
	}
	
	@Override
	public String onRequest(OfflinePlayer offlinePlayer, @NotNull String identifier) {
		PartiesPlaceholder placeholder = PartiesPlaceholder.getPlaceholder(identifier);
		if (placeholder == null)
			return null;

		if (offlinePlayer != null) {
			PartyPlayerImpl partyPlayer = plugin.getPlayerManager().getPlayer(offlinePlayer.getUniqueId());
			PartyImpl party = plugin.getPartyManager().getParty(partyPlayer.getPartyId());
			
			return placeholder.formatPlaceholder(partyPlayer, party, identifier);
		}
		return null;
	}

	@Override
	public String onPlaceholderRequest(Player one, Player two, String identifier) {
		PartiesPlaceholder placeholder = PartiesPlaceholder.getPlaceholder(identifier);
		if (placeholder == null)
			return null;

		if (one != null && two != null) {
			PartyPlayerImpl partyPlayerOne = plugin.getPlayerManager().getPlayer(one.getUniqueId());
			PartyImpl partyOne = plugin.getPartyManager().getParty(partyPlayerOne.getPartyId());

			PartyPlayerImpl partyPlayerTwo = plugin.getPlayerManager().getPlayer(two.getUniqueId());
			PartyImpl partyTwo = plugin.getPartyManager().getParty(partyPlayerTwo.getPartyId());

			String placeholderOne = placeholder.formatPlaceholder(partyPlayerOne, partyOne, identifier, "", partyTwo);
			String placeholderTwo = placeholder.formatPlaceholder(partyPlayerTwo, partyTwo, identifier, "", partyOne);

			if (placeholderOne != null && placeholderTwo != null)
				return placeholderOne.equals(placeholderTwo) ? placeholderOne : "";
		}
		return null;
	}
}
