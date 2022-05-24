package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockInstant extends Block implements ITileEntityProvider {
	Block block;
	
	boolean bottomB = true;
	boolean topB = true;
	boolean leftB = true;
	boolean rightB = true;
	boolean frontB = true;
	boolean backB = true;
	
	String bottomS = Reference.MOD_ID + ":woodhouse_top";
	String topS = Reference.MOD_ID + ":woodhouse_top";
	String leftS = Reference.MOD_ID + ":woodhouse_top";
	String rightS = Reference.MOD_ID + ":woodhouse_top";
	String frontS = Reference.MOD_ID + ":woodhouse_top";
	String backS = Reference.MOD_ID + ":woodhouse_top";

	Block bottomBl;
	Block topBl;
	Block leftBl;
	Block rightBl;
	Block frontBl;
	Block backBl;
	
	IIcon bottomI;
	IIcon topI;
	IIcon leftI;
	IIcon rightI;
	IIcon frontI;
	IIcon backI;

	public String createMsg = "";
	public String errorMsg = "";

	boolean is_directional = false;
	GuiID guiID = null;
	
	protected BlockInstant(Block block, String name, Material material, SoundType soundType, float hardness) {
		super(material);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName(Reference.MOD_ID + ":" + name);
        setHardness(hardness);
        setResistance(2000F);
        setStepSound(soundType);
        this.block = block;
	}

	public void setCreateMsg(String msg) {
		this.createMsg = msg;
	}

	public void setErrorMsg(String msg) {
		this.errorMsg = msg;
	}

	public void setDirectional(boolean directional) {
		this.is_directional = directional;
	}

	public void setGuiID(GuiID guiID) {
		this.guiID = guiID;
	}

	public void setTextures(String bottom, String top, String left, String right, String front, String back) {
		this.bottomS = bottom;
		this.topS = top;
		this.leftS = left;
		this.rightS = right;
		this.frontS = front;
		this.backS = back;
	}
	
	public void setTextures(String bottom, String top, String side) {
		this.bottomS = bottom;
		this.topS = top;
		this.leftS = side;
		this.rightS = side;
		this.frontS = side;
		this.backS = side;
	}
	
	public void setTextures(String texture) {
		this.bottomS = texture;
		this.topS = texture;
		this.leftS = texture;
		this.rightS = texture;
		this.frontS = texture;
		this.backS = texture;
	}
	

	public void setTextures(IIcon bottom, IIcon top, IIcon left, IIcon right, IIcon front, IIcon back) {
		this.bottomI = bottom;
		this.topI = top;
		this.leftI = left;
		this.rightI = right;
		this.frontI = front;
		this.backI = back;
	}
	

	public void setTextures(IIcon bottom, IIcon top, IIcon side) {
		this.bottomI = bottom;
		this.topI = top;
		this.leftI = side;
		this.rightI = side;
		this.frontI = side;
		this.backI = side;
	}
	

	public void setTextures(IIcon texture) {
		this.bottomI = texture;
		this.topI = texture;
		this.leftI = texture;
		this.rightI = texture;
		this.frontI = texture;
		this.backI = texture;
	}

	//Glass Dome
	public void setTextures(Block bottom, Block top, String side) {
		this.bottomBl = bottom;
		this.topBl = top;
		this.leftS = side;
		this.rightS = side;
		this.frontS = side;
		this.backS = side;
	}

	//Lava and Water
	public void setTextures(Block side) {
		this.bottomBl = side;
		this.topBl = side;
		this.leftBl = side;
		this.rightBl = side;
		this.frontBl = side;
		this.backBl = side;
	}
	
	public void setTextureBooleans(boolean bottom, boolean top, boolean left, boolean right, boolean front, boolean back) {
		this.bottomB = bottom;
		this.topB = top;
		this.leftB = left;
		this.rightB = right;
		this.frontB = front;
		this.backB = back;
	}
	
	public void registerBlockIcons(IIconRegister ir) {
		//if(bottomB);
			bottomI = ir.registerIcon(bottomS);
		//if(topB);
			topI = ir.registerIcon(topS);
		//if(leftB);
			leftI = ir.registerIcon(leftS);
		//if(rightB);
			rightI = ir.registerIcon(rightS);
		//if(frontB);
			frontI = ir.registerIcon(frontS);
		//if(backB);
			backI = ir.registerIcon(backS);
		
		this.blockIcon = ir.registerIcon(this.getTextureName());
	}

	public IIcon getIcon(int side, int meta) {
		if(meta==2 || meta==6) {
			if(side==0) {
				if(bottomB) {
					return bottomI;
				} else {
					return bottomBl.getIcon(0, 0);
				}
			} else if(side==1) {
				if(topB) {
					return topI;
				} else {
					return topBl.getIcon(0, 0);
				}
			} else if(side==2) {
				if(frontB) {
					return frontI;
				} else {
					return frontBl.getIcon(0, 0);
				}
			} else if(side==3) {
				if(backB) {
					return backI;
				} else {
					return backBl.getIcon(0, 0);
				}
			} else if(side==4) {
				if(leftB) {
					return leftI;
				} else {
					return leftBl.getIcon(0, 0);
				}
			} else if(side==5) {
				if(rightB) {
					return rightI;
				} else {
					return rightBl.getIcon(0, 0);
				}
			}
		} else if(meta==0 || meta==4) {
			if(side==0) {
				if(bottomB) {
					return bottomI;
				} else {
					return bottomBl.getIcon(0, 0);
				}
			} else if(side==1) {
				if(topB) {
					return topI;
				} else {
					return topBl.getIcon(0, 0);
				}
			} else if(side==2) {
				if(backB) {
					return backI;
				} else {
					return backBl.getIcon(0, 0);
				}
			} else if(side==3) {
				if(frontB) {
					return frontI;
				} else {
					return frontBl.getIcon(0, 0);
				}
			} else if(side==4) {
				if(rightB) {
					return rightI;
				} else {
					return rightBl.getIcon(0, 0);
				}
			} else if(side==5) {
				if(leftB) {
					return leftI;
				} else {
					return leftBl.getIcon(0, 0);
				}
			}
		} else if(meta==1 || meta==5) {
			if(side==0) {
				if(bottomB) {
					return bottomI;
				} else {
					return bottomBl.getIcon(0, 0);
				}
			} else if(side==1) {
				if(topB) {
					return topI;
				} else {
					return topBl.getIcon(0, 0);
				}
			} else if(side==2) {
				if(rightB) {
					return rightI;
				} else {
					return rightBl.getIcon(0, 0);
				}
			} else if(side==3) {
				if(leftB) {
					return leftI;
				} else {
					return leftBl.getIcon(0, 0);
				}
			} else if(side==4) {
				if(frontB) {
					return frontI;
				} else {
					return frontBl.getIcon(0, 0);
				}
			} else if(side==5) {
				if(backB) {
					return backI;
				} else {
					return backBl.getIcon(0, 0);
				}
			}
		} else if(meta==3 || meta==7) {
			if(side==0) {
				if(bottomB) {
					return bottomI;
				} else {
					return bottomBl.getIcon(0, 0);
				}
			} else if(side==1) {
				if(topB) {
					return topI;
				} else {
					return topBl.getIcon(0, 0);
				}
			} else if(side==2) {
				if(leftB) {
					return leftI;
				} else {
					return leftBl.getIcon(0, 0);
				}
			} else if(side==3) {
				if(rightB) {
					return rightI;
				} else {
					return rightBl.getIcon(0, 0);
				}
			} else if(side==4) {
				if(backB) {
					return backI;
				} else {
					return backBl.getIcon(0, 0);
				}
			} else if(side==5) {
				if(frontB) {
					return frontI;
				} else {
					return frontBl.getIcon(0, 0);
				}
			}
		}
		return blockIcon;
	}
    
    public int quantityDropped(Random random) {
        return 1;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return null;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		if(this.is_directional) {
			int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			world.setBlockMetadataWithNotify(x, y, z, meta, 2);
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return guiID == null ? onActivate(world,x,y,z,player) : onActivateGui(world,x,y,z,player);
	}

	public boolean canActivate(World world, int x, int y, int z, EntityPlayer player) {
		return true;
	}

	public boolean onActivate(World world, int x, int y, int z, EntityPlayer player) {
		if(!canActivate(world,x,y,z,player)) {
			return true;
		}

		ItemStack is = player.getCurrentEquippedItem();

		if(Config.USE_WANDS) {
			if(IBHelper.isWand(is)) {
				if(BuildHelper.getBlock(world,x, y, z) != ModBlocks.ibStatue && BuildHelper.getBlock(world,x, y, z) != ModBlocks.ibLava && BuildHelper.getBlock(world,x, y, z) != ModBlocks.ibWater) {
					is.damageItem(1, player);
				}
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}

		build(world, x, y, z);
		build(world, x, y, z, player);

		IBHelper.keepBlocks(world, x, y, z, this.block);
		IBHelper.xp(world, player, Config.XP_AMOUNT);

		IBHelper.sound(world, Config.SOUND, x, y, z);
		IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
		IBHelper.msg(player, this.createMsg, Colors.a);

		return true;
	}

	public boolean onActivateGui(World world, int x, int y, int z, EntityPlayer player) {
		if(!canActivate(world,x,y,z,player)) {
			return true;
		}

		ItemStack is = player.getCurrentEquippedItem();

		if(Config.USE_WANDS) {
			if(!IBHelper.isWand(is)) {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}

		player.openGui(InstantBlocks.instance, this.guiID.ordinal(), world, x, y, z);

		return true;
	}
	
	public void build(World world, int x, int y, int z) {
		//build structure
	}
	
	public void build(World world, int x, int y, int z, EntityPlayer player) {
		//build structure
	}
}
