package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.network.PacketSkydive;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiSkydive extends GuiScreen {
	private final World world;
	private final int x, y, z;
	private final EntityPlayer player;
	private final TileEntitySkydive tileEntity;
	
    private GuiButtonExt done, cancel, random;
	private GuiTextField[] color = new GuiTextField[11];
	private GuiCheckBox tp;
	private GuiTextField radius;

	private final int cutoff = 6;

	public GuiSkydive(EntityPlayer player, TileEntitySkydive entity, World world, int x, int y, int z) {
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
        this.buttonList.add(this.done = new GuiButtonExt(101, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, "Generate"));
        this.buttonList.add(this.cancel = new GuiButtonExt(102, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        this.buttonList.add(this.random = new GuiButtonExt(103, this.width / 2 + 4, this.height / 4 + 98 + 12, 150, 20, "Randomize"));

		this.buttonList.add(this.tp = new GuiCheckBox(104, this.width / 2 + 4, this.height / 4 + 83 + 12, "Teleport to top", true));
		//this.buttonList.add(this.actualRainbow = new GuiCheckBox(105, this.width / 2 - 4 - 150, this.height / 4 + 83 + 12, "Use actual rainbow", false));

		this.radius = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 100 - 12, this.height / 4 + 100 + 12, 110, 16);
		this.radius.setText(String.valueOf(Config.SKYDIVE_RADIUS));

        for(int i=0; i<cutoff; i++) {
        	this.color[i] = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 100 + 6, 45 + (18*i), 60, 14);
			this.color[i].setText(tileEntity.color[i]);
        }
        for(int i=cutoff; i<=10; i++) {
        	this.color[i] = new GuiTextField(this.fontRendererObj, this.width / 2 + 4 + 50, 45 + (18*(i-cutoff)), 60, 14);
			this.color[i].setText(tileEntity.color[i]);
        }
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
			} else if(btn.id == random.id) {
				setRandom();
			}
//			} else if(btn.id == actualRainbow.id) {
//				if(actualRainbow.isChecked()) {
//					this.random.enabled = false;
//					for(int i=0; i<this.color.length; i++) {
//						this.color[i].setEnabled(false);
//					}
//				} else {
//					this.random.enabled = true;
//					for(int i=0; i<this.color.length; i++) {
//						this.color[i].setEnabled(true);
//					}
//				}
//			}
		}
	}
	
	@Override
	public void updateScreen() {
		for(int i=0; i<11; i++) {
			this.color[i].updateCursorCounter();
		}
		this.radius.updateCursorCounter();
	}
	
	@Override
	protected void keyTyped(final char par1, final int par2) {
		if(("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ".indexOf(par1) >= 0) || (par2 == 14)) {
			for(int i=0; i<11; i++) {
				color[i].textboxKeyTyped(par1, par2);
				tileEntity.setColorCode(i, color[i].getText());
				tileEntity.setColor(i, color[i].getText());
	        }
		}
		if(("0123456789".indexOf(par1) >= 0) || (par2 == 14)) {
			radius.textboxKeyTyped(par1, par2);
		}
		
		if(par2 == done.id) {
			actionPerformed(done);
		} else if(par2 == cancel.id) {
			actionPerformed(cancel);
		} else if(par2 == random.id) {
			actionPerformed(random);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int click) {
        super.mouseClicked(x, y, click);
        for(int i=0; i<11; i++) {
        	this.color[i].mouseClicked(x, y, click);
        }
		this.radius.mouseClicked(x, y, click);
    }
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Skydive Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Enter colors by name or hexadecimal value.", this.width / 2 - 4 - 150, 33, 10526880); //aarrggbb
		this.drawString(this.fontRendererObj, "Radius:", this.width / 2 - 4 - 150, this.height / 4 + 104 + 12,10526880);
        
        for(int i=0; i<cutoff; i++) {
			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 - 4 - 150, 48 + (18*i), tileEntity.colorCode[i]);
        	this.color[i].drawTextBox();
        }
        for(int i=cutoff; i<=10; i++) {
			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 + 4, 48 + (18*(i-cutoff)), tileEntity.colorCode[i]);
        	this.color[i].drawTextBox();
        }

		this.radius.drawTextBox();
         
		super.drawScreen(par1, par2, par3);
	}
	
	public void sendInfo() {
		int radius;
		try {
			radius = Integer.parseInt(this.radius.getText());
		} catch (NumberFormatException e) {
			radius = Config.SKYDIVE_RADIUS;
		}
		InstantBlocks.packetPipeline.sendToServer(new PacketSkydive(this.world, this.x, this.y, this.z, this.player.getDisplayName(), getColors(), radius, tp.isChecked()));
		
		IBHelper.xp(world, player, Config.XP_AMOUNT);
        IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
        IBHelper.msg(player, Strings.CREATE_SKYDIVE, Colors.a);
	}
	
	public int[] getColors() {
		int[] colors = new int[this.color.length];
		for(int i=0; i<colors.length; i++) {
			colors[i] = Colors.textToColor(this.color[i].getText()).getRGB();
		}
		return colors;
	}
	
	public void setRandom() {
		for(int i=0; i<=10; i++) {
			color[i].setText(createRandomHex());
			tileEntity.setColorCode(i, color[i].getText());
			tileEntity.setColor(i, color[i].getText());
		}
	}
	
	public String createRandomHex() {
		Color color = Colors.generateRandomColor();
		return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}
}