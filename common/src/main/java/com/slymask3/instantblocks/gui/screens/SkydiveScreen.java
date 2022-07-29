package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.SkydiveBlockEntity;
import com.slymask3.instantblocks.config.entry.ColorSet;
import com.slymask3.instantblocks.gui.components.ColorEditBox;
import com.slymask3.instantblocks.network.packet.SkydivePacket;
import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;


public class SkydiveScreen extends InstantScreen {
	private final ColorEditBox[] color = new ColorEditBox[11];
	private final Button[] colorClear = new Button[11];
	private Checkbox teleport;
	private EditBox radius;
	private Button preset;
	private final List<ColorSet> colorSets;
	private int colorSetsIndex;

	public SkydiveScreen(Player player, Level world, BlockPos pos) {
		super(player, world, pos, "ib.gui.skydive.title");
		this.colorSets = ClientHelper.SKYDIVE_PRESETS;
	}

	@Override
	public void init() {
		super.init();

		SkydiveBlockEntity blockEntity = (SkydiveBlockEntity)world.getBlockEntity(pos);

		this.colorSetsIndex = blockEntity.colorSetsIndex < colorSets.size() ? blockEntity.colorSetsIndex : 0;

		Button random = new Button(this.width / 2 + 4, this.height / 4 + 111, 150, 20, Component.translatable("ib.gui.skydive.random"), (p_88642_) -> {
			this.setRandom();
		});

		this.teleport = new Checkbox(this.width / 2 + 4, this.height / 4 + 90, 150, 20, Component.translatable("ib.gui.skydive.teleport"), blockEntity.teleport);

		EditBox radiusField = new EditBox(this.font, this.width / 2 + 4 + 50, 135, 60, 14, Component.literal("radius")) {
			public void insertText(String textToWrite) {
				if(Character.isDigit(textToWrite.charAt(0)) && this.getValue().length() < 4) {
					super.insertText(textToWrite);
				}
			}
		};
		radiusField.setValue(String.valueOf(blockEntity.radius));
		this.radius = radiusField;

		int cutoff = 6;
		int color_x, color_y;
		for(int i=0; i<color.length; i++) {
			if(i < cutoff) {
				color_x = this.width / 2 - 4 - 100;
				color_y = 45 + (18*i);
			} else {
				color_x = this.width / 2 + 4 + 50;
				color_y = 45 + (18*(i- cutoff));
			}
			this.color[i] = new ColorEditBox(this.font, color_x, color_y, 60, 14, i);
			this.colorClear[i] = this.color[i].getClearButton();

			this.color[i].setValue(blockEntity.color[i]);

			this.addRenderableWidget(this.color[i]);
			this.addRenderableWidget(this.colorClear[i]);
		}

		String colorSetName = this.colorSetsIndex >= 0 && this.colorSetsIndex < this.colorSets.size() ? this.colorSets.get(this.colorSetsIndex).name : Component.translatable("ib.gui.skydive.preset.none").getString();
		this.preset = new Button(this.width / 2 - 4 - 150, this.height / 4 + 111, 150, 20, Component.translatable("ib.gui.skydive.preset",colorSetName), (p_88642_) -> {
			this.nextPreset();
			this.setPreset();
		});
		this.preset.active = this.colorSets.size() > 0;

		this.addRenderableWidget(random);
		this.addRenderableWidget(this.teleport);
		this.addRenderableWidget(this.radius);
		this.addRenderableWidget(this.preset);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.skydive.input"), this.width / 2 - 4 - 150, 33, 0xA0A0A0);
		this.font.draw(poseStack, Component.translatable("ib.gui.skydive.radius"), this.width / 2 + 4, 138, 0xE0E0E0);

		for(ColorEditBox colorEditBox : color) {
			colorEditBox.renderLabel(poseStack);
		}
	}

	public void sendInfo(boolean activate) {
		int radius;
		try {
			radius = Integer.parseInt(this.radius.getValue());
		} catch (NumberFormatException e) {
			radius = Common.CONFIG.SKYDIVE_RADIUS();
		}
		String[] colors = getColors();
		Common.NETWORK.sendToServer(new SkydivePacket(activate, this.pos, colors, radius, teleport.selected(), this.colorSetsIndex));
	}
	
	private String[] getColors() {
		ArrayList<String> colorsBuilder = new ArrayList<>();
		for(ColorEditBox colorEditBox : this.color) {
			if(!colorEditBox.getValue().equals("")) {
				colorsBuilder.add(colorEditBox.getValue());
			}
		}
		return colorsBuilder.toArray(new String[0]);
	}

	private void setRandom() {
		for(ColorEditBox colorEditBox : color) {
			colorEditBox.setRandomHex();
		}
	}

	private void nextPreset() {
		this.colorSetsIndex = ++this.colorSetsIndex == colorSets.size() ? 0 : this.colorSetsIndex;
	}

	private void setPreset() {
		if(this.colorSets.size() > 0) {
			ColorSet colorSet = this.colorSets.get(this.colorSetsIndex);
			this.preset.setMessage(Component.translatable("ib.gui.skydive.preset",colorSet.name));
			for(int i=0; i<this.color.length; i++) {
				this.color[i].setValue(i < colorSet.colors.size() ? colorSet.colors.get(i) : "");
			}
		} else {
			this.preset.setMessage(Component.translatable("ib.gui.skydive.preset",Component.translatable("ib.gui.skydive.preset.none").getString()));
			for(ColorEditBox colorEditBox : this.color) {
				colorEditBox.setValue("");
			}
		}
	}
}