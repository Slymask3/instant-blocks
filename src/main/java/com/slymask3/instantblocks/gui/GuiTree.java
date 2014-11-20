package com.slymask3.instantblocks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.network.PacketTree;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.tileentity.TileEntityTree;
import com.slymask3.instantblocks.utility.IBHelper;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTree extends GuiScreen {
	private GuiTreeSlot treeList;
    private int selected = -1;
    private int selectedTree;
    private int[] trees = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	private EntityPlayer player;
	private TileEntityTree tileEntity;
	
    private GuiButtonExt done;
    private GuiButtonExt cancel;

	private GuiCheckBox fullLog;
	private GuiCheckBox fullLeaves;
	private GuiCheckBox air;
	
	private World world;
	private int x;
	private int y;
	private int z;

	public GuiTree(EntityPlayer player, TileEntityTree entity, World world, int x, int y, int z) {
		this.player = player;
		this.tileEntity = entity;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		
//		for(int i=0; i<7; i++) {
//			trees.add(i);
//			LogHelper.info(i);
//		}
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
        this.buttonList.add(this.done = new GuiButtonExt(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, "Generate"));
        this.buttonList.add(this.cancel = new GuiButtonExt(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));

        this.buttonList.add(this.fullLog = new GuiCheckBox(2, this.width / 2 + 4, 50, "Hollow Logs", true));
        this.buttonList.add(this.fullLeaves = new GuiCheckBox(3, this.width / 2 + 4, 60, "Hollow Leaves", true));

        this.buttonList.add(this.air = new GuiCheckBox(4, this.width / 2 + 4, 80, "Air Blocks Inside", true));

        //this.buttonList.add(this.list = new GuiListExtended(this.mc, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20));
        
        //this.buttonList.add(this.slot = new GuiSlot(4, this.width / 2 - 4 - 150, this.height / 4 + 25 + 12, "Ignore generating air. (Could prevent lag)", false));
		
        //this.list3 = new GuiModList(this);
        
//		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
//		this.input.setText("");
//		this.input.setFocused(true);
		
		//this.list2.

		//this.list = new GuiList(this.fontRendererObj, this.width / 2 - 3 - 150, this.height / 4 + 66 + 12, 300+8, 50); //50
		//this.list2 = new GuiList2(Minecraft.getMinecraft(),  this.width / 2 - 3 - 150, this.height / 4 + 66 + 12, 100, 500, 50); //50
		
		this.treeList=new GuiTreeSlot(this, trees, this.width / 2 - 4 - 150, 50, 150, 120);
        this.treeList.registerScrollButtons(this.buttonList, 7, 8);
		
		
		this.done.enabled = false;
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
		if (par2 == done.id) {
			actionPerformed(done);
		} else if (par2 == cancel.id) {
			actionPerformed(cancel);
		}

		//File f = new File("schematics/"+input.getText()+".schematic");
        //this.done.enabled = f.isFile();
	}
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Tree Block", this.width / 2, 20, 16777215);
        //this.drawString(this.fontRendererObj, "Enter a Schematic File Name:", this.width / 2 - 4 - 150, 37, 10526880);
        this.drawString(this.fontRendererObj, "Options:", this.width / 2 + 4, 40, 10526880);

        //this.list3 = new GuiModList(this);
        
        //this.list.drawScreen(this.width / 2 - 3 - 150, this.height / 4 + 5 + 12, 1);
        //this.list2.drawScreen(this.width / 2 - 3 - 150, this.height / 4 + 5 + 12, 0);
        
        
        
//        this.schematics=new ArrayList<String>();
//        
//        Collection<File> all = new ArrayList<File>();
//        addTree(new File("schematics/"), all);
//        Object[] schems = all.toArray();
//        
//        for (int i=0; i<all.size();i++) {
//        	String schem = schems[i].toString().replaceAll("schematics", "").replaceAll("\\\\", "").replaceAll(".schematic", "");
//        	schematics.add(schem);
//        }
//        
//        this.schematicList.updateFiles(schematics);
        
        
        
        
        //draw scrolling list
		this.treeList.drawScreen(par1, par2, par3);

		//background behind found files
		this.drawRect(this.width / 2 - 4 - 150, 37, this.width / 2 - 4 - 150 +(150), 37 +(13), -16777216);

		//background behind schematic file
		this.drawRect(this.width / 2 - 4 - 150, 37 +(133), this.width / 2 - 4 - 150 +(150), 37 +(133+13), -16777216);
		
		

        this.drawString(this.fontRendererObj, "Tree Type:", this.width / 2 - 2 - 150, 40, 10526880);
		
		
//		File f = new File("schematics/"+input.getText()+".schematic");
//        if(f.isFile()) {
//        	this.drawString(this.fontRendererObj, "File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), 0x00FF00);
//        } else {
//        	this.drawString(this.fontRendererObj, "File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), 0xAA0000);
//        }
		
		
        //Collection<File> all = new ArrayList<File>();
        //addTree(new File("schematics/"), all);
        //Object[] schems = all.toArray();
        
        //this.drawString(this.fontRendererObj, "Found " + all.size() + " schematic files:", this.width / 2 - 2 - 150, this.height / 4 + 31 + 12, 0xFFFFFF);
        
        //list.cleanLines();
//        for (int i=0; i<all.size();i++) {
//        	String schem = schems[i].toString().replaceAll("schematics", "").replaceAll("\\\\", "").replaceAll(".schematic", "");
////        	if(schem.equalsIgnoreCase(input.getText())) {
////        		this.drawString(this.fontRendererObj, schem, this.width / 2 - 3 - 150, this.height / 4 +66+(i*9) + 12, 0x00FF00);
////        	} else {
////        		this.drawString(this.fontRendererObj, schem, this.width / 2 - 3 - 150, this.height / 4 +66+(i*9) + 12, 10526880);
////        	}
//        	list.addLine(schem);
//        }
        
//        if(all.size() == 0) {
//        	this.drawString(this.fontRendererObj, "Put .schematic files into the 'schematics' folder in .minecraft.", this.width / 2 - 3 - 150, this.height / 4 +60 + 12, 0xAA0000);
//        	this.drawString(this.fontRendererObj, "If the 'schematics' folder doesn't exit, create it.", this.width / 2 - 3 - 150, this.height / 4 +70 + 12, 0xAA0000);
//        	this.drawString(this.fontRendererObj, "Files will update here right away, no need to restart.", this.width / 2 - 3 - 150, this.height / 4 +80 + 12, 0xAA0000);
//        }
//
//		this.input.drawTextBox();
		
		
		
		//File f = new File("schematics/"+input.getText()+".schematic");
//        this.done.enabled = f.isFile();
		
		
		
		super.drawScreen(par1, par2, par3);
	}
	
	private String treeToString(int tree) {
    	switch(tree) {
    	case 0: return "Oak Tree";
    	case 1: return "Spruce Tree";
    	case 2: return "Birch Tree";
    	case 3: return "Jungle Tree";
    	case 4: return "Big Jungle Tree";
    	case 5: return "Acacia Tree";
    	case 6: return "Dark Oak Tree";
    	case 7: return "Reverse Tree";
    	case 8: return "Glass Tree";
    	case 9: return "Error";
	    default: return "Error";
    	}
    }
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketTree(this.world, this.x, this.y, this.z, this.player.getDisplayName(), selectedTree, !fullLog.isChecked(), !fullLeaves.isChecked(), air.isChecked()));
		
		IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, "\u00a7aInstant " + treeToString(selectedTree) + " created.", Colors.a);
	}
	
	
	
	
	
	
	Minecraft getMinecraftInstance() {
        /** Reference to the Minecraft object. */
        return mc;
    }

    FontRenderer getFontRenderer() {
        /** The FontRenderer used by GuiScreen */
        return fontRendererObj;
    }
    
    public void selectSchematicIndex(int var1)
    {
        this.selected=var1;
        if ((var1>=0 && var1<=3) || (var1>=5 && var1<=8)) {
            this.selectedTree=trees[selected];
            this.done.enabled = true;
        } else {
            this.selectedTree=-1;
            this.done.enabled = false;
        }
        
        //File f = new File("schematics/"+input.getText()+".schematic");
        //this.done.enabled = f.isFile();
        //cachedLogo = null;
    }

    public boolean schematicIndexSelected(int var1)
    {
        return var1==selected;
    }
	
}