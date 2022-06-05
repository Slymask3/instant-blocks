package com.slymask3.instantblocks.gui.module;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.Colors;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TextComponent;

import java.awt.*;

public class ColorEditBox extends EditBox {
    private int index;
    private TileEntitySkydive tileEntity;
    private Font font;

    public ColorEditBox(Font font, int x, int y, int width, int length, int index, TileEntitySkydive tileEntity) {
        super(font, x, y, width, length, new TextComponent("Color "+(index+1)+":"));
        this.font = font;
        this.index = index;
        this.tileEntity = tileEntity;
        this.setValue(tileEntity.color[index]);
    }

    public Color getColor() {
        return Colors.textToColor(getValue());
    }

    @Override
    public void insertText(String textToWrite) {
        super.insertText(textToWrite);
        tileEntity.setColorCode(index, getValue());
        tileEntity.setColor(index, getValue());
        InstantBlocks.LOGGER.info(getColor() + " - " + getColor().getRGB() + " - " + tileEntity.colorCode[index]);
    }

    @Override
    public void deleteChars(int pNum) {
        super.deleteChars(pNum);
        tileEntity.setColorCode(index, getValue());
        tileEntity.setColor(index, getValue());
        InstantBlocks.LOGGER.info(getColor() + " - " + getColor().getRGB() + " - " + tileEntity.colorCode[index]);
    }

    public void renderLabel(PoseStack poseStack, int mouseX, int mouseY) {
        this.font.drawShadow(poseStack, "Color "+(index+1)+":", this.x - 50, this.y + 3, tileEntity.colorCode[index]);
    }

    public void setRandomHex() {
        setValue(createRandomHex());
        tileEntity.setColorCode(index, getValue());
        tileEntity.setColor(index, getValue());
    }

    private String createRandomHex() {
        Color color = Colors.generateRandomColor();
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
