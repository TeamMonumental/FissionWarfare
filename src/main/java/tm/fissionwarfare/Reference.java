package tm.fissionwarfare;

import net.minecraft.util.ResourceLocation;

public class Reference {
	
	public static final String MOD_ID = "FissionWarfare";
	public static final String MOD_NAME = "Fission Warfare";
	public static final String VERSION = "1.7.10-1.0.0";
	public static final String CLIENT_PROXY_CLASS = "tm.fissionwarfare.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "tm.fissionwarfare.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "tm.fissionwarfare.gui.base.GuiFactory";
	public static final String DEPENDENCIES = "after:ThermalExpansion;after:CoFHCore;after:ThermalFoundation";
	
	public static final ResourceLocation GUI_TEXTURES = new ResourceLocation(MOD_ID + ":textures/gui/gui_textures.png");
	
	public static int armorIDCompressor;
}
