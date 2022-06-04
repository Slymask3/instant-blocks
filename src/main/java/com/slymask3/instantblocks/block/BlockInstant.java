package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.gui.instant.GuiSkydive;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public abstract class BlockInstant extends Block {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public String createMessage, errorMessage;
	boolean is_directional = false;
	GuiID guiID = null;
	
	protected BlockInstant(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.EAST));
		createMessage = errorMessage = "";
	}

	public void setCreateMessage(String msg) {
		this.createMessage = msg;
	}

	public void setErrorMessage(String msg) {
		this.errorMessage = msg;
	}

	public void setDirectional(boolean directional) {
		this.is_directional = directional;
	}

	public void setGuiID(GuiID guiID) {
		this.guiID = guiID;
	}

	public void onPlace(BlockState p_60566_, Level p_60567_, BlockPos p_60568_, BlockState p_60569_, boolean p_60570_) {
		InstantBlocks.LOGGER.info("placed: " + p_60566_.getValue(FACING));
	}

	// adds all the block state properties you want to use
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	// gets the correct block state for the player to place
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		return guiID == null ? onActivate(state,world,pos,player,hand,result) : onActivateGui(state,world,pos,player,hand,result);
	}

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		return true;
	}

	public InteractionResult onActivate(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(hand == InteractionHand.OFF_HAND) {
			return InteractionResult.FAIL;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		InstantBlocks.LOGGER.info("onActivate() client: " + world.isClientSide());

		ItemStack is = player.getItemInHand(hand);
		if(Config.Common.USE_WANDS.get() && !IBHelper.isWand(is)) {
			IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
			return InteractionResult.FAIL;
		}

		if(!canActivate(world,x,y,z,player)) {
			return InteractionResult.FAIL;
		}

		build(world, x, y, z, player);
		afterBuild(world, x, y, z, player);

		return InteractionResult.SUCCESS;
	}

	public InteractionResult onActivateGui(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(hand == InteractionHand.OFF_HAND) {
			return InteractionResult.FAIL;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if(!canActivate(world,x,y,z,player)) {
			return InteractionResult.FAIL;
		}

		ItemStack is = player.getItemInHand(InteractionHand.MAIN_HAND);
		if(Config.Common.USE_WANDS.get()) {
			if(!IBHelper.isWand(is)) {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return InteractionResult.FAIL;
			}
		}

		if(IBHelper.isClient(world)) {
			Minecraft.getInstance().setScreen(new GuiSkydive(player,world,pos.getX(),pos.getY(),pos.getZ()));
		}

		return InteractionResult.SUCCESS;
	}

	public void build(Level world, int x, int y, int z, Player player) {
		//build structure
	}

	public void afterBuild(Level world, int x, int y, int z, Player player) {
		IBHelper.keepBlocks(world, x, y, z, this);
		IBHelper.xp(world, player, Config.Common.XP_AMOUNT.get());
		IBHelper.sound(world, x, y, z);
		IBHelper.effectFull(world, x, y, z);
		IBHelper.msg(player, this.createMessage, Colors.a);

		if(Config.Common.USE_WANDS.get()) {
			ItemStack is = player.getItemInHand(InteractionHand.MAIN_HAND);
			if(IBHelper.isWand(is)) {
				is.hurtAndBreak(1, player, (entity) -> {
					entity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
				});
			}
		}
	}
}