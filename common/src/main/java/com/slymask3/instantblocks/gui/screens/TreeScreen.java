package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.TreeBlockEntity;
import com.slymask3.instantblocks.block.instant.InstantTreeBlock;
import com.slymask3.instantblocks.gui.components.SelectionList;
import com.slymask3.instantblocks.network.packet.TreePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TreeScreen extends InstantScreen {
	private TreeList treeList;
    private int selected = -1;
    private final int[] trees = {0, 1, 2, 3, 4, 5, 6};

	private Checkbox fullLog, fullLeaves, air;

	public TreeScreen(Player player, Level world, BlockPos pos) {
		super(player, world, pos, "ib.gui.tree.title");
	}

	@Override
	public void init() {
		super.init();

		TreeBlockEntity blockEntity = (TreeBlockEntity)world.getBlockEntity(pos);

		this.fullLog = new Checkbox(this.width / 2 + 4, 50, 150, 20, Component.translatable("ib.gui.tree.logs"), blockEntity.fullLog);
		this.fullLeaves = new Checkbox(this.width / 2 + 4, 72, 150, 20, Component.translatable("ib.gui.tree.leaves"), blockEntity.fullLeaves);
		this.air = new Checkbox(this.width / 2 + 4, 94, 150, 20, Component.translatable("ib.gui.tree.air"), blockEntity.air);

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

	public void sendInfo(boolean activate) {
		Common.NETWORK.sendToServer(new TreePacket(activate, this.pos, trees[selected], !fullLog.selected(), !fullLeaves.selected(), air.selected()));
	}

    public void setSelected(int index) {
        this.selected = index;
		this.done.active = index >= 0 && index < this.trees.length;
    }

	public int getSelected() {
		return this.selected;
	}

	class TreeList extends SelectionList<TreeList.Entry> {
		public TreeList(int x, int y, int width, int height) {
			super(TreeScreen.this.minecraft, x, y, width, height, 18);
			for(int tree : TreeScreen.this.trees) {
				Entry entry = new Entry(tree);
				this.addEntry(entry);
			}
			if(this.getSelected() != null) {
				this.centerScrollOn(this.getSelected());
			}
		}

		protected boolean isFocused() {
			return TreeScreen.this.getFocused() == this;
		}

		class Entry extends ObjectSelectionList.Entry<Entry> {
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