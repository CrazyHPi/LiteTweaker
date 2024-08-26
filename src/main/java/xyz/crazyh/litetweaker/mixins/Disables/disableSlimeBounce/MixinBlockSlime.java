package xyz.crazyh.litetweaker.mixins.Disables.disableSlimeBounce;

import net.minecraft.block.BlockSlime;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.DisableToggle;

@Mixin(BlockSlime.class)
public abstract class MixinBlockSlime {
    @Inject(
            method = "onLanded",
            at = @At("HEAD"),
            cancellable = true
    )
    private void noBounce(World worldIn, Entity entityIn, CallbackInfo ci) {
        if (DisableToggle.DISABLE_SLIME_BOUNCE.getBooleanValue() && entityIn instanceof EntityPlayerSP) {
            entityIn.fallDistance = 0;
            //reset player's motion so player won't bounce when turing this off on slime
            entityIn.motionY = 0.0D;
            ci.cancel();
        }
    }
}
