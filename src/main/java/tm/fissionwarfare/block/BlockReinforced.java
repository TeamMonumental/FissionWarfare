package tm.fissionwarfare.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IReinforcedBlock;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.itemblock.ItemBlockMeta;
import tm.fissionwarfare.itemblock.ItemBlockReinforced;
import tm.fissionwarfare.util.math.MathUtil;

public abstract class BlockReinforced extends BlockMetaBase implements IReinforcedBlock {

	private IIcon[] textures = new IIcon[getMaxMeta() + 1];
	
	public BlockReinforced(String imageName) {
		super(imageName, Material.rock, 2, 0, Float.MAX_VALUE, Block.soundTypeStone, ItemBlockReinforced.class);
		setCreativeTab(InitTabs.tabWarfare);
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
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		
		list.add(new ItemStack(item, 1, 4));
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return (world.getBlockMetadata(x, y, z) * 2);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta >= textures.length) return textures[textures.length - 1]; 
		return textures[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":reinforced/" + imageName + "_" + i);
		}
	}
}
