package com.massivecraft.massivetickets.cmd.type;

import com.massivecraft.massivecore.command.type.TypeAbstractChoice;
import com.massivecraft.massivetickets.ReactionPersonal;
import com.massivecraft.massivetickets.property.reactionpersonal.PropertyReactionPersonalCommands;
import com.massivecraft.massivetickets.property.reactionpersonal.PropertyReactionPersonalEnabled;

public class TypeReactionPersonal extends TypeAbstractChoice<ReactionPersonal>
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static TypeReactionPersonal i = new TypeReactionPersonal();
	public static TypeReactionPersonal get() { return i; }
	public static TypeReactionPersonal get(String innerName) { return new TypeReactionPersonal(innerName); }
	
	private String innerName = null;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public TypeReactionPersonal(String innerName)
	{
		this();
		this.innerName = innerName;
	}
	
	public TypeReactionPersonal()
	{
		super(ReactionPersonal.class);
		this.setInnerProperties(
			PropertyReactionPersonalCommands.get(),
			PropertyReactionPersonalEnabled.get()
		);
	}
	
	@Override
	public String getNameInner(ReactionPersonal object)
	{
		return this.innerName;
	}
	
}
