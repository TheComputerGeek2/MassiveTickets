package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanTrue;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class PropertyLocked extends PreferenceProfileProperty<Boolean>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyLocked i = new PropertyLocked();
	public static PropertyLocked get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyLocked()
	{
		// Super
		super(TypeBooleanTrue.get(), "locked");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT_LOCK));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Boolean getRaw(PreferenceProfile preferenceProfile)
	{
		return preferenceProfile.isLocked();
	}
	
	@Override
	public PreferenceProfile setRaw(PreferenceProfile preferenceProfile, Boolean locked)
	{
		preferenceProfile.setLocked(locked);
		return preferenceProfile;
	}
	
}
