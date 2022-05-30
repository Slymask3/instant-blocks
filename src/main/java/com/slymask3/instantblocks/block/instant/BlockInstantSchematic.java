package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInstantSchematic extends BlockInstant {
	public BlockInstantSchematic() {
		super(Names.Blocks.IB_SCHEMATIC, Material.wood, Block.soundTypeWood, 1.5F);
        setBlockTextureName(Textures.Harvest.SIDE0);
		setGuiID(GuiID.SCHEMATIC);
	}

	public static IIcon top;
	public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(Textures.Schematic.TOP);
		side = ir.registerIcon(Textures.Schematic.SIDE);
	}
    
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return Blocks.planks.getIcon(0, 5);
		} else if(side == 1) {
			return top;
		} else {
			return this.side;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySchematic();
	}

	public boolean build(World world, int x, int y, int z, String schematicName, boolean center, boolean ignoreAir) {
		SchematicHelper.Schematic schematic = SchematicHelper.readSchematic(schematicName);
		if(schematic != null) {
			BuildHelper.setBlock(world,x, y, z, Blocks.air);
			buildSchematic(world, x, y, z, schematic, center, ignoreAir);
			return true;
		}
		return false;
	}

	public static void buildSchematic(World world, int X, int Y, int Z, SchematicHelper.Schematic schematic, boolean center, boolean ignoreAir) {
		short width = schematic.width;
		short height = schematic.height;
		short length = schematic.length;
		byte[] block = schematic.blocks;
		byte[] meta = schematic.data;
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				for(int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					if((block[index] & 0xFF) != 0 && ignoreAir) {
						if(center) {
							BuildHelper.setBlock(world,x+X-(width/2), y+Y, z+Z-(length/2), Block.getBlockById(block[index] & 0xFF), meta[index], 2);
						} else {
							BuildHelper.setBlock(world,x+X, y+Y, z+Z, Block.getBlockById(block[index] & 0xFF), meta[index], 2);
						}
					} else if(ignoreAir) {
						//Ignore Air
					} else {
						if(center) {
							BuildHelper.setBlock(world,x+X-(width/2), y+Y, z+Z-(length/2), Block.getBlockById(block[index] & 0xFF), meta[index], 2);
						} else {
							BuildHelper.setBlock(world,x+X, y+Y, z+Z, Block.getBlockById(block[index] & 0xFF), meta[index], 2);
						}
					}
				}
			}
		}
	}
}
