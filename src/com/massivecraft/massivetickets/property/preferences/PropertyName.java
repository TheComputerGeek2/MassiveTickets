package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.req.RequirementPreferenceProfileUnlocked;
import com.massivecraft.massivetickets.cmd.type.TypeNamePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class PropertyName extends PreferenceProfileProperty<String>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyName i = new PropertyName();
	public static PropertyName get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyName()
	{
		// Super
		super(TypeNamePreferenceProfile.get(), "name");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT_NAME));
		this.addRequirements(RequirementPreferenceProfileUnlocked.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getRaw(PreferenceProfile preferenceProfile)
	{
		return preferenceProfile.getName();
	}
	
	@Override
	public PreferenceProfile setRaw(PreferenceProfile preferenceProfile, String name)
	{
		preferenceProfile.setName(name);
		return preferenceProfile;
	}
	
}
