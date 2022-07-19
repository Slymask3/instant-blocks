package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.HarvestBlockEntity;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class InstantHarvestBlock extends InstantBlock implements EntityBlock {
	public InstantHarvestBlock() {
		super(Properties.of(Material.WOOD)
				.strength(1.5F)
				.sound(SoundType.WOOD)
		);
		setScreen(ClientHelper.Screen.HARVEST);
		setCreateMessage(Strings.CREATE_HARVEST);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_HARVEST();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HarvestBlockEntity(pos,state);
	}
	
	public boolean build(Level world, int X, int Y, int Z, Player player) {
		HarvestBlockEntity blockEntity = (HarvestBlockEntity)world.getBlockEntity(new BlockPos(X,Y,Z));
		int radius = Common.CONFIG.RADIUS_HARVEST();

		Builder.Single.setup(world,X,Y,Z).setBlock(Blocks.CHEST).build();
		ChestBlockEntity chest = (ChestBlockEntity)world.getBlockEntity(new BlockPos(X,Y,Z));

		Random rand = new Random();
    	
        int x = X -radius;
        int y = Y +radius;
        int z = Z -radius;
   
        int x_base = x;
        int z_base = z;
 
        for(int i=0; i<radius*2+1; i++) {
            for(int j=0; j<radius*2+1; j++) {
                for(int k=0; k<radius*2+1; k++) {
                    Block block = Helper.getBlock(world,x, y, z);
					BlockState state = world.getBlockState(new BlockPos(x,y,z));
                    
                    if(chest.getItem(chest.getContainerSize()-1) != ItemStack.EMPTY) {
						if(Helper.isDoubleChest(chest)) {
							Y++;
							X--;
						} else {
							X++;
						}
						Builder.Single.setup(world,X,Y,Z).setBlock(Blocks.CHEST).build();
						chest = (ChestBlockEntity)world.getBlockEntity(new BlockPos(X,Y,Z));
                    }
                    
                    if(block == Blocks.OAK_LOG && blockEntity.logOak) { //OAK
						addLog(chest,x,y,z,block,Blocks.OAK_SAPLING,blockEntity.replant);
                    } else if(block == Blocks.SPRUCE_LOG && blockEntity.logSpruce) { //SPRUCE
						addLog(chest,x,y,z,block,Blocks.SPRUCE_SAPLING,blockEntity.replant);
                    } else if(block == Blocks.BIRCH_LOG && blockEntity.logBirch) { //BIRCH
						addLog(chest,x,y,z,block,Blocks.BIRCH_SAPLING,blockEntity.replant);
                    } else if(block == Blocks.JUNGLE_LOG && blockEntity.logJungle) { //JUNGLE
						addLog(chest,x,y,z,block,Blocks.JUNGLE_SAPLING,blockEntity.replant);
                    } else if(block == Blocks.ACACIA_LOG && blockEntity.logAcacia) { //ACACIA
						addLog(chest,x,y,z,block,Blocks.ACACIA_SAPLING,blockEntity.replant);
                    } else if(block == Blocks.DARK_OAK_LOG && blockEntity.logDark) { //DARK OAK
						addLog(chest,x,y,z,block,Blocks.DARK_OAK_SAPLING,blockEntity.replant);
                    } else if(block == Blocks.WHEAT && state.getValue(CropBlock.AGE) == CropBlock.MAX_AGE && blockEntity.wheat) { //WHEAT
                    	Helper.addToChest(chest, Items.WHEAT, 1);
						replantBlock(world,x,y,z,Blocks.WHEAT,blockEntity.replant);
                    } else if(block == Blocks.CARROTS && state.getValue(CarrotBlock.AGE) == CarrotBlock.MAX_AGE && blockEntity.carrot) { //CARROT
                    	Helper.addToChest(chest, Items.CARROT, rand.nextInt(4)+1);
						replantBlock(world,x,y,z,Blocks.CARROTS,blockEntity.replant);
                    } else if(block == Blocks.POTATOES && state.getValue(PotatoBlock.AGE) == PotatoBlock.MAX_AGE && blockEntity.potato) { //POTATO
                    	Helper.addToChest(chest, Items.POTATO, rand.nextInt(4)+1);
						replantBlock(world,x,y,z,Blocks.POTATOES,blockEntity.replant);
                    } else if(block == Blocks.CACTUS && blockEntity.cactus) { //CACTUS
						Block blockBelow = Helper.getBlock(world,x, y-1, z);
                    	if((blockBelow == Blocks.SAND || blockBelow == Blocks.RED_SAND) && blockEntity.replant) {
                    		Builder.Single.setup(world,x,y,z).setBlock(Blocks.CACTUS).build();
                    	} else {
                        	Helper.addToChest(chest, block, 1);
                    		Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    	}
                    } else if(block == Blocks.PUMPKIN && blockEntity.pumpkin) { //PUMPKIN
                    	Helper.addToChest(chest, block, 1);
                    	Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    } else if(block == Blocks.MELON && blockEntity.melon) { //MELON
                    	Helper.addToChest(chest, Items.MELON_SLICE, rand.nextInt(5)+3);
                    	Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    } else if(block == Blocks.SUGAR_CANE && blockEntity.sugarcane) { //SUGARCANE
                    	if(canPlaceSugarCane(Helper.getBlock(world,x, y-1, z)) && blockEntity.replant) {
                    		Builder.Single.setup(world,x,y,z).setBlock(block).build();
                    	} else {
                        	Helper.addToChest(chest, Items.SUGAR_CANE, 1);
                    		Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    	}
                    } else if(block == Blocks.COCOA && state.getValue(CocoaBlock.AGE) == CocoaBlock.MAX_AGE && blockEntity.cocoa) { //COCOA
                    	Helper.addToChest(chest, Items.COCOA_BEANS, 3);
                    	Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    } else if((block == Blocks.RED_MUSHROOM_BLOCK || block == Blocks.RED_MUSHROOM) && blockEntity.mushroom) { //MUSHROOM RED
                    	Helper.addToChest(chest, Blocks.RED_MUSHROOM, 1);
                    	Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    } else if((block == Blocks.BROWN_MUSHROOM_BLOCK || block == Blocks.BROWN_MUSHROOM) && blockEntity.mushroom) { //MUSHROOM BROWN
                    	Helper.addToChest(chest, Blocks.BROWN_MUSHROOM, 1);
                    	Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
                    } else if(block == Blocks.NETHER_WART && state.getValue(NetherWartBlock.AGE) == NetherWartBlock.MAX_AGE && blockEntity.netherwart) { //NETHERWART
                    	Helper.addToChest(chest, Items.NETHER_WART, rand.nextInt(3)+2);
						replantBlock(world,x,y,z,Blocks.NETHER_WART,blockEntity.replant);
                    } else if(block instanceof LeavesBlock) {
						Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
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
		Helper.addToChest(chest, block, 1);
		replantBlock(world,x,y,z,sapling,(Helper.getBlock(world,x, y-1, z) == Blocks.DIRT || Helper.getBlock(world,x, y-1, z) == Blocks.GRASS_BLOCK) && replant);
	}

	private void replantBlock(Level world, int x, int y, int z, Block block, boolean replant) {
		if(replant) {
			Builder.Single.setup(world,x,y,z).setBlock(block).build();
		} else {
			Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();
		}
	}

	private boolean canPlaceSugarCane(Block block) {
		return block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.SAND || block == Blocks.RED_SAND;
	}
}