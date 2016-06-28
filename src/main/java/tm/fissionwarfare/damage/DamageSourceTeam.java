package tm.fissionwarfare.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSourceTeam extends DamageSource {

	private EntityPlayer player;
	private String text;
	
	public DamageSourceTeam(EntityPlayer player, String text) {
		super("team");
		this.player = player;
		this.text = text;
	}
	
	public IChatComponent func_151519_b(EntityLivingBase entity) {		   
		return new ChatComponentText(player.getDisplayName() + " was killed by " + text);
	}
}
