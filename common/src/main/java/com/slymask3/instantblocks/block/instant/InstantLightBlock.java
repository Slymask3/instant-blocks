package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Random;

public class InstantLightBlock extends InstantBlock {
    public final ArrayList<BlockPos> posList;

    public InstantLightBlock() {
        super(Properties.of(Material.DECORATION)
                .sound(SoundType.WOOD)
                .noCollission()
                .instabreak()
                .lightLevel((par1) -> 14)
        );
        this.posList = new ArrayList<>();
    }

    public boolean isEnabled() {
        return Common.CONFIG.ENABLE_LIGHT();
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(6.0D, 0.0D, 6.0D, 10.0D, 15.0D, 10.0D);
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 1.0D;
        double d2 = (double)pos.getZ() + 0.5D;
        world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
    
    public boolean build(Level world, int x, int y, int z, Player player) {
        Builder builder = new Builder();
        checkForDarkness(builder,world,x,y,z);
        if(posList.isEmpty()) {
            Helper.sendMessage(player,Strings.ERROR_LIGHT, ChatFormatting.RED + String.valueOf(Common.CONFIG.RADIUS_LIGHT()));
            return false;
        }
        Single.setup(builder,world,x,y,z).setBlock(Blocks.TORCH).queue();
        builder.build();
        setCreateMessage(Strings.CREATE_LIGHT_AMOUNT, String.valueOf(posList.size()));
        posList.clear();
        return true;
	}

    private void checkForDarkness(Builder builder, Level world, int x_center, int y_center, int z_center) {
        int radius = Common.CONFIG.RADIUS_LIGHT();
        Random random = new Random();

        for(int y=y_center+radius; y>y_center-radius*2; y-=(random.nextInt(3)+2)) {
            for(int x=x_center-radius; x<x_center+radius*2; x+=(random.nextInt(3)+2)) {
                for(int z=z_center-radius; z<z_center+radius*2; z+=(random.nextInt(3)+2)) {
                    BlockPos pos = new BlockPos(x,y,z);
                    if(world.getBlockState(pos).getBlock() == Blocks.AIR && world.getRawBrightness(pos,0) < 8 && canPlaceTorch(world,pos)) {
                        addPos(pos);
                        placeTorch(builder, world, pos);
                        //todo update brightness
                    }
                }
            }
        }
    }

    private boolean canPlaceTorch(Level world, BlockPos pos) {
        return world.getBlockState(pos.below()).isFaceSturdy(world,pos,Direction.UP)
            || world.getBlockState(pos.north()).isFaceSturdy(world,pos,Direction.SOUTH)
            || world.getBlockState(pos.east()).isFaceSturdy(world,pos,Direction.WEST)
            || world.getBlockState(pos.south()).isFaceSturdy(world,pos,Direction.NORTH)
            || world.getBlockState(pos.west()).isFaceSturdy(world,pos,Direction.EAST);
    }

    private void placeTorch(Builder builder, Level world, BlockPos pos) {
        if(world.getBlockState(pos.below()).isFaceSturdy(world,pos,Direction.UP)) {
            Single.setup(builder,world,pos).setBlock(Blocks.TORCH).setDirection(Direction.UP).queue();
        } else if(world.getBlockState(pos.north()).isFaceSturdy(world,pos,Direction.SOUTH)) {
            Single.setup(builder,world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.SOUTH).queue();
        } else if(world.getBlockState(pos.east()).isFaceSturdy(world,pos,Direction.WEST)) {
            Single.setup(builder,world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.WEST).queue();
        } else if(world.getBlockState(pos.south()).isFaceSturdy(world,pos,Direction.NORTH)) {
            Single.setup(builder,world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.NORTH).queue();
        } else if(world.getBlockState(pos.west()).isFaceSturdy(world,pos,Direction.EAST)) {
            Single.setup(builder,world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.EAST).queue();
        }
        //world.getLightEngine().updateSectionStatus(pos,true);
        //world.getLightEngine().enableLightSources(new ChunkPos(pos),true);
        //world.getLightEngine().checkBlock(pos);
        //world.markAndNotifyBlock(pos,world.getChunkAt(pos),world.getBlockState(pos),world.getBlockState(pos),2,3);
        //world.sendBlockUpdated(pos,world.getBlockState(pos),world.getBlockState(pos),0);
    }

    private boolean addPos(BlockPos pos) {
        if(!posList.contains(pos)) {
            posList.add(pos);
            return true;
        }
        return false;
    }
}