package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.ReactionPersonal;
import com.massivecraft.massivetickets.cmd.req.RequirementPreferenceProfileUnlocked;
import com.massivecraft.massivetickets.cmd.type.TypeReactionPersonal;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class PropertyReactionPersonalPick extends PreferenceProfileProperty<ReactionPersonal>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyReactionPersonalPick i = new PropertyReactionPersonalPick();
	public static PropertyReactionPersonalPick get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyReactionPersonalPick()
	{
		// Super
		// TODO see if I can make this construct a new TypeReactionPersonal and add unique requirements to the inner properties via parameters
		super(TypeReactionPersonal.get("ReactionPick"), "reactionPick");
		
		// Requirements
		this.addRequirements(RequirementPreferenceProfileUnlocked.get());
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT_REACTION_PICK));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ReactionPersonal getRaw(PreferenceProfile object)
	{
		return object.getReactionPersonalPickCreative().copy();
	}
	
	@Override
	public PreferenceProfile setRaw(PreferenceProfile object, ReactionPersonal value)
	{
		object.setReactionPersonalPick(value);
		return object;
	}
	
}
