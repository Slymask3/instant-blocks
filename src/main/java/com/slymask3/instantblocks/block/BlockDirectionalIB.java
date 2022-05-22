package com.slymask3.instantblocks.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDirectionalIB extends BlockIB {

	protected BlockDirectionalIB(Block block, String name, Material material, SoundType soundType, float hardness) {
		super(block, name, material, soundType, hardness);
	}
	
	public static int getDirection(int meta) {
        return meta & 3;
    }
	
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase entity, ItemStack is) {
        int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);
    }
	
}
