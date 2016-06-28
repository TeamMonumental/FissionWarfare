package tm.fissionwarfare;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.event.OnPlayerJoinEvent;
import tm.fissionwarfare.event.SecurityEvent;
import tm.fissionwarfare.gui.base.GuiHandler;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitEntities;
import tm.fissionwarfare.init.InitFluids;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTileEntities;
import tm.fissionwarfare.init.InitToolMaterials;
import tm.fissionwarfare.init.recipe.InitRecipes;
import tm.fissionwarfare.init.recipe.InitTERecipes;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.packet.ServerPacketHandler;
import tm.fissionwarfare.proxy.IProxy;
import tm.fissionwarfare.world.WorldGenOre;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, dependencies = Reference.DEPENDENCIES)
public class FissionWarfare {
	
	@Instance(Reference.MOD_ID)
	public static FissionWarfare instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
		
	public static Configuration config;
	public static SimpleNetworkWrapper network;	
	
	public static WorldGenOre worldGenOre;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		config = new Configuration(event.getSuggestedConfigurationFile());
		FWConfig.syncConfig();
		
		FMLCommonHandler.instance().bus().register(new FWConfig());
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("fissionwarfare");
		network.registerMessage(ServerPacketHandler.Handler.class, ServerPacketHandler.class, 0, Side.SERVER);
		network.registerMessage(ClientPacketHandler.Handler.class, ClientPacketHandler.class, 1, Side.CLIENT);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		MinecraftForge.EVENT_BUS.register(new SecurityEvent());
		FMLCommonHandler.instance().bus().register(new OnPlayerJoinEvent());
		
		InitToolMaterials.init();
		
		InitItems.init();
		InitBlocks.init();
		InitFluids.init();
	}
		
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerRenders();
		
		InitRecipes.init();
		InitTERecipes.init();
		
		InitEntities.init();
		InitTileEntities.init();
		
		worldGenOre = new WorldGenOre();
		GameRegistry.registerWorldGenerator(worldGenOre, 1);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}