package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.Coords;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class BlockInstantLight extends BlockInstant {
    public ArrayList<Coords> coordsList;

    public BlockInstantLight() {
        super(Block.Properties.of(Material.DECORATION)
                .strength(0.5F, 2000F)
                .sound(SoundType.WOOD)
                .noCollission()
                .instabreak()
                .lightLevel((par1) -> 14)
        );
        //setCreateMessage(Strings.CREATE_LIGHT);
        this.coordsList = new ArrayList<>();
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(6.0D, 0.0D, 6.0D, 10.0D, 15.0D, 10.0D);
    }

//    public boolean canActivate(Level world, int x, int y, int z, Player player) {
//        checkForDarkness(world,x,y,z);
//        if(coordsList.isEmpty()) {
//            IBHelper.msg(player, Strings.ERROR_LIGHT.replace("%i%",String.valueOf(Config.Common.RADIUS_LIGHT.get())), Colors.c);
//            return false;
//        }
//        return true;
//    }
    
    public void build(Level world, int x, int y, int z, Player player) {
        //for(Coords coords : coordsList) {
        //    BlockPos pos = coords.getBlockPos();
        //    placeTorch(world,pos);
        //}

        if(IBHelper.isServer(world)) {
            checkForDarkness(world,x,y,z);
            if(coordsList.isEmpty()) {
                IBHelper.sendMessage(player,Strings.ERROR_LIGHT.replace("%i%",String.valueOf(Config.Common.RADIUS_LIGHT.get())),Colors.c);
                return;
            }
            IBHelper.sendMessage(player,Strings.CREATE_LIGHT_AMOUNT.replace("%i%",String.valueOf(coordsList.size())),Colors.a,x,y,z);
            coordsList.clear();
        }
	}

    private void checkForDarkness(Level world, int x_center, int y_center, int z_center) {
        int radius = Config.Common.RADIUS_LIGHT.get();

        for(int y=y_center+radius; y>y_center-radius*2; y--) {
            for(int x=x_center-radius; x<x_center+radius*2; x++) {
                for(int z=z_center-radius; z<z_center+radius*2; z++) {
                    BlockPos pos = new BlockPos(x,y,z);
                    if(world.getBlockState(pos).getBlock() == Blocks.AIR && world.getRawBrightness(pos,0) < 8 && canPlaceTorch(world,pos)) {
                        addCoords(x,y,z);
                        if(IBHelper.isServer(world)) {
                            placeTorch(world, pos); //todo move to build
                        }
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
            BuildHelper.setBlock(world,pos,Blocks.TORCH);
        } else if(world.getBlockState(pos.north()).isFaceSturdy(world,pos,Direction.SOUTH)) {
            BuildHelper.setBlock(world,pos,Blocks.WALL_TORCH,Direction.SOUTH);
        } else if(world.getBlockState(pos.east()).isFaceSturdy(world,pos,Direction.WEST)) {
            BuildHelper.setBlock(world,pos,Blocks.WALL_TORCH,Direction.WEST);
        } else if(world.getBlockState(pos.south()).isFaceSturdy(world,pos,Direction.NORTH)) {
            BuildHelper.setBlock(world,pos,Blocks.WALL_TORCH,Direction.NORTH);
        } else if(world.getBlockState(pos.west()).isFaceSturdy(world,pos,Direction.EAST)) {
            BuildHelper.setBlock(world,pos,Blocks.WALL_TORCH,Direction.EAST);
        }
        world.markAndNotifyBlock(pos,world.getChunk(pos.getX(),pos.getZ()),world.getBlockState(pos),world.getBlockState(pos),2,3);
    }

    private boolean addCoords(int x, int y, int z) {
        Coords coords = new Coords(x,y,z);
        if(!coordsList.contains(coords)) {
            coordsList.add(coords);
            return true;
        }
        return false;
    }
}