package com.slymask3.instantblocks.gui.instant;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.block.instant.BlockInstantStatue;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.PacketStatue;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GuiStatue extends GuiInstant {
	private EditBox input;
	private Checkbox head, body, armLeft, armRight, legLeft, legRight;
	private Checkbox rgbMode;
	private final TileEntityStatue tileEntity;

	public GuiStatue(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "Instant Statue");
		this.tileEntity = (TileEntityStatue)world.getBlockEntity(new BlockPos(x,y,z));
	}

	@Override
	public void init() {
		super.init();

		int x_left = this.width / 2 - 4 - 150;
		int x_right = this.width / 2 + 4;
		int y = this.height / 4 + 32;
		int slot = 22;

		this.head = new Checkbox(x_left, y, 150, 20, new TextComponent("Head"), true);
		this.body = new Checkbox(x_right, y, 150, 20, new TextComponent("Body"), true);
		this.armLeft = new Checkbox(x_left, y+(slot), 150, 20, new TextComponent("Arm (Left)"), true);
		this.armRight = new Checkbox(x_right, y+(slot), 150, 20, new TextComponent("Arm (Right)"), true);
		this.legLeft = new Checkbox(x_left, y+(slot*2), 150, 20, new TextComponent("Leg (Left)"), true);
		this.legRight = new Checkbox(x_right, y+(slot*2), 150, 20, new TextComponent("Leg (Right)"), true);
		this.rgbMode = new Checkbox(x_left, y+(slot*3), 150, 20, new TextComponent("RGB Mode"), true);

		this.input = new EditBox(this.font, this.width / 2 - 4 - 150, 50, 300+8, 20, new TextComponent("Input"));

		this.addRenderableWidget(this.head);
		this.addRenderableWidget(this.body);
		this.addRenderableWidget(this.armLeft);
		this.addRenderableWidget(this.armRight);
		this.addRenderableWidget(this.legLeft);
		this.addRenderableWidget(this.legRight);
		this.addRenderableWidget(this.rgbMode);
		this.addRenderableWidget(this.input);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "Enter a Minecraft Username:", this.width / 2 - 4 - 150, 37, 10526880);
		this.font.draw(poseStack, "Select parts to generate:", this.width / 2 - 3 - 150, this.height / 4 + 8 + 12, 10526880);
		this.font.draw(poseStack, "Unchecking RGB Mode will use vanilla minecraft wool colors.", this.width / 2 - 3 - 150, this.height / 4 + 32 + 88, 10526880);

	}
	
	public void sendInfo() {
		PacketHandler.sendToServer(new PacketStatue(this.x, this.y, this.z, input.getValue(), head.selected(), body.selected(), armLeft.selected(), armRight.selected(), legLeft.selected(), legRight.selected(), rgbMode.selected()));

		BlockInstantStatue block = (BlockInstantStatue) BuildHelper.getBlock(world,x,y,z);
		if(block.getImage(input.getValue()) != null) {
			block.setCreateMessage(Strings.CREATE_STATUE.replace("%username%",input.getValue()));
			block.afterBuild(world,x,y,z,player);
		} else {
			IBHelper.msg(player, Strings.ERROR_STATUE.replace("%username%",input.getValue()), Colors.c);
		}
	}
}