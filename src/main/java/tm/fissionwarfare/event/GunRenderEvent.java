package tm.fissionwarfare.event;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import tm.fissionwarfare.item.ItemGun;

public class GunRenderEvent {

	@SubscribeEvent(priority=EventPriority.LOW)
	public void gunRenderEvent(RenderPlayerEvent.Pre event) {

		ItemStack item = event.entityPlayer.getHeldItem();

		RenderPlayer rp = event.renderer;
		
		if (item != null && item.getItem() instanceof ItemGun) {
			
			rp.modelBipedMain.aimedBow = true;
			rp.modelArmor.aimedBow = true;
			rp.modelArmorChestplate.aimedBow = true;
		} 
		
		else {
			rp.modelBipedMain.aimedBow = false;
			rp.modelArmor.aimedBow = false;
			rp.modelArmorChestplate.aimedBow = false;
		}
	}	
}
