package com.slymask3.instantblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.sound.SoundTypeLiquid;
import com.slymask3.instantblocks.utility.BuildHelper;

public class BlockInstantWater extends Block {
	private BuildHelper ibf = new BuildHelper();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantWater() {
        super(Material.water);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_WATER);
        setHardness(0.5F);
        setResistance(2000F);
        //setStepSound(ib.soundWaterFootstep); //ORIGINAL, FIX 1.7.10 DONE
        setStepSound(new SoundTypeLiquid("random.splash", 1.0F, 1.0F));
        setLightOpacity(3);
    }
    
    public int quantityDropped(Random random) {
        return 1;
    }
    
	public IIcon getIcon(int side, int meta) {
		return Blocks.water.getIcon(1, 0);
	}
	
	public static int checkWater = 0;
	
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (checkWater == 0) {
			triggerWaterBubbles(world, x, y ,z);
			checkWater = 30;
		}
	}
	
	protected void triggerWaterBubbles(World world, int x, int y, int z) {
        for (int l = 0; l < 8; ++l) {
            world.spawnParticle("bubble", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
	
	// NO getAABBPool() METHOD IN AxisAlignedBB CLASS. 1.7.10
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
        entity.extinguish();
    }
	
	public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }
    
    public int getRenderBlockPass() {
        return this.blockMaterial == Material.water ? 1 : 0;
    }
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				//player.triggerAchievement(ib.achWater);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		Block liquid2 = Blocks.water; // FIX TO waterStill? 1.7.10
		Block liquid = Blocks.water; // FIX TO waterMoving? 1.7.10
		Block air = Blocks.air;
    	
		if (config.simpleWL == true) {
			ibf.checkLiquid5S(world, x, y, z, liquid);
			ibf.buildLiquid4S(world, x, y, z, liquid);
			ibf.checkLiquid5UndoS(world, x, y, z, liquid);
		} else {
			ibf.checkLiquid5(world, x, y, z, liquid);
			ibf.buildLiquid4(world, x, y, z, liquid);
			ibf.checkLiquid5Undo(world, x, y, z, liquid);
		}
		
		if (ibf.built == true) {
			world.setBlock(x, y, z, liquid);
			ibf.keepBlocks(world, x, y, z, mb.ibWater);
			ibf.sound(world, config.sound, x, y, z);
			ibf.effectFull(world, "reddust", x, y, z);
			ibf.msg(player, "\u00a7aInstant Water created with " + (ibf.counter + 1) + " water blocks.", Colors.a);
			ibf.xp(world, player, config.xp);
			if (config.useWands == true) {
				is.damageItem(1, player);
			}
		} else {
			if (world.getBlock(x+1, y, z) != Blocks.air && world.getBlock(x-1, y, z) != Blocks.air && world.getBlock(x, y, z+1) != Blocks.air && world.getBlock(x, y, z-1) != Blocks.air && world.getBlock(x, y-1, z) != Blocks.air) {
				world.setBlock(x, y, z, liquid);
				ibf.keepBlocks(world, x, y, z, mb.ibWater);
				ibf.sound(world, config.sound, x, y, z);
				ibf.effectFull(world, "reddust", x, y, z);
				ibf.msg(player, ibf.waterCreate1, Colors.a);
				ibf.xp(world, player, config.xp);
				if (config.useWands == true) {
					is.damageItem(1, player);
				}
			} else {
				ibf.msg(player, "\u00a7cPrevented from creating over " + config.max + " water blocks.", Colors.c);
			}
		}
		
		ibf.counter = 0;
		ibf.c2 = 0;
		ibf.c5 = 0;
		ibf.built = false;
		
		return true;
    }
}