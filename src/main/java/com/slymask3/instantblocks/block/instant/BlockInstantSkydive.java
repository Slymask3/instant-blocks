package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
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

public class BlockInstantSkydive extends BlockInstant {
    public BlockInstantSkydive() {
		super(Names.Blocks.IB_SKYDIVE, Material.cloth, Block.soundTypeCloth, 1.5F);
        setBlockTextureName(Textures.Harvest.SIDE0);
		setGuiID(GuiID.SKYDIVE);
		setCreateMessage(Strings.CREATE_SKYDIVE);
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

	public void build(World world, int x, int y, int z, EntityPlayer player, int[] selectedColors, int radius, boolean tp) {
		int meta = world.getBlockMetadata(x, y, z);

		BuildHelper.setBlock(world,x,y,z,Blocks.air);

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
		int index = 0;
		colors = new Color[selectedColors.length*10];
		for(int i=0; i<selectedColors.length; i++) {
			Color after;
			if(i+1 < selectedColors.length) {
				after = new Color(selectedColors[i+1]);
			} else {
				after = new Color(selectedColors[0]);
			}
			Color base = new Color(selectedColors[i]);
			colors[index] = base; index++;
			colors[index] = Colors.getColorBetween(base,after,90,10); index++;
			colors[index] = Colors.getColorBetween(base,after,80,20); index++;
			colors[index] = Colors.getColorBetween(base,after,70,30); index++;
			colors[index] = Colors.getColorBetween(base,after,60,40); index++;
			colors[index] = Colors.getColorBetween(base,after,50,50); index++;
			colors[index] = Colors.getColorBetween(base,after,40,60); index++;
			colors[index] = Colors.getColorBetween(base,after,30,70); index++;
			colors[index] = Colors.getColorBetween(base,after,20,80); index++;
			colors[index] = Colors.getColorBetween(base,after,10,90); index++;
		}

		int i = 0;
		int min = Config.SKYDIVE_MIN;
		int max = Config.SKYDIVE_MAX;
		int water = Config.SKYDIVE_WATER;
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