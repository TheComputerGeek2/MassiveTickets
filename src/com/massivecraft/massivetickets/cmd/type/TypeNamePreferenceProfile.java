package com.massivecraft.massivetickets.cmd.type;

import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.command.type.TypeNameAbstract;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.PreferenceProfileColl;
import com.massivecraft.massivetickets.property.PropertyPeferenceProfileUsed;
import org.bukkit.command.CommandSender;

public class TypeNamePreferenceProfile extends TypeNameAbstract
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static TypeNamePreferenceProfile i = new TypeNamePreferenceProfile();
	public static TypeNamePreferenceProfile get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public TypeNamePreferenceProfile()
	{
		super(true);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Integer getLengthMin()
	{
		return MConf.get().getModeratorPreferenceProfileNameLengthMin();
	}
	
	@Override
	public Integer getLengthMax()
	{
		return MConf.get().getModeratorPreferenceProfileNameLengthMax();
	}
	
	@Override
	public Named getCurrent(CommandSender sender)
	{
		return PropertyPeferenceProfileUsed.get().getValue(sender);
	}
	
	@Override
	public boolean isNameTaken(String name)
	{
		return PreferenceProfileColl.get().isNameTaken(name);
	}
	
}
