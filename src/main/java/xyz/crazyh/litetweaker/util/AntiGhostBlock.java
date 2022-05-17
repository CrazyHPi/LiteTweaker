package xyz.crazyh.litetweaker.util;

import fi.dy.masa.malilib.overlay.message.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import xyz.crazyh.litetweaker.config.Configs;

public class AntiGhostBlock {
    public static void clearGhostBlock() {
        clear();
        MessageUtils.printCustomActionbarMessage("litetweaker.message.clear_ghost_block");
    }

    public static void silentClearGhostBlock() {
        clear();
    }

    private static int autoClearGhostBlockCounter;
    public static void autoClearGhostBlock(boolean inGame, boolean clock) {
        if (clock && inGame){
            if (Configs.Generic.AUTO_CLEAR_GHOST_BLOCK.getBooleanValue()) {
                if (autoClearGhostBlockCounter++ >= Configs.Generic.AUTO_CLEAR_GHOST_BLOCK_INTERVAL.getIntegerValue()) {
                    AntiGhostBlock.silentClearGhostBlock();
                    autoClearGhostBlockCounter = 0;
                }
            }
        }
    }

    private static void clear() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = minecraft.player;
        NetHandlerPlayClient client = minecraft.getConnection();

        if (client == null) {
            return;
        }

        BlockPos pos = playerSP.getPosition();

        int range = 4;

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range ; j++) {
                for (int k = -range; k <=range ; k++) {
                    CPacketPlayerDigging packet= new CPacketPlayerDigging(
                            CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK,
                            new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k),
                            EnumFacing.UP
                    );

                    client.sendPacket(packet);
                }
            }

        }
    }

    private static void test() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = minecraft.player;
        NetHandlerPlayClient client = minecraft.getConnection();
        //CPacketPlayerTryUseItemOnBlock packet = new CPacketPlayerTryUseItemOnBlock();
    }
}
