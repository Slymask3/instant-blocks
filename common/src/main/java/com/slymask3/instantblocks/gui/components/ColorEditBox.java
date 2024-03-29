package com.slymask3.instantblocks.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.util.ColorHelper;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ColorEditBox extends EditBox {
    private final int index;
    private final Font font;

    public ColorEditBox(Font font, int x, int y, int width, int height, int index) {
        super(font, x, y, width, height, Component.translatable("ib.gui.skydive.color",(index+1)));
        this.font = font;
        this.index = index;
    }

    public Color getColor() {
        return ColorHelper.textToColor(getValue());
    }

    public void renderLabel(PoseStack poseStack) {
        if(!this.getValue().isEmpty()) {
            this.font.drawShadow(poseStack, Component.translatable("ib.gui.skydive.color",(index+1)), this.x - 50, this.y + 3, ColorHelper.textToColor(this.getValue()).getRGB());
        }
    }

    public void setRandomHex() {
        setValue(createRandomHex());
    }

    private String createRandomHex() {
        Color color = ColorHelper.generateRandomColor();
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public Button getClearButton() {
        return new ClearButton(this.x + this.width + 5, this.y + 1, (onPress) -> {
            this.setValue("");
        });
    }

    public static class ClearButton extends Button {
        public ClearButton(int x, int y, OnPress onPress) {
            super(x,y,12,12,Component.literal("X"),onPress);
        }

        public void renderButton(PoseStack p_86777_, int p_86778_, int p_86779_, float p_86780_) {
            RenderSystem.setShaderTexture(0, new ResourceLocation("realms", "textures/gui/realms/cross_icon.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            float f = this.isHoveredOrFocused() ? 12.0F : 0.0F;
            blit(p_86777_, this.x, this.y, 0.0F, f, 12, 12, 12, 24);
        }
    }
}