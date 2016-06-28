package tm.fissionwarfare.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.event.GunRenderEvent;
import tm.fissionwarfare.event.TierTooltipEvent;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.key.KeyBindings;
import tm.fissionwarfare.key.KeyInputHandler;
import tm.fissionwarfare.render.RenderCompressor;
import tm.fissionwarfare.render.RenderControlPanel;
import tm.fissionwarfare.render.RenderExplosive;
import tm.fissionwarfare.render.RenderLaunchPad;
import tm.fissionwarfare.render.RenderMissile;
import tm.fissionwarfare.render.RenderTurret;
import tm.fissionwarfare.render.item.ItemRenderLaunchPad;
import tm.fissionwarfare.render.item.ItemRenderMissile;
import tm.fissionwarfare.render.item.ItemRenderShotgun;
import tm.fissionwarfare.render.item.ItemRenderTurret;
import tm.fissionwarfare.tileentity.machine.TileEntityControlPanel;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {
		
		ClientRegistry.registerKeyBinding(KeyBindings.teamGuiButton);
		ClientRegistry.registerKeyBinding(KeyBindings.reloadGunButton);
		
		MinecraftForge.EVENT_BUS.register(new TierTooltipEvent());
		MinecraftForge.EVENT_BUS.register(new GunRenderEvent());
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosive.class, new RenderExplosive());
		RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.shotgun, new ItemRenderShotgun());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.sentry_turret), new ItemRenderTurret());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.launch_pad), new ItemRenderLaunchPad());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.missile, new ItemRenderMissile());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurretSentry.class, new RenderTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaunchPad.class, new RenderLaunchPad());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityControlPanel.class, new RenderControlPanel());
		
		Reference.armorIDCompressor = addArmor("compressor");
	}
	
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);				
	}

	@Override
	public EntityPlayer getPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
}