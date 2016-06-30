package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretBase;
import tm.fissionwarfare.tileentity.machine.TileEntityTurretSentry;

public class RenderSentryTurret extends RenderTurretBase {
	
	public RenderSentryTurret() {
		super("sentry");
	}

	@Override
	public void renderBase() {
		model.renderOnly("Base_Cube_1", "Base_2_Cube_2");
	}

	@Override
	public void renderTurnTable() {
		model.renderOnly("Base_3_Cube_3", "Claw_1_Cube_4", "Claw_2_Cube_5");
	}

	@Override
	public void renderHead() {
		model.renderOnly("Head_Cube_6", "Antenna_Cube_7", "Antenna_Top_Cube_8", "Barrel_Cube_9", "Barrel_Tip_Cube_10");
	}
}