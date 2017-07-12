package scripts.fc.missions.fc_witchs_potion;

import java.util.Arrays;
import java.util.LinkedList;

import scripts.fc.framework.quest.QuestScriptManager;
import scripts.fc.framework.requirement.Requirement;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_witchs_potion.data.WPReqs;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;
import scripts.fc.missions.fc_witchs_potion.tasks.impl.BurnBeef;
import scripts.fc.missions.fc_witchs_potion.tasks.impl.CollectRatTail;
import scripts.fc.missions.fc_witchs_potion.tasks.impl.DrinkFromCauldron;
import scripts.fc.missions.fc_witchs_potion.tasks.impl.StartQuest;
import scripts.fc.missions.fc_witchs_potion.tasks.impl.TurnInMaterials;

public class FCWitchsPotion extends QuestScriptManager
{
	public static final int SETTING = 67;
	
	public FCWitchsPotion(FCMissionScript fcScript)
	{
		super(fcScript);
	}

	@Override
	public boolean canStart()
	{
		return true;
	}

	@Override
	public boolean hasReachedEndingCondition()
	{
		return WPSettings.QUEST_COMPLETE.isValid();
	}

	@Override
	public String getMissionName()
	{
		return "FC Witch's Potion";
	}

	@Override
	public String getEndingMessage()
	{
		return "FC Witch's Potion has ended";
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[]{};
	}

	@Override
	public void resetStatistics()
	{
	}

	@Override
	public Requirement[] getRequirements()
	{
		return new Requirement[]{new WPReqs(missionScript)};
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<>(Arrays.asList(new StartQuest(), new CollectRatTail(), new BurnBeef(),
				new TurnInMaterials(), new DrinkFromCauldron()));
	}
	
	public String toString()
	{
		return "Witch's Potion";
	}

}
