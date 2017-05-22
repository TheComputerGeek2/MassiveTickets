package com.massivecraft.massivetickets.property.reactionpersonal;

import com.massivecraft.massivecore.command.editor.CommandEditAbstract;
import com.massivecraft.massivecore.command.editor.EditSettings;
import com.massivecraft.massivecore.command.type.TypeStringCommand;
import com.massivecraft.massivecore.command.type.container.TypeList;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.ReactionPersonal;

import java.util.Collections;
import java.util.List;

public class PropertyReactionPersonalCommands extends PropertyReactionPersonalAbstract<List<String>>
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	private static final List<String> HELP_HEADER = Txt.parse(
		MUtil.list(
			"<i>Variable Keys can be used as dynamic values in your reaction commands",
			"<h>{PLAYER_NAME}<i> gets replaced with the name of the player making the ticket",
			"<h>{MODERATOR_NAME}<i> gets replaced with your name",
			"<h>{TICKETS_THIS_WEEK}<i> gets replaced with how many tickets you've done this week",
			"<h>{TICKETS_TOTAL}<i> gets replaced with how many tickets you've done total",
			"<h>{TICKET_AGE}<i> gets replaced with how old the ticket is",
			"<h>{CURRENT_YEAR}<i> gets replaced with the current year",
			"<h>{CURRENT_WEEK}<i> gets replaced with the current week"
		)
	);
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static PropertyReactionPersonalCommands i = new PropertyReactionPersonalCommands();
	public static PropertyReactionPersonalCommands get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public PropertyReactionPersonalCommands()
	{
		super(TypeList.get(TypeStringCommand.get()), "commands");
		this.setNullable(false);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getRaw(ReactionPersonal object)
	{
		return object.getCommands();
	}
	
	@Override
	public ReactionPersonal setRaw(ReactionPersonal object, List<String> value)
	{
		object.setCommands(value);
		return object;
	}
	
	@Override
	public CommandEditAbstract<ReactionPersonal, List<String>> createEditCommand(EditSettings<ReactionPersonal> settings)
	{
		// Add some extra help information to the edit command
		CommandEditAbstract<ReactionPersonal, List<String>> ret = super.createEditCommand(settings);
		ret.setHelp(Collections.unmodifiableList(HELP_HEADER));
		return ret;
	}
	
}
