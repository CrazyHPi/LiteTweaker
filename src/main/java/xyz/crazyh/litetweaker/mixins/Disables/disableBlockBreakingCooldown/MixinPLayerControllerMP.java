package xyz.crazyh.litetweaker.mixins.Disables.disableBlockBreakingCooldown;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPLayerControllerMP {
    @Shadow
    private int blockHitDelay;

    @Inject(
            method = "onPlayerDamageBlock",
            at = @At("HEAD")
    )
    public void removeBlockHitDelay(BlockPos iblockstate, EnumFacing block, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.DISABLE_BLOCK_BREAKING_COOLDOWN.getBooleanValue() && this.blockHitDelay > 0) {
            this.blockHitDelay = 0;
        }
    }
}
