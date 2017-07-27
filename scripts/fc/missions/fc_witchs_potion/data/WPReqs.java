package scripts.fc.missions.fc_witchs_potion.data;

import java.util.List;

import scripts.fc.framework.mission.Mission;
import scripts.fc.framework.quest.InvBankBool;
import scripts.fc.framework.quest.InvBankBool.TYPE;
import scripts.fc.framework.quest.Order;
import scripts.fc.framework.quest.SettingBool;
import scripts.fc.framework.requirement.item.ItemRequirement;
import scripts.fc.framework.requirement.item.ReqItem;
import scripts.fc.framework.requirement.item.SingleReqItem;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.missions.fc_witchs_potion.FCWitchsPotion;

public class WPReqs extends ItemRequirement
{
	public static final int EYE_OF_NEWT = 221, ONION = 1957, RAW_BEEF = 2132, RAT_TAIL = 300,
			COOKED_MEAT = 2142, BURNT_MEAT = 2146;
	
	public WPReqs(FCMissionScript script)
	{
		super(script);
	}

	@Override
	public ReqItem[] getReqItems()
	{
		return new ReqItem[]
		{
			new SingleReqItem(EYE_OF_NEWT, 1, true, true).when(new SettingBool(FCWitchsPotion.SETTING, 1, true, Order.BEFORE_EQUALS)),
			new SingleReqItem(ONION, 1, true, true).when(new SettingBool(FCWitchsPotion.SETTING, 1, true, Order.BEFORE_EQUALS)),
			new SingleReqItem(RAW_BEEF, 1, true, true)
				.when(new SettingBool(FCWitchsPotion.SETTING, 1, true, Order.BEFORE_EQUALS)
						.and(new InvBankBool(COOKED_MEAT, 1, TYPE.IN_ONE, false)
						.and(new InvBankBool(BURNT_MEAT, 1, TYPE.IN_ONE, false)))),
		};
	}
}
