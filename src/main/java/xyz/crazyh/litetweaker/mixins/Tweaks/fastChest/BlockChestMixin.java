package xyz.crazyh.litetweaker.mixins.Tweaks.fastChest;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(BlockChest.class)
public abstract class BlockChestMixin {
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void fastChest(IBlockState state, CallbackInfoReturnable<EnumBlockRenderType> cir) {
        if (TweaksToggle.FAST_CHEST.getBooleanValue()) {
           cir.setReturnValue(EnumBlockRenderType.MODEL);
        }
    }
}
