package com.slymask3.instantblocks.gui;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public abstract class GuiInstant extends GuiScreen {
	protected final World world;
	protected final int x, y, z;
	protected final EntityPlayer player;

	protected GuiButtonExt done, cancel;
	private String doneText;

	private int id;

	public GuiInstant(EntityPlayer player, World world, int x, int y, int z) {
		this.player = player;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.doneText = "Generate";
		this.id = 100;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public int getID() {
		return ++this.id;
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(this.done = new GuiButtonExt(getID(), this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, doneText));
		this.buttonList.add(this.cancel = new GuiButtonExt(getID(), this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
		this.init();
	}

	@Override
	protected void actionPerformed(final GuiButton btn) {
		if(btn.enabled) {
			if(btn.id == done.id) {
				sendInfo();
				Keyboard.enableRepeatEvents(false);
				mc.displayGuiScreen(null);
			} else if(btn.id == cancel.id) {
				Keyboard.enableRepeatEvents(false);
				mc.displayGuiScreen(null);
			}
			click(btn);
		}
	}

	@Override
	protected void keyTyped(final char character, final int key) {
		if(key == 28 || key == 156) {
			actionPerformed(done);
		} else if(key == 1) {
			actionPerformed(cancel);
		}
		typed(character,key);
	}
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.draw(par1,par2,par3);
		super.drawScreen(par1,par2,par3);
	}

	public FontRenderer getFontRenderer() {
		return fontRendererObj;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void init() {}
	public void draw(int par1, int par2, float par3) {}
	public void click(GuiButton btn) {}
	public void typed(char character, int key) {}
	public void sendInfo() {}
}