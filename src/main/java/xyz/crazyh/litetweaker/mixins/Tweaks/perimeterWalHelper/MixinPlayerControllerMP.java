package xyz.crazyh.litetweaker.mixins.Tweaks.perimeterWalHelper;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.util.BlockBlackListHelper;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Inject(
            method = "clickBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelClick(BlockPos loc, EnumFacing face, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.PERIMETER_WALL_HELPER.getBooleanValue() && BlockBlackListHelper.checkTopBlockBlackListed(loc)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "onPlayerDamageBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelDamage(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.PERIMETER_WALL_HELPER.getBooleanValue() && BlockBlackListHelper.checkTopBlockBlackListed(posBlock)) {
            cir.setReturnValue(true);
        }
    }
}
