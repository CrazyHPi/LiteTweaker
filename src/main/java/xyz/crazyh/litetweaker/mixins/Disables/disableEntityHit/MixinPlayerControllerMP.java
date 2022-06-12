package xyz.crazyh.litetweaker.mixins.Disables.disableEntityHit;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.util.EntityUtils;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Inject(
            method = "attackEntity",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableEntityHit(EntityPlayer playerIn, Entity targetEntity, CallbackInfo ci) {
        if (TweaksToggle.DISABLE_ENTITY_HIT.getBooleanValue() && EntityUtils.checkEntityBlackList(targetEntity.getClass())) {
            ci.cancel();
        }
    }
}
