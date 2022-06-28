package com.slymask3.instantblocks.block.instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;
import com.slymask3.instantblocks.util.BuildHelper;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;

public class BlockInstantStatue extends BlockInstant {
	public BlockInstantStatue() {
		super(Names.Blocks.IB_STATUE, Material.wood, Block.soundTypeWood, 1.5F);
        setBlockTextureName(Textures.Statue.FRONT);
		setGuiID(GuiID.STATUE);
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
		if(meta==2 || meta==6) {
			if(side==0) {
				return bottom;
			} else if(side==1) {
				return top;
			} else if(side==2) {
				return front;
			} else if(side==3) {
				return back;
			} else if(side==4) {
				return sidel;
			} else if(side==5) {
				return sider;
			}
		} else if(meta==0 || meta==4) {
			if(side==0) {
				return bottom;
			} else if(side==1) {
				return top;
			} else if(side==2) {
				return back;
			} else if(side==3) {
				return front;
			} else if(side==4) {
				return sider;
			} else if(side==5) {
				return sidel;
			}
		} else if(meta==1 || meta==5) {
			if(side==0) {
				return bottom;
			} else if(side==1) {
				return top;
			} else if(side==2) {
				return sider;
			} else if(side==3) {
				return sidel;
			} else if(side==4) {
				return front;
			} else if(side==5) {
				return back;
			}
		} else if(meta==3 || meta==7) {
			if(side==0) {
				return bottom;
			} else if(side==1) {
				return top;
			} else if(side==2) {
				return sidel;
			} else if(side==3) {
				return sider;
			} else if(side==4) {
				return back;
			} else if(side==5) {
				return front;
			}
		}
		return blockIcon;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityStatue();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}

	public Skin getSkin(String username) {
		if(!username.equalsIgnoreCase("")) {
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
				JsonObject skin_json = image_json.get("textures").getAsJsonObject().get("SKIN").getAsJsonObject();
				String image_url = skin_json.get("url").getAsString();

				Skin skin = new Skin(ImageIO.read(new URL(image_url)));
				if(skin_json.has("metadata")) {
					JsonObject metadata = skin_json.get("metadata").getAsJsonObject();
					if(metadata.has("model") && metadata.get("model").getAsString().equalsIgnoreCase("slim")) {
						skin.setSlim(true);
					}
				}

				return skin;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean build(World world, int x, int y, int z, EntityPlayer player, String username, boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight, boolean rgb) {
		int meta = world.getBlockMetadata(x,y,z);

		Skin skin = getSkin(username);
		if(skin != null) {
			BufferedImage img = skin.getImage();

			BuildHelper.setBlock(world,x, y, z, Blocks.air);

			buildHead(world, x, y, z, img, meta, head, rgb);
			buildBody(world, x, y, z, img, meta, body, rgb);
			buildLegs(world, x, y, z, img, meta, legLeft, legRight, rgb);

			if(skin.isSlim()) {
				buildArmsSlim(world, x, y, z, img, meta, armLeft, armRight, rgb);
			} else {
				buildArms(world, x, y, z, img, meta, armLeft, armRight, rgb);
			}

			return true;
		}
		return false;
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
		if(build) {
			//TOP
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
					BuildHelper.setColorBlockComplex(world, x, y+31, z, img, 8+xtimes, 0+ytimes, meta, -5+ytimes, -4+xtimes, rgb);
				}
			}

			//BOTTOM
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
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
		if(build) {
			//TOP
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //topbody is 8x4
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 20+xtimes, 16+ytimes, meta, -3+ytimes, -4+xtimes, rgb); //topbody starts at 20, 16
				} //topbody starts at x-4, y+23, z+3
			}

