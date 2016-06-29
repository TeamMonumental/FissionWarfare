package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretBase;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class BlockTurret extends BlockContainerBase {

	public Class tileEntity;
	
	public BlockTurret(String name, Class tileEntity) {
		super(name + "_turret", "steel_block", Material.iron, 2, 15, 1, Block.soundTypeMetal);
		this.tileEntity = tileEntity;
		setBounds(1.5F, 0, 1.5F, 14.5F, 8, 14.5F);
		setCreativeTab(InitTabs.tabWarfare);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public TileEntity getTileEntity(int meta) {
		
		try {
			return (TileEntity) tileEntity.newInstance();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
