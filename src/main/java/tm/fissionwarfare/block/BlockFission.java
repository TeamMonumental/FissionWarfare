package tm.fissionwarfare.block;

import java.util.Random;

import cofh.core.entity.EntitySelectorInRangeByType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tm.fissionwarfare.damage.DamageSourceCustom;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.util.math.Location;

public class BlockFission extends BlockBase {

	Random rand = new Random();
	
	public BlockFission() {
		super("fission", Material.rock, 0, 0, Float.MAX_VALUE, Block.soundTypeStone);
		setBlockUnbreakable();
		setTickRandomly(true);
	}
	
	@Override
	public int tickRate(World world) {
		return 10;
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		
		if (entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer)entity;
			
			player.attackEntityFrom(new DamageSourceCustom(player.getDisplayName() + " was consumed by fission"), 5);			
		}
		
		else entity.attackEntityFrom(DamageSource.magic, 5);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
				
		for (int offX = -1; offX <= 1; offX++) {
			
			for (int offY = -1; offY <= 1; offY++) {
				
				for (int offZ = -1; offZ <= 1; offZ++) {
					
					Location loc = new Location(world, x + offX, y + offY, z + offZ);
									
					if (rand.nextInt(2) == 1 && loc.getBlock() != this && loc.getBlock() != Blocks.air) {
					
						loc.setBlock(this);						
					}
				}
			}
		}
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
			
		world.spawnParticle("portal", x + rand.nextDouble(), y + 1, z + rand.nextDouble(), 0, 0.5D, 0);
	}
}
