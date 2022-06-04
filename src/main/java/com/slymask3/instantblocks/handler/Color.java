package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.tileentity.TileEntityColor;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Color implements BlockColor {
    public int getColor(BlockState state, BlockAndTintGetter blockAndTintGetter, BlockPos pos, int tintIndex) {
        if(blockAndTintGetter == null) {
            return -1;
        }
        BlockEntity tileEntity = blockAndTintGetter.getBlockEntity(pos);
        if(tileEntity instanceof TileEntityColor) {
            return ((TileEntityColor)tileEntity).color;
        } else {
            return -1;
        }
    }
}