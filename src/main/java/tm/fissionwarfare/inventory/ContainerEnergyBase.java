package tm.fissionwarfare.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ICrafting;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class ContainerEnergyBase extends ContainerBase {
	
	private int lastProgress;	
	public TileEntityEnergyBase tileEntityEnergy = (TileEntityEnergyBase)tileEntity;
	
	public ContainerEnergyBase(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
	}
	
	public void addCraftingToCrafters(ICrafting craft) {
		super.addCraftingToCrafters(craft);

		craft.sendProgressBarUpdate(this, 1, tileEntityEnergy.progress);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		if (player instanceof EntityPlayerMP) {
			FissionWarfare.network.sendTo(new ClientPacketHandler("sync.energy%" + tileEntityEnergy.storage.getEnergyStored()), (EntityPlayerMP) player);
		}
			
		for (int i = 0; i < this.crafters.size(); ++i) {
			
			ICrafting craft = (ICrafting) this.crafters.get(i);
			
			if (this.lastProgress != tileEntityEnergy.progress) {
				craft.sendProgressBarUpdate(this, 1, tileEntityEnergy.progress);
			}
		}
		
		lastProgress = tileEntityEnergy.progress;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int i2) {
		
		if (i == 0) tileEntityEnergy.storage.setEnergyStored(i2);		
		if (i == 1) tileEntityEnergy.progress = i2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
