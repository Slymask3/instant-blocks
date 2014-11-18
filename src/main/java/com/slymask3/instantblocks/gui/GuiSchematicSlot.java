package com.slymask3.instantblocks.gui;

import java.util.ArrayList;

import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.client.GuiScrollingList;

public class GuiSchematicSlot extends GuiScrollingList
{
    private GuiSchematic parent;
    private ArrayList<String> files;
    private static int slotHeight = 15;

    public GuiSchematicSlot(GuiSchematic parent, ArrayList<String> files, int x, int y, int width, int height) {
        //super(parent.getMinecraftInstance(), width, parent.height, 32, parent.height - 66 + 4, 10, slotHeight);
        super(parent.getMinecraftInstance(), width, height, y, y+height, x, slotHeight);
        this.parent=parent;
        this.files=files;

		//this.list = new GuiList(this.fontRendererObj, this.width / 2 - 3 - 150, this.height / 4 + 66 + 12, 300+8, 50); //50
    }

    public void updateFiles(ArrayList<String> files) {
    	this.files = files;
    }
    
    @Override
    protected int getSize()
    {
        return files.size();
    }

    @Override
    protected void elementClicked(int var1, boolean var2)
    {
        this.parent.selectSchematicIndex(var1);
    }

    @Override
    protected boolean isSelected(int var1)
    {
        return this.parent.schematicIndexSelected(var1);
    }

    @Override
    protected void drawBackground()
    {
        //this.parent.drawWorldBackground(0);
    }

    @Override
    protected int getContentHeight()
    {
        return (this.getSize()) * slotHeight + 1;
    }

    @Override
    protected void drawSlot(int listIndex, int var2, int var3, int var4, Tessellator var5)
    {
        //ModContainer mc=mods.get(listIndex);
//        if (Loader.instance().getModState(mc)==ModState.DISABLED)
//        {
//            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getName(), listWidth - 10), this.left + 3 , var3 + 2, 0xFF2222);
//            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getDisplayVersion(), listWidth - 10), this.left + 3 , var3 + 12, 0xFF2222);
//            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth("DISABLED", listWidth - 10), this.left + 3 , var3 + 22, 0xFF2222);
//        }
//        else
//        {
            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(files.get(listIndex), listWidth - 10), this.left + 3 , var3 + 2, 0xFFFFFF);
//            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getDisplayVersion(), listWidth - 10), this.left + 3 , var3 + 12, 0xCCCCCC);
//            this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(mc.getMetadata() !=null ? mc.getMetadata().getChildModCountString() : "Metadata not found", listWidth - 10), this.left + 3 , var3 + 22, 0xCCCCCC);
//        }
    }

}