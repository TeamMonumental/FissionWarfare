package tm.fissionwarfare.util.math;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Location {

	public World world;
	public int x, y, z;

	public Location(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location(World world, Vector3d vector) {
		this.world = world;
		this.x = MathHelper.floor_double(vector.x);
		this.y = MathHelper.floor_double(vector.y);
		this.z = MathHelper.floor_double(vector.z);
	}
	
	public Location add(ForgeDirection dir, int scale) {
		return new Location(world, x + dir.offsetX * scale, y + dir.offsetY * scale, z + dir.offsetZ * scale);
	}
		
	public Location add(ForgeDirection dir) {
		return add(dir, 1);
	}
	
	public Location add(int meta, boolean frontDir) {
		
		Location loc;
		
		int offset = frontDir ? 1 : -1;
			
		if (meta == 0) loc = new Location(world, x, y, z - offset);
		else if (meta == 1) loc = new Location(world, x + offset, y, z);
		else if (meta == 2) loc = new Location(world, x, y, z + offset);
		else if (meta == 3) loc = new Location(world, x - offset, y, z);
		else loc = new Location(world, x, y, z);
						
		return loc;		
	}

	public Location copy() {
		return new Location(world, x, y, z);
	}
	
	public Block getBlock() {
		return world.getBlock(x, y, z);
	}

	public int getMetadata() {
		return world.getBlockMetadata(x, y, z);
	}
	
	public boolean hasTileEntity() {
		return getBlock().hasTileEntity(getMetadata());
	}
	
	public TileEntity getTileEntity() {
		return world.getTileEntity(x, y, z);
	}

	public double getDistance(Location location) {
		
		int dx = x - location.x;
		int dy = y - location.y;
		int dz = z - location.z;
		
		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}

	public float getBlockResistance(double explosionX, double explosionY, double explosionZ) {
		return getBlock().getExplosionResistance(null, world, x, y, z, explosionX, explosionY, explosionZ);
	}
	
	public void setBlockMetadata(int meta) {
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}
	
	public void setBlock(Block block) {
		world.setBlock(x, y, z, block);
	}
	
	public void setBlockToAir() {
		setBlock(Blocks.air);
	}
	
	public List<ItemStack> getDrops() {
		return getBlock().getDrops(world, x, y, z, getMetadata(), 0);		
	}
	
	public EntityItem dropItem(ItemStack stack) {
		EntityItem entityItem = new EntityItem(world, x, y, z, stack);
		world.spawnEntityInWorld(entityItem);
		return entityItem;
	}
	
	public void breakBlock() {
		
		List<ItemStack> drops = getDrops();
		
		for (ItemStack is : drops) {
			dropItem(is);
		}
		
		TileEntity entity = getTileEntity();
		
		if (entity != null && entity instanceof IInventory) {
			
			IInventory inv = (IInventory) entity;
			
			for (int i = 0 ; i < inv.getSizeInventory(); i++) {
				
				dropItem(inv.getStackInSlot(i));
			}
		}
		
		setBlockToAir();
	}
	
	public boolean checkBlock(Block block) {
		return getBlock() == block;
	}
	
	public boolean matches(Location loc) {
		return loc.x == x && loc.y == y && loc.z == z && loc.world.provider.dimensionId == world.provider.dimensionId;
	}
}
