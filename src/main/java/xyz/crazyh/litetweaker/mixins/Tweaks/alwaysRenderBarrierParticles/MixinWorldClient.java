package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderBarrierParticles;

import net.minecraft.client.multiplayer.WorldClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(WorldClient.class)
public abstract class MixinWorldClient {
    @ModifyArg(
            method = "doVoidFogParticles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/WorldClient;showBarrierParticles(IIIILjava/util/Random;ZLnet/minecraft/util/math/BlockPos$MutableBlockPos;)V"
            )
    )
    private boolean alwaysRenderBarrier(boolean flag) {
        return TweaksToggle.ALWAYS_RENDER_BARRIER_PARTICLES.getBooleanValue() || flag;
    }
}