			//BOTTOM
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //botbody is 8x4
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 28+xtimes, 16+ytimes, meta, -ytimes, -4+xtimes, rgb); //botbody starts at 28, 16
				} //botbody starts at x-4, y+12, z
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightbody is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 16+xtimes, 20+ytimes, meta, -3+xtimes, -4, rgb); //rightbody starts at 16, 20
				} //rightbody starts at x-4, y+23, z-3
			}

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftbody is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 28+xtimes, 20+ytimes, meta, -xtimes, +3, rgb); //leftbody starts at 28, 20
				} //rightbody starts at x+3, y+23, z
			}

			//FRONT
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //body is 8x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 20+xtimes, 20+ytimes, meta, 0, -4+xtimes, rgb); //body starts at 20, 20
				} //body starts at x-4, y+23, z
			}

			//BACK
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //back is 8x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 32+xtimes, 20+ytimes, meta, -3, +3-xtimes, rgb); //back starts at 32, 20
				} //back starts at x+3, y+23, z-3
			}
		}
	}

	private static void buildArms(World world, int x, int y, int z, BufferedImage img, int meta, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			//LEFT ARM

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmleft is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 48+xtimes, 20+ytimes, meta, -xtimes, +4, rgb); //rightarmleft starts at 48, 20
				} //rightarmleft starts
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmright is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 40+xtimes, 20+ytimes, meta, -3+xtimes, +7, rgb); //rightarmright starts at 40, 20
				} //rightarmright starts
			}
		
			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderleft is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 44+xtimes, 16+ytimes, meta, -3+ytimes, +4+xtimes, rgb); //shoulderleft starts at 44, 16
				} //shoulderleft starts
			}
	
			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistleft is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 48+xtimes, 16+ytimes, meta, -3+ytimes, +4+xtimes, rgb); //fistleft starts at 44, 16
				} //fistleft starts
			}
			
			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmfront is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 44+xtimes, 20+ytimes, meta, 0, +7-xtimes, rgb); //rightarmfront starts at 44, 20 DONE?
				} //rightarmfront starts
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmback is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 52+xtimes, 20+ytimes, meta, -3, +4+xtimes, rgb); //rightarmback starts at 52, 20
				} //rightarmback starts 
			}
		}
		
		if(buildRight) {
			//RIGHT ARM

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmleft is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 48+xtimes, 20+ytimes, meta, -xtimes, -5, rgb); //leftarmleft starts at 48, 20
				} //leftarmleft starts
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmright is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 40+xtimes, 20+ytimes, meta, -3+xtimes, -8, rgb); //leftarmright starts at 40, 20
				} //leftarmright starts
			}
			
		

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderright is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 44+xtimes, 16+ytimes, meta, -3+ytimes, -5-xtimes, rgb); //shoulderright starts at 44, 16
				} //shoulderright starts
			}
	
			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistright is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 48+xtimes, 16+ytimes, meta, -3+ytimes, -5-xtimes, rgb); //fistright starts at 44, 16
				} //fistright starts
			}
			
			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmfront is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 44+xtimes, 20+ytimes, meta, 0, -8+xtimes, rgb); //leftarmfront starts at 44, 20
				} //leftarmfront starts
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmback is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 52+xtimes, 20+ytimes, meta, -3, -5-xtimes, rgb); //leftarmback starts at 52, 20
				} //leftarmback starts 
			}
		}
	}

	private static void buildArmsSlim(World world, int x, int y, int z, BufferedImage img, int meta, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmleft is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 47+xtimes, 20+ytimes, meta, -xtimes, +4, rgb);
				} //rightarmleft starts at 47, 20
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmright is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 40+xtimes, 20+ytimes, meta, -3+xtimes, +6, rgb);
				} //rightarmright starts at 40, 20
			}

			//TOP
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderleft is 3x4
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 44+xtimes, 16+ytimes, meta, -3+ytimes, +4+xtimes, rgb);
				} //shoulderleft starts at 44, 16
			}

			//BOTTOM
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistleft is 3x4
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 47+xtimes, 16+ytimes, meta, -3+ytimes, +4+xtimes, rgb);
				} //fistleft starts at 47, 16
			}

			//FRONT
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmfront is 3x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 44+xtimes, 20+ytimes, meta, 0, +6-xtimes, rgb);
				} //rightarmfront starts at 44, 20 DONE?
			}

			//BACK
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmback is 3x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 51+xtimes, 20+ytimes, meta, -3, +4+xtimes, rgb);
				} //rightarmback starts at 51, 20
			}
		}

		if(buildRight) {
			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmleft is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 48+xtimes, 20+ytimes, meta, -xtimes, -5, rgb);
				} //leftarmleft starts at 48, 20
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmright is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 40+xtimes, 20+ytimes, meta, -3+xtimes, -7, rgb);
				} //leftarmright starts at 40, 20
			}

			//TOP
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderright is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+23, z, img, 44+xtimes, 16+ytimes, meta, -3+ytimes, -5-xtimes, rgb);
				} //shoulderright starts at 44, 16
			}

			//BOTTOM
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistright is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+12, z, img, 47+xtimes, 16+ytimes, meta, -3+ytimes, -5-xtimes, rgb);
				} //fistright starts at 44, 16
			}

			//FRONT
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmfront is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 44+xtimes, 20+ytimes, meta, 0, -7+xtimes, rgb);
				} //leftarmfront starts at 44, 20
			}

			//BACK
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmback is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+23-ytimes, z, img, 51+xtimes, 20+ytimes, meta, -3, -5-xtimes, rgb);
				} //leftarmback starts at 52, 20
			}
		}
	}
	
	private static void buildLegs(World world, int x, int y, int z, BufferedImage img, int meta, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			//LEFT LEG

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //leftlegtop is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+11, z, img, 4+xtimes, 16+ytimes, meta, -3+ytimes, +xtimes, rgb); //leftlegtop starts at 4, 16
				} //leftlegtop starts at x, y+11, z-3
			}

			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //leftlegtop is 4x4
					BuildHelper.setColorBlockComplex(world, x, y, z, img, 8+xtimes, 16+ytimes, meta, -3+ytimes, +xtimes, rgb); //leftlegbot starts at 8, 16
				} //leftlegbot starts at x, y, z-3
			}

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegleft is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 8+xtimes, 20+ytimes, meta, -xtimes, +3, rgb); //leftlegleft starts at 8, 20
				} //leftlegleft starts at x+3, y+11, z
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegright is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 0+xtimes, 20+ytimes, meta, -3+xtimes, 0, rgb); //leftlegright starts at 0, 20
				} //leftlegright starts at x, y+11, z-3
			}

			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegfront is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 4+xtimes, 20+ytimes, meta, 0, +xtimes, rgb); //leftlegfront starts at 4, 20
				} //leftlegfront starts at x, y+11, z
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegback is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 12+xtimes, 20+ytimes, meta, -3, +3-xtimes, rgb); //leftlegback starts at 12, 20
				} //leftlegback starts at x+3, y+11, z+3
			}
		}

		if(buildRight) {
			//RIGHT LEG

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //rightlegtop is 4x4
					BuildHelper.setColorBlockComplex(world, x, y+11, z, img, 4+xtimes, 16+ytimes, meta, -3+ytimes, -4+xtimes, rgb); //rightlegtop starts at 4, 16
				} //rightlegtop starts at x-4, y+11, z-3
			}

			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //rightlegbot is 4x4
					BuildHelper.setColorBlockComplex(world, x, y, z, img, 8+xtimes, 16+ytimes, meta, -3+ytimes, -4+xtimes, rgb); //rightlegbot starts at 8, 16
				} //rightlegbot starts at x-4, y, z-3
			}

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegleft is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 8+xtimes, 20+ytimes, meta, -xtimes, -1, rgb); //rightlegleft starts at 8, 20
				} //rightlegleft starts at x+3, y+11, z
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegright is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 0+xtimes, 20+ytimes, meta, -3+xtimes, -4, rgb); //rightlegright starts at 0, 20
				} //rightlegright starts at x, y+11, z-3
			}

			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegfront is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 4+xtimes, 20+ytimes, meta, 0, -1-xtimes, rgb); //rightlegfront starts at 4, 20
				} //rightlegfront starts at x, y+11, z
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegback is 4x12
					BuildHelper.setColorBlockComplex(world, x, y+11-ytimes, z, img, 12+xtimes, 20+ytimes, meta, -3, -4+xtimes, rgb); //rightlegback starts at 12, 20
				} //rightlegback starts at x+3, y+11, z+3
			}
		}
	}

	public static class Skin {
		private final BufferedImage image;
		private boolean slim;
		public Skin(BufferedImage image) {
			this.image = image;
			this.slim = false;
		}
		public BufferedImage getImage() {
			return this.image;
		}
		public boolean isSlim() {
			return this.slim;
		}
		public void setSlim(boolean slim) {
			this.slim = slim;
		}
	}
}