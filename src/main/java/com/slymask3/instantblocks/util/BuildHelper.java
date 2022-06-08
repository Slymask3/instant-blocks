package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.awt.image.BufferedImage;

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
	
	public static void setBlock(Level world, int x, int y, int z, Block block, Direction direction, int flag) {
		Block getBlock = getBlock(world,x,y,z);
		if(Config.Common.KEEP_BLOCKS.get() && getBlock instanceof BlockInstant) {
			return;
		}
		if(getBlock != Blocks.BEDROCK) {
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
		setBlock(world, x, y, z, ModBlocks.color.get());
		try {
			((TileEntityColor) world.getBlockEntity(new BlockPos(x, y, z))).color = color;
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

		int xDif = IBHelper.toPositive(x1 - x2);
		int yDif = IBHelper.toPositive(y1 - y2);
		int zDif = IBHelper.toPositive(z1 - z2);

		int x_cur = x1;
		int y_cur = y1;
		int z_cur = z1;

		int bx = x1;
		int bz = z1;

		for (int i=0; i<yDif+1; i++) {
			for (int j=0; j<zDif+1; j++) {
				for (int k=0; k<xDif+1; k++) {

					BuildHelper.setBlock(world,x_cur, y_cur, z_cur, block, directionBlock, flag);

					if(IBHelper.isPositive(x1 - x2)) {
						x_cur--;
					} else {
						x_cur++;
					}
				}
				x_cur = bx;

				if(IBHelper.isPositive(z1 - z2)) {
					z_cur--;
				} else {
					z_cur++;
				}
			}
			z_cur = bz;
			x_cur = bx;

			if(IBHelper.isPositive(y1 - y2)) {
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
    		if(IBHelper.isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = IBHelper.toPositive(forwardBack);
    		}
    		if(IBHelper.isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = IBHelper.toPositive(leftRight);
    			left = 0;
    		}
    		if(direction == Direction.SOUTH) {
        		setBlock(world, x+left-right, y, z-forward+back, ModBlocks.color.get());
        		((TileEntityColor) world.getBlockEntity(new BlockPos(x+left-right, y, z-forward+back))).color = img.getRGB(imgx, imgy);
    			//world.markBlockForUpdate(x+left-right, y, z-forward+back);
    		} else if(direction == Direction.WEST) {
    			setBlock(world, x+forward-back, y, z+left-right, ModBlocks.color.get());
    			((TileEntityColor) world.getBlockEntity(new BlockPos(x+forward-back, y, z+left-right))).color = img.getRGB(imgx, imgy);
    			//world.markBlockForUpdate(x+forward-back, y, z+left-right);
    		} else if(direction == Direction.NORTH) {
    			setBlock(world, x-left+right, y, z+forward-back, ModBlocks.color.get());
    			((TileEntityColor) world.getBlockEntity(new BlockPos(x-left+right, y, z+forward-back))).color = img.getRGB(imgx, imgy);
    			//world.markBlockForUpdate(x-left+right, y, z+forward-back);
    		} else if(direction == Direction.EAST) {
    			setBlock(world, x-forward+back, y, z-left+right, ModBlocks.color.get());
    			((TileEntityColor) world.getBlockEntity(new BlockPos(x-forward+back, y, z-left+right))).color = img.getRGB(imgx, imgy);
    			//world.markBlockForUpdate(x-forward+back, y, z-left+right);
    		}
		} else {
    		if(IBHelper.isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = IBHelper.toPositive(forwardBack);
    		}
    		if(IBHelper.isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = IBHelper.toPositive(leftRight);
    			left = 0;
    		}
    		if(direction == Direction.SOUTH) {
        		setBlock(world, x+left-right, y, z-forward+back, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)));
    		} else if(direction == Direction.WEST) {
    			setBlock(world, x+forward-back, y, z+left-right, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)));
    		} else if(direction == Direction.NORTH) {
    			setBlock(world, x-left+right, y, z+forward-back, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)));
    		} else if(direction == Direction.EAST) {
    			setBlock(world, x-forward+back, y, z-left+right, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)));
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
}