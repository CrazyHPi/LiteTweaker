package xyz.crazyh.litetweaker.mixins.Disables.disableEnderPearlCooldown;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.play.server.SPacketCooldown;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient {
    @Shadow private Minecraft client;

    /*@Inject(
            method = "handleCooldown",
            at = @At(
                    value = "HEAD",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void noCooldown(SPacketCooldown packetIn, CallbackInfo ci) {
        if (TweaksToggle.DISABLE_ENDER_PEARL_COOLDOWN.getBooleanValue() && packetIn.getItem() instanceof ItemEnderPearl) {
            this.client.player.getCooldownTracker().removeCooldown(packetIn.getItem());
            ci.cancel();
        }
    }*/
}
