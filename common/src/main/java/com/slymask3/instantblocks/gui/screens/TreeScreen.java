package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.TreeBlockEntity;
import com.slymask3.instantblocks.block.instant.InstantTreeBlock;
import com.slymask3.instantblocks.network.packet.TreePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TreeScreen extends InstantScreen {
	protected final TreeBlockEntity tileEntity;

	private TreeList treeList;
    private int selected = -1;
    private final int[] trees = {0, 1, 2, 3, 4, 5, 6};

	private Checkbox fullLog, fullLeaves, air;

	public TreeScreen(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "ib.gui.tree.title");
		this.tileEntity = (TreeBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
	}

	@Override
	public void init() {
		super.init();

		this.fullLog = new Checkbox(this.width / 2 + 4, 50, 150, 20, Component.translatable("ib.gui.tree.logs"), tileEntity.fullLog) {
			public void onPress() {
				super.onPress();
				tileEntity.fullLog = this.selected();
			}
		};
		this.fullLeaves = new Checkbox(this.width / 2 + 4, 72, 150, 20, Component.translatable("ib.gui.tree.leaves"), tileEntity.fullLeaves) {
			public void onPress() {
				super.onPress();
				tileEntity.fullLeaves = this.selected();
			}
		};
		this.air = new Checkbox(this.width / 2 + 4, 94, 150, 20, Component.translatable("ib.gui.tree.air"), tileEntity.air) {
			public void onPress() {
				super.onPress();
				tileEntity.air = this.selected();
			}
		};

		this.treeList = new TreeList(this.width / 2 - 4 - 150,50,144,120);
		this.addWidget(this.treeList);

		this.done.active = false;

		this.addRenderableWidget(this.fullLog);
		this.addRenderableWidget(this.fullLeaves);
		this.addRenderableWidget(this.air);
		this.addRenderableWidget(this.treeList);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.tree.options"), this.width / 2 + 4, 40, 10526880);
		this.font.draw(poseStack, Component.translatable("ib.gui.tree.type"), this.width / 2 - 2 - 150, 40, 10526880);
	}

	public void sendInfo() {
		Common.NETWORK.sendToServer(new TreePacket(this.x, this.y, this.z, trees[selected], !fullLog.selected(), !fullLeaves.selected(), air.selected()));
	}

    public void setSelected(int index) {
        this.selected = index;
		this.done.active = index >= 0 && index < this.trees.length;
    }

	public int getSelected() {
		return this.selected;
	}

	//@OnlyIn(Dist.CLIENT)
	class TreeList extends ObjectSelectionList<TreeList.Entry> {
		public TreeList(int x, int y, int width, int height) {
			super(TreeScreen.this.minecraft, width, height, y, y + height, 18);
			this.setLeftPos(x);
			this.setRenderHeader(false, 0);
			this.setRenderTopAndBottom(false);
			this.setRenderSelection(true);
			for(int tree : TreeScreen.this.trees) {
				Entry entry = new Entry(tree);
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
			return TreeScreen.this.getFocused() == this;
		}

		//@OnlyIn(Dist.CLIENT)
		public class Entry extends ObjectSelectionList.Entry<Entry> {
			final int index;

			public Entry(int index) {
				this.index = index;
			}

			public void render(PoseStack poseStack, int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTick) {
				if(top > TreeList.this.y0 - 5 && top + entryHeight < TreeList.this.y1 + 5) {
					String string = InstantTreeBlock.treeToString(index, Minecraft.getInstance().player);
					TreeScreen.this.font.drawShadow(poseStack, string, (float)(left + entryWidth / 2 - TreeScreen.this.font.width(string) / 2), top, TreeScreen.this.getSelected() == this.index ? 0x00AA00 : 16777215, true);
				}
			}

			public boolean mouseClicked(double p_96122_, double p_96123_, int p_96124_) {
				if (p_96124_ == 0) {
					TreeScreen.this.setSelected(this.index);
					return true;
				} else {
					return false;
				}
			}

			public Component getNarration() {
				return Component.translatable("narrator.select", InstantTreeBlock.treeToString(index, Minecraft.getInstance().player));
			}
		}
	}
}