package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.editor.Property;
import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public abstract class PreferenceProfileProperty<V> extends Property<PreferenceProfile, V>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PreferenceProfileProperty(Type<V> valueType, String... names)
	{
		// Super
		super(TypePreferenceProfile.get(), valueType, names);
	}
	
}
