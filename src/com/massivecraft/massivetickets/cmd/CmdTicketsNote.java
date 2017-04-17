package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.TypeNullable;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import org.bukkit.ChatColor;

import java.util.List;

import static com.massivecraft.massivecore.mson.Mson.SPACE;

public class CmdTicketsNote extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static CmdTicketsNote i = new CmdTicketsNote() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsNote; } };
	public static CmdTicketsNote get() { return i; }
	
	public CmdTicketsNote()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getAny(), "player");
		this.addParameter(TypeNullable.get(TypeString.get()), "note", true);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.NOTE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsNote;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Parameters
		MPlayer ticket = this.readArg();
		String note = this.readArg();
		
		// Set the note
		ticket.setNote(note);
		
		// Alert moderators online of the note
		MassiveTickets.alertModeratorsMessage(this.getNoteMson(ticket, note));
	}
	
	// -------------------------------------------- //
	// UTILITY
	// -------------------------------------------- //
	
	private static Mson UNNOTED = mson("removed a note from");
	private static Mson NOTED = mson("applied a note to");
	private static Mson TICKET = mson("'s ticket.");
	
	private Mson getNoteMson(MPlayer ticket, String newNote)
	{
		return mson(
			// Have to use null as the viewer since it is being reused for all moderators
			msender.getDisplayNameMson(null),
			SPACE,
			newNote == null ? UNNOTED : NOTED,
			SPACE,
			// Watcher object is null for the above reason
			ticket.getDisplayNameMson(null),
			TICKET,
			SPACE,
			BUTTON_SHOW.command(CmdTickets.get().cmdTicketsShow, ticket.getName())
		).color(ChatColor.LIGHT_PURPLE);
	}
	
}
