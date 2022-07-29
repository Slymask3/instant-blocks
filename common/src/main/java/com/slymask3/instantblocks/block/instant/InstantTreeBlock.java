package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.TreeBlockEntity;
import com.slymask3.instantblocks.network.packet.TreeUpdatePacket;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
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

public class InstantTreeBlock extends InstantBlock implements EntityBlock {
	public InstantTreeBlock() {
		super(Properties.of(Material.PLANT)
				.sound(SoundType.GRASS)
				.noCollission()
				.instabreak()
		);
		setScreen(ClientHelper.Screen.TREE);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_TREE();
	}

	public void openScreen(Player player, BlockPos pos) {
		Common.NETWORK.sendToClient(player, new TreeUpdatePacket(Common.CONFIG.HUGE_TREES(),pos));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TreeBlockEntity(pos,state);
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
	}
	
	public boolean build(Level world, int x_center, int y, int z_center, Player player) {
		TreeBlockEntity blockEntity = (TreeBlockEntity)world.getBlockEntity(new BlockPos(x_center,y,z_center));
		int size = Common.CONFIG.TREE_SIZE();
		int half = (int)Math.floor(size / 2);
		int x = x_center - half;
		int z = z_center - half;
		switch(blockEntity.tree.type) {
			case OAK -> buildOak(world, x, y, z, size, blockEntity);
			case SPRUCE -> buildSpruce(world, x, y, z, size, blockEntity);
			case BIRCH -> buildBirch(world, x, y, z, size, blockEntity);
			case JUNGLE -> buildJungle(world, x, y, z, size, blockEntity);
			case ACACIA -> buildAcacia(world, x, y, z, size, blockEntity);
			case DARK_OAK -> buildDarkOak(world, x, y, z, size, blockEntity);
			default -> {
				Helper.sendMessage(player, Strings.ERROR_TREE);
				return false;
			}
		}
		setCreateMessage(Strings.CREATE_TREE, blockEntity.tree.name);
		return true;
	}

	private void buildOak(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity) {
		buildLog(world, x, y, z, size, blockEntity, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, size, blockEntity, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*2, z+size, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*2, z, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*2, z-size, size, blockEntity, false, true, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*2, z+size*2, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*2, size, blockEntity, false, true, true, false, true, false);
		
		buildLeaves(world, x, y+size*2, z+size*2, size, blockEntity, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*2, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*2, z+size*2, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*2, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*2, z+size, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*2, z, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size*2, size, blockEntity, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*3, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*3, z, size, blockEntity, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*3, z-size, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*3, z+size*2, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*3, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size*2, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x, y+size*3, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*3, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size*2, size, blockEntity, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*3, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*3, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size*2, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*3, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*3, z+size, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size, y+size*4, z, size, blockEntity, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*4, z+size, size, blockEntity, false, false, false, true, true, false);
		buildLeaves(world, x, y+size*4, z-size, size, blockEntity, false, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*4, z+size, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*4, z, size, blockEntity, false, false, false, false, false, true);
		buildLeaves(world, x-size, y+size*4, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size, y+size*5, z, size, blockEntity, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*5, z+size, size, blockEntity, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*5, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, size, blockEntity, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*5, z, size, blockEntity, true, false, true, true, false, true);
	}

