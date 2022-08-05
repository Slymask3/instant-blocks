package com.slymask3.instantblocks.builder.type;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.block.instant.InstantLightBlock;
import com.slymask3.instantblocks.builder.BlockType;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;

public class Single extends Base<Single> {
    private Single(Builder builder, Level world, int x, int y, int z) {
        super(builder, world, x, y, z);
    }

    public Single offset(Direction direction, int forwardBack, int leftRight) {
        int forward = Helper.isPositive(forwardBack) ? 0 : Math.abs(forwardBack);
        int back = Helper.isPositive(forwardBack) ? forwardBack : 0;
        int left = Helper.isPositive(leftRight) ? leftRight : 0;
        int right = Helper.isPositive(leftRight) ? 0 : Math.abs(leftRight);
        return offset(direction, forward, back, left, right, 0, 0);
    }

    public Single offset(Direction direction, int forward, int back, int left, int right) {
        return offset(direction, forward, back, left, right, 0, 0);
    }

    public Single offset(Direction direction, int forward, int back, int left, int right, int up, int down) {
        this.y = this.y + up - down;
        if(direction == Direction.SOUTH) {
            this.x = this.x - left + right;
            this.z = this.z - forward + back;
        } else if(direction == Direction.WEST) {
            this.x = this.x + forward - back;
            this.z = this.z - left + right;
        } else if(direction == Direction.NORTH) {
            this.x = this.x + left - right;
            this.z = this.z + forward - back;
        } else if(direction == Direction.EAST) {
            this.x = this.x - forward + back;
            this.z = this.z + left - right;
        }
        return this;
    }

    public static Single setup(Builder builder, Level world, int x, int y, int z) {
        return new Single(builder, world, x, y, z);
    }

