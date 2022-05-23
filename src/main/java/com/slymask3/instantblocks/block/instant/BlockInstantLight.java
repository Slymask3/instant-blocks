package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockIB;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantLight extends BlockIB {
	
    public BlockInstantLight() {
        super(ModBlocks.ibLight, Names.Blocks.IB_LIGHT, Material.wood, Block.soundTypeWood, 0.5F);
        setResistance(2000F);
        setCreateMsg(Strings.CREATE_LIGHT);
        setBlockTextureName(Textures.Light.SIDE);
        setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.90F, 0.625F);
        setLightLevel(1.0F);
    }

    public static IIcon side;
    public static IIcon top;
    
	public void registerBlockIcons(IIconRegister ir) {
		side = ir.registerIcon(Textures.Light.SIDE);
		top = ir.registerIcon(Textures.Light.TOP);
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return this.top;
		} else {
			return this.side;
		}
	}
	
//    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
//    {
//        byte b0 = 0;
//        float f = 0.0625F;
//        return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)((float)p_149668_3_ + (float)b0 * f), (double)p_149668_4_ + this.maxZ);
//    }
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        //int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = (double)((float)p_149734_2_ + 0.5F);
        double d1 = (double)((float)p_149734_3_ + 1.0F);
        double d2 = (double)((float)p_149734_4_ + 0.5F);
        //double d3 = 0.2199999988079071D;
        //double d4 = 0.27000001072883606D;

        p_149734_1_.spawnParticle("smoke", d0-0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("flame", d0-0.1, d1, d2, 0.0D, 0.0D, 0.0D);

        p_149734_1_.spawnParticle("smoke", d0+0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("flame", d0+0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        
        p_149734_1_.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        
        p_149734_1_.spawnParticle("smoke", d0, d1, d2-0.1, 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("flame", d0, d1, d2-0.1, 0.0D, 0.0D, 0.0D);
        
        p_149734_1_.spawnParticle("smoke", d0, d1, d2+0.1, 0.0D, 0.0D, 0.0D);
        p_149734_1_.spawnParticle("flame", d0, d1, d2+0.1, 0.0D, 0.0D, 0.0D);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.useWands) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}

		build(world, x, y, z, player);
    		
    	return true;
    }
    
    public void build(World world, int x, int y, int z, EntityPlayer player) {
		lightUp(world, x, y, z, Config.radiusLight, player);
	}
	
	public void lightUp(World world, int X, int Y, int Z, int radius, EntityPlayer player) {
		world.setBlock(X, Y, Z, Blocks.air);
		
		int amount = 0;
		
		Random rand = new Random();
    	
        int x = (int) (X -radius);
        int y = (int) (Y +radius);
        int z = (int) (Z -radius);
   
        int bx = x;
        int bz = z;
 
        for (int i=0; i<radius*2+1; i++) {
            for (int j=0; j<radius*2+1; j++) {
                for (int k=0; k<radius*2+1; k++) {
                    Block block = world.getBlock(x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    //LogHelper.info("getAmbientOcclusionLightValue() == "+block.getAmbientOcclusionLightValue());
                    //LogHelper.info("getBlockLightValue() == "+world.getBlockLightValue(x, y, z));

                    Block block1 = world.getBlock(x+1, y, z);
                    Block block2 = world.getBlock(x-1, y, z);
                    Block block4 = world.getBlock(x, y-1, z);
                    Block block5 = world.getBlock(x, y, z+1);
                    Block block6 = world.getBlock(x, y, z-1);
                    
                    //Block[] badBlocks = {Blocks.air, Blocks.water, Blocks.lava, Blocks.leaves, Blocks.leaves2, Blocks.reeds, Blocks.cactus, Blocks.rail, Blocks.glass, Blocks.glass_pane, Blocks.glowstone, Blocks.bed, Blocks.acacia_stairs, Blocks.oak_stairs, Blocks.spruce_stairs, Blocks.birch_stairs, Blocks.jungle_stairs, Blocks.dark_oak_stairs, Blocks.anvil, Blocks.brewing_stand, Blocks.cake, Blocks.carpet, Blocks.carrots, Blocks.potatoes, Blocks.wheat, Blocks.stone_brick_stairs, Blocks.brick_stairs, Blocks.stone_stairs, Blocks.sandstone_stairs, Blocks.nether_brick_stairs};
                    
                    //Block water = Blocks.water;
                    //Block lava = Blocks.lava;
                    
                    if((world.getBlockLightValue(x, y, z) < 10) && (block == Blocks.air) && (block1.isOpaqueCube() || block2.isOpaqueCube() || block4.isOpaqueCube() || block5.isOpaqueCube() || block6.isOpaqueCube())) {
                    	
                    	world.setBlock(x, y, z, Blocks.torch);
                    	amount++;
                    	
                    	
//                    	boolean place = false;
//                    	
//                    	for(int b=0; b<badBlocks.length; b++) {
//                    		if (block1 == badBlocks[b] || block2 == badBlocks[b] || block4 == badBlocks[b] || block5 == badBlocks[b] || block6 == badBlocks[b]) {
//                    			break;
//                    		}
//                    		
//                    		if (b==badBlocks.length-1) {
//                    			place = true;
//                    		}
//                    	}
//
//                		if(place) {
//                			world.setBlock(x, y, z, Blocks.torch);
//                        	amount++;
//                		}
                    }
                    
                    x++;
                }
                z++;
                x = bx;
            }
            z = bz;
            x = bx;
            y--;
        }
        
        if(amount > 0) {
        	IBHelper.msg(player, Strings.CREATE_LIGHT_AMOUNT.replace("%i%",String.valueOf(amount)), Colors.a);
            
            IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibLight);
    		IBHelper.xp(world, player, Config.xp);
    		IBHelper.sound(world, Config.sound, x, y, z);
    		IBHelper.effectFull(world, "reddust", x, y, z);
        } else {
    		world.setBlock(X, Y, Z, ModBlocks.ibLight);
	        IBHelper.msg(player, Strings.ERROR_LIGHT.replace("%i%",String.valueOf(Config.radiusLight)), Colors.c);
    		
        }
    }
	
//	public boolean canLightUp(World world, int X, int Y, int Z, int radius) {
//		int amount = 0;
//		
//		int x = (int) (X -radius);
//        int y = (int) (Y +radius);
//        int z = (int) (Z -radius);
//   
//        int bx = x;
//        int bz = z;
// 
//        for (int i=0; i<radius*2+1; i++) {
//            for (int j=0; j<radius*2+1; j++) {
//                for (int k=0; k<radius*2+1; k++) {
//                    Block block = world.getBlock(x, y, z);
//                    
//                    Block block1 = world.getBlock(x+1, y, z);
//                    Block block2 = world.getBlock(x-1, y, z);
//                    Block block4 = world.getBlock(x, y-1, z);
//                    Block block5 = world.getBlock(x, y, z+1);
//                    Block block6 = world.getBlock(x, y, z-1);
//                    
//                    if((world.getBlockLightValue(x, y, z) < 10) && (block == Blocks.air) && (block1.isOpaqueCube() || block2.isOpaqueCube() || block4.isOpaqueCube() || block5.isOpaqueCube() || block6.isOpaqueCube())) {
//                    	amount++;
//                    }
//                    
//                    x++;
//                }
//                z++;
//                x = bx;
//            }
//            z = bz;
//            x = bx;
//            y--;
//        }
//        
//        if (amount > 0) {
//        	return true;
//        } else {
//        	return false;
//        }
//    }
}