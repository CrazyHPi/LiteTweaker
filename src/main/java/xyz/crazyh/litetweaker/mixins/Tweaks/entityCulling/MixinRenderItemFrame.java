package xyz.crazyh.litetweaker.mixins.Tweaks.entityCulling;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItemFrame;
import net.minecraft.entity.item.EntityItemFrame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(RenderItemFrame.class)
public abstract class MixinRenderItemFrame {
    @Inject(
            method = "doRender(Lnet/minecraft/entity/item/EntityItemFrame;DDDFF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cullItemFrame(EntityItemFrame entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (TweaksToggle.ENTITY_CULLING.getBooleanValue() && !Minecraft.getMinecraft().player.canEntityBeSeen(entity)) {
            ci.cancel();
        }
    }
}