    public static Single setup(Builder builder, Level world, BlockPos pos) {
        return new Single(builder, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public void queue(int priority, boolean replace) {
        this.setPriority(priority);
        this.replace = replace;
        this.builder.queue(this,this.replace);
    }

    public void build() {
        BlockState state = blockType.getBlockState(world, y);
        Block block = blockType.getBlock(world, y);
        Block getBlock = getWorldBlock();
        if(blockType.isConditionalTorch()) {
            if(InstantLightBlock.canPlaceTorch(world,getBlockPos())) {
                BlockPos pos = getBlockPos();
                if(world.getBlockState(pos.below()).isFaceSturdy(world,pos,Direction.UP)) {
                    world.setBlock(pos, Blocks.TORCH.defaultBlockState(), flag);
                } else if(world.getBlockState(pos.north()).isFaceSturdy(world,pos,Direction.SOUTH)) {
                    world.setBlock(pos, Blocks.WALL_TORCH.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING,Direction.SOUTH), flag);
                } else if(world.getBlockState(pos.east()).isFaceSturdy(world,pos,Direction.WEST)) {
                    world.setBlock(pos, Blocks.WALL_TORCH.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING,Direction.WEST), flag);
                } else if(world.getBlockState(pos.south()).isFaceSturdy(world,pos,Direction.NORTH)) {
                    world.setBlock(pos, Blocks.WALL_TORCH.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING,Direction.NORTH), flag);
                } else if(world.getBlockState(pos.west()).isFaceSturdy(world,pos,Direction.EAST)) {
                    world.setBlock(pos, Blocks.WALL_TORCH.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING,Direction.EAST), flag);
                }
            }
            return;
        }
        if(Common.CONFIG.KEEP_BLOCKS() && getBlock instanceof InstantBlock) {
            return;
        }
        if(Helper.isNether(world) && block.equals(Blocks.WATER) && !Common.CONFIG.ALLOW_WATER_IN_NETHER()) {
            state = Blocks.AIR.defaultBlockState(); //replace water with air in the nether
        }
        if(canSet(getBlock)) {
            if(block instanceof CrossCollisionBlock) {
                state = Block.updateFromNeighbourShapes(state == null ? block.defaultBlockState() : state, world, getBlockPos());
            }
            if(block instanceof SlabBlock && direction == Direction.UP) {
                direction = null;
                state = state.setValue(SlabBlock.TYPE, SlabType.TOP);
            }
            if(direction != null && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                state = state.setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
            }
            if(block == Blocks.FARMLAND) {
                state = state.setValue(FarmBlock.MOISTURE, FarmBlock.MAX_MOISTURE);
            }
            if(block instanceof BedBlock && direction != null) {
                state = state.setValue(BedBlock.PART, BedPart.HEAD);
            }
            if(block instanceof LeavesBlock) {
                state = state.setValue(LeavesBlock.PERSISTENT, Boolean.TRUE);
            }
            if(blockType.isDoubleChest()) {
                BlockPos right_pos = new BlockPos(x, y, z).relative(direction.getCounterClockWise(), 1);
                world.setBlock(right_pos, state.setValue(ChestBlock.TYPE, ChestType.LEFT), flag);
                state = state.setValue(ChestBlock.TYPE, ChestType.RIGHT);
            }
            world.setBlock(new BlockPos(x, y, z), state, flag);
            if(block instanceof DoorBlock) {
                world.setBlock(new BlockPos(x, y, z).above(), state.setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
            if(block instanceof BedBlock && direction != null) {
                world.setBlock(new BlockPos(x, y, z).relative(direction.getOpposite(), 1), state.setValue(BedBlock.PART, BedPart.FOOT), 3);
            }
            if(blockType.isColor()) {
                try {
                    ColorBlockEntity entity = (ColorBlockEntity) world.getBlockEntity(new BlockPos(x, y, z));
                    if(entity != null) {
                        entity.color = blockType.getColor();
                    }
                } catch (Exception e) {
                    Common.LOG.info(e.getMessage());
                }
            } else if(blockType.isChest()) {
                ChestBlockEntity blockEntity = (ChestBlockEntity)world.getBlockEntity(this.getBlockPos());
                for(ItemStack itemStack : blockType.getContainerItems()) {
                    Helper.addToChest(blockEntity, itemStack);
                }
            }
        }
    }

    private boolean canSet(Block block) {
        return block.defaultDestroyTime() >= 0F || block.equals(Blocks.AIR);
    }

    public Block getWorldBlock() {
        return this.getWorldBlockState().getBlock();
    }

    public BlockState getWorldBlockState() {
        return world.getBlockState(this.getBlockPos());
    }

    public BlockEntity getBlockEntity() {
        return world.getBlockEntity(this.getBlockPos());
    }

    public BlockType getBlockType() {
        return this.blockType;
    }

    public Helper.BuildSound getBuildSound() {
        if(blockType.isConditionalTorch() && (this.getWorldBlock().equals(Blocks.TORCH) || this.getWorldBlock().equals(Blocks.WALL_TORCH))) {
            return new Helper.BuildSound(getBlockPos(),Blocks.TORCH.getSoundType(Blocks.TORCH.defaultBlockState()).getPlaceSound(),null,0.1F);
        }
        BlockState breakBlockState = this.getWorldBlockState();
        BlockState placeBlockState = this.blockType.getBlockState(); //this.blockType.getBlockState(world,y);
        SoundEvent placeSound = null, breakSound = null;
        if(placeBlockState != null && !placeBlockState.getBlock().equals(Blocks.AIR)) {
            if(placeBlockState.getBlock().equals(Blocks.WATER)) {
                placeSound = SoundEvents.BUCKET_EMPTY;
            } else if(placeBlockState.getBlock().equals(Blocks.LAVA)) {
                placeSound = SoundEvents.BUCKET_EMPTY_LAVA;
            } else {
                placeSound = placeBlockState.getBlock().getSoundType(placeBlockState).getPlaceSound();
            }
        }
        if(breakBlockState != null && !breakBlockState.getBlock().equals(Blocks.AIR)) {
            if(breakBlockState.getBlock().equals(Blocks.WATER)) {
                breakSound = SoundEvents.BUCKET_FILL;
            } else if(breakBlockState.getBlock().equals(Blocks.LAVA)) {
                breakSound = SoundEvents.BUCKET_FILL_LAVA;
            } else {
                breakSound = breakBlockState.getBlock().getSoundType(breakBlockState).getBreakSound();
            }
        }
        //Common.LOG.info("place: " + (placeBlockState != null ? placeBlockState.getBlock() : "none") + " - " + (placeSound != null ? placeSound.getLocation() : "none"));
        //Common.LOG.info("break: " + (breakBlockState != null ? breakBlockState.getBlock() : "none") + " - " + (breakSound != null ? breakSound.getLocation() : "none"));
        //Common.LOG.info("------");
        return new Helper.BuildSound(this.getBlockPos(),placeSound,breakSound,0.1F);
    }
}