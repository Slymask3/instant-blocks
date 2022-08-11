package com.slymask3.instantblocks.builder;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class BlockPosHolder {
    private final List<BlockPos> posList;
    private final CheckDirections checkDirections;
    private final CheckBlock checkBlock;

    public BlockPosHolder(BlockPos origin, CheckDirections checkDirections, CheckBlock checkBlock) {
        this.posList = new ArrayList<>();
        this.checkDirections = checkDirections;
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
        this.checkDirections.call(pos, this);
    }

    public void checkBlock(BlockPos pos) {
        this.checkBlock.call(pos, this);
    }

    public interface CheckDirections {
        void call(BlockPos pos, BlockPosHolder holder);
    }

    public interface CheckBlock {
        void call(BlockPos pos, BlockPosHolder holder);
    }
}