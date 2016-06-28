package tm.fissionwarfare.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.entity.EntityBullet;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.GunData;

public class ServerPacketHandler implements IMessage {

	private String text;
	
	public ServerPacketHandler() {
		
	}

	public ServerPacketHandler(String text) {
		this.text = text;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		text = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, text);
	}

	public static class Handler implements IMessageHandler<ServerPacketHandler, IMessage> {

		@Override
		public IMessage onMessage(ServerPacketHandler message, MessageContext ctx) {
			
			String[] data = message.text.split("%");
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			
			if (data[0].equalsIgnoreCase("toggle.tileEntity")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				((TileEntityEnergyBase)player.worldObj.getTileEntity(x, y, z)).enabled = !((TileEntityEnergyBase)player.worldObj.getTileEntity(x, y, z)).enabled;
			}
			
			if (data[0].equalsIgnoreCase("set.coords")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				int index = Integer.parseInt(data[4]);
				int coord = Integer.parseInt(data[5]);
				
				TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)player.worldObj.getTileEntity(x, y, z);
				
				if (tileEntity.getControlPanel() != null) {
										
					if (index == 0) tileEntity.getControlPanel().targetX = coord;
					else tileEntity.getControlPanel().targetZ = coord;
				}
			}
			
			if (data[0].equalsIgnoreCase("toggle.launch")) {
				
				int x = Integer.parseInt(data[1]);
				int y = Integer.parseInt(data[2]);
				int z = Integer.parseInt(data[3]);
				
				EntityPlayer entityPlayer = player.worldObj.getPlayerEntityByName(data[4]);
				
				TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)player.worldObj.getTileEntity(x, y, z);
				tileEntity.toggleLaunch(entityPlayer);
			}
			
			//GUN START---------------------------------------------------------------------------------------------
			
			if (data[0].equalsIgnoreCase("reload")) {
				
				ItemStack is = player.getCurrentEquippedItem();
				
				GunData gunData = new GunData(is);
				
				gunData.reloading = true;
				gunData.flush();
			}
			
			if (data[0].equalsIgnoreCase("stop.use")) {
								
				ItemStack is = player.inventory.getStackInSlot(Integer.parseInt(data[1]));
				
				if (is != null) {
					
					GunData gunData = new GunData(is);
				
					gunData.usingTicks = 0;
					gunData.flush();
				}				
			}
			
			if (data[0].equalsIgnoreCase("use.gun")) {
				
				int shotsPerFire = Integer.parseInt(data[1]);
				int damage = Integer.parseInt(data[2]);
				int accuracy = Integer.parseInt(data[3]);
				float gravityVelocity = Float.parseFloat(data[4]);
				int hurtTime = Integer.parseInt(data[5]);
				
				ItemStack is = player.getCurrentEquippedItem();
				
				for (int i = 0; i < shotsPerFire; i++) {
					
				}
								
				if (is != null) {
					
					GunData gunData = new GunData(is);
				
					gunData.ammo--;
					gunData.flush();
				}	
			}
			
			return null;
		}
	}
}