package com.slymask3.instantblocks.gui.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.gui.module.GuiTreeSlot;
import com.slymask3.instantblocks.network.PacketTree;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityTree;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiTree extends GuiInstant {
	protected final TileEntityTree tileEntity;

	private GuiTreeSlot treeList;
    private int selected = -1;
    private int selectedTree;
    private int[] trees = {0, 1, 2, 3, 4, 5, 6};

	private GuiCheckBox fullLog, fullLeaves, air;

	public GuiTree(EntityPlayer player, TileEntityTree entity, World world, int x, int y, int z) {
		super(player,world,x,y,z);
		this.tileEntity = entity;
	}

	public void init() {
		this.buttonList.add(this.fullLog = new GuiCheckBox(getID(), this.width / 2 + 4, 50, "Hollow Logs", true));
		this.buttonList.add(this.fullLeaves = new GuiCheckBox(getID(), this.width / 2 + 4, 60, "Hollow Leaves", true));

		this.buttonList.add(this.air = new GuiCheckBox(getID(), this.width / 2 + 4, 80, "Air Blocks Inside", true));

		this.treeList = new GuiTreeSlot(this, trees, this.width / 2 - 4 - 150, 50, 150, 120);
		this.treeList.registerScrollButtons(this.buttonList, 7, 8);

		this.done.enabled = false;
	}

	public void draw(int par1, int par2, float par3) {
        this.drawCenteredString(this.fontRendererObj, "Instant Huge Tree Block", this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, "Options:", this.width / 2 + 4, 40, 10526880);
        
        //draw scrolling list
		this.treeList.drawScreen(par1, par2, par3);

		//background behind found files
		this.drawRect(this.width / 2 - 4 - 150, 37, this.width / 2 - 4 - 150 +(150), 37 +(13), -16777216);

		//background behind schematic file
		this.drawRect(this.width / 2 - 4 - 150, 37 +(133), this.width / 2 - 4 - 150 +(150), 37 +(133+13), -16777216);

        this.drawString(this.fontRendererObj, "Tree Type:", this.width / 2 - 2 - 150, 40, 10526880);
		
//        if(selected == 7) {
//            this.drawString(this.fontRendererObj, "Coming Soon:", this.width / 2 + 4, 100, 0xAA0000);
//            this.drawString(this.fontRendererObj, "4x4 Jungle Tree", this.width / 2 + 4, 110, 0xAA0000);
//            this.drawString(this.fontRendererObj, "Tall Spruce Tree", this.width / 2 + 4, 120, 0xAA0000);
//            this.drawString(this.fontRendererObj, "Post any of your suggestions", this.width / 2 + 4, 140, 0xAA0000);
//            this.drawString(this.fontRendererObj, "in the official mod thread.", this.width / 2 + 4, 150, 0xAA0000);
//        }
	}

	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketTree(this.world, this.x, this.y, this.z, selectedTree, !fullLog.isChecked(), !fullLeaves.isChecked(), air.isChecked()));

		BlockInstant block = (BlockInstant)BuildHelper.getBlock(world,x,y,z);
		block.setCreateMessage(Strings.CREATE_TREE.replace("%tree%",treeToString(selectedTree)));
		block.afterBuild(world,x,y,z,player);
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

    public boolean schematicIndexSelected(int var1) {
        return var1==selected;
    }
}