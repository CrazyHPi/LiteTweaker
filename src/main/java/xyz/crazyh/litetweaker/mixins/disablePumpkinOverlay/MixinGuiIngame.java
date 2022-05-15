package xyz.crazyh.litetweaker.mixins.disablePumpkinOverlay;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {
    @Inject(
            method = "renderPumpkinOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noPumpkinOverlay(ScaledResolution scaledRes, CallbackInfo ci) {
        if (TweaksToggle.DISABLE_PUMPKIN_OVERLAY.getBooleanValue()) {
            ci.cancel();
        }
    }
}
