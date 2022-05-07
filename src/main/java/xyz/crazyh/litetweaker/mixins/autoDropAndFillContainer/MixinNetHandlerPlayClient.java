package xyz.crazyh.litetweaker.mixins.autoDropAndFillContainer;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.world.IInteractionObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.util.autoContainerProcess.DropContainer;
import xyz.crazyh.litetweaker.util.autoContainerProcess.FillContainer;

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
