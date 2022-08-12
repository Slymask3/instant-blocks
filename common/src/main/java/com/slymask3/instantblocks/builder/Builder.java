package com.slymask3.instantblocks.builder;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.network.packet.client.SoundPacket;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.*;

public class Builder {
	private enum Status { SETUP, BUILD, DONE }
	public enum Origin { FROM, TO }

	private final HashMap<BlockPos,Single> queueMap;
	private final List<Single> queue;
	private final Level world;
	private final BlockPos originPos;
	private Status status;
	private int speed;
	private int ticks;
	private Direction priorityDirection;
	private Origin priorityOrigin;
	private int distanceMultiplier;
	private ClientHelper.Particles particles;

	private Builder(Level world, BlockPos pos) {
		this.queueMap = new HashMap<>();
		this.queue = new ArrayList<>();
		this.world = world;
		this.originPos = pos;
		this.status = Status.SETUP;
		this.speed = 1;
		this.ticks = 0;
		this.priorityDirection = null;
		this.priorityOrigin = null;
		this.distanceMultiplier = 1;
		this.particles = ClientHelper.Particles.PLACE_BLOCK;
	}

	public static Builder setup(Level world, BlockPos pos) {
		return new Builder(world,pos);
	}

	public static Builder setup(Level world, int x, int y, int z) {
		return new Builder(world, new BlockPos(x,y,z));
	}

	public Builder setSpeed(int speed) {
		this.speed = speed;
		this.ticks = speed - 1;
		return this;
	}

	public Builder setDirection(Direction direction) {
		this.priorityDirection = direction;
		return this;
	}

	public Builder setOrigin(Origin originType) {
		return this.setOrigin(originType,1);
	}

	public Builder setOrigin(Origin priorityOrigin, int distanceMultiplier) {
		this.priorityOrigin = priorityOrigin;
		this.distanceMultiplier = distanceMultiplier;
		return this;
	}

	public Builder setParticles(ClientHelper.Particles particles) {
		this.particles = particles;
		return this;
	}

	public void tick() {
		if(this.status.equals(Status.BUILD) && !queue.isEmpty()) {
			this.ticks++;
			if(this.ticks >= this.speed) {
				List<BuildSound> buildSounds = new ArrayList<>();
				Single first = queue.get(0);
				Level world = first.getLevel();
				BlockPos firstBlockPos = first.getBlockPos();
				int priority = first.priority;
				this.handle(buildSounds);
				while(!queue.isEmpty() && queue.get(0).priority == priority) {
					this.handle(buildSounds);
				}
				if(!buildSounds.isEmpty()) {
					Common.NETWORK.sendToAllAround(world,firstBlockPos,new SoundPacket(buildSounds));
				}
				if(queue.isEmpty()) {
					this.status = Status.DONE;
				}
				this.ticks = 0;
			}
		}
	}

	private void handle(List<BuildSound> buildSounds) {
		if(queue.get(0).getBlockType().isConditionalTorch()) {
			queue.get(0).build();
			buildSounds.add(queue.get(0).getBuildSound(this.particles));
			queue.remove(0);
			return;
		}
		buildSounds.add(queue.get(0).getBuildSound(this.particles));
		queue.get(0).build();
		queue.remove(0);
	}

	public void queue(Single single, boolean replace) {
		BlockPos pos = single.getBlockPos();
		if(replace) {
			if(this.queueMap.containsKey(pos)) {
				this.queueMap.replace(single.getBlockPos(),single);
			} else {
				this.queueMap.put(single.getBlockPos(),single);
			}
		} else {
			this.queue.add(single);
		}
	}

	public void build() {
		for(Map.Entry<BlockPos,Single> set : this.queueMap.entrySet()) {
			this.queue.add(set.getValue());
		}
		this.queueMap.clear();

		if(this.priorityDirection != null) {
			for(Single single : this.queue) {
				single.priority = switch(this.priorityDirection) {
					case UP -> single.y;
					case DOWN -> -single.y;
					case NORTH -> -single.z;
					case SOUTH -> single.z;
					case WEST -> -single.x;
					case EAST -> single.x;
				};
			}
		}

		if(this.priorityOrigin != null) {
			for(Single single : this.queue) {
				int distance = (int)Math.floor(Helper.getDistanceBetween(this.originPos,single.getBlockPos()) * this.distanceMultiplier);
				single.priority = switch(priorityOrigin) {
					case FROM -> distance;
					case TO -> -distance;
				};
			}
		}

		this.queue.sort(Comparator.comparingInt(one -> one.priority));

		this.status = Status.BUILD;
		builders.add(this);
	}

	public static List<Builder> builders = new ArrayList<>();

	public static void globalTick() {
		if(!builders.isEmpty()) {
			for(Builder builder : builders) {
				builder.tick();
				if(builder.status.equals(Status.DONE)) {
					builders.remove(builder);
					break;
				}
			}
		}
	}

	public static boolean inProgress(LevelAccessor world, BlockPos pos) {
		if(!builders.isEmpty()) {
			for(Builder builder : builders) {
				if(builder.status.equals(Status.BUILD) && builder.world.equals(world) && builder.originPos.equals(pos) && world.getBlockState(pos).getBlock() instanceof InstantBlock) {
					return true;
				}
			}
		}
		return false;
	}
}