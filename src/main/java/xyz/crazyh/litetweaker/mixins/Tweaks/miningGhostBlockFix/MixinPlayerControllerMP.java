package xyz.crazyh.litetweaker.mixins.Tweaks.miningGhostBlockFix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Inject(
            method = "clickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;onPlayerDestroyBlock(Lnet/minecraft/util/math/BlockPos;)Z",
                    shift = At.Shift.AFTER
            )
    )
    private void miningGBFix(BlockPos loc, EnumFacing face, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.MINING_GHOST_BLOCK_FIX.getBooleanValue()) {
            final NetHandlerPlayClient client = Minecraft.getMinecraft().getConnection();
            if (client != null) {
                client.sendPacket(new CPacketPlayerTryUseItemOnBlock(loc, face, EnumHand.MAIN_HAND, 0, 0, 0));
            }
        }
    }
}
