package com.slymask3.instantblocks.block.instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.ColorLayerBlock;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.StatueBlockEntity;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.ColorHelper;
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

			if(img.getHeight() == 64) {
				if(skin.isSlim()) {
					buildArmsSlimOverlay(builder, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
				} else {
					buildArmsOverlay(builder, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
				}
				buildLegsOverlay(builder, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
				buildHeadOverlay(builder, img, direction, blockEntity.head, blockEntity.rgb);
				buildBodyOverlay(builder, img, direction, blockEntity.head, blockEntity.rgb);
			}

			buildHead(builder, img, direction, blockEntity.head, blockEntity.rgb);
			buildBody(builder, img, direction, blockEntity.body, blockEntity.rgb);
			buildLegs(builder, img, direction, blockEntity.legLeft, blockEntity.legRight, blockEntity.rgb);
			if(skin.isSlim()) {
				buildArmsSlim(builder, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
			} else {
				buildArms(builder, img, direction, blockEntity.armLeft, blockEntity.armRight, blockEntity.rgb);
			}

			builder.build();

			setCreateMessage(Strings.CREATE_STATUE, skin.getName());
			return true;
		} else {
			Helper.sendMessage(player, Strings.ERROR_STATUE, ChatFormatting.RED + blockEntity.username);
		}
		return false;
	}


	private static void buildHeadOverlay(Builder builder, BufferedImage img, Direction direction, boolean build, boolean rgb) {
		if(build) {
			buildFromImage(builder,direction,rgb,5,0,3,0,32,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,40,0,8,8,RelativeDirection.DOWN); //top
			buildFromImage(builder,direction,rgb,5,0,3,0,23,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,48,0,8,8,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,5,0,4,0,31,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,32,8,8,8,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,2,0,5,31,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,48,8,8,8,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,3,3,0,31,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,40,8,8,8,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,6,0,0,4,31,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,56,8,8,8,RelativeDirection.BACK); //back
		}
	}

	private static void buildBodyOverlay(Builder builder, BufferedImage img, Direction direction, boolean build, boolean rgb) {
		if(build) {
			buildFromImage(builder,direction,rgb,3,0,3,0,24,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,20, 32,8,4,RelativeDirection.DOWN); //top
			buildFromImage(builder,direction,rgb,3,0,3,0,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,28, 32,8,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,4,0,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,16, 36,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,0,5,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,28, 36,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,3,0,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,20, 36,8,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,0,4,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,32, 36,8,12,RelativeDirection.BACK); //back
		}
	}

	private static void buildArmsOverlay(Builder builder, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			buildFromImage(builder,direction,rgb,3,0,0,5,24,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,52, 48,4,4,RelativeDirection.DOWN); //top
			buildFromImage(builder,direction,rgb,3,0,0,5,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,56, 48,4,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,0,4,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,48, 52,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,0,9,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,56, 52,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,52, 52,4,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,0,8,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,60, 52,4,12,RelativeDirection.BACK); //back
		}
		if(buildRight) {
			buildFromImage(builder,direction,rgb,3,0,7,0,24,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,44, 32,4,4,RelativeDirection.DOWN); //top
			buildFromImage(builder,direction,rgb,3,0,7,0,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,48, 32,4,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,8,0,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,40, 36,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,3,0,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,48, 36,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,7,0,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,44, 36,4,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,4,0,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,52, 36,4,12,RelativeDirection.BACK); //back
		}
	}

	private static void buildArmsSlimOverlay(Builder builder, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			buildFromImage(builder,direction,rgb,3,0,0,5,24,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,52, 48,3,4,RelativeDirection.DOWN); //top
			buildFromImage(builder,direction,rgb,3,0,0,5,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,55, 48,3,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,0,4,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,48, 52,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,0,8,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,55, 52,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,52, 52,3,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,0,7,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,59, 52,3,12,RelativeDirection.BACK); //back
		}
		if(buildRight) {
			buildFromImage(builder,direction,rgb,3,0,6,0,24,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,44, 32,3,4,RelativeDirection.DOWN); //top
			buildFromImage(builder,direction,rgb,3,0,6,0,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,47, 32,3,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,7,0,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,40, 36,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,3,0,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,47, 36,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,6,0,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,44, 36,3,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,4,0,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,51, 36,3,12,RelativeDirection.BACK); //back
		}
	}

	private static void buildLegsOverlay(Builder builder, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			buildFromImage(builder,direction,rgb,3,0,0,1,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,4, 48,4,4,RelativeDirection.DOWN); //top
			//buildFromImage(builder,direction,rgb,3,0,0,1,0,1, RelativeDirection.RIGHT, RelativeDirection.BACK,img,8, 48,4,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,0,0,11,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,0, 52,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,0,5,11,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,8, 52,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,0,1,11,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,4, 52,4,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,0,4,11,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,12, 52,4,12,RelativeDirection.BACK); //back
		}
		if(buildRight) {
			buildFromImage(builder,direction,rgb,3,0,3,0,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,4, 32,4,4,RelativeDirection.DOWN); //top
			//buildFromImage(builder,direction,rgb,3,0,3,0,0,1, RelativeDirection.RIGHT, RelativeDirection.BACK,img,8, 32,4,4,RelativeDirection.UP); //bottom
			buildFromImage(builder,direction,rgb,3,0,4,0,11,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,0, 36,4,12,RelativeDirection.RIGHT); //left
			buildFromImage(builder,direction,rgb,0,0,0,1,11,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,8, 36,4,12,RelativeDirection.LEFT); //right
			buildFromImage(builder,direction,rgb,0,1,3,0,11,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,4, 36,4,12,RelativeDirection.FORWARD); //front
			buildFromImage(builder,direction,rgb,4,0,0,0,11,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,12, 36,4,12,RelativeDirection.BACK); //back
		}
	}

	private static void buildHead(Builder builder, BufferedImage img, Direction direction, boolean build, boolean rgb) {
		if(build) {
			buildFromImage(builder,direction,rgb,5,0,3,0,31,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,8,0,8,8); //top
			buildFromImage(builder,direction,rgb,5,0,3,0,24,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,16,0,8,8); //bottom
			buildFromImage(builder,direction,rgb,5,0,3,0,31,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,0,8,8,8); //left
			buildFromImage(builder,direction,rgb,0,2,0,4,31,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,16,8,8,8); //right
			buildFromImage(builder,direction,rgb,0,2,3,0,31,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,8,8,8,8); //front
			buildFromImage(builder,direction,rgb,5,0,0,4,31,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,24,8,8,8); //back
		}
	}

	private static void buildBody(Builder builder, BufferedImage img, Direction direction, boolean build, boolean rgb) {
		if(build) {
			buildFromImage(builder,direction,rgb,3,0,3,0,23,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,20, 16,8,4); //top
			buildFromImage(builder,direction,rgb,3,0,3,0,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,28, 16,8,4); //bottom
			buildFromImage(builder,direction,rgb,3,0,3,0,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,16, 20,4,12); //left
			buildFromImage(builder,direction,rgb,0,0,0,4,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,28, 20,4,12); //right
			buildFromImage(builder,direction,rgb,0,0,3,0,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,20, 20,8,12); //front
			buildFromImage(builder,direction,rgb,3,0,0,4,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,32, 20,8,12); //back
		}
	}

	private static void buildArms(Builder builder, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			if(img.getHeight() == 32) {
				buildFromImage(builder,direction,rgb,3,0,0,8,23,0, RelativeDirection.LEFT, RelativeDirection.BACK,img,44, 16,4,4); //top
				buildFromImage(builder,direction,rgb,3,0,0,8,12,0, RelativeDirection.LEFT, RelativeDirection.BACK,img,48, 16,4,4); //bottom
				buildFromImage(builder,direction,rgb,0,0,0,5,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,48, 20,4,12); //left
				buildFromImage(builder,direction,rgb,3,0,0,8,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,40, 20,4,12); //right
				buildFromImage(builder,direction,rgb,0,0,0,8,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,44, 20,4,12); //front
				buildFromImage(builder,direction,rgb,3,0,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,52, 20,4,12); //back
			} else {
				buildFromImage(builder,direction,rgb,3,0,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,36, 48,4,4); //top
				buildFromImage(builder,direction,rgb,3,0,0,5,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,40, 48,4,4); //bottom
				buildFromImage(builder,direction,rgb,3,0,0,5,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,32, 52,4,12); //left
				buildFromImage(builder,direction,rgb,0,0,0,8,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,40, 52,4,12); //right
				buildFromImage(builder,direction,rgb,0,0,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,36, 52,4,12); //front
				buildFromImage(builder,direction,rgb,3,0,0,8,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,44, 52,4,12); //back
			}
		}
		if(buildRight) {
			buildFromImage(builder,direction,rgb,3,0,7,0,23,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,44, 16,4,4); //top
			buildFromImage(builder,direction,rgb,3,0,7,0,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,48, 16,4,4); //bottom
			buildFromImage(builder,direction,rgb,3,0,7,0,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,40, 20,4,12); //left
			buildFromImage(builder,direction,rgb,0,0,4,0,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,48, 20,4,12); //right
			buildFromImage(builder,direction,rgb,0,0,7,0,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,44, 20,4,12); //front
			buildFromImage(builder,direction,rgb,3,0,4,0,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,52, 20,4,12); //back
		}
	}

	private static void buildArmsSlim(Builder builder, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			if(img.getHeight() == 32) {
				buildFromImage(builder,direction,rgb,3,0,0,7,23,0, RelativeDirection.LEFT, RelativeDirection.BACK,img,44, 16,3,4); //top
				buildFromImage(builder,direction,rgb,3,0,0,7,12,0, RelativeDirection.LEFT, RelativeDirection.BACK,img,47, 16,3,4); //bottom
				buildFromImage(builder,direction,rgb,0,0,0,5,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,47, 20,4,12); //left
				buildFromImage(builder,direction,rgb,3,0,0,7,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,40, 20,4,12); //right
				buildFromImage(builder,direction,rgb,0,0,0,7,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,44, 20,3,12); //front
				buildFromImage(builder,direction,rgb,3,0,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,51, 20,3,12); //back
			} else {
				buildFromImage(builder,direction,rgb,3,0,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,36, 48,3,4); //top
				buildFromImage(builder,direction,rgb,3,0,0,5,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,39, 48,3,4); //bottom
				buildFromImage(builder,direction,rgb,3,0,0,5,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,32, 52,4,12); //left
				buildFromImage(builder,direction,rgb,0,0,0,7,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,39, 52,4,12); //right
				buildFromImage(builder,direction,rgb,0,0,0,5,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,36, 52,3,12); //front
				buildFromImage(builder,direction,rgb,3,0,0,7,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,43, 52,3,12); //back
			}
		}
		if(buildRight) {
			buildFromImage(builder,direction,rgb,3,0,6,0,23,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,44, 16,3,4); //top
			buildFromImage(builder,direction,rgb,3,0,6,0,12,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,47, 16,3,4); //bottom
			buildFromImage(builder,direction,rgb,3,0,6,0,23,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,40, 20,4,12); //left
			buildFromImage(builder,direction,rgb,0,0,4,0,23,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,47, 20,4,12); //right
			buildFromImage(builder,direction,rgb,0,0,6,0,23,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,44, 20,3,12); //front
			buildFromImage(builder,direction,rgb,3,0,4,0,23,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,51, 20,3,12); //back
		}
	}
	
	private static void buildLegs(Builder builder, BufferedImage img, Direction direction, boolean buildLeft, boolean buildRight, boolean rgb) {
		if(buildLeft) {
			if(img.getHeight() == 32) {
				buildFromImage(builder,direction,rgb,3,0,0,4,11,0, RelativeDirection.LEFT, RelativeDirection.BACK,img,4, 16,4,4); //top
				buildFromImage(builder,direction,rgb,3,0,0,4,0,0, RelativeDirection.LEFT, RelativeDirection.BACK,img,8, 16,4,4); //bottom
				buildFromImage(builder,direction,rgb,0,0,0,1,11,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,8, 20,4,12); //left
				buildFromImage(builder,direction,rgb,3,0,0,4,11,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,0, 20,4,12); //right
				buildFromImage(builder,direction,rgb,0,0,0,4,11,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,4, 20,4,12); //front
				buildFromImage(builder,direction,rgb,3,0,0,1,11,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,12, 20,4,12); //back
			} else {
				buildFromImage(builder,direction,rgb,3,0,0,1,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,20, 48,4,4); //top
				buildFromImage(builder,direction,rgb,3,0,0,1,0,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,24, 48,4,4); //bottom
				buildFromImage(builder,direction,rgb,3,0,0,1,11,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,16, 52,4,12); //left
				buildFromImage(builder,direction,rgb,0,0,0,4,11,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,24, 52,4,12); //right
				buildFromImage(builder,direction,rgb,0,0,0,1,11,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,20, 52,4,12); //front
				buildFromImage(builder,direction,rgb,3,0,0,4,11,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,28, 52,4,12); //back
			}
		}
		if(buildRight) {
			buildFromImage(builder,direction,rgb,3,0,3,0,11,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,4, 16,4,4); //top
			buildFromImage(builder,direction,rgb,3,0,3,0,0,0, RelativeDirection.RIGHT, RelativeDirection.BACK,img,8, 16,4,4); //bottom
			buildFromImage(builder,direction,rgb,3,0,3,0,11,0, RelativeDirection.BACK, RelativeDirection.DOWN,img,0, 20,4,12); //left
			buildFromImage(builder,direction,rgb,0,0,0,0,11,0, RelativeDirection.FORWARD, RelativeDirection.DOWN,img,8, 20,4,12); //right
			buildFromImage(builder,direction,rgb,0,0,3,0,11,0, RelativeDirection.RIGHT, RelativeDirection.DOWN,img,4, 20,4,12); //front
			buildFromImage(builder,direction,rgb,3,0,0,0,11,0, RelativeDirection.LEFT, RelativeDirection.DOWN,img,12, 20,4,12); //back
		}
	}

	private enum RelativeDirection { LEFT,RIGHT,FORWARD,BACK,UP,DOWN }

	private static void buildFromImage(Builder builder, Direction direction, boolean rgb, int forward, int back, int left, int right, int up, int down, RelativeDirection dirX, RelativeDirection dirY, BufferedImage img, int imgX, int imgY, int sizeX, int sizeY) {
		buildFromImage(builder,direction,rgb,forward,back,left,right,up,down,dirX,dirY,img,imgX,imgY,sizeX,sizeY,null);
	}

	private static void buildFromImage(Builder builder, Direction direction, boolean rgb, int forward, int back, int left, int right, int up, int down, RelativeDirection dirX, RelativeDirection dirY, BufferedImage img, int imgX, int imgY, int sizeX, int sizeY, RelativeDirection overlayDirection) {
		BlockPos pos = builder.getOriginPos();
		for(int xtimes=0; xtimes<sizeX; xtimes++) {
			for(int ytimes=0; ytimes<sizeY; ytimes++) {
				int f = forward;
				int b = back;
				int l = left;
				int r = right;
				int u = up;
				int d = down;
				switch(dirX) {
					case LEFT -> l += xtimes;
					case RIGHT -> r += xtimes;
					case FORWARD -> f += xtimes;
					case BACK -> b += xtimes;
					case UP -> u += xtimes;
					case DOWN -> d += xtimes;
				}
				switch(dirY) {
					case LEFT -> l += ytimes;
					case RIGHT -> r += ytimes;
					case FORWARD -> f += ytimes;
					case BACK -> b += ytimes;
					case UP -> u += ytimes;
					case DOWN -> d += ytimes;
				}
				if(overlayDirection != null) {
					if(rgb && ColorHelper.isTransparent(img.getRGB(imgX+xtimes,imgY+ytimes))) {
						Direction stateDirection = switch(overlayDirection) {
							case LEFT -> direction.getCounterClockWise();
							case RIGHT -> direction.getClockWise();
							case FORWARD -> direction;
							case BACK -> direction.getOpposite();
							case UP -> Direction.UP;
							case DOWN -> Direction.DOWN;
						};
						BlockState state = ColorLayerBlock.getStateForDirection(stateDirection);
						Single.setup(builder,builder.getWorld(),pos).offset(direction,f,b,l,r,u,d).setImageColor(img,imgX+xtimes,imgY+ytimes,state).queue();
					}
				} else {
					Single.setup(builder,builder.getWorld(),pos).offset(direction,f,b,l,r,u,d).setImageColor(img,imgX+xtimes,imgY+ytimes,rgb).queue();
				}
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