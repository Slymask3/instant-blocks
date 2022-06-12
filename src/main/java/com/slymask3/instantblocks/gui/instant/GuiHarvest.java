package com.slymask3.instantblocks.gui.instant;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.PacketHarvest;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GuiHarvest extends GuiInstant {
	private Checkbox logOak, logSpruce, logBirch, logJungle, logAcacia, logDark;
	private Checkbox wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart;
	private Checkbox replant;
	private final TileEntityHarvest tileEntity;

	public GuiHarvest(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "Instant Harvest");
		this.tileEntity = (TileEntityHarvest)world.getBlockEntity(new BlockPos(x,y,z));
	}

	@Override
	public void init() {
		super.init();

		int x_left = this.width / 2 - 154;
		int x_middle = this.width / 2 - 52;
		int x_right = this.width / 2 + 50;
		int y = this.height / 4 - 8;
		int slot = 22;

		this.logOak = new Checkbox(x_left, y, 150, 20, new TextComponent("Oak Log"), true);
		this.logSpruce = new Checkbox(x_left, y+slot, 150, 20, new TextComponent("Spruce Log"), true);
		this.logBirch = new Checkbox(x_left, y+(slot*2), 150, 20, new TextComponent("Birch Log"), true);
		this.logJungle = new Checkbox(x_left, y+(slot*3), 150, 20, new TextComponent("Jungle Log"), true);
		this.logAcacia = new Checkbox(x_left, y+(slot*4), 150, 20, new TextComponent("Acacia Log"), true);
		this.logDark = new Checkbox(x_left, y+(slot*5), 150, 20, new TextComponent("Dark Oak Log"), true);

		this.wheat = new Checkbox(x_middle, y, 150, 20, new TextComponent("Wheat"), true);
		this.carrot = new Checkbox(x_middle, y+slot, 150, 20, new TextComponent("Carrot"), true);
		this.potato = new Checkbox(x_middle, y+(slot*2), 150, 20, new TextComponent("Potato"), true);
		this.cactus = new Checkbox(x_middle, y+(slot*3), 150, 20, new TextComponent("Cactus"), true);
		this.pumpkin = new Checkbox(x_middle, y+(slot*4), 150, 20, new TextComponent("Pumpkin"), true);
		this.melon = new Checkbox(x_middle, y+(slot*5), 150, 20, new TextComponent("Melon"), true);

		this.sugarcane = new Checkbox(x_right, y, 150, 20, new TextComponent("Sugar Cane"), true);
		this.cocoa = new Checkbox(x_right, y+slot, 150, 20, new TextComponent("Pumpkin"), true);
		this.mushroom = new Checkbox(x_right, y+(slot*2), 150, 20, new TextComponent("Melon"), true);
		this.netherwart = new Checkbox(x_right, y+(slot*3), 150, 20, new TextComponent("Sugar Cane"), true);
		this.replant = new Checkbox(x_right, y+(slot*4), 150, 20, new TextComponent("Re-Plant"), true);

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
		this.font.draw(poseStack, "Select blocks to harvest:", this.width / 2 - 3 - 150, this.height / 4 - 30 + 12, 10526880);
	}
	
	public void sendInfo() {
		PacketHandler.sendToServer(new PacketHarvest(this.x, this.y, this.z, logOak.selected(), logSpruce.selected(), logBirch.selected(), logJungle.selected(), logAcacia.selected(), logDark.selected(), wheat.selected(), carrot.selected(), potato.selected(), cactus.selected(), pumpkin.selected(), melon.selected(), sugarcane.selected(), cocoa.selected(), mushroom.selected(), netherwart.selected(), replant.selected()));
	}
}