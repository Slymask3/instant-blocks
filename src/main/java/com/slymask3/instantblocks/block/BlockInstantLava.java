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
import net.minecraft.util.DamageSource;
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
import com.slymask3.instantblocks.utility.InstantBlocksFunctions;

public class BlockInstantLava extends Block {
	private InstantBlocksFunctions ibf = new InstantBlocksFunctions();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantLava() {
        super(Material.lava);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_LAVA);
        setHardness(0.5F);
        setResistance(2000F);
        //setStepSound(ib.soundLavaFootstep); //ORIGINAL, FIX 1.7.10 DONE
        //setStepSound(Block.soundTypeCloth);
        setStepSound(new SoundTypeLiquid("random.fizz", 1.0F, 1.0F));
        setLightLevel(1.0F);
    }
    
    public int quantityDropped(Random random) {
        return 1;
    }
    
	public IIcon getIcon(int side, int meta) {
		return Blocks.lava.getIcon(1, 0);
	}
	
	public static int checkLava = 0;
	
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (checkLava == 0) {
			triggerLavaMixEffects(world, x, y ,z, random);
			checkLava = 30;
		}
	}
	
	protected void triggerLavaMixEffects(World world, int x, int y, int z, Random rand) {
        ibf.sound(world, "random.fizz", x, y, z);
        
        for (int l = 0; l < 8; ++l) {
            world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
	
	// NO getAABBPool() METHOD IN AxisAlignedBB CLASS. 1.7.10
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
        entity.attackEntityFrom(DamageSource.lava, 1);
        entity.setFire(5);
    }
	
	public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				//player.triggerAchievement(ib.achLava);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		/*int liquid2 = Block.lavaStill.blockID;
		int liquid = Block.lavaMoving.blockID;
		int air = 0;*/
		
		Block liquid2 = Blocks.lava; // FIX TO lavaStill? 1.7.10
		Block liquid = Blocks.lava; // FIX TO lavaMoving? 1.7.10
		Block air = Blocks.air;
		
		int max = 6;
		
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
			ibf.keepBlocks(world, x, y, z, mb.ibLava);
			ibf.sound(world, config.sound, x, y, z);
			ibf.effectFull(world, "reddust", x, y, z);
			ibf.msg(player, "\u00a7aInstant Lava created with " + (ibf.counter + 1) + " lava blocks.", Colors.a);
			ibf.xp(world, player, config.xp);
			if (config.useWands == true) {
				is.damageItem(1, player);
			}
		} else {
			if (world.getBlock(x+1, y, z) != Blocks.air && world.getBlock(x-1, y, z) != Blocks.air && world.getBlock(x, y, z+1) != Blocks.air && world.getBlock(x, y, z-1) != Blocks.air && world.getBlock(x, y-1, z) != Blocks.air) {
				world.setBlock(x, y, z, liquid);
				ibf.keepBlocks(world, x, y, z, mb.ibLava);
				ibf.sound(world, config.sound, x, y, z);
				ibf.effectFull(world, "reddust", x, y, z);
				ibf.msg(player, ibf.lavaCreate1, Colors.a);
				ibf.xp(world, player, config.xp);
				if (config.useWands == true) {
					is.damageItem(1, player);
				}
			} else {
				ibf.msg(player, "\u00a7cPrevented from creating over " + config.max + " lava blocks.", Colors.c);
			}
		}
		
		ibf.counter = 0;
		ibf.c2 = 0;
		ibf.c5 = 0;
		ibf.built = false;
		
		return true;
    }
}