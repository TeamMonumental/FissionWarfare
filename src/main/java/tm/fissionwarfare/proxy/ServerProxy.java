package tm.fissionwarfare.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ServerProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		
	}

	@Override
	public EntityPlayer getPlayer() {
		return null;
	}
}
