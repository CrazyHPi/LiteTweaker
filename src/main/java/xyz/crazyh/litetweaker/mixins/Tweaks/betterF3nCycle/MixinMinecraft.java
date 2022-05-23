package xyz.crazyh.litetweaker.mixins.Tweaks.betterF3nCycle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow private static Minecraft instance;

    @Redirect(
            method = "processKeyF3",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSpectator()Z"
            )
    )
    private boolean notJustSpector(EntityPlayerSP instance) {
        if (TweaksToggle.BETTER_F3N_CYCLE.getBooleanValue()) {
            return true;
        } else {
            return instance.isSpectator();
        }
    }
}
