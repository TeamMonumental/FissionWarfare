package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class RenderTurret extends RenderTileEntityBase {
	
	public RenderTurret() {
		super("turret");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z,	float meta) {
				
		TileEntityTurretSentry turret = (TileEntityTurretSentry)tileEntity;
			
		GL11.glPushMatrix();
		
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glScaled(0.627D, 0.627D, 0.627D);
			
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderOnly("Base_Cube_1", "Base_2_Cube_2");
		
		GL11.glRotated(turret.angle.yaw, 0, 1, 0);
		model.renderOnly("Base_3_Cube_3", "Claw_1_Cube_4", "Claw_2_Cube_5");
		
		GL11.glTranslated(0, 1.7D, 0);
		GL11.glRotated(turret.angle.pitch, 0, 0, 1);
		GL11.glTranslated(0, -1.7D, 0);
		
		model.renderOnly("Head_Cube_6", "Antenna_Cube_7", "Antenna_Top_Cube_8", "Barrel_Cube_9", "Barrel_Tip_Cube_10");
				
		GL11.glPopMatrix();
	}
}