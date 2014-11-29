package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.init.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerInstantCraft extends Container
{

    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    public InventoryBasic instantBlocks;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;

    public ContainerInstantCraft(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        craftMatrix = new InventoryCrafting(this, 6, 6);
        craftResult = new InventoryCraftResult();
        instantBlocks = new InventoryBasic("instantBlocks", true, 6 * 6);
        
        worldObj = world;
        posX = i;
        posY = j;
        posZ = k;
        
        //result
        //this.addSlotToContainer(new SlotCrafting(inventoryplayer.player, craftMatrix, craftResult, 0, 131, 36));
        this.addSlotToContainer(new SlotCrafting(inventoryplayer.player, craftMatrix, craftResult, 0, 227, 63));
        
        //crafting
        for(int x = 0; x < 6; x++) {
            for(int y = 0; y < 6; y++) {
            	this.addSlotToContainer(new Slot(craftMatrix, y + x * 5, 84 + y * 18, 18 + x * 18));
            }

        }
        
       //instantBlocks
        for(int x = 0; x < 6; x++) {
            for(int y = 0; y < 4; y++) {
            	this.addSlotToContainer(new Slot(instantBlocks, y + x * 5, 8 + y * 18, 18 + x * 18));
            }

        }

        //inventory
        for(int i1 = 0; i1 < 3; i1++) {
            for(int l1 = 0; l1 < 9; l1++) {
            	this.addSlotToContainer(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 48 + l1 * 18, 140 + i1 * 18));
            }

        }

        //hotbar
        for(int x = 0; x < 9; x++) {
        	this.addSlotToContainer(new Slot(inventoryplayer, x, 48 + x * 18, 198));
        }

        onCraftMatrixChanged(craftMatrix);
    }

    public void onCraftMatrixChanged(IInventory iinventory)
    {
        craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
    }

    public void onContainerClosed(EntityPlayer entityplayer)
    {
        super.onContainerClosed(entityplayer);
        if(worldObj.isRemote)
        {
            return;
        }
//        for(int i = 0; i < 25; i++)
//        {
//            ItemStack itemstack = craftMatrix.getStackInSlot(i);
//            if(itemstack != null)
//            {
//                //entityplayer.dropPlayerItem(itemstack);
//                entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
//            }
//        }

    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if(worldObj.getBlock(posX, posY, posZ) != ModBlocks.instantcraft)
        {
            return false;
        } else
        {
            return entityplayer.getDistanceSq((double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D) <= 64D;
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(par2);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(par2 == 0)
            {
                if(!mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }
            } else
            if(par2 >= 10 && par2 < 37)
            {
                if(!mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            } else
            if(par2 >= 37 && par2 < 46)
            {
                if(!mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            } else
            if(!mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }
}