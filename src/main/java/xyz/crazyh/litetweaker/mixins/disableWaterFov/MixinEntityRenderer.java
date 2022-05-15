package xyz.crazyh.litetweaker.mixins.disableWaterFov;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {
    @Redirect(
            method = "getFOVModifier",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"
            )
    )
    private Material getMaterial(IBlockState instance) {
        if (TweaksToggle.DISABLE_WATER_FOV.getBooleanValue()) {
            return Material.AIR;
        }
        return instance.getMaterial();
    }
}
