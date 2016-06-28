package tm.fissionwarfare.block;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.proxy.ClientProxy;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;
import tm.fissionwarfare.tileentity.machine.TileEntityControlPanel;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.math.Location;

public class BlockControlPanel extends BlockContainerBase {

	public BlockControlPanel() {
		super("control_panel", "steel_block", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal, false);		
		setBounds(2.6F, 0, 2.6F, 13.4F, 16, 13.4F);
		GameRegistry.registerBlock(this, "control_panel");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		Location loc = new Location(w, x, y, z);
		Location tileLoc = loc.add(loc.getMetadata(), true);
		
		if (!w.isRemote) {			
			FMLNetworkHandler.openGui(p, FissionWarfare.instance, 0, tileLoc.world, tileLoc.x, tileLoc.y, tileLoc.z);	
		}
		
		return true;
	}
	
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is) {
		
		int l = MathHelper.floor_double((double) (e.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;		
		w.setBlockMetadataWithNotify(x, y, z, l, 2);
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
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(InitItems.control_panel);
	}
	
	@Override
	public Item getItemDropped(int i1, Random rand, int i2) {
		return InitItems.control_panel;
	}

	@Override
	public TileEntity getTileEntity(int meta) {
		return new TileEntityControlPanel();
	}
}
