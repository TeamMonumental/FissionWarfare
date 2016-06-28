package tm.fissionwarfare.util;

import java.util.Collection;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class UnitChatMessage {

	private String unitName;
	private EntityPlayer[] players;
	
	public UnitChatMessage(String unitName, EntityPlayer... players) {
		this.unitName = unitName;
		this.players = players;		
	}
	
	public UnitChatMessage(String unitName, World world, String teamName) {
		this.unitName = unitName;
		
		players = (EntityPlayer[]) world.getScoreboard().getTeam(teamName).getMembershipCollection().toArray();
	}
	
	public void printMessage(EnumChatFormatting format, String message) {
						
		String newMessage = "";
		String[] m = message.split(" ");
		
		for(String t : m){
			
			newMessage += format;
			newMessage += t;
			newMessage += " ";
		}		
		
		for (EntityPlayer player : players) {
			player.addChatComponentMessage(new ChatComponentText(getUnitName() + (format + newMessage)));
		}		
	}
	
	public void printSpace() {
		
		for (EntityPlayer player : players) {
			
			player.addChatComponentMessage(new ChatComponentText(""));
		}
	}
	
	private int getMessageLength(String message) {
		return getUnitName().length() + message.length();
	}
	
	private String getUnitName() {
		return "[" + unitName + "] ";
	}
}
