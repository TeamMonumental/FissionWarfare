package tm.fissionwarfare.missile;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.explosion.type.EnumExplosionType;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;

public class MissileUtil {

	public static ItemStack[] getIngrediants(ItemStack is) {
		
		ItemStack[] ingrediants = new ItemStack[4];
		
		MissileData data = MissileData.getDataFromItem(is);
		
		Block block = null;
				
		if (data.getExplosionType() == EnumExplosionType.BASIC) block = InitBlocks.basicExplosive;
		if (data.getExplosionType() == EnumExplosionType.EMP) block = InitBlocks.empExplosive;
		if (data.getExplosionType() == EnumExplosionType.PYRO) block = InitBlocks.pyroExplosive;
		if (data.getExplosionType() == EnumExplosionType.CHEMICAL) block = InitBlocks.chemicalExplosive;
		if (data.getExplosionType() == EnumExplosionType.ATOMIC) block = InitBlocks.atomicExplosive;
				
		if (data.getExplosionType() != null) ingrediants[0] = new ItemStack(block);
		
		if (data.getAccuracyTier() > 0) ingrediants[1] = new ItemStack(InitItems.circuit, 1, data.getAccuracyTier() - 1);
		if (data.getFuelTier() > 0) ingrediants[2] = new ItemStack(InitItems.fuel_canister, 1, data.getFuelTier() - 1);
		if (data.getArmorTier() > 0) ingrediants[3] = new ItemStack(InitItems.plating, 1, data.getArmorTier() - 1);
		
		return ingrediants;
	}
	
	public static int getEnergyCost(ItemStack chip, ItemStack fuel, ItemStack armor) {
		return ((chip.getItemDamage() + 1) * 20000) + ((fuel.getItemDamage() + 1) * 20000) + ((armor.getItemDamage() + 1) * 20000);
	}
}
