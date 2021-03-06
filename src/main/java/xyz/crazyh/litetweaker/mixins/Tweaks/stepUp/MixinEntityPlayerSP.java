package xyz.crazyh.litetweaker.mixins.Tweaks.stepUp;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {
    @Shadow public abstract boolean isSneaking();

    @Inject(
            method = "onUpdate",
            at = @At("HEAD")
    )
    private void stepUp(CallbackInfo ci) {
        if (TweaksToggle.STEP_UP.getBooleanValue()) {
            ((EntityPlayerSP) (Object) this).stepHeight = ((EntityPlayerSP) (Object) this).isSneaking() ? 0.6F : 1.125F;
        } else {
            ((EntityPlayerSP) (Object) this).stepHeight = 0.6F;
        }
    }
}
