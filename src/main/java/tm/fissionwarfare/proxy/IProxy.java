package tm.fissionwarfare.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public interface IProxy {

	public void registerRenders();
	
	public EntityPlayer getPlayer();
}
