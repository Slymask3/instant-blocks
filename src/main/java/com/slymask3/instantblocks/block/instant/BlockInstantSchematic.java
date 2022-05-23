package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.*;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.LogHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.io.File;

public class BlockInstantSchematic extends BlockContainer implements ITileEntityProvider {

	public BlockInstantSchematic() {
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName(Reference.MOD_ID + ":" + Names.Blocks.IB_SCHEMATIC);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
        setBlockTextureName(Textures.Harvest.SIDE0);
	}

	public static IIcon top;
	public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(Textures.Schematic.TOP);
		side = ir.registerIcon(Textures.Schematic.SIDE);
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return Blocks.planks.getIcon(0, 5);
		} else if (side == 1) {
			return top;
		} else {
			return this.side;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySchematic();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.useWands) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}
		
		
		
		player.openGui(InstantBlocks.instance, GuiID.SCHEMATIC.ordinal(), world, x, y, z);
		
		return true;
	}
	
	public void build(World world, int x, int y, int z, int meta, String playerS, String schematic, boolean center, boolean ignoreAir) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		
		try{
			File f = new File("schematics/"+schematic+".schematic");
			
			SchematicHelper.loadSchematic(world, x, y ,z, f, center, ignoreAir);
			
			ItemStack is = player.getCurrentEquippedItem();
	    	
			if (Config.useWands) {
				if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
					is.damageItem(1, player);
				}
			}
			
			world.setBlock(x, y, z, Blocks.air);
			
			IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSchematic);
    		IBHelper.xp(world, player, Config.xp);
    		IBHelper.sound(world, Config.sound, x, y, z);
    		IBHelper.effectFull(world, "reddust", x, y, z);
			
		} catch(Exception e) {
			LogHelper.error("failed to build schematic (" + schematic + "): " + e.getMessage());
		}
		
	}

}
