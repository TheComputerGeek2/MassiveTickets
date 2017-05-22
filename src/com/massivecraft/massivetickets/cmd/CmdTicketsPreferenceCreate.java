package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.container.TypeList;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypeNamePreferenceProfile;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfileColl;

import java.util.List;

public class CmdTicketsPreferenceCreate extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPreferenceCreate()
	{
		// Parameters
		this.addParameter(TypeNamePreferenceProfile.get(), "name");
		this.addParameter(null, TypeList.get(TypePreferenceProfile.get()), "templates", "none", true);
		
		// Aliases
		this.addAliases("new");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_CREATE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		String name = this.readArg();
		List<PreferenceProfile> templates = this.readArg();
		
		// Create new profile
		PreferenceProfile preferenceProfile = PreferenceProfileColl.get().create();
		
		// Load from template
		preferenceProfile.append(templates);
		preferenceProfile.setName(name);
		preferenceProfile.addAuthor(msender);
		
		// Set as used
		msender.setPreferenceProfileUsed(preferenceProfile);
		
		// Inform
		msg(Txt.parse("<i>Preference <h>%s<i> created.", preferenceProfile.getName()));
	}
	
}
