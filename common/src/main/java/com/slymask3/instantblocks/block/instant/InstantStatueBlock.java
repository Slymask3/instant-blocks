package com.slymask3.instantblocks.block.instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.StatueBlockEntity;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;

public class InstantStatueBlock extends InstantBlock implements EntityBlock {
	public InstantStatueBlock() {
		super(Properties.of(Material.WOOD)
				.strength(1.5F)
				.sound(SoundType.WOOD)
		);
		setScreen(ClientHelper.Screen.STATUE);
		setDirectional(true);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_STATUE();
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StatueBlockEntity(pos,state);
	}

	public Skin getSkin(String username) {
		if(!username.equalsIgnoreCase("")) {
			return getSkinFromCache(username);
		}
		return null;
	}

	private Skin getSkinFromCache(String username) {
		try {
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();

			String cache_file_name = Common.STATUE_CACHE_DIR + "/statue-skins.json";
			File cache_file = new File(cache_file_name);
			JsonArray cache_json;
			if(cache_file.exists()) {
				cache_json = gson.fromJson(Files.readString(cache_file.toPath()),JsonArray.class);
			} else {
				cache_json = new JsonArray();
			}

			int cache_index = -1;
			for(int i=0; i<cache_json.size(); i++) {
				JsonObject obj = cache_json.get(i).getAsJsonObject();
				if(obj.get("name").getAsString().equalsIgnoreCase(username)) {
					cache_index = i;
					break;
				}
			}
			if(cache_index == -1) {
				JsonObject obj = new JsonObject();
				obj.addProperty("name", username);
				obj.addProperty("uuid", "");
				obj.addProperty("skin", "");
				obj.addProperty("slim", false);
				obj.addProperty("timestamp", 0);
				cache_json.add(obj);
				cache_index = cache_json.size() - 1;
			}

			JsonObject cache_user = cache_json.get(cache_index).getAsJsonObject();
			long timestamp = cache_user.get("timestamp").getAsLong();
			String cache_skin_url = cache_user.get("skin").getAsString();
			String cache_name = cache_user.get("name").getAsString();
			String cache_uuid = cache_user.get("uuid").getAsString();
			boolean cache_slim = cache_user.get("slim").getAsBoolean();

			if(cache_skin_url.isEmpty() || System.currentTimeMillis() - timestamp >= (long) Common.CONFIG.STATUE_CACHE_TIME() * 60 * 1000) {
				Skin skin;
				if(cache_uuid.isEmpty()) {
					skin = getSkinFromAPI(username);
				} else {
					skin = getSkinFromAPIUUID(cache_uuid);
				}
				if(skin != null) {
					JsonObject new_user = new JsonObject();
					new_user.addProperty("name", skin.getName());
					new_user.addProperty("uuid", skin.getUUID());
					new_user.addProperty("skin", skin.getImageURL());
					new_user.addProperty("slim", skin.isSlim());
					new_user.addProperty("timestamp", System.currentTimeMillis());

					cache_json.set(cache_index,new_user);

					try(FileWriter file = new FileWriter(cache_file_name)) {
						gson.toJson(cache_json,file);
					}
				}
				return skin;
			} else {
				return new Skin(cache_uuid,cache_name,cache_skin_url,cache_slim);
			}
		} catch(Exception e) {
			Common.LOG.error(e.getMessage());
		}
		return null;
	}

	private Skin getSkinFromAPI(String username) {
		try {
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();

			String user_api_contents = Helper.get_url_contents("https://api.mojang.com/users/profiles/minecraft/"+username);
			JsonObject user_json = gson.fromJson(user_api_contents,JsonObject.class);
			String uuid = user_json.get("id").getAsString();

			return getSkinFromAPIUUID(uuid);
		} catch(Exception e) {
			Common.LOG.error(e.getMessage());
		}
		return null;
	}

