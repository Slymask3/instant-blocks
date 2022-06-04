package com.slymask3.instantblocks.gui.instant;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.PacketSkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.awt.*;


public class GuiSkydive extends GuiInstant {
    private Button random;
	private EditBox[] color = new EditBox[11];
	private Checkbox tp;
	private EditBox radius;

	private final int cutoff = 6;

	public GuiSkydive(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "Instant Skydive");
	}

	@Override
	public void init() {
		super.init();

		this.random = new Button(this.width / 2 + 4, this.height / 4 + 98 + 12, 150, 20, new TextComponent("Randomize"), (p_88642_) -> {
			this.setRandom();
		});

		this.tp = new Checkbox(this.width / 2 + 4, this.height / 4 + 83 + 12, 150, 10, new TextComponent("Teleport to top"), true);

		EditBox radiusField = new EditBox(this.font, this.width / 2 - 4 - 100 - 12, this.height / 4 + 100 + 12, 110, 16, new TextComponent("test"));
		radiusField.setValue(String.valueOf(Config.Common.SKYDIVE_RADIUS.get()));
		this.radius = radiusField;

		for(int i=0; i<cutoff; i++) {
			this.color[i] = new EditBox(this.font, this.width / 2 - 4 - 100 + 6, 45 + (18*i), 60, 14, new TextComponent("test"));
			//this.color[i].setValue(tileEntity.color[i]);
		}
		for(int i=cutoff; i<=10; i++) {
			this.color[i] = new EditBox(this.font, this.width / 2 + 4 + 50, 45 + (18*(i-cutoff)), 60, 14, new TextComponent("test"));
			//this.color[i].setValue(tileEntity.color[i]);
		}

		this.addRenderableWidget(this.random);
		this.addRenderableWidget(this.tp);
		this.addRenderableWidget(this.radius);
		for(int i=0; i<this.color.length; i++) {
			this.addRenderableWidget(this.color[i]);
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		poseStack.pushPose();
//		poseStack.translate(this.width / 2, this.height / 2 - WIDGET_HEIGHT / 2 - 2 * SPACING - 15, 0);
//		fill(poseStack, -SLIDER_WIDTH / 2, -WIDGET_HEIGHT, SLIDER_WIDTH / 2, WIDGET_HEIGHT, 0xFF000000);
//		fill(poseStack, -SLIDER_WIDTH / 2 + 1, -WIDGET_HEIGHT + 1, SLIDER_WIDTH / 2 - 1, WIDGET_HEIGHT - 1, getColor());
		poseStack.popPose();
	}

//	public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
//		super.keyPressed(p_96552_,p_96553_,p_96554_);
//		return !this.radius.keyPressed(p_96552_,p_96553_,p_96554_) && !this.radius.canConsumeInput() ? super.keyPressed(p_96552_,p_96553_,p_96554_) : true;
//	}

//	public void draw(int par1, int par2, float par3) {
//		this.drawCenteredString(this.fontRendererObj, "Instant Skydive Block", this.width / 2, 20, 16777215);
//		this.drawString(this.fontRendererObj, "Enter colors by name or hexadecimal value.", this.width / 2 - 4 - 150, 33, 10526880); //aarrggbb
//		this.drawString(this.fontRendererObj, "Radius:", this.width / 2 - 4 - 150, this.height / 4 + 104 + 12,10526880);
//
//		for(int i=0; i<cutoff; i++) {
//			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 - 4 - 150, 48 + (18*i), tileEntity.colorCode[i]);
//			this.color[i].drawTextBox();
//		}
//		for(int i=cutoff; i<=10; i++) {
//			this.drawString(this.fontRendererObj, "Color "+(i+1)+":", this.width / 2 + 4, 48 + (18*(i-cutoff)), tileEntity.colorCode[i]);
//			this.color[i].drawTextBox();
//		}
//
//		this.radius.drawTextBox();
//	}
	
	public void sendInfo() {
		int radius;
		try {
			radius = Integer.parseInt(this.radius.getValue());
		} catch (NumberFormatException e) {
			radius = Config.Common.SKYDIVE_RADIUS.get();
		}
		PacketHandler.sendToServer(new PacketSkydive(this.x, this.y, this.z, getColors(), radius, tp.isActive()));

		BlockInstant block = (BlockInstant) BuildHelper.getBlock(world,x,y,z);
		block.afterBuild(world,x,y,z,player);
	}
	
	private int[] getColors() {
		int[] colors = new int[this.color.length];
		for(int i=0; i<colors.length; i++) {
			colors[i] = Colors.textToColor(this.color[i].getValue()).getRGB();
		}
		return colors;
	}

	private void setRandom() {
		for(int i=0; i<=10; i++) {
			color[i].setValue(createRandomHex());
//			tileEntity.setColorCode(i, color[i].getText());
//			tileEntity.setColor(i, color[i].getText());
		}
	}

	private String createRandomHex() {
		Color color = Colors.generateRandomColor();
		return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
	}
}