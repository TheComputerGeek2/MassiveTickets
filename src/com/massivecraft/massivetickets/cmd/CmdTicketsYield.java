package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsYield extends MassiveTicketsCommand
{
	public CmdTicketsYield(List<String> aliases)
	{
		super(aliases);
		
		this.addRequiredArg("player");
		
		this.addRequirements(ReqHasPerm.get(Perm.YIELD.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		MPlayer ticket = this.arg(0, ARMPlayer.getStartOnline());
		if (ticket == null) return;
		
		// The person who picked this ticket is ...
		MPlayer moderator = ticket.getModerator();
		
		// ... noone?
		if (moderator == null)
		{
			msg("<i>Can't yield since noone has picked this ticket.");
			return;
		}
		
		// ... someone else?
		boolean other = (moderator != msender);
		if (other && !Perm.YIELD_OTHER.has(sender, true)) return;
		
		// Apply
		ticket.setModeratorId(null);
		
		// Inform
		MassiveTickets.alertMsg("<white>%s <pink>yielded <white>%s<pink>'s ticket:", msender.getDisplayName(), ticket.getDisplayName());
		MassiveTickets.alertMsg(ticket.getMessage());
		
		MassiveTickets.alertMsg(ticket.getId(), "<white>%s <pink>has yielded your ticket.", msender.getDisplayName());
		MassiveTickets.alertMsg(ticket.getId(), "It is now placed back in the ticket list. ");
	}
	
}