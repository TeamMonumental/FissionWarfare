package tm.fissionwarfare.init;

import cofh.thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.block.BlockBase;
import tm.fissionwarfare.block.BlockControlPanel;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.block.BlockFission;
import tm.fissionwarfare.block.BlockLaunchPad;
import tm.fissionwarfare.block.BlockMissileFactory;
import tm.fissionwarfare.block.BlockOreBase;
import tm.fissionwarfare.block.BlockReinforced;
import tm.fissionwarfare.block.BlockSupportFrame;
import tm.fissionwarfare.block.BlockTurret;
import tm.fissionwarfare.explosion.type.EnumExplosionType;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretMissile;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class InitBlocks {

	//Main
	
	public static Block quartz_ore, sulfur_ore, uranium_ore;
	
	//Warfare
	
	public static Block steel_block;
	public static Block concrete;
	public static Block reinforced_glass;
	
	public static Block fission;
	
	public static Block basic_explosive;
	public static Block emp_explosive;
	public static Block pyro_explosive;
	public static Block chemical_explosive;
	public static Block atomic_explosive;
	public static Block flakey_explosive;
	public static Block fission_explosive;
		
	public static Block sentry_turret;
	public static Block missile_turret;
	
	public static Block missile_factory;
	
	public static Block launch_pad;
	public static Block control_panel;	
	public static Block support_frame;
	
	public static void init() {
		
		//Main
		
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(new ItemStack(InitItems.quartz_chunk), 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(TFItems.dustSulfur, 1, 2, 2, 4).setRareDrop(TFItems.dustNiter);		
		uranium_ore = new BlockOreBase("uranium").setDroppedItem(new ItemStack(InitItems.radioactive_chunk), 1, 2, 1, 2).setLightLevel(0.3F);
		
		//Warfare
		
		steel_block = new BlockReinforced("steel_block", 3, true, true);
		concrete = new BlockReinforced("concrete", 3, true, true);
		reinforced_glass = new BlockReinforced("reinforced_glass", 2, false, false);
		
		fission = new BlockFission();
		
		basic_explosive = new BlockExplosive("basic", EnumExplosionType.BASIC);
		emp_explosive = new BlockExplosive("emp", EnumExplosionType.EMP);
		pyro_explosive = new BlockExplosive("pyro", EnumExplosionType.PYRO);
		chemical_explosive = new BlockExplosive("chemical", EnumExplosionType.CHEMICAL);
		atomic_explosive = new BlockExplosive("atomic", EnumExplosionType.ATOMIC);
		flakey_explosive = new BlockExplosive("flakey", EnumExplosionType.FLAKEY);
		fission_explosive = new BlockExplosive("fission", EnumExplosionType.FISSION);
		
		sentry_turret = new BlockTurret("sentry", TileEntityTurretSentry.class);
		missile_turret = new BlockTurret("missile", TileEntityTurretMissile.class);
		
		missile_factory = new BlockMissileFactory();
		
		launch_pad = new BlockLaunchPad();
		control_panel = new BlockControlPanel();
		support_frame = new BlockSupportFrame();
	}	
}