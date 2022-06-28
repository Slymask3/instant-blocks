package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
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

public class BuildHelper {
	/***************
	 * @INFO	   *
	 * x+# = EAST  *
	 * x-# = WEST  *
	 * z+# = SOUTH *
	 * z-# = NORTH *
	 * 			   *
	 * y+# = UP    *
	 * y-# = DOWN  *
	 ***************/

	public static void setBlock(Level world, int x, int y, int z, Block block) {
		setBlock(world,x,y,z,block,null,2);
	}

	public static void setBlock(Level world, int x, int y, int z, Block block, Direction direction) {
		setBlock(world,x,y,z,block,direction,2);
	}

	public static void setBlock(Level world, BlockPos pos, Block block) {
		setBlock(world,pos.getX(),pos.getY(),pos.getZ(),block,null,2);
	}

	public static void setBlock(Level world, BlockPos pos, Block block, Direction direction) {
		setBlock(world,pos.getX(),pos.getY(),pos.getZ(),block,direction,2);
	}

	public static void setBlock(Level world, int x, int y, int z, Block block, Direction direction, int flag) {
		setBlock(world,x,y,z,block.defaultBlockState(),direction,flag);
	}

	public static void setBlock(Level world, BlockPos pos, BlockState state) {
		setBlock(world,pos.getX(),pos.getY(),pos.getZ(),state, null,2);
	}

	public static void setBlock(Level world, int x, int y, int z, BlockState state) {
		setBlock(world,x,y,z,state,null, 2);
	}

	public static void setBlock(Level world, int x, int y, int z, BlockState state, int flag) {
		setBlock(world,x,y,z,state,null,flag);
	}
	
