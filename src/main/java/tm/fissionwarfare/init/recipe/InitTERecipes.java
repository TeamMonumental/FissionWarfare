package tm.fissionwarfare.init.recipe;

import cofh.api.modhelpers.ThermalExpansionHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitFluids;
import tm.fissionwarfare.init.InitItems;

public class InitTERecipes {

	public static void init() {
		
		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.wet_cement), new FluidStack(InitFluids.concrete_mix, 1000));

		ThermalExpansionHelper.addCrucibleRecipe(8000, new ItemStack(InitItems.quartz_sand), new FluidStack(InitFluids.liquid_glass, 1000));
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.cement),	new ItemStack(InitItems.wet_cement), new FluidStack(FluidRegistry.WATER, 1000), false);
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.iron_frame), new ItemStack(InitBlocks.concrete, 1, 4), new FluidStack(InitFluids.concrete_mix, 1000), false);
		
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(InitItems.iron_frame), new ItemStack(InitBlocks.reinforced_glass, 1, 4), new FluidStack(InitFluids.liquid_glass, 1000), false);
		
		ThermalExpansionHelper.addPulverizerRecipe(2000, new ItemStack(InitItems.radioactive_chunk), new ItemStack(Blocks.cobblestone), new ItemStack(InitItems.uranium_pellet), 20);
		
		ThermalExpansionHelper.addSmelterRecipe(1000, new ItemStack(InitItems.steel_ingot), new ItemStack(InitItems.steel_ingot), new ItemStack(InitItems.steel_plate));
		
		//ThermalExpansionHelper.addPulverizerRecipe(input, output, sideoutput, energycost, rarespawn chance);
	}
}
