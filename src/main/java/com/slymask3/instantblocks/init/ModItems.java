package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.item.ItemInstantWand;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static final RegistryObject<Item> ibWandWood = ITEMS.register(Names.Items.IB_WAND_WOOD, () -> new ItemInstantWand(Tiers.WOOD));
	public static final RegistryObject<Item> ibWandStone = ITEMS.register(Names.Items.IB_WAND_STONE, () -> new ItemInstantWand(Tiers.STONE));
	public static final RegistryObject<Item> ibWandIron = ITEMS.register(Names.Items.IB_WAND_IRON, () -> new ItemInstantWand(Tiers.IRON));
	public static final RegistryObject<Item> ibWandGold = ITEMS.register(Names.Items.IB_WAND_GOLD, () -> new ItemInstantWand(Tiers.GOLD));
	public static final RegistryObject<Item> ibWandDiamond = ITEMS.register(Names.Items.IB_WAND_DIAMOND, () -> new ItemInstantWand(Tiers.DIAMOND));

	public static class ModCreativeTab extends CreativeModeTab {
		public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, Reference.MOD_ID);
		private ModCreativeTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModBlocks.ibWoodHouse.get());
		}
	}
}
