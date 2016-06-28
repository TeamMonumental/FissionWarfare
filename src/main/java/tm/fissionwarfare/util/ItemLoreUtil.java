package tm.fissionwarfare.util;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.EnumChatFormatting;

public class ItemLoreUtil {
	
	public static boolean addShiftLore(List list) {
				
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			return true;
		} 
		
		else {
			list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
			return false;
		}
	}
}
