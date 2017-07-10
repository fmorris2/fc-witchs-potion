package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fc_witchs_potion.data.WPReqs;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;

public class StartQuest extends HettyDialogue implements ItemsRequiredTask
{
	private static final long serialVersionUID = 4612792351523544594L;

	@Override
	public boolean shouldExecute()
	{
		return WPSettings.START_QUEST.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Start quest";
	}

	@Override
	public FCItem[] getRequiredItems()
	{
		return new FCItem[]
		{
			new FCItem(1, false, WPReqs.EYE_OF_NEWT),
			new FCItem(1, false, WPReqs.ONION),
			new FCItem(1, false, WPReqs.RAW_BEEF)
		};
	}

}
