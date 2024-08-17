package xyz.crazyh.litetweaker.mixins.Disables.disableEnderPearlCooldown;

import net.minecraft.item.Item;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.util.CooldownTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(ItemEnderPearl.class)
public abstract class MixinItemEnderPearl {
    /*@Redirect(
            method = "onItemRightClick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/CooldownTracker;setCooldown(Lnet/minecraft/item/Item;I)V"
            )
    )
    private void noCooldown(CooldownTracker instance, Item itemIn, int ticksIn) {
        if (TweaksToggle.DISABLE_ENDER_PEARL_COOLDOWN.getBooleanValue()) {
            return;
        }

        instance.setCooldown(itemIn, ticksIn);
    }*/
}
