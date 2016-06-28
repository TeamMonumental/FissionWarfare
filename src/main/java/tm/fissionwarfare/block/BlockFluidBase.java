package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import tm.fissionwarfare.Reference;

public class BlockFluidBase extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon, flowingIcon;

	private Fluid fluid;
	
	public BlockFluidBase(Fluid fluid) {
		super(fluid, Material.water);
		setBlockName(fluidName);
		this.fluid = fluid;
		GameRegistry.registerBlock(this, fluidName);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return (side == 0 || side == 1) ? stillIcon : flowingIcon;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
		return super.displaceIfPossible(world, x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		stillIcon = register.registerIcon(Reference.MOD_ID + ":fluids/" + fluidName + "_still");
		flowingIcon = register.registerIcon(Reference.MOD_ID + ":fluids/" + fluidName + "_flowing");
		fluid.setIcons(stillIcon, flowingIcon);
	}
}