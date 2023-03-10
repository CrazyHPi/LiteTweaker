package xyz.crazyh.litetweaker.mixins.Tweaks.autoDropAndFillContainer;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.SPacketOpenWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.util.autoInventoryProcess.DropContainer;
import xyz.crazyh.litetweaker.util.autoInventoryProcess.FillContainer;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient {
    @Inject(
            method = "handleOpenWindow",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/play/server/SPacketOpenWindow;getWindowId()I",
                    ordinal = 4,
                    shift = At.Shift.AFTER
            )
    )
    private void processContainer(SPacketOpenWindow packetIn, CallbackInfo ci) {
        if (DropContainer.drop(packetIn)) {
            return;
        }
        FillContainer.fill(packetIn);
    }
}
