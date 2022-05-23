package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.network.PacketHarvest;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class GuiHarvest extends GuiScreen {
	private EntityPlayer player;
	private TileEntityHarvest tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;
	
	private GuiCheckBox logOak;
	private GuiCheckBox logSpruce;
	private GuiCheckBox logBirch;
	private GuiCheckBox logJungle;
	private GuiCheckBox logAcacia;
	private GuiCheckBox logDark;

	private GuiCheckBox wheat;
	private GuiCheckBox carrot;
	private GuiCheckBox potato;

	private GuiCheckBox cactus;
	private GuiCheckBox pumpkin;
	private GuiCheckBox melon;
	private GuiCheckBox sugarcane;
	
	private GuiCheckBox cocoa;
	private GuiCheckBox mushroom;
	private GuiCheckBox netherwart;
	
	private GuiCheckBox replant;
	
	private World world;
	private int x;
	private int y;
	private int z;

	public GuiHarvest(EntityPlayer player, TileEntityHarvest entity, World world, int x, int y, int z) {
		this.player = player;
		this.tileEntity = entity;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
        this.buttonList.add(this.done = new GuiButtonExt(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, "Harvest"));
        this.buttonList.add(this.cancel = new GuiButtonExt(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        
        this.buttonList.add(this.logOak = new GuiCheckBox(2, this.width / 2 - 4 - 150, this.height / 4 - 20 + 12, "Oak Log", true));
        this.buttonList.add(this.logSpruce = new GuiCheckBox(3, this.width / 2 - 4 - 150, this.height / 4 - 10 + 12, "Spruce Log", true));
        this.buttonList.add(this.logBirch = new GuiCheckBox(4, this.width / 2 - 4 - 150, this.height / 4 + 0 + 12, "Birch Log", true));
        this.buttonList.add(this.logJungle = new GuiCheckBox(5, this.width / 2 - 4 - 150, this.height / 4 + 10 + 12, "Jungle Log", true));
        this.buttonList.add(this.logAcacia = new GuiCheckBox(6, this.width / 2 - 4 - 150, this.height / 4 + 20 + 12, "Acacia Log", true));
        this.buttonList.add(this.logDark = new GuiCheckBox(7, this.width / 2 - 4 - 150, this.height / 4 + 30 + 12, "Dark Oak Log", true));
        
        this.buttonList.add(this.wheat = new GuiCheckBox(8, this.width / 2 - 4 - 150, this.height / 4 + 50 + 12, "Wheat", true));
        this.buttonList.add(this.carrot = new GuiCheckBox(9, this.width / 2 - 4 - 150, this.height / 4 + 60 + 12, "Carrot", true));
        this.buttonList.add(this.potato = new GuiCheckBox(10, this.width / 2 - 4 - 150, this.height / 4 + 70 + 12, "Potato", true));
        
        this.buttonList.add(this.cactus = new GuiCheckBox(11, this.width / 2 + 4, this.height / 4 - 20 + 12, "Cactus", true));
        this.buttonList.add(this.pumpkin = new GuiCheckBox(12, this.width / 2 + 4, this.height / 4 - 10 + 12, "Pumpkin", true));
        this.buttonList.add(this.melon = new GuiCheckBox(13, this.width / 2 + 4, this.height / 4 + 0 + 12, "Melon", true));
        this.buttonList.add(this.sugarcane = new GuiCheckBox(14, this.width / 2 + 4, this.height / 4 + 10 + 12, "Sugar Cane", true));

        this.buttonList.add(this.cocoa = new GuiCheckBox(15, this.width / 2 + 4, this.height / 4 + 30 + 12, "Pumpkin", true));
        this.buttonList.add(this.mushroom = new GuiCheckBox(16, this.width / 2 + 4, this.height / 4 + 40 + 12, "Melon", true));
        this.buttonList.add(this.netherwart = new GuiCheckBox(17, this.width / 2 + 4, this.height / 4 + 50 + 12, "Sugar Cane", true));
		
        this.buttonList.add(this.replant = new GuiCheckBox(18, this.width / 2 + 4, this.height / 4 + 70 + 12, "Re-Plant", true));
	}

	@Override
	protected void actionPerformed(final GuiButton btn) {
		if (btn.enabled) {
			if (btn.id == done.id) {
				sendInfo();
				Keyboard.enableRepeatEvents(false);
				mc.displayGuiScreen(null);
			} else if (btn.id == cancel.id) {
				Keyboard.enableRepeatEvents(false);
				mc.displayGuiScreen(null);
			}
		}
	}

	@Override
	protected void keyTyped(final char par1, final int par2) {
		if (par2 == done.id) {
			actionPerformed(done);
		} else if (par2 == cancel.id) {
			actionPerformed(cancel);
		}
	}
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Harvest Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Select blocks to harvest:", this.width / 2 - 3 - 150, this.height / 4 - 30 + 12, 10526880);
        
		super.drawScreen(par1, par2, par3);
	}
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketHarvest(this.world, this.x, this.y, this.z, this.player.getDisplayName(), logOak.isChecked(), logSpruce.isChecked(), logBirch.isChecked(), logJungle.isChecked(), logAcacia.isChecked(), logDark.isChecked(), wheat.isChecked(), carrot.isChecked(), potato.isChecked(), cactus.isChecked(), pumpkin.isChecked(), melon.isChecked(), sugarcane.isChecked(), cocoa.isChecked(), mushroom.isChecked(), netherwart.isChecked(), replant.isChecked()));
	
		IBHelper.xp(world, player, Config.XP_AMOUNT);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, Strings.CREATE_HARVEST, Colors.a);
	}
	
}