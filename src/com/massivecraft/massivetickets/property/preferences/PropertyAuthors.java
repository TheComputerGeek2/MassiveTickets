package com.massivecraft.massivetickets.property.preferences;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.container.TypeSet;
import com.massivecraft.massivecore.command.type.sender.TypeSenderId;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.req.RequirementPreferenceProfileUnlocked;
import com.massivecraft.massivetickets.entity.PreferenceProfile;

import java.util.Set;

public class PropertyAuthors extends PreferenceProfileProperty<Set<String>>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyAuthors i = new PropertyAuthors();
	public static PropertyAuthors get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyAuthors()
	{
		// Super
		super(TypeSet.get(TypeSenderId.get()), "authors");
		
		this.setNullable(false);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT_AUTHORS));
		this.addRequirements(RequirementPreferenceProfileUnlocked.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Set<String> getRaw(PreferenceProfile preferenceProfile)
	{
		return preferenceProfile.getAuthors();
	}
	
	@Override
	public PreferenceProfile setRaw(PreferenceProfile preferenceProfile, Set<String> authors)
	{
		preferenceProfile.setAuthors(authors);
		return preferenceProfile;
	}
	
}
