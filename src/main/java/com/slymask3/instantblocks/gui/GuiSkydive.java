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
import com.slymask3.instantblocks.network.PacketSkydive;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.utility.IBHelper;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSkydive extends GuiScreen {
	private EntityPlayer player;
	private TileEntitySkydive tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;

	private GuiTextField[] color = new GuiTextField[11];
	
	private int[] colorCode = {
		getColor(150, 52, 48),
		getColor(219, 125, 62),
		getColor(177, 166, 39),
		getColor(65, 174, 56),
		getColor(53, 70, 27),
		getColor(46, 110, 137),
		getColor(107, 138, 201),
		getColor(46, 56, 141),
		getColor(126, 61, 181),
		getColor(179, 80, 188),
		getColor(208, 132, 153)
	};
	
	private World world;
	private int x;
	private int y;
	private int z;

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
        this.buttonList.add(this.done = new GuiButtonExt(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, "Generate"));
        this.buttonList.add(this.cancel = new GuiButtonExt(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        
        for(int i=0; i<=6; i++) {
        	this.color[i] = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 100, 45 + (20*i), 60, 14);
			this.color[i].setText(getDefaultColor(i));
        }
        
        for(int i=7; i<=10; i++) {
        	this.color[i] = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 + 50, 45 + (20*(i-7)), 60, 14);
			this.color[i].setText(tileEntity.color[i]);
        }
        
        //this.color1 = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 + 150, 60 + (15*5), 200, 20);
		//this.color1.setText(getDefaultColor(0));
        
        //this.color[0].setFocused(true);
	}
	
	public String getDefaultColor(int color) {
		switch(color) {
			case 0:
				return "red";
			case 1:
				return "orange";
			case 2:
				return "yellow";
			case 3:
				return "lime";
			case 4:
				return "green";
			case 5:
				return "cyan";
			case 6:
				return "light blue";
			case 7:
				return "blue";
			case 8:
				return "purple";
			case 9:
				return "magenta";
			case 10:
				return "pink";
			default:
				return "white";
		}
	}
	
	public int getAARRGGBB(int id) {
		switch(id) {
			case 0:
				return getColor(150, 52, 48);
			case 1:
				return getColor(219, 125, 62);
			case 2:
				return getColor(177, 166, 39);
			case 3:
				return getColor(65, 174, 56);
			case 4:
				return getColor(53, 70, 27);
			case 5:
				return getColor(46, 110, 137);
			case 6:
				return getColor(107, 138, 201);
			case 7:
				return getColor(46, 56, 141);
			case 8:
				return getColor(126, 61, 181);
			case 9:
				return getColor(179, 80, 188);
			case 10:
				return getColor(208, 132, 153);
			default:
				return getColor(255, 255, 255);
		}
	}
	
	public int getColor(int r, int g, int b) {
		return (r * 65536) + (g * 256) + b;
	}
	
	private void setColorCode(int i, String input) {
		int color = 16777215;
		
		if(input.equalsIgnoreCase("red")) {
			color = getAARRGGBB(0);
		} else if(input.equalsIgnoreCase("orange")) {
			color = getAARRGGBB(1);
		} else if(input.equalsIgnoreCase("yellow")) {
			color = getAARRGGBB(2);
		} else if(input.equalsIgnoreCase("lime")) {
			color = getAARRGGBB(3);
		} else if(input.equalsIgnoreCase("green")) {
			color = getAARRGGBB(4);
		} else if(input.equalsIgnoreCase("cyan")) {
			color = getAARRGGBB(5);
		} else if(input.equalsIgnoreCase("light blue") || input.equalsIgnoreCase("lightblue")) {
			color = getAARRGGBB(6);
		} else if(input.equalsIgnoreCase("blue")) {
			color = getAARRGGBB(7);
		} else if(input.equalsIgnoreCase("purple")) {
			color = getAARRGGBB(8);
		} else if(input.equalsIgnoreCase("magenta")) {
			color = getAARRGGBB(9);
		} else if(input.equalsIgnoreCase("pink")) {
			color = getAARRGGBB(10);
		} else {
			try {
				color = Integer.parseInt(input, 16);
			} catch (Exception e) {}
		}
		
		this.colorCode[i] = color;
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
		
		if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ#".indexOf(par1) >= 0) || (par2 == 14)) {
			
			for(int i=0; i<11; i++) {
				color[i].textboxKeyTyped(par1, par2);
				tileEntity.setColorCode(i, color[i].getText());
				tileEntity.setColor(i, color[i].getText());
	        }
		}
		
		if (par2 == done.id) {
			actionPerformed(done);
		} else if (par2 == cancel.id) {
			actionPerformed(cancel);
		}

		
		
        //this.done.enabled = this.input.getText().trim().length() > 3;
	}

	@Override
	protected void mouseClicked(int x, int y, int click) {
        super.mouseClicked(x, y, click);
        
        for(int i=0; i<11; i++) {
        	this.color[i].mouseClicked(x, y, click);
        }
    }
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Skydive Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Enter colors by name or hex value.", this.width / 2 - 4 - 150, 33, 10526880); //aarrggbb
        
//        for(int i=0; i<11; i++) {
//        	this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 - 4 - 150, 50 + (12*i), 10526880);
//        	this.color[i].drawTextBox();
//        }
        
        for(int i=0; i<=6; i++) {
			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 - 4 - 150, 48 + (20*i), tileEntity.colorCode[i]);
        	this.color[i].drawTextBox();
        }
        
        for(int i=7; i<=10; i++) {
			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 - 4, 48 + (20*(i-7)), tileEntity.colorCode[i]);
        	this.color[i].drawTextBox();
        }
         
		super.drawScreen(par1, par2, par3);
	}
	
	public void sendInfo() {
		//InstantBlocks.packetPipeline.sendToAll(new PacketHarvest(this.world, this.x, this.y, this.z, this.player.getDisplayName(), logOak.isChecked(), logSpruce.isChecked(), logBirch.isChecked(), logJungle.isChecked(), logAcacia.isChecked(), logDark.isChecked(), wheat.isChecked(), carrot.isChecked(), potato.isChecked(), cactus.isChecked(), pumpkin.isChecked(), melon.isChecked(), sugarcane.isChecked(), cocoa.isChecked(), mushroom.isChecked(), netherwart.isChecked(), replant.isChecked()));
		//InstantBlocks.packetPipeline.sendToServer(new PacketHarvest(this.world, this.x, this.y, this.z, this.player.getDisplayName(), logOak.isChecked(), logSpruce.isChecked(), logBirch.isChecked(), logJungle.isChecked(), logAcacia.isChecked(), logDark.isChecked(), wheat.isChecked(), carrot.isChecked(), potato.isChecked(), cactus.isChecked(), pumpkin.isChecked(), melon.isChecked(), sugarcane.isChecked(), cocoa.isChecked(), mushroom.isChecked(), netherwart.isChecked(), replant.isChecked()));
		
		LogHelper.info("sendInfo()");
		
		InstantBlocks.packetPipeline.sendToServer(new PacketSkydive(this.world, this.x, this.y, this.z, this.player.getDisplayName(), getHex(color[0].getText()), getHex(color[1].getText()), getHex(color[2].getText()), getHex(color[3].getText()), getHex(color[4].getText()), getHex(color[5].getText()), getHex(color[6].getText()), getHex(color[7].getText()), getHex(color[8].getText()), getHex(color[9].getText()), getHex(color[10].getText())));
		
		IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, Strings.fallCreate, Colors.a);
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
		} else {
			try {
				color = (int)Long.parseLong(input, 16);
			} catch (Exception e) {}
		}
		
		return color;
	}
	
}