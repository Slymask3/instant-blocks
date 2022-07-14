package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.HarvestBlockEntity;
import com.slymask3.instantblocks.network.packet.HarvestPacket;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HarvestScreen extends InstantScreen {
	private Checkbox logOak, logSpruce, logBirch, logJungle, logAcacia, logDark;
	private Checkbox wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart;
	private Checkbox replant;
	private final HarvestBlockEntity tileEntity;

	public HarvestScreen(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "ib.gui.harvest.title");
		this.tileEntity = (HarvestBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
		this.setDoneText("ib.gui.harvest.done");
	}

	@Override
	public void init() {
		super.init();

		int x_left = this.width / 2 - 154;
		int x_middle = this.width / 2 - 52;
		int x_right = this.width / 2 + 50;
		int y = this.height / 4 - 8;
		int slot = 22;
		int check_width = 100;

		this.logOak = new Checkbox(x_left, y, check_width, 20, Component.translatable("block.minecraft.oak_log"), tileEntity.logOak) {
			public void onPress() {
				super.onPress();
				tileEntity.logOak = this.selected();
			}
		};
		this.logSpruce = new Checkbox(x_left, y+slot, check_width, 20, Component.translatable("block.minecraft.spruce_log"), tileEntity.logSpruce) {
			public void onPress() {
				super.onPress();
				tileEntity.logSpruce = this.selected();
			}
		};
		this.logBirch = new Checkbox(x_left, y+(slot*2), check_width, 20, Component.translatable("block.minecraft.birch_log"), tileEntity.logBirch) {
			public void onPress() {
				super.onPress();
				tileEntity.logBirch = this.selected();
			}
		};
		this.logJungle = new Checkbox(x_left, y+(slot*3), check_width, 20, Component.translatable("block.minecraft.jungle_log"), tileEntity.logJungle) {
			public void onPress() {
				super.onPress();
				tileEntity.logJungle = this.selected();
			}
		};
		this.logAcacia = new Checkbox(x_left, y+(slot*4), check_width, 20, Component.translatable("block.minecraft.acacia_log"), tileEntity.logAcacia) {
			public void onPress() {
				super.onPress();
				tileEntity.logAcacia = this.selected();
			}
		};
		this.logDark = new Checkbox(x_left, y+(slot*5), check_width, 20, Component.translatable("block.minecraft.dark_oak_log"), tileEntity.logDark) {
			public void onPress() {
				super.onPress();
				tileEntity.logDark = this.selected();
			}
		};

		this.wheat = new Checkbox(x_middle, y, check_width, 20, Component.translatable("item.minecraft.wheat"), tileEntity.wheat) {
			public void onPress() {
				super.onPress();
				tileEntity.wheat = this.selected();
			}
		};
		this.carrot = new Checkbox(x_middle, y+slot, check_width, 20, Component.translatable("item.minecraft.carrot"), tileEntity.carrot) {
			public void onPress() {
				super.onPress();
				tileEntity.carrot = this.selected();
			}
		};
		this.potato = new Checkbox(x_middle, y+(slot*2), check_width, 20, Component.translatable("item.minecraft.potato"), tileEntity.potato) {
			public void onPress() {
				super.onPress();
				tileEntity.potato = this.selected();
			}
		};
		this.cactus = new Checkbox(x_middle, y+(slot*3), check_width, 20, Component.translatable("block.minecraft.cactus"), tileEntity.cactus) {
			public void onPress() {
				super.onPress();
				tileEntity.cactus = this.selected();
			}
		};
		this.pumpkin = new Checkbox(x_middle, y+(slot*4), check_width, 20, Component.translatable("block.minecraft.pumpkin"), tileEntity.pumpkin) {
			public void onPress() {
				super.onPress();
				tileEntity.pumpkin = this.selected();
			}
		};
		this.melon = new Checkbox(x_middle, y+(slot*5), check_width, 20, Component.translatable("block.minecraft.melon"), tileEntity.melon) {
			public void onPress() {
				super.onPress();
				tileEntity.melon = this.selected();
			}
		};

		this.sugarcane = new Checkbox(x_right, y, check_width, 20, Component.translatable("block.minecraft.sugar_cane"), tileEntity.sugarcane) {
			public void onPress() {
				super.onPress();
				tileEntity.sugarcane = this.selected();
			}
		};
		this.cocoa = new Checkbox(x_right, y+slot, check_width, 20, Component.translatable("block.minecraft.cocoa"), tileEntity.cocoa) {
			public void onPress() {
				super.onPress();
				tileEntity.cocoa = this.selected();
			}
		};
		this.mushroom = new Checkbox(x_right, y+(slot*2), check_width, 20, Component.translatable("ib.gui.harvest.mushroom"), tileEntity.mushroom) {
			public void onPress() {
				super.onPress();
				tileEntity.mushroom = this.selected();
			}
		};
		this.netherwart = new Checkbox(x_right, y+(slot*3), check_width, 20, Component.translatable("block.minecraft.nether_wart"), tileEntity.netherwart) {
			public void onPress() {
				super.onPress();
				tileEntity.netherwart = this.selected();
			}
		};
		this.replant = new Checkbox(x_right, y+(slot*4), check_width, 20, Component.translatable("ib.gui.harvest.replant"), tileEntity.replant) {
			public void onPress() {
				super.onPress();
				tileEntity.replant = this.selected();
			}
		};

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
		this.font.draw(poseStack, Component.translatable("ib.gui.harvest.select"), this.width / 2 - 3 - 150, this.height / 4 - 30 + 12, 10526880);
	}
	
	public void sendInfo() {
		Common.NETWORK.sendToServer(new HarvestPacket(this.x, this.y, this.z, logOak.selected(), logSpruce.selected(), logBirch.selected(), logJungle.selected(), logAcacia.selected(), logDark.selected(), wheat.selected(), carrot.selected(), potato.selected(), cactus.selected(), pumpkin.selected(), melon.selected(), sugarcane.selected(), cocoa.selected(), mushroom.selected(), netherwart.selected(), replant.selected()));
	}
}