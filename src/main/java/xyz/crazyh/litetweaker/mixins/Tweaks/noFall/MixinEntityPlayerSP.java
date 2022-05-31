package xyz.crazyh.litetweaker.mixins.Tweaks.noFall;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {
    /*@Inject(
            method = "onUpdate",
            at = @At("HEAD")
    )
    private void noFall(CallbackInfo ci) {
        if (TweaksToggle.NO_FALL.getBooleanValue() && !((EntityPlayerSP) (Object) this).isElytraFlying()) {
            ((EntityPlayerSP) (Object) this).fallDistance = 0;
        }
    }*/
}
