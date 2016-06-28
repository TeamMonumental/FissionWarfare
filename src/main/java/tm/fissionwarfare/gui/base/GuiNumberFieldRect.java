package tm.fissionwarfare.gui.base;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;

public class GuiNumberFieldRect extends GuiTextFieldRect {

	public GuiNumberFieldRect(FontRenderer fontRenderer, int x, int y, int width, int stringLimit) {
		super(fontRenderer, x, y, width, stringLimit);
	}
	
	@Override
	public void writeText(String str) {
			
		String newString = "";
		
		for (int i = 0; i < str.length(); i++) {
			
			if (Character.isDigit(str.charAt(i)) || (getCursorPosition() == 0 && str.charAt(i) == '-')) {		
				newString += str.charAt(i);
			}
		}
			
		super.writeText(newString);
	}
	
	@Override
	public boolean textboxKeyTyped(char c, int i) {		
				
		if (Character.isDigit(c) || (getCursorPosition() == 0 && c == '-') ||
			Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) ||
			Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) ||
			Keyboard.isKeyDown(Keyboard.KEY_BACK) ||
			Keyboard.isKeyDown(Keyboard.KEY_DELETE) ||
			Keyboard.isKeyDown(Keyboard.KEY_LEFT) ||
			Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			return super.textboxKeyTyped(c, i); 
		
		return false;		
	}	
}
