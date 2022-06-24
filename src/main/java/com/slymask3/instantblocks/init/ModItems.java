package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.item.InstantWandItem;
import com.slymask3.instantblocks.reference.Names;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InstantBlocks.MOD_ID);

	public static final RegistryObject<Item> WAND_WOOD = ITEMS.register(Names.Items.IB_WAND_WOOD, () -> new InstantWandItem(Tiers.WOOD));
	public static final RegistryObject<Item> WAND_STONE = ITEMS.register(Names.Items.IB_WAND_STONE, () -> new InstantWandItem(Tiers.STONE));
	public static final RegistryObject<Item> WAND_IRON = ITEMS.register(Names.Items.IB_WAND_IRON, () -> new InstantWandItem(Tiers.IRON));
	public static final RegistryObject<Item> WAND_GOLD = ITEMS.register(Names.Items.IB_WAND_GOLD, () -> new InstantWandItem(Tiers.GOLD));
	public static final RegistryObject<Item> WAND_DIAMOND = ITEMS.register(Names.Items.IB_WAND_DIAMOND, () -> new InstantWandItem(Tiers.DIAMOND));
	public static final RegistryObject<Item> WAND_NETHERITE = ITEMS.register(Names.Items.IB_WAND_NETHERITE, () -> new InstantWandItem(Tiers.NETHERITE));

	public static class ModCreativeTab extends CreativeModeTab {
		public static final ModCreativeTab instance = new ModCreativeTab(CreativeModeTab.TABS.length, InstantBlocks.MOD_ID);
		private ModCreativeTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModBlocks.INSTANT_WOOD_HOUSE.get());
		}
	}
}