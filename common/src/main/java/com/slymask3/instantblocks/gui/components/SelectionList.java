package com.slymask3.instantblocks.gui.components;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;

public class SelectionList<T extends ObjectSelectionList.Entry<T>> extends ObjectSelectionList<T> {
    public SelectionList(Minecraft minecraft, int x, int y, int width, int height, int itemHeight) {
        super(minecraft, width, height, y, y + height, itemHeight);
        this.setLeftPos(x);
        this.setRenderHeader(false, 0);
        this.setRenderTopAndBottom(false);
        this.setRenderSelection(true);
        //this.setRenderBackground(false);
    }

    protected int getScrollbarPosition() {
        return this.x0 + this.width;
    }

    protected void renderList(PoseStack p_93452_, int p_93453_, int p_93454_, int p_93455_, int p_93456_, float p_93457_) {
        super.renderList(p_93452_, p_93453_, p_93454_, p_93455_, p_93456_, p_93457_);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
        RenderSystem.disableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(this.x0, this.y0 + 4, 0.0D).color(0, 0, 0, 0).endVertex();
        bufferbuilder.vertex(this.x1, this.y0 + 4, 0.0D).color(0, 0, 0, 0).endVertex();
        bufferbuilder.vertex(this.x1, this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(this.x0, this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(this.x0, this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(this.x1, this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
        bufferbuilder.vertex(this.x1, this.y1 - 4, 0.0D).color(0, 0, 0, 0).endVertex();
        bufferbuilder.vertex(this.x0, this.y1 - 4, 0.0D).color(0, 0, 0, 0).endVertex();
        tesselator.end();

        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferbuilder.vertex(this.x0, this.y0 - 10, 0.0D).color(255, 255, 255, 0).endVertex();
        bufferbuilder.vertex(this.x1, this.y0 - 10, 0.0D).color(255, 255, 255, 0).endVertex();
        bufferbuilder.vertex(this.x1, this.y0, 0.0D).color(255, 255, 255, 0).endVertex();
        bufferbuilder.vertex(this.x0, this.y0, 0.0D).color(255, 255, 255, 0).endVertex();
        tesselator.end();
    }
}