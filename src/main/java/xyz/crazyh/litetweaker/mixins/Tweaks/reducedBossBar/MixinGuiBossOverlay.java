package xyz.crazyh.litetweaker.mixins.Tweaks.reducedBossBar;

import net.minecraft.client.gui.GuiBossOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(GuiBossOverlay.class)
public abstract class MixinGuiBossOverlay {
    @Inject(
            method = "renderBossHealth",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/ScaledResolution;getScaledHeight()I"
            ),
            cancellable = true
    )
    private void reduceBossBar(CallbackInfo ci) {
        if (TweaksToggle.REDUCED_BOSS_BAR.getBooleanValue()) {
            ci.cancel();
        }
    }
}
