package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.ReactionPersonal;
import com.massivecraft.massivetickets.cmd.req.RequirementPreferenceProfileUnlocked;
import com.massivecraft.massivetickets.cmd.type.TypeReactionPersonal;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class PropertyReactionPersonalCreate extends PreferenceProfileProperty<ReactionPersonal>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyReactionPersonalCreate i = new PropertyReactionPersonalCreate();
	public static PropertyReactionPersonalCreate get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyReactionPersonalCreate()
	{
		// Super
		// TODO see if I can make this construct a new TypeReactionPersonal and add unique requirements to the inner properties via parameters
		// The parameters to pass should specify requirements for
		// - Editing the commands
		// - Toggling the enable flag
		super(TypeReactionPersonal.get("ReactionCreate"), "reactionCreate");
		
		// Requirements
		this.addRequirements(RequirementPreferenceProfileUnlocked.get());
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT_REACTION_CREATE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ReactionPersonal getRaw(PreferenceProfile object)
	{
		return object.getReactionPersonalCreate().copy();
	}
	
	@Override
	public PreferenceProfile setRaw(PreferenceProfile object, ReactionPersonal value)
	{
		object.setReactionPersonalCreate(value);
		return object;
	}
	
}
