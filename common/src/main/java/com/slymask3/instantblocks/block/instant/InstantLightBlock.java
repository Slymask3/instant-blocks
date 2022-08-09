package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.builder.BlockType;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.builder.type.Sphere;
import com.slymask3.instantblocks.reference.Strings;
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

public class InstantLightBlock extends InstantBlock {
    public InstantLightBlock() {
        super(Properties.of(Material.DECORATION)
                .sound(SoundType.WOOD)
                .noCollission()
                .instabreak()
                .lightLevel((par1) -> 14)
        );
    }

    public boolean isEnabled() {
        return Common.CONFIG.ENABLE_LIGHT();
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
        Builder builder = Builder.setup(world,x,y,z).setOrigin(Builder.Origin.FROM,10);
        Single.setup(builder,world,x,y,z).setBlock(Blocks.AIR).queue();
        Sphere.setup(builder,world,x,y,z,Common.CONFIG.RADIUS_LIGHT()).setBlock(BlockType.conditionalTorch()).queue();
        builder.build();
        setCreateMessage(Strings.CREATE_LIGHT_AMOUNT, "0");
        return true;
	}

    public static boolean canPlaceTorch(Level world, BlockPos pos) {
        return world.getBlockState(pos).getBlock().equals(Blocks.AIR)
            && world.getRawBrightness(pos,0) <= Common.CONFIG.LIGHT_MAX()
            && (world.getBlockState(pos.below()).isFaceSturdy(world,pos, Direction.UP)
            || world.getBlockState(pos.north()).isFaceSturdy(world,pos,Direction.SOUTH)
            || world.getBlockState(pos.east()).isFaceSturdy(world,pos,Direction.WEST)
            || world.getBlockState(pos.south()).isFaceSturdy(world,pos,Direction.NORTH)
            || world.getBlockState(pos.west()).isFaceSturdy(world,pos,Direction.EAST));
    }
}