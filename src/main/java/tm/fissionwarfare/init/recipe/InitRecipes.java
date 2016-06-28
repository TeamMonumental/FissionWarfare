package tm.fissionwarfare.init.recipe;

import cofh.thermalexpansion.block.TEBlocks;
import cofh.thermalexpansion.item.TEItems;
import cofh.thermalfoundation.block.TFBlocks;
import cofh.thermalfoundation.item.TFItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;

public class InitRecipes {

	public static void init() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.quartz_sand, 2), new Object[] { Blocks.sand, Blocks.gravel, InitItems.quartz_chunk, Items.clay_ball });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.cement, 3), new Object[] { Blocks.sand, Blocks.gravel, Items.clay_ball, });
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.cartridge, 10), new Object[] {
			" C ", "CGC", "CGC", 'C', TFItems.ingotCopper, 'G', Items.gunpowder
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.bullet, 16), new Object[] {
			" S ", "SCS", "SCS", 'S', InitItems.cartridge, 'C', TFItems.ingotLead 
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.lightning_rod, 2), new Object[] {
			" L ", "LBL", " L ", 'B', Items.blaze_rod, 'L', TFItems.ingotLumium
		}));
					
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.iron_frame, 32), new Object[] {
			"I I", " I ", "I I", 'I', "ingotIron"
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.nail, 3), new Object[] {
			"SSS", " S ", " S ", 'S', TFItems.nuggetPlatinum
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.nail_gun_magazine, 1), new Object[] {
			"FNF", "FNF", "FNF", 'N', new ItemStack(InitItems.nail), 'F', InitItems.steel_plate
		}));
	
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.gunpowder, 2), new Object[] {
			"SSS", "SCS", "SSS", 'S', TFItems.dustSulfur, 'C', Items.coal
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.gunpowder, 2), new Object[] {
			"SSS", "SCS", "SSS", 'S', TFItems.dustSulfur, 'C', new ItemStack(Items.coal, 1, 1)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.compressor, 1), new Object[] {
			"SSS", "CMC", "SSS", 'S', InitItems.steel_plate, 'C', new ItemStack(InitItems.motor, 1), 'M', new ItemStack(TEItems.itemMultimeter, 1)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.nail_gun, 1), new Object[] {
			"SSS", " BC", " SS", 'S', InitItems.steel_ingot, 'C', new ItemStack(InitItems.motor, 1), 'B', Blocks.stone_button
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.stock, 1), new Object[] {
			" SL", "SLS", "LS ", 'L', "logWood", 'S', "stairWood",
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.handle, 1), new Object[] {
			"SSS", "SLI", "SII", 'I', TFItems.ingotInvar, 'S', new ItemStack(InitItems.steel_plate, 1), 'L', Blocks.lever
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.barrel, 1), new Object[] {
			" SD", "SIS", "IS ", 'I', TFItems.ingotInvar, 'S', new ItemStack(InitItems.steel_plate), 'D', Blocks.dispenser
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shotgun, 1), new Object[] {
			"   ", "SHB", " T ", 'S', new ItemStack(InitItems.stock), 'T', new ItemStack(InitItems.handle), 'B', new ItemStack(InitItems.barrel), 'H', Blocks.hopper
		}));
		
		/////////////////////////////////////////////////////////CIRCUIT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1), new Object[] {
			"GGG", "ESE", "GGG", 'G', InitBlocks.reinforced_glass, 'E', TFItems.ingotElectrum, 'S', TEItems.powerCoilSilver
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1, 1), new Object[] {
			"HHH", "SCS", "HHH", 'H', TEBlocks.blockGlass, 'S', TFItems.ingotSignalum, 'C', InitItems.circuit
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.circuit, 1, 2), new Object[] {
			"LLL", "DCD", "LLL", 'L', TEBlocks.blockGlass, 'D', Items.diamond, 'C', new ItemStack(InitItems.circuit, 1, 1)
		}));
		
		/////////////////////////////////////////////////////////CANISTER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fuel_canister, 1, 0), new Object[] {
			" S ", "SBS", "SBS", 'S', InitItems.steel_ingot, 'B', TFItems.bucketCoal,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fuel_canister, 1, 1), new Object[] {
			" T ", "TBT", "TBT", 'T', TFItems.ingotBronze, 'B', TFItems.bucketPyrotheum,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fuel_canister, 1, 2), new Object[] {
			" T ", "TBT", "TBT", 'T', TFItems.ingotSilver, 'B', TFItems.bucketCryotheum,
		}));
		
		/////////////////////////////////////////////////////////CAPSULE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.fiery_capsule, 2), new Object[] {
			"LBL", "BMB", "LBL", 'L', Items.lava_bucket, 'B', Items.blaze_powder, 'M', Items.magma_cream
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.poison_capsule, 1), new Object[] {
			"RRR", "FPF", "RRR", 'R', Items.rotten_flesh, 'F', Items.fermented_spider_eye, 'P', new ItemStack(Items.fish, 1, 3)
		}));
		
		/*GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shrapnel_capsule, 1), new Object[] {
			"AAA", "AIA", "AAA", 'I', Items.iron_axe, 'A', Items.arrow,
		}));*/
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.nuclear_capsule, 1), new Object[] {
			"EEE", "UUU", "EEE", 'U', InitItems.uranium_pellet, 'E', TFItems.ingotEnderium,
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.electromagnetic_capsule, 1), new Object[] {
			"SSS", "SLS", "CPC", 'L', new ItemStack(InitItems.lightning_rod), 'C', TEItems.capacitorHardened, 'P', TEItems.powerCoilSilver, 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		////////////////////////////////////////////////////////EXPLOSIVES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.atomicExplosive, 1), new Object[] {
			"SRS", "BNB", "SRS", 'R', TFItems.bucketEnder, 'N', InitItems.nuclear_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.empExplosive, 1), new Object[] {
			"IGI", "BEB", "IGI", 'E', InitItems.electromagnetic_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'I', new ItemStack(InitItems.steel_plate), 'G', TFItems.bucketGlowstone
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.pyroExplosive, 1), new Object[] {
			"SPS", "BFB", "SPS", 'P', TFItems.bucketPyrotheum, 'F', InitItems.fiery_capsule, 'B', new ItemStack(InitBlocks.basicExplosive, 1), 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.chemicalExplosive, 1), new Object[] {
			"SBS", "EPE", "SBS", 'B', TFItems.bucketPetrotheum, 'P', InitItems.poison_capsule, 'E', new ItemStack(InitBlocks.basicExplosive, 1), 'S', new ItemStack(InitItems.steel_plate)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.basicExplosive, 1), new Object[] {
			"SES", "ERE", "SES", 'S', InitItems.steel_plate, 'E', Blocks.tnt, 'R', TEItems.powerCoilSilver
		}));
		
		/////////////////////////////////////////////////////////MACHINES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.sentry_turret, 1), new Object[] {
			"R  ", "MCD", "MGM", 'M', new ItemStack(TEBlocks.blockFrame, 1, 2), 'C', new ItemStack(InitItems.circuit, 1, 2), 'G', new ItemStack(InitItems.motor), 'D', Blocks.dispenser, 'R', Blocks.redstone_torch
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.missile_factory, 1), new Object[] {
			"MGM", "DCD", "MGM", 'M', new ItemStack(TEBlocks.blockFrame, 1, 1), 'C', new ItemStack(InitItems.circuit, 1, 0), 'G', TFItems.gearGold, 'D', new ItemStack(TEBlocks.blockWorkbench, 1, 2)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.launch_pad, 1), new Object[] {
			"GGG", "MPM", "MCM", 'M', new ItemStack(TEBlocks.blockFrame, 1, 2), 'C', new ItemStack(InitItems.circuit, 1, 1), 'G', new ItemStack(InitItems.steel_plate), 'P', new ItemStack(InitItems.motor)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.support_frame, 24, 1), new Object[] {
			"BSB", "BSB", "BSB", 'S', new ItemStack(InitItems.steel_plate), 'B', Blocks.iron_bars
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.steel_block, 1), new Object[] {
			"BBB", "BBB", "BBB", 'B', new ItemStack(InitItems.steel_ingot, 1)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.motor, 1), new Object[] {
			"CCC", "STS", "CCC", 'S', TFItems.gearInvar, 'C', TFItems.ingotCopper, 'T', TEItems.powerCoilSilver
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.location_linker, 1), new Object[] {
			"R  ", "SCS", "SBS", 'S', InitItems.steel_plate, 'B', Blocks.stone_button, 'R', Blocks.redstone_torch, 'C', new ItemStack(InitItems.circuit, 1, 0)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.control_panel, 1), new Object[] {
			"GGG", "SCS", "SRS", 'S', InitItems.steel_plate, 'G', InitBlocks.reinforced_glass,'C', new ItemStack(InitItems.circuit, 1, 1), 'R', TEItems.powerCoilElectrum
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.support_frame, 1), new Object[] {
			"SGS", "SGS", "SGS", 'S', InitItems.steel_plate, 'G', Blocks.iron_bars
		}));
		
	}
}
