package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.ColorHelper;
import com.slymask3.instantblocks.util.Coords;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;

public class BlockInstantFall extends BlockInstant {
    public BlockInstantFall() {
		super(Names.Blocks.IB_SKYDIVE, Material.cloth, Block.soundTypeCloth, 1.5F);
        setBlockTextureName(Textures.Harvest.SIDE0);
		setGuiID(GuiID.SKYDIVE);
    }
    
    public static IIcon bottom;
    public static IIcon top;
    public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		bottom = ir.registerIcon(Textures.Skydive.BOTTOM_A);
		top = ir.registerIcon(Textures.Skydive.TOP_A);
		side = ir.registerIcon(Textures.Skydive.SIDE_A);
	}
    
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return bottom;
		} else if(side == 1) {
			return top;
		} else if(side>=2 && side<=5) {
			return this.side;
		}
		return blockIcon;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}

	public void build(World world, int x, int y, int z, String playerS, int c0, int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int c10, int radius, boolean tp, boolean actualRainbow) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		int meta = world.getBlockMetadata(x, y, z);

		ArrayList<Coords> coordsList = new ArrayList<>();
		ArrayList<Coords> coordsAirList = new ArrayList<>();
		double distance;
		for (int row = 0; row <= 2 * radius; row++) {
			for (int col = 0; col <= 2 * radius; col++) {
				distance = Math.sqrt((row - radius) * (row - radius) + (col - radius) * (col - radius));
				if(distance > radius - 0.4 && distance < radius + 0.5) {
					coordsList.add(new Coords(x+row-radius,0,z+col-radius));
				} else if(distance < radius - 0.3) {
					coordsAirList.add(new Coords(x+row-radius,0,z+col-radius));
				}
			}
		}

		Color[] colors;
		if(actualRainbow) {
			colors = ColorHelper.generateRainbow(100);
		} else {
			int index = 0;
			int length = 3;
			colors = new Color[33];
			for(int i=0; i<length; i++) { colors[index] = new Color(c0); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c1); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c2); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c3); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c4); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c5); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c6); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c7); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c8); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c9); index++; }
			for(int i=0; i<length; i++) { colors[index] = new Color(c10); index++; }
		}

		int i = 0;
		int min = 5;
		int max = 256;
		int water = 3;
		for(int c=max; c>=min; c--) {
			for(Coords coords : coordsAirList) {
				if(c == min) {
					BuildHelper.setColorBlock(world,coords.getX(),c,coords.getZ(),colors[i].getRGB());
				} else if(c < min+water+1) {
					BuildHelper.setBlock(world,coords.getX(),c,coords.getZ(),Blocks.water);
				} else {
					BuildHelper.setBlock(world,coords.getX(),c,coords.getZ(),Blocks.air);
				}
			}
			for(Coords coords : coordsList) {
				if(i>=colors.length) {
					i = 0;
				}
				if(c == min+water+1 && (((coords.getX()-radius==x || coords.getX()+radius==x) && coords.getZ()==z) || ((coords.getZ()-radius==z || coords.getZ()+radius==z) && coords.getX()==x))) {
					BuildHelper.setBlock(world,coords.getX(),c,coords.getZ(),ModBlocks.skydiveTP);
				} else {
					BuildHelper.setColorBlock(world,coords.getX(),c,coords.getZ(),colors[i].getRGB());
				}
			}
			i++;
		}

		if(meta == 0) {
			IBHelper.teleport(world,player,x,max+1,z+radius, tp);
		} else if(meta == 1) {
			IBHelper.teleport(world,player,x-radius,max+1,z, tp);
		} else if(meta == 2) {
			IBHelper.teleport(world,player,x,max+1,z-radius, tp);
		} else if(meta == 3) {
			IBHelper.teleport(world,player,x+radius,max+1,z, tp);
		}
	}

    @Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySkydive();
	}
}