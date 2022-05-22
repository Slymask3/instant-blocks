package com.slymask3.instantblocks.block.instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.*;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Random;

public class BlockInstantStatue extends BlockContainer implements ITileEntityProvider {

	public BlockInstantStatue() {
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName(Reference.MOD_ID + ":" + Names.Blocks.IB_STATUE);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
        setBlockTextureName(Textures.Statue.FRONT);
	}

	public int quantityDropped(Random random) {
		return 1;
	}

	public static IIcon bottom;
	public static IIcon top;
	public static IIcon sidel;
	public static IIcon sider;
	public static IIcon front;
	public static IIcon back;

	public void registerBlockIcons(IIconRegister ir) {
		bottom = ir.registerIcon(Textures.Statue.BOTTOM);
		top = ir.registerIcon(Textures.Statue.TOP);
		sidel = ir.registerIcon(Textures.Statue.SIDE_LEFT);
		sider = ir.registerIcon(Textures.Statue.SIDE_RIGHT);
		front = ir.registerIcon(Textures.Statue.FRONT);
		back = ir.registerIcon(Textures.Statue.BACK);
	}

	public IIcon getIcon(int side, int meta) {
		if (meta==2 || meta==6) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return front;
			} else if (side==3) {
				return back;
			} else if (side==4) {
				return sidel;
			} else if (side==5) {
				return sider;
			}
		} else if (meta==0 || meta==4) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return back;
			} else if (side==3) {
				return front;
			} else if (side==4) {
				return sider;
			} else if (side==5) {
				return sidel;
			}
		} else if (meta==1 || meta==5) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return sider;
			} else if (side==3) {
				return sidel;
			} else if (side==4) {
				return front;
			} else if (side==5) {
				return back;
			}
		} else if (meta==3 || meta==7) {
			if (side==0) {
				return bottom;
			} else if (side==1) {
				return top;
			} else if (side==2) {
				return sidel;
			} else if (side==3) {
				return sider;
			} else if (side==4) {
				return back;
			} else if (side==5) {
				return front;
			}
		}
		return blockIcon;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityStatue();
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int meta = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		//TileEntityInstantStatue.tile = (TileEntityInstantStatue) world.getTileEntity(x, y, z);
		
		int meta = world.getBlockMetadata(x, y, z);
		//LogHelper.info("meta == "+meta);
		
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.useWands) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.wandReq, Colors.c);
				return true;
			}
		}
		
		player.openGui(InstantBlocks.instance, GuiID.STATUE.ordinal(), world, x, y, z);
		
		//build(world, x, y, z, player, meta);
		
		return true;
	}

	public void build(World world, int x, int y, int z, String playerS, int meta, String username, boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight, boolean rgb) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		
		LogHelper.info(player);
		
		if (!username.equalsIgnoreCase("")) {
			try {
				GsonBuilder builder = new GsonBuilder();
				builder.setPrettyPrinting();
				Gson gson = builder.create();

				String user_api_contents = get_contents("https://api.mojang.com/users/profiles/minecraft/"+username);
				JsonObject user_json = gson.fromJson(user_api_contents,JsonObject.class);
				String uuid = user_json.get("id").getAsString();

				String uuid_api_contents = get_contents("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid);
				JsonObject uuid_json = gson.fromJson(uuid_api_contents,JsonObject.class);
				String base64 = uuid_json.get("properties").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();

				String base64_decoded = new String(Base64.getDecoder().decode(base64));
				JsonObject image_json = gson.fromJson(base64_decoded,JsonObject.class);
				String image_url = image_json.get("textures").getAsJsonObject().get("SKIN").getAsJsonObject().get("url").getAsString();

				BufferedImage img = ImageIO.read(new URL(image_url));

				world.setBlock(x, y, z, Blocks.air);
		        
		        buildHead(world, x, y, z, img, meta, head, rgb);
		        buildBody(world, x, y, z, img, meta, body, rgb);
		        buildArms(world, x, y, z, img, meta, armLeft, armRight, rgb);
		        buildLegs(world, x, y, z, img, meta, legLeft, legRight, rgb);
		        
		        //BuildHelper.buildColorBlock(world, x-5, y, z-2, 5, 3, 1, 0xFF0000); //WALL
		        
		        //LogHelper.info(ColorHelper.getColorAt(img, 0, 0));
		        
		        IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibStatue);
		        //IBHelper.xp(world, player, ConfigurationHandler.xp);
		        IBHelper.sound(world, Config.sound, x, y, z);
		        //IBHelper.effectFull(world, "reddust", x, y, z);
		        //IBHelper.msg(player, Colors.a + "Instant Statue created of the player '" + username + "'.", Colors.a);
				
				ItemStack is = player.getCurrentEquippedItem();
				
				if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
					is.damageItem(1, player);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				IBHelper.msg(player, Colors.c + "The minecraft username '" + username + Colors.c + "' does not have a skin.", Colors.c);
			}
		}
	}

	private static String get_contents(String url_string) {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(url_string);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	private static void buildHead(World world, int x, int y, int z, BufferedImage img, int meta, boolean build, boolean rgb) {
		if (build) {
		//TOP
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<8; ytimes++) {
				//BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+31, z-5+ytimes, img, 8+xtimes, 0+ytimes);
				BuildHelper.setColorBlockComplex(world, x, y+31, z, img, 8+xtimes, 0+ytimes, meta, -5+ytimes, -4+xtimes, rgb);
			}
		}

		//BOTTOM
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<8; ytimes++) {
				//BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+24, z-5+ytimes, img, 16+xtimes, 0+ytimes);
				BuildHelper.setColorBlockComplex(world, x, y+24, z, img, 16+xtimes, 0+ytimes, meta, -5+ytimes, -4+xtimes, rgb);
			}
		}
		
		//RIGHT
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<8; ytimes++) {
				BuildHelper.setColorBlockComplex(world, x, y+31-ytimes, z, img, 0+xtimes, 8+ytimes, meta, -5+xtimes, -4, rgb);
			}
		}
		
		//LEFT
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<8; ytimes++) {
				BuildHelper.setColorBlockComplex(world, x, y+31-ytimes, z, img, 16+xtimes, 8+ytimes, meta, +2-xtimes, +3, rgb);
			}
		}

		//FRONT
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<8; ytimes++) { //face is 8x8, so 8 total times
				BuildHelper.setColorBlockComplex(world, x, y+31-ytimes, z, img, 8+xtimes, 8+ytimes, meta, +2, -4+xtimes, rgb); //face starts at 8, 8
			} //face starts x-4, y+31, z+2
		}
		
		//BACK
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<8; ytimes++) {
				BuildHelper.setColorBlockComplex(world, x, y+31-ytimes, z, img, 24+xtimes, 8+ytimes, meta, -5, +3-xtimes, rgb);
			}
		}
		}
				
	}

	private static void buildBody(World world, int x, int y, int z, BufferedImage img, int meta, boolean build, boolean rgb) {
		if (build) {
		//TOP
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<4; ytimes++) { //topbody is 8x4
				//BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+23, z-3+ytimes, img, 20+xtimes, 16+ytimes); //topbody starts at 20, 16
				BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 20+xtimes, 16+ytimes, meta, -3+ytimes, -4+xtimes, rgb); //topbody starts at 20, 16
			} //topbody starts at x-4, y+23, z+3
		}

		//BOTTOM
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<4; ytimes++) { //botbody is 8x4
				//BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+12, z-ytimes, img, 28+xtimes, 16+ytimes); //botbody starts at 28, 16
				BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 28+xtimes, 16+ytimes, meta, -ytimes, -4+xtimes, rgb); //botbody starts at 28, 16
			} //botbody starts at x-4, y+12, z
		}
		
		//RIGHT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //rightbody is 4x12
				//BuildHelper.setColorBlockComplex(world, x-4, y+23-ytimes, z-3+xtimes, img, 16+xtimes, 20+ytimes); //rightbody starts at 16, 20
				BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 16+xtimes, 20+ytimes, meta, -3+xtimes, -4, rgb); //rightbody starts at 16, 20
			} //rightbody starts at x-4, y+23, z-3
		}

		//LEFT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //leftbody is 4x12
				//BuildHelper.setColorBlockComplex(world, x+3, y+23-ytimes, z-xtimes, img, 28+xtimes, 20+ytimes); //leftbody starts at 28, 20
				BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 28+xtimes, 20+ytimes, meta, -xtimes, +3, rgb); //leftbody starts at 28, 20
			} //rightbody starts at x+3, y+23, z
		}
		
		//FRONT
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //body is 8x12
				//BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+23-ytimes, z, img, 20+xtimes, 20+ytimes); //body starts at 20, 20
				BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 20+xtimes, 20+ytimes, meta, 0, -4+xtimes, rgb); //body starts at 20, 20
			} //body starts at x-4, y+23, z
		}

		//BACK
		for(int xtimes=0; xtimes<8; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //back is 8x12
				//BuildHelper.setColorBlockComplex(world, x+3-xtimes, y+23-ytimes, z-3, img, 32+xtimes, 20+ytimes); //back starts at 32, 20
				BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 32+xtimes, 20+ytimes, meta, -3, +3-xtimes, rgb); //back starts at 32, 20
			} //back starts at x+3, y+23, z-3
		}
		}
	}

	private static void buildArms(World world, int x, int y, int z, BufferedImage img, int meta, boolean buildLeft, boolean buildRight, boolean rgb) {
		if (buildLeft) {
			//LEFT ARM

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmleft is 4x12
					//BuildHelper.setColorBlockComplex(world, x+4, y+23-ytimes, z-xtimes, img, 48+xtimes, 20+ytimes); //rightarmleft starts at 48, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 48+xtimes, 20+ytimes, meta, -xtimes, +4, rgb); //rightarmleft starts at 48, 20
				} //rightarmleft starts
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmright is 4x12
					//BuildHelper.setColorBlockComplex(world, x+7, y+23-ytimes, z-3+xtimes, img, 40+xtimes, 20+ytimes); //rightarmright starts at 40, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 40+xtimes, 20+ytimes, meta, -3+xtimes, +7, rgb); //rightarmright starts at 40, 20
				} //rightarmright starts
			}
		
			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderleft is 4x4
					//BuildHelper.setColorBlockComplex(world, x+4+xtimes, y+23, z-3+ytimes, img, 44+xtimes, 16+ytimes); //shoulderleft starts at 44, 16
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 44+xtimes, 16+ytimes, meta, -3+ytimes, +4+xtimes, rgb); //shoulderleft starts at 44, 16
				} //shoulderleft starts
			}
	
			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistleft is 4x4
					//BuildHelper.setColorBlockComplex(world, x+4+xtimes, y+12, z-3+ytimes, img, 48+xtimes, 16+ytimes); //fistleft starts at 44, 16
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 48+xtimes, 16+ytimes, meta, -3+ytimes, +4+xtimes, rgb); //fistleft starts at 44, 16
				} //fistleft starts
			}
			
			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmfront is 4x12
					//BuildHelper.setColorBlockComplex(world, x+7-xtimes, y+23-ytimes, z, img, 44+xtimes, 20+ytimes); //rightarmfront starts at 44, 20 DONE?
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 44+xtimes, 20+ytimes, meta, 0, +7-xtimes, rgb); //rightarmfront starts at 44, 20 DONE?
				} //rightarmfront starts
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmback is 4x12
					//BuildHelper.setColorBlockComplex(world, x+4+xtimes, y+23-ytimes, z-3, img, 52+xtimes, 20+ytimes); //rightarmback starts at 52, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 52+xtimes, 20+ytimes, meta, -3, +4+xtimes, rgb); //rightarmback starts at 52, 20
				} //rightarmback starts 
			}
		}
		
		if (buildRight) {
			//RIGHT ARM

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmleft is 4x12
					//BuildHelper.setColorBlockComplex(world, x-5, y+23-ytimes, z-xtimes, img, 48+xtimes, 20+ytimes); //leftarmleft starts at 48, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 48+xtimes, 20+ytimes, meta, -xtimes, -5, rgb); //leftarmleft starts at 48, 20
				} //leftarmleft starts
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmright is 4x12
					//BuildHelper.setColorBlockComplex(world, x-8, y+23-ytimes, z-3+xtimes, img, 40+xtimes, 20+ytimes); //leftarmright starts at 40, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 40+xtimes, 20+ytimes, meta, -3+xtimes, -8, rgb); //leftarmright starts at 40, 20
				} //leftarmright starts
			}
			
		

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderright is 4x4
					//BuildHelper.setColorBlockComplex(world, x-5-xtimes, y+23, z-3+ytimes, img, 44+xtimes, 16+ytimes); //shoulderright starts at 44, 16
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 44+xtimes, 16+ytimes, meta, -3+ytimes, -5-xtimes, rgb); //shoulderright starts at 44, 16
				} //shoulderright starts
			}
	
			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistright is 4x4
					//BuildHelper.setColorBlockComplex(world, x-5-xtimes, y+12, z-3+ytimes, img, 48+xtimes, 16+ytimes); //fistright starts at 44, 16
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 48+xtimes, 16+ytimes, meta, -3+ytimes, -5-xtimes, rgb); //fistright starts at 44, 16
				} //fistright starts
			}
			
			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmfront is 4x12
					//BuildHelper.setColorBlockComplex(world, x-8+xtimes, y+23-ytimes, z, img, 44+xtimes, 20+ytimes); //leftarmfront starts at 44, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 44+xtimes, 20+ytimes, meta, 0, -8+xtimes, rgb); //leftarmfront starts at 44, 20
				} //leftarmfront starts
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmback is 4x12
					//BuildHelper.setColorBlockComplex(world, x-5-xtimes, y+23-ytimes, z-3, img, 52+xtimes, 20+ytimes); //leftarmback starts at 52, 20
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 52+xtimes, 20+ytimes, meta, -3, -5-xtimes, rgb); //leftarmback starts at 52, 20
				} //leftarmback starts 
			}
		}
	}
	
	private static void buildLegs(World world, int x, int y, int z, BufferedImage img, int meta, boolean buildLeft, boolean buildRight, boolean rgb) {
		if (buildLeft) {
		//LEFT LEG

		//TOP
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<4; ytimes++) { //leftlegtop is 4x4
//					BuildHelper.setColorBlockComplex(world, x+xtimes, y+11, z-3+ytimes, img, 4+xtimes, 16+ytimes); //leftlegtop starts at 4, 16
				BuildHelper.setColorBlockComplex(world, x, y+11, z, img, 4+xtimes, 16+ytimes, meta, -3+ytimes, +xtimes, rgb); //leftlegtop starts at 4, 16
			} //leftlegtop starts at x, y+11, z-3
		}

		//BOTTOM
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<4; ytimes++) { //leftlegtop is 4x4
//					BuildHelper.setColorBlockComplex(world, x+xtimes, y, z-3+ytimes, img, 8+xtimes, 16+ytimes); //leftlegbot starts at 8, 16
				BuildHelper.setColorBlockComplex(world, x, y, z, img, 8+xtimes, 16+ytimes, meta, -3+ytimes, +xtimes, rgb); //leftlegbot starts at 8, 16
			} //leftlegbot starts at x, y, z-3
		}

		//LEFT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //leftlegleft is 4x12
