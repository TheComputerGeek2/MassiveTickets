package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.command.editor.CommandEditProperties;
import com.massivecraft.massivecore.command.editor.PropertyThis;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import com.massivecraft.massivetickets.property.EditSettingsPreferenceProfile;

public class CmdTicketsPreferenceEdit extends CommandEditProperties<PreferenceProfile, PreferenceProfile>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPreferenceEdit()
	{
		// Super
		super(EditSettingsPreferenceProfile.get(), new PropertyThis<>(TypePreferenceProfile.get()));
		
		// Aliases
		this.setAliases("edit");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT));
	}
	
}
