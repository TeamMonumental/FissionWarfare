package tm.fissionwarfare.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tm.fissionwarfare.api.ITieredItem;

public class TierTooltipEvent {
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		
		if (event.itemStack.getItem() instanceof ITieredItem) {		
			
			event.toolTip.add("Tier " + ((ITieredItem)event.itemStack.getItem()).getTier(event.itemStack));
		}
	}
}
