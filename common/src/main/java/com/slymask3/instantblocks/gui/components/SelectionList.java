package com.slymask3.instantblocks.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;

public class SelectionList<T extends ObjectSelectionList.Entry<T>> extends ObjectSelectionList<T> {
    public SelectionList(Minecraft minecraft, int x, int y, int width, int height, int itemHeight) {
        super(minecraft, width, height, y, y + height, itemHeight);
        this.setLeftPos(x);
        this.setRenderHeader(false, 0);
        this.setRenderTopAndBottom(false);
        this.setRenderSelection(false);
        this.setRenderBackground(true);
    }

    protected int getScrollbarPosition() {
        return this.x0 + this.width;
    }
}