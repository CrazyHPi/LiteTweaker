package xyz.crazyh.litetweaker.mixins.Events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Timer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.LiteTweaker;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    @Final
    private Timer timer;

    @Shadow
    public abstract @Nullable Entity getRenderViewEntity();

    @Inject(
            method = "runGameLoop()V",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/client/renderer/EntityRenderer;updateCameraAndRender(FJ)V"
            )
    )
    private void onTick(CallbackInfo ci) {
        boolean clock = this.timer.elapsedTicks > 0;

        // Flag indicates whether we are in game at the moment
        Entity renderViewEntity = this.getRenderViewEntity();
        boolean inGame = renderViewEntity != null && renderViewEntity.world != null;

        LiteTweaker.onTick(((Minecraft) (Object) this), inGame, clock);
    }
}
