package xyz.crazyh.litetweaker.mixins.disablePlayerSlowDown;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.Configs;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends EntityLivingBase {

    @Shadow
    private boolean handActive;

    public MixinEntityPlayerSP(World worldIn) {
        super(worldIn);
    }

    @Redirect(
            method = "onLivingUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z",
                    ordinal = 0
            )
    )
    private boolean isUsingItem(EntityPlayerSP playerSP) {
        if (TweaksToggle.DISABLE_SLOW_DOWN.getBooleanValue()) {
            return false;
        }
        return this.handActive;
    }
}
