package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ColorBlock extends Block implements EntityBlock {
    public static Block blockType = Blocks.WHITE_WOOL;

    public ColorBlock() {
        super(Block.Properties.of(blockType.defaultBlockState().getMaterial())
                .strength(0.5F)
                .sound(blockType.defaultBlockState().getSoundType())
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ColorBlockEntity(pos,state);
    }

    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state1, boolean var5) {
        super.onPlace(state,world,pos,state1,var5);
        if(Helper.isServer(world)) {
            BlockEntity entity = world.getBlockEntity(pos);
            if(entity instanceof ColorBlockEntity) {
                ((ColorBlockEntity)entity).color = Colors.generateRandomColor().getRGB();
            }
        }
        world.markAndNotifyBlock(pos,world.getChunkAt(pos),state,state,2,0);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        world.markAndNotifyBlock(pos,world.getChunkAt(pos),state,state,2,0);
        return InteractionResult.FAIL;
    }
}