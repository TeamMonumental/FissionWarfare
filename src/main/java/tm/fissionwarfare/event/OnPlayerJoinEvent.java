package tm.fissionwarfare.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class OnPlayerJoinEvent {
	
	@SubscribeEvent
	public void onPlayerJoinWorld(PlayerLoggedInEvent event) {
		
		for (Object o : event.player.worldObj.loadedTileEntityList) {
			
			if (o != null && o instanceof TileEntityLaunchPad) {
				
				TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)o;
				
				tileEntity.update();
			}
		}
	}
}
