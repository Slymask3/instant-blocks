package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.TreeBlockEntity;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.server.command.TextComponentHelper;
import org.jetbrains.annotations.Nullable;

public class InstantTreeBlock extends InstantBlock implements EntityBlock {
	public InstantTreeBlock() {
		super(Block.Properties.of(Material.PLANT)
				.strength(0.1F)
				.sound(SoundType.GRASS)
				.noCollission()
				.instabreak()
		, Config.Common.DISABLE_TREE);
		setScreen(ClientHelper.Screen.TREE);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TreeBlockEntity(pos,state);
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	}
	
	public boolean build(Level world, int x_center, int y, int z_center, Player player, int type, boolean fullLog, boolean fullLeaves, boolean air) {
		int size = Config.Common.TREE_SIZE.get();
		int half = (int)Math.floor(size / 2);
		int x = x_center - half;
		int z = z_center - half;
		switch(type) {
			case 0 -> buildOak(world, x, y, z, Blocks.OAK_LOG, Blocks.OAK_LEAVES, size, fullLog, fullLeaves, air);
			case 1 -> buildSpruce(world, x, y, z, Blocks.SPRUCE_LOG, Blocks.SPRUCE_LEAVES, size, fullLog, fullLeaves, air);
			case 2 -> buildBirch(world, x, y, z, Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, size, fullLog, fullLeaves, air);
			case 3 -> buildJungle(world, x, y, z, Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, size, fullLog, fullLeaves, air);
			case 4 -> buildAcacia(world, x, y, z, Blocks.ACACIA_LOG, Blocks.ACACIA_LEAVES, size, fullLog, fullLeaves, air);
			case 5 -> buildDarkOak(world, x, y, z, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, size, fullLog, fullLeaves, air);
			case 6 -> buildOak(world, x, y, z, Blocks.BROWN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS, size, fullLog, fullLeaves, air);
		}
		setCreateMessage(Strings.CREATE_TREE, treeToString(type,player));
		return true;
	}

