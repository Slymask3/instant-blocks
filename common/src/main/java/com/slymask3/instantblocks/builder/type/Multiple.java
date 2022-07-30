package com.slymask3.instantblocks.builder.type;

import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class Multiple extends Base<Multiple> {
    final int x1, y1, z1;
    final int x2, y2, z2;

    private Multiple(Builder builder, Level world, int x, int y, int z, int x1, int y1, int z1, int x2, int y2, int z2) {
        super(builder, world, x, y, z);
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public static Multiple setup(Builder builder, Level world, int x, int y, int z, Direction direction, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
        int x1 = x, z1 = z, x2 = x, z2 = z;
        int y2 = y + upX - downX;
        if (direction == Direction.SOUTH) {
            x1 = x - left + right;
            z1 = z - forward + back;
            x2 = x1 - leftX + rightX;
            z2 = z1 - forwardX + backX;
        } else if (direction == Direction.WEST) {
            x1 = x + forward - back;
            z1 = z - left + right;
            x2 = x1 + forwardX - backX;
            z2 = z1 - leftX + rightX;
        } else if (direction == Direction.NORTH) {
            x1 = x + left - right;
            z1 = z + forward - back;
            x2 = x1 + leftX - rightX;
            z2 = z1 + forwardX - backX;
        } else if (direction == Direction.EAST) {
            x1 = x - forward + back;
            z1 = z + left - right;
            x2 = x1 - forwardX + backX;
            z2 = z1 + leftX - rightX;
        }
        return new Multiple(builder, world, x, y, z, x1, y, z1, x2, y2, z2);
    }

    public static Multiple setup(Builder builder, Level world, int x_start, int y_start, int z_start, int x_times, int y_times, int z_times) {
        return new Multiple(builder, world, x_start, y_start, z_start, x_start, y_start, z_start, x_start + x_times - 1, y_start + y_times - 1, z_start + z_times - 1);
    }

    public void build() {
        boolean x_dir = Helper.isPositive(x1 - x2);
        boolean y_dir = Helper.isPositive(y1 - y2);
        boolean z_dir = Helper.isPositive(z1 - z2);
        for (int y_cur = y1; (y_dir ? y_cur >= y2 : y_cur <= y2); y_cur = y_cur + (y_dir ? -1 : 1)) {
            for (int z_cur = z1; (z_dir ? z_cur >= z2 : z_cur <= z2); z_cur = z_cur + (z_dir ? -1 : 1)) {
                for (int x_cur = x1; (x_dir ? x_cur >= x2 : x_cur <= x2); x_cur = x_cur + (x_dir ? -1 : 1)) {
                    Single.setup(builder, world, x_cur, y_cur, z_cur).setBlock(blockType).setDirection(direction).queue(this.priority);
                }
            }
        }
    }
}