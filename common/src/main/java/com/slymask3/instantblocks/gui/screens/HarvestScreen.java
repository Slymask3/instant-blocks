package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.HarvestBlockEntity;
import com.slymask3.instantblocks.network.packet.HarvestPacket;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HarvestScreen extends InstantScreen {
	private Checkbox logOak, logSpruce, logBirch, logJungle, logAcacia, logDark;
	private Checkbox wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart;
	private Checkbox replant;

	public HarvestScreen(Player player, Level world, BlockPos pos) {
		super(player, world, pos, "ib.gui.harvest.title");
		this.setDoneText("ib.gui.harvest.done");
	}

	@Override
	public void init() {
		super.init();

		HarvestBlockEntity blockEntity = (HarvestBlockEntity)world.getBlockEntity(pos);

		int x_left = this.width / 2 - 154;
		int x_middle = this.width / 2 - 52;
		int x_right = this.width / 2 + 50;
		int y = this.height / 4 - 8;
		int slot = 22;
		int check_width = 100;

		this.logOak = new Checkbox(x_left, y, check_width, 20, new TranslatableComponent("block.minecraft.oak_log"), blockEntity.logOak);
		this.logSpruce = new Checkbox(x_left, y+slot, check_width, 20, new TranslatableComponent("block.minecraft.spruce_log"), blockEntity.logSpruce);
		this.logBirch = new Checkbox(x_left, y+(slot*2), check_width, 20, new TranslatableComponent("block.minecraft.birch_log"), blockEntity.logBirch);
		this.logJungle = new Checkbox(x_left, y+(slot*3), check_width, 20, new TranslatableComponent("block.minecraft.jungle_log"), blockEntity.logJungle);
		this.logAcacia = new Checkbox(x_left, y+(slot*4), check_width, 20, new TranslatableComponent("block.minecraft.acacia_log"), blockEntity.logAcacia);
		this.logDark = new Checkbox(x_left, y+(slot*5), check_width, 20, new TranslatableComponent("block.minecraft.dark_oak_log"), blockEntity.logDark);

		this.wheat = new Checkbox(x_middle, y, check_width, 20, new TranslatableComponent("item.minecraft.wheat"), blockEntity.wheat);
		this.carrot = new Checkbox(x_middle, y+slot, check_width, 20, new TranslatableComponent("item.minecraft.carrot"), blockEntity.carrot);
		this.potato = new Checkbox(x_middle, y+(slot*2), check_width, 20, new TranslatableComponent("item.minecraft.potato"), blockEntity.potato);
		this.cactus = new Checkbox(x_middle, y+(slot*3), check_width, 20, new TranslatableComponent("block.minecraft.cactus"), blockEntity.cactus);
		this.pumpkin = new Checkbox(x_middle, y+(slot*4), check_width, 20, new TranslatableComponent("block.minecraft.pumpkin"), blockEntity.pumpkin);
		this.melon = new Checkbox(x_middle, y+(slot*5), check_width, 20, new TranslatableComponent("block.minecraft.melon"), blockEntity.melon);

		this.sugarcane = new Checkbox(x_right, y, check_width, 20, new TranslatableComponent("block.minecraft.sugar_cane"), blockEntity.sugarcane);
		this.cocoa = new Checkbox(x_right, y+slot, check_width, 20, new TranslatableComponent("block.minecraft.cocoa"), blockEntity.cocoa);
		this.mushroom = new Checkbox(x_right, y+(slot*2), check_width, 20, new TranslatableComponent("ib.gui.harvest.mushroom"), blockEntity.mushroom);
		this.netherwart = new Checkbox(x_right, y+(slot*3), check_width, 20, new TranslatableComponent("block.minecraft.nether_wart"), blockEntity.netherwart);
		this.replant = new Checkbox(x_right, y+(slot*4), check_width, 20, new TranslatableComponent("ib.gui.harvest.replant"), blockEntity.replant);

		this.addRenderableWidget(this.logOak);
		this.addRenderableWidget(this.logSpruce);
		this.addRenderableWidget(this.logBirch);
		this.addRenderableWidget(this.logJungle);
		this.addRenderableWidget(this.logAcacia);
		this.addRenderableWidget(this.logDark);
		this.addRenderableWidget(this.wheat);
		this.addRenderableWidget(this.carrot);
		this.addRenderableWidget(this.potato);
		this.addRenderableWidget(this.cactus);
		this.addRenderableWidget(this.pumpkin);
		this.addRenderableWidget(this.melon);
		this.addRenderableWidget(this.sugarcane);
		this.addRenderableWidget(this.cocoa);
		this.addRenderableWidget(this.mushroom);
		this.addRenderableWidget(this.netherwart);
		this.addRenderableWidget(this.replant);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, new TranslatableComponent("ib.gui.harvest.select"), this.width / 2 - 3 - 150, this.height / 4 - 30 + 12, 10526880);
	}

	public void sendInfo(boolean activate) {
		Common.NETWORK.sendToServer(new HarvestPacket(activate, this.pos, logOak.selected(), logSpruce.selected(), logBirch.selected(), logJungle.selected(), logAcacia.selected(), logDark.selected(), wheat.selected(), carrot.selected(), potato.selected(), cactus.selected(), pumpkin.selected(), melon.selected(), sugarcane.selected(), cocoa.selected(), mushroom.selected(), netherwart.selected(), replant.selected()));
	}
}