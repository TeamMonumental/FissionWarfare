package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.tileentity.machine.TileEntityMissileFactory;

public class BlockMissileFactory extends BlockContainerBase {

	private IIcon top_icon, bottom_icon;
	
	public BlockMissileFactory() {
		super("missile_factory", Material.iron, 2, 2, 2, Block.soundTypeMetal);
		setCreativeTab(InitTabs.tabWarfare);
	}
	
	@Override
	public TileEntity getTileEntity(int meta) {
		return new TileEntityMissileFactory();
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		
		if (side == 0) return bottom_icon;
		if (side == 1) return top_icon;
		return blockIcon;		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconreg) {
		
		blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":missile_factory_side");
		top_icon = iconreg.registerIcon(Reference.MOD_ID + ":missile_factory_top");
		bottom_icon = iconreg.registerIcon(Reference.MOD_ID + ":steel_block");
	}
}
