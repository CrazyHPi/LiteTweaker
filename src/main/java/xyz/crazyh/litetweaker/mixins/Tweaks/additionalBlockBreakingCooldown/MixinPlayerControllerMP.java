package xyz.crazyh.litetweaker.mixins.Tweaks.additionalBlockBreakingCooldown;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.Configs;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Shadow
    private int blockHitDelay;

    @Inject(
            method = "onPlayerDamageBlock",
            at = @At(
                    value = "RETURN",
                    ordinal = 4
            )
    )
    public void addBlockHitDelay(BlockPos iblockstate, EnumFacing block, CallbackInfoReturnable<Boolean> cir) {
        if (Configs.Generic.ADDITIONAL_BLOCK_BREAKING_COOLDOWN.getBooleanValue() && this.blockHitDelay == 0) {
            this.blockHitDelay = Configs.Generic.ADDITIONAL_DELAY.getIntegerValue();
        }
    }
}
