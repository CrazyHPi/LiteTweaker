package xyz.crazyh.litetweaker.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class noFall {
    public static void cancelPlayerFall(Minecraft minecraft, boolean inGame, boolean clock) {
        EntityPlayerSP playerSP = Minecraft.getMinecraft().player;

        if (inGame && clock && TweaksToggle.NO_FALL.getBooleanValue() && playerSP.fallDistance > 2F && !playerSP.isElytraFlying()) {
            playerSP.connection.sendPacket(new CPacketPlayer(true));
        }
    }
}
