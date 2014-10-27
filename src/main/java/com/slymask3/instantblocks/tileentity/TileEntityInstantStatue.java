package com.slymask3.instantblocks.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.Level;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstantStatue;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityInstantStatue extends TileEntity { //implements ISidedInventory {
	//private String username = "";
	//public EntityPlayer player;
	public int color = 0x00000000;
	
	//public static World world;
	//public static int x;
	//public static int y;
	//public static int z;
	//public static TileEntityInstantStatue tile;

	public TileEntityInstantStatue() {
		super();
	}

	@Override
	public void updateEntity() {
		if (BlockInstantStatue.username != "" && BlockInstantStatue.player != null && !this.getWorldObj().isRemote) {
			BlockInstantStatue.build(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, BlockInstantStatue.player, this.getBlockMetadata(), BlockInstantStatue.username, BlockInstantStatue.head, BlockInstantStatue.body, BlockInstantStatue.armLeft, BlockInstantStatue.armRight, BlockInstantStatue.legLeft, BlockInstantStatue.legRight);
			BlockInstantStatue.username = "";
			BlockInstantStatue.player = null;
			//this.tile = null;
			//setPlayer(null);
			//setUsername("");
			//LogHelper.info("isRemote == " + this.getWorldObj().isRemote);
			//LogHelper.info("updateEntity(): "+head+" "+body+" "+armLeft+" "+armRight+" "+legLeft+" "+legRight);
			/*if (!this.getWorldObj().isRemote) {
				BlockInstantStatue.username = "";
				BlockInstantStatue.player = null;
			}*/
		
		}
		
		//LogHelper.info("isRemote == " + this.getWorldObj().isRemote + " username == " + BlockInstantStatue.username);
	}
	
	/*public void setParts(boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight) {
		this.head = head;
		this.body = body;
		this.armLeft = armLeft;
		this.armRight = armRight;
		this.legLeft = legLeft;
		this.legRight = legRight;

		LogHelper.info("setParts(): "+head+" "+body+" "+armLeft+" "+armRight+" "+legLeft+" "+legRight);
	}*/
	
	//public void setUsername(String username) {
	//	this.username = username;
	//}
	
	//public void setPlayer(EntityPlayer player) {
	//	this.player = player;
	//}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}
}