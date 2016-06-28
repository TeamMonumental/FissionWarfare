package tm.fissionwarfare.gui.base;

import java.text.NumberFormat;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiTextFieldRect extends GuiTextField {

	public GuiTextFieldRect(FontRenderer fontRenderer, int x, int y, int width, int stringLimit) {
		super(fontRenderer, x + 1, y + 2, width, 12);
		setTextColor(-1);
		setDisabledTextColour(-1);
		setEnableBackgroundDrawing(false);
		setMaxStringLength(stringLimit);
	}
}