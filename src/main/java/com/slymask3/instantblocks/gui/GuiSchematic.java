package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.network.PacketSchematic;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@SideOnly(Side.CLIENT)
public class GuiSchematic extends GuiScreen {
	private GuiSchematicSlot schematicList;
    private int selected = -1;
    private String selectedSchem;
    private int listWidth = 300;
    private ArrayList<String> schematics;

	private EntityPlayer player;
	private TileEntitySchematic tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;
	private GuiTextField input;

	private GuiCheckBox center;
	private GuiCheckBox ignoreAir;
	
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

        this.buttonList.add(this.center = new GuiCheckBox(2, this.width / 2 - 4 - 150, this.height / 4 + 4 + 12, "Generate the schematic from the center.", false));
        this.buttonList.add(this.ignoreAir = new GuiCheckBox(3, this.width / 2 - 4 - 150, this.height / 4 + 14 + 12, "Ignore generating air. (Could prevent lag)", false));
        
		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
		this.input.setText("");
		this.input.setFocused(true);
		
		this.schematicList=new GuiSchematicSlot(this, schematics, this.width / 2 - 4 - 150, this.height / 4 + 42 + 12, 300+8, 62);
        this.schematicList.registerScrollButtons(this.buttonList, 7, 8);

		this.done.enabled = false;
	}

	@Override
	public void updateScreen() {
		this.input.updateCursorCounter();
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
		}
	}

	@Override
	protected void keyTyped(final char par1, final int par2) {
		
		if(("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ^&'@{}[],$=!-#()%.+~_ ".indexOf(par1) >= 0) || (par2 == 14)) {
			input.textboxKeyTyped(par1, par2);
		} else if(par2 == done.id) {
			actionPerformed(done);
		} else if(par2 == cancel.id) {
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
        
        this.schematics=new ArrayList<String>();
        
        Collection<File> all = new ArrayList<File>();
        addTree(new File("schematics/"), all);
        Object[] schems = all.toArray();
        
        for(int i=0; i<all.size();i++) {
        	String schem = schems[i].toString().replaceAll("schematics", "").replaceAll("\\\\", "").replaceAll(".schematic", "");
        	schematics.add(schem);
        }
        
        this.schematicList.updateFiles(schematics);
        
        //draw scrolling list
		this.schematicList.drawScreen(par1, par2, par3);

		//background behind found files
		this.drawRect(this.width / 2 - 4 - 150, this.height / 4 + 29 + 12, this.width / 2 - 4 - 150 +(300+8), this.height / 4 + 29 + 12 +(13), -16777216);

		//background behind schematic file
		this.drawRect(this.width / 2 - 4 - 150, this.height / 4 + 29 + 12 +(75), this.width / 2 - 4 - 150 +(300+8), this.height / 4 + 29 + 12 +(75+13), -16777216);
		
		
		File f = new File("schematics/"+input.getText()+".schematic");
        if(f.isFile()) {
        	this.drawString(this.fontRendererObj, "File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), 0x00FF00);
        } else {
        	this.drawString(this.fontRendererObj, "File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), 0xAA0000);
        }
        
        this.drawString(this.fontRendererObj, "Found " + all.size() + " schematic files:", this.width / 2 - 2 - 150, this.height / 4 + 31 + 12, 0xFFFFFF);
        
        if(all.size() == 0) {
        	this.drawString(this.fontRendererObj, "Put .schematic files into the 'schematics' folder in .minecraft.", this.width / 2 - 3 - 150, this.height / 4 +60 + 12, 0xAA0000);
        	this.drawString(this.fontRendererObj, "If the 'schematics' folder doesn't exit, create it.", this.width / 2 - 3 - 150, this.height / 4 +70 + 12, 0xAA0000);
        	this.drawString(this.fontRendererObj, "Files will update here right away, no need to restart.", this.width / 2 - 3 - 150, this.height / 4 +80 + 12, 0xAA0000);
        }

		this.input.drawTextBox();

        this.done.enabled = f.isFile();
		
		super.drawScreen(par1, par2, par3);
	}
	
	public void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if(children != null) {
            for(File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketSchematic(this.world, this.x, this.y, this.z, this.player.getDisplayName(), input.getText(), center.isChecked(), ignoreAir.isChecked()));
		
		IBHelper.xp(world, player, Config.XP_AMOUNT);
        IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
        IBHelper.msg(player, Strings.CREATE_SCHEMATIC.replace("%schematic%",input.getText()), Colors.a);
	}
	
	Minecraft getMinecraftInstance() {
        /** Reference to the Minecraft object. */
        return mc;
    }

    FontRenderer getFontRenderer() {
        /** The FontRenderer used by GuiScreen */
        return fontRendererObj;
    }

    public void selectSchematicIndex(int index) {
        this.selected=index;
        if(index>=0 && index<=schematics.size()) {
            this.selectedSchem=schematics.get(selected);
            this.input.setText(selectedSchem);
        } else {
            this.selectedSchem=null;
        }
        
        File f = new File("schematics/"+input.getText()+".schematic");
        this.done.enabled = f.isFile();
    }

    public boolean schematicIndexSelected(int var1)
    {
        return var1==selected;
    }
	
}