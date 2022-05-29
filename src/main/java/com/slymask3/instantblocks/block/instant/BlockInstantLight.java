package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantLight extends BlockInstant {
    public BlockInstantLight() {
        super(Names.Blocks.IB_LIGHT, Material.wood, Block.soundTypeWood, 0.5F);
        setCreateMessage(Strings.CREATE_LIGHT);
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
		if(side == 1) {
			return this.top;
		} else {
			return this.side;
		}
	}
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        double d0 = x + 0.5F;
        double d1 = y + 0.5F;
        double d2 = z + 0.5F;
        world.spawnParticle("smoke", d0-0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", d0-0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("smoke", d0+0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", d0+0.1, d1, d2, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("smoke", d0, d1, d2-0.1, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", d0, d1, d2-0.1, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("smoke", d0, d1, d2+0.1, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", d0, d1, d2+0.1, 0.0D, 0.0D, 0.0D);
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
    
    public void build(World world, int x, int y, int z, EntityPlayer player) {
		lightUp(world, x, y, z, Config.RADIUS_LIGHT, player);
	}
	
	public void lightUp(World world, int X, int Y, int Z, int radius, EntityPlayer player) {
		BuildHelper.setBlock(world,X, Y, Z, Blocks.air);
		
		int amount = 0;
    	
        int x = (int) (X -radius);
        int y = (int) (Y +radius);
        int z = (int) (Z -radius);
   
        int bx = x;
        int bz = z;
 
        for(int i=0; i<radius*2+1; i++) {
            for(int j=0; j<radius*2+1; j++) {
                for(int k=0; k<radius*2+1; k++) {
                    Block block = BuildHelper.getBlock(world,x, y, z);
                    Block block1 = BuildHelper.getBlock(world,x+1, y, z);
                    Block block2 = BuildHelper.getBlock(world,x-1, y, z);
                    Block block4 = BuildHelper.getBlock(world,x, y-1, z);
                    Block block5 = BuildHelper.getBlock(world,x, y, z+1);
                    Block block6 = BuildHelper.getBlock(world,x, y, z-1);
                    if((world.getBlockLightValue(x, y, z) < 10) && (block == Blocks.air) && (block1.isOpaqueCube() || block2.isOpaqueCube() || block4.isOpaqueCube() || block5.isOpaqueCube() || block6.isOpaqueCube())) {
                    	BuildHelper.setBlock(world,x, y, z, Blocks.torch);
                    	amount++;
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
    		IBHelper.xp(world, player, Config.XP_AMOUNT);
    		IBHelper.sound(world, Config.SOUND, x, y, z);
    		IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
        } else {
    		BuildHelper.setBlock(world,X, Y, Z, ModBlocks.ibLight);
	        IBHelper.msg(player, Strings.ERROR_LIGHT.replace("%i%",String.valueOf(Config.RADIUS_LIGHT)), Colors.c);
    		
        }
    }
}