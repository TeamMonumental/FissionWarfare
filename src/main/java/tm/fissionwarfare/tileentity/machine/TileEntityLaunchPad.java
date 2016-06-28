package tm.fissionwarfare.tileentity.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cofh.lib.audio.ISoundSource;
import cofh.lib.audio.SoundBase;
import cofh.lib.audio.SoundTile;
import cofh.lib.util.helpers.SoundHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.block.BlockSupportFrame;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.entity.EntityMissileFlameFX;
import tm.fissionwarfare.entity.EntityMissileSmokeFX;
import tm.fissionwarfare.gui.GuiControlPanel;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerEnergyBase;
import tm.fissionwarfare.itemblock.ItemSupportFrame;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.sounds.MissileSound;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.EffectUtil;
import tm.fissionwarfare.util.PacketUtil;
import tm.fissionwarfare.util.UnitChatMessage;
import tm.fissionwarfare.util.math.Location;

public class TileEntityLaunchPad extends TileEntityEnergyBase implements ISecurity, ISoundSource {
	
	private Random rand = new Random();
	
	public SecurityProfile profile = new SecurityProfile();
	
	public static final int ENERGY_COST = 10000;

	public ItemStack missile;

	public boolean launching;
		
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!worldObj.isRemote) {
			checkForFullFrame();
		}

		if (getControlPanel() == null || getSupportFrame() == null || missile == null || !isDistanceInRange() || !canExtractEnergy(ENERGY_COST) || !isPathClear()) {
			launching = false;
		}

		if (launching) {
			
			progress++;	
			
			if (worldObj.isRemote) {
				doEffects();
			}
		}

		else progress = 0;

		if (isDoneAndReset()) {

			launching = false;
			storage.extractEnergy(ENERGY_COST, false);

			if (!worldObj.isRemote) {

				int distances[] = { 0, 5, 20, 50 };

				int percentage = rand.nextInt(100 - ((getMissileData().getAccuracyTier() - 1) * 20));

				int index;

				if (percentage < 20) index = 1;
				else if (percentage < 80) index = 2;
				else index = 3;

				int x = MathHelper.getRandomIntegerInRange(rand, distances[index - 1], distances[index]);
				int z = MathHelper.getRandomIntegerInRange(rand, distances[index - 1], distances[index]);

				if (rand.nextInt(2) == 0) x = 0 - x;
				if (rand.nextInt(2) == 0) z = 0 - z;				

				worldObj.spawnEntityInWorld(new EntityMissile(worldObj, xCoord, yCoord + 0.6D, zCoord, getControlPanel().targetX + x, getControlPanel().targetZ + z, missile));
			}

			missile = null;
			update();
		}
	}

	public void toggleLaunch(EntityPlayer player) {

		if (launching) launching = false;		
		else startLaunch(player);
		
		update();
	}

	public void startLaunch(EntityPlayer player) {
		
		if (!launching && getControlPanel() != null && getSupportFrame() != null && missile != null && isDistanceInRange() && canExtractEnergy(ENERGY_COST) && isPathClear()) {			
			
			launching = true;
			
			PacketUtil.sendClientPacketsToGroup(worldObj, "launch%" + xCoord + "%" + yCoord + "%" + zCoord, xCoord, yCoord, zCoord, 50);			
			PacketUtil.sendClientPacketsToGroup(worldObj, "playtilesound%" + xCoord + "%" + yCoord + "%" + zCoord, xCoord, yCoord, zCoord, 50);
		}

		else printErrorMessage(player);
	}
	
	public MissileData getMissileData() {
		return MissileData.getDataFromItem(missile);
	}
	
	private boolean isDistanceInRange() {		
		return getDistanceFromCoords() <= getMissileData().getMaxBlockDistance();		
	}
	
	private double getDistanceFromCoords() {
		return getLocation().getDistance(new Location(worldObj, getControlPanel().targetX, yCoord, getControlPanel().targetZ));
	}

	private boolean isPathClear() {

		for (int x = -1; x < 2; x++) {

			for (int z = -1; z < 2; z++) {

				if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord, zCoord + z)) return false;
			}
		}

		return true;
	}

	public TileEntityControlPanel getControlPanel() {

		Location loc = getLocation().add(getBlockMetadata(), false);

		if (loc.hasTileEntity() && loc.getTileEntity() instanceof TileEntityControlPanel) {
			return (TileEntityControlPanel) loc.getTileEntity();
		}

		return null;
	}

	public BlockSupportFrame getSupportFrame() {

		Location loc = getLocation().add(getBlockMetadata(), true);

		if (loc.getBlock() instanceof BlockSupportFrame) {
			return (BlockSupportFrame) loc.getBlock();
		}

		return null;
	}

	public List<Location> getFrame() {

		List<Location> locs = new ArrayList<Location>();

		Location loc = getLocation().add(getBlockMetadata(), true);

		for (int xzOffset = -1; xzOffset < 2; xzOffset++) {

			for (int yOffset = 0; yOffset < 7; yOffset++) {

				locs.add(new Location(worldObj, loc.x + (ItemSupportFrame.shouldRotate(getBlockMetadata()) ? xzOffset : 0), loc.y + yOffset, loc.z + (!ItemSupportFrame.shouldRotate(getBlockMetadata()) ? xzOffset : 0)));
			}
		}

		return locs;
	}

	public void checkForFullFrame() {

		boolean hasFrame = false;
		boolean hasAir = false;

		for (Location loc : getFrame()) {

			if (loc.getBlock() instanceof BlockSupportFrame) {
				hasAir = true;
			}

			else hasFrame = true;
		}

		if (hasFrame && hasAir) {
			destroyFrame();
		}
	}

	public void destroyFrame() {

		worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord + 0.5F, zCoord, new ItemStack(InitItems.support_frame)));

		for (Location loc : getFrame()) {

			loc.setBlockToAir();
		}
	}

	private void printErrorMessage(EntityPlayer player) {

		UnitChatMessage message = new UnitChatMessage("Launch Pad", player);

		if (getSupportFrame() == null) message.printMessage(EnumChatFormatting.RED, "No Support Frame connected!");
		if (getControlPanel() == null) message.printMessage(EnumChatFormatting.RED, "No Control Panel connected!");

		else {

			if (missile == null) message.printMessage(EnumChatFormatting.RED, "No missile in this unit!");
			else if (!isDistanceInRange()) message.printMessage(EnumChatFormatting.RED, "Distance between target position is too great: " + (int)getDistanceFromCoords());
			
			if (!isPathClear()) message.printMessage(EnumChatFormatting.RED, "The path is not cleared! (A 3x3 wide square of blocks need to see the sky)");
		}

		if (!canExtractEnergy(ENERGY_COST)) message.printMessage(EnumChatFormatting.RED, "Not enough energy! (" + ENERGY_COST + " RF required)");

	}
	
	@SideOnly(Side.CLIENT)
	private void doEffects() {
		
		for (int i = 0; i < 1 + (progress / 60); i++) {

			double randX = MathHelper.getRandomDoubleInRange(rand, -0.25D, 0.25D);
			double randY = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0);
			double randZ = MathHelper.getRandomDoubleInRange(rand, -0.25D, 0.25D);

			double x = xCoord + 0.5D;
			double y = yCoord + 0.77D;
			double z = zCoord + 0.5D;

			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, x, y, z, -0.3D, randY, randX));
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, x, y, z, 0.3D, randY, randX));
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, x, y, z, randZ, randY, 0.3D));
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, x, y, z, randZ, randY, -0.3D));

			if (i % 4 == 3) {

				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, x, y, z, -0.3D, randY, randX));
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, x, y, z, 0.3D, randY, randX));
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, x, y, z, randZ, randY, 0.3D));
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, x, y, z, randZ, randY, -0.3D));
			}
		}		
	}

	@Override
	public int getMaxEnergy() {
		return 100000;
	}

	@Override
	public int getMaxReceive() {
		return 10000;
	}

	@Override
	public int getMaxExtract() {
		return 10000;
	}

	@Override
	public int getMaxProgress() {
		return (20 * 16) + 10;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir == ForgeDirection.DOWN;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerEnergyBase(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiControlPanel(getTileContainer(player), player, this);
	}

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1F, yCoord, zCoord - 1F, xCoord + 2F, yCoord + 7F, zCoord + 2F);
	}

	@Override
	public void readSyncNBT(NBTTagCompound nbt) {

		super.readSyncNBT(nbt);

		NBTTagCompound tempTag = nbt.getCompoundTag("missile");

		if (!tempTag.getBoolean("null")) {
			missile = ItemStack.loadItemStackFromNBT(tempTag);
		}

		else missile = null;

		profile.readFromNBT(nbt);

		launching = nbt.getBoolean("launching");
	}

	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {

		super.writeSyncNBT(nbt);

		NBTTagCompound tempTag = new NBTTagCompound();

		if (missile != null) {

			missile.writeToNBT(tempTag);
			tempTag.setBoolean("null", false);
		}

		else tempTag.setBoolean("null", true);

		nbt.setTag("missile", tempTag);

		profile.writeToNBT(nbt);

		nbt.setBoolean("launching", launching);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ISound getSound() {
		return new SoundTile(this, Reference.MOD_ID + ":launch", 4, 1, true, 0, xCoord, yCoord, zCoord).setFadeOut(30);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldPlaySound() {
		return launching;
	}
}
