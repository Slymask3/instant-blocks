package com.slymask3.instantblocks.gui.instant;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.PacketSchematic;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.File;
import java.util.ArrayList;

public class GuiSchematic extends GuiInstant {
	private final TileEntitySchematic tileEntity;

	private SchematicList schematicList;
    private int selected = -1;
    private ArrayList<String> schematics;

	private EditBox input;
	private Checkbox center, ignoreAir;

	public GuiSchematic(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "Instant Schematic");
		this.tileEntity = (TileEntitySchematic)world.getBlockEntity(new BlockPos(x,y,z));
		this.schematics = SchematicHelper.getSchematics();
		for(String name : schematics) {
			InstantBlocks.LOGGER.info("schematic: " + name);
		}
	}

	@Override
	public void init() {
		super.init();

		this.center = new Checkbox(this.width / 2 - 4 - 150, this.height / 4 + 4 + 12, 150, 20, new TextComponent("Generate from center"), false);
		this.ignoreAir = new Checkbox(this.width / 2 + 4, this.height / 4 + 4 + 12, 150, 20, new TextComponent("Ignore generating air"), false);

		this.input = new EditBox(this.font, this.width / 2 - 4 - 150, 50, 300+8, 20, new TextComponent("Input")) {
			@Override
			public void insertText(String textToWrite) {
				super.insertText(textToWrite);
				GuiSchematic.this.checkForSchematic();
			}

			@Override
			public void deleteChars(int pNum) {
				super.deleteChars(pNum);
				GuiSchematic.this.checkForSchematic();
			}
		};
		this.input.setFocus(true);

		this.schematicList = new SchematicList(this.width / 2 - 4 - 150, this.height / 4 + 42 + 12, 300+8, 62);
		this.addWidget(this.schematicList);

		this.done.active = false;

		this.addRenderableWidget(this.center);
		this.addRenderableWidget(this.ignoreAir);
		this.addRenderableWidget(this.input);
		this.addRenderableWidget(this.schematicList);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "Enter a Schematic File Name:", this.width / 2 - 4 - 150, 37, 10526880);

		this.font.draw(poseStack, "File: '.minecraft/schematics/"+input.getValue()+"'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), this.checkForSchematic() ? 0x00FF00 : 0xAA0000);

		this.font.draw(poseStack, "Found " + this.schematics.size() + " schematic files:", this.width / 2 - 2 - 150, this.height / 4 + 31 + 12, 0xFFFFFF);

		if(this.schematics.size() == 0) {
			this.font.draw(poseStack, "Put .schem files into the 'schematics' folder in .minecraft.", this.width / 2 - 3 - 150, this.height / 4 +60 + 12, 0xAA0000);
			this.font.draw(poseStack, "If the 'schematics' folder doesn't exit, create it.", this.width / 2 - 3 - 150, this.height / 4 +70 + 12, 0xAA0000);
			this.font.draw(poseStack, "Files will update here right away, no need to restart.", this.width / 2 - 3 - 150, this.height / 4 +80 + 12, 0xAA0000);
		}

		//background behind found files
		//this.drawRect(this.width / 2 - 4 - 150, this.height / 4 + 29 + 12, this.width / 2 - 4 - 150 +(300+8), this.height / 4 + 29 + 12 +(13), -16777216);

		//background behind schematic file
		//this.drawRect(this.width / 2 - 4 - 150, this.height / 4 + 29 + 12 +(75), this.width / 2 - 4 - 150 +(300+8), this.height / 4 + 29 + 12 +(75+13), -16777216);
	}
	
	public void sendInfo() {
		PacketHandler.sendToServer(new PacketSchematic(this.x, this.y, this.z, this.input.getValue(), !center.selected(), !ignoreAir.selected()));
	}

	public boolean checkForSchematic() {
		File file = new File(Reference.SCHEMATICS_DIR + "/" + input.getValue());
		GuiSchematic.this.done.active = file.isFile();
		return file.isFile();
	}

    public void setSelected(int index) {
        this.selected = index;
        if(index>=0 && index<=schematics.size()) {
            this.input.setValue(schematics.get(selected));
        }
		this.checkForSchematic();
		SchematicHelper.Schematic schematic = SchematicHelper.readSchematic(schematics.get(selected));
    }

	public int getSelected() {
		return this.selected;
	}

	@OnlyIn(Dist.CLIENT)
	class SchematicList extends ObjectSelectionList<GuiSchematic.SchematicList.Entry> {
		public SchematicList(int x, int y, int width, int height) {
			super(GuiSchematic.this.minecraft, width, height, y, y + height, 18);
			this.setLeftPos(x);
			this.setRenderHeader(false, 0);
			this.setRenderTopAndBottom(false);
			this.setRenderSelection(true);
			//this.setRenderBackground(false);
			for(int i=0; i<GuiSchematic.this.schematics.size(); i++) {
				GuiSchematic.SchematicList.Entry entry = new GuiSchematic.SchematicList.Entry(i);
				this.addEntry(entry);
			}
			if (this.getSelected() != null) {
				this.centerScrollOn(this.getSelected());
			}
		}

		protected int getScrollbarPosition() {
			return this.x0 + this.width;
		}

		protected void renderList(PoseStack p_93452_, int p_93453_, int p_93454_, int p_93455_, int p_93456_, float p_93457_) {
			super.renderList(p_93452_, p_93453_, p_93454_, p_93455_, p_93456_, p_93457_);

			Tesselator tesselator = Tesselator.getInstance();
			BufferBuilder bufferbuilder = tesselator.getBuilder();

			RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
			//RenderSystem.setShaderTexture(0, GuiComponent.BACKGROUND_LOCATION);
			RenderSystem.enableDepthTest();
			RenderSystem.depthFunc(515);
			RenderSystem.disableDepthTest();
			RenderSystem.enableBlend();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
			RenderSystem.disableTexture();
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex((double)this.x0, (double)(this.y0 + 4), 0.0D).color(0, 0, 0, 0).endVertex();
			bufferbuilder.vertex((double)this.x1, (double)(this.y0 + 4), 0.0D).color(0, 0, 0, 0).endVertex();
			bufferbuilder.vertex((double)this.x1, (double)this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex((double)this.x0, (double)this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex((double)this.x0, (double)this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex((double)this.x1, (double)this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex((double)this.x1, (double)(this.y1 - 4), 0.0D).color(0, 0, 0, 0).endVertex();
			bufferbuilder.vertex((double)this.x0, (double)(this.y1 - 4), 0.0D).color(0, 0, 0, 0).endVertex();
			tesselator.end();

			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex((double)this.x0, (double)(this.y0 - 10), 0.0D).color(255, 255, 255, 0).endVertex();
			bufferbuilder.vertex((double)this.x1, (double)(this.y0 - 10), 0.0D).color(255, 255, 255, 0).endVertex();
			bufferbuilder.vertex((double)this.x1, (double)(this.y0), 0.0D).color(255, 255, 255, 0).endVertex();
			bufferbuilder.vertex((double)this.x0, (double)(this.y0), 0.0D).color(255, 255, 255, 0).endVertex();
			tesselator.end();
		}

		protected boolean isFocused() {
			return GuiSchematic.this.getFocused() == this;
		}

		@OnlyIn(Dist.CLIENT)
		public class Entry extends ObjectSelectionList.Entry<GuiSchematic.SchematicList.Entry> {
			final int index;

			public Entry(int index) {
				this.index = index;
			}

			public void render(PoseStack poseStack, int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTick) {
				if(top > GuiSchematic.SchematicList.this.y0 - 5 && top + entryHeight < GuiSchematic.SchematicList.this.y1 + 5) {
					String string = GuiSchematic.this.schematics.get(index);
					GuiSchematic.this.font.drawShadow(poseStack, string, (float)(left + entryWidth / 2 - GuiSchematic.this.font.width(string) / 2), top, GuiSchematic.this.getSelected() == this.index ? 0x00AA00 : 16777215, true);
				}
			}

			public boolean mouseClicked(double p_96122_, double p_96123_, int p_96124_) {
				InstantBlocks.LOGGER.info("mouseClicked: " + p_96124_);
				if (p_96124_ == 0) {
					GuiSchematic.this.setSelected(this.index);
					return true;
				} else {
					return false;
				}
			}

			public Component getNarration() {
				return new TranslatableComponent("narrator.select", GuiSchematic.this.schematics.get(index));
			}
		}
	}
}