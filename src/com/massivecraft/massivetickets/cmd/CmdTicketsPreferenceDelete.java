package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class CmdTicketsPreferenceDelete extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPreferenceDelete()
	{
		// Parameters
		this.addParameter(TypePreferenceProfile.get(), "preference");
		
		// Aliases
		this.addAliases("delete");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_DELETE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		PreferenceProfile preferenceProfile = this.readArg();
		
		// Detach
		preferenceProfile.detach();
		
		// Inform
		// TODO could this get a visual instead of a name
		// TODO could the visual also be an Mson?
		msg(Txt.parse("<i>Preference <h>%s<i> deleted.", preferenceProfile.getName()));
	}
	
}
