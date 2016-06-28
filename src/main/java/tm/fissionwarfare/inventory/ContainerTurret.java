package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerTurret extends ContainerEnergyBase {

	public ContainerTurret(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
		
		addSlotToContainer(new SlotFilter(tileEntityEnergy, tileEntity, 0, 80, 39, InitItems.bullet));
	}
}
