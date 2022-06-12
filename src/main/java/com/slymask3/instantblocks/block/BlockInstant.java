package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.gui.instant.*;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.Helper;
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
		if(directional) {
			this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.EAST));
		}
	}

	public void setGuiID(GuiID guiID) {
		this.guiID = guiID;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.is_directional ? this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()) : super.getStateForPlacement(context);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		return guiID == null ? onActivate(state,world,pos,player,hand,result) : onActivateGui(state,world,pos,player,hand,result);
	}

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		return true;
	}

	public InteractionResult onActivate(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if(Helper.isServer(world)) {
			if(hand == InteractionHand.OFF_HAND) {
				return InteractionResult.FAIL;
			}

			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			ItemStack is = player.getItemInHand(hand);
			if(Config.Common.USE_WANDS.get() && !Helper.isWand(is)) {
				Helper.sendMessage(player, Strings.ERROR_WAND, Colors.c);
				return InteractionResult.FAIL;
			}

			if(!canActivate(world,x,y,z,player)) {
				return InteractionResult.FAIL;
			}

			if(build(world, x, y, z, player)) {
				afterBuild(world, x, y, z, player);
			} else {
				return InteractionResult.FAIL;
			}
		}
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
			if(!Helper.isWand(is)) {
				Helper.sendMessage(player, Strings.ERROR_WAND, Colors.c);
				return InteractionResult.FAIL;
			}
		}

		if(ClientHelper.isClient(world)) {
			switch(guiID) {
				case SKYDIVE -> Minecraft.getInstance().setScreen(new GuiSkydive(player,world,pos.getX(),pos.getY(),pos.getZ()));
				case STATUE -> Minecraft.getInstance().setScreen(new GuiStatue(player,world,pos.getX(),pos.getY(),pos.getZ()));
				case HARVEST -> Minecraft.getInstance().setScreen(new GuiHarvest(player,world,pos.getX(),pos.getY(),pos.getZ()));
				case TREE -> Minecraft.getInstance().setScreen(new GuiTree(player,world,pos.getX(),pos.getY(),pos.getZ()));
				case SCHEMATIC -> Minecraft.getInstance().setScreen(new GuiSchematic(player,world,pos.getX(),pos.getY(),pos.getZ()));
			}
		}

		return InteractionResult.SUCCESS;
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		//build structure
		return true;
	}

	public void afterBuild(Level world, int x, int y, int z, Player player) {
		Helper.sendMessage(player,this.createMessage,Colors.a,x,y,z);
		Helper.xp(world, player, Config.Common.XP_AMOUNT.get());
		if(Config.Common.USE_WANDS.get()) {
			ItemStack is = player.getItemInHand(InteractionHand.MAIN_HAND);
			if(Helper.isWand(is)) {
				is.hurtAndBreak(Helper.wandDamage(is), player, (entity) -> {
					entity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
				});
			}
		}
	}
}