package tm.fissionwarfare.nei;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.gui.base.GuiUtil;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.missile.MissileUtil;
import tm.fissionwarfare.tileentity.machine.TileEntityMissileFactory;
import tm.fissionwarfare.util.math.MathUtil;

public class MissileFactoryRecipeHandler extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return "Missile Factory";
	}

	@Override
	public String getGuiTexture() {
		return Reference.MOD_ID + ":textures/gui/nei/missile_factory.png";
	}

	@Override
	public String getOverlayIdentifier() {
		return "Missile Factory";
	}
	
	@Override
	public void drawBackground(int recipe) {
		super.drawBackground(recipe);
		
		CachedMissileFactoryRecipe curRecipe = (CachedMissileFactoryRecipe) arecipes.get(recipe);
		
		GuiDraw.changeTexture(Reference.MOD_ID + ":textures/gui/gui_textures.png");
		
		int scale = MathUtil.scaleInt(MissileUtil.getEnergyCost(curRecipe.chip.item, curRecipe.fuel.item, curRecipe.armor.item), 200000, 34);
		
		GuiDraw.drawTexturedModalRect(22, 48 - scale + 1, 19, 40, 6, scale);
	}
	
	@Override
	public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe) {
				
		CachedMissileFactoryRecipe curRecipe = (CachedMissileFactoryRecipe) arecipes.get(recipe);
		
        if (GuiContainerManager.shouldShowTooltip(gui) && currenttip.size() == 0) {
        	
            Point offset = gui.getRecipePosition(recipe);
            Point pos = GuiDraw.getMousePosition();
            Point relMouse = new Point(pos.x - (gui.width - 176) / 2 - offset.x, pos.y - (gui.height - 166) / 2 - offset.y);
            
            Rectangle energyRect = new Rectangle(27 - 5, 26 - 11, 6, 34);
            
            if (energyRect.contains(relMouse)) {

            	currenttip.add("Cost: " + MissileUtil.getEnergyCost(curRecipe.chip.item, curRecipe.fuel.item, curRecipe.armor.item) + " RF");
                return currenttip;
            }
        }
		
		return super.handleTooltip(gui, currenttip, recipe);
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		
		if (result.getItem() instanceof ItemMissile) {
			
			ItemStack[] ingrediants = MissileUtil.getIngrediants(result);
			
			arecipes.add(new CachedMissileFactoryRecipe(ingrediants[0], ingrediants[1], ingrediants[2], ingrediants[3], result));
		}
	}
	
	public class CachedMissileFactoryRecipe extends CachedRecipe {

		private PositionedStack tnt;
		private PositionedStack chip;
		private PositionedStack fuel;
		private PositionedStack armor;
		
		private PositionedStack output;
				
		public CachedMissileFactoryRecipe(ItemStack tnt, ItemStack chip, ItemStack fuel, ItemStack armor, ItemStack output) {
			
			int xOffset = 5;
			int yOffset = 11;
			
			this.tnt = new PositionedStack(tnt, 48 - xOffset, 17 - yOffset);
			this.chip = new PositionedStack(chip, 48 - xOffset, 35 - yOffset);
			this.fuel = new PositionedStack(fuel, 48 - xOffset, 53 - yOffset);
			this.armor = new PositionedStack(armor, 80 - xOffset, 35 - yOffset);
			
			this.output = new PositionedStack(output, 112 - xOffset, 35 - yOffset);
		}
		
		@Override
		public PositionedStack getResult() {
			return output;
		}
		
		@Override
		public List<PositionedStack> getIngredients() {
			
			List<PositionedStack> list = new ArrayList<PositionedStack>();
			
			list.add(tnt);
			list.add(chip);
			list.add(fuel);
			list.add(armor);
			
			return list;
		}
	}	
}
