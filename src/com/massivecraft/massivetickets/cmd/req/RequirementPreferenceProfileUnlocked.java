package com.massivecraft.massivetickets.cmd.req;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import org.bukkit.command.CommandSender;

public class RequirementPreferenceProfileUnlocked extends RequirementPreferenceProfileAbstract
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static RequirementPreferenceProfileUnlocked i = new RequirementPreferenceProfileUnlocked();
	public static RequirementPreferenceProfileUnlocked get() { return i; }
	
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	private static final String MESSAGE_ERROR = Txt.parse("<b>This preference profile is locked.");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean applyInner(CommandSender sender, MassiveCommand command, PreferenceProfile preferenceProfileUsed)
	{
		if (preferenceProfileUsed == null) return true;
		return !preferenceProfileUsed.isLocked();
	}
	
	@Override
	public String createErrorMessageInner(CommandSender sender, MassiveCommand command, PreferenceProfile preferenceProfileUsed)
	{
		return MESSAGE_ERROR;
	}
	
}
