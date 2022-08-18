package xyz.crazyh.litetweaker.mixins.Disables.disableArmorStandRendering;

import net.minecraft.client.renderer.entity.RenderArmorStand;
import net.minecraft.entity.item.EntityArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(RenderArmorStand.class)
public abstract class MixinRenderArmorStand {
    @Inject(
            method = "doRender(Lnet/minecraft/entity/item/EntityArmorStand;DDDFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doNotRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (TweaksToggle.DISABLE_ARMOR_STAND_RENDERING.getBooleanValue()) {
            ci.cancel();
        }
    }
}
