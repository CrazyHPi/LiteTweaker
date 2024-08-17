package xyz.crazyh.litetweaker.mixins.Tweaks.placementOffset;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP {
    /*@ModifyArg(
            method = "processRightClickBlock",
            at = @At(
                    value = "NEW",
                    target = "Lnet/minecraft/network/play/client/CPacketPlayerTryUseItemOnBlock;<init>(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/EnumHand;FFF)V"
            )
    )
    private BlockPos offset(BlockPos posIn) {
        if (TweaksToggle.PLACEMENT_OFFSET.getBooleanValue()) {
            return posIn.offset(EnumFacing.UP).offset(Minecraft.getMinecraft().objectMouseOver.sideHit);
        }

        return posIn;
    }*/

    /*@Redirect(
            method = "processRightClickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V"
            )
    )
    private void modP(NetHandlerPlayClient instance, Packet<?> packetIn) {
        if (TweaksToggle.PLACEMENT_OFFSET.getBooleanValue()) {
            instance.sendPacket(new CPacketPlayerTryUseItemOnBlock(
                    ((CPacketPlayerTryUseItemOnBlock)packetIn).getPos().offset(EnumFacing.UP).offset(((CPacketPlayerTryUseItemOnBlock)packetIn).getDirection()),
                    ((CPacketPlayerTryUseItemOnBlock)packetIn).getDirection(),
                    ((CPacketPlayerTryUseItemOnBlock)packetIn).getHand(),
                    ((CPacketPlayerTryUseItemOnBlock)packetIn).getFacingX(),
                    ((CPacketPlayerTryUseItemOnBlock)packetIn).getFacingY(),
                    ((CPacketPlayerTryUseItemOnBlock)packetIn).getFacingZ()
            ));
        } else {
            instance.sendPacket(packetIn);
        }
    }*/

    /*@ModifyVariable(
            method = "processRightClickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V"
            ),
            name = "pos"
    )
    private BlockPos offset(BlockPos posIn) {
        if (TweaksToggle.PLACEMENT_OFFSET.getBooleanValue()) {
            return posIn.offset(EnumFacing.UP).offset(Minecraft.getMinecraft().objectMouseOver.sideHit);
        }

        return posIn;
    }*/
}
