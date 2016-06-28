package tm.fissionwarfare.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.itemblock.ItemBlockMeta;

public class BlockSupportFrame extends BlockBase {

	private IIcon top_icon;
	
	public BlockSupportFrame() {
		super("support_frame", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal, false);
		GameRegistry.registerBlock(this, "support_frame");
	}
	
	private void setMetaBounds(int meta, int x, int y, int z) {		
		if (meta == 0) setBounds(5, 0, 0, 11, 16, 16);
		else setBounds(0, 0, 5, 16, 16, 11);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setMetaBounds(world.getBlockMetadata(x, y, z), x, y, z);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		setMetaBounds(world.getBlockMetadata(x, y, z), x, y, z);
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setMetaBounds(world.getBlockMetadata(x, y, z), x, y, z);
		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
	
	public void setBlockBoundsForItemRender() {
		setBounds(0, 0, 5, 16, 16, 11);
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack is) {
				
		int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = world.getBlockMetadata(x, y, z) >> 2;
        ++l;
        l %= 4;

        if (l == 0) world.setBlockMetadataWithNotify(x, y, z, 0 | i1 << 2, 2);
        if (l == 1) world.setBlockMetadataWithNotify(x, y, z, 1 | i1 << 2, 2);
        if (l == 2) world.setBlockMetadataWithNotify(x, y, z, 0 | i1 << 2, 2);        
        if (l == 3) world.setBlockMetadataWithNotify(x, y, z, 1 | i1 << 2, 2);
    }
		
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		
		for (int yPos = -1; yPos < 2; yPos++) {
			
			if (world.getBlock(x, y + yPos, z) instanceof BlockSupportFrame) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(InitItems.support_frame);		
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<ItemStack>();
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {		
		Block block = world.getBlock(x, y, z);
		return block == this && (side == 0 || side == 1) ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		
		if (side < 2 || side == 2 + (meta * 2) || side == 3 + (meta * 2)) {
			return top_icon;
		}
		
		return blockIcon;		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconreg) {
		
		blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":support_frame_side");
		top_icon = iconreg.registerIcon(Reference.MOD_ID + ":support_frame_top");
	}
}
