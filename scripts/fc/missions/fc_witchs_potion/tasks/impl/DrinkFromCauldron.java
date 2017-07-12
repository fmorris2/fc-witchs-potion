package scripts.fc.missions.fc_witchs_potion.tasks.impl;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.utils.InterfaceUtils;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fc_witchs_potion.data.WPSettings;

public class DrinkFromCauldron extends Task
{
	private static final long serialVersionUID = -6519521559076186666L;

	@Override
	public boolean execute()
	{
		if(DialogueThread.areDialogueInterfacesUp())
		{
			DialogueThread.doClickToContinue();
			FCTiming.waitCondition(() -> InterfaceUtils.isQuestInterfaceUp(), 2500);
		}
		
		return new ClickObject("Drink From", "Cauldron", 10).execute() 
				&& FCTiming.waitCondition(() -> DialogueThread.areDialogueInterfacesUp(), 4000);
	}

	@Override
	public boolean shouldExecute()
	{
		return WPSettings.DRINK_FROM_CAULDRON.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Drink from cauldron";
	}

}
