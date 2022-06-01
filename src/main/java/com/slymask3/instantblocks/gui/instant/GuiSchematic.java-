package com.slymask3.instantblocks.gui.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.instant.BlockInstantSchematic;
import com.slymask3.instantblocks.gui.GuiInstant;
import com.slymask3.instantblocks.gui.module.GuiSchematicSlot;
import com.slymask3.instantblocks.network.PacketSchematic;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@SideOnly(Side.CLIENT)
public class GuiSchematic extends GuiInstant {
	private final TileEntitySchematic tileEntity;

	private GuiSchematicSlot schematicList;
    private int selected = -1;
    private ArrayList<String> schematics;

	private GuiTextField input;
	private GuiCheckBox center, ignoreAir;

	public GuiSchematic(EntityPlayer player, TileEntitySchematic entity, World world, int x, int y, int z) {
		super(player,world,x,y,z);
		this.tileEntity = entity;
	}

	public void init() {
		this.buttonList.add(this.center = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 4 + 12, "Generate the schematic from the center.", false));
		this.buttonList.add(this.ignoreAir = new GuiCheckBox(getID(), this.width / 2 - 4 - 150, this.height / 4 + 14 + 12, "Ignore generating air. (Could prevent lag)", false));

		this.input = new GuiTextField(this.fontRendererObj, this.width / 2 - 4 - 150, 50, 300+8, 20);
		this.input.setText("");
		this.input.setFocused(true);

		this.schematicList = new GuiSchematicSlot(this, schematics, this.width / 2 - 4 - 150, this.height / 4 + 42 + 12, 300+8, 62);
		this.schematicList.registerScrollButtons(this.buttonList, 7, 8);

		this.done.enabled = false;
	}

	@Override
	public void updateScreen() {
		this.input.updateCursorCounter();
	}

	@Override
	public void typed(char character, int key) {
		if(("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ^&'@{}[],$=!-#()%.+~_ ".indexOf(character) >= 0) || (key == 14)) {
			input.textboxKeyTyped(character, key);
		}
		File f = new File("schematics/"+input.getText()+".schematic");
        this.done.enabled = f.isFile();
	}

	@Override
	protected void mouseClicked(int x, int y, int click) {
        super.mouseClicked(x, y, click);
        this.input.mouseClicked(x, y, click);
    }

	public void draw(int par1, int par2, float par3) {
		this.drawCenteredString(this.fontRendererObj, "Instant Schematic Block", this.width / 2, 20, 16777215);
		this.drawString(this.fontRendererObj, "Enter a Schematic File Name:", this.width / 2 - 4 - 150, 37, 10526880);

		this.schematics = new ArrayList<String>();

		Collection<File> all = new ArrayList<File>();
		addTree(new File("schematics/"), all);
		Object[] schems = all.toArray();

		for(int i=0; i<all.size();i++) {
			String schem = schems[i].toString().replaceAll("schematics", "").replaceAll("\\\\", "").replaceAll(".schematic", "");
			schematics.add(schem);
		}

		this.schematicList.updateFiles(schematics);

		//draw scrolling list
		this.schematicList.drawScreen(par1, par2, par3);

		//background behind found files
		this.drawRect(this.width / 2 - 4 - 150, this.height / 4 + 29 + 12, this.width / 2 - 4 - 150 +(300+8), this.height / 4 + 29 + 12 +(13), -16777216);

		//background behind schematic file
		this.drawRect(this.width / 2 - 4 - 150, this.height / 4 + 29 + 12 +(75), this.width / 2 - 4 - 150 +(300+8), this.height / 4 + 29 + 12 +(75+13), -16777216);


		File f = new File("schematics/"+input.getText()+".schematic");
		if(f.isFile()) {
			this.drawString(this.fontRendererObj, "File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), 0x00FF00);
		} else {
			this.drawString(this.fontRendererObj, "File: '.minecraft/schematics/"+input.getText()+".schematic'", this.width / 2 - 2 - 150, this.height / 4 + 41 + 12 +(65), 0xAA0000);
		}

		this.drawString(this.fontRendererObj, "Found " + all.size() + " schematic files:", this.width / 2 - 2 - 150, this.height / 4 + 31 + 12, 0xFFFFFF);

		if(all.size() == 0) {
			this.drawString(this.fontRendererObj, "Put .schematic files into the 'schematics' folder in .minecraft.", this.width / 2 - 3 - 150, this.height / 4 +60 + 12, 0xAA0000);
			this.drawString(this.fontRendererObj, "If the 'schematics' folder doesn't exit, create it.", this.width / 2 - 3 - 150, this.height / 4 +70 + 12, 0xAA0000);
			this.drawString(this.fontRendererObj, "Files will update here right away, no need to restart.", this.width / 2 - 3 - 150, this.height / 4 +80 + 12, 0xAA0000);
		}

		this.input.drawTextBox();

		this.done.enabled = f.isFile();
	}
	
	private void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if(children != null) {
            for(File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }
	
	public void sendInfo() {
		InstantBlocks.packetPipeline.sendToServer(new PacketSchematic(this.world, this.x, this.y, this.z, input.getText(), center.isChecked(), ignoreAir.isChecked()));

		BlockInstantSchematic block = (BlockInstantSchematic) BuildHelper.getBlock(world,x,y,z);
		if(SchematicHelper.readSchematic(input.getText()) != null) {
			block.setCreateMessage(Strings.CREATE_SCHEMATIC.replace("%schematic%",input.getText()));
			block.afterBuild(world,x,y,z,player);
		} else {
			IBHelper.msg(player, Strings.ERROR_SCHEMATIC.replace("%schematic%",input.getText()), Colors.c);
		}
	}

    public void selectSchematicIndex(int index) {
        this.selected = index;
        if(index>=0 && index<=schematics.size()) {
            this.input.setText(schematics.get(selected));
        }
        File f = new File("schematics/"+input.getText()+".schematic");
        this.done.enabled = f.isFile();
    }

    public boolean schematicIndexSelected(int var1) {
        return var1==selected;
    }
}