package xyz.crazyh.litetweaker.mixins.Disables.disableVignette;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {
    /*@Inject(
            method = "renderVignette",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noVignette(float lightLevel, ScaledResolution scaledRes, CallbackInfo ci) {
        if (TweaksToggle.DISABLE_VIGNETTING.getBooleanValue()) {
            ci.cancel();
        }
    }*/

    @ModifyVariable(
            method = "renderVignette",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            argsOnly = true
    )
    private float test(float value){
        if (TweaksToggle.DISABLE_VIGNETTING.getBooleanValue()) {
            return -14;
        } else {
            return 1.0F - value;
        }
    }
}
