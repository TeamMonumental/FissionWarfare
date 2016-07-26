package tm.fissionwarfare.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import tm.fissionwarfare.api.IShiftInfoItem;
import tm.fissionwarfare.api.ITieredItem;
import tm.fissionwarfare.itemblock.ItemBlockReinforced;
import tm.fissionwarfare.util.ItemLoreUtil;
import tm.fissionwarfare.util.NBTUtil;

public class LoreEvent {

	@SubscribeEvent
	public void onTierLore(ItemTooltipEvent event) {
		
		if (event.itemStack.getItem() instanceof ITieredItem) {		
			
			event.toolTip.add("Tier " + ((ITieredItem)event.itemStack.getItem()).getTier(event.itemStack));
		}
		
		if (event.itemStack.getItem() instanceof ItemBlockReinforced) {
			
			event.toolTip.add("Durability: " + event.itemStack.getItemDamage());
		}
	}
	
	@SubscribeEvent
	public void onDescriptionLore(ItemTooltipEvent event) {
		
		if (event.itemStack.getItem() instanceof IShiftInfoItem) {
			
			IShiftInfoItem info = (IShiftInfoItem)event.itemStack.getItem();
			
			if (ItemLoreUtil.addShiftLore(event.toolTip)) {			
				
				info.addShiftLore(event.toolTip);
			}
		}
	}
	
	@SubscribeEvent
	public void onWrenchedLore(ItemTooltipEvent event) {
		
		NBTTagCompound nbt = NBTUtil.getNBT(event.itemStack);
		
		if (nbt.hasKey("energy")) {
			
			if (ItemLoreUtil.addCtrlLore(event.toolTip)) {
				
				if (nbt.hasKey("energy")) {
				
					event.toolTip.add(EnumChatFormatting.GOLD + "RF : " + EnumChatFormatting.AQUA + nbt.getInteger("energy"));
				}
			}
		}	
	}
}
