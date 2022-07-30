package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class InstantScreen extends Screen {
	protected final Level world;
	protected final BlockPos pos;
	protected final Player player;

	protected Button done, cancel;
	private String doneText;

	public InstantScreen(Player player, Level world, BlockPos pos, String title) {
		super(Component.translatable(title));
		this.player = player;
		this.world = world;
		this.pos = pos;
		this.doneText = "ib.gui.generate";
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	@Override
	public void init() {
		this.done = this.addRenderableWidget(new Button(this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, Component.translatable(doneText), (p_88642_) -> {
			this.sendInfo(true);
			this.close();
		}));
		this.cancel = this.addRenderableWidget(new Button(this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, CommonComponents.GUI_CANCEL, (p_88642_) -> {
			this.onClose();
		}));

		addRenderableWidget(this.done);
		addRenderableWidget(this.cancel);
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		poseStack.pushPose();
		this.font.drawShadow(poseStack, this.title, this.width / 2 - this.font.width(this.title) / 2, 20, 16777215);
		this.renderLabels(poseStack,mouseX,mouseY);
		poseStack.popPose();
	}

//	public void renderBackground(PoseStack matrices) {
//		overlayBackground(0, 0, this.width, this.height, 64, 64, 64, 128, 128);
//	}
//
//	static void overlayBackground(int x1, int y1, int x2, int y2, int red, int green, int blue, int startAlpha, int endAlpha) {
//		Tesselator tessellator = Tesselator.getInstance();
//		BufferBuilder buffer = tessellator.getBuilder();
//		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
//		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//		RenderSystem.setShaderTexture(0, GuiComponent.BACKGROUND_LOCATION);
//		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
//		buffer.vertex((double)x1, (double)y2, 0.0).uv((float)x1 / 32.0F, (float)y2 / 32.0F).color(red, green, blue, endAlpha).endVertex();
//		buffer.vertex((double)x2, (double)y2, 0.0).uv((float)x2 / 32.0F, (float)y2 / 32.0F).color(red, green, blue, endAlpha).endVertex();
//		buffer.vertex((double)x2, (double)y1, 0.0).uv((float)x2 / 32.0F, (float)y1 / 32.0F).color(red, green, blue, startAlpha).endVertex();
//		buffer.vertex((double)x1, (double)y1, 0.0).uv((float)x1 / 32.0F, (float)y1 / 32.0F).color(red, green, blue, startAlpha).endVertex();
//		tessellator.end();
//	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {}

	public boolean isPauseScreen() {
		return false;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Minecraft getMinecraft() {
		return this.minecraft;
	}

	public void sendInfo(boolean activate) {}

	public void onClose() {
		this.sendInfo(false);
		this.close();
	}

	private void close() {
		this.minecraft.setScreen(null);
	}
}