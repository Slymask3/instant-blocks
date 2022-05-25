package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstantLiquid;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.sound.SoundTypeLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantWater extends BlockInstantLiquid {
    public BlockInstantWater() {
        super(ModBlocks.ibWater, Names.Blocks.IB_WATER, Material.water, new SoundTypeLiquid("random.splash", 1.0F, 1.0F), 0.5F, Blocks.air, Blocks.water);
        setTextures(Blocks.water);
        setTextureBooleans(false, false, false, false, false, false);
        setLightOpacity(3);
        setBlockTextureName(Textures.Water.SIDE);
		setErrorMsg(Strings.ERROR_WATER_MAX.replace("%i%",String.valueOf(Config.MAX_LIQUID)));
		this.create = Strings.CREATE_WATER;
		this.create1 = Strings.CREATE_WATER_1;
    }

	int checkWater = 0;
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if(checkWater == 0) {
			for(int l = 0; l < 8; ++l) {
	            world.spawnParticle("splash", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
	        }
			checkWater = 5;
		} else if(checkWater > 0) {
        	checkWater--;
        }
	}
	
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
}