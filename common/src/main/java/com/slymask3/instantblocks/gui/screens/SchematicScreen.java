package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.SchematicBlockEntity;
import com.slymask3.instantblocks.network.packet.SchematicPacket;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class SchematicScreen extends InstantScreen {
	private final SchematicBlockEntity tileEntity;

	private SchematicList schematicList;
    private int selected = -1;
    private final ArrayList<String> schematics;

	private EditBox input;
	private Checkbox center, ignoreAir;

	public SchematicScreen(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "ib.gui.schematic.title");
		this.tileEntity = (SchematicBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
		this.schematics = this.tileEntity.schematics;
	}

	@Override
	public void init() {
		super.init();

		this.center = new Checkbox(this.width / 2 - 4 - 150, 75, 150, 20, Component.translatable("ib.gui.schematic.center"), tileEntity.center) {
			public void onPress() {
				super.onPress();
				tileEntity.center = this.selected();
			}
		};
		this.ignoreAir = new Checkbox(this.width / 2 + 4, 75, 150, 20, Component.translatable("ib.gui.schematic.ignore"), tileEntity.ignoreAir) {
			public void onPress() {
				super.onPress();
				tileEntity.ignoreAir = this.selected();
			}
		};

		this.input = new EditBox(this.font, this.width / 2 - 4 - 150, 50, 300+8, 20, Component.literal("Input")) {
			@Override
			public void insertText(String textToWrite) {
				super.insertText(textToWrite);
				SchematicScreen.this.checkForSchematic();
			}

			@Override
			public void deleteChars(int pNum) {
				super.deleteChars(pNum);
				SchematicScreen.this.checkForSchematic();
			}
		};

		this.schematicList = new SchematicList(this.width / 2 - 4 - 150, 111, 302, this.height / 4);
		this.addWidget(this.schematicList);

		this.done.active = false;

		this.addRenderableWidget(this.center);
		this.addRenderableWidget(this.ignoreAir);
		this.addRenderableWidget(this.input);
		this.addRenderableWidget(this.schematicList);

		this.setInitialFocus(this.input);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.input"), this.width / 2 - 4 - 150, 37, 10526880);

		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.file", input.getValue()), this.width / 2 - 2 - 150, this.height / 4 + 115, this.checkForSchematic() ? 0x00FF00 : 0xAA0000);

		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.found", this.schematics.size()), this.width / 2 - 2 - 150, 100, 0xFFFFFF);

		if(this.schematics.size() == 0) {
			this.font.draw(poseStack, Component.translatable("ib.gui.schematic.instructions.1"), this.width / 2 - 3 - 150, 120, 0xAA0000);
			this.font.draw(poseStack, Component.translatable("ib.gui.schematic.instructions.2"), this.width / 2 - 3 - 150, 130, 0xAA0000);
			this.font.draw(poseStack, Component.translatable("ib.gui.schematic.instructions.3"), this.width / 2 - 3 - 150, 140, 0xAA0000);
		}
	}
	
	public void sendInfo() {
		Common.NETWORK.sendToServer(new SchematicPacket(this.x, this.y, this.z, this.input.getValue(), !center.selected(), !ignoreAir.selected()));
	}

	public boolean checkForSchematic() {
		this.done.active = this.schematics.contains(input.getValue());
		this.tileEntity.schematic = input.getValue();
		return this.done.active;
	}

    public void setSelected(int index) {
        this.selected = index;
        if(index>=0 && index<=schematics.size()) {
            this.input.setValue(schematics.get(selected));
        }
		this.checkForSchematic();
    }

	public int getSelected() {
		return this.selected;
	}

	//@OnlyIn(Dist.CLIENT)
	class SchematicList extends ObjectSelectionList<SchematicList.Entry> {
		public SchematicList(int x, int y, int width, int height) {
			super(SchematicScreen.this.minecraft, width, height, y, y + height, 18);
			this.setLeftPos(x);
			this.setRenderHeader(false, 0);
			this.setRenderTopAndBottom(false);
			this.setRenderSelection(true);
			//this.setRenderBackground(false);
			for(int i = 0; i< SchematicScreen.this.schematics.size(); i++) {
				Entry entry = new Entry(i);
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

		protected boolean isFocused() {
			return SchematicScreen.this.getFocused() == this;
		}

		//@OnlyIn(Dist.CLIENT)
		public class Entry extends ObjectSelectionList.Entry<Entry> {
			final int index;

			public Entry(int index) {
				this.index = index;
			}

			public void render(PoseStack poseStack, int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTick) {
				if(top > SchematicList.this.y0 - 5 && top + entryHeight < SchematicList.this.y1 + 5) {
					String string = SchematicScreen.this.schematics.get(index);
					SchematicScreen.this.font.drawShadow(poseStack, string, (float)(left + entryWidth / 2 - SchematicScreen.this.font.width(string) / 2), top, SchematicScreen.this.getSelected() == this.index ? 0x00AA00 : 16777215, true);
				}
			}

			public boolean mouseClicked(double p_96122_, double p_96123_, int p_96124_) {
				if (p_96124_ == 0) {
					SchematicScreen.this.setSelected(this.index);
					return true;
				} else {
					return false;
				}
			}

			public Component getNarration() {
				return Component.translatable("narrator.select", SchematicScreen.this.schematics.get(index));
			}
		}
	}
}