package com.slymask3.instantblocks.gui.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.network.PacketSkydive;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiSkydive extends GuiInstant {
	protected final TileEntitySkydive tileEntity;

    private GuiButtonExt random;
	private GuiTextField[] color = new GuiTextField[11];
	private GuiCheckBox tp;
	private GuiTextField radius;

	private final int cutoff = 6;

	public GuiSkydive(EntityPlayer player, TileEntitySkydive entity, World world, int x, int y, int z) {
		super(player,world,x,y,z);
		this.tileEntity = entity;
	}

	public void init() {
		this.buttonList.add(this.random = new GuiButtonExt(getID(), this.width / 2 + 4, this.height / 4 + 98 + 12, 150, 20, "Randomize"));

		this.buttonList.add(this.tp = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 83 + 12, "Teleport to top", true));
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

	public void click(GuiButton btn) {
		if(btn.id == random.id) {
			setRandom();
		}
//		} else if(btn.id == actualRainbow.id) {
//			if(actualRainbow.isChecked()) {
//				this.random.enabled = false;
//				for(int i=0; i<this.color.length; i++) {
//					this.color[i].setEnabled(false);
//				}
//			} else {
//				this.random.enabled = true;
//				for(int i=0; i<this.color.length; i++) {
//					this.color[i].setEnabled(true);
//				}
//			}
//		}
	}
	
	@Override
	public void updateScreen() {
		for(int i=0; i<11; i++) {
			this.color[i].updateCursorCounter();
		}
		this.radius.updateCursorCounter();
	}

	public void typed(char character, int key) {
		if(("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ".indexOf(character) >= 0) || (key == 14)) {
			for(int i=0; i<11; i++) {
				color[i].textboxKeyTyped(character, key);
				tileEntity.setColorCode(i, color[i].getText());
				tileEntity.setColor(i, color[i].getText());
	        }
		}
		if(("0123456789".indexOf(character) >= 0) || (key == 14)) {
			radius.textboxKeyTyped(character, key);
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

	public void draw(int par1, int par2, float par3) {
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
	}
	
	public void sendInfo() {
		int radius;
		try {
			radius = Integer.parseInt(this.radius.getText());
		} catch (NumberFormatException e) {
			radius = Config.SKYDIVE_RADIUS;
		}
		InstantBlocks.packetPipeline.sendToServer(new PacketSkydive(this.world, this.x, this.y, this.z, getColors(), radius, tp.isChecked()));

		BlockInstant block = (BlockInstant) BuildHelper.getBlock(world,x,y,z);
		block.afterBuild(world,x,y,z,player);
	}
	
	private int[] getColors() {
		int[] colors = new int[this.color.length];
		for(int i=0; i<colors.length; i++) {
			colors[i] = Colors.textToColor(this.color[i].getText()).getRGB();
		}
		return colors;
	}

	private void setRandom() {
		for(int i=0; i<=10; i++) {
			color[i].setText(createRandomHex());
			tileEntity.setColorCode(i, color[i].getText());
			tileEntity.setColor(i, color[i].getText());
		}
	}

	private String createRandomHex() {
		Color color = Colors.generateRandomColor();
		return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}
}