package com.slymask3.instantblocks.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
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

    protected void renderList(PoseStack poseStack, int p_93453_, int p_93454_, int p_93455_, int p_93456_, float p_93457_) {
        super.renderList(poseStack, p_93453_, p_93454_, p_93455_, p_93456_, p_93457_);
    }
}