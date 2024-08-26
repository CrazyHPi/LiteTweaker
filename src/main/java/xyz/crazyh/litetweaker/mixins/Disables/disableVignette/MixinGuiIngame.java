package xyz.crazyh.litetweaker.mixins.Disables.disableVignette;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.crazyh.litetweaker.config.DisableToggle;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {
    @ModifyVariable(
            method = "renderVignette",
            at = @At(
                    value = "STORE",
                    ordinal = 0
            ),
            argsOnly = true
    )
    private float test(float value){
        if (DisableToggle.DISABLE_VIGNETTING.getBooleanValue()) {
            return -14;
        } else {
            return 1.0F - value;
        }
    }
}