//					BuildHelper.setColorBlockComplex(world, x+3, y+11-ytimes, z-xtimes, img, 8+xtimes, 20+ytimes); //leftlegleft starts at 8, 20
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 8+xtimes, 20+ytimes, meta, -xtimes, +3, rgb); //leftlegleft starts at 8, 20
			} //leftlegleft starts at x+3, y+11, z
		}

		//RIGHT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //leftlegright is 4x12
//					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z-3+xtimes, img, 0+xtimes, 20+ytimes); //leftlegright starts at 0, 20
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 0+xtimes, 20+ytimes, meta, -3+xtimes, 0, rgb); //leftlegright starts at 0, 20
			} //leftlegright starts at x, y+11, z-3
		}
		
		//FRONT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //leftlegfront is 4x12
//					BuildHelper.setColorBlockComplex(world, x+xtimes, y+11-ytimes, z, img, 4+xtimes, 20+ytimes); //leftlegfront starts at 4, 20 blaze it
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 4+xtimes, 20+ytimes, meta, 0, +xtimes, rgb); //leftlegfront starts at 4, 20 blaze it
			} //leftlegfront starts at x, y+11, z
		}

		//BACK
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //leftlegback is 4x12
//					BuildHelper.setColorBlockComplex(world, x+3-xtimes, y+11-ytimes, z-3, img, 12+xtimes, 20+ytimes); //leftlegback starts at 12, 20
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 12+xtimes, 20+ytimes, meta, -3, +3-xtimes, rgb); //leftlegback starts at 12, 20
			} //leftlegback starts at x+3, y+11, z+3
		}
		}

		if (buildRight) {
		//RIGHT LEG
 
		//TOP
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<4; ytimes++) { //rightlegtop is 4x4
//					BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+11, z-3+ytimes, img, 4+xtimes, 16+ytimes); //rightlegtop starts at 4, 16
				BuildHelper.setColorBlockComplex(world, x, y+11, z, img, 4+xtimes, 16+ytimes, meta, -3+ytimes, -4+xtimes, rgb); //rightlegtop starts at 4, 16
			} //rightlegtop starts at x-4, y+11, z-3
		}

		//BOTTOM
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<4; ytimes++) { //rightlegbot is 4x4
//					BuildHelper.setColorBlockComplex(world, x-4+xtimes, y, z-3+ytimes, img, 8+xtimes, 16+ytimes); //rightlegbot starts at 8, 16
				BuildHelper.setColorBlockComplex(world, x, y, z, img, 8+xtimes, 16+ytimes, meta, -3+ytimes, -4+xtimes, rgb); //rightlegbot starts at 8, 16
			} //rightlegbot starts at x-4, y, z-3
		}

		//LEFT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //rightlegleft is 4x12
