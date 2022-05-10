package xyz.crazyh.litetweaker.mixins.autoFish;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.util.AutoFish;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient {
    @Inject(
            method = "handleSoundEffect",
            at = @At("TAIL")
    )
    private void autoFish(SPacketSoundEffect packetIn, CallbackInfo ci) {
        if (TweaksToggle.AUTO_FISH.getBooleanValue() && SoundEvents.ENTITY_BOBBER_SPLASH.equals(packetIn.getSound())) {
            AutoFish.retractFishingRod();
        }
    }
}
