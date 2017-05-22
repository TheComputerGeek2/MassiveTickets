package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.type.primitive.TypeStringId;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class PropertyId extends PreferenceProfileProperty<String>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyId i = new PropertyId();
	public static PropertyId get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyId()
	{
		// Super
		super(TypeStringId.get(), "id");
		
		// This should never be edited
		this.setEditable(false);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getRaw(PreferenceProfile preferenceProfile)
	{
		return preferenceProfile.getId();
	}
	
	@Override
	public PreferenceProfile setRaw(PreferenceProfile preferenceProfile, String value)
	{
		return preferenceProfile;
	}
	
}
