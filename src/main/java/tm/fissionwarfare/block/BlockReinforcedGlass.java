package tm.fissionwarfare.block;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.itemblock.ItemBlockReinforced;

public class BlockReinforcedGlass extends BlockReinforced {

	public BlockReinforcedGlass() {
		super("reinforced_glass");
	}

	@Override
	public int getMaxMeta() {
		return 4;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side) {		
		Block block = iBlockAccess.getBlock(x, y, z);
		return block == this ? false : super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, getMaxMeta());		
	}
	
	@Override
	public int damageDropped(int meta) {
		
		int i;
		if (meta > 3) i = getMaxMeta();
		else i = 0;
		
		return i;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		
		if (metadata < getMaxMeta()) {
			return new ArrayList<ItemStack>();
		}
		
		return super.getDrops(world, x, y, z, metadata, fortune);
	}
}
