package tm.fissionwarfare.render;

public class RenderMissileTurret extends RenderTurretBase {

	public RenderMissileTurret() {
		super("missile");
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
		model.renderOnly("Head_Cube_6", "Antenna_Cube_7", "Antenna_Top_Cube_8", "Antenna_2_Cube_9", "Antenna_Top_2_Cube_10", "Bar_1_Cube_11", "Bar_2_Cube_12", "Holder_Cube_13", "Barrel_Cube_14");
	}
}
