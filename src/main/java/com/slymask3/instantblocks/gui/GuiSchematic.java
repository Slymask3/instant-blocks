package com.slymask3.instantblocks.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.network.PacketSchematic;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.IBHelper;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSchematic extends GuiScreen {
	private static BuildHelper ibf = new BuildHelper();
	private static ConfigurationHandler config = new ConfigurationHandler();
	
	private EntityPlayer player;
	private TileEntitySchematic tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;
	private GuiTextField input;
	
	private GuiCheckBox center;
	
	private World world;
	private int x;
	private int y;
	private int z;

	public GuiSchematic(EntityPlayer player, TileEntitySchematic entity, World world, int x, int y, int z) {
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
        
        this.buttonList.add(this.center = new GuiCheckBox(2, this.width / 2 - 4 - 150, this.height / 4 + 20 + 12, "Center", false));
		
		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
		this.input.setText("");
		this.input.setFocused(true);
		
		this.done.enabled = this.input.getText().trim().length() > 0;
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
		
		if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-`~!@#$%^&*()+='/,{}[]<>?| ".indexOf(par1) >= 0) || (par2 == 14)) {
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
        this.drawCenteredString(this.fontRendererObj, "Instant Schematic Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Enter a Schematic File Name: (Put your schematic files into '.minecraft/schematics/'))", this.width / 2 - 4 - 150, 37, 10526880);
        this.drawString(this.fontRendererObj, "Options:", this.width / 2 - 3 - 150, this.height / 4 + 8 + 12, 10526880);
        this.drawString(this.fontRendererObj, "Schematic File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 3 - 150, this.height / 4 + 83 + 12, 10526880);
        
		this.input.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketSchematic(this.world, this.x, this.y, this.z, this.player.getDisplayName(), input.getText(), center.isChecked()));
		
		IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, "\u00a7aInstant Schematic created from the file '" + input.getText() + "'.", Colors.a);
	}
	
}