//					BuildHelper.setColorBlockComplex(world, x-1, y+11-ytimes, z-xtimes, img, 8+xtimes, 20+ytimes); //rightlegleft starts at 8, 20
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 8+xtimes, 20+ytimes, meta, -xtimes, -1, rgb); //rightlegleft starts at 8, 20
			} //rightlegleft starts at x+3, y+11, z
		}

		//RIGHT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //rightlegright is 4x12
//					BuildHelper.setColorBlockComplex(world, x-4, y+11-ytimes, z-3+xtimes, img, 0+xtimes, 20+ytimes); //rightlegright starts at 0, 20
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 0+xtimes, 20+ytimes, meta, -3+xtimes, -4, rgb); //rightlegright starts at 0, 20
			} //rightlegright starts at x, y+11, z-3
		}
		
		//FRONT
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //rightlegfront is 4x12
//					BuildHelper.setColorBlockComplex(world, x-1-xtimes, y+11-ytimes, z, img, 4+xtimes, 20+ytimes); //rightlegfront starts at 4, 20 blaze it
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 4+xtimes, 20+ytimes, meta, 0, -1-xtimes, rgb); //rightlegfront starts at 4, 20 blaze it
			} //rightlegfront starts at x, y+11, z
		}
 
		//BACK
		for(int xtimes=0; xtimes<4; xtimes++) {
			for(int ytimes=0; ytimes<12; ytimes++) { //rightlegback is 4x12
//					BuildHelper.setColorBlockComplex(world, x-4+xtimes, y+11-ytimes, z-3, img, 12+xtimes, 20+ytimes); //rightlegback starts at 12, 20
				BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 12+xtimes, 20+ytimes, meta, -3, -4+xtimes, rgb); //rightlegback starts at 12, 20
			} //rightlegback starts at x+3, y+11, z+3
		}
		}
	}
}