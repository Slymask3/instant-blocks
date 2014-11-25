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
import com.slymask3.instantblocks.network.PacketFlat;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.tileentity.TileEntityFlat;
import com.slymask3.instantblocks.utility.IBHelper;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFlat extends GuiScreen {
	private EntityPlayer player;
	private TileEntityFlat tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;

	private GuiTextFieldColored up;
	private GuiTextFieldColored down;
	private GuiTextFieldColored forward;
	private GuiTextFieldColored back;
	private GuiTextFieldColored left;
	private GuiTextFieldColored right;
	
	//private GuiCheckBox harvest;
	
	private World world;
	private int x;
	private int y;
	private int z;

	public GuiFlat(EntityPlayer player, TileEntityFlat entity, World world, int x, int y, int z) {
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
        this.buttonList.add(this.done = new GuiButtonExt(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, "Clear"));
        this.buttonList.add(this.cancel = new GuiButtonExt(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
        
        //this.buttonList.add(this.harvest = new GuiCheckBox(8, this.width / 2 - 4 - 150, 130, "Put blocks into a chest", true));

		this.up = new GuiTextFieldColored(this.fontRendererObj, this.width / 2 - 4 - 25, 50, 50+8, 20, 0x0000AA, 0xFFFF88);
		this.down = new GuiTextFieldColored(this.fontRendererObj, this.width / 2 - 4 - 25, 150, 50+8, 20, 0x0000AA, 0xFFFF88);
		
		this.forward = new GuiTextFieldColored(this.fontRendererObj, this.width / 2 - 4 - 100, 120, 50+8, 20, 0xAA0000, 0x88FFFF);
		this.left = new GuiTextFieldColored(this.fontRendererObj, this.width / 2 - 4 - 125, 80, 50+8, 20, 0x00AA00, 0xFF88FF);
		
		this.back = new GuiTextFieldColored(this.fontRendererObj, this.width / 2 - 4 + 50, 80, 50+8, 20, 0xAA0000, 0x88FFFF);
		this.right = new GuiTextFieldColored(this.fontRendererObj, this.width / 2 - 4 + 75, 120, 50+8, 20, 0x00AA00, 0xFF88FF);
		
		this.up.setText(Integer.toString(tileEntity.up));
		this.down.setText(Integer.toString(tileEntity.down));
		this.forward.setText(Integer.toString(tileEntity.forward));
		this.back.setText(Integer.toString(tileEntity.back));
		this.left.setText(Integer.toString(tileEntity.left));
		this.right.setText(Integer.toString(tileEntity.right));
		
		this.up.setFocused(true);
		
		this.done.enabled = notEmpty();
	}

	@Override
	public void updateScreen() {
		this.up.updateCursorCounter();
		this.down.updateCursorCounter();
		this.forward.updateCursorCounter();
		this.back.updateCursorCounter();
		this.left.updateCursorCounter();
		this.right.updateCursorCounter();
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
		if (("0123456789".indexOf(par1) >= 0) || (par2 == 14)) {
			up.textboxKeyTyped(par1, par2);
			down.textboxKeyTyped(par1, par2);
			forward.textboxKeyTyped(par1, par2);
			back.textboxKeyTyped(par1, par2);
			left.textboxKeyTyped(par1, par2);
			right.textboxKeyTyped(par1, par2);
			
			try {
				int upN = Integer.parseInt(up.getText());
				int downN = Integer.parseInt(down.getText());
				int forwardN = Integer.parseInt(forward.getText());
				int backN = Integer.parseInt(back.getText());
				int leftN = Integer.parseInt(left.getText());
				int rightN = Integer.parseInt(right.getText());
				tileEntity.set(upN, downN, forwardN, backN, leftN, rightN);
			} catch (NumberFormatException e) {
				
			}
		} else if (par2 == done.id) {
			actionPerformed(done);
		} else if (par2 == cancel.id) {
			actionPerformed(cancel);
		}

        this.done.enabled = notEmpty();
	}

	@Override
	protected void mouseClicked(int x, int y, int click) {
        super.mouseClicked(x, y, click);
        this.up.mouseClicked(x, y, click);
        this.down.mouseClicked(x, y, click);
        this.forward.mouseClicked(x, y, click);
        this.back.mouseClicked(x, y, click);
        this.left.mouseClicked(x, y, click);
        this.right.mouseClicked(x, y, click);
    }
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Clear Block", this.width / 2, 20, 16777215);

        this.drawString(this.fontRendererObj, "Up (y+):", this.width / 2 - 4 - 25, 40, 0x0000AA);
        this.drawString(this.fontRendererObj, "Down (y-):", this.width / 2 - 4 - 25, 140, 0x0000AA);
        
        int meta = tileEntity.getBlockMetadata();
        
        if(meta == 0) {
	        this.drawString(this.fontRendererObj, "Forward (z+):", this.width / 2 - 4 - 100, 110, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Back (z-):", this.width / 2 - 4 + 50, 70, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Left (x-):", this.width / 2 - 4 - 125, 70, 0x00AA00);
	        this.drawString(this.fontRendererObj, "Right (x+):", this.width / 2 - 4 + 75, 110, 0x00AA00);
        } else if(meta == 1) {
        	this.drawString(this.fontRendererObj, "Forward (x-):", this.width / 2 - 4 - 100, 110, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Back (x+):", this.width / 2 - 4 + 50, 70, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Left (z-):", this.width / 2 - 4 - 125, 70, 0x00AA00);
	        this.drawString(this.fontRendererObj, "Right (z+):", this.width / 2 - 4 + 75, 110, 0x00AA00);
        } else if(meta == 2) {
        	this.drawString(this.fontRendererObj, "Forward (z-):", this.width / 2 - 4 - 100, 110, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Back (z+):", this.width / 2 - 4 + 50, 70, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Left (x+):", this.width / 2 - 4 - 125, 70, 0x00AA00);
	        this.drawString(this.fontRendererObj, "Right (x-):", this.width / 2 - 4 + 75, 110, 0x00AA00);
        } else if(meta == 3) {
        	this.drawString(this.fontRendererObj, "Forward (x+):", this.width / 2 - 4 - 100, 110, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Back (x-):", this.width / 2 - 4 + 50, 70, 0xAA0000);
	        this.drawString(this.fontRendererObj, "Left (z+):", this.width / 2 - 4 - 125, 70, 0x00AA00);
	        this.drawString(this.fontRendererObj, "Right (z-):", this.width / 2 - 4 + 75, 110, 0x00AA00);
        }

        //this.drawVerticalLine(this.width / 2 - 4 - 100 + 58, 120, this.width / 2 + 0 + 50, 80);
        
        
		this.up.drawTextBox();
		this.down.drawTextBox();
		this.forward.drawTextBox();
		this.back.drawTextBox();
		this.left.drawTextBox();
		this.right.drawTextBox();
		
		super.drawScreen(par1, par2, par3);
	}
	
	public boolean notEmpty() {
		return
		(this.up.getText().trim().length() > 0) && 
		(this.down.getText().trim().length() > 0) && 
		(this.forward.getText().trim().length() > 0) && 
		(this.back.getText().trim().length() > 0) && 
		(this.left.getText().trim().length() > 0) && 
		(this.right.getText().trim().length() > 0);
	}
	
	public void sendInfo() {
		try {
			int upN = Integer.parseInt(up.getText());
			int downN = Integer.parseInt(down.getText());
			int forwardN = Integer.parseInt(forward.getText());
			int backN = Integer.parseInt(back.getText());
			int leftN = Integer.parseInt(left.getText());
			int rightN = Integer.parseInt(right.getText());

			InstantBlocks.packetPipeline.sendToServer(new PacketFlat(this.world, this.x, this.y, this.z, this.player.getDisplayName(), upN, downN, forwardN, backN, leftN, rightN, false));
//			InstantBlocks.packetPipeline.sendToServer(new PacketFlat(this.world, this.x, this.y, this.z, this.player.getDisplayName(), upN, downN, forwardN, backN, leftN, rightN, harvest.isChecked()));
//			InstantBlocks.packetPipeline.sendToServer(new PacketFlat(this.world, this.x, this.y, this.z, this.player.getDisplayName(), 10, 10, 1, 5, 10, 1, harvest.isChecked()));
			
			IBHelper.xp(world, player, ConfigurationHandler.xp);
	        IBHelper.effectFull(world, "reddust", x, y, z);
	        IBHelper.msg(player, "\u00a7aCleared blocks around.", Colors.a);
		} catch (NumberFormatException e) {
	        IBHelper.msg(player, "\u00a7cCould not clear the blocks around.", Colors.c);
		}
	}
	
}