package tm.fissionwarfare.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IExplosiveBlock;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.explosion.type.EnumExplosionType;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.proxy.ClientProxy;

public class BlockExplosive extends BlockBase implements IExplosiveBlock {
		
	private IIcon top_icon;
	
	private EnumExplosionType explosion;
	private boolean isHighTier;

	public BlockExplosive(String imagePath, EnumExplosionType explosion) {
		super(imagePath + "_explosive", Material.tnt, 0, 0, 0, Block.soundTypeStone);
		this.explosion = explosion;
		setCreativeTab(InitTabs.tabWarfare);
	}

	public BlockExplosive setHighTier() {		
		isHighTier = true;		
		return this;
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);

		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			activate(world, x, y, z);
		}
	}

	public void onNeighborBlockChange(World world, int x, int y, int z,	Block block) {
		
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {			
			activate(world, x, y, z);
		}
	}
	
	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {		
		activate(world, x, y, z);
	}
	
	public void activate(World world, int x, int y, int z) {	
		
		if (FWConfig.enableIgnitingPlacedExplosives) {
			
			world.setBlockToAir(x, y, z);
			world.spawnEntityInWorld(new EntityExplosive(world, x, y, z, this));
		}	 
	}

	@Override
	public EnumExplosionType getExplosion() {
		return explosion;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		
		if (side == 0 || side == 1) {
			return top_icon;
		}
		
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		
		top_icon = iconReg.registerIcon(Reference.MOD_ID + ":explosives/explosive_top_" + (isHighTier ? "black" : "green"));
		blockIcon = iconReg.registerIcon(Reference.MOD_ID + ":explosives/" + imageName);		
	}
}
