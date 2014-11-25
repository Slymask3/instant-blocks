package com.slymask3.instantblocks.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.instant.BlockInstantStatue;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.network.PacketSkydive;
import com.slymask3.instantblocks.network.PacketStatue;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.IBHelper;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStatue extends GuiScreen {
	private static BuildHelper ibf = new BuildHelper();
	private static ConfigurationHandler config = new ConfigurationHandler();
	
	private EntityPlayer player;
	private TileEntityStatue tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;
	private GuiTextField input;
	
	private GuiCheckBox head;
	private GuiCheckBox body;
	private GuiCheckBox armLeft;
	private GuiCheckBox armRight;
	private GuiCheckBox legLeft;
	private GuiCheckBox legRight;
	
	private GuiCheckBox rgbMode;
	
	private World world;
	private int x;
	private int y;
	private int z;

	public GuiStatue(EntityPlayer player, TileEntityStatue entity, World world, int x, int y, int z) {
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
        this.buttonList.add(this.done = new GuiButtonExt(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, "Generate"));
        this.buttonList.add(this.cancel = new GuiButtonExt(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        
        this.buttonList.add(this.head = new GuiCheckBox(2, this.width / 2 - 4 - 150, this.height / 4 + 20 + 12, "Head", true));//BlockInstantStatue.head));
        this.buttonList.add(this.body = new GuiCheckBox(3, this.width / 2 - 4 - 150, this.height / 4 + 30 + 12, "Body", true));//BlockInstantStatue.body));
        this.buttonList.add(this.armLeft = new GuiCheckBox(4, this.width / 2 - 4 - 150, this.height / 4 + 40 + 12, "Arm (Left)", true));//BlockInstantStatue.armLeft));
        this.buttonList.add(this.armRight = new GuiCheckBox(5, this.width / 2 + 4, this.height / 4 + 40 + 12, "Arm (Right)", true));//BlockInstantStatue.armRight));
        this.buttonList.add(this.legLeft = new GuiCheckBox(6, this.width / 2 - 4 - 150, this.height / 4 + 50 + 12, "Leg (Left)", true));//BlockInstantStatue.legLeft));
        this.buttonList.add(this.legRight = new GuiCheckBox(7, this.width / 2 + 4, this.height / 4 + 50 + 12, "Leg (Right)", true));//BlockInstantStatue.legRight));
        
        this.buttonList.add(this.rgbMode = new GuiCheckBox(8, this.width / 2 - 4 - 150, this.height / 4 + 70 + 12, "RGB Mode", true));
		
		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
		this.input.setText("");
		this.input.setFocused(true);
		
		this.done.enabled = this.input.getText().trim().length() > 3;
	}

	@Override
	public void updateScreen() {
		this.input.updateCursorCounter();
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
		
		if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".indexOf(par1) >= 0) || (par2 == 14)) {
			input.textboxKeyTyped(par1, par2);
		} else if (par2 == done.id) {
			actionPerformed(done);
		} else if (par2 == cancel.id) {
			actionPerformed(cancel);
		}

        this.done.enabled = this.input.getText().trim().length() > 3;
	}

	@Override
	protected void mouseClicked(int x, int y, int click) {
        super.mouseClicked(x, y, click);
        this.input.mouseClicked(x, y, click);
    }
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Statue Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Enter a Minecraft Username:", this.width / 2 - 4 - 150, 37, 10526880);
        this.drawString(this.fontRendererObj, "Select parts to generate:", this.width / 2 - 3 - 150, this.height / 4 + 8 + 12, 10526880);
        this.drawString(this.fontRendererObj, "Unchecking RGB Mode will use vanilla minecraft wool colors.", this.width / 2 - 3 - 150, this.height / 4 + 83 + 12, 10526880);
        
		this.input.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketStatue(this.world, this.x, this.y, this.z, this.player.getDisplayName(), input.getText(), head.isChecked(), body.isChecked(), armLeft.isChecked(), armRight.isChecked(), legLeft.isChecked(), legRight.isChecked(), rgbMode.isChecked()));
		//InstantBlocks.packetPipeline.sendToAll(new PacketInstantStatue(this.world, this.x, this.y, this.z, this.player.getDisplayName(), input.getText(), head.isChecked(), body.isChecked(), armLeft.isChecked(), armRight.isChecked(), legLeft.isChecked(), legRight.isChecked(), rgbMode.isChecked()));
		//int c = 0xFF0000;
		//InstantBlocks.packetPipeline.sendToServer(new PacketSkydive(this.world, this.x, this.y, this.z, this.player.getDisplayName(), c,c,c,c,c,c,c,c,c,c,c));
		
		//LogHelper.info(this.player.getDisplayName());
		
		//config.rgbMode = rgbMode.isChecked();
		
		IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, "\u00a7aInstant Statue created of the player '" + input.getText() + "'.", Colors.a);
		
		
		
		//LogHelper.info(input.getText());
	}
	
}