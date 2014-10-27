package com.slymask3.instantblocks.block;

import java.awt.Image;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.utility.InstantBlocksFunctions;

public class BlockInstantHouseWood extends BlockDirectional {
	private InstantBlocksFunctions ibf = new InstantBlocksFunctions();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
	//public boolean b = false;
	public int drop = 1;

	public BlockInstantHouseWood() {
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName("instantblocks:" + Names.Blocks.IB_WOOD_HOUSE);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
	}

	public int quantityDropped(Random random) {
		/*if (notThere == true) {
			//notThere = false;
			return 0;
		} else {
			return 1;
		}*/
		
		return drop;
	}

	//public static IIcon bottom;
	public static IIcon top;
	public static IIcon side;
	public static IIcon front;

	public void registerBlockIcons(IIconRegister ir) {
		//bottom = ir.registerIcon("planks_spruce");
		top = ir.registerIcon("instantblocks:woodhouse_top");
		side = ir.registerIcon("instantblocks:woodhouse_side");
		front = ir.registerIcon("instantblocks:woodhouse_front");
	}

	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return Blocks.planks.getIcon(0, 1);
		} else if (side == 1) {
			return top;
		} else if (side == 2) {
			if (meta == 2 || meta == 6) {
				return front;
			} else {
				return this.side;
			}
		} else if (side == 3) {
			if (meta == 0 || meta == 4) {
				return front;
			} else {
				return this.side;
			}
		} else if (side == 4) {
			if (meta == 1 || meta == 5) {
				return front;
			} else {
				return this.side;
			}
		} else if (side == 5) {
			if (meta == 3 || meta == 7) {
				return front;
			} else {
				return this.side;
			}
		} else {
			return blockIcon;
		}
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int meta = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);

		System.out.println("meta = " + meta);
	}
	
	//public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {}

	
	
	//URL url = new URL("http://www.digitalphotoartistry.com/rose1.jpg");
	//Image image = ImageIO.read(url);  
	
	
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (world.getBlockMetadata(x, y, z) >= 4 && world.getBlockMetadata(x, y, z) <= 7) {
			ibf.msg(player, ibf.woodError, Colors.c);
			return true;
		}
		
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achWood);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		int meta = world.getBlockMetadata(x, y, z);
		
		build(world, x, y, z);
			
		ibf.xp(world, player, config.xp);
		//ibf.keepBlocks(world, x, y, z, InstantBlocks.ibWood.blockID);
				
		ibf.sound(world, config.sound, x, y, z);
		ibf.effectFull(world, "reddust", x, y, z);
		ibf.msg(player, ibf.woodCreate, Colors.a);
		
		//System.out.println("meta = " + meta);
		
		return true;
	}
	
	//EntityPlayer currentPlayer;
	
	/*public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		currentPlayer = player;
		
		System.out.println("currentPlayer = " + currentPlayer);
	}*/
	
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		System.out.println("isRemote == " + world.isRemote);
		
		check(world, x, y, z, meta);
		
		//EntityPlayer player = world.getClosestPlayer(x, y, z, 10);
		//ibf.msg(player, "WENT THROUGH");
	}

	private void build(World world, int x, int y, int z) {
		/*int light = Block.planks.blockID;
		int dark = Block.planks.blockID;
		int log = Block.wood.blockID;
		int craft = Block.workbench.blockID;
		int chest = Block.chest.blockID;
		int furnace = Block.furnaceIdle.blockID;
		int fence = Block.fence.blockID;
		int gate = Block.fenceGate.blockID;
		int stair = Block.stairsWoodOak.blockID;
		int sign = Block.signWall.blockID;
		int bed = Block.bed.blockID;
		int door = Block.doorWood.blockID;
		int pane = Block.thinGlass.blockID;
		int torch = Block.torchWood.blockID;
		int plate = Block.pressurePlatePlanks.blockID;
		int slabL = 126;
		int slabD = 126;
		int air = 0;*/
		
		Block light = Blocks.planks;
		Block dark = Blocks.planks;
		Block log = Blocks.log;
		Block craft = Blocks.crafting_table;
		Block chest = Blocks.chest;
		Block furnace = Blocks.furnace;
		Block fence = Blocks.fence;
		Block gate = Blocks.fence_gate;
		Block stair = Blocks.oak_stairs;
		Block sign = Blocks.wall_sign;
		Block bed = Blocks.bed;
		Block door = Blocks.wooden_door;
		Block pane = Blocks.glass_pane;
		Block torch = Blocks.torch;
		Block plate = Blocks.wooden_pressure_plate;
		Block slabL = Blocks.wooden_slab;
		Block slabD = Blocks.wooden_slab;
		Block air = Blocks.air;

		int meta = world.getBlockMetadata(x, y, z);
		
		/************************ meta == 0 ************************/
		if (meta == 0 || meta == 4) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			ibf.buildMeta(world, x-5, y, z-5, air, 0, 2, 8, 6, 11); //HOUSE ROOM
			ibf.buildMeta(world, x-4, y+5, z-4, air, 0, 2, 6, 1, 9); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z-3, air, 0, 2, 4, 1, 7); //HOUSE ROOF 2

			ibf.buildMeta(world, x-4, y, z+3, air, 0, 2, 4, 5, 9); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //DONE
			ibf.buildMeta(world, x-5, y, z-5, dark, 1, 2, 8, 1, 11); //HOME FLOOR 1
			ibf.buildMeta(world, x-4, y, z-4, light, 2, 2, 6, 1, 9); //HOME FLOOR 2
			ibf.buildMeta(world, x-3, y, z-3, dark, 1, 2, 4, 1, 7); //HOME FLOOR 3
			ibf.buildMeta(world, x-2, y, z-2, light, 2, 2, 2, 1, 5); //HOME FLOOR 4

			ibf.buildMeta(world, x-4, y, z+3, dark, 1, 2, 4, 1, 9); //PORCH FLOOR 1
			ibf.buildMeta(world, x-3, y, z+4, light, 2, 2, 2, 1, 7); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/ //DONE
			ibf.buildMeta(world, x-5, y+1, z-5, log, 2, 2, 1, 4, 1); //LOG CORNER 1
			ibf.buildMeta(world, x+5, y+1, z-5, log, 2, 2, 1, 4, 1); //LOG CORNER 2
			ibf.buildMeta(world, x-5, y+1, z+2, log, 2, 2, 1, 4, 1); //LOG CORNER 3
			ibf.buildMeta(world, x+5, y+1, z+2, log, 2, 2, 1, 4, 1); //LOG CORNER 4
			ibf.buildMeta(world, x-4, y+1, z-5, light, 2, 2, 1, 4, 9); //WOOD WALL 1
			ibf.buildMeta(world, x-4, y+1, z+2, light, 2, 2, 1, 4, 9); //WOOD WALL 2
			ibf.buildMeta(world, x-5, y+1, z-4, light, 2, 2, 6, 4, 1); //WOOD WALL 3
			ibf.buildMeta(world, x+5, y+1, z-4, light, 2, 2, 6, 4, 1); //WOOD WALL 4
			
			ibf.buildMeta(world, x-3, y+2, z-5, pane, 0, 2, 1, 2, 7); //PANE WALL 1
			ibf.buildMeta(world, x-3, y+2, z+2, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (1)
			ibf.buildMeta(world, x+3, y+2, z+2, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (2)
			ibf.buildMeta(world, x-5, y+2, z-3, pane, 0, 2, 4, 2, 1); //PANE WALL 3
			ibf.buildMeta(world, x+5, y+2, z-3, pane, 0, 2, 4, 2, 1); //PANE WALL 4

			ibf.buildMeta(world, x-1, y+1, z+2, log, 2, 2, 1, 3, 3); //LOG ENTRANCE

			ItemDoor.placeDoorBlock(world, x, y+1, z+2, 3, Blocks.wooden_door); //DOOR

			/************************ Layer 1 to 4 : PORCH ************************/ //DONE
			ibf.buildMeta(world, x-4, y+4, z+3, slabD, 9, 2, 4, 1, 9); //PORCH ROOF 1
			ibf.buildMeta(world, x-3, y+4, z+4, slabD, 10, 2, 2, 1, 7); //PORCH ROOF 2

			ibf.buildMeta(world, x-4, y+1, z+3, dark, 1, 2, 1, 4, 1); //PORCH CORNER 1
			ibf.buildMeta(world, x+4, y+1, z+3, dark, 1, 2, 1, 4, 1); //PORCH CORNER 2
			ibf.buildMeta(world, x-4, y+1, z+6, dark, 1, 2, 1, 4, 1); //PORCH CORNER 3
			ibf.buildMeta(world, x+4, y+1, z+6, dark, 1, 2, 1, 4, 1); //PORCH CORNER 4
			
			ibf.buildMeta(world, x-4, y+2, z+3, fence, 0, 2, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			ibf.buildMeta(world, x+4, y+2, z+3, fence, 0, 2, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			ibf.buildMeta(world, x-4, y+2, z+6, fence, 0, 2, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			ibf.buildMeta(world, x+4, y+2, z+6, fence, 0, 2, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			ibf.buildMeta(world, x-4, y+1, z+4, fence, 0, 2, 2, 1, 1); //PORCH SIDE 1
			ibf.buildMeta(world, x+4, y+1, z+4, fence, 0, 2, 2, 1, 1); //PORCH SIDE 2
			ibf.buildMeta(world, x-3, y+1, z+6, fence, 0, 2, 1, 1, 7); //PORCH SIDE 3 (FRONT)
			world.setBlock(x, y+1, z+6, gate, 0, 0); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/
			ibf.buildMeta(world, x-5, y+5, z-5, slabD, 1, 2, 1, 1, 11); //HOUSE ROOF 1
			ibf.buildMeta(world, x-5, y+5, z+2, slabD, 1, 2, 1, 1, 11); //HOUSE ROOF 2
			ibf.buildMeta(world, x-5, y+5, z-4, slabD, 1, 2, 6, 1, 1); //HOUSE ROOF 3
			ibf.buildMeta(world, x+5, y+5, z-4, slabD, 1, 2, 6, 1, 1); //HOUSE ROOF 4

			ibf.buildMeta(world, x-4, y+5, z-4, light, 2, 2, 1, 1, 9); //HOUSE ROOF 1
			ibf.buildMeta(world, x-4, y+5, z+1, light, 2, 2, 1, 1, 9); //HOUSE ROOF 2
			ibf.buildMeta(world, x-4, y+5, z-3, light, 2, 2, 4, 1, 1); //HOUSE ROOF 3
			ibf.buildMeta(world, x+4, y+5, z-3, light, 2, 2, 4, 1, 1); //HOUSE ROOF 4

			ibf.buildMeta(world, x-3, y+6, z-3, slabD, 1, 2, 1, 1, 7); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z, slabD, 1, 2, 1, 1, 7); //HOUSE ROOF 2
			ibf.buildMeta(world, x-3, y+6, z-2, slabD, 1, 2, 2, 1, 1); //HOUSE ROOF 3
			ibf.buildMeta(world, x+3, y+6, z-2, slabD, 1, 2, 2, 1, 1); //HOUSE ROOF 4

			ibf.buildMeta(world, x-2, y+6, z-2, light, 2, 2, 2, 1, 5); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			world.setBlock(x + 4, y + 1, z - 4, bed, 11, 0); //BED //NOT 10 //GOOD
			world.setBlock(x + 3, y + 1, z - 4, bed, 3, 0); //BED //NOT 2 //GOOD
			
			world.setBlock(x-2, y + 1, z-4, chest); //CHEST
			world.setBlock(x-3, y + 1, z-4, chest); //CHEST
			world.setBlock(x-4, y + 1, z-3, chest); //CHEST
			world.setBlock(x-4, y + 1, z-2, chest); //CHEST
			
			world.setBlock(x - 4, y + 1, z - 4, craft); //WORKBENCH
			world.setBlock(x + 4, y + 1, z - 2, furnace, 3, 0); //FURNACE
			world.setBlock(x + 4, y + 1, z - 1, furnace, 3, 0); //FURNACE

			
			
			//world.setBlock(x - 4, y + 1, z - 1, stair, 9, 0); //CHAIR //NOT 3, 2, 1
			//world.setBlock(x+4, y + 1, z-1, stair, 0, 0); //CHAIR //NOT 10 //GOOD
			//world.setBlock(x-4, y + 1, z, sign, 3, 0); //CHAIR //NOT 4, 5
			//world.setBlock(x+4, y + 1, z, sign, 3, 0); //CHAIR //NOT 4, 5
			
			world.setBlock(x-4, y + 1, z+1, stair, 9, 0); //CHAIR
			world.setBlock(x + 4, y + 1, z + 1, stair, 0, 0); //CHAIR
			world.setBlock(x-4, y + 1, z, sign, 2, 0); //CHAIR
			world.setBlock(x+4, y + 1, z, sign, 2, 0); //CHAIR
			
			world.setBlock(x-2,y+2,z+1,plate); //TABLE
			world.setBlock(x+2,y+2,z+1,plate); //TABLE
			world.setBlock(x-2,y+1,z+1,fence); //TABLE
			world.setBlock(x+2,y+1,z+1,fence); //TABLE
			
			world.setBlock(x-1, y + 2, z+6, torch);
			world.setBlock(x + 1, y + 2, z + 6, torch);
			world.setBlock(x-1, y + 3, z+3, torch);
			world.setBlock(x + 1, y + 3, z + 3, torch);
			world.setBlock(x - 2, y + 4, z - 4, torch);
			world.setBlock(x+2, y + 4, z-4, torch);
			world.setBlock(x - 4, y + 4, z - 2, torch);
			world.setBlock(x+4, y + 4, z-2, torch);
			world.setBlock(x - 4, y + 4, z - 1, torch);
			world.setBlock(x+4, y + 4, z-1, torch);
			world.setBlock(x-2, y + 4, z+1, torch);
			world.setBlock(x + 2, y + 4, z + 1, torch);
			world.setBlock(x + 5, y + 4, z + 6, torch);
			world.setBlock(x-5, y + 4, z+6, torch);
			world.setBlock(x-4, y + 4, z+7, torch);
			world.setBlock(x + 4, y + 4, z + 7, torch);
			
			//world.setBlock(p_147449_1_, p_147449_2_, p_147449_3_, p_147449_4_); ////1.7.10 setBlock
			
			if (config.packWood == true) {
				world.setBlock(x, y, z, mb.ibWood, 4, 2);
			}
		}
		
		/************************ meta == 1 ************************/
		else if (meta == 1 || meta == 5) {
			/************************ Layer 0 to 7 : AIR ************************/
			ibf.buildMeta(world, x-2, y, z-5, air, 0, 2, 11, 6, 8); //HOUSE ROOM
			ibf.buildMeta(world, x-1, y+5, z-4, air, 0, 2, 9, 1, 6); //HOUSE ROOF 1
			ibf.buildMeta(world, x, y+6, z-3, air, 0, 2, 7, 1, 4); //HOUSE ROOF 2

			ibf.buildMeta(world, x-6, y, z-4, air, 0, 2, 9, 5, 4); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //GOOD
			ibf.buildMeta(world, x-2, y, z-5, dark, 1, 2, 11, 1, 8); //HOME FLOOR 1
			ibf.buildMeta(world, x-1, y, z-4, light, 2, 2, 9, 1, 6); //HOME FLOOR 2
			ibf.buildMeta(world, x, y, z-3, dark, 1, 2, 7, 1, 4); //HOME FLOOR 3
			ibf.buildMeta(world, x+1, y, z-2, light, 2, 2, 5, 1, 2); //HOME FLOOR 4

			ibf.buildMeta(world, x-6, y, z-4, dark, 1, 2, 9, 1, 4); //PORCH FLOOR 1
			ibf.buildMeta(world, x-5, y, z-3, light, 2, 2, 7, 1, 2); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/
			ibf.buildMeta(world, x+5, y+1, z-5, log, 2, 2, 1, 4, 1); //LOG CORNER 1
			ibf.buildMeta(world, x+5, y+1, z+5, log, 2, 2, 1, 4, 1); //LOG CORNER 2
			ibf.buildMeta(world, x-2, y+1, z-5, log, 2, 2, 1, 4, 1); //LOG CORNER 3
			ibf.buildMeta(world, x-2, y+1, z+5, log, 2, 2, 1, 4, 1); //LOG CORNER 4
			ibf.buildMeta(world, x+5, y+1, z-4, light, 2, 2, 9, 4, 1); //WOOD WALL 1
			ibf.buildMeta(world, x-2, y+1, z-4, light, 2, 2, 9, 4, 1); //WOOD WALL 2
			ibf.buildMeta(world, x-1, y+1, z-5, light, 2, 2, 1, 4, 6); //WOOD WALL 3 //GOOD
			ibf.buildMeta(world, x-1, y+1, z+5, light, 2, 2, 1, 4, 6); //WOOD WALL 4 //GOOD
			
			ibf.buildMeta(world, x+5, y+2, z-3, pane, 0, 2, 7, 2, 1); //PANE WALL 1
			ibf.buildMeta(world, x-2, y+2, z-3, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (1)
			ibf.buildMeta(world, x-2, y+2, z+3, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (2)
			ibf.buildMeta(world, x, y+2, z-5, pane, 0, 2, 1, 2, 4); //PANE WALL 3 //GOOD
			ibf.buildMeta(world, x, y+2, z+5, pane, 0, 2, 1, 2, 4); //PANE WALL 4 //GOOD

			ibf.buildMeta(world, x-2, y+1, z-1, log, 2, 2, 3, 3, 1); //LOG ENTRANCE

			ItemDoor.placeDoorBlock(world, x-2, y+1, z, 0, Blocks.wooden_door);

			/************************ Layer 1 to 4 : PORCH ************************/
			ibf.buildMeta(world, x-6, y+4, z-4, slabD, 9, 2, 9, 1, 4); //PORCH ROOF 1
			ibf.buildMeta(world, x-5, y+4, z-3, slabD, 10, 2, 7, 1, 2); //PORCH ROOF 2

			ibf.buildMeta(world, x-3, y+1, z-4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 1
			ibf.buildMeta(world, x-3, y+1, z+4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 2
			ibf.buildMeta(world, x-6, y+1, z-4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 3
			ibf.buildMeta(world, x-6, y+1, z+4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 4
			
			ibf.buildMeta(world, x-3, y+2, z-4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			ibf.buildMeta(world, x-3, y+2, z+4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			ibf.buildMeta(world, x-6, y+2, z-4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			ibf.buildMeta(world, x-6, y+2, z+4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			ibf.buildMeta(world, x-5, y+1, z-4, fence, 0, 2, 1, 1, 2); //PORCH SIDE 1
			ibf.buildMeta(world, x-5, y+1, z+4, fence, 0, 2, 1, 1, 2); //PORCH SIDE 2
			ibf.buildMeta(world, x-6, y+1, z-3, fence, 0, 2, 7, 1, 1); //PORCH SIDE 3 (FRONT)
			world.setBlock(x-6, y+1, z, gate, 1, 0); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/ //GOOD
			ibf.buildMeta(world, x+5, y+5, z-5, slabD, 1, 2, 11, 1, 1); //HOUSE ROOF 1
			ibf.buildMeta(world, x-2, y+5, z-5, slabD, 1, 2, 11, 1, 1); //HOUSE ROOF 2
			ibf.buildMeta(world, x-1, y+5, z-5, slabD, 1, 2, 1, 1, 6); //HOUSE ROOF 3 //GOOD
			ibf.buildMeta(world, x-1, y+5, z+5, slabD, 1, 2, 1, 1, 6); //HOUSE ROOF 4 //GOOD

			ibf.buildMeta(world, x+4, y+5, z-4, light, 2, 2, 9, 1, 1); //HOUSE ROOF 1
			ibf.buildMeta(world, x-1, y+5, z-4, light, 2, 2, 9, 1, 1); //HOUSE ROOF 2
			ibf.buildMeta(world, x, y+5, z-4, light, 2, 2, 1, 1, 4); //HOUSE ROOF 3
			ibf.buildMeta(world, x, y+5, z+4, light, 2, 2, 1, 1, 4); //HOUSE ROOF 4

			ibf.buildMeta(world, x+3, y+6, z-3, slabD, 1, 2, 7, 1, 1); //HOUSE ROOF 1
			ibf.buildMeta(world, x, y+6, z-3, slabD, 1, 2, 7, 1, 1); //HOUSE ROOF 2
			ibf.buildMeta(world, x+1, y+6, z-3, slabD, 1, 2, 1, 1, 2); //HOUSE ROOF 3
			ibf.buildMeta(world, x+1, y+6, z+3, slabD, 1, 2, 1, 1, 2); //HOUSE ROOF 4

			ibf.buildMeta(world, x+1, y+6, z-2, light, 2, 2, 5, 1, 2); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			world.setBlock(x + 4, y + 1, z + 4, bed, 8, 0); //BED //NOT 10, 9, 11 //GOD
			world.setBlock(x + 4, y + 1, z + 3, bed, 0, 0); //BED //NOT 2, 1, 3 //GOOD
			
			world.setBlock(x + 4, y + 1, z - 2, chest); //CHEST
			world.setBlock(x + 4, y + 1, z - 3, chest); //CHEST
			world.setBlock(x + 3, y + 1, z - 4, chest); //CHEST
			world.setBlock(x + 2, y + 1, z - 4, chest); //CHEST
			
			world.setBlock(x + 4, y + 1, z - 4, craft); //WORKBENCH
			world.setBlock(x + 2, y + 1, z + 4, furnace, 2, 0); //FURNACE //NOT 3
			world.setBlock(x + 1, y + 1, z + 4, furnace, 2, 0); //FURNACE //NOT 3

			world.setBlock(x - 1, y + 1, z - 4, stair, 3, 0); //CHAIR
			world.setBlock(x - 1, y + 1, z + 4, stair, 10, 0); //CHAIR
			world.setBlock(x, y + 1, z - 4, sign, 5, 0); //CHAIR //NOT 4, 3
			world.setBlock(x, y + 1, z + 4, sign, 5, 0); //CHAIR //NOT 4, 3
			
			world.setBlock(x-1,y+2,z-2,plate); //TABLE
			world.setBlock(x-1,y+2,z+2,plate); //TABLE
			world.setBlock(x-1,y+1,z-2,fence); //TABLE
			world.setBlock(x-1,y+1,z+2,fence); //TABLE
			
			world.setBlock(x - 6, y + 2, z - 1, torch);
			world.setBlock(x - 6, y + 2, z + 1, torch);
			world.setBlock(x - 3, y + 3, z - 1, torch);
			world.setBlock(x - 3, y + 3, z + 1, torch);
			world.setBlock(x + 4, y + 4, z - 2, torch);
			world.setBlock(x + 4, y + 4, z + 2, torch);
			world.setBlock(x + 2, y + 4, z - 4, torch);
			world.setBlock(x + 2, y + 4, z + 4, torch);
			world.setBlock(x + 1, y + 4, z - 4, torch);
			world.setBlock(x + 1, y + 4, z + 4, torch);
			world.setBlock(x - 1, y + 4, z - 2, torch);
			world.setBlock(x - 1, y + 4, z + 2, torch);
			world.setBlock(x - 6, y + 4, z + 5, torch);
			world.setBlock(x - 6, y + 4, z - 5, torch);
			world.setBlock(x - 7, y + 4, z - 4, torch);
			world.setBlock(x - 7, y + 4, z + 4, torch);
			
			if (config.packWood == true) {
				world.setBlock(x, y, z, mb.ibWood, 5, 2);
			}
		}
		
		/************************ meta == 2 ************************/
		else if (meta == 2 || meta == 6) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			ibf.buildMeta(world, x-5, y, z-2, air, 0, 2, 8, 6, 11); //HOUSE ROOM
			ibf.buildMeta(world, x-4, y+5, z-1, air, 0, 2, 6, 1, 9); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z, air, 0, 2, 4, 1, 7); //HOUSE ROOF 2

			ibf.buildMeta(world, x-4, y, z-6, air, 0, 2, 4, 5, 9); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //DONE
			ibf.buildMeta(world, x-5, y, z-2, dark, 1, 2, 8, 1, 11); //HOME FLOOR 1
			ibf.buildMeta(world, x-4, y, z-1, light, 2, 2, 6, 1, 9); //HOME FLOOR 2
			ibf.buildMeta(world, x-3, y, z, dark, 1, 2, 4, 1, 7); //HOME FLOOR 3
			ibf.buildMeta(world, x-2, y, z+1, light, 2, 2, 2, 1, 5); //HOME FLOOR 4

			ibf.buildMeta(world, x-4, y, z-6, dark, 1, 2, 4, 1, 9); //PORCH FLOOR 1
			ibf.buildMeta(world, x-3, y, z-5, light, 2, 2, 2, 1, 7); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/ //DONE
			ibf.buildMeta(world, x-5, y+1, z+5, log, 2, 2, 1, 4, 1); //LOG CORNER 1
			ibf.buildMeta(world, x+5, y+1, z+5, log, 2, 2, 1, 4, 1); //LOG CORNER 2
			ibf.buildMeta(world, x-5, y+1, z-2, log, 2, 2, 1, 4, 1); //LOG CORNER 3
			ibf.buildMeta(world, x+5, y+1, z-2, log, 2, 2, 1, 4, 1); //LOG CORNER 4
			ibf.buildMeta(world, x-4, y+1, z+5, light, 2, 2, 1, 4, 9); //WOOD WALL 1
			ibf.buildMeta(world, x-4, y+1, z-2, light, 2, 2, 1, 4, 9); //WOOD WALL 2
			ibf.buildMeta(world, x-5, y+1, z-1, light, 2, 2, 6, 4, 1); //WOOD WALL 3 //GOOD
			ibf.buildMeta(world, x+5, y+1, z-1, light, 2, 2, 6, 4, 1); //WOOD WALL 4 //GOOD
			
			ibf.buildMeta(world, x-3, y+2, z+5, pane, 0, 2, 1, 2, 7); //PANE WALL 1
			ibf.buildMeta(world, x-3, y+2, z-2, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (1)
			ibf.buildMeta(world, x+3, y+2, z-2, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (2)
			ibf.buildMeta(world, x-5, y+2, z, pane, 0, 2, 4, 2, 1); //PANE WALL 3 //GOOD
			ibf.buildMeta(world, x+5, y+2, z, pane, 0, 2, 4, 2, 1); //PANE WALL 4 //GOOD

			ibf.buildMeta(world, x-1, y+1, z-2, log, 2, 2, 1, 3, 3); //LOG ENTRANCE

			ItemDoor.placeDoorBlock(world, x, y+1, z-2, 1, Blocks.wooden_door); //DOOR //NOT 0 //GOOD

			/************************ Layer 1 to 4 : PORCH ************************/ //DONE
			ibf.buildMeta(world, x-4, y+4, z-6, slabD, 9, 2, 4, 1, 9); //PORCH ROOF 1
			ibf.buildMeta(world, x-3, y+4, z-5, slabD, 10, 2, 2, 1, 7); //PORCH ROOF 2

			ibf.buildMeta(world, x-4, y+1, z-3, dark, 1, 2, 1, 4, 1); //PORCH CORNER 1
			ibf.buildMeta(world, x+4, y+1, z-3, dark, 1, 2, 1, 4, 1); //PORCH CORNER 2
			ibf.buildMeta(world, x-4, y+1, z-6, dark, 1, 2, 1, 4, 1); //PORCH CORNER 3
			ibf.buildMeta(world, x+4, y+1, z-6, dark, 1, 2, 1, 4, 1); //PORCH CORNER 4
			
			ibf.buildMeta(world, x-4, y+2, z-3, fence, 0, 2, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			ibf.buildMeta(world, x+4, y+2, z-3, fence, 0, 2, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			ibf.buildMeta(world, x-4, y+2, z-6, fence, 0, 2, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			ibf.buildMeta(world, x+4, y+2, z-6, fence, 0, 2, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			ibf.buildMeta(world, x-4, y+1, z-5, fence, 0, 2, 2, 1, 1); //PORCH SIDE 1
			ibf.buildMeta(world, x+4, y+1, z-5, fence, 0, 2, 2, 1, 1); //PORCH SIDE 2
			ibf.buildMeta(world, x-3, y+1, z-6, fence, 0, 2, 1, 1, 7); //PORCH SIDE 3 (FRONT)
			world.setBlock(x, y+1, z-6, gate, 0, 0); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/ //DONE
			ibf.buildMeta(world, x-5, y+5, z+5, slabD, 1, 2, 1, 1, 11); //HOUSE ROOF 1
			ibf.buildMeta(world, x-5, y+5, z-2, slabD, 1, 2, 1, 1, 11); //HOUSE ROOF 2
			ibf.buildMeta(world, x-5, y+5, z-1, slabD, 1, 2, 6, 1, 1); //HOUSE ROOF 3 //GOOD
			ibf.buildMeta(world, x+5, y+5, z-1, slabD, 1, 2, 6, 1, 1); //HOUSE ROOF 4 //GOOD

			ibf.buildMeta(world, x-4, y+5, z+4, light, 2, 2, 1, 1, 9); //HOUSE ROOF 1
			ibf.buildMeta(world, x-4, y+5, z-1, light, 2, 2, 1, 1, 9); //HOUSE ROOF 2
			ibf.buildMeta(world, x-4, y+5, z, light, 2, 2, 4, 1, 1); //HOUSE ROOF 3
			ibf.buildMeta(world, x+4, y+5, z, light, 2, 2, 4, 1, 1); //HOUSE ROOF 4

			ibf.buildMeta(world, x-3, y+6, z+3, slabD, 1, 2, 1, 1, 7); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z, slabD, 1, 2, 1, 1, 7); //HOUSE ROOF 2
			ibf.buildMeta(world, x-3, y+6, z+1, slabD, 1, 2, 2, 1, 1); //HOUSE ROOF 3
			ibf.buildMeta(world, x+3, y+6, z+1, slabD, 1, 2, 2, 1, 1); //HOUSE ROOF 4

			ibf.buildMeta(world, x-2, y+6, z+1, light, 2, 2, 2, 1, 5); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			world.setBlock(x - 4, y + 1, z + 4, bed, 9, 0); //BED //NOT 10, 9, 11, 8
			world.setBlock(x - 3, y + 1, z + 4, bed, 1, 0); //BED //NOT 2, 1, 3, 0
			
			world.setBlock(x+2, y + 1, z+4, chest); //CHEST
			world.setBlock(x+3, y + 1, z+4, chest); //CHEST
			world.setBlock(x+4, y + 1, z+3, chest); //CHEST
			world.setBlock(x+4, y + 1, z+2, chest); //CHEST
			
			world.setBlock(x + 4, y + 1, z + 4, craft); //WORKBENCH
			world.setBlock(x - 4, y + 1, z + 2, furnace, 2, 0); //FURNACE //NOT 3
			world.setBlock(x - 4, y + 1, z + 1, furnace, 2, 0); //FURNACE //NOT 3

			world.setBlock(x - 4, y + 1, z - 1, stair, 9, 0); //CHAIR //NOT 3, 2, 1
			world.setBlock(x+4, y + 1, z-1, stair, 0, 0); //CHAIR //NOT 10 //GOOD
			world.setBlock(x-4, y + 1, z, sign, 3, 0); //CHAIR //NOT 4, 5
			world.setBlock(x+4, y + 1, z, sign, 3, 0); //CHAIR //NOT 4, 5
			
			world.setBlock(x-2,y+2,z-1,plate); //TABLE
			world.setBlock(x+2,y+2,z-1,plate); //TABLE
			world.setBlock(x-2,y+1,z-1,fence); //TABLE
			world.setBlock(x+2,y+1,z-1,fence); //TABLE
			
			world.setBlock(x - 1, y + 2, z - 6, torch);
			world.setBlock(x+1, y + 2, z-6, torch);
			world.setBlock(x - 1, y + 3, z - 3, torch);
			world.setBlock(x+1, y + 3, z-3, torch);
			world.setBlock(x-2, y + 4, z+4, torch);
			world.setBlock(x + 2, y + 4, z + 4, torch);
			world.setBlock(x-4, y + 4, z+2, torch);
			world.setBlock(x + 4, y + 4, z + 2, torch);
			world.setBlock(x-4, y + 4, z+1, torch);
			world.setBlock(x + 4, y + 4, z + 1, torch);
			world.setBlock(x - 2, y + 4, z - 1, torch);
			world.setBlock(x+2, y + 4, z-1, torch);
			world.setBlock(x+5, y + 4, z-6, torch);
			world.setBlock(x - 5, y + 4, z - 6, torch);
			world.setBlock(x - 4, y + 4, z - 7, torch);
			world.setBlock(x+4, y + 4, z-7, torch);
			
			if (config.packWood == true) {
				world.setBlock(x, y, z, mb.ibWood, 6, 2);
			}
		}
		
		/************************ meta == 3 ************************/
		else if (meta == 3 || meta == 7) {
			/************************ Layer 0 to 7 : AIR ************************/
			ibf.buildMeta(world, x-5, y, z-5, air, 0, 2, 11, 6, 8); //HOUSE ROOM
			ibf.buildMeta(world, x-4, y+5, z-4, air, 0, 2, 9, 1, 6); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z-3, air, 0, 2, 7, 1, 4); //HOUSE ROOF 2

			ibf.buildMeta(world, x+3, y, z-4, air, 0, 2, 9, 5, 4); //PORCH

			/************************ Layer 0 : FLOOR ************************/
			ibf.buildMeta(world, x-5, y, z-5, dark, 1, 2, 11, 1, 8); //HOME FLOOR 1
			ibf.buildMeta(world, x-4, y, z-4, light, 2, 2, 9, 1, 6); //HOME FLOOR 2
			ibf.buildMeta(world, x-3, y, z-3, dark, 1, 2, 7, 1, 4); //HOME FLOOR 3
			ibf.buildMeta(world, x-2, y, z-2, light, 2, 2, 5, 1, 2); //HOME FLOOR 4

			ibf.buildMeta(world, x+3, y, z-4, dark, 1, 2, 9, 1, 4); //PORCH FLOOR 1
			ibf.buildMeta(world, x+4, y, z-3, light, 2, 2, 7, 1, 2); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/
			ibf.buildMeta(world, x-5, y+1, z-5, log, 2, 2, 1, 4, 1); //LOG CORNER 1
			ibf.buildMeta(world, x-5, y+1, z+5, log, 2, 2, 1, 4, 1); //LOG CORNER 2
			ibf.buildMeta(world, x+2, y+1, z-5, log, 2, 2, 1, 4, 1); //LOG CORNER 3
			ibf.buildMeta(world, x+2, y+1, z+5, log, 2, 2, 1, 4, 1); //LOG CORNER 4
			ibf.buildMeta(world, x-5, y+1, z-4, light, 2, 2, 9, 4, 1); //WOOD WALL 1
			ibf.buildMeta(world, x+2, y+1, z-4, light, 2, 2, 9, 4, 1); //WOOD WALL 2
			ibf.buildMeta(world, x-4, y+1, z-5, light, 2, 2, 1, 4, 6); //WOOD WALL 3
			ibf.buildMeta(world, x-4, y+1, z+5, light, 2, 2, 1, 4, 6); //WOOD WALL 4
			
			ibf.buildMeta(world, x-5, y+2, z-3, pane, 0, 2, 7, 2, 1); //PANE WALL 1
			ibf.buildMeta(world, x+2, y+2, z-3, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (1)
			ibf.buildMeta(world, x+2, y+2, z+3, pane, 0, 2, 1, 2, 1); //PANE WALL 2 (2)
			ibf.buildMeta(world, x-3, y+2, z-5, pane, 0, 2, 1, 2, 4); //PANE WALL 3
			ibf.buildMeta(world, x-3, y+2, z+5, pane, 0, 2, 1, 2, 4); //PANE WALL 4

			ibf.buildMeta(world, x+2, y+1, z-1, log, 2, 2, 3, 3, 1); //LOG ENTRANCE

			ItemDoor.placeDoorBlock(world, x+2, y+1, z, 2, Blocks.wooden_door); //DOOR

			/************************ Layer 1 to 4 : PORCH ************************/
			ibf.buildMeta(world, x+3, y+4, z-4, slabD, 9, 2, 9, 1, 4); //PORCH ROOF 1
			ibf.buildMeta(world, x+4, y+4, z-3, slabD, 10, 2, 7, 1, 2); //PORCH ROOF 2

			ibf.buildMeta(world, x+3, y+1, z-4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 1
			ibf.buildMeta(world, x+3, y+1, z+4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 2
			ibf.buildMeta(world, x+6, y+1, z-4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 3
			ibf.buildMeta(world, x+6, y+1, z+4, dark, 1, 2, 1, 4, 1); //PORCH CORNER 4
			
			ibf.buildMeta(world, x+3, y+2, z-4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			ibf.buildMeta(world, x+3, y+2, z+4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			ibf.buildMeta(world, x+6, y+2, z-4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			ibf.buildMeta(world, x+6, y+2, z+4, fence, 0, 2, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			ibf.buildMeta(world, x+4, y+1, z-4, fence, 0, 2, 1, 1, 2); //PORCH SIDE 1
			ibf.buildMeta(world, x+4, y+1, z+4, fence, 0, 2, 1, 1, 2); //PORCH SIDE 2
			ibf.buildMeta(world, x+6, y+1, z-3, fence, 0, 2, 7, 1, 1); //PORCH SIDE 3 (FRONT)
			world.setBlock(x+6, y+1, z, gate, 1, 0); //PORCH GATE
			
			/************************ Layer 5 tp 6 : ROOF ************************/
			ibf.buildMeta(world, x-5, y+5, z-5, slabD, 1, 2, 11, 1, 1); //HOUSE ROOF 1
			ibf.buildMeta(world, x+2, y+5, z-5, slabD, 1, 2, 11, 1, 1); //HOUSE ROOF 2
			ibf.buildMeta(world, x-4, y+5, z-5, slabD, 1, 2, 1, 1, 6); //HOUSE ROOF 3
			ibf.buildMeta(world, x-4, y+5, z+5, slabD, 1, 2, 1, 1, 6); //HOUSE ROOF 4

			ibf.buildMeta(world, x-4, y+5, z-4, light, 2, 2, 9, 1, 1); //HOUSE ROOF 1
			ibf.buildMeta(world, x+1, y+5, z-4, light, 2, 2, 9, 1, 1); //HOUSE ROOF 2
			ibf.buildMeta(world, x-3, y+5, z-4, light, 2, 2, 1, 1, 4); //HOUSE ROOF 3
			ibf.buildMeta(world, x-3, y+5, z+4, light, 2, 2, 1, 1, 4); //HOUSE ROOF 4

			ibf.buildMeta(world, x-3, y+6, z-3, slabD, 1, 2, 7, 1, 1); //HOUSE ROOF 1
			ibf.buildMeta(world, x, y+6, z-3, slabD, 1, 2, 7, 1, 1); //HOUSE ROOF 2
			ibf.buildMeta(world, x-2, y+6, z-3, slabD, 1, 2, 1, 1, 2); //HOUSE ROOF 3
			ibf.buildMeta(world, x-2, y+6, z+3, slabD, 1, 2, 1, 1, 2); //HOUSE ROOF 4

			ibf.buildMeta(world, x-2, y+6, z-2, light, 2, 2, 5, 1, 2); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			world.setBlock(x - 4, y + 1, z - 4, bed, 10, 0); //BED
			world.setBlock(x - 4, y + 1, z - 3, bed, 2, 0); //BED
			
			world.setBlock(x - 4, y + 1, z + 2, chest); //CHEST
			world.setBlock(x - 4, y + 1, z + 3, chest); //CHEST
			world.setBlock(x - 3, y + 1, z + 4, chest); //CHEST
			world.setBlock(x - 2, y + 1, z + 4, chest); //CHEST
			
			world.setBlock(x - 4, y + 1, z + 4, craft); //WORKBENCH
			world.setBlock(x - 2, y + 1, z - 4, furnace, 3, 0); //FURNACE
			world.setBlock(x - 1, y + 1, z - 4, furnace, 3, 0); //FURNACE

			world.setBlock(x + 1, y + 1, z - 4, stair, 3, 0); //CHAIR
			world.setBlock(x + 1, y + 1, z + 4, stair, 10, 0); //CHAIR
			world.setBlock(x, y + 1, z - 4, sign, 4, 0); //CHAIR
			world.setBlock(x, y + 1, z + 4, sign, 4, 0); //CHAIR
			
			world.setBlock(x+1,y+2,z-2,plate); //TABLE
			world.setBlock(x+1,y+2,z+2,plate); //TABLE
			world.setBlock(x+1,y+1,z-2,fence); //TABLE
			world.setBlock(x+1,y+1,z+2,fence); //TABLE
			
			world.setBlock(x + 6, y + 2, z - 1, torch);
			world.setBlock(x + 6, y + 2, z + 1, torch);
			world.setBlock(x + 3, y + 3, z - 1, torch);
			world.setBlock(x + 3, y + 3, z + 1, torch);
			world.setBlock(x - 4, y + 4, z - 2, torch);
			world.setBlock(x - 4, y + 4, z + 2, torch);
			world.setBlock(x - 2, y + 4, z - 4, torch);
			world.setBlock(x - 2, y + 4, z + 4, torch);
			world.setBlock(x - 1, y + 4, z - 4, torch);
			world.setBlock(x - 1, y + 4, z + 4, torch);
			world.setBlock(x + 1, y + 4, z - 2, torch);
			world.setBlock(x + 1, y + 4, z + 2, torch);
			world.setBlock(x + 6, y + 4, z + 5, torch);
			world.setBlock(x + 6, y + 4, z - 5, torch);
			world.setBlock(x + 7, y + 4, z - 4, torch);
			world.setBlock(x + 7, y + 4, z + 4, torch);
			
			if (config.packWood == true) {
				world.setBlock(x, y, z, mb.ibWood, 7, 2);
			}
		}
	}

	public static boolean notThere = false;
	
	private boolean check(World world, int x, int y, int z, int meta) {
		/*int light = Block.planks.blockID;
		int dark = Block.planks.blockID;
		int log = Block.wood.blockID;
		int craft = Block.workbench.blockID;
		int chest = Block.chest.blockID;
		int furnace = Block.furnaceIdle.blockID;
		int fence = Block.fence.blockID;
		int gate = Block.fenceGate.blockID;
		int stair = Block.stairsWoodOak.blockID;
		int sign = Block.signWall.blockID;
		int bed = Block.bed.blockID;
		int door = Block.doorWood.blockID;
		int pane = Block.thinGlass.blockID;
		int torch = Block.torchWood.blockID;
		int plate = Block.pressurePlatePlanks.blockID;
		int slabL = 126;
		int slabD = 126;
		int air = 0;*/
		
		Block light = Blocks.planks;
		Block dark = Blocks.planks;
		Block log = Blocks.log;
		Block craft = Blocks.crafting_table;
		Block chest = Blocks.chest;
		Block furnace = Blocks.furnace;
		Block fence = Blocks.fence;
		Block gate = Blocks.fence_gate;
		Block stair = Blocks.oak_stairs;
		Block sign = Blocks.wall_sign;
		Block bed = Blocks.bed;
		Block door = Blocks.wooden_door;
		Block pane = Blocks.glass_pane;
		Block torch = Blocks.torch;
		Block plate = Blocks.wooden_pressure_plate;
		Block slabL = Blocks.wooden_slab;
		Block slabD = Blocks.wooden_slab;
		Block air = Blocks.air;
		
		drop = 1;
		//int meta = world.getBlockMetadata(x, y, z);
		
		EntityPlayer player = world.getClosestPlayer(x, y, z, 10);
		
		if (meta == 4) {
			//ibf.ifNoBlockThenStop(world, x+4, y+1, z-4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x+3, y+1, z-4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-3, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-2, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-1, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x, y+1, z+2, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 4, 2);
				notThere = false;
				drop = 0;
				return true;
			}
			
			pack(world, x, y, z, 4);

			//player.triggerAchievement(ib.achWoodPack);
			
			ibf.msg(player, ibf.woodPack, Colors.a);
			ibf.sound(world, "step.ladder", x, y, z);
		} else if (meta == 5) {
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+3, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z+4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x+1, y+1, z+4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 5, 2);
				notThere = false;
				drop = 0;
				return true;
			}
			
			pack(world, x, y, z, 5);

			//player.triggerAchievement(ib.achWoodPack);
			
			ibf.msg(player, ibf.woodPack, Colors.a);
			ibf.sound(world, "step.ladder", x, y, z);
		} else if (meta == 6) {
			ibf.ifNoBlockThenStop(world, x-3, y+1, z+4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+3, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+2, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+1, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x, y+1, z-2, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 6, 2);
				notThere = false;
				drop = 0;
				return true;
			}
			
			pack(world, x, y, z, 6);

			//player.triggerAchievement(ib.achWoodPack);

			ibf.msg(player, ibf.woodPack, Colors.a);
			ibf.sound(world, "step.ladder", x, y, z);
		} else if (meta == 7) {
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-3, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-3, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z-4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x-1, y+1, z-4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 7, 2);
				notThere = false;
				drop = 0;
				return true;
			}
			
			pack(world, x, y, z, 7);

			//player.triggerAchievement(ib.achWoodPack);
			
			ibf.msg(player, ibf.woodPack, Colors.a);
			ibf.sound(world, "step.ladder", x, y, z);
		}
		return true;
	}
	

	private void pack(World world, int x, int y, int z, int meta) {
		//int air = 0;
		Block air = Blocks.air;
		//int meta = world.getBlockMetadata(x, y, z);
		
		/************************ meta == 0 ************************/
		if (meta == 0 || meta == 4) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			ibf.buildMeta(world, x-5, y, z-5, air, 0, 2, 8, 6, 11); //HOUSE ROOM
			ibf.buildMeta(world, x-4, y+5, z-4, air, 0, 2, 6, 1, 9); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z-3, air, 0, 2, 4, 1, 7); //HOUSE ROOF 2

			ibf.buildMeta(world, x-4, y, z+3, air, 0, 2, 4, 5, 9); //PORCH
			
			world.setBlock(x + 5, y + 4, z + 6, air, 0, 2);
			world.setBlock(x-5, y + 4, z+6, air, 0, 2);
			world.setBlock(x-4, y + 4, z+7, air, 0, 2);
			world.setBlock(x + 4, y + 4, z + 7, air, 0, 2);
			
			System.out.println("0 or 4");
		}
		
		/************************ meta == 1 ************************/
		else if (meta == 1 || meta == 5) {
			/************************ Layer 0 to 7 : AIR ************************/
			ibf.buildMeta(world, x-2, y, z-5, air, 0, 2, 11, 6, 8); //HOUSE ROOM
			ibf.buildMeta(world, x-1, y+5, z-4, air, 0, 2, 9, 1, 6); //HOUSE ROOF 1
			ibf.buildMeta(world, x, y+6, z-3, air, 0, 2, 7, 1, 4); //HOUSE ROOF 2

			ibf.buildMeta(world, x-6, y, z-4, air, 0, 2, 9, 5, 4); //PORCH

			world.setBlock(x - 6, y + 4, z + 5, air, 0, 2);
			world.setBlock(x - 6, y + 4, z - 5, air, 0, 2);
			world.setBlock(x - 7, y + 4, z - 4, air, 0, 2);
			world.setBlock(x - 7, y + 4, z + 4, air, 0, 2);
			
			System.out.println("1 or 5");
		}
		
		/************************ meta == 2 ************************/
		else if (meta == 2 || meta == 6) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			ibf.buildMeta(world, x-5, y, z-2, air, 0, 2, 8, 6, 11); //HOUSE ROOM
			ibf.buildMeta(world, x-4, y+5, z-1, air, 0, 2, 6, 1, 9); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z, air, 0, 2, 4, 1, 7); //HOUSE ROOF 2

			ibf.buildMeta(world, x-4, y, z-6, air, 0, 2, 4, 5, 9); //PORCH

			world.setBlock(x+5, y + 4, z-6, air, 0, 2);
			world.setBlock(x - 5, y + 4, z - 6, air, 0, 2);
			world.setBlock(x - 4, y + 4, z - 7, air, 0, 2);
			world.setBlock(x+4, y + 4, z-7, air, 0, 2);
			
			System.out.println("2 or 6");
		}
		
		/************************ meta == 3 ************************/
		else if (meta == 3 || meta == 7) {
			/************************ Layer 0 to 7 : AIR ************************/
			ibf.buildMeta(world, x-5, y, z-5, air, 0, 2, 11, 6, 8); //HOUSE ROOM
			ibf.buildMeta(world, x-4, y+5, z-4, air, 0, 2, 9, 1, 6); //HOUSE ROOF 1
			ibf.buildMeta(world, x-3, y+6, z-3, air, 0, 2, 7, 1, 4); //HOUSE ROOF 2

			ibf.buildMeta(world, x+3, y, z-4, air, 0, 2, 9, 5, 4); //PORCH

			world.setBlock(x + 6, y + 4, z + 5, air, 0, 2);
			world.setBlock(x + 6, y + 4, z - 5, air, 0, 2);
			world.setBlock(x + 7, y + 4, z - 4, air, 0, 2);
			world.setBlock(x + 7, y + 4, z + 4, air, 0, 2);
			
			System.out.println("3 or 7");
		}
	}

	private int checkDrop(World world, int x, int y, int z, int meta) {
		/*int light = Block.planks.blockID;
		int dark = Block.planks.blockID;
		int log = Block.wood.blockID;
		int craft = Block.workbench.blockID;
		int chest = Block.chest.blockID;
		int furnace = Block.furnaceIdle.blockID;
		int fence = Block.fence.blockID;
		int gate = Block.fenceGate.blockID;
		int stair = Block.stairsWoodOak.blockID;
		int sign = Block.signWall.blockID;
		int bed = Block.bed.blockID;
		int door = Block.doorWood.blockID;
		int pane = Block.thinGlass.blockID;
		int torch = Block.torchWood.blockID;
		int plate = Block.pressurePlatePlanks.blockID;
		int slabL = 126;
		int slabD = 126;
		int air = 0;*/
		
		Block light = Blocks.planks;
		Block dark = Blocks.planks;
		Block log = Blocks.log;
		Block craft = Blocks.crafting_table;
		Block chest = Blocks.chest;
		Block furnace = Blocks.furnace;
		Block fence = Blocks.fence;
		Block gate = Blocks.fence_gate;
		Block stair = Blocks.oak_stairs;
		Block sign = Blocks.wall_sign;
		Block bed = Blocks.bed;
		Block door = Blocks.wooden_door;
		Block pane = Blocks.glass_pane;
		Block torch = Blocks.torch;
		Block plate = Blocks.wooden_pressure_plate;
		Block slabL = Blocks.wooden_slab;
		Block slabD = Blocks.wooden_slab;
		Block air = Blocks.air;
		
		//int meta = world.getBlockMetadata(x, y, z);
		
		EntityPlayer player = world.getClosestPlayer(x, y, z, 10);
		
		if (meta == 4) {
			ibf.ifNoBlockThenStop(world, x+3, y+1, z-4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-3, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-2, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-1, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x, y+1, z+2, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 4, 2);
				notThere = false;
				return 0;
			}
			
			//pack(world, x, y, z, 4);

			//player.triggerAchievement(ib.achWood);
			
			//ibf.msg(player, "\u00a7aInstant Wooden House Re-Packed.");
		} else if (meta == 5) {
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+3, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z-4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z-4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z+4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x+1, y+1, z+4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 5, 2);
				notThere = false;
				return 0;
			}
			
			//pack(world, x, y, z, 5);

			//player.triggerAchievement(ib.achWood);
			
			//ibf.msg(player, "\u00a7aInstant Wooden House Re-Packed.");
		} else if (meta == 6) {
			ibf.ifNoBlockThenStop(world, x-3, y+1, z+4, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+3, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x+4, y+1, z+4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+2, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+1, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x, y+1, z-2, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 6, 2);
				notThere = false;
				return 0;
			}
			
			//pack(world, x, y, z, 6);

			//player.triggerAchievement(ib.achWood);
		} else if (meta == 7) {
			ibf.ifNoBlockThenStop(world, x-4, y+1, z-3, bed, player, "Bed");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+2, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+3, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-3, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z+4, chest, player, "Chest");
			ibf.ifNoBlockThenStop(world, x-4, y+1, z+4, craft, player, "Workbench");
			ibf.ifNoBlockThenStop(world, x-2, y+1, z-4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x-1, y+1, z-4, furnace, player, "Furnace");
			ibf.ifNoBlockThenStop(world, x+2, y+1, z, door, player, "Door");
			
			if (notThere == true) {
				world.setBlock(x, y, z, mb.ibWood, 7, 2);
				notThere = false;
				return 0;
			}
			
			//pack(world, x, y, z, 7);

			//player.triggerAchievement(ib.achWood);
			
			//ibf.msg(player, "\u00a7aInstant Wooden House Re-Packed.");
		}
		return 1;
	}
}