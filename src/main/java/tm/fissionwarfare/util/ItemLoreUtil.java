package tm.fissionwarfare.util;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.EnumChatFormatting;

public class ItemLoreUtil {
	
	public static boolean addShiftLore(List list) {
		
		list.add("");
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			return true;
		} 
		
		else {
						
			list.add("Hold " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
			return false;
		}
	}
	
	public static boolean addCtrlLore(List list) {
		
		list.add("");
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
			return true;
		} 
		
		else {	
						
			list.add("Hold " + EnumChatFormatting.AQUA + "CTRL" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for storage info");
			return false;
		}
	}
}
