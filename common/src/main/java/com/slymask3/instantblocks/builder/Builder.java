package com.slymask3.instantblocks.builder;

import com.slymask3.instantblocks.builder.type.Single;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Builder {
	public enum Status { SETUP, BUILD, DONE }

	public List<Single> queue;
	public Status status;
	public int speed;
	public int ticks;

	public Builder() {
		this(1);
	}

	public Builder(int speed) {
		this.queue = new ArrayList<>();
		this.status = Status.SETUP;
		this.speed = speed;
		this.ticks = speed - 1;
	}

	public void tick() {
		if(this.status.equals(Status.BUILD) && !queue.isEmpty()) {
			this.ticks++;
			if(this.ticks == this.speed) {
				Single first = queue.get(0);
				int priority = first.priority;
				first.build();
				queue.remove(0);
				while(!queue.isEmpty() && queue.get(0).priority == priority) {
					queue.get(0).build();
					queue.remove(0);
				}
				if(queue.isEmpty()) {
					this.status = Status.DONE;
				}
				this.ticks = 0;
			}
		}
	}

	public void queue(Single single) {
		this.queue.removeIf(temp -> temp.getBlockPos().equals(single.getBlockPos()) && temp.priority == single.priority);
		//this.queue.removeIf(temp -> temp.x == single.x && temp.y == single.y && temp.z == single.z && temp.priority == single.priority);
		this.queue.add(single);
	}

	public void build() {
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
}