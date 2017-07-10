package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;

public abstract class HettyDialogue extends Task
{
	private static final long serialVersionUID = 1693300668350788417L;
	private static final Positionable HETTY_TILE = new RSTile(2967, 3206, 0);
	private static final int DIST_THRESH = 5;

	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(HETTY_TILE) > DIST_THRESH)
			return Travel.webWalkTo(HETTY_TILE) && Timing.waitCondition(FCConditions.withinDistanceOfTile(HETTY_TILE, DIST_THRESH), 4000);
		
		return new NpcDialogue("Talk-to", "Hetty", 10, 0,0).execute();
	}
}
