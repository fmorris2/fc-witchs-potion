package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;

public class CollectRatTail extends Task
{
	private static final long serialVersionUID = 4120185611343950592L;
	private static final Positionable RAT_TILE = new RSTile(2956, 3203, 0);
	private static final int DIST_THRESH = 4;
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(RAT_TILE) > DIST_THRESH)
			return Travel.webWalkTo(RAT_TILE) && Timing.waitCondition(FCConditions.withinDistanceOfTile(RAT_TILE, DIST_THRESH), 3500);
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return WPSettings.COLLECT_RAT_TAIL.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Collect rat tail";
	}

}
