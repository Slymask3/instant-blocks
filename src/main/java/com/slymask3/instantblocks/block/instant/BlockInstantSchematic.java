package com.slymask3.instantblocks.block.instant;


import java.io.File;

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

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.utility.IBHelper;
import com.slymask3.instantblocks.utility.SchematicHelper;

public class BlockInstantSchematic extends BlockContainer implements ITileEntityProvider {

	public BlockInstantSchematic() {
		//super(ModBlocks.ibSchematic, Names.Blocks.IB_SCHEMATIC, Material.cactus, Block.soundTypeAnvil, 1.5F);
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName("instantblocks:" + Names.Blocks.IB_SCHEMATIC);
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
    	
		if (ConfigurationHandler.useWands == true) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.wandReq, Colors.c);
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
	    	
			if (ConfigurationHandler.useWands == true) {
				if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
					is.damageItem(1, player);
				}
			}
			
			world.setBlock(x, y, z, Blocks.air);
			
			IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSchematic);
    		IBHelper.xp(world, player, ConfigurationHandler.xp);
    		IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
    		IBHelper.effectFull(world, "reddust", x, y, z);
			
		} catch(Exception e) {}
		
	}

}
