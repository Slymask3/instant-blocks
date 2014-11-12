package com.slymask3.instantblocks.block.instant;

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
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.IBHelper;
import com.slymask3.instantblocks.utility.LogHelper;

public class BlockInstantFall extends BlockContainer implements ITileEntityProvider {
	
    public BlockInstantFall() {
        //super(ModBlocks.ibFall, Names.Blocks.IB_RAINBOW_SKYDIVE, Material.cloth, Block.soundTypeCloth, 1.5F);
        //setTextures("instantblocks:skydive_bottom", "instantblocks:skydive_top", "instantblocks:skydive_");
        //setCreateMsg(Strings.fallCreate);
        //setBlockTextureName(Textures.Skydive.SIDE_A);
        
        super(Material.cloth);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName("instantblocks:" + Names.Blocks.IB_STATUE);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeCloth);
        setBlockTextureName(Textures.Harvest.SIDE0);
    }
    
    public static IIcon bottom;
    public static IIcon top;
    public static IIcon side;
    //public static IIcon side0;
    
	public void registerBlockIcons(IIconRegister ir) {
		//if (ConfigurationHandler.animated.getBoolean(true)) {
			bottom = ir.registerIcon(Textures.Skydive.BOTTOM_A);
			top = ir.registerIcon(Textures.Skydive.TOP_A);
			side = ir.registerIcon(Textures.Skydive.SIDE_A);
		//} else {
		//	side0 = ir.registerIcon(Textures.Skydive.SIDE);
		//}
	}
    
	public IIcon getIcon(int side, int meta) {
		//if (ConfigurationHandler.animated.getBoolean(true)) {
			if (side == 0) {
				return bottom;
			} else if (side == 1) {
				return top;
			} else if (side>=2 && side<=5) {
				return this.side;
			}
		//} else {
		//	if (side == 0) {
		//		return Blocks.wool.getIcon(0, 13);
		//	} else if (side == 1) {
		//		return Blocks.wool.getIcon(0, 14);
		//	} else if (side>=2 && side<=5) {
		//		return this.side0;
		//	}
		//}
		return blockIcon;
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
		
		//LogHelper.info("player == " + player);
		
		player.openGui(InstantBlocks.instance, GuiID.SKYDIVE.ordinal(), world, x, y, z);
		
		return true;
	}
	
    public void build(World world, int x, int y, int z, String playerS, int c0, int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int c10) {
    	LogHelper.info("build()");
    	LogHelper.info("c0 == "+c0);
    	
    	EntityPlayer player = world.getPlayerEntityByName(playerS);
    	
    	Block wool = Blocks.wool;
		Block stone = Blocks.stone;
		Block water = Blocks.water;
		Block ladder = Blocks.ladder;
		
		int meta = world.getBlockMetadata(x, y, z);
		
//		/************************ 0 : Red (14) ************************/
//		for (int c = 286; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c0); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c0); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c0); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c0); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c0); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c0); //CORNER
//		}
//		
//		/************************ 1 : Orange (1) ************************/
//		for (int c = 283; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c1); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c1); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c1); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c1); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c1); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c1); //CORNER
//		}
//		
//		/************************ 2 : Yellow (4) ************************/
//		for (int c = 280; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c2); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c2); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c2); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c2); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c2); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c2); //CORNER
//		}
//		
//		/************************ 3 : Lime (5) ************************/
//		for (int c = 277; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c3); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c3); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c3); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c3); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c3); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c3); //CORNER
//		}
//		
//		/************************ 4 : Green (13) ************************/
//		for (int c = 274; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c4); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c4); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c4); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c4); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c4); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c4); //CORNER
//		}
//		
//		/************************ 5 : Cyan (9) ************************/
//		for (int c = 271; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c5); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c5); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c5); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c5); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c5); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c5); //CORNER
//		}
//		
//		/************************ 6 : Light Blue (3) ************************/
//		for (int c = 268; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c6); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c6); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c6); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c6); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c6); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c6); //CORNER
//		}
//		
//		/************************ 7 : Blue (11) ************************/
//		for (int c = 265; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c7); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c7); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c7); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c7); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c7); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c7); //CORNER
//		}
//		
//		/************************ 8 : Purple (10) ************************/
//		for (int c = 262; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c8); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c8); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c8); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c8); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c8); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c8); //CORNER
//		}
//		
//		/************************ 9 : Light Purple (2) ************************/
//		for (int c = 259; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c9); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c9); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c9); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c9); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c9); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c9); //CORNER
//		}
//		
//		/************************ 10 : Pink (6) ************************/
//		for (int c = 256; c >= 1; c =  c - 33) {
//			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c10); //WALL
//			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c10); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c10); //WALL
//			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c10); //WALL
//			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c10); //CORNER
//			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c10); //CORNER
//		}

		/************************ Air ************************/
		for (int c = 256; c >= 1; c--) {
			BuildHelper.buildClean(world, x-3, c, z-3, Blocks.air, 7, 1, 7); //CENTER
			BuildHelper.buildClean(world, x-4, c, z-2, Blocks.air, 5, 1, 1); //WALL
			BuildHelper.buildClean(world, x+4, c, z-2, Blocks.air, 5, 1, 1); //WALL
			BuildHelper.buildClean(world, x-2, c, z+4, Blocks.air, 1, 1, 5); //WALL
			BuildHelper.buildClean(world, x-2, c, z-4, Blocks.air, 1, 1, 5); //WALL
		}

		/************************ Stone (Prevent Lava Burning) ************************/
//		for (int c = 50; c >= 1; c--) {
//			BuildHelper.buildClean(world, x-6, c, z-3, stone, 7, 1, 1); //WALL
//			BuildHelper.buildClean(world, x+6, c, z-3, stone, 7, 1, 1); //WALL
//			BuildHelper.buildClean(world, x-3, c, z+6, stone, 1, 1, 7); //WALL
//			BuildHelper.buildClean(world, x-3, c, z-6, stone, 1, 1, 7); //WALL
//			
//			BuildHelper.buildClean(world, x-5, c, z-4, stone, 2, 1, 1); //PRE-CORNER
//			BuildHelper.buildClean(world, x-5, c, z+3, stone, 2, 1, 1); //PRE-CORNER
//
//			BuildHelper.buildClean(world, x+5, c, z-4, stone, 2, 1, 1); //PRE-CORNER
//			BuildHelper.buildClean(world, x+5, c, z+3, stone, 2, 1, 1); //PRE-CORNER
//			
//			BuildHelper.buildClean(world, x-4, c, z-5, stone, 1, 1, 2); //PRE-CORNER
//			BuildHelper.buildClean(world, x+3, c, z-5, stone, 1, 1, 2); //PRE-CORNER
//
//			BuildHelper.buildClean(world, x-4, c, z+5, stone, 1, 1, 2); //PRE-CORNER
//			BuildHelper.buildClean(world, x+3, c, z+5, stone, 1, 1, 2); //PRE-CORNER
//
//			BuildHelper.buildClean(world, x+4, c, z+4, stone, 1, 1, 1); //CORNER
//			BuildHelper.buildClean(world, x+4, c, z-4, stone, 1, 1, 1); //CORNER
//			BuildHelper.buildClean(world, x-4, c, z+4, stone, 1, 1, 1); //CORNER
//			BuildHelper.buildClean(world, x-4, c, z-4, stone, 1, 1, 1); //CORNER
//		}

		/************************ Water ************************/
		for (int c = 4; c > 1; c--) {
			BuildHelper.buildClean(world, x-3, c, z-3, water, 7, 1, 7); //CENTER
			BuildHelper.buildClean(world, x-4, c, z-2, water, 5, 1, 1); //WALL
			BuildHelper.buildClean(world, x+4, c, z-2, water, 5, 1, 1); //WALL
			BuildHelper.buildClean(world, x-2, c, z+4, water, 1, 1, 5); //WALL
			BuildHelper.buildClean(world, x-2, c, z-4, water, 1, 1, 5); //WALL
		}
		
//		/************************ Floor (c7) ************************/
//		BuildHelper.buildColorBlock(world, x-3, 1, z-3, 7, 1, 7, c7); //CENTER
//		BuildHelper.buildColorBlock(world, x-4, 1, z-2, 5, 1, 1, c7); //WALL
//		BuildHelper.buildColorBlock(world, x+4, 1, z-2, 5, 1, 1, c7); //WALL
//		BuildHelper.buildColorBlock(world, x-2, 1, z+4, 1, 1, 5, c7); //WALL
//		BuildHelper.buildColorBlock(world, x-2, 1, z-4, 1, 1, 5, c7); //WALL

		/************************ Ladder ************************/
		if (meta == 0) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMetaClean(world, x-2, c, z-4, ladder, 3, 0, 1, 1, 5);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				IBHelper.sound(world, ConfigurationHandler.sound, x, 256, z+5);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x + 0.5, 257 + 0.5, z+5 + 0.5);
				}
			}
		} else if (meta == 1) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMetaClean(world, x+4, c, z-2, ladder, 4, 0, 5, 1, 1);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				IBHelper.sound(world, ConfigurationHandler.sound, x-5, 256, z);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x-5 + 0.5, 257 + 0.5, z + 0.5);
				}
			}
		} else if (meta == 2) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMetaClean(world, x-2, c, z+4, ladder, 2, 0, 1, 1, 5);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				IBHelper.sound(world, ConfigurationHandler.sound, x, 256, z-5);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x + 0.5, 257 + 0.5, z-5 + 0.5);
				}
			}
		} else if (meta == 3) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMetaClean(world, x-4, c, z-2, ladder, 5, 0, 5, 1, 1);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				IBHelper.sound(world, ConfigurationHandler.sound, x+5, 256, z);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x+5 + 0.5, 257 + 0.5, z + 0.5);
					IBHelper.sound(world, ConfigurationHandler.sound, (int)player.posX, (int)player.posY, (int)player.posZ);
				}
			}
		}
	}

    @Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySkydive();
	}
}