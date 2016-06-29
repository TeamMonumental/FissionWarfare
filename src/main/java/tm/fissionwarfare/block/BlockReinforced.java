package tm.fissionwarfare.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.itemblock.ItemBlockReinforced;
import tm.fissionwarfare.proxy.ClientProxy;
import tm.fissionwarfare.util.math.MathUtil;

public class BlockReinforced extends BlockMetaBase {
	
	public IIcon[] broken_overlay = new IIcon[4];
	
	public int maxTiers;
	private boolean isOpaque;
	private boolean hasBrokenParticles;
	
	public BlockReinforced(String imageName, int maxTiers, boolean isOpaque, boolean hasBrokenParticles) {
		super(imageName, Material.rock, 2, 0, Float.MAX_VALUE, Block.soundTypeStone, ItemBlockReinforced.class);		
		this.maxTiers = maxTiers;
		this.isOpaque = isOpaque;
		this.hasBrokenParticles = hasBrokenParticles;		
		setCreativeTab(InitTabs.tabWarfare);
	}
	
	public int getRepairedMeta(int meta) {
				
		for (int i = 1; i <= maxTiers; i++) {
			
			if (meta <= i * 5) return i * 5;
		}
		
		return maxTiers * 5;
	}
	
	public int getDamagedMeta(int meta) {
		
		for (int i = 1; i <= maxTiers + 1; i++) {
			
			if (meta < i * 5) return i * 5 - 5;
		}
		
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {	
		
		for (int i = 1; i <= maxTiers; i++) {
			
			list.add(new ItemStack(item, 1, i * 5));
		}	
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return (world.getBlockMetadata(x, y, z) * 2);
	}
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta <= 4) {		
			
			int scale = MathUtil.scaleInt(meta, 4, 50);
			return 50 - scale;
		}
		
		return 0;
	}
	
	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
		int meta = world.getBlockMetadata(x, y, z);
		
		return (meta < 4) ? 4 - meta : 0;
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z,	Random rand) {
		
		if (hasBrokenParticles && FWConfig.enableConcretePaticles) {
			
			int meta = world.getBlockMetadata(x, y, z);
			
			if (!world.getBlock(x, y - 1, z).isBlockNormalCube()) {
			
				if (meta < 3) {
				
					for (int i = 0; i < (3 - meta); i++) {
						world.spawnParticle("blockdust_" + Block.getIdFromBlock(this) + "_" + world.getBlockMetadata(x, y, z), x + rand.nextDouble(), y, z + rand.nextDouble(), 0, 0, 0);
					}		
				}
			}	
		}	
	}
	
	@Override
	public boolean isOpaqueCube() {
		return isOpaque;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side) {		
		
		if (isOpaque) return super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
		
		Block block = iBlockAccess.getBlock(x, y, z);
		return block == this ? false : super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
	}

	public boolean renderAsNormalBlock() {
		return isOpaque;
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.REINFORCED_RENDER_ID;
	}
	
	public static int getColorDifference(int meta) {
		return (meta > 5 ? ((meta - 5) * 0x090909) : 0);
	}
	
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 0xFFFFFF;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int i) {
		return this.getBlockColor();
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		
		int meta = blockAccess.getBlockMetadata(x, y, z);
		
		return getBlockColor() - getColorDifference(meta);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, getRepairedMeta(world.getBlockMetadata(x, y, z)));		
	}
	
	@Override
	public int damageDropped(int meta) {
		return getDamagedMeta(meta);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		
		if (meta < 5) {
			return new ArrayList<ItemStack>();
		}
		
		return super.getDrops(world, x, y, z, meta, fortune);
	}
		
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		super.registerBlockIcons(iconReg);
		
		for (int i = 0; i < broken_overlay.length; i++) {
			broken_overlay[i] = iconReg.registerIcon(Reference.MOD_ID + ":reinforced/broken_overlay_" + (i + 1));
		}
	}
}
