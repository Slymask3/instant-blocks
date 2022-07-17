package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.*;
import com.slymask3.instantblocks.network.packet.*;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PacketHelper {
    public enum PacketID { CLIENT, SKYDIVE, STATUE, HARVEST, TREE, SCHEMATIC, SCHEMATIC_UPDATE }

    public static void handleClient(ClientPacket message, Player player) {
        if(player != null) {
            if(message.particles != ClientHelper.Particles.NONE.ordinal()) {
                Level world = player.getLevel();
                ClientHelper.playSound(world, message.pos, ClientHelper.Particles.values()[message.particles]);
                ClientHelper.showParticles(world, message.pos, ClientHelper.Particles.values()[message.particles]);
            }
            if(!message.message.isEmpty()) {
                ClientHelper.sendMessage(player, message.message, message.variable);
            }
        }
    }

    public static void handleSkydive(SkydivePacket message, Player player) {
        if(player != null) {
            Level world = player.getLevel();
            InstantSkydiveBlock block = (InstantSkydiveBlock) Helper.getBlock(world,message._x, message._y, message._z);
            if(block.build(world,message._x, message._y, message._z, player, message._colors, message._radius, message._tp)) {
                block.afterBuild(world,message._x, message._y, message._z, player);
            }
        }
    }

    public static void handleStatue(StatuePacket message, Player player) {
        if(player != null) {
            Level world = player.getLevel();
            InstantStatueBlock block = (InstantStatueBlock) Helper.getBlock(world,message._x, message._y, message._z);
            if(block.build(world, message._x, message._y, message._z, player, message._username, message._head, message._body, message._armLeft, message._armRight, message._legLeft, message._legRight, message._rgb)) {
                block.afterBuild(world, message._x, message._y, message._z, player);
            }
        }
    }

    public static void handleHarvest(HarvestPacket message, Player player) {
        if(player != null) {
            Level world = player.getLevel();
            InstantHarvestBlock block = (InstantHarvestBlock) Helper.getBlock(world,message._x, message._y, message._z);
            if(block.build(world, message._x, message._y, message._z, message._logOak, message._logSpruce, message._logBirch, message._logJungle, message._logAcacia, message._logDark, message._wheat, message._carrot, message._potato, message._cactus, message._pumpkin, message._melon, message._sugarcane, message._cocoa, message._mushroom, message._netherwart, message._replant)) {
                block.afterBuild(world, message._x, message._y, message._z, player);
            }
        }
    }

    public static void handleTree(TreePacket message, Player player) {
        if(player != null) {
            Level world = player.getLevel();
            InstantTreeBlock block = (InstantTreeBlock) Helper.getBlock(world,message._x, message._y, message._z);
            if(block.build(world, message._x, message._y, message._z, player, message._type, message._log, message._leaves, message._air)) {
                block.afterBuild(world, message._x, message._y, message._z, player);
            }
        }
    }

    public static void handleSchematic(SchematicPacket message, Player player) {
        if(player != null) {
            Level world = player.getLevel();
            InstantSchematicBlock block = (InstantSchematicBlock) Helper.getBlock(world,message._x, message._y, message._z);
            if(block.build(world,message._x, message._y, message._z, player, message._schematic, message._center, message._air)) {
                block.afterBuild(world,message._x, message._y, message._z, player);
            }
        }
    }

    public static void handleSchematicUpdate(SchematicUpdatePacket message, Player player) {
        if(player != null) {
            SchematicHelper.SCHEMATICS_LIST = message.schematics;
            ClientHelper.showScreen(ClientHelper.Screen.SCHEMATIC,player,player.getLevel(),message.pos);
        }
    }
}