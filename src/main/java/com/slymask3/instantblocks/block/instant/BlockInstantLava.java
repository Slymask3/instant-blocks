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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantLava extends BlockInstantLiquid {
	public BlockInstantLava() {
		super(ModBlocks.ibLava, Names.Blocks.IB_LAVA, Material.lava, new SoundTypeLiquid("random.fizz", 1.0F, 1.0F), 0.5F, Blocks.air, Blocks.lava);
		setTextures(Blocks.lava);
		setTextureBooleans(false, false, false, false, false, false);
		setLightLevel(1.0F);
		setBlockTextureName(Textures.Lava.SIDE);
		setErrorMsg(Strings.ERROR_LAVA_MAX.replace("%i%",String.valueOf(Config.MAX_LIQUID)));
		this.create = Strings.CREATE_LAVA;
		this.create1 = Strings.CREATE_LAVA_1;
	}

	int checkLava = 0;
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if(checkLava == 0) {
	        for(int l = 0; l < 8; ++l) {
	            world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
	        }
			checkLava = 5;
		} else if(checkLava > 0) {
        	checkLava--;
        }
	}
	
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
}