package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.ColorBlock;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ClearWandItem extends TieredItem {
	public ClearWandItem(Tier tier) {
		super(tier,new Properties().tab(Common.ITEM_GROUP));
	}

	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();

		if(Helper.isClient(world) || player == null) {
			return InteractionResult.PASS;
		}

		Helper.BlockPosHolder holder = new Helper.BlockPosHolder();

		checkForBlock(world,pos,holder);
		if(holder.isEmpty()) {
			Helper.sendMessage(player, Strings.ERROR_CLEAR);
			return InteractionResult.PASS;
		}

		Builder builder = Builder.setup(world,pos).setOrigin(Builder.Origin.FROM);
		for(BlockPos posItem : holder.getList()) {
			Single.setup(builder,world,posItem).setBlock(Blocks.AIR).queue();
		}
		builder.build();

		ItemStack itemStack = player.getItemInHand(context.getHand());
		itemStack.hurtAndBreak(holder.size(), player, (entity) -> {
			entity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
		});

		Helper.sendMessage(player, Strings.CLEAR, ChatFormatting.GREEN + String.valueOf(holder.size()));

		return InteractionResult.PASS;
	}

	private void checkForBlock(Level world, BlockPos pos, Helper.BlockPosHolder holder) {
		check(world,pos.north(1),holder);
		check(world,pos.east(1),holder);
		check(world,pos.south(1),holder);
		check(world,pos.west(1),holder);
		check(world,pos.above(1),holder);
		check(world,pos.below(1),holder);
	}

	private void check(Level world, BlockPos pos, Helper.BlockPosHolder holder) {
		Block block = world.getBlockState(pos).getBlock();
		if(block instanceof ColorBlock && holder.add(pos)) {
			checkForBlock(world,pos,holder);
		}
	}
}