package xyz.crazyh.litetweaker.util;

import fi.dy.masa.malilib.config.value.BlackWhiteList;
import fi.dy.masa.malilib.util.restriction.UsageRestriction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockSnow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;

public class BlockBlackListHelper {
    //perimeter wall helper
    private static final List<Block> periWall = new ArrayList<>();

    //disable block hit
    private static final UsageRestriction<Block> blockHitRestriction = new UsageRestriction<>();

    public static boolean checkTopBlockBlackListed(BlockPos pos) {
        WorldClient world = Minecraft.getMinecraft().world;

        BlockPos topPos = findHighestNonAirBlockPos(pos, world);
        Block topBlock = world.getBlockState(topPos).getBlock();

        return topBlock instanceof BlockSnow ? periWall.contains(world.getBlockState(topPos.offset(EnumFacing.DOWN)).getBlock()) : periWall.contains(topBlock);
    }

    public static boolean checkBlackListed(BlockPos pos) {
        WorldClient world = Minecraft.getMinecraft().world;
        
        return !blockHitRestriction.isAllowed(world.getBlockState(pos).getBlock());
    }

    public static BlockPos findHighestNonAirBlockPos(BlockPos pos, World world) {

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

    public static void updateBlockHitRestriction(BlackWhiteList<Block> list) {
        blockHitRestriction.setListContents(list);
    }

    public static void updatePeriWallHelper(List<Block> list) {
        periWall.clear();
        periWall.addAll(list);
    }
}
