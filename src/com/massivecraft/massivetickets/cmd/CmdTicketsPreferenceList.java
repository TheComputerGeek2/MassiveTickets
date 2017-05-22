package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.comparator.ComparatorNamed;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.pager.Msonifier;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypePreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfile;
import com.massivecraft.massivetickets.entity.PreferenceProfileColl;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.List;

// TODO: future enhancement: add an optional parameter of a predicate set to filter the list results
public class CmdTicketsPreferenceList extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	private static final Msonifier<PreferenceProfile> MSONIFIER = new Msonifier<PreferenceProfile>()
	{
		@Override
		public Mson toMson(PreferenceProfile preference, int index)
		{
			List<String> tooltip = Mson.toPlain(TypePreferenceProfile.get().getShow(preference), true);
			String name = preference.getName();
			String id = preference.getId();
			
			return mson(name)
				.command(CmdTickets.get().cmdTicketsPreference.commandEditUsed, id)
				.tooltip(tooltip);
		}
	};
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPreferenceList()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
		
		// Aliases
		this.addAliases("list");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PREFERENCE_LIST));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		// Pager Create
		final Pager<PreferenceProfile> pager = new Pager<>(this, "Preference Profiles", page, null, MSONIFIER);
		
		// Since this involves a database call which has the potential to be heavy,
		// This is run async to avoid problems.
		Bukkit.getScheduler().runTaskAsynchronously(MassiveTickets.get(), new Runnable()
		{
			@Override
			public void run()
			{
				// Pager Items
				final Collection<PreferenceProfile> preferences = PreferenceProfileColl.get().getAll(ComparatorNamed.get());
				pager.setItems(preferences);
				
				// Pager Message
				pager.message();
			}
		});
	}
	
}
