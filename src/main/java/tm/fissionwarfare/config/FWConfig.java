package tm.fissionwarfare.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.Reference;

public class FWConfig {

	public static Configuration config = FissionWarfare.config;
		
	public static boolean enableIgnitingPlacedExplosives;
	public static boolean enableNailGun;
	
	public static boolean enableConcretePaticles;
	public static boolean enableTeamErrorMessage;
	
	public static boolean enableSulfurGen;
	public static boolean enableQuartzGen;
	public static boolean enableUraniumGen;
	public static boolean enableLimestoneGen;
	
	public static int missileBlockRangeMultiplier;
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
				
		if (event.modID.equals(Reference.MOD_ID)) {
			syncConfig();
		}
	}
	
	public static void syncConfig() {	
		
		FMLCommonHandler.instance().bus().register(FissionWarfare.instance);
		
		final String GENERAL_OPTIONS = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "General Options";
		config.setCategoryLanguageKey(GENERAL_OPTIONS, "category.general_options.name");
		config.addCustomCategoryComment(GENERAL_OPTIONS, "General options for " + Reference.MOD_NAME + ".");
			
			enableIgnitingPlacedExplosives = config.getBoolean("Ignite Placed Explosives", GENERAL_OPTIONS, true, "Enables or disables the ability to ignite placed explosives.");
			enableNailGun = config.getBoolean("Nail Gun", GENERAL_OPTIONS, true, "Enables or disables the ability to use the Nail Gun. Some may like disabling this for War Day.");		
		
		final String CLIENT = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "Client";
		config.setCategoryLanguageKey(CLIENT, "category.client.name");
		config.addCustomCategoryComment(CLIENT, "Options for clients.");
		
			enableConcretePaticles = config.getBoolean("Concrete Particles", CLIENT, true, "Enables or disables concrete particles.");
			enableTeamErrorMessage = config.getBoolean("Team Error Message", CLIENT, true, "Enables or disables the message when you place a unit without a team.");
		
		final String GEN = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "World Gen";
		config.setCategoryLanguageKey(GEN, "category.gen.name");
		config.addCustomCategoryComment(GEN, "Options for world gen.");
		
			enableSulfurGen = config.getBoolean("Sulfur Gen", GEN, true, "Enables or disables sulfur generating in the world.");
			enableQuartzGen = config.getBoolean("Quartz Gen", GEN, true, "Enables or disables quartz generating in the world.");
			enableUraniumGen = config.getBoolean("Uranium Gen", GEN, true, "Enables or disables uranium generating in the world.");
			enableLimestoneGen = config.getBoolean("Limestone Gen", GEN, true, "Enables or disables limestone generating in the world.");
		
		final String WARFARE = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "Warfare";
		config.setCategoryLanguageKey(WARFARE, "category.warfare.name");
		config.addCustomCategoryComment(WARFARE, "Options for Warfare.");
			
			missileBlockRangeMultiplier = config.getInt("Missle Block Range Multiplier", WARFARE, 1000, 0, 100000, "The multiplier for the Missile's fuel block range (Example: 1000 would be 1000, 2000, then 3000).");
		
		if (config.hasChanged()) {
			config.save();
		}
	}
}
