package com.massivecraft.massivetickets;

import com.massivecraft.massivecore.collections.MassiveListDef;
import com.massivecraft.massivecore.util.MUtil;

import java.util.Collections;
import java.util.List;

public class ReactionPersonal
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	// -------------------------------------------- //
	// DEFAULT
	// -------------------------------------------- //
	
	public boolean isDefault()
	{
		return this.commands.isEmpty() && this.enabled == null;
	}
	
	// -------------------------------------------- //
	// FIELDS: DECLARE
	// -------------------------------------------- //
	
	private MassiveListDef<String> commands = new MassiveListDef<>();
	private Boolean enabled = null;
	
	private String name = null;
	
	// -------------------------------------------- //
	// FIELDS: LOW
	// -------------------------------------------- //
	
	// FIELD: commands
	public List<String> getCommands()
	{
		return Collections.unmodifiableList(this.commands);
	}
	
	// Returns true if the commands were changed
	public boolean setCommands(List<String> commands)
	{
		MassiveListDef<String> target = new MassiveListDef<>(commands);
		
		if (MUtil.equals(this.commands, target)) return false;
		
		this.commands = target;
		return true;
	}
	
	public boolean hasCommands()
	{
		return !this.commands.isEmpty();
	}
	
	// FIELD: enabled
	public boolean isEnabled()
	{
		return Boolean.TRUE.equals(this.enabled);
	}
	
	// Returns true if the state was changed
	// TODO use this for creating a shortcut in PreferenceProfile
	public boolean setEnabled(Boolean enabled)
	{
		if (enabled == Boolean.FALSE) enabled = null;
		
		if (MUtil.equals(enabled, this.enabled)) return false;
		
		this.enabled = enabled;
		return true;
	}
	
	public ReactionPersonal copy()
	{
		ReactionPersonal ret = new ReactionPersonal();
		ret.commands = new MassiveListDef<>(this.commands);
		ret.enabled = this.enabled;
		return ret;
	}
	
}
