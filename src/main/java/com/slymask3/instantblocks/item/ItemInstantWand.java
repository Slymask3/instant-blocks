package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ItemInstantWand extends TieredItem {
	public ItemInstantWand(Tier tier) {
		super(tier,new Item.Properties().tab(ModItems.ModCreativeTab.instance));
	}

	public void appendHoverText(ItemStack is, @Nullable Level world, List<Component> list, TooltipFlag flag) {
		int max = (is.getMaxDamage()) + 1;
		int dmg = (is.getMaxDamage() - is.getDamageValue()) + 1;
		list.add(new TextComponent("Uses: " + dmg + "/" + max));
	}
}