package com.slymask3.instantblocks.block.instant;

import java.util.Random;

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
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityFlat;
import com.slymask3.instantblocks.utility.IBHelper;
import com.slymask3.instantblocks.utility.LogHelper;

public class BlockInstantFlat extends BlockContainer implements ITileEntityProvider {
	
	public BlockInstantFlat() {
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName("instantblocks:" + Names.Blocks.IB_FLAT);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
        setBlockTextureName(Textures.Harvest.SIDE0);
	}
	
	public int quantityDropped(Random random) {
		return 1;
	}

	public static IIcon bottom;
	public static IIcon top;
	public static IIcon sidel;
	public static IIcon sider;
	public static IIcon front;
	public static IIcon back;

	public void registerBlockIcons(IIconRegister ir) {
		bottom = ir.registerIcon(Textures.Flat.UD);
		top = ir.registerIcon(Textures.Flat.UD);
		sidel = ir.registerIcon(Textures.Flat.LR);
		sider = ir.registerIcon(Textures.Flat.LR);
		front = ir.registerIcon(Textures.Flat.FB);
		back = ir.registerIcon(Textures.Flat.FB);
	}

	public IIcon getIcon(int side, int meta) {
		if (meta==2 || meta==6) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return front;
			} else if (side==3) {
				return back;
			} else if (side==4) {
				return sider;
			} else if (side==5) {
				return sidel;
			}
		} else if (meta==0 || meta==4) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return back;
			} else if (side==3) {
				return front;
			} else if (side==4) {
				return sidel;
			} else if (side==5) {
				return sider;
			}
		} else if (meta==1 || meta==5) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return sidel;
			} else if (side==3) {
				return sider;
			} else if (side==4) {
				return front;
			} else if (side==5) {
				return back;
			}
		} else if (meta==3 || meta==7) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return sider;
			} else if (side==3) {
				return sidel;
			} else if (side==4) {
				return back;
			} else if (side==5) {
				return front;
			}
		}
		return blockIcon;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFlat();
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int meta = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		int meta = world.getBlockMetadata(x, y, z);
		LogHelper.info(meta);
		
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (ConfigurationHandler.useWands == true) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.wandReq, Colors.c);
				return true;
			}
		}
		
		player.openGui(InstantBlocks.instance, GuiID.FLAT.ordinal(), world, x, y, z);
		
		return true;
	}
	
	public void build(World world, int x, int y, int z, String playerS, int up, int down, int forward, int back, int left, int right, boolean harvest) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (harvest) {
			if (meta == 0) {
				clearHarvest(world, x, y, z, up, down, right, left, forward, back);
			} else if (meta == 1) {
				clearHarvest(world, x, y, z, up, down, back, forward, right, left);
			} else if (meta == 2) {
				clearHarvest(world, x, y, z, up, down, left, right, back, forward);
			} else if (meta == 3) {
				clearHarvest(world, x, y, z, up, down, forward, back, left, right);
			}
		} else {
			if (meta == 0) {
				clear(world, x, y, z, up, down, right, left, forward, back);
			} else if (meta == 1) {
				clear(world, x, y, z, up, down, back, forward, right, left);
			} else if (meta == 2) {
				clear(world, x, y, z, up, down, left, right, back, forward);
			} else if (meta == 3) {
				clear(world, x, y, z, up, down, forward, back, left, right);
			}
		}
		
		IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
		
		ItemStack is = player.getCurrentEquippedItem();
		
		if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
			is.damageItem(1, player);
		}
	}
	
	public void clearHarvest(World world, int X, int Y, int Z, int yplus, int yminus, int xplus, int xminus, int zplus, int zminus) {
		world.setBlock(X, Y, Z, Blocks.chest);
		TileEntityChest chest = (TileEntityChest)world.getTileEntity(X, Y, Z);
		
		int maxX = xplus+xminus+1;
		int maxY = yplus+yminus+1;
		int maxZ = zplus+zminus+1;
		
        int x = (int) (X - xminus);
        int y = (int) (Y + yplus);
        int z = (int) (Z - zminus);
   
        int bx = x;
        int bz = z;
 
        for (int i=0; i<maxY; i++) {
            for (int j=0; j<maxZ; j++) {
                for (int k=0; k<maxX; k++) {
                	Block block = world.getBlock(x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    if (chest.getStackInSlot(26) != null) {
                    	if(world.getBlock(X+1, Y, Z) == Blocks.air) {
                    		world.setBlock(X+1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X+1, Y, Z);
                    		X++;
                    	} else if(world.getBlock(X-1, Y, Z) == Blocks.air) {
                    		world.setBlock(X-1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X-1, Y, Z);
                    		X--;
                    	} else if(world.getBlock(X, Y, Z+1) == Blocks.air) {
                    		world.setBlock(X, Y, Z+1, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X, Y, Z+1);
                    		Z++;
                    	} else if(world.getBlock(X, Y, Z-1) == Blocks.air) {
                    		world.setBlock(X, Y, Z-1, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X, Y, Z-1);
                    		Z--;
                    	} else {
                    		world.setBlock(X+1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X+1, Y, Z);
                    		X++;
                    	}
                    	LogHelper.info("had to create another chest");
                    }
	                    
	                    
	                if (block != Blocks.air && block != Blocks.chest && block != Blocks.bedrock) {
	                	//if (x==X && y==Y && z==Y) {
	                		//chest is here
	                	//} else {
		                	IBHelper.addItemsToChest(chest, block, 1, meta);
		                	world.setBlock(x, y, z, Blocks.air);
	                	//}
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
        
        //world.setBlock(X, Y, Z, this);
    }
	
	public void clear(World world, int X, int Y, int Z, int yplus, int yminus, int xplus, int xminus, int zplus, int zminus) {
		int maxX = xplus+xminus+1;
		int maxY = yplus+yminus+1;
		int maxZ = zplus+zminus+1;
		
        int x = (int) (X - xminus);
        int y = (int) (Y + yplus);
        int z = (int) (Z - zminus);
   
        int bx = x;
        int bz = z;
 
        for (int i=0; i<maxY; i++) {
            for (int j=0; j<maxZ; j++) {
                for (int k=0; k<maxX; k++) {
                	Block block = world.getBlock(x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    if (block != Blocks.air) {
                    	world.setBlock(x, y, z, Blocks.air);
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
        
        //world.setBlock(X, Y, Z, this);
    }
}
