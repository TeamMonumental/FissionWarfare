package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.math.Location;

public class BlockLaunchPad extends BlockContainerBase {

	public BlockLaunchPad() {
		super("launch_pad", "steel_block", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal);
		setBounds(0, 0, 0, 16, 10.9F, 16);
		setCreativeTab(InitTabs.tabWarfare);
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is) {
		
		int l = MathHelper.floor_double((double) (e.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;		
		w.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		destroyLaunchPad(world, x, y, z);
	}
	
	private void destroyLaunchPad(World world, int x, int y, int z) {
		
		Location loc = new Location(world, x, y, z);
		
		if (loc.hasTileEntity()) {
			
			TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)loc.getTileEntity();
		
			if (tileEntity.missile != null) loc.dropItem(tileEntity.missile);
			if (tileEntity.getSupportFrame() != null) tileEntity.destroyFrame();
			if (tileEntity.getControlPanel() != null) loc.add(loc.getMetadata(), false).breakBlock();
		}		
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f, float f2, float f3) {
		
		TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)world.getTileEntity(x, y, z);
		
		player.swingItem();
		
		if (!world.isRemote) {
			
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemMissile) {
			
				if (tileEntity.missile == null) {
					
					tileEntity.missile = player.getCurrentEquippedItem();
					tileEntity.update();				
										
					player.inventory.decrStackSize(player.inventory.currentItem, 1);
					player.inventory.markDirty();
					
					FissionWarfare.network.sendTo(new ClientPacketHandler("playsound%random.click%" + x + "%" + y + "%" + z + "%" + 1), (EntityPlayerMP) player);
					
					return true;
				}			
			}
		
			else if (player.getCurrentEquippedItem() == null) {
			
				if (tileEntity.missile != null) {
									
					player.inventory.setInventorySlotContents(player.inventory.currentItem, tileEntity.missile);
					player.inventory.markDirty();
					
					tileEntity.missile = null;
					tileEntity.update();
					
					FissionWarfare.network.sendTo(new ClientPacketHandler("playsound%random.click%" + x + "%" + y + "%" + z + "%" + 1), (EntityPlayerMP) player);
					
					return true;
				}
			}	
		}
				
		return false;		
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public TileEntity getTileEntity(int meta) {
		return new TileEntityLaunchPad();
	}
}
