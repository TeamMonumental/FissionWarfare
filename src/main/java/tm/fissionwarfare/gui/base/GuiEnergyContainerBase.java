package tm.fissionwarfare.gui.base;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public abstract class GuiEnergyContainerBase extends GuiContainerBase {

	public TileEntityEnergyBase tileEntity;
	public EntityPlayer player;
	
	public GuiEnergyContainerBase(Container container, EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(container, player, tileEntity);
		this.tileEntity = tileEntity;
		this.player = player;
	}	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor4f(1, 1, 1, 1);
		
		GuiUtil.drawStatusPanel(tileEntity.storage.getEnergyStored(), tileEntity.storage.getMaxEnergyStored(), tileEntity.progress, tileEntity.getMaxProgress(), getScreenX() - 19, getScreenY() + ((getGuiSize() / 4) - 34), mouseX, mouseY);
		
		if (tileEntity instanceof ISecurity) {
			
			SecurityProfile profile = ((ISecurity)tileEntity).getSecurityProfile();
						
			profile.cleanTeam(player.worldObj);
			
			if (profile.hasTeam()) {
				GuiUtil.drawBottomInfoBox(player.worldObj.getScoreboard().getTeam(profile.getTeamName()).getColorPrefix() + profile.getTeamName(), getScreenX() + getGuiSize() / 2, getScreenY() + getGuiSize(), 0xFFFFFF);
			}		
		}
	}		
}
