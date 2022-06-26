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
		Block getBlock = getBlock(world,x,y,z);
		if(Config.Common.KEEP_BLOCKS.get() && getBlock instanceof InstantBlock) {
			return;
		}
		if(canSet(getBlock)) {
			BlockState state = block.defaultBlockState();
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

	public static void setBlock(Level world, BlockPos pos, BlockState state) {
		setBlock(world,pos.getX(),pos.getY(),pos.getZ(),state,2);
	}

	public static void setBlock(Level world, int x, int y, int z, BlockState state) {
		setBlock(world,x,y,z,state,3);
	}

	public static void setBlock(Level world, int x, int y, int z, BlockState state, int flag) {
		world.setBlock(new BlockPos(x,y,z),state,flag);
	}

	public static void setBlockLight(Level world, BlockPos pos, Block block, Direction direction) {
		BlockState state = block.defaultBlockState();
		if(direction != null && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
			state = state.setValue(BlockStateProperties.HORIZONTAL_FACING,direction);
		}
		world.setBlockAndUpdate(pos,state);
	}

	public static void setColorBlock(Level world, int x, int y, int z, int color) {
		setBlock(world, x, y, z, ModBlocks.COLOR.get());
		try {
			((ColorBlockEntity) world.getBlockEntity(new BlockPos(x, y, z))).color = color;
		} catch(Exception e) {
			InstantBlocks.LOGGER.info(e);
		}
		//world.markBlockForUpdate(x, y, z);
	}

	public static Block getBlock(Level world, int x, int y, int z) {
		return world.getBlockState(new BlockPos(x,y,z)).getBlock();
	}

	public static void build(Level world, int x, int y, int z, Block block, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		build(world, x, y, z, block, null, 2, xTimesTotal, yTimesTotal, zTimesTotal);
	}
	
	public static void build(Level world, int x, int y, int z, Block block, Direction direction, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		build(world, x, y, z, block, direction, 2, xTimesTotal, yTimesTotal, zTimesTotal);
	}

	public static void build(Level world, int x, int y, int z, Block block, Direction direction, int flag, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		for(int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for(int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for(int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setBlock(world, x2, y, z2, block, direction, flag);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}

	public static void build(Level world, int x, int y, int z, BlockState state, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		for(int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for(int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for(int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setBlock(world, x2, y, z2, state, 2);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
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
		buildDirectional(world, x, y, z, block, direction, forward, back, left, right, forwardX, backX, leftX, rightX, upX, downX, null, 2);
	}

	public static void buildDirectional(Level world, int x, int y, int z, Block block, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX, Direction directionBlock, int flag) {
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

					BuildHelper.setBlock(world,x_cur, y_cur, z_cur, block, directionBlock, flag);

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
    
    public static void setColorBlockComplex(Level world, int x, int y, int z, BufferedImage img, int imgx, int imgy, Direction direction, int forwardBack, int leftRight, boolean rgb) {
		int forward;
		int back;
		int left;
		int right;
    	if(rgb) {
    		if(Helper.isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = Helper.toPositive(forwardBack);
    		}
    		if(Helper.isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = Helper.toPositive(leftRight);
    			left = 0;
    		}
    		if(direction == Direction.SOUTH) {
				setColorBlock(world,x+left-right, y, z-forward+back,img.getRGB(imgx, imgy));
    		} else if(direction == Direction.WEST) {
				setColorBlock(world,x+forward-back, y, z+left-right,img.getRGB(imgx, imgy));
    		} else if(direction == Direction.NORTH) {
				setColorBlock(world,x-left+right, y, z+forward-back,img.getRGB(imgx, imgy));
    		} else if(direction == Direction.EAST) {
				setColorBlock(world,x-forward+back, y, z-left+right,img.getRGB(imgx, imgy));
    		}
		} else {
    		if(Helper.isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = Helper.toPositive(forwardBack);
    		}
    		if(Helper.isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = Helper.toPositive(leftRight);
    			left = 0;
    		}
    		if(direction == Direction.SOUTH) {
        		setBlock(world, x+left-right, y, z-forward+back, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)));
    		} else if(direction == Direction.WEST) {
    			setBlock(world, x+forward-back, y, z+left-right, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)));
    		} else if(direction == Direction.NORTH) {
    			setBlock(world, x-left+right, y, z+forward-back, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)));
    		} else if(direction == Direction.EAST) {
    			setBlock(world, x-forward+back, y, z-left+right, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)));
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

	public static void buildStone(Level world, int x, int y, int z, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		for(int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for(int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for(int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setStone(world, new BlockPos(x2, y, z2));
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}

	public static void buildStoneDirectional(Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
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

					setStone(world,x_cur, y_cur, z_cur);

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

	private static boolean canSet(Block block) {
		return block.defaultDestroyTime() >= 0F || block.equals(Blocks.AIR);
	}
}