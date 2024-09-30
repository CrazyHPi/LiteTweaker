package xyz.crazyh.litetweaker.mixins.Tweaks.RawInput;

import net.minecraft.util.MouseHelper;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.util.RawInput;

@Mixin(MouseHelper.class)
public abstract class MouseHelperMixin {
    @Shadow
    public int deltaX;

    @Shadow
    public int deltaY;

    @Inject(method = "mouseXYChange", at = @At("HEAD"), cancellable = true)
    private void onMouseXYChange(CallbackInfo ci) {
        if (TweaksToggle.RAW_INPUT.getBooleanValue()) {
            this.deltaX = RawInput.dx;
            RawInput.dx = 0;
            this.deltaY = -RawInput.dy;
            RawInput.dy = 0;
            ci.cancel();
        }
    }

    @Inject(method = "grabMouseCursor", at = @At("HEAD"), cancellable = true)
    private void rawGrabMouseCursor(CallbackInfo ci) {
        if (TweaksToggle.RAW_INPUT.getBooleanValue()) {
            Mouse.setGrabbed(true);
            this.deltaX = 0;
            RawInput.dx = 0;
            this.deltaY = 0;
            RawInput.dy = 0;
            ci.cancel();
        }
    }
}
