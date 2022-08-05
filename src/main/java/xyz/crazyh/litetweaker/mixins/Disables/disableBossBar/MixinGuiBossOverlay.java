package xyz.crazyh.litetweaker.mixins.Disables.disableBossBar;

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
            at = @At("HEAD"),
            cancellable = true
    )
    private void dontRender(CallbackInfo ci) {
        if (TweaksToggle.DISABLE_BOSS_BAR.getBooleanValue()) {
            ci.cancel();
        }
    }
}
