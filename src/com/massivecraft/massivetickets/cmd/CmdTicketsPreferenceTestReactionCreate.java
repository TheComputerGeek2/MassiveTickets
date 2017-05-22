package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.req.RequirementPreferenceProfileUsed;
import com.massivecraft.massivetickets.cmd.type.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class CmdTicketsPreferenceTestReactionCreate extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	private static final String MESSAGE_DONE = Txt.parse("<i>Personal ticket create reaction test completed.");
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPreferenceTestReactionCreate()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getOnline(), "ticket");
		
		// Aliases
		this.setAliases("testTicketCreateReaction");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_TEST_REACTION_CREATE));
		this.addRequirements(RequirementPreferenceProfileUsed.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.readArg();
		
		// Run the reaction
		PreferenceProfile moderatorPreferenceProfile = msender.getPreferenceProfileUsed();
		moderatorPreferenceProfile.runReactionCreate(ticket, msender);
		
		// Inform that it finished in case output was not noticable
		msg(MESSAGE_DONE);
	}
	
}
