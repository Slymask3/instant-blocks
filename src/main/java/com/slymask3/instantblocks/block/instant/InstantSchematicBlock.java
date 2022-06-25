package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.SchematicBlockEntity;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Helper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InstantSchematicBlock extends InstantBlock implements EntityBlock {
	public InstantSchematicBlock() {
		super(Block.Properties.of(Material.WOOD)
				.strength(1.5F, 2000F)
				.sound(SoundType.WOOD)
		, Config.Common.DISABLE_SCHEMATIC);
		setGuiID(GuiID.SCHEMATIC);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SchematicBlockEntity(pos,state);
	}

	public boolean build(Level world, int x, int y, int z, Player player, String schematicName, boolean center, boolean ignoreAir) {
		SchematicHelper.Schematic schematic = SchematicHelper.readSchematic(schematicName);
		if(schematic != null) {
			BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
			buildSchematic(world, x, y, z, schematic, center, ignoreAir);
			setCreateMessage(Strings.CREATE_SCHEMATIC.replace("%schematic%",schematicName));
			return true;
		}
		Helper.sendMessage(player, Strings.ERROR_SCHEMATIC.replace("%schematic%",schematicName), ChatFormatting.RED);
		return false;
	}

	public static void buildSchematic(Level world, int X, int Y, int Z, SchematicHelper.Schematic schematic, boolean center, boolean ignoreAir) {
		int width = schematic.width;
		int height = schematic.height;
		int length = schematic.length;

		int x_offset = 0;
		int z_offset = 0;
		if(center) {
			x_offset = width / 2;
			z_offset = length / 2;
		}

		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				for(int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					BlockState state = schematic.getBlockState(index);
					BlockPos pos = new BlockPos(x+X-x_offset,y+Y,z+Z-z_offset);
					if(!(ignoreAir && state.getBlock() == Blocks.AIR)) {
						BuildHelper.setBlock(world,pos,state);
						CompoundTag tag = schematic.getBlockEntityTag(x,y,z);
						BlockEntity entity = world.getBlockEntity(pos);
						if(tag != null && entity != null) {
							entity.load(tag);
						}
					}
				}
			}
		}
		for(CompoundTag entityTag : schematic.getEntityTags()) {
			Optional<Entity> optional = EntityType.create(entityTag,world);
			if(optional.isPresent()) {
				Entity entity = optional.get();
				world.addFreshEntity(entity);
			}
		}
	}
}