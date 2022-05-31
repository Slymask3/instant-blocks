package com.slymask3.instantblocks.gui.module;

import com.slymask3.instantblocks.gui.instant.GuiSchematic;
import cpw.mods.fml.client.GuiScrollingList;
import net.minecraft.client.renderer.Tessellator;

import java.util.ArrayList;

public class GuiSchematicSlot extends GuiScrollingList
{
    private GuiSchematic parent;
    private ArrayList<String> files;
    private static int slotHeight = 15;

    public GuiSchematicSlot(GuiSchematic parent, ArrayList<String> files, int x, int y, int width, int height) {
        super(parent.mc, width, height, y, y+height, x, slotHeight);
        this.parent=parent;
        this.files=files;
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
    protected int getContentHeight()
    {
        return (this.getSize()) * slotHeight + 1;
    }

    @Override
    protected void drawBackground() {}

    @Override
    protected void drawSlot(int listIndex, int var2, int var3, int var4, Tessellator var5) {
        this.parent.getFontRenderer().drawString(this.parent.getFontRenderer().trimStringToWidth(files.get(listIndex), listWidth - 10), this.left + 3 , var3 + 2, 0xFFFFFF);
    }
}