package tm.fissionwarfare.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import tm.fissionwarfare.Reference;

public class NetworkManager {

	public static SimpleNetworkWrapper network;
	
	public static void init() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	}
}
