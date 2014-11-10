package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInstantStatue extends TileEntity { //implements ISidedInventory {
	//private String username = "";
	//public EntityPlayer player;
	//public int color = 0x00000000;
	
	//public static World world;
	//public static int x;
	//public static int y;
	//public static int z;
	//public static TileEntityInstantStatue tile;

	public String username="";
	//public EntityPlayer player;
	public boolean head;
	public boolean body;
	public boolean armLeft;
	public boolean armRight;
	public boolean legLeft;
	public boolean legRight;
	public boolean rgb;

	public TileEntityInstantStatue() {
		super();
		this.username="";
		this.head=true;
		this.body=true;
		this.armLeft=true;
		this.armRight=true;
		this.legLeft=true;
		this.legRight=true;
		this.rgb=true;
	}

//	public void updateEntity() {
//		if (this.username != "" && !this.getWorldObj().isRemote) {
//			//sendChangeToServer();
//			((BlockInstantStatue) this.getBlockType()).build(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), this.username, this.head, this.body, this.armLeft, this.armRight, this.legLeft, this.legRight);
//		}
//		LogHelper.info("updateEntity(): username = " + this.username);
//	}
	
//	@Override
//	public void updateEntity() {
//		if (BlockInstantStatue.username != "" && BlockInstantStatue.player != null && !this.getWorldObj().isRemote) {
//			BlockInstantStatue.build(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, BlockInstantStatue.player, this.getBlockMetadata(), BlockInstantStatue.username, BlockInstantStatue.head, BlockInstantStatue.body, BlockInstantStatue.armLeft, BlockInstantStatue.armRight, BlockInstantStatue.legLeft, BlockInstantStatue.legRight);
//			BlockInstantStatue.username = "";
//			BlockInstantStatue.player = null;
//			//this.tile = null;
//			//setPlayer(null);
//			//setUsername("");
//			//LogHelper.info("isRemote == " + this.getWorldObj().isRemote);
//			//LogHelper.info("updateEntity(): "+head+" "+body+" "+armLeft+" "+armRight+" "+legLeft+" "+legRight);
//			/*if (!this.getWorldObj().isRemote) {
//				BlockInstantStatue.username = "";
//				BlockInstantStatue.player = null;
//			}*/
//		
//		}
//		
//		//LogHelper.info("isRemote == " + this.getWorldObj().isRemote + " username == " + BlockInstantStatue.username);
//	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setParts(boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight) {
		this.head = head;
		this.body = body;
		this.armLeft = armLeft;
		this.armRight = armRight;
		this.legLeft = legLeft;
		this.legRight = legRight;

		//LogHelper.info("setParts(): "+head+" "+body+" "+armLeft+" "+armRight+" "+legLeft+" "+legRight);
	}
	
	//public void setPlayer(EntityPlayer player) {
	//	this.player = player;
	//}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		username = nbt.getString("Username");
		//player = EntityPlayer.getName(nbt.getString("Player"));
		head = nbt.getBoolean("Head");
		body = nbt.getBoolean("Body");
		armLeft = nbt.getBoolean("ArmLeft");
		armRight = nbt.getBoolean("ArmRight");
		legLeft = nbt.getBoolean("LegLeft");
		legRight = nbt.getBoolean("LegRight");
		rgb = nbt.getBoolean("RGB");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("Username", username);
		nbt.setBoolean("Head", head);
		nbt.setBoolean("Body", body);
		nbt.setBoolean("ArmLeft", armLeft);
		nbt.setBoolean("ArmRight", armRight);
		nbt.setBoolean("LegLeft", legLeft);
		nbt.setBoolean("LegRight", legRight);
		nbt.setBoolean("RGB", rgb);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
    }
	
//	public void sendChangeToServer(){
//	    //ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
//	    //DataOutputStream outputStream = new DataOutputStream(bos);
//	    
//	    ByteBuf buf = new ByteBuf();
//	    ByteBufUtil buff = new ByteBufUtil();
//	    //buf.capacity(8);
//	    buf.writeInt(this.xCoord);
//	    buf.writeInt(this.yCoord);
//	    buf.writeInt(this.zCoord);
//	    buf.writeBytes(this.username.getBytes());
////	    try {
////	        outputStream.writeInt(this.xCoord);
////	        outputStream.writeInt(this.yCoord);
////	        outputStream.writeInt(this.zCoord);
////	        outputStream.writeUTF(this.username);
////	    } catch (Exception ex) {
////	        ex.printStackTrace();
////	    }
//	               
//	    //S3FPacketCustomPayload packet = new S3FPacketCustomPayload("InstantBlocks", bos.toByteArray());
//	    
//	    IMessage msg = null;
//	    msg.toBytes(buf);
//
//	    PacketDispatcher.sendToServer(msg);
//	}
}