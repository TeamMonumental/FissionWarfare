package tm.fissionwarfare.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.NBTUtil;
import tm.fissionwarfare.util.math.Location;

public class PlaceStorageEvent {

	@SubscribeEvent
	public void onBlockPlace(PlaceEvent event) {
		
		Location loc = new Location(event.world, event.x, event.y, event.z);
		TileEntity tileEntity = loc.getTileEntity();
			
		if (tileEntity instanceof TileEntityEnergyBase) {
				
			TileEntityEnergyBase energy = (TileEntityEnergyBase) tileEntity;
				
			NBTTagCompound nbt = NBTUtil.getNBT(event.itemInHand);
				
			if (nbt.hasKey("energy")) {
				energy.storage.modifyEnergyStored(nbt.getInteger("energy"));
			}
		}	
	}	
}
