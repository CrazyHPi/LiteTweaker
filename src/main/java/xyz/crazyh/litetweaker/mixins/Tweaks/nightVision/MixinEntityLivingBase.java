package xyz.crazyh.litetweaker.mixins.Tweaks.nightVision;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase {
    @Inject(
            method = "isPotionActive",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getNightVision(Potion potionIn, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.FAKE_NIGHT_VISION.getBooleanValue()) {
            Minecraft mc = Minecraft.getMinecraft();

            if (potionIn == MobEffects.NIGHT_VISION) {
                cir.setReturnValue(true);
            }
        }
    }
}
