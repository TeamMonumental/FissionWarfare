package tm.fissionwarfare.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.ITieredItem;

public class ItemTiered extends ItemBase implements ITieredItem {

	public int maxTier;
	private IIcon[] textures;
		
	public ItemTiered(String imageName, int maxTier) {
		super(imageName);		
		this.maxTier = maxTier;		
		textures = new IIcon[maxTier];
		this.hasSubtypes = true;
	}

	@Override
	public int getTier(ItemStack stack) {
		return stack.getItemDamage() + 1;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		
		for (int i = 0; i < maxTier; i++) {
			list.add(new ItemStack(item, 1, i));
		}		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return "item." + imageName + "_" + is.getItemDamage();
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta >= maxTier) return textures[maxTier - 1];
		return textures[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconReg) {
		
		for (int i = 0; i < maxTier; i++) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":" + imageName + "_" + i);
		}
	}
}
