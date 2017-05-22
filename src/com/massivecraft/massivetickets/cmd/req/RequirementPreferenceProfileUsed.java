package com.massivecraft.massivetickets.cmd.req;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import org.bukkit.command.CommandSender;

public class RequirementPreferenceProfileUsed extends RequirementPreferenceProfileAbstract
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static RequirementPreferenceProfileUsed i = new RequirementPreferenceProfileUsed();
	public static RequirementPreferenceProfileUsed get() { return i; }
	
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	private static final String MESSAGE_ERROR = Txt.parse("<b>You do not have a preference profile selected.");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean applyInner(CommandSender sender, MassiveCommand command, PreferenceProfile preferenceProfileUsed)
	{
		return preferenceProfileUsed != null;
	}
	
	@Override
	public String createErrorMessageInner(CommandSender sender, MassiveCommand command, PreferenceProfile preferenceProfileUsed)
	{
		return MESSAGE_ERROR;
	}
	
}
