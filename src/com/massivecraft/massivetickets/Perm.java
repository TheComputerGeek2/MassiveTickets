package com.massivecraft.massivetickets;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	BASECOMMAND,
	LIST,
	SHOW,
	SHOW_OTHER,
	CREATE,
	DONE,
	DONE_OTHER,
	PICK,
	YIELD,
	YIELD_OTHER,
	HIGHSCORE,
	MODLIST,
	WORKING,
	CHEAT,
	PREFERENCE,
	PREFERENCE_CREATE,
	PREFERENCE_DELETE,
	PREFERENCE_EDIT,
	PREFERENCE_EDIT_NAME,
	PREFERENCE_EDIT_LOCK,
	PREFERENCE_EDIT_AUTHORS,
	PREFERENCE_EDIT_REACTION_CREATE,
	PREFERENCE_EDIT_REACTION_PICK,
	PREFERENCE_EDIT_USED,
	PREFERENCE_LIST,
	PREFERENCE_TEST_REACTION_CREATE,
	TELEPORT,
	CONFIG,
	VERSION,
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final String id;
	@Override public String getId() { return this.id; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	Perm()
	{
		this.id = PermissionUtil.createPermissionId(MassiveTickets.get(), this);
	}
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
	public boolean has(Permissible permissible, boolean verboose)
	{
		return PermissionUtil.hasPermission(permissible, this, verboose);
	}
	
	public boolean has(Permissible permissible)
	{
		return PermissionUtil.hasPermission(permissible, this);
	}
	
}
