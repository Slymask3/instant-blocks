package com.slymask3.instantblocks.gui.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.network.PacketHarvest;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiHarvest extends GuiInstant {
	private final TileEntityHarvest tileEntity;

	private GuiCheckBox logOak, logSpruce, logBirch, logJungle, logAcacia, logDark;
	private GuiCheckBox wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart;
	private GuiCheckBox replant;

	public GuiHarvest(EntityPlayer player, TileEntityHarvest entity, World world, int x, int y, int z) {
		super(player,world,x,y,z);
		this.tileEntity = entity;
	}

	public void init() {
		this.buttonList.add(this.logOak = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 - 20 + 12, "Oak Log", true));
		this.buttonList.add(this.logSpruce = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 - 10 + 12, "Spruce Log", true));
		this.buttonList.add(this.logBirch = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 0 + 12, "Birch Log", true));
		this.buttonList.add(this.logJungle = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 10 + 12, "Jungle Log", true));
		this.buttonList.add(this.logAcacia = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 20 + 12, "Acacia Log", true));
		this.buttonList.add(this.logDark = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 30 + 12, "Dark Oak Log", true));

		this.buttonList.add(this.wheat = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 50 + 12, "Wheat", true));
		this.buttonList.add(this.carrot = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 60 + 12, "Carrot", true));
		this.buttonList.add(this.potato = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 70 + 12, "Potato", true));

		this.buttonList.add(this.cactus = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 - 20 + 12, "Cactus", true));
		this.buttonList.add(this.pumpkin = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 - 10 + 12, "Pumpkin", true));
		this.buttonList.add(this.melon = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 0 + 12, "Melon", true));
		this.buttonList.add(this.sugarcane = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 10 + 12, "Sugar Cane", true));

		this.buttonList.add(this.cocoa = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 30 + 12, "Pumpkin", true));
		this.buttonList.add(this.mushroom = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 40 + 12, "Melon", true));
		this.buttonList.add(this.netherwart = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 50 + 12, "Sugar Cane", true));

		this.buttonList.add(this.replant = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 70 + 12, "Re-Plant", true));
	}

	public void draw(int par1, int par2, float par3) {
		this.drawCenteredString(this.fontRendererObj, "Instant Harvest Block", this.width / 2, 20, 16777215);
		this.drawString(this.fontRendererObj, "Select blocks to harvest:", this.width / 2 - 3 - 150, this.height / 4 - 30 + 12, 10526880);
	}
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketHarvest(this.world, this.x, this.y, this.z, logOak.isChecked(), logSpruce.isChecked(), logBirch.isChecked(), logJungle.isChecked(), logAcacia.isChecked(), logDark.isChecked(), wheat.isChecked(), carrot.isChecked(), potato.isChecked(), cactus.isChecked(), pumpkin.isChecked(), melon.isChecked(), sugarcane.isChecked(), cocoa.isChecked(), mushroom.isChecked(), netherwart.isChecked(), replant.isChecked()));

		BlockInstant block = (BlockInstant)BuildHelper.getBlock(world,x,y,z);
		block.afterBuild(world,x,y,z,player);
	}
}