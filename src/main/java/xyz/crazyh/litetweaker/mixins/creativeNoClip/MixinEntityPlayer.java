package xyz.crazyh.litetweaker.mixins.creativeNoClip;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends Entity {
    @Shadow public abstract boolean isCreative();

    @Shadow public PlayerCapabilities capabilities;

    public MixinEntityPlayer(World worldIn) {
        super(worldIn);
    }

    @Redirect(
            method = "onUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;isSpectator()Z"
            )
    )
    private boolean noClip(EntityPlayer instance) {
        return instance.isSpectator() || (TweaksToggle.CREATIVE_NO_CLIP.getBooleanValue() && instance.isCreative() && instance.capabilities.isFlying);
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        if (type == MoverType.SELF || !(TweaksToggle.CREATIVE_NO_CLIP.getBooleanValue() && this.isCreative() && this.capabilities.isFlying)) {
            super.move(type, x, y, z);
        }
    }
}
