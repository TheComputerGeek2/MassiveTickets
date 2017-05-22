package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.command.editor.CommandEditUsed;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.property.EditSettingsPreferenceProfile;

import java.util.List;

// TODO: FUTURE ENHANCEMENT: create "useless profile" search tool for listing profiles which appear unneeded
// TODO should there be any predefined profiles to have as examples?
// TODO perhaps the examples should demonstrate the text substitution available
public class CmdTicketsPreference extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsPreference i = new CmdTicketsPreference() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsPreferences; } };
	public static CmdTicketsPreference get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// TODO add a show command that optionally accepts a name or id of the profile to show
	
	// TODO: future enhancement: add an optional parameter of a predicate set to filter the list results
	public CmdTicketsPreferenceList cmdTicketsPreferenceList = new CmdTicketsPreferenceList();
	public CmdTicketsPreferenceCreate cmdTicketsPreferenceCreate = new CmdTicketsPreferenceCreate();
	
	// TODO should locked profiles really be deletable?
	public CmdTicketsPreferenceDelete cmdTicketsPreferenceDelete = new CmdTicketsPreferenceDelete();
	public CmdTicketsPreferenceEdit cmdTicketsPreferenceEdit = new CmdTicketsPreferenceEdit();
	
	// TODO should this be changed to allow setting the used profile of other moderators?
	// TODO or should that be made in the form of an MPlayer editor?
	public CommandEditUsed commandEditUsed = EditSettingsPreferenceProfile.get().createCommandUsed();
	public CmdTicketsPreferenceTestReactionCreate cmdTicketsPreferenceTestReactionCreate = new CmdTicketsPreferenceTestReactionCreate();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPreference()
	{
		// Children
		this.addChild(this.cmdTicketsPreferenceList);
		this.addChild(this.cmdTicketsPreferenceCreate);
		this.addChild(this.cmdTicketsPreferenceDelete);
		this.addChild(this.cmdTicketsPreferenceEdit);
		this.addChild(this.commandEditUsed);
		this.addChild(this.cmdTicketsPreferenceTestReactionCreate);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsPreferences;
	}
	
}
