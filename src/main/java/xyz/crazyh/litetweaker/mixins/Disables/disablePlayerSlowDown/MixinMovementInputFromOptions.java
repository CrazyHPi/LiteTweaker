package xyz.crazyh.litetweaker.mixins.Disables.disablePlayerSlowDown;

import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(MovementInputFromOptions.class)
public abstract class MixinMovementInputFromOptions extends MovementInput {
    @Redirect(
            method = "updatePlayerMoveState",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/util/MovementInputFromOptions;sneak:Z",
                    ordinal = 1
            )
    )
    private boolean isSneaking(MovementInputFromOptions instance){
        if (TweaksToggle.DISABLE_SNEAK_SLOW_DOWN.getBooleanValue()) {
            return false;
        }
        return this.sneak;
    }
}
