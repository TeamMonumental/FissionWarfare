package tm.fissionwarfare.event;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tm.fissionwarfare.api.ITieredItem;
import tm.fissionwarfare.block.BlockReinforced;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.itemblock.ItemBlockReinforced;

public class TierTooltipEvent {
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		
		if (event.itemStack.getItem() instanceof ITieredItem) {		
			
			event.toolTip.add("Tier " + ((ITieredItem)event.itemStack.getItem()).getTier(event.itemStack));
		}
		
		if (event.itemStack.getItem() instanceof ItemBlockReinforced) {
			
			event.toolTip.add("Durability: " + event.itemStack.getItemDamage());
		}
	}
}
