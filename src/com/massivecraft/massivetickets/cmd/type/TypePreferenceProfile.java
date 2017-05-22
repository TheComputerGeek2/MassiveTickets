package com.massivecraft.massivetickets.cmd.type;

import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivecore.command.type.store.TypeEntity;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfileColl;
import com.massivecraft.massivetickets.property.preferences.PropertyAuthors;
import com.massivecraft.massivetickets.property.preferences.PropertyId;
import com.massivecraft.massivetickets.property.preferences.PropertyLocked;
import com.massivecraft.massivetickets.property.preferences.PropertyName;
import com.massivecraft.massivetickets.property.preferences.PropertyReactionPersonalCreate;
import com.massivecraft.massivetickets.property.preferences.PropertyReactionPersonalPick;

public class TypePreferenceProfile extends TypeEntity<PreferenceProfile>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	// This additional type is created because there seemed to be issues
	// When using the normal TypePreferenceProfile in some situations
	private static Type<PreferenceProfile> simpleType = TypeEntity.get(PreferenceProfileColl.get());
	public static Type<PreferenceProfile> getSimpleType() { return simpleType; }
	
	private static TypePreferenceProfile i = new TypePreferenceProfile();
	public static TypePreferenceProfile get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public TypePreferenceProfile()
	{
		// Super
		super(PreferenceProfileColl.get());
		
		// Inner properties
		this.setInnerProperties(
			PropertyId.get(),
			PropertyName.get(),
			PropertyLocked.get(),
			PropertyAuthors.get(),
			PropertyReactionPersonalCreate.get(),
			PropertyReactionPersonalPick.get()
		);
	}
	
}
