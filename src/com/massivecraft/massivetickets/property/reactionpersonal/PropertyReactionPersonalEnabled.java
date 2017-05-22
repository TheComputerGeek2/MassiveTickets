package com.massivecraft.massivetickets.property.reactionpersonal;

import com.massivecraft.massivecore.command.type.primitive.TypeBooleanTrue;
import com.massivecraft.massivetickets.ReactionPersonal;

public class PropertyReactionPersonalEnabled extends PropertyReactionPersonalAbstract<Boolean>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyReactionPersonalEnabled i = new PropertyReactionPersonalEnabled();
	public static PropertyReactionPersonalEnabled get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyReactionPersonalEnabled()
	{
		super(TypeBooleanTrue.get(), "enabled");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Boolean getRaw(ReactionPersonal object)
	{
		return object.isEnabled();
	}
	
	@Override
	public ReactionPersonal setRaw(ReactionPersonal object, Boolean value)
	{
		object.setEnabled(value);
		return object;
	}
	
}
