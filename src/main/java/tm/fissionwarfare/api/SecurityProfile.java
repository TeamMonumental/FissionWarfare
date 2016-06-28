package tm.fissionwarfare.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.World;

public class SecurityProfile {

	private String teamName;
		
	public void cleanTeam(World world) {
		
		if (world.getScoreboard().getTeam(teamName) == null) {
			teamName = null;
		}
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(Team team) {
		teamName = team.getRegisteredName();
	}
	
	public boolean hasTeam() {
		return teamName != null;
	}
	
	public boolean isSameTeam(EntityPlayer player) {
		
		if (!hasTeam()) {
			return true;
		}
		
		if (player.getTeam() == null && hasTeam()) {
			return false;
		}
		
		Team team = player.worldObj.getScoreboard().getTeam(teamName);
		
		return team.isSameTeam(player.getTeam());
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("teamName")) {
			teamName = nbt.getString("teamName");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		if (teamName != null) {
			nbt.setString("teamName", teamName);
		}
	}
}
