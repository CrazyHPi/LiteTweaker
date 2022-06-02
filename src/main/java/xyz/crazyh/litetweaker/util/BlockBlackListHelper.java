package xyz.crazyh.litetweaker.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import xyz.crazyh.litetweaker.config.Configs;

public class BlockBlackListHelper {
    public static boolean checkTopBlockBlackListed(BlockPos pos) {
        WorldClient world = Minecraft.getMinecraft().world;
        ImmutableList<Block> list = Configs.Generic.PERIMETER_WALL_LIST.getValue();

        return list.contains(world.getBlockState(findHighestNonAirBlock(pos, world)).getBlock());
    }

    public static boolean checkBlackListed(BlockPos pos) {
        WorldClient world = Minecraft.getMinecraft().world;

        return false;
    }

    public static BlockPos findHighestNonAirBlock(BlockPos pos, World world) {

        Chunk chunk = world.getChunk(pos);
        BlockPos pos1;

        for (pos1 = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); pos1.getY() >= 0; pos1 = pos1.down())
        {
            if (!(chunk.getBlockState(pos1).getBlock() instanceof BlockAir)) {
                break;
            }
        }

        return pos1;
    }
}
