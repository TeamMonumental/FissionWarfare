package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public class ContainerLaunchPad extends ContainerBase {

	public ContainerLaunchPad(EntityPlayer player, TileEntityInventoryBase tileEntity) {
		super(player, tileEntity);

		//addSlotToContainer(new SlotFilter(tileEntity, tileEntity, 0, 0, 21, InitItems.missile));
	}
}
