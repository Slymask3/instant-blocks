package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.builder.BlockPosHolder;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
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

		if(!Helper.isColorBlock(world.getBlockState(origin).getBlock())) {
			Helper.sendMessage(player, Strings.ERROR_CLEAR);
			return InteractionResult.PASS;
		}

		BlockPosHolder holder = new BlockPosHolder(origin,true,true,true,true,true,true, (pos,hold) -> {
			Block block = world.getBlockState(pos).getBlock();
			if(Helper.isColorBlock(block) && hold.add(pos)) {
				hold.checkDirections(pos);
			}
		});

		Builder builder = Builder.setup(world,origin).setOrigin(Builder.Origin.FROM).setParticles(ClientHelper.Particles.CLEAR_BLOCK);
		for(BlockPos posItem : holder.getList()) {
			Single.setup(builder,world,posItem).setBlock(Blocks.AIR).queue();
		}
		builder.build();

		ItemStack itemStack = player.getItemInHand(context.getHand());
		itemStack.hurtAndBreak((int)Math.floor(holder.size() / 100.0), player, (entity) -> {
			entity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
		});

		Helper.sendMessage(player, Strings.CLEAR, ChatFormatting.GREEN + String.valueOf(holder.size()));

		return InteractionResult.PASS;
	}
}