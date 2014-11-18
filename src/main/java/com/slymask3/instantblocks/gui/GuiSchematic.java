package com.slymask3.instantblocks.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
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

import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.client.GuiScrollingList;
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
	private GuiCheckBox ignoreAir;
	
//	private GuiListExtended list;
//	
//	private GuiScrollingList list2;
//	
//	private GuiModList list3;
//	
//	private GuiSlot slot;
	
	private GuiList list;
	
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

        this.buttonList.add(this.center = new GuiCheckBox(2, this.width / 2 - 4 - 150, this.height / 4 + 15 + 12, "Generate the schematic from the center.", false));
        this.buttonList.add(this.ignoreAir = new GuiCheckBox(3, this.width / 2 - 4 - 150, this.height / 4 + 25 + 12, "Ignore generating air. (Could prevent lag)", false));

        //this.buttonList.add(this.list = new GuiListExtended(this.mc, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20));
        
        //this.buttonList.add(this.slot = new GuiSlot(4, this.width / 2 - 4 - 150, this.height / 4 + 25 + 12, "Ignore generating air. (Could prevent lag)", false));
		
        //this.list3 = new GuiModList(this);
        
		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
		this.input.setText("");
		this.input.setFocused(true);
		
		//this.list2.

		this.list = new GuiList(this.fontRendererObj, this.width / 2 - 3 - 150, this.height / 4 + 66 + 12, 300+8, 50); //50
		
		
		this.done.enabled = false;
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
		
		if (("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ^&'@{}[],$=!-#()%.+~_ ".indexOf(par1) >= 0) || (par2 == 14)) {
			input.textboxKeyTyped(par1, par2);
		} else if (par2 == done.id) {
			actionPerformed(done);
		} else if (par2 == cancel.id) {
			actionPerformed(cancel);
		}

		File f = new File("schematics/"+input.getText()+".schematic");
        this.done.enabled = f.isFile();
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
        this.drawString(this.fontRendererObj, "Enter a Schematic File Name:", this.width / 2 - 4 - 150, 37, 10526880);
        this.drawString(this.fontRendererObj, "Options:", this.width / 2 - 3 - 150, this.height / 4 + 5 + 12, 10526880);

        //this.list3 = new GuiModList(this);
        
        //this.list.drawScreen(this.width / 2 - 3 - 150, this.height / 4 + 5 + 12, 1);
        //this.list2.drawScreen(this.width / 2 - 3 - 150, this.height / 4 + 5 + 12, 0);
        
        File f = new File("schematics/"+input.getText()+".schematic");
        if(f.isFile()) {
        	this.drawString(this.fontRendererObj, "Schematic File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 3 - 150, this.height / 4 + 40 + 12, 0x00FF00);
        } else {
        	this.drawString(this.fontRendererObj, "Schematic File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 3 - 150, this.height / 4 + 40 + 12, 0xAA0000);
        }
        
        Collection<File> all = new ArrayList<File>();
        addTree(new File("schematics/"), all);
        Object[] schems = all.toArray();
        //System.out.println(all);
        
        this.drawString(this.fontRendererObj, "Found " + all.size() + " schematic files:", this.width / 2 - 3 - 150, this.height / 4 +55 + 12, 10526880);
        
        list.cleanLines();
        for (int i=0; i<all.size();i++) {
        	String schem = schems[i].toString().replaceAll("schematics", "").replaceAll("\\\\", "").replaceAll(".schematic", "");
//        	if(schem.equalsIgnoreCase(input.getText())) {
//        		this.drawString(this.fontRendererObj, schem, this.width / 2 - 3 - 150, this.height / 4 +66+(i*9) + 12, 0x00FF00);
//        	} else {
//        		this.drawString(this.fontRendererObj, schem, this.width / 2 - 3 - 150, this.height / 4 +66+(i*9) + 12, 10526880);
//        	}
        	list.addLine(schem);
        }
        
        if(all.size() == 0) {
        	this.drawString(this.fontRendererObj, "Put .schematic files into the 'schematics' folder in '.minecraft'.", this.width / 2 - 3 - 150, this.height / 4 +66 + 12, 0xAA0000);
        	this.drawString(this.fontRendererObj, "If the 'schematics' folder doesn't exit, create it.", this.width / 2 - 3 - 150, this.height / 4 +76 + 12, 0xAA0000);
        	this.drawString(this.fontRendererObj, "Files will update here right away, no need to restart the game.", this.width / 2 - 3 - 150, this.height / 4 +86 + 12, 0xAA0000);
        }

		this.list.drawList();
		this.input.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}
	
	public void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketSchematic(this.world, this.x, this.y, this.z, this.player.getDisplayName(), input.getText(), center.isChecked(), ignoreAir.isChecked()));
		
		IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, "\u00a7aInstant Schematic created from the file '" + input.getText() + "'.", Colors.a);
	}
	
}