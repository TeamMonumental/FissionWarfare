package tm.fissionwarfare.block;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public abstract class BlockContainerBase extends BlockContainer {

	private final Random random = new Random();

	float pixel = 1F/16F;
	
	public BlockContainerBase(String name, String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {
		super(material);		
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + imageName);
		setHarvestLevel("pickaxe", harvestLevel);
		setStepSound(stepSound);
		setHardness(hardness);
		setResistance(resistance);
		
		if (isRegistered) {
			setCreativeTab(InitTabs.tabMain);
			GameRegistry.registerBlock(this, name);
		}
	}	
	
	public BlockContainerBase(String name, String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		this(name, imageName, material, harvestLevel, hardness, resistance, stepSound, true);
	}
	
	public BlockContainerBase(String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		this(imageName, imageName, material, harvestLevel, hardness, resistance, stepSound, true);
	}
	
	public BlockContainerBase setBounds(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
		setBlockBounds(xStart * pixel, yStart * pixel, zStart * pixel, xEnd * pixel, yEnd * pixel, zEnd * pixel);
		return this;
	}
	
	public abstract TileEntity getTileEntity(int meta); 
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return getTileEntity(meta);
	}
	
	@Override	
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

		if (!(world.getTileEntity(x, y, z) instanceof ISidedInventory)) {
			return;
		}
		
		ISidedInventory tileentity = (ISidedInventory) world.getTileEntity(x, y, z);
		
		if (tileentity != null) {
			
			for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileentity.getStackInSlot(i1);

				if (itemstack != null) {
					
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
						
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, meta);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float f2, float f3) {
		
		if (!world.isRemote) {			
			FMLNetworkHandler.openGui(player, FissionWarfare.instance, 0, world, x, y, z);	
		}
		
		return true;	
	}
}
