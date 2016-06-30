package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretBase;

public abstract class RenderTurretBase extends RenderTileEntityBase {

	public RenderTurretBase(String name) {
		super(name + "_turret");
	}

	public abstract void renderBase();
	public abstract void renderTurnTable();
	public abstract void renderHead();
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z,	float meta) {
				
		TileEntityTurretBase turret = (TileEntityTurretBase)tileEntity;
			
		GL11.glPushMatrix();
		
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glScaled(0.627D, 0.627D, 0.627D);
			
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		renderBase();
		
		GL11.glRotated(turret.angle.yaw, 0, 1, 0);
		
		renderTurnTable();
		
		GL11.glTranslated(0, 1.7D, 0);
		GL11.glRotated(turret.angle.pitch, 0, 0, 1);
		GL11.glTranslated(0, -1.7D, 0);
		
		renderHead();
				
		GL11.glPopMatrix();
	}
}
