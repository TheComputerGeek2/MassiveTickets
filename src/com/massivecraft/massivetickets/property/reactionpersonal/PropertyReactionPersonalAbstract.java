package com.massivecraft.massivetickets.property.reactionpersonal;

import com.massivecraft.massivecore.command.editor.Property;
import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivetickets.ReactionPersonal;
import com.massivecraft.massivetickets.cmd.type.TypeReactionPersonal;

public abstract class PropertyReactionPersonalAbstract<V> extends Property<ReactionPersonal, V>
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyReactionPersonalAbstract(Type<V> valueType, String... names)
	{
		super(TypeReactionPersonal.get(), valueType, names);
	}
	
}
