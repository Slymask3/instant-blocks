package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.network.PacketSkydive;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
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

import java.util.Random;

@SideOnly(Side.CLIENT)
public class GuiSkydive extends GuiScreen {
	private final World world;
	private final int x, y, z;
	private final EntityPlayer player;
	private final TileEntitySkydive tileEntity;
	
    private GuiButtonExt done, cancel, random;
	private GuiTextField[] color = new GuiTextField[11];
	private GuiCheckBox tp, actualRainbow;
	private GuiTextField radius;

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
        this.buttonList.add(this.random = new GuiButtonExt(103, this.width / 2 - 4 - 150, this.height / 4 + 98 + 12, 308, 20, "Randomize"));

		this.buttonList.add(this.tp = new GuiCheckBox(104, this.width / 2 + 4, this.height / 4 + 83 + 12, "Teleport to top", true));
		this.buttonList.add(this.actualRainbow = new GuiCheckBox(105, this.width / 2 - 4 - 150, this.height / 4 + 83 + 12, "Use actual rainbow", true));

		this.radius = new GuiTextField(this.fontRendererObj, this.width / 2 + 4 + 50, 135, 60, 14);
		this.radius.setText(String.valueOf(Config.SKYDIVE_RADIUS));
        
        for(int i=0; i<=5; i++) {
        	this.color[i] = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 100, 45 + (18*i), 60, 14);
			this.color[i].setText(tileEntity.color[i]);
        }
        
        for(int i=6; i<=10; i++) {
        	this.color[i] = new GuiTextField(this.fontRendererObj, this.width / 2 + 4 + 50, 45 + (18*(i-6)), 60, 14);
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
		this.drawString(this.fontRendererObj, "Radius:", this.width / 2 + 4, 138,10526880);
        
        for(int i=0; i<=5; i++) {
			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 - 4 - 150, 48 + (18*i), tileEntity.colorCode[i]);
        	this.color[i].drawTextBox();
        }
        
        for(int i=6; i<=10; i++) {
			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 + 4, 48 + (18*(i-6)), tileEntity.colorCode[i]);
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
		InstantBlocks.packetPipeline.sendToServer(new PacketSkydive(this.world, this.x, this.y, this.z, this.player.getDisplayName(), getHex(color[0].getText()), getHex(color[1].getText()), getHex(color[2].getText()), getHex(color[3].getText()), getHex(color[4].getText()), getHex(color[5].getText()), getHex(color[6].getText()), getHex(color[7].getText()), getHex(color[8].getText()), getHex(color[9].getText()), getHex(color[10].getText()), radius, tp.isChecked(), actualRainbow.isChecked()));
		
		IBHelper.xp(world, player, Config.XP_AMOUNT);
        IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
        IBHelper.msg(player, Strings.CREATE_SKYDIVE, Colors.a);
	}
	
	public int getHex(String input) {
		int color = 0x00FFFFFF;
		if(input.equalsIgnoreCase("red")) {
			color = 0x00963430;
		} else if(input.equalsIgnoreCase("orange")) {
			color = 0x00DB7D3E;
		} else if(input.equalsIgnoreCase("yellow")) {
			color = 0x00B1A627;
		} else if(input.equalsIgnoreCase("lime")) {
			color = 0x0041AE38;
		} else if(input.equalsIgnoreCase("green")) {
			color = 0x0035461B;
		} else if(input.equalsIgnoreCase("cyan")) {
			color = 0x002E6E89;
		} else if(input.equalsIgnoreCase("light blue") || input.equalsIgnoreCase("lightblue")) {
			color = 0x006B8AC9;
		} else if(input.equalsIgnoreCase("blue")) {
			color = 0x002E388D;
		} else if(input.equalsIgnoreCase("purple")) {
			color = 0x007E3DB5;
		} else if(input.equalsIgnoreCase("magenta")) {
			color = 0x00B350BC;
		} else if(input.equalsIgnoreCase("pink")) {
			color = 0x00D08499;
		} else if(input.equalsIgnoreCase("white")) {
			color = 0x00DDDDDD;
		} else if(input.equalsIgnoreCase("gray") || input.equalsIgnoreCase("grey")) {
			color = 0x00404040;
		} else if(input.equalsIgnoreCase("light gray") || input.equalsIgnoreCase("lightgray") || input.equalsIgnoreCase("light grey") || input.equalsIgnoreCase("lightgrey")) {
			color = 0x009AA1A1;
		} else if(input.equalsIgnoreCase("brown")) {
			color = 0x004F321F;
		} else if(input.equalsIgnoreCase("black")) {
			color = 0x00191616;
		} else {
			try {
				color = (int)Long.parseLong(input, 16);
			} catch(Exception e) {}
		}
		return color;
	}
	
	public void setRandom() {
		for(int i=0; i<=10; i++) {
			color[i].setText(createRandomHex());
			tileEntity.setColorCode(i, color[i].getText());
			tileEntity.setColor(i, color[i].getText());
		}
	}
	
	public String createRandomHex() {
		StringBuilder sb = new StringBuilder();
		sb.append(getHexSingleValue());
		sb.append(getHexSingleValue());
		sb.append(getHexSingleValue());
		sb.append(getHexSingleValue());
		sb.append(getHexSingleValue());
		sb.append(getHexSingleValue());
		return sb.toString();
	}
	
	public String getHexSingleValue() {
		Random rand = new Random();
		int i = rand.nextInt(16);
		switch(i) {
			case 0: case 1: case 2: case 3: case 4:
			case 5: case 6: case 7: case 8: case 9:
				return String.valueOf(i);
			case 10: return "A";
			case 11: return "B";
			case 12: return "C";
			case 13: return "D";
			case 14: return "E";
			case 15: return "F";
			default: return "0";
		}
	}
}