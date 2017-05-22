package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.store.Coll;

public class PreferenceProfileColl extends Coll<PreferenceProfile>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static PreferenceProfileColl i = new PreferenceProfileColl();
	public static PreferenceProfileColl get() { return i; }
	
	// -------------------------------------------- //
	// STACK TRACEABILITY
	// -------------------------------------------- //
	
	@Override
	public void onTick()
	{
		super.onTick();
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void setActive(boolean active)
	{
		super.setActive(active);
	}
	
}
