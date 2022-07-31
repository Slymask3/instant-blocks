package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.TreeBlockEntity;
import com.slymask3.instantblocks.config.entry.HugeTree;
import com.slymask3.instantblocks.gui.components.SelectionList;
import com.slymask3.instantblocks.network.packet.TreePacket;
import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class TreeScreen extends InstantScreen {
	private Checkbox hollowLogs, hollowLeaves, airInside;
	private TreeList treeList;

	private final List<HugeTree> hugeTrees;
	private int hugeTreesIndex;

	public TreeScreen(Player player, Level world, BlockPos pos) {
		super(player, world, pos, "ib.gui.tree.title");
		this.hugeTrees = ClientHelper.HUGE_TREES;
	}

	@Override
	public void init() {
		super.init();

		TreeBlockEntity blockEntity = (TreeBlockEntity)world.getBlockEntity(pos);

		this.hugeTreesIndex = blockEntity.hugeTreesIndex < hugeTrees.size() ? blockEntity.hugeTreesIndex : 0;

		this.hollowLogs = new Checkbox(this.width / 2 + 4, 50, 150, 20, Component.translatable("ib.gui.tree.logs"), blockEntity.hollowLogs);
		this.hollowLeaves = new Checkbox(this.width / 2 + 4, 72, 150, 20, Component.translatable("ib.gui.tree.leaves"), blockEntity.hollowLeaves);
		this.airInside = new Checkbox(this.width / 2 + 4, 94, 150, 20, Component.translatable("ib.gui.tree.air"), blockEntity.airInside);

		this.treeList = new TreeList(this.width / 2 - 4 - 150,50,144,this.height / 4 + 70);
		this.addWidget(this.treeList);

		this.addRenderableWidget(this.hollowLogs);
		this.addRenderableWidget(this.hollowLeaves);
		this.addRenderableWidget(this.airInside);
		this.addRenderableWidget(treeList);

		this.setSelected(this.hugeTrees.get(this.hugeTreesIndex));
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.tree.options"), this.width / 2 + 4, 40, 0xA0A0A0);
		this.font.draw(poseStack, Component.translatable("ib.gui.tree.type"), this.width / 2 - 2 - 150, 40, 0xA0A0A0);
	}

	public void sendInfo(boolean activate) {
		Common.NETWORK.sendToServer(new TreePacket(activate, this.pos, this.hugeTrees.get(this.hugeTreesIndex), this.hollowLogs.selected(), this.hollowLeaves.selected(), this.airInside.selected(), this.hugeTreesIndex));
	}

    public void setSelected(HugeTree tree) {
		int index = -1;
		for(int i=0; i<this.hugeTrees.size(); i++) {
			if(this.hugeTrees.get(i) == tree) {
				index = i;
				break;
			}
		}
        this.hugeTreesIndex = index;
		this.treeList.setSelectedTree(index);
		this.done.active = index >= 0 && index < this.hugeTrees.size();
    }

	public HugeTree getSelected() {
		return this.hugeTreesIndex >= 0 && this.hugeTreesIndex < this.hugeTrees.size()  ? this.hugeTrees.get(this.hugeTreesIndex) : null;
	}

	class TreeList extends SelectionList<TreeList.Entry> {
		public TreeList(int x, int y, int width, int height) {
			super(TreeScreen.this.minecraft, x, y, width, height, 18);
			for(HugeTree tree : TreeScreen.this.hugeTrees) {
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

		public void setSelectedTree(int index) {
			if(index > 0 && index < TreeScreen.this.hugeTrees.size()) {
				this.setSelected(this.getEntry(index));
				this.centerScrollOn(this.getSelected());
			} else {
				this.setSelected(null);
			}
		}

		class Entry extends ObjectSelectionList.Entry<Entry> {
			final HugeTree tree;

			public Entry(HugeTree tree) {
				this.tree = tree;
			}

			public void render(PoseStack poseStack, int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTick) {
				int entry_y = top + 4;
				if(entry_y > TreeList.this.y0 - 5 && entry_y + entryHeight < TreeList.this.y1 + 5) {
					String string = tree.name;
					TreeScreen.this.font.drawShadow(poseStack, string, (float)(left + entryWidth / 2 - TreeScreen.this.font.width(string) / 2), entry_y, TreeScreen.this.getSelected() == this.tree ? 0x00AA00 : 16777215, true);
				}
			}

			public boolean mouseClicked(double p_96122_, double p_96123_, int p_96124_) {
				if(p_96124_ == 0) {
					TreeScreen.this.setSelected(this.tree);
					return true;
				} else {
					return false;
				}
			}

			public Component getNarration() {
				return Component.translatable("narrator.select", this.tree.name);
			}
		}
	}
}