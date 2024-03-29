package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.SchematicBlockEntity;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.network.packet.client.SchematicUpdatePacket;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Optional;

public class InstantSchematicBlock extends InstantBlock implements EntityBlock {
	public InstantSchematicBlock() {
		super(Properties.of(Material.WOOD)
				.strength(1.5F)
				.sound(SoundType.WOOD)
		);
		setScreen(ClientHelper.Screen.SCHEMATIC);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_SCHEMATIC();
	}

	public void openScreen(Player player, BlockPos pos) {
		Common.NETWORK.sendToClient(player, new SchematicUpdatePacket(SchematicHelper.getSchematics(),pos));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SchematicBlockEntity(pos,state);
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = Builder.setup(world,x,y,z).setSpeed(2).setDirection(Direction.UP);
		SchematicBlockEntity blockEntity = (SchematicBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
		SchematicHelper.Schematic schematic = SchematicHelper.readSchematic(blockEntity.schematic);
		if(schematic != null) {
			Single.setup(builder,world,x,y,z).setBlock(Blocks.AIR).queue();
			buildSchematic(builder, world, x, y, z, schematic, blockEntity.center, blockEntity.ignoreAir);
			builder.build();
			setCreateMessage(Strings.CREATE_SCHEMATIC, blockEntity.schematic);
			return true;
		}
		Helper.sendMessage(player, Strings.ERROR_SCHEMATIC, ChatFormatting.RED + blockEntity.schematic);
		return false;
	}

	public static void buildSchematic(Builder builder, Level world, int X, int Y, int Z, SchematicHelper.Schematic schematic, boolean center, boolean ignoreAir) {
		int width = schematic.width;
		int height = schematic.height;
		int length = schematic.length;

		int x_offset = 0;
		int z_offset = 0;
		if(center) {
			x_offset = width / 2;
			z_offset = length / 2;
		}

		for(int y = 0; y < height; ++y) {
			for(int x = 0; x < width; ++x) {
				for(int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					BlockState state = schematic.getBlockState(index);
					BlockPos pos = new BlockPos(x+X-x_offset,y+Y,z+Z-z_offset);
					if(state != null && !(ignoreAir && state.getBlock() == Blocks.AIR)) {
						Single.setup(builder,world,pos).setBlock(state).queue();
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