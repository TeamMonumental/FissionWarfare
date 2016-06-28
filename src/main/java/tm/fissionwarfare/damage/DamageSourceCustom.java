package tm.fissionwarfare.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSourceCustom extends DamageSource {

	private String text;
	
	public DamageSourceCustom(String text) {
		super("team");
		this.text = text;
	}
	
	public IChatComponent func_151519_b(EntityLivingBase entity) {		   
		return new ChatComponentText(text);
	}
}