package xyz.crazyh.litetweaker.mixins.Tweaks.nightVision;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {
    @Inject(
            method = "getNightVisionBrightness",
            at = @At("HEAD"),
            cancellable = true
    )
    private void alwaysNightVision(EntityLivingBase entitylivingbaseIn, float partialTicks, CallbackInfoReturnable<Float> cir) {
        if (TweaksToggle.FAKE_NIGHT_VISION.getBooleanValue()) {
            cir.setReturnValue(1F);
        }
    }
}
