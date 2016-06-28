package tm.fissionwarfare.config;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.Reference;

public class GuiFWConfig extends GuiConfig {

	public GuiFWConfig(GuiScreen screen) {
		super(screen, new ConfigElement(FissionWarfare.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(FissionWarfare.config.toString()));
	}
}
