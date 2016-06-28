package tm.fissionwarfare.init;

import cofh.thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.block.BlockBase;
import tm.fissionwarfare.block.BlockConcrete;
import tm.fissionwarfare.block.BlockControlPanel;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.block.BlockLaunchPad;
import tm.fissionwarfare.block.BlockMissileFactory;
import tm.fissionwarfare.block.BlockOreBase;
import tm.fissionwarfare.block.BlockReinforcedGlass;
import tm.fissionwarfare.block.BlockSupportFrame;
import tm.fissionwarfare.block.BlockSentryTurret;
import tm.fissionwarfare.explosion.type.EnumExplosionType;

public class InitBlocks {

	//Main
	
	public static Block quartz_ore, sulfur_ore, uranium_ore;
	public static Block steel_block;
	
	//Warfare
	
	public static Block concrete;
	public static Block reinforced_glass;
	
	public static Block basicExplosive;
	public static Block empExplosive;
	public static Block pyroExplosive;
	public static Block chemicalExplosive;
	public static Block atomicExplosive;
	
	public static Block sentry_turret;
	
	public static Block missile_factory;
	
	public static Block launch_pad;
	public static Block control_panel;	
	public static Block support_frame;
	
	public static void init() {
		
		//Main
		
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(new ItemStack(InitItems.quartz_chunk), 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(TFItems.dustSulfur, 1, 2, 2, 4).setRareDrop(TFItems.dustNiter);		
		uranium_ore = new BlockOreBase("uranium").setDroppedItem(new ItemStack(InitItems.radioactive_chunk), 1, 2, 1, 2).setLightLevel(0.3F);
		
		steel_block = new BlockBase("steel_block", Material.iron, 2, 2.0F, 2.0F, Block.soundTypeMetal);	
		
		//Warfare
		
		concrete = new BlockConcrete();
		reinforced_glass = new BlockReinforcedGlass();
		
		basicExplosive = new BlockExplosive("basic", EnumExplosionType.BASIC);
		empExplosive = new BlockExplosive("emp", EnumExplosionType.EMP);
		pyroExplosive = new BlockExplosive("pyro", EnumExplosionType.PYRO);
		chemicalExplosive = new BlockExplosive("chemical", EnumExplosionType.CHEMICAL);
		atomicExplosive = new BlockExplosive("atomic", EnumExplosionType.ATOMIC);
		
		sentry_turret = new BlockSentryTurret();
		
		missile_factory = new BlockMissileFactory();
		
		launch_pad = new BlockLaunchPad();
		control_panel = new BlockControlPanel();
		support_frame = new BlockSupportFrame();
	}	
}