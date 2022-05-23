package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.*;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockInstantFall extends BlockContainer implements ITileEntityProvider {
	
    public BlockInstantFall() {
        super(Material.cloth);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName(Reference.MOD_ID + ":" + Names.Blocks.IB_SKYDIVE);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeCloth);
        setBlockTextureName(Textures.Harvest.SIDE0);
    }
    
    public static IIcon bottom;
    public static IIcon top;
    public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		bottom = ir.registerIcon(Textures.Skydive.BOTTOM_A);
		top = ir.registerIcon(Textures.Skydive.TOP_A);
		side = ir.registerIcon(Textures.Skydive.SIDE_A);
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return bottom;
		} else if (side == 1) {
			return top;
		} else if (side>=2 && side<=5) {
			return this.side;
		}
		return blockIcon;
	}
    
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int meta = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.USE_WANDS) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}
		
		player.openGui(InstantBlocks.instance, GuiID.SKYDIVE.ordinal(), world, x, y, z);
		
		return true;
	}
	
    public void build(World world, int x, int y, int z, String playerS, int c0, int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int c10, boolean tp) {
    	EntityPlayer player = world.getPlayerEntityByName(playerS);
    	
    	Block wool = Blocks.wool;
		Block stone = Blocks.stone;
		Block water = Blocks.water;
		Block ladder = Blocks.ladder;
		
		int meta = world.getBlockMetadata(x, y, z);

		/************************ Air ************************/
		for (int c = 256; c >= 1; c--) {
			BuildHelper.buildClean(world, x-3, c, z-3, Blocks.air, 7, 1, 7); //CENTER
			BuildHelper.buildClean(world, x-4, c, z-2, Blocks.air, 5, 1, 1); //WALL
			BuildHelper.buildClean(world, x+4, c, z-2, Blocks.air, 5, 1, 1); //WALL
			BuildHelper.buildClean(world, x-2, c, z+4, Blocks.air, 1, 1, 5); //WALL
			BuildHelper.buildClean(world, x-2, c, z-4, Blocks.air, 1, 1, 5); //WALL
		}
		
		/************************ 0 : Red (14) ************************/
		for (int c = 253; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c0); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c0); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c0); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c0); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c0); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c0); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c0);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c0);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c0);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c0);
			}
		}
		
		/************************ 1 : Orange (1) ************************/
		for (int c = 250; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c1); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c1); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c1); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c1); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c1); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c1); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c1);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c1);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c1);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c1);
			}
		}
		
		/************************ 2 : Yellow (4) ************************/
		for (int c = 247; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c2); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c2); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c2); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c2); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c2); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c2); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c2);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c2);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c2);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c2);
			}
		}
		
		/************************ 3 : Lime (5) ************************/
		for (int c = 244; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c3); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c3); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c3); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c3); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c3); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c3); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c3);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c3);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c3);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c3);
			}
		}
		
		/************************ 4 : Green (13) ************************/
		for (int c = 241; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c4); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c4); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c4); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c4); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c4); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c4); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c4);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c4);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c4);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c4);
			}
		}
		
		/************************ 5 : Cyan (9) ************************/
		for (int c = 238; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c5); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c5); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c5); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c5); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c5); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c5); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c5);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c5);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c5);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c5);
			}
		}
		
		/************************ 6 : Light Blue (3) ************************/
		for (int c = 235; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c6); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c6); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c6); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c6); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c6); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c6); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c6);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c6);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c6);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c6);
			}
		}
		
		/************************ 7 : Blue (11) ************************/
		for (int c = 232; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c7); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c7); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c7); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c7); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c7); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c7); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c7);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c7);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c7);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c7);
			}
		}
		
		/************************ 8 : Purple (10) ************************/
		for (int c = 229; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c8); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c8); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c8); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c8); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c8); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c8); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c8);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c8);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c8);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c8);
			}
		}
		
		/************************ 9 : Light Purple (2) ************************/
		for (int c = 226; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c9); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c9); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c9); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c9); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c9); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c9); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c9);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c9);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c9);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c9);
			}
		}
		
		/************************ 10 : Pink (6) ************************/
		for (int c = 223; c >= 1; c =  c - 33) {
			BuildHelper.buildColorBlock(world, x-5, c, z-2, 5, 3, 1, c10); //WALL
			BuildHelper.buildColorBlock(world, x+5, c, z-2, 5, 3, 1, c10); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z+5, 1, 3, 5, c10); //WALL
			BuildHelper.buildColorBlock(world, x-2, c, z-5, 1, 3, 5, c10); //WALL
			BuildHelper.buildColorBlock(world, x+3, c, z+4, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x+3, c, z-4, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z+3, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x+4, c, z-3, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z+4, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x-3, c, z-4, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z+3, 1, 3, 1, c10); //CORNER
			BuildHelper.buildColorBlock(world, x-4, c, z-3, 1, 3, 1, c10); //CORNER
			
			if (meta==0) {
				BuildHelper.buildColorLadder(world, x-2, c, z-4, 1, 3, 5, 3, c10);
			} else if (meta == 1) {
				BuildHelper.buildColorLadder(world, x+4, c, z-2, 5, 3, 1, 4, c10);
			} else if (meta == 2) {
				BuildHelper.buildColorLadder(world, x-2, c, z+4, 1, 3, 5, 2, c10);
			} else if (meta == 3) {
				BuildHelper.buildColorLadder(world, x-4, c, z-2, 5, 3, 1, 5, c10);
			}
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
		
		/************************ Floor (c7) ************************/
		BuildHelper.buildColorBlock(world, x-3, 1, z-3, 7, 1, 7, c7); //CENTER
		BuildHelper.buildColorBlock(world, x-4, 1, z-2, 5, 1, 1, c7); //WALL
		BuildHelper.buildColorBlock(world, x+4, 1, z-2, 5, 1, 1, c7); //WALL
		BuildHelper.buildColorBlock(world, x-2, 1, z+4, 1, 1, 5, c7); //WALL
		BuildHelper.buildColorBlock(world, x-2, 1, z-4, 1, 1, 5, c7); //WALL

		/************************ Ladder ************************/
		if (meta == 0) {
			world.setBlock(x, 5, z+5, ModBlocks.skydiveTP);
			world.setBlock(x+5, 5, z, ModBlocks.skydiveTP);
			world.setBlock(x-5, 5, z, ModBlocks.skydiveTP);
		
			if (tp) {
				IBHelper.sound(world, Config.SOUND, x, 256, z+5);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x + 0.5, 257 + 0.5, z+5 + 0.5);
				}
			}
		} else if (meta == 1) {
			world.setBlock(x-5, 5, z, ModBlocks.skydiveTP);
			world.setBlock(x, 5, z+5, ModBlocks.skydiveTP);
			world.setBlock(x, 5, z-5, ModBlocks.skydiveTP);
		
			if (tp) {
				IBHelper.sound(world, Config.SOUND, x-5, 256, z);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x-5 + 0.5, 257 + 0.5, z + 0.5);
				}
			}
		} else if (meta == 2) {
			world.setBlock(x, 5, z-5, ModBlocks.skydiveTP);
			world.setBlock(x+5, 5, z, ModBlocks.skydiveTP);
			world.setBlock(x-5, 5, z, ModBlocks.skydiveTP);
		
			if (tp) {
				IBHelper.sound(world, Config.SOUND, x, 256, z-5);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x + 0.5, 257 + 0.5, z-5 + 0.5);
				}
			}
		} else if (meta == 3) {
			world.setBlock(x+5, 5, z, ModBlocks.skydiveTP);
			world.setBlock(x, 5, z+5, ModBlocks.skydiveTP);
			world.setBlock(x, 5, z-5, ModBlocks.skydiveTP);
		
			if (tp) {
				IBHelper.sound(world, Config.SOUND, x+5, 256, z);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x+5 + 0.5, 257 + 0.5, z + 0.5);
					IBHelper.sound(world, Config.SOUND, (int)player.posX, (int)player.posY, (int)player.posZ);
				}
			}
		}
		
		
	}

    @Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySkydive();
	}
}