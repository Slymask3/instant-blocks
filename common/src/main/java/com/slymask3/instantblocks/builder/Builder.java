package com.slymask3.instantblocks.builder;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.network.packet.SoundPacket;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

import java.util.*;

public class Builder {
	private enum Status { SETUP, BUILD, DONE }

	private final HashMap<BlockPos,Single> queueMap;
	private final List<Single> queue;
	private Status status;
	private final int speed;
	private int ticks;
	private final Direction priorityDirection;
	private BlockPos originPos;
	private boolean fromOrigin;
	private int distanceMultiplier;

	public Builder() {
		this(1);
	}

	public Builder(int speed) {
		this(speed, null);
	}

	public Builder(int speed, Direction priorityDirection) {
		Common.Timer.start();
		this.queueMap = new HashMap<>();
		this.queue = new ArrayList<>();
		this.status = Status.SETUP;
		this.speed = speed;
		this.ticks = speed - 1;
		this.priorityDirection = priorityDirection;
		this.originPos = null;
		this.fromOrigin = true;
		this.distanceMultiplier = 1;
	}

	public Builder setOrigin(BlockPos pos, boolean fromOrigin) {
		return this.setOrigin(pos,fromOrigin,1);
	}

	public Builder setOrigin(BlockPos pos, boolean fromOrigin, int distanceMultiplier) {
		this.originPos = pos;
		this.fromOrigin = fromOrigin;
		this.distanceMultiplier = distanceMultiplier;
		return this;
	}

	public void tick() {
		if(this.status.equals(Status.BUILD) && !queue.isEmpty()) {
			this.ticks++;
			if(this.ticks >= this.speed) {
				List<Helper.BuildSound> buildSounds = new ArrayList<>();
				Single first = queue.get(0);
				Level world = first.getLevel();
				BlockPos firstBlockPos = first.getBlockPos();
				int priority = first.priority;
				this.handle(buildSounds);
				while(!queue.isEmpty() && queue.get(0).priority == priority) {
					this.handle(buildSounds);
				}
				Common.LOG.info("buildSounds.size(): {}", buildSounds.size());
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

	private void handle(List<Helper.BuildSound> buildSounds) {
		if(queue.get(0).getBlockType().isConditionalTorch()) {
			queue.get(0).build();
			buildSounds.add(queue.get(0).getBuildSound());
			queue.remove(0);
			return;
		}
		buildSounds.add(queue.get(0).getBuildSound());
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
		Common.LOG.info("build(): " + Common.Timer.end());

		Common.Timer.start();
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

		if(this.originPos != null) {
			for(Single single : this.queue) {
				int distance = (int)Math.floor(Helper.getDistanceBetween(this.originPos,single.getBlockPos()) * this.distanceMultiplier);
				single.priority = this.fromOrigin ? distance : -distance;
			}
		}

		this.queue.sort(Comparator.comparingInt(one -> one.priority));
		Common.LOG.info("sort(): " + Common.Timer.end());

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
}