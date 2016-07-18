package tm.fissionwarfare.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitBlocks;

public class NEIFissionWarfareConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		
		API.hideItem(new ItemStack(InitBlocks.control_panel));
		API.hideItem(new ItemStack(InitBlocks.support_frame));
		API.hideItem(new ItemStack(InitBlocks.fission));
		
		API.registerRecipeHandler(new MissileFactoryRecipeHandler());
	}
	
	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.VERSION;
	}
}
