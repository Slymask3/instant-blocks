package com.slymask3.instantblocks.builder.type;

import com.slymask3.instantblocks.builder.BlockType;
import com.slymask3.instantblocks.builder.Builder;
import net.minecraft.world.level.Level;

public class Circle extends Base<Circle> {
    BlockType outerBlockType, innerBlockType;
    final int radius;

    private Circle(Builder builder, Level world, int x, int y, int z, int radius) {
        super(builder, world, x, y, z);
        this.radius = radius;
    }

    public static Circle setup(Builder builder, Level world, int x, int y, int z, int radius) {
        return new Circle(builder, world, x, y, z, radius);
    }

    public Circle setBlock(BlockType type) {
        this.outerBlockType = type;
        this.innerBlockType = type;
        return this;
    }

    public Circle setOuter(BlockType type) {
        this.outerBlockType = type;
        return this;
    }

    public Circle setInner(BlockType type) {
        this.innerBlockType = type;
        return this;
    }

    public void build() {
        double distance;
        for(int row = 0; row <= 2 * radius; row++) {
            for(int col = 0; col <= 2 * radius; col++) {
                distance = Math.sqrt((row - radius) * (row - radius) + (col - radius) * (col - radius));
                if(distance > radius - 0.4 && distance < radius + 0.6) {
                    Single.setup(builder, world, x + row - radius, y, z + col - radius).setBlock(outerBlockType).queue(this.priority);
                } else if(distance < radius - 0.3) {
                    Single.setup(builder, world, x + row - radius, y, z + col - radius).setBlock(innerBlockType).queue(this.priority);
                }
            }
        }
    }
}