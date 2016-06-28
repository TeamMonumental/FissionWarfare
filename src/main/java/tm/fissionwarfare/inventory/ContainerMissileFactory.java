package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerMissileFactory extends ContainerEnergyBase {

	public ContainerMissileFactory(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
		
		addSlotToContainer(new SlotFilter(tileEntity, tileEntity, 0, 48, 21, BlockExplosive.class));
		addSlotToContainer(new SlotFilter(tileEntity, tileEntity, 1, 48, 43, InitItems.circuit));
		addSlotToContainer(new SlotFilter(tileEntity, tileEntity, 2, 48, 65, InitItems.fuel_canister));
		
		addSlotToContainer(new SlotFilter(tileEntity, tileEntity, 3, 80, 43, InitItems.steel_plate));
		addSlotToContainer(new SlotFilter(tileEntity, tileEntity, 4, 112, 43));		
	}
}
