package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.Coords;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public abstract class BlockInstantLiquid extends BlockInstant {
	public ArrayList<Coords> coordsList;
	public Block blockCheck;
	public Block blockReplace;
	public String create;
	public String create1;
	public boolean isSuction = false;
	public String particle = null;

    public BlockInstantLiquid(String name, Material material, SoundType soundType, float hardness, Block blockCheck, Block blockReplace) {
        super(name, material, soundType, hardness);
		this.coordsList = new ArrayList<>();
		this.blockCheck = blockCheck;
		this.blockReplace = blockReplace;
    }

	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if(this.particle != null && random.nextInt(10) == 0) {
			for(int i=0; i<8; i++) {
				world.spawnParticle(this.particle, (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		if(this.isSuction) {
			return super.getCollisionBoundingBoxFromPool(world,x,y,z);
		} else  {
			float f = 0.0625F;
			return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
		}
	}

	public boolean renderAsNormalBlock() {
		return this.isSuction;
	}

	public boolean isOpaqueCube() {
		return this.isSuction;
	}

	public int getRenderBlockPass() {
		return this.blockMaterial == Material.water ? 1 : 0;
	}

	private int getMax() {
		return isSuction ? Config.MAX_FILL : Config.MAX_LIQUID;
	}

	private Block getMainReplaceBlock() {
		if(isSuction) {
			return blockCheck == Blocks.water ? ModBlocks.ibWater : ModBlocks.ibLava;
		}
		return blockReplace;
	}

	public boolean canActivate(World world, int x, int y, int z, EntityPlayer player) {
		if(isSuction && Config.SHOW_EFFECTS) {
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 1.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z + 1.2D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y - 0.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x - 0.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z - 0.2D, 0.0D, 0.0D, 0.0D);
		}
		checkForBlock(world,x,y,z);
		if(isSuction && coordsList.isEmpty()) {
			IBHelper.msg(player, Strings.ERROR_NO_LIQUID, Colors.c);
			return false;
		}
		if(coordsList.size() >= getMax()) {
			IBHelper.msg(player, errorMessage, Colors.c);
			coordsList = new ArrayList<>();
			return false;
		} else {
			return true;
		}
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		for(Coords coords : coordsList) {
			BuildHelper.setBlock(world, coords.getX(), coords.getY(), coords.getZ(), blockReplace);
		}
		BuildHelper.setBlock(world,x, y, z, getMainReplaceBlock());
		if(coordsList.size() > 0) {
			setCreateMessage(create.replace("%i%",String.valueOf(isSuction ? coordsList.size() : coordsList.size()+1)).replace("%type%",blockCheck == Blocks.water ? "water" : "lava"));
		} else {
			setCreateMessage(create1.replace("%type%",blockCheck == Blocks.water ? "water" : "lava"));
		}
		coordsList = new ArrayList<>();
		if(isSuction) {
			this.blockCheck = null;
		}
	}

	private void checkForBlock(World world, int x, int y, int z) {
		check(world,x+1,y,z);
		check(world,x-1,y,z);
		check(world,x,y,z+1);
		check(world,x,y,z-1);
		if(!Config.SIMPLE_LIQUID || isSuction) {
			check(world,x,y-1,z);
		}
		if(isSuction) {
			check(world,x,y+1,z);
		}
	}

	private void check(World world, int x, int y, int z) {
		Block blockCurrent = BuildHelper.getBlock(world,x,y,z);
		if(isCorrectBlock(blockCurrent) && coordsList.size() < getMax() && addCoords(x,y,z)) {
			if(blockCheck == null) {
				if(blockCurrent == Blocks.flowing_water) {
					blockCheck = Blocks.water;
				} else if(blockCurrent == Blocks.flowing_lava) {
					blockCheck = Blocks.lava;
				} else {
					blockCheck = blockCurrent;
				}
			}
			checkForBlock(world,x,y,z);
		}
	}

	private boolean isCorrectBlock(Block block) {
		if(blockCheck == null) {
			return block == Blocks.water || block == Blocks.lava || block == Blocks.flowing_water || block == Blocks.flowing_lava;
		} else if(blockCheck == Blocks.air) {
			return block == blockCheck || block instanceof BlockBush || block == Blocks.flowing_water || block == Blocks.flowing_lava;
		} else if(blockCheck == Blocks.water) {
			return block == blockCheck || block == Blocks.flowing_water;
		} else if(blockCheck == Blocks.lava) {
			return block == blockCheck || block == Blocks.flowing_lava;
		}
		return block == blockCheck;
	}

	private boolean addCoords(int x, int y, int z) {
		Coords coords = new Coords(x,y,z);
		if(!coordsList.contains(coords)) {
			coordsList.add(coords);
			return true;
		}
		return false;
	}
}