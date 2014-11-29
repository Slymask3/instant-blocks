package com.slymask3.instantblocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInstantCraft extends TileEntity implements IInventory {

	private ItemStack[] contents = new ItemStack[36];
	
	public TileEntityInstantCraft() {
		super();
	}

	@Override
	public int getSizeInventory() {
		return 36;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.contents[slot];
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public void readFromNBT(NBTTagCompound nbt) {
//		super.readFromNBT(nbt);
//	}
//
//	@Override
//	public void writeToNBT(NBTTagCompound nbt) {
//		super.writeToNBT(nbt);
//	}

//	@Override
//	public Packet getDescriptionPacket() {
//		NBTTagCompound tag = new NBTTagCompound();
//		writeToNBT(tag);
//		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
//	}
//
//	@Override
//	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
//		readFromNBT(pkt.func_148857_g());
//    }
	
//	public boolean canInteractWith(EntityPlayer entityplayer)
//    {
//        if(worldObj.getBlockId(posX, posY, posZ) != Builder.builder.blockID)
//        {
//            return false;
//        } else
//        {
//            return entityplayer.getDistanceSq((double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D) <= 64D;
//        }
//    }
}