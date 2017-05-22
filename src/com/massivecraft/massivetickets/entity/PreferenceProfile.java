package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.collections.MassiveSet;
import com.massivecraft.massivecore.collections.MassiveSetDef;
import com.massivecraft.massivecore.command.type.TypeMillisDiff;
import com.massivecraft.massivecore.mixin.MixinCommand;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.ReactionPersonal;
import org.bukkit.command.CommandSender;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

// FIXME make sure to go over the structure of this class again after reworking everything

// TODO FUTURE ENHANCEMENT: consider adding other reaction conditions, such as ticket done, marked as working, marked as not working

// TODO FUTURE ENHANCEMENT: should time created and time last updated be added as fields for sorting?
// TODO FUTURE ENHANCEMENT: should time last run be stored for helping detect little used profiles?

// TODO FUTURE ENHANCEMENT: add a set of predicates that need to pass in order to execute this profile
// There should be a set of predicates for operating on the following aspects
// - Message contents
// - Tickets this week
// - Ticket age
// - Number of moderators working
// - POSSIBLE HOOK WITH FACTIONS: in same faction (can't take tickets for someone in your own faction)

// TODO FUTURE ENHANCEMENT: allow preference profiles to execute other preference profiles
// This must have protection against cyclically dependent profiles
// This must cache any other profiles it calls
// TODO determine if the external profile calls should be exclusively before and after or if it needs to be anywhere in the commands
// The string substitution data should be shared between the profiles so it isn't recalculated each time
//
// Benefits/Drawbacks of Before/After over inserting the profile execution anywhere in the middle
// Benefits
// - Easier to make
// - More structured
// - Easier to cache profile dependencies
// - Promotes better layout from the moderators since it limits what a single profile can do alone
//
// Drawbacks
// - Less flexible
// - Will likely require more profiles to be created
//
// Things to look out for
// - If a profile gets deleted but another profile has a reference to it,
//   This could cause unexpected behavior and memory leaks.
// - How to handle a call to a profile that is no longer existing.
public class PreferenceProfile extends Entity<PreferenceProfile> implements Named
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	// Variable Keys
	private static transient final String VARIABLE_KEY_PLAYER_NAME = Pattern.quote("{PLAYER_NAME}");
	private static transient final String VARIABLE_KEY_MODERATOR_NAME = Pattern.quote("{MODERATOR_NAME}");
	private static transient final String VARIABLE_KEY_TICKETS_THIS_WEEK = Pattern.quote("{TICKETS_THIS_WEEK}");
	private static transient final String VARIABLE_KEY_TICKETS_TOTAL = Pattern.quote("{TICKETS_TOTAL}");
	private static transient final String VARIABLE_KEY_TICKET_TIME_CREATED = Pattern.quote("{TICKET_AGE}");
	private static transient final String VARIABLE_KEY_CURRENT_YEAR = Pattern.quote("{CURRENT_YEAR}");
	private static transient final String VARIABLE_KEY_CURRENT_WEEK = Pattern.quote("{CURRENT_WEEK}");
	
	private static transient final String MESSAGE_VARIABLE_NOT_APPLICABLE = "N/A";
	
	private static transient final String ERROR_PROFILE_LOCKED = "Cannot edit locked profiles.";
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public PreferenceProfile load(PreferenceProfile other)
	{
		// Load Fields
		this.name = other.name;
		this.locked = other.locked;
		this.authors = other.authors;
		this.reactionPersonalCreate = other.reactionPersonalCreate;
		this.reactionPersonalPick = other.reactionPersonalPick;
		
		// Return
		return this;
	}
	
	@Override
	public boolean isDefault()
	{
		return this.name == null;
	}
	
	// -------------------------------------------- //
	// FIELDS: DECLARE
	// -------------------------------------------- //
	
	private String name = null;
	
	private Boolean locked = null;
	
	private MassiveSetDef<String> authors = new MassiveSetDef<>();
	
	// TODO this probably needs to be renamed
	private ReactionPersonal reactionPersonalCreate = null;
	
	// TODO this probably needs to be renamed
	private ReactionPersonal reactionPersonalPick = null;
	
	// -------------------------------------------- //
	// FIELDS: LOW
	// -------------------------------------------- //
	
	// FIELD: NAME
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		// Enforce Lock
		this.enforceLock();
		
		// Detect Nochange
		if (MUtil.equals(this.name, name)) return;
		
		// Apply
		this.name = name;
		
		// Mark as Changed
		this.changed();
	}
	
	// FIELD: LOCKED
	public boolean isLocked()
	{
		return Boolean.TRUE.equals(this.locked);
	}
	
	public void setLocked(Boolean locked)
	{
		// Clean Input
		Boolean target = locked;
		if (target == Boolean.FALSE) target = null;
		
		// Detect Nochange
		if (MUtil.equals(this.locked, target)) return;
		
		// Apply
		this.locked = target;
		
		// Mark as Changed
		this.changed();
	}
	
	// FIELD: AUTHORS
	public Set<String> getAuthors()
	{
		return Collections.unmodifiableSet(this.authors);
	}
	
	public void setAuthors(Set<String> authors)
	{
		// Enforce Lock
		this.enforceLock();
		
		// Clean Input
		MassiveSetDef<String> target = new MassiveSetDef<>(authors);
		
		// Detect Nochange
		if (MUtil.equals(this.authors, target)) return;
		
		// Apply
		this.authors = target;
		
		// Mark as Changed
		this.changed();
	}
	
	public void addAuthor(Object authorObject)
	{
		// Enforce Lock
		this.enforceLock();
		
		String authorId = IdUtil.getId(authorObject);
		if (authorId == null) return;
		
		// Try adding and return if it already contained
		if (!this.authors.add(authorId)) return;
		
		// Mark as Changed
		this.changed();
	}
	
	// FIELD: REACTION PERSONAL TICKET CREATE
	// TODO make a non creative version of this
	// TODO this creative version might be wasteful
	public ReactionPersonal getReactionPersonalCreate()
	{
		if (this.reactionPersonalCreate == null) return new ReactionPersonal();
		return this.reactionPersonalCreate;
	}
	
	public void setReactionPersonalCreate(ReactionPersonal reactionPersonalCreate)
	{
		// Enforce Lock
		this.enforceLock();
		
		// Clean input
		if (reactionPersonalCreate.isDefault()) reactionPersonalCreate = null;
		
		// Detect Nochange
		if (MUtil.equals(this.reactionPersonalCreate, reactionPersonalCreate)) return;
		
		// Apply
		this.reactionPersonalCreate = reactionPersonalCreate;
		
		// Mark as changed
		this.changed();
	}
	
	public boolean hasReactionCreate()
	{
		if (this.reactionPersonalCreate == null) return false;
		return this.reactionPersonalCreate.hasCommands();
	}
	
	// FIXME move this to an appropriate section
	public Boolean isReactionCreateEnabled()
	{
		if (this.reactionPersonalCreate == null) return false;
		return this.reactionPersonalCreate.isEnabled();
	}
	
	// FIELD: REACTION PERSONAL TICKET PICK
	// TODO check to make this doesn't cause errors
	public ReactionPersonal getReactionPersonalPick()
	{
		return this.reactionPersonalPick;
	}
	
	public ReactionPersonal getReactionPersonalPickCreative()
	{
		if (this.reactionPersonalPick == null) return new ReactionPersonal();
		return this.reactionPersonalPick;
	}
	
	public void setReactionPersonalPick(ReactionPersonal reactionPersonalPick)
	{
		// Enforce Lock
		this.enforceLock();
		
		// Clean input
		if (reactionPersonalPick != null && reactionPersonalPick.isDefault()) reactionPersonalPick = null;
		
		// Detect Nochange
		if (MUtil.equals(this.reactionPersonalPick, reactionPersonalPick)) return;
		
		// Apply
		this.reactionPersonalPick = reactionPersonalPick;
		
		// Mark as Changed
		this.changed();
	}
	
	public boolean hasReactionPick()
	{
		if (this.reactionPersonalPick == null) return false;
		return this.reactionPersonalPick.hasCommands();
	}
	
	// FIELD: REACTION TICKET PICK ENABLED
	public Boolean isReactionPickEnabled()
	{
		if (this.reactionPersonalPick == null) return false;
		return this.reactionPersonalPick.isEnabled();
	}
	
	// -------------------------------------------- //
	// RUN REACTIONS
	// -------------------------------------------- //
	
	public void runReactionCreate(MPlayer ticket, MPlayer moderator)
	{
		if (!this.isReactionCreateEnabled()) return;
		if (!this.hasReactionCreate()) return;
		
		CommandSender moderatorSender = moderator.getSender();
		
		for (String command : prepareCommands(ticket, moderator, this.getReactionPersonalCreate().getCommands()))
		{
			MixinCommand.get().dispatchCommand(moderatorSender, command);
		}
	}
	
	public void runReactionPick(MPlayer ticket, MPlayer moderator)
	{
		if (!this.isReactionPickEnabled()) return;
		if (!this.hasReactionPick()) return;
		
		CommandSender moderatorSender = moderator.getSender();
		
		for (String command : prepareCommands(ticket, moderator, this.getReactionPersonalPick().getCommands()))
		{
			MixinCommand.get().dispatchCommand(moderatorSender, command);
		}
	}
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	private static Iterable<String> prepareCommands(MPlayer ticket, MPlayer moderator, Iterable<String> rawCommands)
	{
		// Create
		Collection<String> ret = new MassiveList<>();
		
		// Cache Variables
		String playerName = ticket.getName();
		String moderatorName = moderator.getName();
		int currentYear = MassiveTickets.getCurrentYear();
		String strCurrentYear = String.valueOf(currentYear);
		int currentWeek = MassiveTickets.getCurrentWeek();
		String strCurrentWeek = String.valueOf(currentWeek);
		String ticketsThisWeek = String.valueOf(moderator.getCount(currentYear, currentWeek));
		String ticketsTotal = String.valueOf(moderator.getTotalCount());
		String ticketAge = MESSAGE_VARIABLE_NOT_APPLICABLE;
		if (ticket.hasMillis()) ticketAge = TypeMillisDiff.get().getVisual(System.currentTimeMillis() - ticket.getMillis());
		
		// Fill
		for (String rawCommand: rawCommands)
		{
			// Remove Dust
			rawCommand = Txt.removeLeadingCommandDust(rawCommand);
			
			// Apply Variables
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_PLAYER_NAME, playerName);
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_MODERATOR_NAME, moderatorName);
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_TICKETS_THIS_WEEK, ticketsThisWeek);
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_TICKETS_TOTAL, ticketsTotal);
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_TICKET_TIME_CREATED, ticketAge);
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_CURRENT_YEAR, strCurrentYear);
			rawCommand = rawCommand.replaceAll(VARIABLE_KEY_CURRENT_WEEK, strCurrentWeek);
			
			// Add to Return
			ret.add(rawCommand);
		}
		
		// Return
		return ret;
	}
	
	private void enforceLock()
	{
		if (this.isLocked()) throw new IllegalStateException(ERROR_PROFILE_LOCKED);
	}
	
	// -------------------------------------------- //
	// MERGE
	// -------------------------------------------- //
	
	// TODO for discussion: https://github.com/TheComputerGeek2/MassiveTickets/commit/0f57c2c31feea99b00fda847558057849f5de5cc#commitcomment-21921474
	// Benefits of not using inheritance instead of using others as templates:
	// - More flexability for each moderator to edit their profiles
	// - Fewer things that can change unexpectedly
	// Drawbacks:
	// - Increased likihood of redundant data
	// - Harder to edit profiles in bulk
	
	// Add all the settings
	public PreferenceProfile append(Iterable<PreferenceProfile> others)
	{
		// Quick Exits
		if (others == null) return this;
		if (!others.iterator().hasNext()) return this;
		
		// Enforce Lock
		this.enforceLock();
		
		// Initialize Merge Fields
		Set<String> authorsMerged = new MassiveSet<>(this.authors);
		
		ReactionPersonal reactionPersonalCreateMerging = this.getReactionPersonalCreate();
		List<String> reactionCreateMerged = new MassiveList<>(reactionPersonalCreateMerging.getCommands());
		
		ReactionPersonal reactionPersonalPickMerging = this.getReactionPersonalPick();
		List<String> reactionPickMerged = new MassiveList<>(reactionPersonalPickMerging.getCommands());
		
		for (PreferenceProfile other: others)
		{
			if (other == null) continue;
			
			// Merge Authors
			authorsMerged.addAll(other.authors);
			
			// Merge Reaction Create
			// TODO make a method to return commands or empty list if none
			if (other.hasReactionCreate()) reactionCreateMerged.addAll(other.getReactionPersonalCreate().getCommands());
			
			// Merge Reaction Pick
			// TODO make a method to return commands or empty list if none
			if (other.hasReactionPick()) reactionPickMerged.addAll(other.getReactionPersonalPick().getCommands());
		}
		
		// Apply Merges
		this.setAuthors(authorsMerged);
		
		if (reactionPersonalCreateMerging.setCommands(reactionCreateMerged)) this.changed();
		// In case we started with a default which would cause it to have not been stored
		this.setReactionPersonalCreate(reactionPersonalCreateMerging);
		
		if (reactionPersonalCreateMerging.setCommands(reactionPickMerged)) this.changed();
		// In case we started with a default which would cause it to have not been stored
		this.setReactionPersonalPick(reactionPersonalPickMerging);
		
		// Return
		return this;
	}
	
}
