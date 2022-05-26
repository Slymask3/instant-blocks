package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.LogHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.io.File;

public class BlockInstantSchematic extends BlockInstant {
	public BlockInstantSchematic() {
		super(Names.Blocks.IB_SCHEMATIC, Material.wood, Block.soundTypeWood, 1.5F);
        setBlockTextureName(Textures.Harvest.SIDE0);
		setGuiID(GuiID.SCHEMATIC);
	}

	public static IIcon top;
	public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(Textures.Schematic.TOP);
		side = ir.registerIcon(Textures.Schematic.SIDE);
	}
    
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return Blocks.planks.getIcon(0, 5);
		} else if(side == 1) {
			return top;
		} else {
			return this.side;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySchematic();
	}
	
	public void build(World world, int x, int y, int z, int meta, String playerS, String schematic, boolean center, boolean ignoreAir) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		
		try {
			File f = new File("schematics/"+schematic+".schematic");
			
			SchematicHelper.loadSchematic(world, x, y ,z, f, center, ignoreAir);
			
			ItemStack is = player.getCurrentEquippedItem();
	    	
			if(Config.USE_WANDS) {
				if(IBHelper.isWand(is)) {
					is.damageItem(1, player);
				}
			}
			
			BuildHelper.setBlock(world,x, y, z, Blocks.air);
			
			IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSchematic);
    		IBHelper.xp(world, player, Config.XP_AMOUNT);
    		IBHelper.sound(world, Config.SOUND, x, y, z);
    		IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
			
		} catch(Exception e) {
			LogHelper.error("failed to build schematic (" + schematic + "): " + e.getMessage());
		}
	}
}
