package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
    public ArrayList<Helper.Coords> coordsList;

    public InstantLightBlock() {
        super(Block.Properties.of(Material.DECORATION)
                .strength(0.5F, 2000F)
                .sound(SoundType.WOOD)
                .noCollission()
                .instabreak()
                .lightLevel((par1) -> 14)
        , Config.Common.DISABLE_LIGHT);
        this.coordsList = new ArrayList<>();
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(6.0D, 0.0D, 6.0D, 10.0D, 15.0D, 10.0D);
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 1.0D;
        double d2 = (double)pos.getZ() + 0.5D;
        world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        world.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
    
    public boolean build(Level world, int x, int y, int z, Player player) {
        checkForDarkness(world,x,y,z);
        if(coordsList.isEmpty()) {
            Helper.sendMessage(player,Strings.ERROR_LIGHT, ChatFormatting.RED + String.valueOf(Config.Common.RADIUS_LIGHT.get()));
            return false;
        }
        Builder.Single.setup(world,x,y,z).setBlock(Blocks.TORCH).build();
        setCreateMessage(Strings.CREATE_LIGHT_AMOUNT, String.valueOf(coordsList.size()));
        coordsList.clear();
        return true;
	}

    private void checkForDarkness(Level world, int x_center, int y_center, int z_center) {
        int radius = Config.Common.RADIUS_LIGHT.get();
        Random random = new Random();

        for(int y=y_center+radius; y>y_center-radius*2; y-=(random.nextInt(3)+2)) {
            for(int x=x_center-radius; x<x_center+radius*2; x+=(random.nextInt(3)+2)) {
                for(int z=z_center-radius; z<z_center+radius*2; z+=(random.nextInt(3)+2)) {
                    BlockPos pos = new BlockPos(x,y,z);
                    if(world.getBlockState(pos).getBlock() == Blocks.AIR && world.getRawBrightness(pos,0) < 8 && canPlaceTorch(world,pos)) {
                        addCoords(x,y,z);
                        placeTorch(world, pos);
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

    private void placeTorch(Level world, BlockPos pos) {
        if(world.getBlockState(pos.below()).isFaceSturdy(world,pos,Direction.UP)) {
            Builder.Single.setup(world,pos).setBlock(Blocks.TORCH).setDirection(Direction.UP).build();
        } else if(world.getBlockState(pos.north()).isFaceSturdy(world,pos,Direction.SOUTH)) {
            Builder.Single.setup(world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.SOUTH).build();
        } else if(world.getBlockState(pos.east()).isFaceSturdy(world,pos,Direction.WEST)) {
            Builder.Single.setup(world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.WEST).build();
        } else if(world.getBlockState(pos.south()).isFaceSturdy(world,pos,Direction.NORTH)) {
            Builder.Single.setup(world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.NORTH).build();
        } else if(world.getBlockState(pos.west()).isFaceSturdy(world,pos,Direction.EAST)) {
            Builder.Single.setup(world,pos).setBlock(Blocks.WALL_TORCH).setDirection(Direction.EAST).build();
        }
        //world.getLightEngine().updateSectionStatus(pos,true);
        //world.getLightEngine().enableLightSources(new ChunkPos(pos),true);
        //world.getLightEngine().checkBlock(pos);
        //world.markAndNotifyBlock(pos,world.getChunkAt(pos),world.getBlockState(pos),world.getBlockState(pos),2,3);
        //world.sendBlockUpdated(pos,world.getBlockState(pos),world.getBlockState(pos),0);
    }

    private boolean addCoords(int x, int y, int z) {
        Helper.Coords coords = new Helper.Coords(x,y,z);
        if(!coordsList.contains(coords)) {
            coordsList.add(coords);
            return true;
        }
        return false;
    }
}