	private void buildSpruce(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity) {
		buildLog(world, x, y, z, size, blockEntity, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*5, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*6, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*7, z, size, blockEntity, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*3, y+size*2, z+size*2, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*3, y+size*2, z+size, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*2, z, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*2, z-size, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*2, z-size*2, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x+size*2, y+size*2, z+size*3, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*2, z+size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z-size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*2, z-size*3, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x+size, y+size*2, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x+size, y+size*2, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*3, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x, y+size*2, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x, y+size*2, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*3, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*2, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x-size, y+size*2, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*3, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*2, z+size*3, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*2, z+size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z-size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*2, z-size*3, size, blockEntity, true, true, true, false, false, true);

		buildLeaves(world, x-size*3, y+size*2, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*2, z+size, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*2, z, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*2, z-size, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*2, z-size*2, size, blockEntity, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*3, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*3, z, size, blockEntity, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*3, z-size, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x+size, y+size*3, z+size*2, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*3, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size*2, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x, y+size*3, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*3, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size*2, size, blockEntity, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*3, z+size*2, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*3, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size*2, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*3, z+size, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*3, z, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z-size, size, blockEntity, true, false, true, false, false, true);

		/** third layer **/
		buildLeaves(world, x+size, y+size*4, z, size, blockEntity, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*4, z+size, size, blockEntity, false, false, false, true, true, true);
		buildLeaves(world, x, y+size*4, z-size, size, blockEntity, false, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*4, z, size, blockEntity, false, false, true, true, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size*2, y+size*5, z+size, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*5, z, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x+size, y+size*5, z+size*2, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x, y+size*5, z+size*2, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x, y+size*5, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*5, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*5, z+size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, size, blockEntity, true, true, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*5, z+size, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*5, z, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*5, z-size, size, blockEntity, true, true, true, false, false, true);

		/** fifth layer **/
		buildLeaves(world, x+size, y+size*6, z, size, blockEntity, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*6, z+size, size, blockEntity, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*6, z-size, size, blockEntity, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*6, z, size, blockEntity, true, false, true, true, false, true);

		/** seventh layer **/
		buildLeaves(world, x+size, y+size*8, z, size, blockEntity, true, true, true, true, true, false);

		buildLeaves(world, x, y+size*8, z+size, size, blockEntity, true, true, false, true, true, true);
		buildLeaves(world, x, y+size*8, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*8, z-size, size, blockEntity, true, true, true, false, true, true);

		buildLeaves(world, x-size, y+size*8, z, size, blockEntity, true, true, true, true, false, true);

		/** eighth layer **/
		buildLeaves(world, x, y+size*9, z, size, blockEntity, true, false, true, true, true, true);
	}

	private void buildBirch(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity) {
		buildLog(world, x, y, z, size, blockEntity, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, size, blockEntity, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*2, z+size, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*2, z, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*2, z-size, size, blockEntity, false, true, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*2, z+size*2, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*2, z-size*2, size, blockEntity, false, true, true, false, true, false);
		
		buildLeaves(world, x, y+size*2, z+size*2, size, blockEntity, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*2, z-size*2, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*2, z+size*2, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*2, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*2, z-size*2, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*2, z+size, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*2, z, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*2, z-size*2, size, blockEntity, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*3, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*3, z, size, blockEntity, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*3, z-size, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*3, z+size*2, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*3, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*3, z-size*2, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x, y+size*3, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*3, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*3, z-size*2, size, blockEntity, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*3, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*3, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*3, z-size*2, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*3, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*3, z+size, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*3, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size, y+size*4, z, size, blockEntity, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*4, z+size, size, blockEntity, false, false, false, true, true, false);
		buildLeaves(world, x, y+size*4, z-size, size, blockEntity, false, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*4, z+size, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*4, z, size, blockEntity, false, false, false, false, false, true);
		buildLeaves(world, x-size, y+size*4, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size, y+size*5, z, size, blockEntity, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*5, z+size, size, blockEntity, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*5, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, size, blockEntity, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*5, z, size, blockEntity, true, false, true, true, false, true);
	}

