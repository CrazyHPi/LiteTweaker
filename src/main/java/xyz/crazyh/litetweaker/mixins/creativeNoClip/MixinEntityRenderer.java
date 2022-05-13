package xyz.crazyh.litetweaker.mixins.creativeNoClip;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {
    @Redirect(
            method = "renderWorldPass(IFJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSpectator()Z"
            )
    )
    private boolean fixSpectator(EntityPlayerSP player) {
        return player.isSpectator() || (TweaksToggle.CREATIVE_NO_CLIP.getBooleanValue() && player.isCreative());
    }
}
