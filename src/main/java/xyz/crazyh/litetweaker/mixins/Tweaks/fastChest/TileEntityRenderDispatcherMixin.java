package xyz.crazyh.litetweaker.mixins.Tweaks.fastChest;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(TileEntityRendererDispatcher.class)
public abstract class TileEntityRenderDispatcherMixin {
    @Inject(method = "getRenderer(Ljava/lang/Class;)Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer;", at = @At("HEAD"), cancellable = true)
    private <T extends TileEntity> void fastChest(Class<? extends TileEntity> teClass, CallbackInfoReturnable<TileEntitySpecialRenderer<T>> cir) {
        if (TweaksToggle.FAST_CHEST.getBooleanValue()) {
            if (teClass == TileEntityChest.class || teClass == TileEntityEnderChest.class) {
                cir.setReturnValue(null);
            }
        }
    }
}
