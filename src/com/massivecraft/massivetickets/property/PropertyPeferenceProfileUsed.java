package com.massivecraft.massivetickets.property;

import com.massivecraft.massivecore.command.editor.Property;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.sender.TypeSender;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import org.bukkit.command.CommandSender;

public class PropertyPeferenceProfileUsed extends Property<CommandSender, PreferenceProfile>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyPeferenceProfileUsed i = new PropertyPeferenceProfileUsed();
	public static PropertyPeferenceProfileUsed get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyPeferenceProfileUsed()
	{
		// Super
		super(TypeSender.get(), TypePreferenceProfile.getSimpleType(), "used");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_EDIT_USED));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public PreferenceProfile getRaw(CommandSender sender)
	{
		MPlayer mplayer = MPlayerColl.get().get(sender);
		if (mplayer == null) return null;
		return mplayer.getPreferenceProfileUsed();
	}
	
	@Override
	public CommandSender setRaw(CommandSender sender, PreferenceProfile preferenceProfile)
	{
		MPlayer mplayer = MPlayerColl.get().get(sender);
		mplayer.setPreferenceProfileUsed(preferenceProfile);
		return sender;
	}
	
}
