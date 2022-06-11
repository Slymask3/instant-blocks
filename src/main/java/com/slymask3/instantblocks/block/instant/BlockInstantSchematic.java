package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class BlockInstantSchematic extends BlockInstant {
	public BlockInstantSchematic() {
		super(Block.Properties.of(Material.WOOD)
				.strength(1.5F, 2000F)
				.sound(SoundType.WOOD)
		);
		setGuiID(GuiID.SCHEMATIC);
	}

	public boolean build(Level world, int x, int y, int z, Player player, String schematicName, boolean center, boolean ignoreAir) {
		SchematicHelper.Schematic schematic = SchematicHelper.readSchematic(schematicName);
		if(schematic != null) {
			BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
			buildSchematic(world, x, y, z, schematic, center, ignoreAir);
			setCreateMessage(Strings.CREATE_SCHEMATIC.replace("%schematic%",schematicName));
			return true;
		}
		IBHelper.sendMessage(player, Strings.ERROR_SCHEMATIC.replace("%schematic%",schematicName), Colors.c);
		return false;
	}

	public static void buildSchematic(Level world, int X, int Y, int Z, SchematicHelper.Schematic schematic, boolean center, boolean ignoreAir) {
		short width = schematic.width;
		short height = schematic.height;
		short length = schematic.length;
		byte[] block = schematic.blocks;
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				for(int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					BlockState state = schematic.getBlockState(block[index]);
					if((block[index] & 0xFF) != 0 && ignoreAir) {
						if(center) {
							BuildHelper.setBlock(world,x+X-(width/2), y+Y, z+Z-(length/2), state);
						} else {
							BuildHelper.setBlock(world,x+X, y+Y, z+Z, state);
						}
					} else if(ignoreAir) {
						//Ignore Air
					} else {
						if(center) {
							BuildHelper.setBlock(world,x+X-(width/2), y+Y, z+Z-(length/2), state);
						} else {
							BuildHelper.setBlock(world,x+X, y+Y, z+Z, state);
						}
					}
				}
			}
		}
	}
}
