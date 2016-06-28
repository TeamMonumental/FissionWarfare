package tm.fissionwarfare.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import tm.fissionwarfare.explosion.type.EnumExplosionType;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.util.ItemLoreUtil;

public class ItemMissile extends ItemBase {

	public ItemMissile() {
		super("missile", InitTabs.tabWarfare, false);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		MissileData missileData = MissileData.getDataFromItem(is);
		
		if (missileData.getExplosionType() != null) list.add(EnumChatFormatting.GOLD + "Type: " + EnumChatFormatting.AQUA + missileData.getExplosionType().getName());
		list.add(EnumChatFormatting.GOLD + "Accuracy Tier: " + EnumChatFormatting.AQUA + missileData.getAccuracyTier());
		list.add(EnumChatFormatting.GOLD + "Fuel Tier: " + EnumChatFormatting.AQUA + missileData.getFuelTier() + " (Max Distance: " + missileData.getMaxBlockDistance() + ")");		
		list.add("");
		
		if (ItemLoreUtil.addShiftLore(list)) {
			
			list.add("Right-click : Places it on a Launch Pad");			
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
			
		for (EnumExplosionType type : EnumExplosionType.values()) {
			
			for (int i = 1; i < 4; i++) {
				
				ItemStack stack = new ItemStack(this);
				MissileData data = MissileData.getDataFromItem(stack);
			
				data.setAccuracyTier(i);
				data.setFuelTier(i);			
				data.setExplosionType(type);
			
				list.add(MissileData.setDataToItem(stack, data));				
			}				
		}	
	}
}
