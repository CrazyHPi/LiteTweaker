package xyz.crazyh.litetweaker.mixins.stepUp;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {
    @Inject(
            method = "onUpdate",
            at = @At("HEAD")
    )
    private void stepUp(CallbackInfo ci) {
        if (TweaksToggle.STEP_UP.getBooleanValue()) {
            ((EntityPlayerSP) (Object) this).stepHeight = 1.1F;
        } else {
            ((EntityPlayerSP) (Object) this).stepHeight = 0.6F;
        }
    }
}
