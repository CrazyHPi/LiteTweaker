package xyz.crazyh.litetweaker.mixins.Events;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.SPacketJoinGame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.LiteTweaker;

@Mixin(NetHandlerPlayClient.class)
public abstract class NetHandlerPlayClientMixin {
    @Inject(method = "handleJoinGame", at = @At("TAIL"))
    private void onPostJoinGame(SPacketJoinGame packetIn, CallbackInfo ci) {
        LiteTweaker.onJoinGame();
    }
}
