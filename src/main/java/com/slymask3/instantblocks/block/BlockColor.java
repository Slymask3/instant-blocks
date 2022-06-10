package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.util.IBHelper;
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

public class BlockColor extends Block implements EntityBlock {
    public static Block blockType = Blocks.WHITE_WOOL;

    public BlockColor() {
        super(Block.Properties.of(blockType.defaultBlockState().getMaterial())
                .strength(0.5F)
                .sound(blockType.defaultBlockState().getSoundType())
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityColor(pos,state);
    }

    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state1, boolean var5) {
        super.onPlace(state,world,pos,state1,var5);
        if(IBHelper.isServer(world)) {
            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            ((TileEntityColor) world.getBlockEntity(pos)).color = ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
        }
        world.markAndNotifyBlock(pos,world.getChunkAt(pos),state,state,2,0);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        world.markAndNotifyBlock(pos,world.getChunkAt(pos),state,state,2,0);
        TileEntityColor tile = (TileEntityColor) world.getBlockEntity(pos);
        InstantBlocks.LOGGER.info("color: " + tile.color);
        return InteractionResult.FAIL;
    }
}