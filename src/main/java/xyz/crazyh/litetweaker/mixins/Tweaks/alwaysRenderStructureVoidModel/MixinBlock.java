package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderStructureVoidModel;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.block.BlockFakeSV;
import xyz.crazyh.litetweaker.mixins.Accessors.BlockAccessor;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Inject(
            method = "registerBlocks",
            at = @At("HEAD")
    )
    private static void regSV(CallbackInfo ci) {
        BlockAccessor.invokeRegisterBlock(555, "fakesv", (new BlockFakeSV()).setTranslationKey("fakesv"));
    }
}
