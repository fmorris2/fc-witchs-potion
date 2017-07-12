package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_witchs_potion.data.WPReqs;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;

public class TurnInMaterials extends HettyDialogue implements ItemsRequiredTask
{
	private static final long serialVersionUID = -7001466631451786920L;

	@Override
	public boolean shouldExecute()
	{
		return WPSettings.HETTY_DIALOGUE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Turn in materials";
	}
	
	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, WPReqs.EYE_OF_NEWT),
			new FCItem(1, false, WPReqs.ONION),
			new FCItem(1, false, WPReqs.BURNT_MEAT),
			new FCItem(1, false, WPReqs.RAT_TAIL),
		};
	}

}
