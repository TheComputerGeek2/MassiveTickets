package com.massivecraft.massivetickets.cmd.req;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import com.massivecraft.massivetickets.property.PropertyPeferenceProfileUsed;
import org.bukkit.command.CommandSender;

public abstract class RequirementPreferenceProfileAbstract extends RequirementAbstract
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender, MassiveCommand command)
	{
		PreferenceProfile profileUsed = PropertyPeferenceProfileUsed.get().getValue(sender);
		return this.applyInner(sender, command, profileUsed);
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
	{
		PreferenceProfile profileUsed = PropertyPeferenceProfileUsed.get().getValue(sender);
		return this.createErrorMessageInner(sender, command, profileUsed);
	}
	
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	
	public abstract boolean applyInner(CommandSender sender, MassiveCommand command, PreferenceProfile preferenceProfileUsed);
	public abstract String createErrorMessageInner(CommandSender sender, MassiveCommand command, PreferenceProfile preferenceProfileUsed);
	
}
