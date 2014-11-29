package com.slymask3.instantblocks.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntityInstantCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInstantCraft extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/instantcraft.png");

    public GuiInstantCraft(EntityPlayer player, TileEntityInstantCraft e, World world, int x, int y, int z) {
        super(new ContainerInstantCraft(player.inventory, world, x, y, z));
        this.xSize = 256;
        this.ySize = 222;
    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }
    
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        this.fontRendererObj.drawString("Instant Blocks", 7, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 85, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 49, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}