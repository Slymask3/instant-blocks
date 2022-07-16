package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.SkydiveBlockEntity;
import com.slymask3.instantblocks.gui.components.ColorEditBox;
import com.slymask3.instantblocks.network.packet.SkydivePacket;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;


public class SkydiveScreen extends InstantScreen {
    private Button random;
	private final ColorEditBox[] color = new ColorEditBox[11];
	private final Button[] colorClear = new Button[11];
	private Checkbox tp;
	private EditBox radius;
	private final SkydiveBlockEntity tileEntity;

	public SkydiveScreen(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "ib.gui.skydive.title");
		this.tileEntity = (SkydiveBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
	}

	@Override
	public void init() {
		super.init();

		this.random = new Button(this.width / 2 + 4, this.height / 4 + 98 + 12, 150, 20, Component.translatable("ib.gui.skydive.random"), (p_88642_) -> {
			this.setRandom();
		});

		this.tp = new Checkbox(this.width / 2 + 4, this.height / 4 + 76 + 12, 150, 20, Component.translatable("ib.gui.skydive.teleport"), tileEntity.teleport) {
			public void onPress() {
				super.onPress();
				tileEntity.teleport = this.selected();
			}
		};

		EditBox radiusField = new EditBox(this.font, this.width / 2 - 4 - 100 - 12, this.height / 4 + 100 + 12, 110, 16, Component.literal("radius")) {
			public void insertText(String textToWrite) {
				if(Character.isDigit(textToWrite.charAt(0)) && this.getValue().length() < 4) {
					super.insertText(textToWrite);
				}
				tileEntity.radius = this.getValue().isEmpty() ? 0 : Integer.parseInt(this.getValue());
			}
			public void deleteChars(int pNum) {
				super.deleteChars(pNum);
				tileEntity.radius = this.getValue().isEmpty() ? 0 : Integer.parseInt(this.getValue());
			}
		};
		radiusField.setValue(String.valueOf(tileEntity.radius));
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
			this.color[i] = new ColorEditBox(this.font, color_x, color_y, 60, 14, i, tileEntity);
			this.colorClear[i] = this.color[i].getClearButton();

			this.addRenderableWidget(this.color[i]);
			this.addRenderableWidget(this.colorClear[i]);
		}

		this.addRenderableWidget(this.random);
		this.addRenderableWidget(this.tp);
		this.addRenderableWidget(this.radius);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.skydive.input"), this.width / 2 - 4 - 150, 33, 10526880); //aarrggbb
		this.font.draw(poseStack, Component.translatable("ib.gui.skydive.radius"), this.width / 2 - 4 - 150, this.height / 4 + 104 + 12,14737632);

		for(ColorEditBox colorEditBox : color) {
			colorEditBox.renderLabel(poseStack);
		}
	}
	
	public void sendInfo() {
		int radius;
		try {
			radius = Integer.parseInt(this.radius.getValue());
			//Common.CONFIG.SKYDIVE_RADIUS().set(radius);
		} catch (NumberFormatException e) {
			radius = Common.CONFIG.SKYDIVE_RADIUS();
		}
		int[] colors = getColors();
		Common.NETWORK.sendToServer(new SkydivePacket(this.x, this.y, this.z, colors.length, colors, radius, tp.selected()));
	}
	
	private int[] getColors() {
		ArrayList<Integer> colorsBuilder = new ArrayList<>();
		for(ColorEditBox colorEditBox : this.color) {
			if(!colorEditBox.getValue().equals("")) {
				colorsBuilder.add(colorEditBox.getColor().getRGB());
			}
		}
		Integer[] arr = colorsBuilder.toArray(new Integer[0]);
		return ArrayUtils.toPrimitive(arr);
	}

	private void setRandom() {
		for(ColorEditBox colorEditBox : color) {
			colorEditBox.setRandomHex();
		}
	}
}