	private Skin getSkinFromAPIUUID(String uuid) {
		try {
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();

			String uuid_api_contents = Helper.get_url_contents("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid);
			JsonObject uuid_json = gson.fromJson(uuid_api_contents,JsonObject.class);
			String name = uuid_json.get("name").getAsString();
			String base64 = uuid_json.get("properties").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();

			String base64_decoded = new String(Base64.getDecoder().decode(base64));
			JsonObject image_json = gson.fromJson(base64_decoded,JsonObject.class);
			JsonObject skin_json = image_json.get("textures").getAsJsonObject().get("SKIN").getAsJsonObject();
			String image_url = skin_json.get("url").getAsString();

			Skin skin = new Skin(uuid,name,image_url);
			if(skin_json.has("metadata")) {
				JsonObject metadata = skin_json.get("metadata").getAsJsonObject();
				if(metadata.has("model") && metadata.get("model").getAsString().equalsIgnoreCase("slim")) {
					skin.setSlim(true);
				}
			}

			return skin;
		} catch(Exception e) {
			Common.LOG.error(e.getMessage());
		}
		return null;
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = Builder.setup(world,x,y,z).setSpeed(2).setDirection(Direction.UP);
		
		StatueBlockEntity blockEntity = (StatueBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		Skin skin = getSkin(blockEntity.username);
		if(skin != null) {
			BufferedImage img = skin.getImage();

			Single.setup(builder,world,x,y,z).setBlock(Blocks.AIR).queue();

			buildHead(builder, world, x, y, z, img, direction, blockEntity.head, blockEntity.rgb);
			buildBody(builder, world, x, y, z, img, direction, blockEntity.body, blockEntity.rgb);
			buildLegs(builder, world, x, y, z, img, direction, blockEntity.legLeft, blockEntity.legRight, blockEntity.rgb);

			if(skin.isSlim()) {
				buildArmsSlim(builder, world, x, y, z, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
			} else {
				buildArms(builder, world, x, y, z, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
			}

			builder.build();

			setCreateMessage(Strings.CREATE_STATUE, skin.getName());
			return true;
		} else {
			Helper.sendMessage(player, Strings.ERROR_STATUE, ChatFormatting.RED + blockEntity.username);
		}
		return false;
	}

	private static void buildHead(Builder builder, Level world, int x, int y, int z, BufferedImage img, Direction direction, boolean build, boolean rgb) {
		if(build) {
			//TOP
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
					Single.setup(builder,world,x,y+31,z).offset(direction,-5+ytimes,-4+xtimes).setImageColor(img,8+xtimes, ytimes,rgb).queue();
				}
			}

			//BOTTOM
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
					Single.setup(builder,world,x,y+24,z).offset(direction,-5+ytimes,-4+xtimes).setImageColor(img,16+xtimes, ytimes,rgb).queue();
				}
			}

			//RIGHT
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
					Single.setup(builder,world,x,y+31-ytimes,z).offset(direction,-5+xtimes,-4).setImageColor(img, xtimes,8+ytimes,rgb).queue();
				}
			}

