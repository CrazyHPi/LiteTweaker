package xyz.crazyh.litetweaker.mixins.Tweaks.backgroundFPSLimit;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.Configs;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Inject(
            method = "getLimitFramerate",
            at = @At("HEAD"),
            cancellable = true
    )
    private void limitFPS(CallbackInfoReturnable<Integer> cir) {
        int fps = Configs.Generic.BACKGROUND_FPS_LIMIT.getIntegerValue();
        if (!Display.isActive() && fps != -1) {
            cir.setReturnValue(fps);
        }
    }
}
