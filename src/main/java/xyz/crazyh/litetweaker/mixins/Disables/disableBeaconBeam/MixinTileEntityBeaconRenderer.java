package xyz.crazyh.litetweaker.mixins.Disables.disableBeaconBeam;

import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(TileEntityBeaconRenderer.class)
public abstract class MixinTileEntityBeaconRenderer {
    @Inject(
            method = "render(Lnet/minecraft/tileentity/TileEntityBeacon;DDDFIF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void disableBeaconBeam(TileEntityBeacon te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, CallbackInfo ci) {
        if (TweaksToggle.DISABLE_BEACON_BEAM.getBooleanValue()) {
            ci.cancel();
        }
    }
}
