package xyz.crazyh.litetweaker.mixins.Tweaks.placementOffset;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    /*@Shadow public RayTraceResult objectMouseOver;

    @ModifyArg(
            method = "rightClickMouse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;processRightClickBlock(Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/EnumHand;)Lnet/minecraft/util/EnumActionResult;"
            )
    )
    private BlockPos offset(BlockPos posIn) {
        if (TweaksToggle.PLACEMENT_OFFSET.getBooleanValue()) {
            EnumFacing offsetSide = this.objectMouseOver.sideHit;
            return posIn.offset(EnumFacing.UP).offset(offsetSide);
        }

        return posIn;
    }*/
}
