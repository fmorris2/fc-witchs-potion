package scripts.fc.missions.fc_witchs_potion.data;

import java.util.Arrays;

import scripts.fc.framework.quest.InvBankBool;
import scripts.fc.framework.quest.InvBankBool.TYPE;
import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.QuestState;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.missions.fc_witchs_potion.FCWitchsPotion;

public enum WPSettings
{
	START_QUEST
	(
		new QuestState
		(
			new SettingBool(FCWitchsPotion.SETTING, 0, true, Order.EQUALS)
		)
	),
	
	COLLECT_RAT_TAIL
	(
		new QuestState
		(
			new SettingBool(FCWitchsPotion.SETTING, 1, true, Order.EQUALS),
			new InvBankBool(WPReqs.RAT_TAIL, 1, TYPE.NOT_IN_EITHER, true)
		)
	),
	
	BURN_BEEF
	(
		new QuestState
		(
			new SettingBool(FCWitchsPotion.SETTING, 1, true, Order.EQUALS),
			new InvBankBool(WPReqs.BURNT_MEAT, 1, TYPE.NOT_IN_EITHER, true)
		)
	),
	
	HETTY_DIALOGUE
	(
		new QuestState
		(
			new SettingBool(FCWitchsPotion.SETTING, 1, true, Order.EQUALS),
			new InvBankBool(WPReqs.BURNT_MEAT, 1, TYPE.IN_ONE, true),
			new InvBankBool(WPReqs.EYE_OF_NEWT, 1, TYPE.IN_ONE, true),
			new InvBankBool(WPReqs.ONION, 1, TYPE.IN_ONE, true),
			new InvBankBool(WPReqs.RAT_TAIL, 1, TYPE.IN_ONE, true)
		)
	),
	
	DRINK_FROM_CAULDRON
	(
		new QuestState
		(
			new SettingBool(FCWitchsPotion.SETTING, 2, true, Order.EQUALS)
		)
	),
	
	QUEST_COMPLETE
	(
		new QuestState
		(
			new SettingBool(FCWitchsPotion.SETTING, 3, true, Order.AFTER_EQUALS)
		)
	);
	
	private QuestState[] states;
	
	WPSettings(QuestState... states)
	{
		this.states = states;
	}
	
	public boolean isValid()
	{
		return Arrays.stream(states).allMatch(s -> s.validate());
	}
}
