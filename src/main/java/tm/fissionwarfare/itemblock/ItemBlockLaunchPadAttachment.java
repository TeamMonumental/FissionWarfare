package tm.fissionwarfare.itemblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.api.IShiftInfoItem;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.ItemLoreUtil;
import tm.fissionwarfare.util.math.Location;

public abstract class ItemBlockLaunchPadAttachment extends ItemBlockBase implements IShiftInfoItem {

	public ItemBlockLaunchPadAttachment(String imageName) {
		super(imageName);
	}
	
	public abstract boolean placeAttachment(World world, Location hitLoc);

	@Override
	public void addShiftLore(List<String> list) {		
		list.add("A component to the Launch Pad.");
		list.add("Right-click : Uses item.");
	}
	
	@Override
	public boolean placeBlocks(World world, int hitX, int hitY, int hitZ, int x, int y, int z) {

		Location hitLoc = new Location(world, hitX, hitY, hitZ);
		
		if (!world.isRemote && hitLoc.getBlock() != null && hitLoc.hasTileEntity() && hitLoc.getTileEntity() instanceof TileEntityLaunchPad) {
			
			return placeAttachment(world, hitLoc);
		}
		
		return false;
	}
}
