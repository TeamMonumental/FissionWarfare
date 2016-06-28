package tm.fissionwarfare.init;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tm.fissionwarfare.block.BlockFluidBase;
import tm.fissionwarfare.fluid.FluidBase;

public class InitFluids {

	public static Fluid concrete_mix;	
	public static Fluid liquid_glass;
	
	public static Block block_concrete_mix;	
	public static Block block_liquid_glass;
	
	public static void init() {
		
		concrete_mix = new FluidBase("concrete_mix");		
		liquid_glass = new FluidBase("liquid_glass");
		
		block_concrete_mix = new BlockFluidBase(concrete_mix);		
		block_liquid_glass = new BlockFluidBase(liquid_glass);
	}
}
