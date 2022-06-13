package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class InstantWandItem extends TieredItem {
	public InstantWandItem(Tier tier) {
		super(tier,new Item.Properties().tab(ModItems.ModCreativeTab.instance));
	}

	public void appendHoverText(ItemStack is, @Nullable Level world, List<Component> list, TooltipFlag flag) {
		int toolDamage = Helper.wandDamage(is);
		int max = (int)Math.floor(is.getMaxDamage() / toolDamage) + 1;
		int dmg = max - (int)Math.floor(is.getDamageValue()/toolDamage);
		list.add(new TextComponent("Uses: " + dmg + "/" + max));
	}
}