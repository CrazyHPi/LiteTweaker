package xyz.crazyh.litetweaker.mixins.Disables.disableExplosionSound;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.DisableToggle;

@Mixin(WorldClient.class)
public abstract class WorldClientMixin {
    @Inject(method = "playSound(DDDLnet/minecraft/util/SoundEvent;Lnet/minecraft/util/SoundCategory;FFZ)V", at = @At("HEAD"), cancellable = true)
    private void disableExplosionSound(double x, double y, double z, SoundEvent soundIn, SoundCategory category, float volume, float pitch, boolean distanceDelay, CallbackInfo ci) {
        if (DisableToggle.DISABLE_EXPLOSION_SOUND.getBooleanValue() && soundIn == SoundEvents.ENTITY_GENERIC_EXPLODE) {
            ci.cancel();
        }
    }
}
