package com.slymask3.instantblocks.builder;

import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockType {
    enum Type { BLOCK, COLOR, STONE, CHEST }

    final Type type;
    final BlockState state;
    final int color;
    final List<ItemStack> containerItems;
    final boolean isDoubleChest;

    private BlockType(Type type, BlockState state, int color) {
        this.type = type;
        this.state = state;
        this.color = color;
        this.containerItems = null;
        this.isDoubleChest = false;
    }

    private BlockType(Type type, BlockState state, boolean isDoubleChest, ItemStack... items) {
        this.type = type;
        this.state = state;
        this.color = 0;
        this.containerItems = List.of(items);
        this.isDoubleChest = isDoubleChest;
    }

    public static BlockType block(Block block) {
        return block(block.defaultBlockState());
    }

    public static BlockType block(BlockState state) {
        return new BlockType(Type.BLOCK, state, 0);
    }

    public static BlockType color(int color) {
        return color(color, ModBlocks.COLOR);
    }

    public static BlockType color(int color, Block block) {
        return new BlockType(Type.COLOR, block.defaultBlockState(), color);
    }

    public static BlockType stone() {
        return new BlockType(Type.STONE, null, 0);
    }

    public static BlockType chest(boolean isDoubleChest, ItemStack... itemStacks) {
        return new BlockType(Type.CHEST, Blocks.CHEST.defaultBlockState(), isDoubleChest, itemStacks);
    }

    public Type getType() {
        return this.type;
    }

    public boolean isColor() {
        return this.type == Type.COLOR;
    }

    public boolean isChest() {
        return this.type == Type.CHEST;
    }

    public boolean isDoubleChest() {
        return this.isDoubleChest;
    }

    public List<ItemStack> getContainerItems() {
        return this.containerItems;
    }

    public Block getBlock(Level world, int y) {
        return getBlockState(world, y).getBlock();
    }

    public BlockState getBlockState() {
        return this.state;
    }

    public BlockState getBlockState(Level world, int y) {
        if(this.type == Type.STONE) {
            BlockState state;
            ResourceKey<Level> dimension = world.dimension();
            if(dimension.equals(Level.OVERWORLD)) {
                if(y > 8) {
                    state = Blocks.STONE.defaultBlockState();
                } else if(y < 0) {
                    state = Blocks.DEEPSLATE.defaultBlockState();
                } else { //0,1,2,3,4,5,6,7,8
                    ArrayList<Helper.WeightedBlock> blocks = new ArrayList<>();
                    blocks.add(new Helper.WeightedBlock(Blocks.STONE, y + 1));          //1,2,3,4,5,6,7,8,9
                    blocks.add(new Helper.WeightedBlock(Blocks.DEEPSLATE, 10 - y + 1)); //9,8,7,6,5,4,3,2,1
                    state = Helper.getRandomBlock(blocks).defaultBlockState();
                }
            } else if(dimension.equals(Level.NETHER)) {
                state = Blocks.NETHERRACK.defaultBlockState();
            } else if(dimension.equals(Level.END)) {
                state = Blocks.END_STONE.defaultBlockState();
            } else {
                state = Blocks.STONE.defaultBlockState();
            }
            return state;
        }
        return this.state;
    }

    public int getColor() {
        return this.color;
    }
}