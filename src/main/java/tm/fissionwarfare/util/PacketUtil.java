package tm.fissionwarfare.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.packet.ClientPacketHandler;

public class PacketUtil {
	
	public static void sendClientPacketsToGroup(World world, String packet, int x, int y, int z, int maxRange) {
		
		for (Object o : world.loadedEntityList) {
			
			if (o instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer)o;
				
				if (player.getDistance(x, y, z) <= maxRange) {
					
					FissionWarfare.network.sendTo(new ClientPacketHandler(packet), (EntityPlayerMP) player);
				}
			}
		}
	}
}
