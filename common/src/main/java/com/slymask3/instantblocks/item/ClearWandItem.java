package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.ColorBlock;
import com.slymask3.instantblocks.builder.BlockPosHolder;
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
		BlockPos origin = context.getClickedPos();
		Player player = context.getPlayer();

		if(Helper.isClient(world) || player == null) {
			return InteractionResult.PASS;
		}

		if(!(world.getBlockState(origin).getBlock() instanceof ColorBlock)) {
			Helper.sendMessage(player, Strings.ERROR_CLEAR);
			return InteractionResult.PASS;
		}

		BlockPosHolder holder = new BlockPosHolder(origin, (pos,hold) -> {
			hold.checkBlock(pos.north(1));
			hold.checkBlock(pos.east(1));
			hold.checkBlock(pos.south(1));
			hold.checkBlock(pos.west(1));
			hold.checkBlock(pos.above(1));
			hold.checkBlock(pos.below(1));
		}, (pos,hold) -> {
			Block block = world.getBlockState(pos).getBlock();
			if(block instanceof ColorBlock && hold.add(pos)) {
				hold.checkDirections(pos);
			}
		});

		Builder builder = Builder.setup(world,origin).setOrigin(Builder.Origin.FROM);
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
}