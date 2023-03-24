package xyz.crazyh.litetweaker.mixins.Tweaks.tabPingDisplay;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.crazyh.litetweaker.config.TweaksToggle;

import java.util.List;

@Mixin(GuiPlayerTabOverlay.class)
public abstract class MixinGuiPlayerTabOverlay {

    private static int PING_TEXT_RENDER_OFFSET = -13;
    private static int PLAYER_ICON_WIDTH = 9;
    private static int EXTRA_WIDTH = 25;

    @Shadow @Final private Minecraft mc;

    @ModifyConstant(
            method = "renderPlayerlist",
            constant = @Constant(intValue = 13)
    )
    private int addWidth(int constant) {
        return constant + EXTRA_WIDTH;
    }

    @Inject(
            method = "renderPlayerlist",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiPlayerTabOverlay;drawPing(IIILnet/minecraft/client/network/NetworkPlayerInfo;)V"
            ),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            cancellable = true
    )
    private void renderPing(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn, CallbackInfo ci, NetHandlerPlayClient nethandlerplayclient, List list, int i, int j, int l3, int i4, int j4, boolean flag, int l, int i1, int j1, int k1, int l1, List list1, List list2, int k4, int l4, int i5, int j2, int k2, NetworkPlayerInfo networkplayerinfo1) {
        //shamelessly stolen from: https://github.com/vladmarica/better-ping-display/tree/1.12.2

        if (TweaksToggle.TAB_PING_DISPLAY.getBooleanValue()) {
            String pingString = String.format("%dms", networkplayerinfo1.getResponseTime());
            int pingWidth = mc.fontRenderer.getStringWidth(pingString);
            mc.fontRenderer.drawStringWithShadow(
                    pingString,
                    (float) i1 + j2 - pingWidth + PING_TEXT_RENDER_OFFSET - (flag ? PLAYER_ICON_WIDTH : 0),
                    (float) k2,
                    0xA0A0A0
            );
        }
    }

}
