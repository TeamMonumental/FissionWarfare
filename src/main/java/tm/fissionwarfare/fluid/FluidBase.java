package tm.fissionwarfare.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidBase extends Fluid {

	public FluidBase(String name) {
		super(name);		
		setDensity(5000);
		setViscosity(8000);
		FluidRegistry.registerFluid(this);
	}
}
