package xyz.crazyh.litetweaker.mixins.Tweaks.quickBlockSwapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.input.MouseInputHandlerImpl;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    @Shadow
    public abstract EnumActionResult processRightClickBlock(EntityPlayerSP player, WorldClient worldIn, BlockPos pos, EnumFacing direction, Vec3d vec, EnumHand hand);

    @Shadow
    @Final
    private Minecraft mc;

    @Inject(
            method = "clickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void tryUse(BlockPos loc, EnumFacing face, CallbackInfoReturnable<Boolean> cir) {
        tryToSwap(loc, face);
    }

    @Inject(
            method = "clickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private void tryUse1(BlockPos loc, EnumFacing face, CallbackInfoReturnable<Boolean> cir) {
        tryToSwap(loc, face);
    }

    private void tryToSwap(BlockPos loc, EnumFacing face) {
        if (TweaksToggle.QUICK_BLOCK_SWAPPER.getBooleanValue()) {
            if (MouseInputHandlerImpl.isRightPressed && MouseInputHandlerImpl.isLeftPressed) {
                this.processRightClickBlock(this.mc.player, this.mc.world, loc, face, this.mc.objectMouseOver.hitVec, EnumHand.MAIN_HAND);
                MouseInputHandlerImpl.isRightPressed = false;
                MouseInputHandlerImpl.isLeftPressed = false;
            }
        }
    }
}
