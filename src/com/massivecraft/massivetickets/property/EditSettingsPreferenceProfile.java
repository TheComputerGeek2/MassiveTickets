package com.massivecraft.massivetickets.property;

import com.massivecraft.massivecore.command.editor.EditSettings;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

public class EditSettingsPreferenceProfile extends EditSettings<PreferenceProfile>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static EditSettingsPreferenceProfile i = new EditSettingsPreferenceProfile();
	public static EditSettingsPreferenceProfile get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public EditSettingsPreferenceProfile()
	{
		// Super
		super(TypePreferenceProfile.get(), PropertyPeferenceProfileUsed.get());
	}
	
}
