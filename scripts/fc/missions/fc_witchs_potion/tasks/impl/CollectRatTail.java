package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.grounditems.PickUpGroundItem;
import scripts.fc.api.interaction.impl.npcs.AttackNpc;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;

public class CollectRatTail extends Task
{
	private static final long serialVersionUID = 4120185611343950592L;
	private static final String ITEM_NAME = "Rat's tail";
	private static final Positionable RAT_TILE = new RSTile(2956, 3203, 0);
	private static final int DIST_THRESH = 4;
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(RAT_TILE) > DIST_THRESH)
			return Travel.webWalkTo(RAT_TILE) && Timing.waitCondition(FCConditions.withinDistanceOfTile(RAT_TILE, DIST_THRESH), 3500);
		else if(canLoot())
			return loot();
		else if(FCConditions.IN_COMBAT_CONDITION.active())
			return waitForCombat() && FCTiming.waitCondition(() -> canLoot(), 4000);
		else
			return attack();
	}
	
	private boolean waitForCombat()
	{
		General.println("Wait for combat");
		return FCTiming.waitCondition(() -> !FCConditions.IN_COMBAT_CONDITION.active(), 4000);
	}
	
	private boolean canLoot()
	{
		return GroundItems.find(ITEM_NAME).length > 0;
	}
	
	private boolean loot()
	{
		General.println("Loot " + ITEM_NAME);
		return new PickUpGroundItem(ITEM_NAME).execute() && Timing.waitCondition(FCConditions.inventoryContains(ITEM_NAME), 4000);
	}
	
	private boolean attack()
	{
		General.println("Attack rat");
		return new AttackNpc("Attack", "Rat", 10).execute() && Timing.waitCondition(FCConditions.IN_COMBAT_CONDITION, 6000);
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
