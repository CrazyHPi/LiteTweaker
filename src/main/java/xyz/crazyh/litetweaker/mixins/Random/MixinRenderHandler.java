package xyz.crazyh.litetweaker.mixins.Random;

import fi.dy.masa.minihud.event.RenderHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.crazyh.litetweaker.config.Configs;

@Mixin(RenderHandler.class)
public abstract class MixinRenderHandler {
    //Generic.MINIHUD_FONT_SCALE
    //temp tweaks, will remove after masa made a info widget save-able.
    @ModifyArg(
            method = "createStringListRenderer",
            at = @At(
                    value = "INVOKE",
                    target = "Lfi/dy/masa/malilib/overlay/widget/StringListRendererWidget;setScale(D)V"
            ),
            remap = false
    )
    private double overrideFontScale(double par1) {
        return Configs.Generic.MINIHUD_FONT_SCALE.getDoubleValue();
    }
}
