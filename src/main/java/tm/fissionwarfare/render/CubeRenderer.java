package tm.fissionwarfare.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class CubeRenderer {

	public static void render(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon iicon) {
		
		Tessellator tes = Tessellator.instance;
			
		float minU = iicon.getMinU();
		float maxU = iicon.getMaxU();
		float minV = iicon.getMinV();
		float maxV = iicon.getMaxV();
		
		float spaceU = maxU - minU;
		float spaceV = maxV - minV;
		
		float scale = (spaceV / (maxY - minY)) * maxY;
		
		tes.startDrawingQuads();
		
		//Top
		tes.addVertexWithUV(minX, maxY, maxZ, minU, minV);		
		tes.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
		tes.addVertexWithUV(maxX, maxY, minZ, maxU, maxV);
		tes.addVertexWithUV(minX, maxY, minZ, minU, maxV);
		//Bottom
		tes.addVertexWithUV(minX, minY, minZ, minU, minV);
		tes.addVertexWithUV(maxX, minY, minZ, maxU, minV);
		tes.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tes.addVertexWithUV(minX, minY, maxZ, minU, maxV);
		//North
		tes.addVertexWithUV(minX, maxY, minZ, minU, minV);
		tes.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
		tes.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
		tes.addVertexWithUV(minX, minY, minZ, minU, maxV);
		//SOUTH
		tes.addVertexWithUV(maxX, maxY, maxZ, minU, minV);
		tes.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
		tes.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
		tes.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
		//EAST
		tes.addVertexWithUV(maxX, maxY, minZ, minU, minV);
		tes.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
		tes.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tes.addVertexWithUV(maxX, minY, minZ, minU, maxV);
		//WEST
		tes.addVertexWithUV(minX, maxY, maxZ, minU, minV);
		tes.addVertexWithUV(minX, maxY, minZ, maxU, minV);
		tes.addVertexWithUV(minX, minY, minZ, maxU, maxV);
		tes.addVertexWithUV(minX, minY, maxZ, minU, maxV);
		
		tes.draw();
	}
}
