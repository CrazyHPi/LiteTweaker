package xyz.crazyh.litetweaker.util;

import fi.dy.masa.malilib.overlay.message.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class AntiGhostBlock {
    public static void clearGhostBlock() {
        clear();
        MessageUtils.printCustomActionbarMessage("liteloader.message.clear_ghost_block");
    }

    public static void silentClearGhostBlock() {
        clear();
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
}
