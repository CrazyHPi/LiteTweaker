package xyz.crazyh.litetweaker.mixins.Disables.disableBlockHit;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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
        if (TweaksToggle.DISABLE_BLOCK_HIT.getBooleanValue() && BlockBlackListHelper.checkBlackListed(loc)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "onPlayerDamageBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelDamage(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.DISABLE_BLOCK_HIT.getBooleanValue() && BlockBlackListHelper.checkBlackListed(posBlock)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "processRightClickBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelRightClick(EntityPlayerSP player, WorldClient worldIn, BlockPos pos, EnumFacing direction, Vec3d vec, EnumHand hand, CallbackInfoReturnable<EnumActionResult> cir) {
        if (TweaksToggle.DISABLE_BLOCK_HIT.getBooleanValue() && BlockBlackListHelper.checkBlackListed(pos)) {
            cir.setReturnValue(EnumActionResult.PASS);
        }
    }
}
