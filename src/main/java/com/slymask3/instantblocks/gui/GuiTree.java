package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.network.PacketTree;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityTree;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class GuiTree extends GuiScreen {
	private final World world;
	private final int x, y, z;
	private final EntityPlayer player;
	private final TileEntityTree tileEntity;

	private GuiTreeSlot treeList;
    private int selected = -1;
    private int selectedTree;
    private int[] trees = {0, 1, 2, 3, 4, 5, 6, 7};
	
    private GuiButtonExt done, cancel;
	private GuiCheckBox fullLog, fullLeaves, air;

	public GuiTree(EntityPlayer player, TileEntityTree entity, World world, int x, int y, int z) {
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

        this.buttonList.add(this.fullLog = new GuiCheckBox(2, this.width / 2 + 4, 50, "Hollow Logs", true));
        this.buttonList.add(this.fullLeaves = new GuiCheckBox(3, this.width / 2 + 4, 60, "Hollow Leaves", true));

        this.buttonList.add(this.air = new GuiCheckBox(4, this.width / 2 + 4, 80, "Air Blocks Inside", true));

		this.treeList=new GuiTreeSlot(this, trees, this.width / 2 - 4 - 150, 50, 150, 120);
        this.treeList.registerScrollButtons(this.buttonList, 7, 8);

		this.done.enabled = false;
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
		if(par2 == done.id) {
			actionPerformed(done);
		} else if(par2 == cancel.id) {
			actionPerformed(cancel);
		}
	}
	
	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Instant Huge Tree Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Options:", this.width / 2 + 4, 40, 10526880);
        
        //draw scrolling list
		this.treeList.drawScreen(par1, par2, par3);

		//background behind found files
		this.drawRect(this.width / 2 - 4 - 150, 37, this.width / 2 - 4 - 150 +(150), 37 +(13), -16777216);

		//background behind schematic file
		this.drawRect(this.width / 2 - 4 - 150, 37 +(133), this.width / 2 - 4 - 150 +(150), 37 +(133+13), -16777216);

        this.drawString(this.fontRendererObj, "Tree Type:", this.width / 2 - 2 - 150, 40, 10526880);
		
        if(selected == 7) {
            this.drawString(this.fontRendererObj, "Coming Soon:", this.width / 2 + 4, 100, 0xAA0000);
            this.drawString(this.fontRendererObj, "4x4 Jungle Tree", this.width / 2 + 4, 110, 0xAA0000);
            this.drawString(this.fontRendererObj, "Tall Spruce Tree", this.width / 2 + 4, 120, 0xAA0000);
            this.drawString(this.fontRendererObj, "Post any of your suggestions", this.width / 2 + 4, 140, 0xAA0000);
            this.drawString(this.fontRendererObj, "in the official mod thread.", this.width / 2 + 4, 150, 0xAA0000);
        }

		super.drawScreen(par1, par2, par3);
	}
	
	private String treeToString(int tree) {
    	switch(tree) {
			case 0: return "Huge Oak Tree";
			case 1: return "Huge Spruce Tree";
			case 2: return "Huge Birch Tree";
			case 3: return "Huge Jungle Tree";
			case 4: return "Huge Acacia Tree";
			case 5: return "Huge Dark Oak Tree";
			case 6: return "Huge Glass Tree";
			default: return "Error";
    	}
    }
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketTree(this.world, this.x, this.y, this.z, selectedTree, !fullLog.isChecked(), !fullLeaves.isChecked(), air.isChecked()));

		BlockInstant block = (BlockInstant)BuildHelper.getBlock(world,x,y,z);
		block.setCreateMessage(Strings.CREATE_TREE.replace("%tree%",treeToString(selectedTree)));
		block.afterBuild(world,x,y,z,player);
	}
	
	Minecraft getMinecraftInstance() {
        return mc;
    }

    FontRenderer getFontRenderer() {
        return fontRendererObj;
    }
    
    public void selectTreeIndex(int index) {
        this.selected=index;
        if((index>=0 && index<=6)) {
            this.selectedTree=trees[selected];
            this.done.enabled = true;
        } else {
            this.selectedTree=-1;
            this.done.enabled = false;
        }
    }

    public boolean schematicIndexSelected(int var1)
    {
        return var1==selected;
    }
}