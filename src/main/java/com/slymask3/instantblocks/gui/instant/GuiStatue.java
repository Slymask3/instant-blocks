package com.slymask3.instantblocks.gui.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.instant.BlockInstantStatue;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.network.PacketStatue;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiStatue extends GuiInstant {
	private final TileEntityStatue tileEntity;

	private GuiTextField input;
	private GuiCheckBox head, body, armLeft, armRight, legLeft, legRight;
	private GuiCheckBox rgbMode;

	public GuiStatue(EntityPlayer player, TileEntityStatue entity, World world, int x, int y, int z) {
		super(player,world,x,y,z);
		this.tileEntity = entity;
	}

	public void init() {
		this.buttonList.add(this.head = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 20 + 12, "Head", true));
		this.buttonList.add(this.body = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 30 + 12, "Body", true));
		this.buttonList.add(this.armLeft = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 40 + 12, "Arm (Left)", true));
		this.buttonList.add(this.armRight = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 40 + 12, "Arm (Right)", true));
		this.buttonList.add(this.legLeft = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 50 + 12, "Leg (Left)", true));
		this.buttonList.add(this.legRight = new GuiCheckBox(getID(), this.width / 2 + 4, this.height / 4 + 50 + 12, "Leg (Right)", true));

		this.buttonList.add(this.rgbMode = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 70 + 12, "RGB Mode", true));

		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
		this.input.setText("");
		this.input.setFocused(true);

		this.done.enabled = this.input.getText().trim().length() > 3;
	}

	@Override
	public void updateScreen() {
		this.input.updateCursorCounter();
	}

	public void typed(char character, int key) {
		if(("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".indexOf(character) >= 0) || (key == 14)) {
			input.textboxKeyTyped(character, key);
		}
        this.done.enabled = this.input.getText().trim().length() > 3;
	}

	@Override
	protected void mouseClicked(int x, int y, int click) {
        super.mouseClicked(x, y, click);
        this.input.mouseClicked(x, y, click);
    }

	public void draw(int par1, int par2, float par3) {
		this.drawCenteredString(this.fontRendererObj, "Instant Statue Block", this.width / 2, 20, 16777215);
		this.drawString(this.fontRendererObj, "Enter a Minecraft Username:", this.width / 2 - 4 - 150, 37, 10526880);
		this.drawString(this.fontRendererObj, "Select parts to generate:", this.width / 2 - 3 - 150, this.height / 4 + 8 + 12, 10526880);
		this.drawString(this.fontRendererObj, "Unchecking RGB Mode will use vanilla minecraft wool colors.", this.width / 2 - 3 - 150, this.height / 4 + 83 + 12, 10526880);
		this.input.drawTextBox();
	}
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketStatue(this.world, this.x, this.y, this.z, input.getText(), head.isChecked(), body.isChecked(), armLeft.isChecked(), armRight.isChecked(), legLeft.isChecked(), legRight.isChecked(), rgbMode.isChecked()));

		BlockInstantStatue block = (BlockInstantStatue) BuildHelper.getBlock(world,x,y,z);
		if(block.getSkin(input.getText()) != null) {
			block.setCreateMessage(Strings.CREATE_STATUE.replace("%username%",input.getText()));
			block.afterBuild(world,x,y,z,player);
		} else {
			IBHelper.msg(player, Strings.ERROR_STATUE.replace("%username%",input.getText()), Colors.c);
		}
	}
}