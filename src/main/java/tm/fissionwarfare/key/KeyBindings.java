package tm.fissionwarfare.key;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import tm.fissionwarfare.Reference;

public class KeyBindings {

	public static KeyBinding teamGuiButton = new KeyBinding("Team Gui", Keyboard.KEY_G, Reference.MOD_NAME);
	public static KeyBinding reloadGunButton = new KeyBinding("Reload Gun", Keyboard.KEY_R, Reference.MOD_NAME);
}
