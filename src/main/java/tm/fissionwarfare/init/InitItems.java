package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tm.fissionwarfare.item.ItemBase;
import tm.fissionwarfare.item.ItemCompressor;
import tm.fissionwarfare.item.ItemGun;
import tm.fissionwarfare.item.ItemLocationLinker;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.item.ItemNailGun;
import tm.fissionwarfare.item.ItemTiered;
import tm.fissionwarfare.itemblock.ItemControlPanel;
import tm.fissionwarfare.itemblock.ItemSupportFrame;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.GunProfile;
import tm.fissionwarfare.util.ToolSet;

public class InitItems {

	//Main
	
	public static Item quartz_sand;
	public static Item quartz_chunk;
	public static Item limestone_chunk;	
	public static Item radioactive_chunk;
	public static Item uranium_pellet;	
	
	public static Item steel_ingot;
	public static Item steel_plate;
	
	public static Item poison_capsule;
	public static Item fiery_capsule;	
	public static Item electromagnetic_capsule;
	public static Item nuclear_capsule;
	
	public static Item cement;
	public static Item wet_cement;
	
	public static Item lightning_rod;
	public static Item iron_frame;
	public static Item circuit;
	public static Item fuel_canister;
	public static Item motor;	
	
	public static Item nail;
	public static Item cartridge;
	
	public static ToolSet steel;
	
	//Warfare
	
	public static Item control_panel;
	public static Item support_frame;
	
	public static Item nail_gun;
	public static Item nail_gun_magazine;
	public static Item compressor;
		
	public static Item location_linker;
	
	public static Item stock;
	public static Item handle;
	public static Item barrel;
	
	public static Item shotgun;
	public static Item bullet;
	
	public static Item missile;
	
	public static void init() {
		
		//Main
		
		quartz_sand = new ItemBase("quartz_sand");
		quartz_chunk = new ItemBase("quartz_chunk");
		limestone_chunk = new ItemBase("limestone_chunk");
		radioactive_chunk = new ItemBase("radioactive_chunk");
		uranium_pellet = new ItemBase("uranium_pellet");		
		
		poison_capsule = new ItemBase("poison_capsule");
		fiery_capsule = new ItemBase("fiery_capsule");		
		electromagnetic_capsule = new ItemBase("electromagnetic_capsule");
		nuclear_capsule = new ItemBase("nuclear_capsule");
		
		steel_ingot = new ItemBase("steel_ingot");
		steel_plate = new ItemBase("steel_plate");
		
		cement = new ItemBase("cement");
		wet_cement = new ItemBase("wet_cement");
		
		lightning_rod = new ItemBase("lightning_rod");
		iron_frame = new ItemBase("iron_frame");
		circuit = new ItemTiered("circuit", 3);
		fuel_canister = new ItemTiered("fuel_canister", 3);		
		motor = new ItemBase("motor");
		
		nail = new ItemBase("nail");		
		cartridge = new ItemBase("cartridge");
						
		steel = new ToolSet("steel", InitToolMaterials.toolMaterialSteel, steel_ingot, true);
		
		//Warfare
		
		control_panel = new ItemControlPanel();
		support_frame = new ItemSupportFrame();
		
		nail_gun = new ItemNailGun();
		nail_gun_magazine = new ItemBase("nail_gun_magazine", InitTabs.tabWarfare).setMaxStackSize(1).setMaxDamage(150);
		compressor = new ItemCompressor();	
		
		location_linker = new ItemLocationLinker();	
		
		stock = new ItemBase("stock");
		handle = new ItemBase("handle");
		barrel = new ItemBase("barrel");
		
		shotgun = new ItemGun("shotgun", new GunProfile(15, 4, 4, 10, 2, 30, 20, false, FWSound.shotgun_fire));
		bullet = new ItemBase("bullet", InitTabs.tabWarfare);
		
		missile = new ItemMissile();
			
		OreDictionary.registerOre("ingotSteel", steel_ingot);
	}
}
