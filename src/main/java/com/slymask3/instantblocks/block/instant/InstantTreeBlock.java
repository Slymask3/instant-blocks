package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.gui.screens.TreeScreen;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InstantTreeBlock extends InstantBlock {
	public InstantTreeBlock() {
		super(Block.Properties.of(Material.PLANT)
				.strength(0.1F, 2000F)
				.sound(SoundType.GRASS)
				.noCollission()
				.instabreak()
		);
		setGuiID(GuiID.TREE);
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	}
	
	public boolean build(Level world, int x, int y, int z, int type, boolean fullLog, boolean fullLeaves, boolean air) {
		if(type == 0) { //IF OAK
			buildOak(world, x, y, z, Blocks.OAK_LOG, Blocks.OAK_LEAVES, fullLog, fullLeaves, air);
		} else if(type == 1) { //IF SPRUCE
			buildSpruce(world, x, y, z, Blocks.SPRUCE_LOG, Blocks.SPRUCE_LEAVES, fullLog, fullLeaves, air);
		} else if(type == 2) { //IF BIRCH
			buildBirch(world, x, y, z, Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, fullLog, fullLeaves, air);
		} else if(type == 3) { //IF JUNGLE
			buildJungle(world, x, y, z, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, fullLog, fullLeaves, air);
		} else if(type == 4) { //IF ACACIA
			buildAcacia(world, x, y, z, Blocks.ACACIA_LOG, Blocks.ACACIA_LEAVES, fullLog, fullLeaves, air);
		} else if(type == 5) { //IF DARK OAK
			buildDarkOak(world, x, y, z, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, fullLog, fullLeaves, air);
		} else if(type == 6) { //IF GLASS
			buildOak(world, x, y, z, Blocks.BROWN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS, fullLog, fullLeaves, air);
		}
		setCreateMessage(Strings.CREATE_TREE.replace("%tree%", TreeScreen.treeToString(type)));
		return true;
	}

	private void buildOak(Level world, int x, int y, int z, Block log, Block leaves, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, log, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, log, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, leaves, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, leaves, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, leaves, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*4, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildSpruce(Level world, int x, int y, int z, Block log, Block leaves, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, log, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*5, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*6, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*7, z+4*0, log, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*3, y+4*2, z+4*2, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*3, y+4*2, z+4*1, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*2, z+4*0, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*2, z-4*1, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*2, z-4*2, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*2, y+4*2, z+4*3, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*3, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*2, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x+4*0, y+4*2, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*3, y+4*2, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*2, z+4*1, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*2, z+4*0, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*2, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*2, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, leaves, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);

		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, leaves, fullLeaves, air, false, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, leaves, fullLeaves, air, false, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, false, true, true, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*2, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, true, false, false, true);

		/** fifth layer **/
		buildLeaves(world, x+4*1, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, true, true, false, true);

		/** seventh layer **/
		buildLeaves(world, x+4*1, y+4*8, z+4*0, leaves, fullLeaves, air, true, true, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*8, z+4*1, leaves, fullLeaves, air, true, true, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*8, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*8, z-4*1, leaves, fullLeaves, air, true, true, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*8, z+4*0, leaves, fullLeaves, air, true, true, true, true, false, true);

		/** eighth layer **/
		buildLeaves(world, x+4*0, y+4*9, z+4*0, leaves, fullLeaves, air, true, false, true, true, true, true);
	}

	private void buildBirch(Level world, int x, int y, int z, Block log, Block leaves, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, log, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, log, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*1, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, leaves, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, leaves, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, leaves, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*4, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildJungle(Level world, int x, int y, int z, Block log, Block leaves, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, log, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*5, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*6, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*7, z+4*0, log, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*5, z+4*2, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, leaves, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*5, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*6, z+4*2, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*6, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*6, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*6, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*6, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*7, z+4*0, leaves, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*7, z+4*1, leaves, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*7, z-4*1, leaves, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*7, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*7, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*7, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*8, z+4*0, leaves, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*8, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*8, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*8, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*8, z+4*0, leaves, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildAcacia(Level world, int x, int y, int z, Block log, Block leaves, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, log, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, log, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, log, fullLog, air, true, false, true, true, false, true);

		buildLog(world, x+4*1, y+4*2, z+4*0, log, fullLog, air, true, true, true, true, true, false);

		buildLog(world, x+4*2, y+4*3, z+4*0, log, fullLog, air, true, true, true, true, true, true);
		buildLog(world, x-4*1, y+4*3, z+4*0, log, fullLog, air, true, true, true, true, true, true);

		buildLog(world, x+4*3, y+4*4, z+4*0, log, fullLog, air, true, true, true, true, true, true);
		buildLog(world, x-4*2, y+4*4, z+4*0, log, fullLog, air, false, true, true, true, true, true);

		buildLog(world, x-4*2, y+4*5, z+4*0, log, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*5, y+4*4, z+4*1, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*5, y+4*4, z+4*0, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*5, y+4*4, z-4*1, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*4, y+4*4, z+4*2, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*4, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*4, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*4, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*4, y+4*4, z-4*2, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*3, y+4*4, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*3, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*3, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*3, y+4*4, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x+4*2, y+4*4, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x+4*2, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x+4*1, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x+4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x+4*1, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*4, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*4, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*4, y+4*5, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*3, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*3, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, false);
		
		buildLeaves(world, x+4*2, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x+4*1, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*3, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*3, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*4, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*4, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*5, y+4*5, z+4*2, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*5, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*5, y+4*5, z+4*0, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*5, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*5, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*0, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x-4*1, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-4*2, y+4*6, z+4*2, leaves, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*3, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*4, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildDarkOak(Level world, int x, int y, int z, Block log, Block leaves, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, log, fullLog, air, false, true, false, true, true, false);
		buildLog(world, x+4*0, y+4*1, z+4*0, log, fullLog, air, false, false, false, true, true, false);
		buildLog(world, x+4*0, y+4*2, z+4*0, log, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x+4*0, y+4*3, z+4*0, log, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x+4*0, y+4*4, z+4*0, log, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x+4*0, y+4*5, z+4*0, log, fullLog, air, true, false, false, true, true, false);

		buildLog(world, x+4*0, y+4*0, z-4*1, log, fullLog, air, false, true, true, false, true, false);
		buildLog(world, x+4*0, y+4*1, z-4*1, log, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*2, z-4*1, log, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*3, z-4*1, log, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*4, z-4*1, log, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*5, z-4*1, log, fullLog, air, true, false, true, false, true, false);

		buildLog(world, x-4*1, y+4*0, z+4*0, log, fullLog, air, false, true, false, true, false, true);
		buildLog(world, x-4*1, y+4*1, z+4*0, log, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*2, z+4*0, log, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*3, z+4*0, log, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*4, z+4*0, log, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*5, z+4*0, log, fullLog, air, true, false, false, true, false, true);

		buildLog(world, x-4*1, y+4*0, z-4*1, log, fullLog, air, false, true, true, false, false, true);
		buildLog(world, x-4*1, y+4*1, z-4*1, log, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*2, z-4*1, log, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*3, z-4*1, log, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*4, z-4*1, log, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*5, z-4*1, log, fullLog, air, true, false, true, false, false, true);

		buildLog(world, x+4*0, y+4*2, z+4*1, log, fullLog, air, false, true, false, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*1, log, fullLog, air, false, false, false, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*1, log, fullLog, air, true, false, false, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*4, z+4*2, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*2, leaves, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*3, leaves, fullLeaves, air, false, true, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*4, z+4*3, leaves, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*4, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z-4*3, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*3, leaves, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*4, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*3, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*3, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*4, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*4, z-4*3, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*4, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x-4*2, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z-4*3, leaves, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*3, y+4*4, z+4*2, leaves, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*4, z+4*1, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z+4*0, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z-4*1, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z-4*2, leaves, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z-4*3, leaves, fullLeaves, air, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*3, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*3, y+4*5, z+4*0, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*2, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*2, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*3, leaves, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x+4*1, y+4*5, z+4*3, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*2, leaves, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*3, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*4, leaves, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*3, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*2, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*3, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*4, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*3, leaves, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*2, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*3, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*4, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*2, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*2, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*3, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*4, leaves, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*3, y+4*5, z+4*3, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*5, z+4*2, leaves, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*5, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*2, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*3, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*4, leaves, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*4, y+4*5, z+4*1, leaves, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*4, y+4*5, z+4*0, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*4, y+4*5, z-4*1, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*4, y+4*5, z-4*2, leaves, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*4, y+4*5, z-4*3, leaves, fullLeaves, air, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*2, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*6, z+4*2, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*3, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*6, z+4*2, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*0, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*1, leaves, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*3, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*6, z+4*1, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*2, leaves, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*3, y+4*6, z+4*0, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*6, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*0, y+4*7, z+4*0, leaves, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*7, z-4*1, leaves, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*7, z+4*0, leaves, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*7, z-4*1, leaves, fullLeaves, air, true, false, true, false, false, true);
	}

	private void buildLog(Level world, int x, int y, int z, Block block, boolean fullLog, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		buildBlock(world,x,y,z,block,fullLog,air,up,down,north,south,east,west);
	}

	private void buildLeaves(Level world, int x, int y, int z, Block block, boolean fullLeaves, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		buildBlock(world,x,y,z,block,fullLeaves,air,up,down,north,south,east,west);
	}

	private void buildBlock(Level world, int x, int y, int z, Block block, boolean full, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		if(full) {
			BuildHelper.build(world, x-1, y, z-1, block, 4, 4, 4);
		} else {
			if(air) {
				BuildHelper.build(world, x-1, y, z-1, Blocks.AIR, 4, 4, 4);
			}
			if(up) {
				BuildHelper.build(world, x-1, y+3, z-1, block, 4, 1, 4);
			}
			if(down) {
				BuildHelper.build(world, x-1, y, z-1, block, 4, 1, 4);
			}
			if(north) {
				BuildHelper.build(world, x-1, y, z-1, block, 1, 4, 4);
			}
			if(south) {
				BuildHelper.build(world, x-1, y, z+2, block, 1, 4, 4);
			}
			if(east) {
				BuildHelper.build(world, x+2, y, z-1, block, 4, 4, 1);
			}
			if(west) {
				BuildHelper.build(world, x-1, y, z-1, block, 4, 4, 1);
			}
		}
	}
}