			//LEFT
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
					Single.setup(builder,world,x,y+31-ytimes,z).offset(direction, 2 -xtimes,+3).setImageColor(img,16+xtimes,8+ytimes,rgb).queue();
				}
			}

			//FRONT
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) { //face is 8x8, so 8 total times
					Single.setup(builder,world,x,y+31-ytimes,z).offset(direction,+2,-4+xtimes).setImageColor(img,8+xtimes,8+ytimes,rgb).queue(); //face starts at 8, 8
				} //face starts x-4, y+31, z+2
			}

			//BACK
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<8; ytimes++) {
					Single.setup(builder,world,x,y+31-ytimes,z).offset(direction,-5, 3 -xtimes).setImageColor(img,24+xtimes,8+ytimes,rgb).queue();
				}
			}
		}
	}

	private static void buildBody(Builder builder, Level world, int x, int y, int z, BufferedImage img, Direction direction, boolean build, boolean rgb) {
		if(build) {
			//TOP
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //topbody is 8x4
					Single.setup(builder,world,x,y+23,z).offset(direction,-3+ytimes,-4+xtimes).setImageColor(img,20+xtimes,16+ytimes,rgb).queue(); //topbody starts at 20, 16
				} //topbody starts at x-4, y+23, z+3
			}

			//BOTTOM
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //botbody is 8x4
					Single.setup(builder,world,x,y+12,z).offset(direction,-ytimes,-4+xtimes).setImageColor(img,28+xtimes,16+ytimes,rgb).queue(); //botbody starts at 28, 16
				} //botbody starts at x-4, y+12, z
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightbody is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3+xtimes,-4).setImageColor(img,16+xtimes,20+ytimes,rgb).queue(); //rightbody starts at 16, 20
				} //rightbody starts at x-4, y+23, z-3
			}

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftbody is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-xtimes,+3).setImageColor(img,28+xtimes,20+ytimes,rgb).queue(); //leftbody starts at 28, 20
				} //rightbody starts at x+3, y+23, z
			}

			//FRONT
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //body is 8x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,0,-4+xtimes).setImageColor(img,20+xtimes,20+ytimes,rgb).queue(); //body starts at 20, 20
				} //body starts at x-4, y+23, z
			}

			//BACK
			for(int xtimes=0; xtimes<8; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //back is 8x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3, 3 -xtimes).setImageColor(img,32+xtimes,20+ytimes,rgb).queue(); //back starts at 32, 20
				} //back starts at x+3, y+23, z-3
			}
		}
	}

	private static void buildArms(Builder builder, Level world, int x, int y, int z, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			//LEFT ARM

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmleft is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-xtimes,+4).setImageColor(img,48+xtimes,20+ytimes,rgb).queue(); //rightarmleft starts at 48, 20
				} //rightarmleft starts
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmright is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3+xtimes,+7).setImageColor(img,40+xtimes,20+ytimes,rgb).queue(); //rightarmright starts at 40, 20
				} //rightarmright starts
			}
		
			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderleft is 4x4
					Single.setup(builder,world,x,y+23,z).offset(direction,-3+ytimes, 4 +xtimes).setImageColor(img,44+xtimes,16+ytimes,rgb).queue(); //shoulderleft starts at 44, 16
				} //shoulderleft starts
			}
	
			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistleft is 4x4
					Single.setup(builder,world,x,y+12,z).offset(direction,-3+ytimes, 4 +xtimes).setImageColor(img,48+xtimes,16+ytimes,rgb).queue(); //fistleft starts at 44, 16
				} //fistleft starts
			}
			
			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmfront is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,0, 7 -xtimes).setImageColor(img,44+xtimes,20+ytimes,rgb).queue(); //rightarmfront starts at 44, 20 DONE?
				} //rightarmfront starts
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmback is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3, 4 +xtimes).setImageColor(img,52+xtimes,20+ytimes,rgb).queue(); //rightarmback starts at 52, 20
				} //rightarmback starts 
			}
		}
		
		if(buildRight) {
			//RIGHT ARM

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmleft is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-xtimes,-5).setImageColor(img,48+xtimes,20+ytimes,rgb).queue(); //leftarmleft starts at 48, 20
				} //leftarmleft starts
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmright is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3+xtimes,-8).setImageColor(img,40+xtimes,20+ytimes,rgb).queue(); //leftarmright starts at 40, 20
				} //leftarmright starts
			}
			
		

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderright is 4x4
					Single.setup(builder,world,x,y+23,z).offset(direction,-3+ytimes,-5-xtimes).setImageColor(img,44+xtimes,16+ytimes,rgb).queue(); //shoulderright starts at 44, 16
				} //shoulderright starts
			}
	
			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistright is 4x4
					Single.setup(builder,world,x,y+12,z).offset(direction,-3+ytimes,-5-xtimes).setImageColor(img,48+xtimes,16+ytimes,rgb).queue(); //fistright starts at 44, 16
				} //fistright starts
			}
			
			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmfront is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,0,-8+xtimes).setImageColor(img,44+xtimes,20+ytimes,rgb).queue(); //leftarmfront starts at 44, 20
				} //leftarmfront starts
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmback is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3,-5-xtimes).setImageColor(img,52+xtimes,20+ytimes,rgb).queue(); //leftarmback starts at 52, 20
				} //leftarmback starts 
			}
		}
	}

	private static void buildArmsSlim(Builder builder, Level world, int x, int y, int z, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmleft is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-xtimes,+4).setImageColor(img,47+xtimes,20+ytimes,rgb).queue();
				} //rightarmleft starts at 47, 20
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmright is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3+xtimes,+6).setImageColor(img,40+xtimes,20+ytimes,rgb).queue();
				} //rightarmright starts at 40, 20
			}

			//TOP
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderleft is 3x4
					Single.setup(builder,world,x,y+23,z).offset(direction,-3+ytimes, 4 +xtimes).setImageColor(img,44+xtimes,16+ytimes,rgb).queue();
				} //shoulderleft starts at 44, 16
			}

			//BOTTOM
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistleft is 3x4
					Single.setup(builder,world,x,y+12,z).offset(direction,-3+ytimes, 4 +xtimes).setImageColor(img,47+xtimes,16+ytimes,rgb).queue();
				} //fistleft starts at 47, 16
			}

			//FRONT
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmfront is 3x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,0, 6 -xtimes).setImageColor(img,44+xtimes,20+ytimes,rgb).queue();
				} //rightarmfront starts at 44, 20 DONE?
			}

			//BACK
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightarmback is 3x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3, 4 +xtimes).setImageColor(img,51+xtimes,20+ytimes,rgb).queue();
				} //rightarmback starts at 51, 20
			}
		}

		if(buildRight) {
			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmleft is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-xtimes,-5).setImageColor(img,48+xtimes,20+ytimes,rgb).queue();
				} //leftarmleft starts at 48, 20
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmright is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3+xtimes,-7).setImageColor(img,40+xtimes,20+ytimes,rgb).queue();
				} //leftarmright starts at 40, 20
			}

			//TOP
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //shoulderright is 4x4
					Single.setup(builder,world,x,y+23,z).offset(direction,-3+ytimes,-5-xtimes).setImageColor(img,44+xtimes,16+ytimes,rgb).queue();
				} //shoulderright starts at 44, 16
			}

			//BOTTOM
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //fistright is 4x4
					Single.setup(builder,world,x,y+12,z).offset(direction,-3+ytimes,-5-xtimes).setImageColor(img,47+xtimes,16+ytimes,rgb).queue();
				} //fistright starts at 44, 16
			}

			//FRONT
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmfront is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,0,-7+xtimes).setImageColor(img,44+xtimes,20+ytimes,rgb).queue();
				} //leftarmfront starts at 44, 20
			}

			//BACK
			for(int xtimes=0; xtimes<3; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftarmback is 4x12
					Single.setup(builder,world,x,y+23-ytimes,z).offset(direction,-3,-5-xtimes).setImageColor(img,51+xtimes,20+ytimes,rgb).queue();
				} //leftarmback starts at 52, 20
			}
		}
	}
	
	private static void buildLegs(Builder builder, Level world, int x, int y, int z, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			//LEFT LEG

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //leftlegtop is 4x4
					Single.setup(builder,world,x,y+11,z).offset(direction,-3+ytimes,+xtimes).setImageColor(img,4+xtimes,16+ytimes,rgb).queue(); //leftlegtop starts at 4, 16
				} //leftlegtop starts at x, y+11, z-3
			}

			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //leftlegtop is 4x4
					Single.setup(builder,world,x,y,z).offset(direction,-3+ytimes,+xtimes).setImageColor(img,8+xtimes,16+ytimes,rgb).queue(); //leftlegbot starts at 8, 16
				} //leftlegbot starts at x, y, z-3
			}

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegleft is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,-xtimes,+3).setImageColor(img,8+xtimes,20+ytimes,rgb).queue(); //leftlegleft starts at 8, 20
				} //leftlegleft starts at x+3, y+11, z
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegright is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,-3+xtimes,0).setImageColor(img, xtimes,20+ytimes,rgb).queue(); //leftlegright starts at 0, 20
				} //leftlegright starts at x, y+11, z-3
			}

			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegfront is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,0,+xtimes).setImageColor(img,4+xtimes,20+ytimes,rgb).queue(); //leftlegfront starts at 4, 20
				} //leftlegfront starts at x, y+11, z
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //leftlegback is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,-3, 3 -xtimes).setImageColor(img,12+xtimes,20+ytimes,rgb).queue(); //leftlegback starts at 12, 20
				} //leftlegback starts at x+3, y+11, z+3
			}
		}

		if(buildRight) {
			//RIGHT LEG

			//TOP
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //rightlegtop is 4x4
					Single.setup(builder,world,x,y+11,z).offset(direction,-3+ytimes,-4+xtimes).setImageColor(img,4+xtimes,16+ytimes,rgb).queue(); //rightlegtop starts at 4, 16
				} //rightlegtop starts at x-4, y+11, z-3
			}

			//BOTTOM
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<4; ytimes++) { //rightlegbot is 4x4
					Single.setup(builder,world,x,y,z).offset(direction,-3+ytimes,-4+xtimes).setImageColor(img,8+xtimes,16+ytimes,rgb).queue(); //rightlegbot starts at 8, 16
				} //rightlegbot starts at x-4, y, z-3
			}

			//LEFT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegleft is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,-xtimes,-1).setImageColor(img,8+xtimes,20+ytimes,rgb).queue(); //rightlegleft starts at 8, 20
				} //rightlegleft starts at x+3, y+11, z
			}

			//RIGHT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegright is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,-3+xtimes,-4).setImageColor(img, xtimes,20+ytimes,rgb).queue(); //rightlegright starts at 0, 20
				} //rightlegright starts at x, y+11, z-3
			}

			//FRONT
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegfront is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,0,-1-xtimes).setImageColor(img,4+xtimes,20+ytimes,rgb).queue(); //rightlegfront starts at 4, 20
				} //rightlegfront starts at x, y+11, z
			}

			//BACK
			for(int xtimes=0; xtimes<4; xtimes++) {
				for(int ytimes=0; ytimes<12; ytimes++) { //rightlegback is 4x12
					Single.setup(builder,world,x,y+11-ytimes,z).offset(direction,-3,-4+xtimes).setImageColor(img,12+xtimes,20+ytimes,rgb).queue(); //rightlegback starts at 12, 20
				} //rightlegback starts at x+3, y+11, z+3
			}
		}
	}

	private static class Skin {
		private final String uuid;
		private final String name;
		private final String image_url;
		private final BufferedImage image;
		private boolean slim;
		public Skin(String uuid, String name, String image_url) throws IOException {
			this(uuid,name,image_url,false);
		}
		public Skin(String uuid, String name, String image_url, boolean slim) throws IOException {
			this.uuid = uuid;
			this.name = name;
			this.image_url = image_url;
			this.image = ImageIO.read(new URL(image_url));
			this.slim = slim;
		}
		public String getUUID() {
			return this.uuid;
		}
		public String getName() {
			return this.name;
		}
		public String getImageURL() {
			return this.image_url;
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