	private void buildOak(Level world, int x, int y, int z, Block log, Block leaves, int size, boolean fullLog, boolean fullLeaves, boolean air) {
		buildLog(world, x, y, z, log, size, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, log, size, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*3, z, leaves, size, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*3, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*3, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size, y+size*4, z, leaves, size, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*4, z+size, leaves, size, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x, y+size*4, z-size, leaves, size, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*4, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*4, z, leaves, size, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-size, y+size*4, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size, y+size*5, z, leaves, size, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*5, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*5, z, leaves, size, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildSpruce(Level world, int x, int y, int z, Block log, Block leaves, int size, boolean fullLog, boolean fullLeaves, boolean air) {
		buildLog(world, x, y, z, log, size, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*5, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*6, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*7, z, log, size, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*3, y+size*2, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*3, y+size*2, z+size, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*2, z, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*2, z-size, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*2, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+size*2, y+size*2, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*2, z+size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+size, y+size*2, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+size, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x, y+size*2, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*2, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-size, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*2, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*2, z+size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-size*3, y+size*2, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*2, z+size, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*2, z, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*2, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*2, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*3, z, leaves, size, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+size, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*3, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*3, z, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		/** third layer **/
		buildLeaves(world, x+size, y+size*4, z, leaves, size, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*4, z+size, leaves, size, fullLeaves, air, false, false, false, true, true, true);
		buildLeaves(world, x, y+size*4, z-size, leaves, size, fullLeaves, air, false, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*4, z, leaves, size, fullLeaves, air, false, false, true, true, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size*2, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*5, z, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+size, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x, y+size*5, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*5, z, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, true, false, false, true);

		/** fifth layer **/
		buildLeaves(world, x+size, y+size*6, z, leaves, size, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*6, z, leaves, size, fullLeaves, air, true, false, true, true, false, true);

		/** seventh layer **/
		buildLeaves(world, x+size, y+size*8, z, leaves, size, fullLeaves, air, true, true, true, true, true, false);

		buildLeaves(world, x, y+size*8, z+size, leaves, size, fullLeaves, air, true, true, false, true, true, true);
		buildLeaves(world, x, y+size*8, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*8, z-size, leaves, size, fullLeaves, air, true, true, true, false, true, true);

		buildLeaves(world, x-size, y+size*8, z, leaves, size, fullLeaves, air, true, true, true, true, false, true);

		/** eighth layer **/
		buildLeaves(world, x, y+size*9, z, leaves, size, fullLeaves, air, true, false, true, true, true, true);
	}

	private void buildBirch(Level world, int x, int y, int z, Block log, Block leaves, int size, boolean fullLog, boolean fullLeaves, boolean air) {
		buildLog(world, x, y, z, log, size, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, log, size, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*2, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*2, z+size, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*2, z, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*3, z, leaves, size, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*3, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*3, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*3, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*3, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size, y+size*4, z, leaves, size, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*4, z+size, leaves, size, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x, y+size*4, z-size, leaves, size, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*4, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*4, z, leaves, size, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-size, y+size*4, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size, y+size*5, z, leaves, size, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*5, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*5, z, leaves, size, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildJungle(Level world, int x, int y, int z, Block log, Block leaves, int size, boolean fullLog, boolean fullLeaves, boolean air) {
		buildLog(world, x, y, z, log, size, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*5, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*6, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*7, z, log, size, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*5, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x, y+size*5, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*5, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*6, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*6, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size, y+size*7, z, leaves, size, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*7, z+size, leaves, size, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x, y+size*7, z-size, leaves, size, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*7, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*7, z, leaves, size, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-size, y+size*7, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size, y+size*8, z, leaves, size, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*8, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*8, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*8, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*8, z, leaves, size, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildAcacia(Level world, int x, int y, int z, Block log, Block leaves, int size, boolean fullLog, boolean fullLeaves, boolean air) {
		buildLog(world, x, y, z, log, size, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, log, size, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, log, size, fullLog, air, true, false, true, true, false, true);

		buildLog(world, x+size, y+size*2, z, log, size, fullLog, air, true, true, true, true, true, false);

		buildLog(world, x+size*2, y+size*3, z, log, size, fullLog, air, true, true, true, true, true, true);
		buildLog(world, x-size, y+size*3, z, log, size, fullLog, air, true, true, true, true, true, true);

		buildLog(world, x+size*3, y+size*4, z, log, size, fullLog, air, true, true, true, true, true, true);
		buildLog(world, x-size*2, y+size*4, z, log, size, fullLog, air, false, true, true, true, true, true);

		buildLog(world, x-size*2, y+size*5, z, log, size, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*5, y+size*4, z+size, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*5, y+size*4, z, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*5, y+size*4, z-size, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+size*4, y+size*4, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*4, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*4, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*4, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*4, y+size*4, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+size*3, y+size*4, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+size*3, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*3, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*3, y+size*4, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x+size*2, y+size*4, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x+size*2, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*4, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x+size, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x+size, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x+size, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*4, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size*4, y+size*5, z, leaves, size, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+size*4, y+size*5, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+size*3, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+size*3, y+size*5, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size*3, y+size*5, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, false);
		
		buildLeaves(world, x+size*2, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+size*2, y+size*5, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x+size, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x-size, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-size, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size*3, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-size*3, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size*4, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*4, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-size*5, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*5, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*5, y+size*5, z, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*5, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*5, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x, y+size*6, z, leaves, size, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x-size, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x-size, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-size*2, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x-size*2, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-size*3, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*4, y+size*6, z, leaves, size, fullLeaves, air, true, false, true, true, false, true);
	}

	private void buildDarkOak(Level world, int x, int y, int z, Block log, Block leaves, int size, boolean fullLog, boolean fullLeaves, boolean air) {
		buildLog(world, x, y, z, log, size, fullLog, air, false, true, false, true, true, false);
		buildLog(world, x, y+size, z, log, size, fullLog, air, false, false, false, true, true, false);
		buildLog(world, x, y+size*2, z, log, size, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x, y+size*3, z, log, size, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x, y+size*4, z, log, size, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x, y+size*5, z, log, size, fullLog, air, true, false, false, true, true, false);

		buildLog(world, x, y, z-size, log, size, fullLog, air, false, true, true, false, true, false);
		buildLog(world, x, y+size, z-size, log, size, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x, y+size*2, z-size, log, size, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x, y+size*3, z-size, log, size, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x, y+size*4, z-size, log, size, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x, y+size*5, z-size, log, size, fullLog, air, true, false, true, false, true, false);

		buildLog(world, x-size, y, z, log, size, fullLog, air, false, true, false, true, false, true);
		buildLog(world, x-size, y+size, z, log, size, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*2, z, log, size, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*3, z, log, size, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*4, z, log, size, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*5, z, log, size, fullLog, air, true, false, false, true, false, true);

		buildLog(world, x-size, y, z-size, log, size, fullLog, air, false, true, true, false, false, true);
		buildLog(world, x-size, y+size, z-size, log, size, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*2, z-size, log, size, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*3, z-size, log, size, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*4, z-size, log, size, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*5, z-size, log, size, fullLog, air, true, false, true, false, false, true);

		buildLog(world, x, y+size*2, z+size, log, size, fullLog, air, false, true, false, true, true, true);
		buildLog(world, x, y+size*3, z+size, log, size, fullLog, air, false, false, false, true, true, true);
		buildLog(world, x, y+size*4, z+size, log, size, fullLog, air, true, false, false, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*4, z+size*2, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z-size*3, leaves, size, fullLeaves, air, false, true, true, false, true, false);

		buildLeaves(world, x+size, y+size*4, z+size*3, leaves, size, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*4, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z-size*3, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x, y+size*4, z+size*3, leaves, size, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*4, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*4, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*4, z-size*3, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*4, z+size*3, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*4, z+size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*4, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*4, z-size*3, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*4, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x-size*2, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z-size*3, leaves, size, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-size*3, y+size*4, z+size*2, leaves, size, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*4, z+size, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z-size, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z-size*2, leaves, size, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z-size*3, leaves, size, fullLeaves, air, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*3, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+size*3, y+size*5, z, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+size*2, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+size*2, y+size*5, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x+size, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*4, leaves, size, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*5, z+size*2, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*3, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*4, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*5, z+size*2, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*3, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*4, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*2, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*4, leaves, size, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-size*3, y+size*5, z+size*3, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*5, z+size*2, leaves, size, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*5, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*4, leaves, size, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-size*4, y+size*5, z+size, leaves, size, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-size*4, y+size*5, z, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*4, y+size*5, z-size, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*4, y+size*5, z-size*2, leaves, size, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-size*4, y+size*5, z-size*3, leaves, size, fullLeaves, air, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size*2, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+size, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size*3, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*6, z+size*2, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size, leaves, size, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size*3, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*6, z+size, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size*2, leaves, size, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-size*3, y+size*6, z, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*6, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x, y+size*7, z, leaves, size, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x, y+size*7, z-size, leaves, size, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*7, z, leaves, size, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*7, z-size, leaves, size, fullLeaves, air, true, false, true, false, false, true);
	}

	private void buildLog(Level world, int x, int y, int z, Block block, int size, boolean fullLog, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		buildBlock(world,x,y,z,block,size,fullLog,air,up,down,north,south,east,west);
	}

	private void buildLeaves(Level world, int x, int y, int z, Block block, int size, boolean fullLeaves, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		buildBlock(world,x,y,z,block,size,fullLeaves,air,up,down,north,south,east,west);
	}

	private void buildBlock(Level world, int x, int y, int z, Block block, int size, boolean full, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		if(full) {
			Builder.Multiple.setup(world,x,y,z,size,size,size).setBlock(block).build();
		} else {
			if(air) {
				Builder.Multiple.setup(world,x,y,z,size,size,size).setBlock(Blocks.AIR).build();
			}
			if(up) {
				Builder.Multiple.setup(world,x,y+(size-1),z,size,1,size).setBlock(block).build();
			}
			if(down) {
				Builder.Multiple.setup(world,x,y,z,size,1,size).setBlock(block).build();
			}
			if(north) {
				Builder.Multiple.setup(world,x,y,z,size,size,1).setBlock(block).build();
			}
			if(south) {
				Builder.Multiple.setup(world,x,y,z+(size-1),size,size,1).setBlock(block).build();
			}
			if(east) {
				Builder.Multiple.setup(world,x+(size-1),y,z,1,size,size).setBlock(block).build();
			}
			if(west) {
				Builder.Multiple.setup(world,x,y,z,1,size,size).setBlock(block).build();
			}
		}
	}

	public static String treeToString(int tree, Player player) {
		return switch (tree) {
			case 0 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.oak").getString();
			case 1 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.spruce").getString();
			case 2 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.birch").getString();
			case 3 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.jungle").getString();
			case 4 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.acacia").getString();
			case 5 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.dark_oak").getString();
			case 6 -> TextComponentHelper.createComponentTranslation(player, "ib.gui.tree.glass").getString();
			default -> "Error";
		};
	}
}