package com.slymask3.instantblocks.config.entry;

import com.slymask3.instantblocks.util.Helper;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class HugeTree {
    public enum Type { OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK };

    public String name;
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Type type;
    public String logs;
    public String leaves;

    public HugeTree() {
        this.name = "Huge Oak Tree";
        this.type = Type.OAK;
        this.logs = Helper.serializeBlock(Blocks.OAK_LOG);
        this.leaves = Helper.serializeBlock(Blocks.OAK_LEAVES);
    }

    public HugeTree(String name, Type type, Block logs, Block leaves) {
        this.name = name;
        this.type = type;
        this.logs = Helper.serializeBlock(logs);
        this.leaves = Helper.serializeBlock(leaves);
    }

    public HugeTree(String name, Type type, String logs, String leaves) {
        this.name = name;
        this.type = type;
        this.logs = logs;
        this.leaves = leaves;
    }

    public static HugeTree empty() {
        return new HugeTree("",Type.OAK,"","");
    }

    public Block getLogs() {
        return Helper.readBlock(this.logs,Blocks.OAK_LOG);
    }

    public Block getLeaves() {
        return Helper.readBlock(this.leaves,Blocks.OAK_LEAVES);
    }
}