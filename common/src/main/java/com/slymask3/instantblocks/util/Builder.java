package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.core.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Builder {
	public static class BlockType {
		enum Type { BLOCK,COLOR,STONE }
		final Type type;
		final BlockState state;
		final int color;
		private BlockType(Type type, BlockState state, int color) {
			this.type = type;
			this.state = state;
			this.color = color;
		}
		public static BlockType block(Block block) {
			return block(block.defaultBlockState());
		}
		public static BlockType block(BlockState state) {
			return new BlockType(Type.BLOCK,state,0);
		}
		public static BlockType color(int color) {
			return color(color, ModBlocks.COLOR);
		}
		public static BlockType color(int color, Block block) {
			return new BlockType(Type.COLOR,block.defaultBlockState(),color);
		}
		public static BlockType stone() {
			return new BlockType(Type.STONE,null,0);
		}
		public Type getType() {
			return this.type;
		}
		public boolean isColor() {
			return this.type == Type.COLOR;
		}
		public Block getBlock(Level world, int y) {
			return getBlockState(world,y).getBlock();
		}
		public BlockState getBlockState(Level world, int y) {
			if(this.type == Type.STONE) {
				BlockState state;
				ResourceKey<Level> dimension = world.dimension();
				if(dimension.equals(Level.OVERWORLD)) {
					if (y > 8) {
						state = Blocks.STONE.defaultBlockState();
					} else if (y < 0) {
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

	public static abstract class Base<T extends Base<T>> {
		final Level world;
		int x, y, z;
		BlockType blockType;
		Direction direction;
		final int flag;
		private Base(Level world, int x, int y, int z) {
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.flag = 2;
		}
		public T setBlock(BlockType blockType) {
			this.blockType = blockType;
			return (T)this;
		}
		public T setBlock(Block block) {
			return setBlock(block.defaultBlockState());
		}
		public T setBlock(BlockState state) {
			return setBlock(BlockType.block(state));
		}
		public T setColor(int color) {
			return setBlock(BlockType.color(color));
		}
		public T setImageColor(BufferedImage image, int x, int y, boolean useRGB) {
			return useRGB ? setColor(image.getRGB(x,y)) : setBlock(ColorHelper.getWoolColor(ColorHelper.getColorAt(image,x,y)));
		}
		public T setStone() {
			return setBlock(BlockType.stone());
		}
		public T setDirection(Direction direction) {
			this.direction = direction;
			return (T)this;
		}
		public void build() {}
	}

	public static class Single extends Base<Single> {
		private Single(Level world, int x, int y, int z) {
			super(world,x,y,z);
		}
		public Single offset(Direction direction, int forwardBack, int leftRight) {
			int forward = Helper.isPositive(forwardBack) ? 0 : Helper.toPositive(forwardBack);
			int back = Helper.isPositive(forwardBack) ? forwardBack : 0;
			int left = Helper.isPositive(leftRight) ? leftRight : 0;
			int right = Helper.isPositive(leftRight) ? 0 : Helper.toPositive(leftRight);
			return offset(direction,forward,back,left,right,0,0);
		}
		public Single offset(Direction direction, int forward, int back, int left, int right) {
			return offset(direction,forward,back,left,right,0,0);
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
		public static Single setup(Level world, int x, int y, int z) {
			return new Single(world, x, y, z);
		}
		public static Single setup(Level world, BlockPos pos) {
			return new Single(world,pos.getX(),pos.getY(),pos.getZ());
		}
		public void build() {
			BlockState state = blockType.getBlockState(world,y);
			Block block = blockType.getBlock(world,y);
			Block getBlock = getBlock();
			if(Common.CONFIG.KEEP_BLOCKS() && getBlock instanceof InstantBlock) {
				return;
			}
			if(world.dimension().equals(Level.NETHER) && block.equals(Blocks.WATER) && !Common.CONFIG.ALLOW_WATER_IN_NETHER()) {
				state = Blocks.AIR.defaultBlockState(); //replace water with air in the nether
			}
			if(canSet(getBlock)) {
				if(block instanceof CrossCollisionBlock) {
					Context context = new Context(world, new BlockPos(x,y,z));
					state = block.getStateForPlacement(context);
					if(state == null) return;
				}
				if(block instanceof SlabBlock && direction == Direction.UP) {
					direction = null;
					state = state.setValue(SlabBlock.TYPE, SlabType.TOP);
				}
				if(direction != null && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
					state = state.setValue(BlockStateProperties.HORIZONTAL_FACING,direction);
				}
				if(block == Blocks.FARMLAND) {
					state = state.setValue(FarmBlock.MOISTURE,FarmBlock.MAX_MOISTURE);
				}
				if(block instanceof BedBlock && direction != null) {
					state = state.setValue(BedBlock.PART, BedPart.HEAD);
				}
				if(block instanceof LeavesBlock) {
					state = state.setValue(LeavesBlock.PERSISTENT, Boolean.TRUE);
				}
				if(block instanceof ChestBlock && direction != null) {
					BlockPos left_pos = new BlockPos(x,y,z).relative(direction.getCounterClockWise(),1);
					BlockPos right_pos = new BlockPos(x,y,z).relative(direction.getClockWise(),1);
					BlockState left = world.getBlockState(left_pos);
					BlockState right = world.getBlockState(right_pos);
					if(left.getBlock() == Blocks.CHEST && left.getValue(ChestBlock.TYPE) == ChestType.SINGLE && left.getValue(ChestBlock.FACING) == direction) {
						world.setBlock(left_pos,state.setValue(ChestBlock.TYPE,ChestType.LEFT),flag);
						state = state.setValue(ChestBlock.TYPE, ChestType.RIGHT);
					} else if(right.getBlock() == Blocks.CHEST && right.getValue(ChestBlock.TYPE) == ChestType.SINGLE && right.getValue(ChestBlock.FACING) == direction) {
						world.setBlock(left_pos,state.setValue(ChestBlock.TYPE,ChestType.RIGHT),flag);
						state = state.setValue(ChestBlock.TYPE, ChestType.LEFT);
					}
				}
				world.setBlock(new BlockPos(x,y,z),state,flag);
				if(block instanceof DoorBlock) {
					world.setBlock(new BlockPos(x,y,z).above(), state.setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), 3);
				}
				if(block instanceof BedBlock && direction != null) {
					world.setBlock(new BlockPos(x,y,z).relative(direction.getOpposite(),1), state.setValue(BedBlock.PART, BedPart.FOOT), 3);
				}
				if(blockType.isColor()) {
					try {
						ColorBlockEntity entity = (ColorBlockEntity)world.getBlockEntity(new BlockPos(x, y, z));
						if(entity != null) {
							entity.color = blockType.getColor();
						}
					} catch(Exception e) {
						Common.LOG.info(e.getMessage());
					}
				}
			}
		}
		private boolean canSet(Block block) {
			return block.defaultDestroyTime() >= 0F || block.equals(Blocks.AIR);
		}
		public Block getBlock() {
			return world.getBlockState(new BlockPos(x,y,z)).getBlock();
		}
		public BlockEntity getBlockEntity() {
			return world.getBlockEntity(new BlockPos(x,y,z));
		}
	}

	public static class Multiple extends Base<Multiple> {
		final int x1, y1, z1;
		final int x2, y2, z2;
		private Multiple(Level world, int x, int y, int z, int x1, int y1, int z1, int x2, int y2, int z2) {
			super(world,x,y,z);
			this.x1 = x1;
			this.y1 = y1;
			this.z1 = z1;
			this.x2 = x2;
			this.y2 = y2;
			this.z2 = z2;
		}
		public static Multiple setup(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
			int x1 = x, z1 = z, x2 = x, z2 = z;
			int y2 = y + upX - downX;
			if(direction == Direction.SOUTH) {
				x1 = x-left+right;
				z1 = z-forward+back;
				x2 = x1-leftX+rightX;
				z2 = z1-forwardX+backX;
			} else if(direction == Direction.WEST) {
				x1 = x+forward-back;
				z1 = z-left+right;
				x2 = x1+forwardX-backX;
				z2 = z1-leftX+rightX;
			} else if(direction == Direction.NORTH) {
				x1 = x+left-right;
				z1 = z+forward-back;
				x2 = x1+leftX-rightX;
				z2 = z1+forwardX-backX;
			} else if(direction == Direction.EAST) {
				x1 = x-forward+back;
				z1 = z+left-right;
				x2 = x1-forwardX+backX;
				z2 = z1+leftX-rightX;
			}
			return new Multiple(world, x, y, z, x1, y, z1, x2, y2, z2);
		}
		public static Multiple setup(Level world, int x_start, int y_start, int z_start, int x_times, int y_times, int z_times) {
			return new Multiple(world, x_start, y_start, z_start, x_start, y_start, z_start, x_start + x_times - 1, y_start + y_times - 1, z_start + z_times - 1);
		}
		public void build() {
			boolean x_dir = Helper.isPositive(x1 - x2);
			boolean y_dir = Helper.isPositive(y1 - y2);
			boolean z_dir = Helper.isPositive(z1 - z2);
			for(int y_cur=y1; (y_dir?y_cur>=y2:y_cur<=y2); y_cur=y_cur+(y_dir?-1:1)) {
				for(int z_cur=z1; (z_dir?z_cur>=z2:z_cur<=z2); z_cur=z_cur+(z_dir?-1:1)) {
					for(int x_cur=x1; (x_dir?x_cur>=x2:x_cur<=x2); x_cur=x_cur+(x_dir?-1:1)) {
						Single.setup(world,x_cur,y_cur,z_cur).setBlock(blockType).setDirection(direction).build();
					}
				}
			}
		}
	}

	public static class Circle extends Base<Circle> {
		BlockType outerBlockType, innerBlockType;
		final int radius;
		private Circle(Level world, int x, int y, int z, int radius) {
			super(world,x,y,z);
			this.radius = radius;
		}
		public static Circle setup(Level world, int x, int y, int z, int radius) {
			return new Circle(world, x, y, z, radius);
		}
		public Circle setBlock(BlockType type) {
			this.outerBlockType = type;
			this.innerBlockType = type;
			return this;
		}
		public Circle setOuter(BlockType type) {
			this.outerBlockType = type;
			return this;
		}
		public Circle setInner(BlockType type) {
			this.innerBlockType = type;
			return this;
		}
		public void build() {
			double distance;
			for(int row = 0; row <= 2 * radius; row++) {
				for(int col = 0; col <= 2 * radius; col++) {
					distance = Math.sqrt((row - radius) * (row - radius) + (col - radius) * (col - radius));
					if(distance > radius - 0.4 && distance < radius + 0.5) {
						Single.setup(world,x+row-radius,y,z+col-radius).setBlock(outerBlockType).build();
					} else if(distance < radius - 0.3) {
						Single.setup(world,x+row-radius,y,z+col-radius).setBlock(innerBlockType).build();
					}
				}
			}
		}
	}

	public static class Sphere extends Base<Sphere> {
		final int radius;
		BlockType outerBlockType, innerBlockType;
		boolean half;
		private Sphere(Level world, int x, int y, int z, int radius) {
			super(world,x,y,z);
			this.radius = radius;
			this.half = false;
		}
		public static Sphere setup(Level world, int x, int y, int z, int radius) {
			return new Sphere(world, x, y, z, radius);
		}
		public Sphere setBlock(BlockType type) {
			this.outerBlockType = type;
			this.innerBlockType = type;
			return this;
		}
		public Sphere setOuter(BlockType type) {
			this.outerBlockType = type;
			return this;
		}
		public Sphere setInner(BlockType type) {
			this.innerBlockType = type;
			return this;
		}
		public Sphere setHalf() {
			this.half = true;
			return this;
		}
		public void build() {
			double distance;
			for(int xc = 0; xc <= 2 * radius; xc++) {
				for(int zc = 0; zc <= 2 * radius; zc++) {
					for(int yc = 0; yc <= 2 * radius; yc++) {
						distance = Math.sqrt((xc - radius) * (xc - radius) + (zc - radius) * (zc - radius) + (yc - radius) * (yc - radius));
						int y_pos = y+yc-radius;
						if(y_pos >= y || !this.half) {
							if(distance > radius - 0.4 && distance < radius + 0.5) {
								Single.setup(world,x+xc-radius,y+yc-radius,z+zc-radius).setBlock(outerBlockType).build();
							} else if(distance < radius - 0.3) {
								Single.setup(world,x+xc-radius,y+yc-radius,z+zc-radius).setBlock(innerBlockType).build();
							}
						}
					}
				}
			}
		}
	}

	public static class Context extends BlockPlaceContext {
		public Context(Level world, BlockPos pos) {
			super(world, null, InteractionHand.MAIN_HAND, ItemStack.EMPTY, new BlockHitResult(Vec3.ZERO,Direction.DOWN,pos,false));
		}
	}
}