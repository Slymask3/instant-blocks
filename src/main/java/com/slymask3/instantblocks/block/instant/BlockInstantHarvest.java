package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockInstantHarvest extends BlockInstant implements EntityBlock {
	public BlockInstantHarvest() {
		super(Block.Properties.of(Material.WOOD)
				.strength(1.5F, 2000F)
				.sound(SoundType.WOOD)
		);
		setGuiID(GuiID.HARVEST);
		setCreateMessage(Strings.CREATE_HARVEST);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return null;
	}
	
	public boolean build(Level world, int X, int Y, int Z, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		int radius = Config.Common.RADIUS_HARVEST.get();

		BuildHelper.setBlock(world,X, Y, Z, Blocks.CHEST);
		ChestBlockEntity chest = (ChestBlockEntity)world.getBlockEntity(new BlockPos(X,Y,Z));

		Random rand = new Random();
    	
        int x = (int) (X -radius);
        int y = (int) (Y +radius);
        int z = (int) (Z -radius);
   
        int x_base = x;
        int z_base = z;
 
        for(int i=0; i<radius*2+1; i++) {
            for(int j=0; j<radius*2+1; j++) {
                for(int k=0; k<radius*2+1; k++) {
                    Block block = BuildHelper.getBlock(world,x, y, z);
					BlockState state = world.getBlockState(new BlockPos(x,y,z));
                    
                    if(chest.getItem(chest.getContainerSize()-1) != ItemStack.EMPTY) {
						if(Helper.isDoubleChest(chest)) {
							Y++;
							X--;
						} else {
							X++;
						}
						BuildHelper.setBlock(world,X, Y, Z, Blocks.CHEST);
						chest = (ChestBlockEntity)world.getBlockEntity(new BlockPos(X,Y,Z));
                    }
                    
                    if(block == Blocks.OAK_LOG && logOak) { //OAK
						addLog(chest,x,y,z,block,Blocks.OAK_SAPLING,replant);
                    } else if(block == Blocks.SPRUCE_LOG && logSpruce) { //SPRUCE
						addLog(chest,x,y,z,block,Blocks.SPRUCE_SAPLING,replant);
                    } else if(block == Blocks.BIRCH_LOG && logBirch) { //BIRCH
						addLog(chest,x,y,z,block,Blocks.BIRCH_SAPLING,replant);
                    } else if(block == Blocks.JUNGLE_LOG && logJungle) { //JUNGLE
						addLog(chest,x,y,z,block,Blocks.JUNGLE_SAPLING,replant);
                    } else if(block == Blocks.ACACIA_LOG && logAcacia) { //ACACIA
						addLog(chest,x,y,z,block,Blocks.ACACIA_SAPLING,replant);
                    } else if(block == Blocks.DARK_OAK_LOG && logDark) { //DARK OAK
						addLog(chest,x,y,z,block,Blocks.DARK_OAK_SAPLING,replant);
                    } else if(block == Blocks.WHEAT && state.getValue(CropBlock.AGE) == CropBlock.MAX_AGE && wheat) { //WHEAT
                    	Helper.addItemsToChest(chest, Items.WHEAT, 1);
						replantBlock(world,x,y,z,Blocks.WHEAT,replant);
                    } else if(block == Blocks.CARROTS && state.getValue(CarrotBlock.AGE) == CarrotBlock.MAX_AGE && carrot) { //CARROT
                    	Helper.addItemsToChest(chest, Items.CARROT, rand.nextInt(4)+1);
						replantBlock(world,x,y,z,Blocks.CARROTS,replant);
                    } else if(block == Blocks.POTATOES && state.getValue(PotatoBlock.AGE) == PotatoBlock.MAX_AGE && potato) { //POTATO
                    	Helper.addItemsToChest(chest, Items.POTATO, rand.nextInt(4)+1);
						replantBlock(world,x,y,z,Blocks.POTATOES,replant);
                    } else if(block == Blocks.CACTUS && cactus) { //CACTUS
						Block blockBelow = BuildHelper.getBlock(world,x, y-1, z);
                    	if((blockBelow == Blocks.SAND || blockBelow == Blocks.RED_SAND) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.CACTUS);
                    	} else {
                        	Helper.addItemsToChest(chest, block, 1);
                    		BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    	}
                    } else if(block == Blocks.PUMPKIN && pumpkin) { //PUMPKIN
                    	Helper.addItemsToChest(chest, block, 1);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    } else if(block == Blocks.MELON && melon) { //MELON
                    	Helper.addItemsToChest(chest, Items.MELON_SLICE, rand.nextInt(5)+3);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    } else if(block == Blocks.SUGAR_CANE && sugarcane) { //SUGARCANE
                    	if(canPlaceSugarCane(BuildHelper.getBlock(world,x, y-1, z)) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, block);
                    	} else {
                        	Helper.addItemsToChest(chest, Items.SUGAR_CANE, 1);
                    		BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    	}
                    } else if(block == Blocks.COCOA && state.getValue(CocoaBlock.AGE) == CocoaBlock.MAX_AGE && cocoa) { //COCOA
                    	Helper.addItemsToChest(chest, Items.COCOA_BEANS, 3);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    } else if((block == Blocks.RED_MUSHROOM_BLOCK || block == Blocks.RED_MUSHROOM) && mushroom) { //MUSHROOM RED
                    	Helper.addItemsToChest(chest, Blocks.RED_MUSHROOM, 1);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    } else if((block == Blocks.BROWN_MUSHROOM_BLOCK || block == Blocks.BROWN_MUSHROOM) && mushroom) { //MUSHROOM BROWN
                    	Helper.addItemsToChest(chest, Blocks.BROWN_MUSHROOM, 1);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
                    } else if(block == Blocks.NETHER_WART && state.getValue(NetherWartBlock.AGE) == NetherWartBlock.MAX_AGE && netherwart) { //NETHERWART
                    	Helper.addItemsToChest(chest, Items.NETHER_WART, rand.nextInt(3)+2);
						replantBlock(world,x,y,z,Blocks.NETHER_WART,replant);
                    } else if(block instanceof LeavesBlock) {
						BuildHelper.setBlock(world,x,y,z,Blocks.AIR);
					}
                    x++;
                }
                z++;
                x = x_base;
            }
            z = z_base;
            y--;
        }

		return true;
    }

	private void addLog(ChestBlockEntity chest, int x, int y, int z, Block block, Block sapling, boolean replant) {
		Level world = chest.getLevel();
		Helper.addItemsToChest(chest, block, 1);
		replantBlock(world,x,y,z,sapling,(BuildHelper.getBlock(world,x, y-1, z) == Blocks.DIRT || BuildHelper.getBlock(world,x, y-1, z) == Blocks.GRASS_BLOCK) && replant);
	}

	private void replantBlock(Level world, int x, int y, int z, Block block, boolean replant) {
		if(replant) {
			BuildHelper.setBlock(world,x, y, z, block);
		} else {
			BuildHelper.setBlock(world,x, y, z, Blocks.AIR);
		}
	}

	private boolean canPlaceSugarCane(Block block) {
		return block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.SAND || block == Blocks.RED_SAND;
	}
}