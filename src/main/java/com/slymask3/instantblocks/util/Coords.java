package com.slymask3.instantblocks.util;

import net.minecraft.core.BlockPos;

public class Coords {
    private final int x;
    private final int y;
    private final int z;

    public Coords(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public BlockPos getBlockPos() {
        return new BlockPos(x,y,z);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Coords other = (Coords)obj;
        return x == other.getX() && y == other.getY() && z == other.getZ();
    }
}
