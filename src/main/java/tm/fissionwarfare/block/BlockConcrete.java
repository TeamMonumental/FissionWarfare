package tm.fissionwarfare.block;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.init.InitItems;

public class BlockConcrete extends BlockReinforced {
	
	public static int[] metaTiers = new int[]{4, 9, 14};
	
	public BlockConcrete() {
		super("concrete");
	}	
			
	@Override
	public int getMaxMeta() {
		return 14;
	}
		
	public int getRepairedMeta(int meta) {
		if (meta > 9) return metaTiers[2];
		else if (meta > 4) return metaTiers[1];
		else return metaTiers[0];
	}
			
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, getRepairedMeta(world.getBlockMetadata(x, y, z)));		
	}
	
	@Override
	public int damageDropped(int meta) {
		if (meta > 13) return metaTiers[2];
		else if (meta > 8) return metaTiers[1];
		else if (meta > 3) return metaTiers[0];
		else return 0;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		
		if (meta < 4) {
			return new ArrayList<ItemStack>();
		}
		
		return super.getDrops(world, x, y, z, meta, fortune);
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z,	Random rand) {
		
		if (FWConfig.enableConcretePaticles) {
			
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
}
