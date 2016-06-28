package tm.fissionwarfare.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import tm.fissionwarfare.Reference;

public class ChatUtil {

	public static void printFWMessage(EnumChatFormatting format, String message, EntityPlayer... players) {
		
		UnitChatMessage unitMessage = new UnitChatMessage(Reference.MOD_NAME, players);		
		unitMessage.printMessage(format, message);
	}
}
