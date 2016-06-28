package tm.fissionwarfare.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tm.fissionwarfare.item.ItemBase;

public abstract class ItemBlockBase extends ItemBase {

	public ItemBlockBase(String imageName) {
		super("itemblock_" + imageName);
	}
	
	public abstract boolean placeBlocks(World world, int hitX, int hitY, int hitZ, int x, int y, int z);
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {
		
		int xOffset = 0, yOffset = 0, zOffset = 0;
		
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
			side = 1;
		} 
		
		else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
			
			if (side == 0) yOffset--;
			if (side == 1) yOffset++;
			if (side == 2) zOffset--;
			if (side == 3) zOffset++;
			if (side == 4) xOffset--;
			if (side == 5) xOffset++;			
		}

		int newX = x + xOffset, newY = y + yOffset, newZ = z + zOffset;
		
		if (!player.canPlayerEdit(newX, newY, newZ, side, stack) || stack.stackSize == 0) {
			return false;
		} 
		
		if (placeBlocks(world, x, y, z, newX, newY, newZ)) {
			stack.stackSize--;
			return true;
		}
		
		return false;
	}
	
	public void playSound(World world, int x, int y, int z, Block block) {
		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
	}
}
