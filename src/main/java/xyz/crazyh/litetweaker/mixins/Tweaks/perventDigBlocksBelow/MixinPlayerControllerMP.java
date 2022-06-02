package xyz.crazyh.litetweaker.mixins.Tweaks.perventDigBlocksBelow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Inject(
            method = "clickBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelClick(BlockPos loc, EnumFacing face, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.PREVENT_DIG_BLOCKS_BELOW.getBooleanValue() && loc.getY() < Minecraft.getMinecraft().player.posY) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "onPlayerDamageBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelDamage(BlockPos loc, EnumFacing directionFacing, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.PREVENT_DIG_BLOCKS_BELOW.getBooleanValue() && loc.getY() < Minecraft.getMinecraft().player.posY) {
            cir.setReturnValue(true);
        }
    }
}
