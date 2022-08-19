package com.slymask3.instantblocks.builder;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class BlockPosHolder {
    private final List<BlockPos> posList;
    private final CheckBlock checkBlock;
    private final boolean north, east, south, west, above, below;

    public BlockPosHolder(BlockPos origin, boolean north, boolean east, boolean south, boolean west, boolean above, boolean below, CheckBlock checkBlock) {
        this.posList = new ArrayList<>();
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.above = above;
        this.below = below;
        this.checkBlock = checkBlock;
        this.checkDirections(origin);
        this.checkBlock(origin);
    }

    public List<BlockPos> getList() {
        return this.posList;
    }

    public boolean isEmpty() {
        return this.posList.isEmpty();
    }

    public int size() {
        return this.posList.size();
    }

    public boolean add(BlockPos pos) {
        if (!this.posList.contains(pos)) {
            this.posList.add(pos);
            return true;
        }
        return false;
    }

    public void checkDirections(BlockPos pos) {
        if(this.north)this.checkBlock(pos.north(1));
        if(this.east)this.checkBlock(pos.east(1));
        if(this.south)this.checkBlock(pos.south(1));
        if(this.west)this.checkBlock(pos.west(1));
        if(this.above)this.checkBlock(pos.above(1));
        if(this.below)this.checkBlock(pos.below(1));
    }

    public void checkBlock(BlockPos pos) {
        this.checkBlock.call(pos, this);
    }

    public interface CheckBlock {
        void call(BlockPos pos, BlockPosHolder holder);
    }
}