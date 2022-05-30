package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityTree;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInstantTree extends BlockInstant {
	public BlockInstantTree() {
		super(Names.Blocks.IB_TREE, Material.plants, Block.soundTypeGrass, 0.1F);
		setBlockBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 1.0F, 0.8125F);
        setBlockTextureName(Textures.Tree.SIDE);
		setGuiID(GuiID.TREE);
	}
	
	public int getRenderType() {
		return 1;
	}
	
	public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

	public static IIcon side;

	public void registerBlockIcons(IIconRegister ir) {
		side = ir.registerIcon(Textures.Tree.SIDE);
	}

	public IIcon getIcon(int side, int meta) {
		return this.side;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTree();
	}
	
	public void build(World world, int x, int y, int z, int type, boolean fullLog, boolean fullLeaves, boolean air) {
		if(type == 0) { //IF OAK
			buildOak(world, x, y, z, fullLog, fullLeaves, air);
		} else if(type == 1) { //IF SPRUCE
			buildSpruce(world, x, y, z, fullLog, fullLeaves, air);
		} else if(type == 2) { //IF BIRCH
			buildBirch(world, x, y, z, fullLog, fullLeaves, air);
		} else if(type == 3) { //IF JUNGLE
			buildJungle(world, x, y, z, fullLog, fullLeaves, air);
//		} else if(type == 4) { //IF BIG JUNGLE
//			buildJungleBig(world, x, y, z, fullLog, fullLeaves, air);
		} else if(type == 4) { //IF ACACIA
			buildAcacia(world, x, y, z, fullLog, fullLeaves, air);
		} else if(type == 5) { //IF DARK OAK
			buildDarkOak(world, x, y, z, fullLog, fullLeaves, air);
//		} else if(type == 7) { //IF REVERSE
//			buildReverse(world, x, y, z, fullLog, fullLeaves, air);
		} else if(type == 6) { //IF GLASS
			buildGlass(world, x, y, z, fullLog, fullLeaves, air);
		}
	}

	/***************
	 * @INFO	   *
	 * x+# = EAST  *
	 * x-# = WEST  *
	 * z+# = SOUTH *
	 * z-# = NORTH *
	 * 			   *
	 * y+# = UP    *
	 * y-# = DOWN  *
	 ***************/
	
	public void buildOak(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.log, 0, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.log, 0, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.log, 0, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.log, 0, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.log, 0, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*2, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*2, z+4*2, Blocks.leaves, 4, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, Blocks.leaves, 4, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*2, z+4*2, Blocks.leaves, 4, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, Blocks.leaves, 4, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*2, Blocks.leaves, 4, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, Blocks.leaves, 4, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, Blocks.leaves, 4, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, Blocks.leaves, 4, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, Blocks.leaves, 4, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*3, z+4*2, Blocks.leaves, 4, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, Blocks.leaves, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, Blocks.leaves, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*3, z+4*2, Blocks.leaves, 4, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, Blocks.leaves, 4, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*2, Blocks.leaves, 4, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*1, Blocks.leaves, 4, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, Blocks.leaves, 4, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, Blocks.leaves, 4, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, Blocks.leaves, 4, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*1, Blocks.leaves, 4, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*0, Blocks.leaves, 4, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*4, z-4*1, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.leaves, 4, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.leaves, 4, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, Blocks.leaves, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.leaves, 4, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.leaves, 4, fullLeaves, air, true, false, true, true, false, true);
	}
	
	public void buildSpruce(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.log, 1, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.log, 1, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.log, 1, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.log, 1, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.log, 1, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*5, z+4*0, Blocks.log, 1, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*6, z+4*0, Blocks.log, 1, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*7, z+4*0, Blocks.log, 1, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*3, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*3, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*2, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*2, y+4*2, z+4*3, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*3, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*2, z+4*3, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*3, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x+4*0, y+4*2, z+4*3, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*3, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*3, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*3, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*3, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*2, z-4*3, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*3, y+4*2, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*2, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*2, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*2, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*2, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*3, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*3, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, false, true, false, false, true);

		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*4, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, true, true, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*2, y+4*5, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*5, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*2, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*5, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, false, true);

		/** fifth layer **/
		buildLeaves(world, x+4*1, y+4*6, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*6, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*6, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*6, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, false, true, true, false, true);

		/** seventh layer **/
		buildLeaves(world, x+4*1, y+4*8, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, true, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*8, z+4*1, Blocks.leaves, 5, fullLeaves, air, true, true, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*8, z+4*0, Blocks.leaves, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*8, z-4*1, Blocks.leaves, 5, fullLeaves, air, true, true, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*8, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, true, true, true, false, true);

		/** eighth layer **/
		buildLeaves(world, x+4*0, y+4*9, z+4*0, Blocks.leaves, 5, fullLeaves, air, true, false, true, true, true, true);
	}
	
	public void buildBirch(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.log, 2, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.log, 2, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.log, 2, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.log, 2, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.log, 2, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*2, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*2, z+4*2, Blocks.leaves, 6, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, Blocks.leaves, 6, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*2, z+4*2, Blocks.leaves, 6, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, Blocks.leaves, 6, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*2, Blocks.leaves, 6, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, Blocks.leaves, 6, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, Blocks.leaves, 6, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, Blocks.leaves, 6, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, Blocks.leaves, 6, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*3, z+4*2, Blocks.leaves, 6, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, Blocks.leaves, 6, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, Blocks.leaves, 6, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*3, z+4*2, Blocks.leaves, 6, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, Blocks.leaves, 6, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*2, Blocks.leaves, 6, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*1, Blocks.leaves, 6, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, Blocks.leaves, 6, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, Blocks.leaves, 6, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, Blocks.leaves, 6, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*1, Blocks.leaves, 6, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*0, Blocks.leaves, 6, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*4, z-4*1, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.leaves, 6, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.leaves, 6, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, Blocks.leaves, 6, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.leaves, 6, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.leaves, 6, fullLeaves, air, true, false, true, true, false, true);
	}
	
	public void buildJungle(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.log, 3, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.log, 3, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.log, 3, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.log, 3, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.log, 3, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*5, z+4*0, Blocks.log, 3, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*6, z+4*0, Blocks.log, 3, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*7, z+4*0, Blocks.log, 3, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*5, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*5, z+4*2, Blocks.leaves, 7, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, Blocks.leaves, 7, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*5, z+4*2, Blocks.leaves, 7, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, Blocks.leaves, 7, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*2, Blocks.leaves, 7, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, Blocks.leaves, 7, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*5, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*5, z-4*2, Blocks.leaves, 7, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*6, z+4*1, Blocks.leaves, 7, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*6, z+4*0, Blocks.leaves, 7, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*6, z-4*1, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*6, z+4*2, Blocks.leaves, 7, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*6, z+4*1, Blocks.leaves, 7, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*1, Blocks.leaves, 7, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*2, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*6, z+4*2, Blocks.leaves, 7, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*6, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*2, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*6, z+4*2, Blocks.leaves, 7, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*2, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*6, z+4*2, Blocks.leaves, 7, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*1, Blocks.leaves, 7, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*0, Blocks.leaves, 7, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*6, z-4*1, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*7, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*7, z+4*1, Blocks.leaves, 7, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*7, z-4*1, Blocks.leaves, 7, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*7, z+4*1, Blocks.leaves, 7, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*7, z+4*0, Blocks.leaves, 7, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*7, z-4*1, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*8, z+4*0, Blocks.leaves, 7, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*8, z+4*1, Blocks.leaves, 7, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*8, z+4*0, Blocks.leaves, 7, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*8, z-4*1, Blocks.leaves, 7, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*8, z+4*0, Blocks.leaves, 7, fullLeaves, air, true, false, true, true, false, true);
	}
	
	public void buildAcacia(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.log2, 0, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.log2, 0, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.log2, 0, fullLog, air, true, false, true, true, false, true);

		buildLog(world, x+4*1, y+4*2, z+4*0, Blocks.log2, 0, fullLog, air, true, true, true, true, true, false);

		buildLog(world, x+4*2, y+4*3, z+4*0, Blocks.log2, 0, fullLog, air, true, true, true, true, true, true);
		buildLog(world, x-4*1, y+4*3, z+4*0, Blocks.log2, 0, fullLog, air, true, true, true, true, true, true);

		buildLog(world, x+4*3, y+4*4, z+4*0, Blocks.log2, 0, fullLog, air, true, true, true, true, true, true);
		buildLog(world, x-4*2, y+4*4, z+4*0, Blocks.log2, 0, fullLog, air, false, true, true, true, true, true);

		buildLog(world, x-4*2, y+4*5, z+4*0, Blocks.log2, 0, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*5, y+4*4, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*5, y+4*4, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*5, y+4*4, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*4, y+4*4, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*4, y+4*4, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*4, y+4*4, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*4, y+4*4, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*4, y+4*4, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*3, y+4*4, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x+4*3, y+4*4, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*3, y+4*4, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*3, y+4*4, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x+4*2, y+4*4, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x+4*2, y+4*4, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*4, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x+4*1, y+4*4, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x+4*1, y+4*4, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*4, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*4, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*4, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*3, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*3, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, true, false, false, false);
		
		buildLeaves(world, x+4*2, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x+4*1, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*5, z+4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*3, y+4*5, z+4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*3, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*4, y+4*5, z+4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*4, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, false);
		buildLeaves(world, x-4*4, y+4*5, z-4*3, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*5, y+4*5, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*5, y+4*5, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*5, y+4*5, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*5, y+4*5, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*5, y+4*5, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*0, y+4*6, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x-4*1, y+4*6, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-4*2, y+4*6, z+4*2, Blocks.leaves2, 4, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*2, Blocks.leaves2, 4, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*3, y+4*6, z+4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*6, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*6, z-4*1, Blocks.leaves2, 4, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*4, y+4*6, z+4*0, Blocks.leaves2, 4, fullLeaves, air, true, false, true, true, false, true);
	}
	
	public void buildDarkOak(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.log2, 1, fullLog, air, false, true, false, true, true, false);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, true, true, false);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, false, true, false);
		buildLog(world, x+4*0, y+4*5, z+4*0, Blocks.log2, 1, fullLog, air, true, false, false, true, true, false);

		buildLog(world, x+4*0, y+4*0, z-4*1, Blocks.log2, 1, fullLog, air, false, true, true, false, true, false);
		buildLog(world, x+4*0, y+4*1, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*2, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*3, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*4, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, true, false);
		buildLog(world, x+4*0, y+4*5, z-4*1, Blocks.log2, 1, fullLog, air, true, false, true, false, true, false);

		buildLog(world, x-4*1, y+4*0, z+4*0, Blocks.log2, 1, fullLog, air, false, true, false, true, false, true);
		buildLog(world, x-4*1, y+4*1, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*2, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*3, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*4, z+4*0, Blocks.log2, 1, fullLog, air, false, false, false, true, false, true);
		buildLog(world, x-4*1, y+4*5, z+4*0, Blocks.log2, 1, fullLog, air, true, false, false, true, false, true);

		buildLog(world, x-4*1, y+4*0, z-4*1, Blocks.log2, 1, fullLog, air, false, true, true, false, false, true);
		buildLog(world, x-4*1, y+4*1, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*2, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*3, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*4, z-4*1, Blocks.log2, 1, fullLog, air, false, false, true, false, false, true);
		buildLog(world, x-4*1, y+4*5, z-4*1, Blocks.log2, 1, fullLog, air, true, false, true, false, false, true);

		buildLog(world, x+4*0, y+4*2, z+4*1, Blocks.log2, 1, fullLog, air, false, true, false, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*1, Blocks.log2, 1, fullLog, air, false, false, false, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*1, Blocks.log2, 1, fullLog, air, true, false, false, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*4, z+4*2, Blocks.leaves2, 5, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*4, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*4, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*4, z+4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*4, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*4, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*4, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*4, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*4, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*4, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*4, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x-4*2, y+4*4, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*4, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*3, y+4*4, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*4, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*4, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*3, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, true, false, true, true, false);
		buildLeaves(world, x+4*3, y+4*5, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, true, false, false, true, false);
		buildLeaves(world, x+4*3, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*2, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*2, y+4*5, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*2, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x+4*1, y+4*5, z+4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*5, z-4*4, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*4, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*5, z+4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*2, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*5, z-4*4, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*5, z+4*3, Blocks.leaves2, 5, fullLeaves, air, true, true, false, true, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*4, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, false, false);

		buildLeaves(world, x-4*3, y+4*5, z+4*3, Blocks.leaves2, 5, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*5, z+4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*3, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*5, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*3, y+4*5, z-4*4, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, false, true);

		buildLeaves(world, x-4*4, y+4*5, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*4, y+4*5, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*4, y+4*5, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*4, y+4*5, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, true, false, false, false, true);
		buildLeaves(world, x-4*4, y+4*5, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, true, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*2, y+4*6, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*6, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*1, y+4*6, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*6, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*6, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x+4*0, y+4*6, z+4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*6, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*6, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*6, z+4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*6, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z+4*0, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*1, Blocks.leaves2, 5, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*6, z-4*3, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*6, z+4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*6, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x-4*2, y+4*6, z-4*2, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*3, y+4*6, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*3, y+4*6, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*0, y+4*7, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*7, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*7, z+4*0, Blocks.leaves2, 5, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*7, z-4*1, Blocks.leaves2, 5, fullLeaves, air, true, false, true, false, false, true);
	}
	
	
	
	
	public void buildReverse(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.leaves, 4, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.leaves, 4, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.leaves, 4, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.leaves, 4, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.leaves, 4, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*2, z+4*1, Blocks.log, 0, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, Blocks.log, 0, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, Blocks.log, 0, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*2, z+4*2, Blocks.log, 0, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, Blocks.log, 0, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*2, z+4*2, Blocks.log, 0, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, Blocks.log, 0, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*2, Blocks.log, 0, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, Blocks.log, 0, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*1, Blocks.log, 0, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, Blocks.log, 0, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, Blocks.log, 0, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, Blocks.log, 0, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, Blocks.log, 0, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, Blocks.log, 0, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*3, z+4*2, Blocks.log, 0, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, Blocks.log, 0, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, Blocks.log, 0, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, Blocks.log, 0, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*3, z+4*2, Blocks.log, 0, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, Blocks.log, 0, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, Blocks.log, 0, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, Blocks.log, 0, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*2, Blocks.log, 0, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*1, Blocks.log, 0, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, Blocks.log, 0, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, Blocks.log, 0, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.log, 0, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, Blocks.log, 0, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, Blocks.log, 0, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*1, Blocks.log, 0, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*0, Blocks.log, 0, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*4, z-4*1, Blocks.log, 0, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.log, 0, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.log, 0, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, Blocks.log, 0, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.log, 0, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.log, 0, fullLeaves, air, true, false, true, true, false, true);
	}
	
	public void buildGlass(World world, int x, int y, int z, boolean fullLog, boolean fullLeaves, boolean air) {
		/************************************************************* up   down  north south east  west */
		buildLog(world, x+4*0, y+4*0, z+4*0, Blocks.stained_glass, 12, fullLog, air, false, true, true, true, true, true);
		buildLog(world, x+4*0, y+4*1, z+4*0, Blocks.stained_glass, 12, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*2, z+4*0, Blocks.stained_glass, 12, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*3, z+4*0, Blocks.stained_glass, 12, fullLog, air, false, false, true, true, true, true);
		buildLog(world, x+4*0, y+4*4, z+4*0, Blocks.stained_glass, 12, fullLog, air, true, false, true, true, true, true);
		
		/** first layer **/
		buildLeaves(world, x+4*2, y+4*2, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*2, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*2, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*2, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*2, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, false, true, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*2, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*2, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*2, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*2, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*2, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*2, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, false, true, true, false, false, false);

		buildLeaves(world, x-4*2, y+4*2, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*2, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, true, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*2, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, true, true, true, false, false, true);
		
		/** second layer **/
		buildLeaves(world, x+4*2, y+4*3, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*2, y+4*3, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, false, true, false);
		buildLeaves(world, x+4*2, y+4*3, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*1, y+4*3, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, true, true, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*1, y+4*3, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, true, false);
		
		buildLeaves(world, x+4*0, y+4*3, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x+4*0, y+4*3, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*3, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, false, false);

		buildLeaves(world, x-4*1, y+4*3, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, true, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, false);
		buildLeaves(world, x-4*1, y+4*3, z-4*2, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, false, true);

		buildLeaves(world, x-4*2, y+4*3, z+4*2, Blocks.stained_glass, 13, fullLeaves, air, true, true, false, true, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, false, false, true);
		buildLeaves(world, x-4*2, y+4*3, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, false, true);
		
		/** third layer **/
		buildLeaves(world, x+4*1, y+4*4, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*4, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, true, true, false);
		buildLeaves(world, x+4*0, y+4*4, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, false, false, true, false, true, false);

		buildLeaves(world, x-4*1, y+4*4, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, true, false, true);
		buildLeaves(world, x-4*1, y+4*4, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, false, false, false, false, false, true);
		buildLeaves(world, x-4*1, y+4*4, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, false, true);
		
		/** fourth layer **/
		buildLeaves(world, x+4*1, y+4*5, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, true, true, false);

		buildLeaves(world, x+4*0, y+4*5, z+4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, true, true, true);
		buildLeaves(world, x+4*0, y+4*5, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, true, false, false, false, false, false);
		buildLeaves(world, x+4*0, y+4*5, z-4*1, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, false, true, true);

		buildLeaves(world, x-4*1, y+4*5, z+4*0, Blocks.stained_glass, 13, fullLeaves, air, true, false, true, true, false, true);
	}
	
	
	
	public void buildLog(World world, int x, int y, int z, Block block, int meta, boolean fullLog, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		if(fullLog) {
			BuildHelper.build(world, x-1, y, z-1, block, meta, 4, 4, 4);
		} else {
			if(air) {
				BuildHelper.build(world, x-1, y, z-1, Blocks.air, 0, 4, 4, 4);
			}
			if(up) {
				BuildHelper.build(world, x-1, y+3, z-1, block, meta, 4, 1, 4);
			}
			if(down) {
				BuildHelper.build(world, x-1, y, z-1, block, meta, 4, 1, 4);
			}
			if(north) {
				BuildHelper.build(world, x-1, y, z-1, block, meta, 1, 4, 4);
			}
			if(south) {
				BuildHelper.build(world, x-1, y, z+2, block, meta, 1, 4, 4);
			}
			if(east) {
				BuildHelper.build(world, x+2, y, z-1, block, meta, 4, 4, 1);
			}
			if(west) {
				BuildHelper.build(world, x-1, y, z-1, block, meta, 4, 4, 1);
			}
		}
	}
	
	public void buildLeaves(World world, int x, int y, int z, Block block, int meta, boolean fullLeaves, boolean air, boolean up, boolean down, boolean north, boolean south, boolean east, boolean west) {
		if(fullLeaves) {
			BuildHelper.build(world, x-1, y, z-1, block, meta, 4, 4, 4);
		} else {
			if(air) {
				BuildHelper.build(world, x-1, y, z-1, Blocks.air, 0, 4, 4, 4);
			}
			if(up) {
				BuildHelper.build(world, x-1, y+3, z-1, block, meta, 4, 1, 4);
			}
			if(down) {
				BuildHelper.build(world, x-1, y, z-1, block, meta, 4, 1, 4);
			}
			if(north) {
				BuildHelper.build(world, x-1, y, z-1, block, meta, 1, 4, 4);
			}
			if(south) {
				BuildHelper.build(world, x-1, y, z+2, block, meta, 1, 4, 4);
			}
			if(east) {
				BuildHelper.build(world, x+2, y, z-1, block, meta, 4, 4, 1);
			}
			if(west) {
				BuildHelper.build(world, x-1, y, z-1, block, meta, 4, 4, 1);
			}
		}
	}
}
