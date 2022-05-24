package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGuiIB extends BlockContainer {
    private GuiID guiID;

	protected BlockGuiIB(String name, Material material, SoundType soundType, float hardness, GuiID guiID) {
		super(material);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName(Reference.MOD_ID + ":" + name);
        setHardness(hardness);
        setResistance(2000F);
        setStepSound(soundType);
        setGuiID(guiID);
	}

    private void setGuiID(GuiID guiID) {
        this.guiID = guiID;
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack is = player.getCurrentEquippedItem();

        if(Config.USE_WANDS) {
            if(is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
                //is.damageItem(1, player);
            } else {
                IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
                return true;
            }
        }

        player.openGui(InstantBlocks.instance, this.guiID.ordinal(), world, x, y, z);

        return true;
    }
}