	private void buildJungle(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity) {
		buildLog(world, x, y, z, size, blockEntity, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*3, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*4, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*5, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*6, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*7, z, size, blockEntity, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*5, z+size, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*5, z, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, size, blockEntity, false, true, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*5, z+size*2, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, size, blockEntity, false, true, true, false, true, false);
		
		buildLeaves(world, x, y+size*5, z+size*2, size, blockEntity, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*5, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*5, z+size*2, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*5, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*5, z+size, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*5, z, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*5, z-size, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*5, z-size*2, size, blockEntity, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*2, y+size*6, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*6, z, size, blockEntity, true, false, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*6, z-size, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x+size, y+size*6, z+size*2, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*6, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size*2, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x, y+size*6, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*6, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size*2, size, blockEntity, true, false, true, false, false, false);

		buildLeaves(world, x-size, y+size*6, z+size*2, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*6, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size*2, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*6, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*6, z+size, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*6, z, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*2, y+size*6, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size, y+size*7, z, size, blockEntity, false, false, true, true, true, false);

		buildLeaves(world, x, y+size*7, z+size, size, blockEntity, false, false, false, true, true, false);
		buildLeaves(world, x, y+size*7, z-size, size, blockEntity, false, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*7, z+size, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*7, z, size, blockEntity, false, false, false, false, false, true);
		buildLeaves(world, x-size, y+size*7, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+size, y+size*8, z, size, blockEntity, true, false, true, true, true, false);

		buildLeaves(world, x, y+size*8, z+size, size, blockEntity, true, false, false, true, true, true);
		buildLeaves(world, x, y+size*8, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*8, z-size, size, blockEntity, true, false, true, false, true, true);

		buildLeaves(world, x-size, y+size*8, z, size, blockEntity, true, false, true, true, false, true);
	}

	private void buildAcacia(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity) {
		buildLog(world, x, y, z, size, blockEntity, false, true, true, true, true, true);
		buildLog(world, x, y+size, z, size, blockEntity, false, false, true, true, true, true);
		buildLog(world, x, y+size*2, z, size, blockEntity, true, false, true, true, false, true);

		buildLog(world, x+size, y+size*2, z, size, blockEntity, true, true, true, true, true, false);

		buildLog(world, x+size*2, y+size*3, z, size, blockEntity, true, true, true, true, true, true);
		buildLog(world, x-size, y+size*3, z, size, blockEntity, true, true, true, true, true, true);

		buildLog(world, x+size*3, y+size*4, z, size, blockEntity, true, true, true, true, true, true);
		buildLog(world, x-size*2, y+size*4, z, size, blockEntity, false, true, true, true, true, true);

		buildLog(world, x-size*2, y+size*5, z, size, blockEntity, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*5, y+size*4, z+size, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*5, y+size*4, z, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*5, y+size*4, z-size, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x+size*4, y+size*4, z+size*2, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*4, y+size*4, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*4, y+size*4, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*4, y+size*4, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*4, y+size*4, z-size*2, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x+size*3, y+size*4, z+size*2, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x+size*3, y+size*4, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*3, y+size*4, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*3, y+size*4, z-size*2, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x+size*2, y+size*4, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x+size*2, y+size*4, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*4, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*4, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*4, z-size*2, size, blockEntity, true, true, true, false, false, true);

		buildLeaves(world, x+size, y+size*4, z+size, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x+size, y+size*4, z, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x+size, y+size*4, z-size, size, blockEntity, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*4, y+size*5, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size*4, y+size*5, z, size, blockEntity, true, false, false, false, true, false);
		buildLeaves(world, x+size*4, y+size*5, z-size, size, blockEntity, true, false, true, false, true, false);
		
		buildLeaves(world, x+size*3, y+size*5, z+size, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x+size*3, y+size*5, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size*3, y+size*5, z-size, size, blockEntity, true, false, true, false, false, false);
		
		buildLeaves(world, x+size*2, y+size*5, z+size, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x+size*2, y+size*5, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, size, blockEntity, true, false, true, false, false, false);

		buildLeaves(world, x+size, y+size*5, z+size*2, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x, y+size*5, z+size*3, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x, y+size*5, z+size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z+size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*3, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x-size, y+size*5, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x-size, y+size*5, z+size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*3, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*5, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*3, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size*3, y+size*5, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x-size*3, y+size*5, z+size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*3, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size*4, y+size*5, z+size*3, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*4, y+size*5, z+size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z+size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z-size, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z-size*2, size, blockEntity, true, true, false, false, false, false);
		buildLeaves(world, x-size*4, y+size*5, z-size*3, size, blockEntity, true, true, true, false, false, true);

		buildLeaves(world, x-size*5, y+size*5, z+size*2, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*5, y+size*5, z+size, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*5, y+size*5, z, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*5, y+size*5, z-size, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*5, y+size*5, z-size*2, size, blockEntity, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x, y+size*6, z, size, blockEntity, true, false, true, true, true, false);

		buildLeaves(world, x-size, y+size*6, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x-size, y+size*6, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x-size*2, y+size*6, z+size*2, size, blockEntity, true, false, false, true, true, true);
		buildLeaves(world, x-size*2, y+size*6, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size*2, size, blockEntity, true, false, true, false, true, true);

		buildLeaves(world, x-size*3, y+size*6, z+size, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*6, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*6, z-size, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*4, y+size*6, z, size, blockEntity, true, false, true, true, false, true);
	}

