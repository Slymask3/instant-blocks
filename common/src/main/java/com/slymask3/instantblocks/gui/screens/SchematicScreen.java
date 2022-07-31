package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.SchematicBlockEntity;
import com.slymask3.instantblocks.gui.components.SelectionList;
import com.slymask3.instantblocks.network.packet.SchematicPacket;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.Util;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SchematicScreen extends InstantScreen {
	private SchematicList schematicList;
    private int selected = -1;
    private final ArrayList<String> schematics;

	private EditBox input;
	private Checkbox center, ignoreAir;

	public SchematicScreen(Player player, Level world, BlockPos pos) {
		super(player, world, pos, "ib.gui.schematic.title");
		this.schematics = SchematicHelper.SCHEMATICS_LIST;
	}

	@Override
	public void init() {
		super.init();

		SchematicBlockEntity blockEntity = (SchematicBlockEntity)world.getBlockEntity(pos);

		Button open = new Button(this.width / 2 + 134, 50, 20, 20, Component.literal(">"), (p_88642_) -> {
			Util.getPlatform().openFile(new File(SchematicHelper.SCHEMATICS_DIR));
		}, new Button.OnTooltip() {
			public void onTooltip(Button button, PoseStack poseStack, int x, int y) {
				SchematicScreen.this.renderTooltip(poseStack, List.of(Component.translatable("ib.gui.schematic.open").getVisualOrderText()), x, y);
			}
			public void narrateTooltip(Consumer<Component> consumer) {
				consumer.accept(Component.translatable("ib.gui.schematic.open"));
			}
		});

		this.center = new Checkbox(this.width / 2 - 4 - 150, 75, 150, 20, Component.translatable("ib.gui.schematic.center"), blockEntity.center);
		this.ignoreAir = new Checkbox(this.width / 2 + 4, 75, 150, 20, Component.translatable("ib.gui.schematic.ignore"), blockEntity.ignoreAir);

		this.input = new EditBox(this.font, this.width / 2 - 4 - 150, 50, 284, 20, Component.literal("Input")) {
			public void insertText(String textToWrite) {
				super.insertText(textToWrite);
				SchematicScreen.this.checkForSchematic();
			}
			public void deleteChars(int pNum) {
				super.deleteChars(pNum);
				SchematicScreen.this.checkForSchematic();
			}
		};
		this.input.setValue(blockEntity.schematic);

		this.schematicList = new SchematicList(this.width / 2 - 4 - 150, 111, 302, this.height / 4);
		this.addWidget(this.schematicList);

		this.done.active = false;

		this.addRenderableWidget(this.center);
		this.addRenderableWidget(this.ignoreAir);
		this.addRenderableWidget(this.input);
		this.addRenderableWidget(this.schematicList);
		this.addRenderableWidget(open);

		this.setInitialFocus(this.input);

		this.checkForSchematic();
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.input"), this.width / 2 - 4 - 150, 37, 0xA0A0A0);
		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.input"), this.width / 2 - 4 - 150, 37, 0xA0A0A0);

		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.file", input.getValue()), this.width / 2 - 2 - 150, this.height / 4 + 115, this.done.active ? 0x00FF00 : 0xAA0000);

		this.font.draw(poseStack, Component.translatable("ib.gui.schematic.found", this.schematics.size()), this.width / 2 - 2 - 150, 100, 0xFFFFFF);

		if(this.schematics.size() == 0) {
			this.font.draw(poseStack, Component.translatable("ib.gui.schematic.instructions.1"), this.width / 2 - 3 - 150, 120, 0xAA0000);
			this.font.draw(poseStack, Component.translatable("ib.gui.schematic.instructions.2"), this.width / 2 - 3 - 150, 130, 0xAA0000);
			this.font.draw(poseStack, Component.translatable("ib.gui.schematic.instructions.3"), this.width / 2 - 3 - 150, 140, 0xAA0000);
		}
	}
	
	public void sendInfo(boolean activate) {
		Common.NETWORK.sendToServer(new SchematicPacket(activate, this.pos, this.input.getValue(), center.selected(), ignoreAir.selected()));
	}

	public void checkForSchematic() {
		this.done.active = this.schematics.contains(input.getValue());
		if(this.done.active) {
			for(int i = 0; i< this.schematics.size(); i++) {
				if(this.schematics.get(i).equals(input.getValue()) && this.selected != i) {
					this.selected = i;
					break;
				}
			}
		} else {
			this.selected = -1;
		}
		this.schematicList.setSelectedSchematic(this.selected);
	}

    public void clickOnSchematic(int index) {
        this.selected = index;
        if(index>=0 && index<=schematics.size()) {
            this.input.setValue(schematics.get(selected));
        }
		this.checkForSchematic();
    }

	public int getSelected() {
		return this.selected;
	}

	class SchematicList extends SelectionList<SchematicList.Entry> {
		public SchematicList(int x, int y, int width, int height) {
			super(SchematicScreen.this.minecraft, x, y, width, height, 18);
			for(int i = 0; i< SchematicScreen.this.schematics.size(); i++) {
				Entry entry = new Entry(i);
				this.addEntry(entry);
			}
			if(this.getSelected() != null) {
				this.centerScrollOn(this.getSelected());
			}
		}

		protected boolean isFocused() {
			return SchematicScreen.this.getFocused() == this;
		}

		public void setSelectedSchematic(int index) {
			if(index > 0 && index < SchematicScreen.this.schematics.size()) {
				this.setSelected(this.getEntry(index));
				this.centerScrollOn(this.getSelected());
			} else {
				this.setSelected(null);
			}
		}

		class Entry extends ObjectSelectionList.Entry<Entry> {
			final int index;

			public Entry(int index) {
				this.index = index;
			}

			public void render(PoseStack poseStack, int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTick) {
				int entry_y = top + 4;
				if(entry_y > SchematicList.this.y0 - 5 && entry_y + entryHeight < SchematicList.this.y1 + 5) {
					String string = SchematicScreen.this.schematics.get(index);
					SchematicScreen.this.font.drawShadow(poseStack, string, (float)(left + entryWidth / 2 - SchematicScreen.this.font.width(string) / 2), entry_y, SchematicScreen.this.getSelected() == this.index ? 0x00AA00 : 16777215, true);
				}
			}

			public boolean mouseClicked(double p_96122_, double p_96123_, int p_96124_) {
				if(p_96124_ == 0) {
					SchematicScreen.this.clickOnSchematic(this.index);
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