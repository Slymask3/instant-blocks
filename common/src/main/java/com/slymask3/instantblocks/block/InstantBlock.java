package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class InstantBlock extends Block {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public String createMessage, errorMessage, createVariable, errorVariable;
	boolean isDirectional = false;
	ClientHelper.Screen screen = null;
	
	protected InstantBlock(Properties properties) {
		super(properties);
		this.createMessage = this.errorMessage = this.createVariable = this.errorVariable = "";
	}

	public void setCreateMessage(String message) {
		this.setCreateMessage(message,"");
	}

	public void setCreateMessage(String message, String variable) {
		this.createMessage = message;
		this.createVariable = variable.isEmpty() ? "" : ChatFormatting.GREEN + variable;
	}

	public void setErrorMessage(String message) {
		this.setErrorMessage(message,"");
	}

	public void setErrorMessage(String message, String variable) {
		this.errorMessage = message;
		this.errorVariable = variable.isEmpty() ? "" : ChatFormatting.RED + variable;
	}

	public void setDirectional(boolean directional) {
		this.isDirectional = directional;
		if(directional) {
			this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.EAST));
		}
	}

	public void setScreen(ClientHelper.Screen screen) {
		this.screen = screen;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.isDirectional ? this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()) : super.getStateForPlacement(context);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		Common.CONFIG.reload();
		return screen == null ? onActivate(world,pos,player,hand) : onActivateGui(world,pos,player,hand);
	}

	public boolean canActivate(Level world, BlockPos pos, Player player) {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	private boolean isDisabled(Player player) {
		if(!this.isEnabled()) {
			Helper.sendMessage(player,Strings.ERROR_DISABLED);
			return true;
		}
		return false;
	}

	private InteractionResult onActivate(Level world, BlockPos pos, Player player, InteractionHand hand) {
		if(Helper.isServer(world)) {
			if(hand == InteractionHand.OFF_HAND) {
				return InteractionResult.FAIL;
			}

			if(isDisabled(player)) {
				return InteractionResult.FAIL;
			}

			ItemStack is = player.getItemInHand(hand);
			if(Common.CONFIG.USE_WANDS()) {
				if(!Helper.isWand(is)) {
					Helper.sendMessage(player, Strings.ERROR_WAND);
					return InteractionResult.FAIL;
				} else if(!player.isCreative() && !Common.CONFIG.WAND_OVER_DURABILITY() && Helper.wandDamage(Helper.getBlock(world,pos)) > is.getMaxDamage() - is.getDamageValue()) {
					Helper.sendMessage(player, Strings.ERROR_WAND_DURABILITY);
					return InteractionResult.FAIL;
				}
			}

			if(!canActivate(world,pos,player)) {
				return InteractionResult.FAIL;
			}

			return activate(world,pos,player);
		}
		return InteractionResult.FAIL;
	}

	private InteractionResult onActivateGui(Level world, BlockPos pos, Player player, InteractionHand hand) {
		if(hand == InteractionHand.OFF_HAND) {
			return InteractionResult.FAIL;
		}

		if(isDisabled(player)) {
			return InteractionResult.FAIL;
		}

		if(!canActivate(world,pos,player)) {
			return InteractionResult.FAIL;
		}

		ItemStack is = player.getItemInHand(hand);
		if(Common.CONFIG.USE_WANDS()) {
			if(!Helper.isWand(is)) {
				Helper.sendMessage(player, Strings.ERROR_WAND);
				return InteractionResult.FAIL;
			} else if(!player.isCreative() && !Common.CONFIG.WAND_OVER_DURABILITY() && Helper.wandDamage(Helper.getBlock(world,pos)) > is.getMaxDamage() - is.getDamageValue()) {
				Helper.sendMessage(player, Strings.ERROR_WAND_DURABILITY);
				return InteractionResult.FAIL;
			}
		}

		this.openScreen(player,pos);

		return InteractionResult.SUCCESS;
	}

	public void openScreen(Player player, BlockPos pos) {
		ClientHelper.showScreen(this.screen,player,player.getLevel(),pos);
	}

	public InteractionResult activate(Level world, BlockPos pos, Player player) {
		if(build(world, pos.getX(), pos.getY(), pos.getZ(), player)) {
			afterBuild(world, pos, player);
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.FAIL;
		}
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		//build structure
		return true;
	}

	private void afterBuild(Level world, BlockPos pos, Player player) {
		Helper.sendMessage(player,this.createMessage,this.createVariable,pos);
		Helper.giveExp(world, player, Common.CONFIG.XP_AMOUNT());
		if(Common.CONFIG.USE_WANDS()) {
			ItemStack is = player.getItemInHand(InteractionHand.MAIN_HAND);
			if(Helper.isWand(is)) {
				is.hurtAndBreak(Helper.wandDamage(this), player, (entity) -> {
					entity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
				});
			}
		}
	}
}