	private void buildDarkOak(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity) {
		buildLog(world, x, y, z, size, blockEntity, false, true, false, true, true, false);
		buildLog(world, x, y+size, z, size, blockEntity, false, false, false, true, true, false);
		buildLog(world, x, y+size*2, z, size, blockEntity, false, false, false, false, true, false);
		buildLog(world, x, y+size*3, z, size, blockEntity, false, false, false, false, true, false);
		buildLog(world, x, y+size*4, z, size, blockEntity, false, false, false, false, true, false);
		buildLog(world, x, y+size*5, z, size, blockEntity, true, false, false, true, true, false);

		buildLog(world, x, y, z-size, size, blockEntity, false, true, true, false, true, false);
		buildLog(world, x, y+size, z-size, size, blockEntity, false, false, true, false, true, false);
		buildLog(world, x, y+size*2, z-size, size, blockEntity, false, false, true, false, true, false);
		buildLog(world, x, y+size*3, z-size, size, blockEntity, false, false, true, false, true, false);
		buildLog(world, x, y+size*4, z-size, size, blockEntity, false, false, true, false, true, false);
		buildLog(world, x, y+size*5, z-size, size, blockEntity, true, false, true, false, true, false);

		buildLog(world, x-size, y, z, size, blockEntity, false, true, false, true, false, true);
		buildLog(world, x-size, y+size, z, size, blockEntity, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*2, z, size, blockEntity, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*3, z, size, blockEntity, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*4, z, size, blockEntity, false, false, false, true, false, true);
		buildLog(world, x-size, y+size*5, z, size, blockEntity, true, false, false, true, false, true);

		buildLog(world, x-size, y, z-size, size, blockEntity, false, true, true, false, false, true);
		buildLog(world, x-size, y+size, z-size, size, blockEntity, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*2, z-size, size, blockEntity, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*3, z-size, size, blockEntity, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*4, z-size, size, blockEntity, false, false, true, false, false, true);
		buildLog(world, x-size, y+size*5, z-size, size, blockEntity, true, false, true, false, false, true);

		buildLog(world, x, y+size*2, z+size, size, blockEntity, false, true, false, true, true, true);
		buildLog(world, x, y+size*3, z+size, size, blockEntity, false, false, false, true, true, true);
		buildLog(world, x, y+size*4, z+size, size, blockEntity, true, false, false, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+size*2, y+size*4, z+size*2, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*4, z+size, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z-size, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z-size*2, size, blockEntity, false, true, false, false, true, false);
		buildLeaves(world, x+size*2, y+size*4, z-size*3, size, blockEntity, false, true, true, false, true, false);

		buildLeaves(world, x+size, y+size*4, z+size*3, size, blockEntity, false, true, false, true, true, false);
		buildLeaves(world, x+size, y+size*4, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x+size, y+size*4, z-size*3, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x, y+size*4, z+size*3, size, blockEntity, false, true, false, true, false, false);
		buildLeaves(world, x, y+size*4, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*4, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x, y+size*4, z-size*3, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*4, z+size*3, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size, y+size*4, z+size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*4, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*4, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size, y+size*4, z-size*3, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*4, z+size*2, size, blockEntity, false, true, false, true, false, false);
		buildLeaves(world, x-size*2, y+size*4, z+size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z-size, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z-size*2, size, blockEntity, false, true, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*4, z-size*3, size, blockEntity, false, true, true, false, false, false);

		buildLeaves(world, x-size*3, y+size*4, z+size*2, size, blockEntity, false, true, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*4, z+size, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z-size, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z-size*2, size, blockEntity, false, true, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*4, z-size*3, size, blockEntity, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+size*3, y+size*5, z+size, size, blockEntity, true, true, false, true, true, false);
		buildLeaves(world, x+size*3, y+size*5, z, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*5, z-size, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*5, z-size*2, size, blockEntity, true, true, false, false, true, false);
		buildLeaves(world, x+size*3, y+size*5, z-size*3, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x+size*2, y+size*5, z+size, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x+size*2, y+size*5, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size*2, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size*2, y+size*5, z-size*3, size, blockEntity, true, false, true, false, false, false);

		buildLeaves(world, x+size, y+size*5, z+size*3, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*5, z+size*2, size, blockEntity, true, false, false, false, true, false);
		buildLeaves(world, x+size, y+size*5, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*2, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*3, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*5, z-size*4, size, blockEntity, true, true, true, false, true, false);

		buildLeaves(world, x, y+size*5, z+size*3, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x, y+size*5, z+size*2, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*2, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*3, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*5, z-size*4, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size, y+size*5, z+size*3, size, blockEntity, true, false, false, true, false, false);
		buildLeaves(world, x-size, y+size*5, z+size*2, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*2, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*3, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*5, z-size*4, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size*2, y+size*5, z+size*3, size, blockEntity, true, true, false, true, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size*2, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z+size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*2, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*3, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size*4, size, blockEntity, true, true, true, false, false, false);

		buildLeaves(world, x-size*3, y+size*5, z+size*3, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*5, z+size*2, size, blockEntity, true, false, false, false, false, true);
		buildLeaves(world, x-size*3, y+size*5, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*5, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*2, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*3, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*3, y+size*5, z-size*4, size, blockEntity, true, true, true, false, false, true);

		buildLeaves(world, x-size*4, y+size*5, z+size, size, blockEntity, true, true, false, true, false, true);
		buildLeaves(world, x-size*4, y+size*5, z, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*4, y+size*5, z-size, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*4, y+size*5, z-size*2, size, blockEntity, true, true, false, false, false, true);
		buildLeaves(world, x-size*4, y+size*5, z-size*3, size, blockEntity, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+size*2, y+size*6, z, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size*2, y+size*6, z-size, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x+size, y+size*6, z+size, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x+size, y+size*6, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x+size, y+size*6, z-size*2, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x, y+size*6, z+size*2, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x, y+size*6, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size*2, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x, y+size*6, z-size*3, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*6, z+size*2, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*6, z+size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size, size, blockEntity, false, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size*2, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size, y+size*6, z-size*3, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*2, y+size*6, z+size, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size*2, y+size*6, z, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size, size, blockEntity, true, false, false, false, false, false);
		buildLeaves(world, x-size*2, y+size*6, z-size*2, size, blockEntity, true, false, true, false, false, true);

		buildLeaves(world, x-size*3, y+size*6, z, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size*3, y+size*6, z-size, size, blockEntity, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x, y+size*7, z, size, blockEntity, true, false, false, true, true, false);
		buildLeaves(world, x, y+size*7, z-size, size, blockEntity, true, false, true, false, true, false);

		buildLeaves(world, x-size, y+size*7, z, size, blockEntity, true, false, false, true, false, true);
		buildLeaves(world, x-size, y+size*7, z-size, size, blockEntity, true, false, true, false, false, true);
	}

	private void buildLog(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		buildBlock(world,x,y,z,blockEntity.tree.getLogs(),size,blockEntity.hollowLogs,blockEntity.airInside,up,down,north,south,east,west);
	}

	private void buildLeaves(Level world, int x, int y, int z, int size, TreeBlockEntity blockEntity, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		buildBlock(world,x,y,z,blockEntity.tree.getLeaves(),size,blockEntity.hollowLeaves,blockEntity.airInside,up,down,north,south,east,west);
	}

	private void buildBlock(Level world, int x, int y, int z, Block block, int size, boolean hollow, boolean airInside, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		if(!hollow) {
			Builder.Multiple.setup(world,x,y,z,size,size,size).setBlock(block).build();
		} else {
			if(airInside) {
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
}