	public static void setBlock(Level world, int x, int y, int z, BlockState state, Direction direction, int flag) {
		Block block = state.getBlock();
		Block getBlock = getBlock(world,x,y,z);
		if(Config.Common.KEEP_BLOCKS.get() && getBlock instanceof InstantBlock) {
			return;
		}
		if(world.dimension().equals(Level.NETHER) && block.equals(Blocks.WATER) && !Config.Common.ALLOW_WATER_IN_NETHER.get()) {
			state = Blocks.AIR.defaultBlockState(); //replace water with air in the nether
		}
		if(canSet(getBlock)) {
			if(block instanceof CrossCollisionBlock) {
				BlockPlaceContext context = new BlockPlaceContext(world,null, InteractionHand.MAIN_HAND, ItemStack.EMPTY,new BlockHitResult(Vec3.ZERO,Direction.DOWN,new BlockPos(x,y,z),false));
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
		}
	}

	public static void setColorBlock(Level world, int x, int y, int z, int color) {
		setBlock(world, x, y, z, ModBlocks.COLOR.get());
		try {
			ColorBlockEntity entity = (ColorBlockEntity)world.getBlockEntity(new BlockPos(x, y, z));
			if(entity != null) {
				entity.color = color;
			}
		} catch(Exception e) {
			InstantBlocks.LOGGER.info(e);
		}
	}

	public static Block getBlock(Level world, int x, int y, int z) {
		return world.getBlockState(new BlockPos(x,y,z)).getBlock();
	}

	public static void build(Level world, int x_start, int y_start, int z_start, Block block, int x_times, int y_times, int z_times) {
		build(world, x_start, y_start, z_start, block, null, 2, x_times, y_times, z_times);
	}
	
	public static void build(Level world, int x_start, int y_start, int z_start, Block block, Direction direction, int x_times, int y_times, int z_times) {
		build(world, x_start, y_start, z_start, block, direction, 2, x_times, y_times, z_times);
	}

	public static void build(Level world, int x_start, int y_start, int z_start, Block block, Direction direction, int flag, int x_times, int y_times, int z_times) {
		for(int y=y_start; y<y_start+y_times; y++) {
			for(int x=x_start; x<x_start+x_times; x++) {
				for(int z=z_start; z<z_start+z_times; z++) {
					setBlock(world, x, y, z, block, direction, flag);
				}
			}
		}
	}

	public static void setBlockDirectional(Level world, int x, int y, int z, Block block, Direction direction, int forward, int back, int left, int right) {
		setBlockDirectional(world, x, y, z, block, direction, forward, back, left, right, direction, 2);
	}

	public static void setBlockDirectional(Level world, int x, int y, int z, Block block, Direction direction, int forward, int back, int left, int right, Direction directionBlock) {
		setBlockDirectional(world, x, y, z, block, direction, forward, back, left, right, directionBlock, 2);
	}
    
    public static void setBlockDirectional(Level world, int x, int y, int z, Block block, Direction direction, int forward, int back, int left, int right, Direction directionBlock, int flag) {
    	if(direction == Direction.SOUTH) {
    		setBlock(world, x-left+right, y, z-forward+back, block, directionBlock, flag);
		} else if(direction == Direction.WEST) {
			setBlock(world, x+forward-back, y, z-left+right, block, directionBlock, flag);
		} else if(direction == Direction.NORTH) {
			setBlock(world, x+left-right, y, z+forward-back, block, directionBlock, flag);
		} else if(direction == Direction.EAST) {
			setBlock(world, x-forward+back, y, z+left-right, block, directionBlock, flag);
		}
    }

	public static void buildDirectional(Level world, int x, int y, int z, Block block, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
		Directional.setup(world, x, y, z, direction, forward, back, left, right, forwardX, backX, leftX, rightX, upX, downX).setBlock(block).build();
	}
    
    public static void setColorBlockDirectional(Level world, int x, int y, int z, BufferedImage img, int imgx, int imgy, Direction direction, int forwardBack, int leftRight, boolean rgb) {
		int forward = Helper.isPositive(forwardBack) ? 0 : Helper.toPositive(forwardBack);
		int back = Helper.isPositive(forwardBack) ? forwardBack : 0;
		int left = Helper.isPositive(leftRight) ? leftRight : 0;
		int right = Helper.isPositive(leftRight) ? 0 : Helper.toPositive(leftRight);
    	if(rgb) {
			int color = img.getRGB(imgx, imgy);
    		if(direction == Direction.SOUTH) {
				setColorBlock(world,x+left-right, y, z-forward+back,color);
    		} else if(direction == Direction.WEST) {
				setColorBlock(world,x+forward-back, y, z+left-right,color);
    		} else if(direction == Direction.NORTH) {
				setColorBlock(world,x-left+right, y, z+forward-back,color);
    		} else if(direction == Direction.EAST) {
				setColorBlock(world,x-forward+back, y, z-left+right,color);
    		}
		} else {
			Block wool = ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy));
    		if(direction == Direction.SOUTH) {
        		setBlock(world, x+left-right, y, z-forward+back, wool);
    		} else if(direction == Direction.WEST) {
    			setBlock(world, x+forward-back, y, z+left-right, wool);
    		} else if(direction == Direction.NORTH) {
    			setBlock(world, x-left+right, y, z+forward-back, wool);
    		} else if(direction == Direction.EAST) {
    			setBlock(world, x-forward+back, y, z-left+right, wool);
    		}
		}
    }

	public static BlockEntity getBlockEntityDirectional(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right) {
		if(direction == Direction.SOUTH) {
			return world.getBlockEntity(new BlockPos(x-left+right, y, z-forward+back));
		} else if(direction == Direction.WEST) {
			return world.getBlockEntity(new BlockPos(x+forward-back, y, z+left-right));
		} else if(direction == Direction.NORTH) {
			return world.getBlockEntity(new BlockPos(x+left-right, y, z+forward-back));
		} else if(direction == Direction.EAST) {
			return world.getBlockEntity(new BlockPos(x-forward+back, y, z-left+right));
		}
		return null;
	}

	public static void setStone(Level world, int x, int y, int z) {
		setStone(world, new BlockPos(x,y,z));
	}

	public static void setStone(Level world, BlockPos pos) {
		ResourceKey<Level> dimension = world.dimension();
		if(dimension.equals(Level.OVERWORLD)) {
			if (pos.getY() > 8) {
				setBlock(world, pos, Blocks.STONE);
			} else if (pos.getY() < 0) {
				setBlock(world, pos, Blocks.DEEPSLATE);
			} else { //0,1,2,3,4,5,6,7,8
				ArrayList<Helper.WeightedBlock> blocks = new ArrayList<>();
				blocks.add(new Helper.WeightedBlock(Blocks.STONE, pos.getY() + 1));          //1,2,3,4,5,6,7,8,9
				blocks.add(new Helper.WeightedBlock(Blocks.DEEPSLATE, 10 - pos.getY() + 1)); //9,8,7,6,5,4,3,2,1
				setBlock(world, pos, Helper.getRandomBlock(blocks));
			}
		} else if(dimension.equals(Level.NETHER)) {
			setBlock(world, pos, Blocks.NETHERRACK);
		} else if(dimension.equals(Level.END)) {
			setBlock(world, pos, Blocks.END_STONE);
		} else {
			setBlock(world, pos, Blocks.STONE);
		}
	}

	public static void setStoneDirectional(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right) {
		if(direction == Direction.SOUTH) {
			setStone(world, x-left+right, y, z-forward+back);
		} else if(direction == Direction.WEST) {
			setStone(world, x+forward-back, y, z-left+right);
		} else if(direction == Direction.NORTH) {
			setStone(world, x+left-right, y, z+forward-back);
		} else if(direction == Direction.EAST) {
			setStone(world, x-forward+back, y, z+left-right);
		}
	}

	public static void buildStone(Level world, int x_start, int y_start, int z_start, int x_times, int y_times, int z_times) {
		for(int y=y_start; y<y_start+y_times; y++) {
			for(int x=x_start; x<x_start+x_times; x++) {
				for(int z=z_start; z<z_start+z_times; z++) {
					setStone(world, x, y, z);
				}
			}
		}
	}

	public static void buildStoneDirectional(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
		Directional.setup(world, x, y, z, direction, forward, back, left, right, forwardX, backX, leftX, rightX, upX, downX).setStone(true).build();
	}

	private static boolean canSet(Block block) {
		return block.defaultDestroyTime() >= 0F || block.equals(Blocks.AIR);
	}

	public static class Directional {
		Level world;
		int x,y,z;
		Direction direction;
		int forward,back,left,right;
		int forwardX,backX,leftX,rightX,upX,downX;
		Block block;
		Direction blockDirection;
		int flag;
		boolean setStone;
		private Directional(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.direction = direction;
			this.forward = forward;
			this.back = back;
			this.left = left;
			this.right = right;
			this.forwardX = forwardX;
			this.backX = backX;
			this.leftX = leftX;
			this.rightX = rightX;
			this.upX = upX;
			this.downX = downX;
			this.block = null;
			this.blockDirection = null;
			this.setStone = false;
			this.flag = 2;
		}
		public Directional setBlock(Block block) {
			this.block = block;
			return this;
		}
		public Directional setStone(boolean setStone) {
			this.setStone = setStone;
			return this;
		}
		public static Directional setup(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
			return new Directional(world, x, y, z, direction, forward, back, left, right, forwardX, backX, leftX, rightX, upX, downX);
		}
		public void build() {
			int x1 = x, z1 = z, x2 = x, z2 = z;
			int y1 = y;
			int y2 = y1+upX-downX;
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

			int xDif = Helper.toPositive(x1 - x2);
			int yDif = Helper.toPositive(y1 - y2);
			int zDif = Helper.toPositive(z1 - z2);

			int x_cur = x1;
			int y_cur = y1;
			int z_cur = z1;

			int bx = x1;
			int bz = z1;

			for (int i=0; i<yDif+1; i++) {
				for (int j=0; j<zDif+1; j++) {
					for (int k=0; k<xDif+1; k++) {

						if(setStone) {
							BuildHelper.setStone(world,x_cur, y_cur, z_cur);
						} else {
							BuildHelper.setBlock(world,x_cur, y_cur, z_cur, block, blockDirection, flag);
						}

						if(Helper.isPositive(x1 - x2)) {
							x_cur--;
						} else {
							x_cur++;
						}
					}
					x_cur = bx;

					if(Helper.isPositive(z1 - z2)) {
						z_cur--;
					} else {
						z_cur++;
					}
				}
				z_cur = bz;
				x_cur = bx;

				if(Helper.isPositive(y1 - y2)) {
					y_cur--;
				} else {
					y_cur++;
				}
			}
		}
	}

	public static class Circle {
		Level world;
		int x,y,z;
		int radius;
		Block outer, inner;
		public Circle(Level world, int x, int y, int z, int radius, Block outer, Block inner) {
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.radius = radius;
			this.outer = outer;
			this.inner = inner;
		}
		public static Circle setup (Level world, int x, int y, int z, int radius, Block outer, Block inner) {
			return new Circle(world, x, y, z, radius, outer, inner);
		}
		public static Circle setup (Level world, int x, int y, int z, int radius, Block block) {
			return new Circle(world, x, y, z, radius, block, block);
		}
		public void build() {
			double distance;
			for(int row = 0; row <= 2 * radius; row++) {
				for(int col = 0; col <= 2 * radius; col++) {
					distance = Math.sqrt((row - radius) * (row - radius) + (col - radius) * (col - radius));
					if(distance > radius - 0.4 && distance < radius + 0.5) {
						BuildHelper.setBlock(world,x+row-radius,y,z+col-radius,outer);
					} else if(distance < radius - 0.3) {
						BuildHelper.setBlock(world,x+row-radius,y,z+col-radius,inner);
					}
				}
			}
		}
	}
}