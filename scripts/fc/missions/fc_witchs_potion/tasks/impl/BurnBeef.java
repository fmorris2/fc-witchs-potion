package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import java.util.Arrays;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.objects.ItemOnObject;
import scripts.fc.api.items.FCItem;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_witchs_potion.data.WPReqs;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;

public class BurnBeef extends Task implements ItemsRequiredTask
{
	private static final long serialVersionUID = -5920492333191214364L;
	private static final String[] ITEM_NAMES = {"Raw beef", "Cooked meat"};

	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(HettyDialogue.HETTY_TILE) > HettyDialogue.DIST_THRESH)
			return Travel.webWalkTo(HettyDialogue.HETTY_TILE) 
					&& Timing.waitCondition(FCConditions.withinDistanceOfTile(HettyDialogue.HETTY_TILE, HettyDialogue.DIST_THRESH), 4000);
		
		Arrays.stream(ITEM_NAMES)
			.filter(s -> Inventory.getCount(s) > 0)
			.forEachOrdered(i -> {
				General.println("Cooking " + i + " on fireplace");
				if(new ItemOnObject("Use", "Fireplace", i, 10).execute())
					FCTiming.waitCondition(() -> Inventory.getCount(i) == 0, 5000);
			});
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return WPSettings.BURN_BEEF.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Burn beef";
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		if(Inventory.getCount(WPReqs.COOKED_MEAT, WPReqs.RAW_BEEF) > 0)
			return new FCItem[]{};
		
		return new FCItem[]
		{
			new FCItem(1, false, WPReqs.RAW_BEEF)
		};
	}

}
